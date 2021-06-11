package project.green.shop.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import project.green.shop.model.Product;


public interface ProductsRepo extends JpaRepository<Product, Integer>{

}
