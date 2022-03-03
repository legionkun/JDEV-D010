package project.green.shop.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.green.shop.model.ImagesProduct;
import project.green.shop.model.Product;

@Service
@Transactional(rollbackFor = { Exception.class, Throwable.class })
public class ProductService {
	@Autowired
	private ProductsRepo pro;

	@Autowired
	private ProductsRepo2 pro2;

	public Page<Product> getAllProduct(int pagenumber, String sortBy, String sortDirection) {
		Sort sort = Sort.by(sortBy);
		if (sortDirection.equals("asc")) {
			sort = sort.ascending();// tăng dần
		} else {
			sort = sort.descending();// giảm dần
		}
		Pageable pageable;
		if (pagenumber >= 1) {
			pageable = PageRequest.of(pagenumber - 1, 16, sort);
		} else {
			pageable = PageRequest.of(0, 16, sort);
		}
		return pro.findAll(pageable);
	}

	public Product findByIdProduct(int id) {
		return pro.getByProductCode(id);
	}

	public List<ImagesProduct> getAllImage(int id) {
		System.out.println(" On service " + id);
		return pro2.getImageList(id);
	}

	public List<Product> getByNewProduct() {
		return pro.getNewProduct();
	}

	public Product findById(int id) throws Exception  { 
		if(pro.getById(id)==null)
		{
			throw new IllegalAccessException("Product ID not found !!!");
		}
		return pro.getById(id);
	}

	public List<Product> getByBestSell() {
		return pro.getBestSeller();
	}

	public List<Product> getByPriceSell() {
		return pro.getprice_sell();
	}

	public boolean checkPrice_sell(int id) {
		Product prod = pro.getByProductCode(id);
		if (prod.getPrice_sell() == 0) {
			return false;
		}
		return true;
	}
}
