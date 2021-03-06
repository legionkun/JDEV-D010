package project.green.shop.DAO;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.green.shop.model.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer>{
@Query("Select u from CartItem u where custumer_id=?1")
public Set<CartItem> findByIdCustumer(Integer id);

@Query("Select u from CartItem u where custumer_id=?1 and product_id=?1")
public CartItem findByCustumerAndProduct(Integer cus_id,Integer pro_id);

@Query("Update CartItem u Set u.quantity=?1  where product_id=?1")
public void updateByProID(Integer qtity,Integer proid);

}
