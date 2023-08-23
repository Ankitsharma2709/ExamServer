package com.exam.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.Service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailServiceImpl;
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	@Autowired
	private JwtAuthneticationFilter jwtAuthenticationFilter;
	
	/*
	 * The AuthenticationManager is a core component of Spring Security, responsible
	 * for authenticating users based on their credentials (e.g., UserName and
	 * password). It performs the authentication process against the configured user
	 * details service, such as a database or in-memory user store, to validate user
	 * credentials.
	 * 
	 * By exposing the AuthenticationManager as a bean, you make it accessible for
	 * use in other parts of your application or by other beans. For example, you
	 * may use it to manually authenticate users, perform programmatic login, or
	 * wire it into other authentication mechanisms within your application.
	 * 
	 * Please note that if you are using Spring Boot, it will automatically
	 * configure an AuthenticationManager for you, so you may not need to provide
	 * this method explicitly in most cases. However, if you want to customize the
	 * AuthenticationManager or need access to it in your custom code, you can still
	 * override this method to expose it as a bean.
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	@Bean
	public  BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	 @Bean
		/*
		 * DaoAuthenticationProvider is a class provided by Spring Security that implements the AuthenticationProvider interface. It is used for authentication by querying a UserDetailsService.
		 * setUserDetailsService():
		 * 
		This method sets the UserDetailsService implementation that will be used by the DaoAuthenticationProvider to load user details during the authentication process. The UserDetailsService is responsible for retrieving user information from a data source (e.g., a database) based on the provided username.

In your code, this.userDetailsService() is used to refer to the UserDetailsService implementation defined in your application.

setPasswordEncoder():
This method sets the password encoder used by the DaoAuthenticationProvider. The password encoder is responsible for comparing the provided password during authentication with the encoded password stored in the data source (e.g., database).
		 */
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService());
	        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	        return daoAuthenticationProvider;
	    }
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(this.userDetailServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf()
			.disable()
			.cors()
			.disable()
			.authorizeRequests()
			.antMatchers("/generate-token","/user/").permitAll()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.anyRequest().authenticated()
			.and()
			//authenticationEntryPoint class created for error handling
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//jwtAuthenticationFilter class we create to take the header from token and autneitcate it .
		//By using addFilterBefore, you are instructing Spring Security to insert the jwtAuthenticationFilter before the UsernamePasswordAuthenticationFilter in the filter chain. This means that, when a request comes in, it will first go through the jwtAuthenticationFilter, where you can handle JWT-based authentication. If the request is not authenticated by the JWT filter, it will proceed to the UsernamePasswordAuthenticationFilter for form-based authentication, allowing you to support multiple authentication mechanisms in your application.

//Custom filters like jwtAuthenticationFilter give you the flexibility to implement custom authentication mechanisms or add additional security checks to requests in your Spring Security configuration.
//		UsernamePasswordAuthenticationFilter.class: This is the class of the filter that you want the jwtAuthenticationFilter to be placed before. In this case, it's the UsernamePasswordAuthenticationFilter. This is a built-in filter provided by Spring Security, responsible for handling form-based authentication (i.e., authentication based on a username and password provided via a login form).
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
