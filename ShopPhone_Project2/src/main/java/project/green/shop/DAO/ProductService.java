package project.green.shop.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.green.shop.model.Product;


@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductsRepo pro;
	public Page<Product> getAllProduct(int pagenumber, String sortBy, String sortDirection)
	{
		Sort sort = Sort.by(sortBy);
		if (sortDirection.equals("asc")) {
			sort = sort.ascending();
		} else {
			sort = sort.descending();
		}
		Pageable pageable ;
		if (pagenumber >= 1) {
			pageable = PageRequest.of(pagenumber - 1,16, sort);
		} else {
			pageable = PageRequest.of(0, 16, sort);
		}
		return pro.findAll(pageable);
	}
}
