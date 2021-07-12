package project.green.shop.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bytebuddy.utility.RandomString;
import project.green.shop.DAO.CustumerService;
import project.green.shop.handle.Utility;
import project.green.shop.model.Custumer;

@Controller
public class RegisterAndForgotPassController {
	@Autowired
	private CustumerService cus;
	
	@Autowired 
	private CustumerService addclient;
	
	@Autowired
	private JavaMailSender MailSendder;
	
	@RequestMapping("/register")//Lấy para và gán vào model 
	public String newProduct(Model model, HttpServletRequest request)
	{
		Custumer us_1 =new Custumer();
		model.addAttribute("Custumer",us_1);
		return "register";		
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)//tạo acc,check mail và gửi mail qua gmail
	public String saveClient(@ModelAttribute("Custumer")Custumer custumer,HttpServletRequest request,Model model) throws MessagingException, UnsupportedEncodingException
	{
		boolean CheckMail = cus.CheckEmail(custumer.getEmail());
		if(CheckMail==false)
		{
            model.addAttribute("logError","logError");
			return "register";
		}else {
		addclient.RegisterCustomer(custumer);
		String SiteURL= Utility.getSiteURL(request);
		MimeMessage message = MailSendder.createMimeMessage();
		MimeMessageHelper helper= new MimeMessageHelper(message, true);
		String mailSubject= "Chào mừng bạn đến với shop bán hàng";
		String mailContent= "Nếu đây là bạn xin vui lòng kích hoạt tài khoản bằng đường link dươi đây";
		String VeryfiURL = SiteURL +"/verify?code=" +custumer.getVarificationCode();
		String senderName = "Jupiter Elf";
		mailContent += "<h3><a href=\"" + VeryfiURL + "\"> KÍCH HOẠT TÀI KHOẢN</a></h3>";
		mailContent += "<hr><img src='cid:Logoimage'/></hr>";
		helper.setSubject(mailSubject);
		helper.setText(mailContent, true);
		helper.setFrom("webbanhang.shop.vn", senderName);
		helper.setTo(custumer.getEmail());
		ClassPathResource resoures = new ClassPathResource("/static/images/phoenix.jpg");
		helper.addInline("Logoimage", resoures);
		MailSendder.send(message);	
		return "redirect:/";
		}
	}
	
	@GetMapping("/forgot")
	public String ShowForGot()
	{
		return "forgot";
	}
	
	@PostMapping("/forgot/getpass")
	public String GetPW(HttpServletRequest request, Model model) throws MessagingException, UnsupportedEncodingException 
	{	boolean checkmail = cus.CheckEmail(request.getParameter("EMAIL"));
		if(checkmail== true)
		{
			model.addAttribute("logError","logError");
			return "/forgot";
		}else {
			model.addAttribute("logsuccess","logsuccess");
		String email = request.getParameter("EMAIL");
		String token = RandomString.make(64);
		String getLinkResetPW = Utility.getSiteURL(request) + "/reset_password?token=" +token;
		System.out.println(" "+email + " "+ token );
		System.out.println(" "+getLinkResetPW);
		cus.getPW(email, token);
		
		MimeMessage message = MailSendder.createMimeMessage();
		MimeMessageHelper helper= new MimeMessageHelper(message, true);
		String mailSubject= "Chào mừng bạn đến với shop bán hàng";
		String mailContent= "CHÚNG TÔI GỬI LẠI CHO BẠN MẬT KHẨU BẠN ĐÃ QUÊN";
		String senderName = "Jupiter Elf";
		mailContent += "<h3><a href=\"" + getLinkResetPW + "\"> RESET HERE!!!!!!!</a></h3>";
		mailContent += "<hr><img src='cid:Logoimage'/></hr>";
		helper.setSubject(mailSubject);
		helper.setText(mailContent, true);
		helper.setFrom("webbanhang.shop.vn", senderName);
		helper.setTo(email);
		ClassPathResource resoures = new ClassPathResource("/static/images/phoenix.jpg");
		helper.addInline("Logoimage", resoures);
		MailSendder.send(message);	
			return "redirect:/forgot";
		}
	}
	
	@GetMapping("/reset_password")
	public String ActiveChangePass(@Param(value="token") String token, Model model)
	{
		boolean tokenPW = cus.activeChangePass(token);
		if(tokenPW == false)
		{
			return "/403";
		}
		model.addAttribute("token",token);
		return "/changepass";
	}
	
	@PostMapping("/reset_password")
	public String ChangePass(HttpServletRequest request)
	{
		String token = request.getParameter("token");
		String password = request.getParameter("password2");
		Custumer custumer = cus.GetBYTOKEN(token);
		cus.ChangePass(custumer, password);
		return "redirect:/Login";
	}
	
	@RequestMapping("/Changepass")
	public String ShowChangePage()
	{
		return "/changepass";
	}
	
	@GetMapping("/verify")
	public String Active(@Param("code")String code)
	{
		boolean verified = cus.activeUser(code);
		if(verified == false)
		{
			return "redirect:/";
		}
		else
		return "Login";
	}
}
