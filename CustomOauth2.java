package project.green.shop.auth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOauth2 implements OAuth2User{
	private OAuth2User oauth2user;
	
	public OAuth2User getOauth2user() {
		return oauth2user;
	}

	public void setOauth2user(OAuth2User oauth2user) {
		this.oauth2user = oauth2user;
	}
	
	public CustomOauth2(OAuth2User oauth2user) {
		super();
		this.oauth2user = oauth2user;
	}

	@Override
	public Map<String, Object> getAttributes() {
		
		return oauth2user.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return oauth2user.getAuthorities();
	}

	@Override
	public String getName() {
		
		return oauth2user.getAttribute("name");
		}
	public String getEmail() {
		System.out.println("CustomOauth2::getEmail = " + oauth2user.getAttribute("email"));
		return oauth2user.getAttribute("email");
	}

}
