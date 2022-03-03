package project.green.shop.DAO;

import java.util.LinkedHashMap;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.green.shop.model.CartItem;

@Service
@Transactional(rollbackFor = { Exception.class, Throwable.class })
public class CartItemService {
	@Autowired
	private CartItemRepo cartrepo;

	@Autowired
	ProductService proservice;

	public Set<CartItem> findById(Integer id) {
		return cartrepo.findByIdCustumer(id);
	}

	public boolean CheckIfExsist(Integer cus_id, Integer pro_id) throws Exception {
		if (cartrepo.findByCustumerAndProduct(cus_id, pro_id) != null) {
			return true;
		}
		return false;
	}
	
	public void SaveToCart(CartItem cartitem) {
		cartrepo.save(cartitem);
	}

	public void DelProduct(Integer id) {
		cartrepo.deleteById(id);
	}
}