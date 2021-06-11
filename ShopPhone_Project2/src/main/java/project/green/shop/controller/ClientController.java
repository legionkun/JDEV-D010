package project.green.shop.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bytebuddy.utility.RandomString;
import project.green.shop.DAO.CustumerService;
import project.green.shop.DAO.ProductService;
import project.green.shop.handle.Utility;
import project.green.shop.helper.FileUpLoadHelper;
import project.green.shop.model.Custumer;
import project.green.shop.model.Product;
import project.green.shop.security.MyUserDetails;


@Controller
public class ClientController {
	@Autowired 
	private CustumerService addclient;
	
	@Autowired
	private JavaMailSender MailSendder;
	
	@GetMapping("/")
	public String showIndexView() {
		
		return "index";
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
	
	@RequestMapping("/Login")
	public String ShowLogin()
	{
		return "Login";
	}
	
	@GetMapping("/Login/Error")
	public String LoginError(HttpServletRequest request, Model model)
	{
		model.addAttribute("logError","logError");
		return "Login";	
	}
	
	@Autowired
	private CustumerService cus;
	
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String ShowProfile(Model model,@AuthenticationPrincipal MyUserDetails user ) throws IOException
	{		
		String u = user.getUsername();
		model.addAttribute("custumer",cus.getByEmail(u));
		//model.addAttribute("Base64Image", user.getImage());
		System.out.println(" Image on when showing by STRING" + user.getImage());
		return "profile";
	}
	
	@PostMapping(value = {"/profile/update"}, consumes = {"multipart/form-data"})
	public String UpdateProfile(@ModelAttribute(name="custumer") Custumer custumer,@AuthenticationPrincipal MyUserDetails user,@RequestParam("Imageg") MultipartFile multi, RedirectAttributes redirectAttributes) throws IOException
	{		redirectAttributes.addFlashAttribute("message", "Đã Lưu");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			if(!multi.isEmpty())
			{
			
			String filename= org.springframework.util.StringUtils.cleanPath(multi.getOriginalFilename());
			//String encodedString = Base64.getEncoder().encodeToString(filename.getBytes());
			custumer.setImage(filename.getBytes());
			//System.out.println("1111111111111"+encodedString);
			System.out.println("saveProfile photo path: " + filename + " for user " + user.getUsername());
			custumer.setEmail(user.getUsername());
			custumer.setId(user.getId());
			custumer.setEnabled(true);
			custumer.setPassword(user.getPassword());
			Custumer custum = cus.editCustumer(custumer);
			String UploadDir= "custumer-photo/" + custum.getId();
			System.out.println("3333333"+custum.getId());
			System.out.println(multi);
		    try {
				FileUpLoadHelper.SaveFile(UploadDir, filename, multi);
			}
			catch(Exception E) {}
			
			}else if(multi.isEmpty()||multi ==null) {
			custumer.setImage(null);
			custumer.setId(user.getId());
			custumer.setEmail(user.getUsername());
			custumer.setEnabled(true);
			custumer.setPassword(user.getPassword());
			//System.out.println(" IMAGE setter default String "+user.getImage());
			//System.out.println(" IMAGE setter default BYTE "+user.getImage().getBytes());		

		    cus.editCustumer(custumer);
			}
		return "redirect:/profile";		
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
	
	
	@GetMapping("/403")
	public String Error ()
	{
		return "403";
	}
	
	@GetMapping("/access-denied")
    public String getAccessDenied() {
        return "/error/accessDenied";
    }
	
	@Autowired
	private ProductService pro;
	@GetMapping("/product")
	public String showProduct(Model model) {
		return AllProduct(model, 1, null, null);
	}
	
	@GetMapping("/product/{pagenumber}")
	public String AllProduct(Model model,@PathVariable("pagenumber") int currentpage,@Param("sortBy") String sortBy, 
			 @Param("sortDirection") String sortDirection)
	{
		String direction = "asc";
		if (sortDirection != null && sortDirection.equals("asc")) {
			direction = "desc";
		}
		
		if (sortBy == null) {
			sortBy = "price";
		}
		Page<Product> page = pro.getAllProduct(currentpage, sortBy, direction);
		int totalitem = page.getNumberOfElements();
		int totalpage = page.getTotalPages();
		List<Product> list = page.getContent();
		model.addAttribute("currentpage", currentpage);
		model.addAttribute("totalitem",totalitem);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("listproduct",list);
		model.addAttribute("direction", direction);
		model.addAttribute("sortBy", sortBy);
		System.out.println("sortBy: " + sortBy  + " ==  " + "sortDirection");
		return "product";	
	}
	
}
