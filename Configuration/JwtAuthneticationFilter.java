package com.exam.Configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.Service.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthneticationFilter  extends OncePerRequestFilter{
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;  
	
//
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//basically ab tooken header nikaalra then check kia if it is notnull and starts with bearer so it is a valid token
		final String requestTokenheader = request.getHeader("Authorization");
		System.out.println(requestTokenheader);
		String username = null;
		String jwtToken = null;
		if(requestTokenheader != null && requestTokenheader.startsWith("Bearer ")) {
			//yes it is a valid token 
			//it is getting the value of jwt token from requestHeaderToken for eg. Authorization: Bearer dhfgwbufbiuw
			//so substring method will leave the starting 7 characters whihc is bearer and space and then jwttoken wilm store the remaining whihc is the token
			
			jwtToken = requestTokenheader.substring(7);
			
			try {
				//then after getting the token we will extract the username using the util class from token.
				username = this.jwtUtil.extractUsername(jwtToken);
				
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("jwt token has expired");
			}
		}else {
			System.out.println("invalid token, not starts with bearer string ");
		}
		
		//once we get the username thn we will validate the tpoken
		//if username not null and getAuthentication is null(whihc means if it is null that means no authentication has been done regarding the particular username )
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			//LOADING THE USER details like username, password, authorities
			final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			//npw if the util class validates the jwToke adn userzdetails then the token is valid
			if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
				/*
				 * If the JWT token is valid, the code proceeds to create a new
				 * UsernamePasswordAuthenticationToken object. The
				 * UsernamePasswordAuthenticationToken is a class provided by Spring Security to
				 * represent an authenticated user's token.
				 * there are three paramters . first one contains details of the authentcate user like usrname , password and auhtorities
				 * sevcind one is for stroing password but not required for jwt authentication thats why set to null, and third one
				 * is collection of GrantedAuthority objects representing the user's auhtorites like roles and permissions
				 */				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				/*here we are setting additinal details about the authnetication
				 * in this we creates a new WebAuthenticationDetais which holds additional information about the request , such as remote address and session id
				 * build details is used to create the object is webauthenticationdetails based on the current http request
				 * 
				 * */
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				/*
				 * Finally, the code sets the usernamePasswordAuthenticationToken as the
				 * authenticated user's token in the current security context. The
				 * SecurityContextHolder is a thread-local holder for security-related context
				 * information. Setting the Authentication object in the SecurityContextHolder
				 * allows Spring Security to associate the authenticated user with the current
				 * request.
				 */
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				/*
				 * In summary, if the JWT token is validated successfully, the code creates a
				 * new UsernamePasswordAuthenticationToken with the user's details and sets it
				 * as the authenticated user's token in the SecurityContextHolder. This way,
				 * Spring Security recognizes the user as authenticated and allows them access
				 * to secured resources based on their authorities (roles or permissions). The
				 * additional WebAuthenticationDetails provides more context about the request.
				 * 
				 * 
				 * 
				 */




			} else {
				System.out.println("Token is not valid");
			}

		} 
			filterChain.doFilter(request, response);
		

	}
}
