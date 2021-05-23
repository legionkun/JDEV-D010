package project.green.shop.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import project.green.shop.DAO.CustumerService;
import project.green.shop.auth.CustomOauth2;
import project.green.shop.auth.EnumProvider;
import project.green.shop.model.Custumer;
@Component
public class OAuth2LoginSuccess extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private CustumerService cusser;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		CustomOauth2 oauth2User = (CustomOauth2) authentication.getPrincipal();
		String email1 = oauth2User.getEmail();
		String hoten = oauth2User.getName();
		String servletPath  = request.getServletPath();
		
		System.out.println("email: " + email1 + " name: " + hoten + " servlet path: " + servletPath);
		
		EnumProvider provider = EnumProvider.BASIC;
		
		if (servletPath.contains("facebook")) {
			provider = EnumProvider.FACEBOOK;
		} else if (servletPath.contains("google")) {
			provider = EnumProvider.GOOGLE;
		}
		
		Custumer customer = cusser.getByEmail(email1);
		
		if (customer == null) {
			//Dang ky customer moi
			cusser.AddNewCustomer(email1,hoten,"","", provider);
		} else {
			//Cap nhat lai provider & info
			cusser.updateCustomer(customer, hoten , "","", provider);
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
