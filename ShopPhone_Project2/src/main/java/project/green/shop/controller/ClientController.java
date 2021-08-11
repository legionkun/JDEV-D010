package project.green.shop.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.green.shop.DAO.CartItemService;
import project.green.shop.DAO.CateloryService;
import project.green.shop.DAO.CustumerService;
import project.green.shop.DAO.ProductService;
import project.green.shop.helper.FileUpLoadHelper;
import project.green.shop.model.CartItem;
import project.green.shop.model.Custumer;
import project.green.shop.model.Product;
import project.green.shop.security.MyUserDetails;


@Controller
public class ClientController {
	@Autowired
	CateloryService service;
	
	@Autowired
	ProductService proser;
	
	@Autowired
	CartItemService cartservice;
	@GetMapping("/")
	public String showIndexView(Model model,@AuthenticationPrincipal MyUserDetails user) {
		List<Product> prolist = proser.getByNewProduct();
		model.addAttribute("prolistbest_sell", prolist);
		List<Product> propricelist = proser.getByPriceSell();
		model.addAttribute("listprice_sell", propricelist);
		if(user !=null) {
		List<CartItem> cartlist= cartservice.findById(user.getId());
		model.addAttribute("cartlist", cartlist);
		}
		return "index";
	}
	
	@RequestMapping("/Login")
	public String ShowLogin()
	{
		return "Login";
	}
	@RequestMapping("/adminsapo")
	public String ShowAdminPage()
	{
		return "adminsapo";
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
		Integer u = user.getId();
		model.addAttribute("custum",cus.getById(u));
		System.out.println(" Image on when showing by STRING " + user.getImage());
		return "profile";
	}
	
	@PostMapping(value = {"/profile/update"}, consumes = {"multipart/form-data"})
	public String UpdateProfile(@ModelAttribute(name="custum") Custumer custumer,@AuthenticationPrincipal MyUserDetails user,@RequestParam("Imageg") MultipartFile multi, RedirectAttributes redirectAttributes) throws IOException
	{		redirectAttributes.addFlashAttribute("message", "Đã Lưu");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			if(!multi.isEmpty())
			{
			
			String filename= org.springframework.util.StringUtils.cleanPath(multi.getOriginalFilename());
			//String encodedString = Base64.getEncoder().encodeToString(filename.getBytes());
			custumer.setImage(filename);
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
	 
	@GetMapping("/403")
	public String Error ()
	{
		return "403";
	}
	
	@GetMapping("/access-denied")
    public String getAccessDenied() {
        return "/error/accessDenied";
    }
	
	//@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	//public String showProductCategoryView(@PathVariable("name") String name, Model model) {
		
		//Catelory catelory = service.getCateloryByName(name);	
		//List<Catelory> listCategories = catelory.getListSubCategory();
		//List<Catelory> listParents = service.getParents(catelory);
		//model.addAttribute("listParents", listParents);
		//model.addAttribute("listCategories", listCategories);
		
	//	return "product";
	//}

}
