package project.green.shop.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.green.shop.model.Catelory;

public interface CateloryRepo extends JpaRepository<Catelory, Integer> {

	@Query("SELECT c FROM Catelory c WHERE c.parent = NULL")
	public List<Catelory> getRootCategory();
	
	@Query ("Select u from Catelory u where u.name = ?1")
	public Catelory getByName(String name);
}
