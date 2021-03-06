package project.green.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import project.green.shop.DAO.CustumerService;
import project.green.shop.auth.CustomOauth2Service;
import project.green.shop.handle.OAuth2LoginSuccess;
import project.green.shop.helper.CustomAccessDeniedHandler;

@Configuration()
@EnableWebSecurity(debug = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationFilter JwtAuthenFilter() {
		return new JwtAuthenticationFilter();
	}

	@Bean
	@Override

	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();

	}

	@Bean
	@Primary
	public UserDetailsService userDetailsService() {
		return new CustumerService();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
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
		http.authorizeRequests()
				.antMatchers("/", "/adminsapo", "/index", "/verify", "/profile/update", "/page/**", "/product/**",
						"/product_details", "/product_code/**", "/access-denied", "/403", "/404", "/500", "/oauth2/**",
						"/css/**", "/assets/**", "/images/**", "/pro/**", "/js/**", "/lib/**", "/aboutus", "/cart/**",
						"/cart", "/product_id/**", "/api/Login", "/register", "/quydinhgiaohang", "/Login/Error",
						"/reset_password", "/changepass", "/forgot", "/forgot/getpass", "/save", "Register")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/api/Login").permitAll()

				.usernameParameter("email1").passwordParameter("password1").defaultSuccessUrl("/")
				.loginProcessingUrl("/dologin")
				.successHandler(new project.green.shop.handle.OnAuthenticationSuccessHandler())
				.failureUrl("/Login/Error")

				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and().csrf().disable().logout()
				.logoutSuccessUrl("/").permitAll()

				.and().rememberMe().key("qwertyuiop").and().exceptionHandling().accessDeniedPage("/403").and()
				.oauth2Login().loginPage("/api/Login").userInfoEndpoint().userService(customservice).and()
				.successHandler(success).permitAll();

		// Th??m m???t l???p Filter ki???m tra jwt
		http.addFilterBefore(JwtAuthenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
