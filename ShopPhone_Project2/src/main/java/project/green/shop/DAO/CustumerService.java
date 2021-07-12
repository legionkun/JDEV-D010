package project.green.shop.DAO;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bytebuddy.utility.RandomString;
import project.green.shop.model.Custumer;
import project.green.shop.security.MyUserDetails;
import project.green.shop.auth.CustomOauth2;
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
		System.out.println(" " +user.getEmail());
		return new project.green.shop.security.MyUserDetails(user);
	}
	
	public List<Custumer> AllCustomer() {
		return cusrepo.findAll();
	}
	public Custumer getById(Integer id)
	{
		return cusrepo.getById(id);
	}
	
	public Custumer getByEmail(String email1) {
		return cusrepo.getByEmail(email1);
	}
	
	public CustumerService() {}
	
	public void AddNewCustomer(String email1, String hoten, String diachi1, String sdt1, EnumProvider provider) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		Date createtime = new Date();
		Custumer customer = new Custumer();
		customer.setSDT(sdt1);
		customer.setDiachi(diachi1);
		customer.setEnabled(true);
		customer.setEmail(email1);
		customer.setHoten(hoten);
		customer.setCreatetime(createtime);
		customer.setLasttime(createtime);
		customer.setAuth_provider(provider);	
		customer.setPassword(encoder.encode(("")));
		cusrepo.save(customer);
	}
	
	public void updateCustomer(Custumer customer, String hoten, String diachi1, String sdt1, EnumProvider provider) {

		customer.setDiachi(diachi1);
		customer.setHoten(hoten);
		customer.setLasttime(new Date());
		customer.setSDT(sdt1);
		cusrepo.save(customer);
	}
	public Custumer editCustumer(Custumer custumer)
	{
		cusrepo.getByEmail(custumer.getEmail());
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		custumer.setImage(custumer.getImage());
		custumer.setDiachi(custumer.getDiachi());
		custumer.setHoten(custumer.getHoten());
		custumer.setSDT(custumer.getSDT());
		//if(custumer.getPassword()==null)
		//{
		//custumer.setPassword(encoder.encode(custumer.getPassword()));
		//}
		System.out.println(" IMAGE BYTE Service"+custumer.getImage());
		return cusrepo.save(custumer);
	}
	
	//tạo mới tk chưa kích hoạt
	public void RegisterCustomer(Custumer custumer) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		Date date = new Date();
		custumer.setCreatetime(date);
		custumer.setLasttime(date);
		custumer.setPassword(encoder.encode(custumer.getPassword()));
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

	@Transactional
	public boolean CheckEmail(String EmailABC)
	{
		Custumer user = cusrepo.getByEmail(EmailABC);
				if(user !=null)
				{
					return false;
				}
				else return true;	
	}
	
	//Tìm kiếm user và gán token(random string)
	public void getPW(String email, String token)
	{
		Custumer user = cusrepo.getByEmail(email);
		if(user != null)
		{
			user.setTokenPassword(token);
			cusrepo.save(user);
		}else
			System.out.println("Không tìm thấy USER!");
	}
	
	//kích hoạt mã truy cập vào change pass
	@Transactional
	public boolean activeChangePass(String token)
	{
		Custumer user = cusrepo.getByTokenPass(token);
		if(user==null)
				{
			System.out.println("ERROR :!!!!!!" +token);
			System.out.println("Looking  "+user);
			return false;
				}
		else 
		{
			 return true;
		}
		
	}
	
	public Custumer GetBYTOKEN(String token)
	{
		return cusrepo.getByTokenPass(token);
		
	}
	//change pass
	public void ChangePass(Custumer custumer,String Newpass)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		custumer.setPassword(encoder.encode(Newpass));
		custumer.setTokenPassword(null);
		cusrepo.save(custumer);
	}
	
	public Custumer getCurrentLoggedInCustumer(Authentication authentication)
	{   if(authentication == null) return null;
		Custumer custumer = null;
		Object Principal = authentication.getPrincipal();
		if(Principal instanceof MyUserDetails)
		{
			custumer = ((MyUserDetails) Principal).getCustumer();
		}else if (Principal instanceof CustomOauth2)
		{
			String email = ((MyUserDetails) Principal).getUsername();
			custumer = cusrepo.getByEmail(email);
		}
	
		return custumer;
		
	}
}

