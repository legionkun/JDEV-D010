package project.green.shop.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import project.green.shop.DAO.CartItemService;
import project.green.shop.DAO.CateloryService;
import project.green.shop.DAO.CustumerService;
import project.green.shop.DAO.ProductService;
import project.green.shop.model.CartItem;
import project.green.shop.model.Custumer;
import project.green.shop.model.ImagesProduct;
import project.green.shop.model.Product;
import project.green.shop.security.MyUserDetails;

@RestController
public class ProductController {

	@Autowired
	private ProductService pro;

	@Autowired
	CateloryService service;

	@GetMapping("/product")
	public List<Product> showProduct(Model model) {
		return AllProduct(model, 1, null, null);
	}

	@GetMapping("/product/{pagenumber}")
	public List<Product> AllProduct(Model model, @PathVariable("pagenumber") int currentpage,
			@Param("sortBy") String sortBy, @Param("sortDirection") String sortDirection) {
		String direction = "asc";
		if (sortDirection != null && sortDirection.equals("asc")) {
			direction = "asc";
		} else {
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
		model.addAttribute("totalitem", totalitem);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("listproduct", list);
		model.addAttribute("direction", direction);
		model.addAttribute("sortBy", sortBy);
		System.out.println("sortBy: " + sortBy + " :  " + "sortDirection");
		System.out.println(" Derection : " + direction);
		System.out.println("currentpage " + currentpage);
		System.out.println(" SortDerection : " + sortDirection);
		return list;
	}

	@GetMapping("/product_code/{id}")
	public Product ProductDetails(@PathVariable("id") int id, Model model) {
		Product product = pro.findByIdProduct(id);
		List<ImagesProduct> ima = pro.getAllImage(id);
		model.addAttribute("ima", ima);
		model.addAttribute(product);
		return product;
	}

	@Autowired
	CartItemService cartservice;

	@Autowired
	CustumerService cusservice;

	@GetMapping("/product_id/{proId}")
	public Set<CartItem> SettoCartItem(@PathVariable("proId") int id, @AuthenticationPrincipal MyUserDetails user)
			throws Exception {		
		Set<CartItem> cartlist = cartservice.findById(user.getId());
		if (cartservice.CheckIfExsist(user.getId(), id) == false) {
			System.err.println("Sản phẩm này đã được add ");
		} else {
			CartItem cartitem = new CartItem();
			Product prod = pro.findById(id);
			System.out.println("Mã sản phẩm " + id);
			cartitem.setProduct(prod);
			Custumer cus = cusservice.getById(user.getId());
			cartitem.setCustumer(cus);
			cartservice.SaveToCart(cartitem);		
		}		
		return cartlist;
	}
}
