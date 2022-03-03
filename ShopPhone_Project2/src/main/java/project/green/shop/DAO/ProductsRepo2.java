package project.green.shop.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.green.shop.model.ImagesProduct;


public interface ProductsRepo2 extends JpaRepository<ImagesProduct, Integer>{

@Query("Select u from ImagesProduct u where u.product_code = ?1")
public List<ImagesProduct> getImageList(Integer id);
}
