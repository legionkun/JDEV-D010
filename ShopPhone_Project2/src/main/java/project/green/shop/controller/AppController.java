package project.green.shop.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.Base64;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;





import project.green.shop.DAO.CustumerService;
import project.green.shop.handle.Utility;
import project.green.shop.helper.FileUpLoadHelper;
import project.green.shop.model.Custumer;
import project.green.shop.security.MyUserDetails;


@Controller
public class AppController {
	@Autowired 
	private CustumerService addclient;
	
	@Autowired
	private JavaMailSender MailSendder;
	
	@GetMapping("/")
	public String showIndexView() {
		
		return "index";
	}
	
	@GetMapping("/Forgot")
	public String ShowForGot()
	{
		return "forgot";
	}
	
	@RequestMapping("/Login")
	public String ShowLogin()
	{
		return "Login";
	}
	
	@Autowired
	private CustumerService cus;
	
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String ShowProfile(Model model,@AuthenticationPrincipal MyUserDetails user )
	{		
		String u = user.getUsername();
		model.addAttribute("custumer",cus.getByEmail(u));
		return "profile";
	}
	
	@PostMapping(value = {"/profile/update"}, consumes = {"multipart/form-data"})
	public String UpdateProfile(Custumer custumer,@AuthenticationPrincipal MyUserDetails user,@RequestParam("Imageg") MultipartFile multi) throws IOException
	{
			if(!multi.isEmpty())
			{
			String filename= org.springframework.util.StringUtils.cleanPath(multi.getOriginalFilename());
			String encodedString = Base64.getEncoder().encodeToString(filename.getBytes());
			custumer.setImage(encodedString);
			System.out.println("1111111111111"+encodedString);
			System.out.println("saveProfile photo path: " + filename + " for user id" + user.getUsername());
			custumer.setEmail(user.getUsername());
			custumer.setId(user.getId());
			custumer.setEnabled(true);
			Custumer custum = cus.editCustumer(custumer);
			String UploadDir= "custumer-photo/" + custum.getId();
			System.out.println("3333333"+custum.getId());
			System.out.println(multi);
			try {
				FileUpLoadHelper.SaveFile(UploadDir, filename, multi);
			}
			catch(Exception E) {}
			}else if(custumer.getImage().isEmpty()) {
			custumer.setImage(null);
			cus.editCustumer(custumer);
			}
	

		return "redirect:/";		
	}
	
	@RequestMapping("/aboutus")
	public String showUS() {
		
		return "aboutus";
	}
	
	@RequestMapping("/quydinhgiaohang")
	public String showQD() {
		
		return "quydinhgiaohang";
	}
	
	 @RequestMapping(value="/logout", method=RequestMethod.GET)  
	    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
	        if (auth != null){      
	           new SecurityContextLogoutHandler().logout(request, response, auth);  
	        }  
	         return "redirect:/";  
	     }  
	 
	@RequestMapping("/register")//Lấy para và gán vào model 
	public String newProduct(Model model, HttpServletRequest request)
	{
		Custumer us_1 =new Custumer();
		model.addAttribute("Custumer",us_1);
		return "register";		
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)//tạo acc và gửi mail qua gmail
	public String saveClient(@ModelAttribute("Custumer")Custumer custumer,HttpServletRequest request) throws MessagingException, UnsupportedEncodingException
	{
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
	
	
	@GetMapping("/verify")
	public String Active(@Param("code")String code, Model model)
	{
		boolean verified = cus.activeUser(code);
		if(verified == false)
		{
			return "redirect:/";
		}
		else
		return "Login";
	}
	
	@GetMapping("/403")
	public String Error ()
	{
		return "403";
	}
	
	@GetMapping("/access-denied")
    public String getAccessDenied() {
        return "/error/accessDenied";
    }
	
}