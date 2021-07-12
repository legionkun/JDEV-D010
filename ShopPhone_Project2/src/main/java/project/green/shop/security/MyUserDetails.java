package project.green.shop.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import project.green.shop.model.Custumer;
import project.green.shop.model.Role;
import project.green.shop.model.User;



public class MyUserDetails  implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8015629406528630839L;
	private Custumer custumer;
	private User user;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		if(user != null) {
		for(Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
		}}else {
		authorities.add(new SimpleGrantedAuthority("CUSTUMER"));
		}
		return authorities;
	}
	public MyUserDetails() {}
	
	public MyUserDetails(Custumer custumer) {
		super();
		this.custumer = custumer;
	}
	@Override
	public String getPassword() {

		return custumer.getPassword();
	}

	@Override
	public String getUsername() {
	
		if(custumer.getHoten().isEmpty() || custumer.getHoten()== null)
		{
			return custumer.getEmail();
		}
		return custumer.getHoten();
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return custumer.isEnabled();
	}
	
	public Custumer getCustumer() {
		return custumer;
	}
	public void setCustumer(Custumer custumer) {
		this.custumer = custumer;
	}
	

	public String getDiachi() {
		return custumer.getDiachi();
	}

	public String getSDT() {
		return custumer.getSDT();
	}

	public String getHoten() {
		return custumer.getHoten();
	}

	public String getImage()
	{
		return custumer.getImagePath();
	}
	
	public int getId() {
		return custumer.getId();
	}

	
}
