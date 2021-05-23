package project.green.shop.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import project.green.shop.DAO.CustumerService;
import project.green.shop.auth.CustomOauth2Service;
import project.green.shop.handle.OAuth2LoginSuccess;
import project.green.shop.helper.CustomAccessDeniedHandler;

@Configuration()
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new CustumerService();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	 @Bean
	    public CustomAccessDeniedHandler accessDeniedHandler() {
	        return new CustomAccessDeniedHandler();
	    }
	@Autowired
	private CustomOauth2Service customservice;
	@Autowired
	private OAuth2LoginSuccess success;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/verify","/access-denied","/403","/oauth2/**","/css/**","/images/**","/js/**","/lib/**","/aboutus","/Login","/register","/quydinhgiaohang","/forgot","/save","Register").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/Login").permitAll()
		.usernameParameter("email1")
		.passwordParameter("password1")
		.defaultSuccessUrl("/")
		.loginProcessingUrl("/dologin")
		.failureHandler(new project.green.shop.handle.OnAuthenticationFailueHandler())
		.successHandler(new project.green.shop.handle.OnAuthenticationSuccessHandler())
		.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
		.and().csrf().disable().logout().logoutSuccessUrl("/")
		.permitAll()
		.and().exceptionHandling().accessDeniedPage("/403")
		.and().oauth2Login().loginPage("/Login").userInfoEndpoint().userService(customservice)
		.and().successHandler(success).permitAll();
	}
	
}
