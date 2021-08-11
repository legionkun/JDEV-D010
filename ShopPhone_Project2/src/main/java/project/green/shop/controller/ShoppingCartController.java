package project.green.shop.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.green.shop.DAO.CartItemService;
import project.green.shop.DAO.ProductService;
import project.green.shop.model.CartItem;
import project.green.shop.model.Product;
import project.green.shop.security.MyUserDetails;

@Controller
public class ShoppingCartController {

@Autowired
private CartItemService cartservice;

@Autowired
private ProductService proser;

@RequestMapping(value="/cart", method = RequestMethod.GET)
public String ShowShoppingCart(Model model, @AuthenticationPrincipal MyUserDetails user)
{
	if(user!= null)
	{
	List<Product> prolist = proser.getByNewProduct();
	model.addAttribute("prolist", prolist);
	List<CartItem> cartlist= cartservice.findById(user.getId());
	model.addAttribute("cartlist", cartlist);
	return "cart";
	}return "Login";
}

@GetMapping("/cart/delete/{Id}")
public String DelProduct(@PathVariable("Id") int id )
{
	cartservice.DelProduct(id); 
	return "redirect:/cart";
}

@GetMapping("/cart/edit/{quantity}/{Id}")
public String EditCart(@RequestParam(value="quantity",required = false) Integer quantity,@PathVariable("Id") int id)
{
	
	return "redirect:/cart";
	}
}
