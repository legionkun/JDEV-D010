package project.green.shop.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.green.shop.model.ImagesProduct;
import project.green.shop.model.Product;


public interface ProductsRepo extends JpaRepository<Product, Integer>{
@Query("Select u from Product u where u.product_code = :id ")
public Product getByProductCode(@Param("id")Integer id);

@Query("Select u from ImagesProduct u where u.product_code = ?1")
public List<ImagesProduct> getImageList(Integer id);

@Query("Select u from Product u where u.new_product= 1")
public List<Product> getNewProduct();

@Query("Select u from Product u where u.Id=?1")
public Product getById(Integer id);

@Query("Select u from Product u where u.best_sell= 1")
public List<Product> getBestSeller();

@Query("Select u from Product u where u.price_sell != 0")
public List<Product> getprice_sell();

}
