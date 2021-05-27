package project.green.shop.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import project.green.shop.model.Custumer;
import project.green.shop.model.Role;



public class MyUserDetails  implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8015629406528630839L;
	private Custumer user;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
		}
		return authorities;
	}
	public MyUserDetails() {}
	
	public MyUserDetails(Custumer user) {
		super();
		this.user = user;
	}
	@Override
	public String getPassword() {

		return user.getPassword1();
	}

	@Override
	public String getUsername() {
	
		return user.getEmail1();
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
		
		return user.isEnabled();
	}
	public Custumer getUser() {
		return user;
	}
	public void setUser(Custumer user) {
		this.user = user;
	}
	public void setHoten(String hoten) {
		this.user.setHoten(hoten);
	}

	public void setDiachi(String diachi) {
		this.user.setDiachi1(diachi);
	}

	public void setSdt(String sdt) {
		this.user.setSdt1(sdt);
	}

	public void setImage(String image) {
		this.user.setImage(image);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
