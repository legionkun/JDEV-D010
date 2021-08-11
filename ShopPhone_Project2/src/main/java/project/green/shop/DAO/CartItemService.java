package project.green.shop.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.green.shop.model.CartItem;

@Service
@Transactional
public class CartItemService {
@Autowired
private CartItemRepo cartrepo;

@Autowired
ProductService proservice;

public List<CartItem> findById(Integer id)
{
	return cartrepo.findByIdCustumer(id);
	}

public void SaveToCart(CartItem cartitem)
{
	cartrepo.save(cartitem);
}

public void DelProduct(Integer id)
{
	cartrepo.deleteById(id);
	}
}