package project.green.shop.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class OnAuthenticationFailueHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		try {
		 String error = exception.getMessage();
		System.out.println("Login falied" + error);
		response.sendRedirect("/403");
	
	}catch(Exception E)
	{
		System.out.println(" "+E.getMessage());
	}
	}
}
