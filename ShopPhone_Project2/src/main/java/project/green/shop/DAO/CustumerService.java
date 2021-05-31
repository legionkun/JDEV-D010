package project.green.shop.DAO;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bytebuddy.utility.RandomString;
import project.green.shop.model.Custumer;
import project.green.shop.auth.EnumProvider;
@Service
@Transactional
public class CustumerService implements UserDetailsService{
	@Autowired
	private CustumerRepo cusrepo;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email1) throws UsernameNotFoundException {
		Custumer user = cusrepo.getByEmail(email1);
		if(user==null)
		{ 
			System.out.println("Ko tìm thấy tài khoản");
			throw new UsernameNotFoundException(project.green.shop.helper.AppString.usernameNotFound);
		}
		System.out.println(" " +user.getEmail1());
		return new project.green.shop.security.MyUserDetails(user);
	}
	public List<Custumer> AllCustomer() {
		return cusrepo.findAll();
	}
	
	public Custumer getByEmail(String email1) {
		return cusrepo.getByEmail(email1);
	}
	public CustumerService() {}
	public void AddNewCustomer(String email1, String hoten, String diachi1, String sdt1, EnumProvider provider) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		Date createtime = new Date();
		Custumer customer = new Custumer();
		customer.setSdt1(sdt1);
		customer.setDiachi1(diachi1);
		customer.setEnabled(true);
		customer.setEmail1(email1);
		customer.setHoten(hoten);
		customer.setCreatetime(createtime);
		customer.setLasttime(createtime);
		customer.setAuth_provider(provider);	
		customer.setPassword1(encoder.encode(("")));
		cusrepo.save(customer);
	}
	
	public void updateCustomer(Custumer customer, String hoten, String diachi1, String sdt1, EnumProvider provider) {

		customer.setDiachi1(diachi1);
		customer.setHoten(hoten);
		customer.setLasttime(new Date());
		customer.setSdt1(sdt1);
		cusrepo.save(customer);
	}
	public void editCustumer(Custumer cus)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		cus.setDiachi1(cus.getDiachi1());
		cus.setHoten(cus.getHoten());
		cus.setSdt1(cus.getSdt1());
		cus.setPassword1(encoder.encode("")+cus.getPassword1());
		cusrepo.save(cus);
	}
	
	//tạo mới tk chưa kích hoạt
	public void RegisterCustomer(Custumer custumer) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		Date date = new Date();
		custumer.setCreatetime(date);
		custumer.setLasttime(date);
		custumer.setPassword1(encoder.encode(custumer.getPassword1()));
		custumer.setEnabled(false);
		String RandomCode = RandomString.make(64);
		custumer.setVarificationCode(RandomCode);
		EnumProvider provider = EnumProvider.BASIC;
		custumer.setAuth_provider(provider);
		cusrepo.save(custumer);
	}

	//kích hoạt
	@Transactional
	public boolean activeUser(String varifycationCode)
	{
		Custumer user = cusrepo.getByVeryfication(varifycationCode);
		if(user==null || user.isEnabled())
				{
			System.out.println(""+cusrepo.getByVeryfication(varifycationCode));
			return false;
				}
		else 
		{
			 cusrepo.enabled(user.getId());
			 return true;
		}
		
	}

}
