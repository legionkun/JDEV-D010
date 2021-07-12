package project.green.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

@GetMapping("/cart")
public String ShowShoppingCart(Model model, @AuthenticationPrincipal MyUserDetails user)
{
	List<Product> prolist = proser.getByNewProduct();
	model.addAttribute("prolist", prolist);
	List<CartItem> cartlist= cartservice.findById(user.getId());
	model.addAttribute("cartlist", cartlist);
	return "cart";
	}
}
