package com.exam.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.Repo.UserReposiroty;
import com.exam.model.User;
//so spring security by default ise use lrega ab username load karane ke lie 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserReposiroty userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//fetching data from the database of user
		
		User user = this.userRepository.findByUsername(username);
		if(user==null) {
			System.out.println("user not ofund");
			throw new UsernameNotFoundException("No User found !!");
		}
		
		return user;
	}
	
	// Add a method to get user ID by username
    public Long getUserIdByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            return user.getId(); // Assuming your User entity has a getId() method
        }
        return null;
    }

}
