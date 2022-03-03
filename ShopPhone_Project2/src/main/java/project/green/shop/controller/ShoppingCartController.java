package project.green.shop.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.green.shop.DAO.CartItemService;
import project.green.shop.DAO.ProductService;
import project.green.shop.model.CartItem;
import project.green.shop.model.Product;
import project.green.shop.security.MyUserDetails;

@RestController
public class ShoppingCartController {

	@Autowired
	private CartItemService cartservice;

	@Autowired
	private ProductService proser;

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public Set<CartItem> ShowShoppingCart(Model model, @AuthenticationPrincipal MyUserDetails user) {
		//if (user != null) {
			List<Product> prolist = proser.getByNewProduct();
			model.addAttribute("prolist", prolist);
			Set<CartItem> cartlist =  cartservice.findById(user.getId());
			model.addAttribute("cartlist", cartlist);
			
	//	}
		return cartlist;
		//return Login;
	}

	@GetMapping("/cart/delete/{Id}")
	public Set<CartItem> DelProduct(@PathVariable("Id") int id,@AuthenticationPrincipal MyUserDetails user) throws Exception   {
		if(cartservice.CheckIfExsist(id, user.getId())==true) {
		cartservice.DelProduct(id);
		HashSet<CartItem> cartlist = (HashSet<CartItem>) cartservice.findById(user.getId());
		return cartlist;
		} throw new RuntimeException("Không có đơn hàng nào trên");
	}

	@GetMapping("/cart/edit/{quantity}/{Id}")
	@Transactional(rollbackFor = {Exception.class, Throwable.class})
	public Set<CartItem> EditCart(@RequestParam(value = "quantity", required = false) Integer quantity,
			@PathVariable("Id") int id,Model model, @AuthenticationPrincipal MyUserDetails user) {
		Set<CartItem> cartlist =  cartservice.findById(user.getId());
		model.addAttribute("cartlist", cartlist);
		return cartlist;
	}
}
