package com.exam.controller;

import java.security.Principal;

import javax.persistence.GeneratedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Configuration.JwtUtil;
import com.exam.Service.impl.UserDetailsServiceImpl;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtUtil jwtUtils;
	
	//generte token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			//if autheitcate hojaata jo ham call krre niche waala auhtneticate krne ka method
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User Not Found");
			
		}
		/*
		 * after authentication we will store userdetails by calling the loadusername
		 * method. then we will generate token by calling gnerateToken methodof utik class and just return it.
		 */
		
		//authenticated
		UserDetails userDetails =this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	
	//ye auhtneticate krega and agr authenticate fail hua to error send krdega
	private void authenticate(String username, String password) throws Exception {
		try {
			//authnetication manager ki help se authenticate krenge username password ko
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("User DISABLED"+e.getMessage());
			
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid Credentials"+e.getMessage());
		}
		
	}
	
	
	//gt details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User)this.userDetailsService.loadUserByUsername(principal.getName()));
		    
	}

}
