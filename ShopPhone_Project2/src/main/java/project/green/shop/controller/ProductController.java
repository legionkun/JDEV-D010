package project.green.shop.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import project.green.shop.DAO.CartItemService;
import project.green.shop.DAO.CateloryService;
import project.green.shop.DAO.CustumerService;
import project.green.shop.DAO.ProductService;
import project.green.shop.model.CartItem;
import project.green.shop.model.Custumer;
import project.green.shop.model.ImagesProduct;
import project.green.shop.model.Product;
import project.green.shop.security.MyUserDetails;

@Controller
public class ProductController {

	@Autowired
	private ProductService pro;
	
	@Autowired
	CateloryService service;
	
	@GetMapping("/product")
	public String showProduct( Model model) {
		return AllProduct(model, 1, null, null);
	}
	
	@GetMapping("/product/{pagenumber}")
	public String AllProduct(Model model,@PathVariable("pagenumber") int currentpage,@Param("sortBy") String sortBy, 
			 @Param("sortDirection") String sortDirection)
	{
		String direction = "asc";
		if (sortDirection!= null && sortDirection.equals("asc")) 
		{
			direction = "asc";
		}else {
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
		System.out.println("sortBy: " + sortBy  + " :  " + "sortDirection");
		System.out.println(" Derection : " +direction);
		System.out.println("currentpage " +currentpage);
		System.out.println(" SortDerection : " +sortDirection);
		//Catelory catelory = service.getCateloryByName("product");	
		//List<Catelory> listCategories = catelory.getListSubCategory();
		//List<Catelory> listParents = service.getParents(catelory);
		//model.addAttribute("listParents", listParents);
		//model.addAttribute("listCategories", listCategories);
		return "product";
	}
	
	@GetMapping("product_code/{id}")
	public String ProductDetails(@PathVariable("id") int id,Model model)
	{
		Product product = pro.findByIdProduct(id);	
		List<ImagesProduct> ima = pro.getAllImage(id);
		model.addAttribute("ima", ima);
		model.addAttribute(product);
		return "/product_details";
	}
	
	@Autowired
	CartItemService cartservice;
	
	@Autowired
	CustumerService cusservice;
	
	
	@GetMapping("/product_id/{proId}")
	public String SettoCartItem(@PathVariable("proId")int id,@AuthenticationPrincipal MyUserDetails user)
	{	if(user != null)
	{
		CartItem cartitem = new CartItem();
		Product prod = pro.findById(id); 
		cartitem.setProduct(prod);
		Custumer cus = cusservice.getById(user.getId());
		cartitem.setCustumer(cus);
		cartservice.SaveToCart(cartitem);
		return "forward:/product";
	}return "Login";
	}

}
	

