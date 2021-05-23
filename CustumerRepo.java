package project.green.shop.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.green.shop.model.Custumer;

public interface CustumerRepo extends JpaRepository<Custumer, Integer>{
	//Truy cập tài khoản
	@Query("SELECT u FROM Custumer u WHERE u.email1 = :email1")
	public Custumer getByEmail(@Param("email1") String email1);
	
	//kích hoạt tài khoản
	@Query("update Custumer u set u.enabled = true where u.id = ?1")
	@Modifying
	public void enabled(Integer id);
	
	//Truy vấn Veryfication Code
	@Query("Select u from Custumer u where u.varificationCode= ?1")
	public Custumer getByVeryfication(String code);
}
