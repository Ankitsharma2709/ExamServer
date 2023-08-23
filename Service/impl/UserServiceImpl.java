package com.exam.Service.impl;

import java.util.Optional;
import java.util.Set;

import javax.naming.CannotProceedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.Repo.RoleRepository;
import com.exam.Repo.UserReposiroty;
import com.exam.Service.UserService;
import com.exam.customExceptions.BusinessException;
import com.exam.customExceptions.EmptyUserNameException;
import com.exam.model.User;
import com.exam.model.UserRole;

import javassist.NotFoundException;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserReposiroty userRepository;
	 @Autowired
	 private RoleRepository roleRepository;
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	 //Creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		if(user.getUsername().isEmpty() || user.getUsername().length()==0) {
			throw new EmptyUserNameException("601","Please fill proper username , its blank");
		}
		try {
			
			/*
			 * //we will check firstly that whether user is present or not by his username
			 * if user is present than we will throw a message else we will //create a user
			 */
			User local = this.userRepository.findByUsername(user.getUsername());
			if(local!=null) {
				System.out.println("User i alredy presnt!!");
				throw new Exception("user is alredy there!!");
			}else {
				//create the user
				//pehle saare roles jo associated the userroles ke saath unhe save kia kunki database mein roles save krne zaroori 
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				for(UserRole ur:userRoles) {
					roleRepository.save( ur.getRole()); 
				}
				//then un sabhi useroles ke roles ko save krne ke badd hamne sabhi useroles ko user se joda kunki since roles are mapped with userroles to sab kuch user ke satth jud gya
			user.getUserRoles().addAll(userRoles);
				//then sab user ko save krdia database mein
				local = this.userRepository.save(user);
			}

			return local;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException("602","Something went wrong in service layer"+e.getMessage());
		}
		
		
		
	}
	
	//get user by username
	@Override
	public User getUser(String username)  {
		
			return this.userRepository.findByUsername(username);
			
		
		
	}
	//delete the user
	
	
	@Override
	public void deleteUser(Long userId) {
		
		
	    
	       this. userRepository.deleteById(userId);
	    
	}

	@Override
	public void updateUserData(User user) throws Exception {
		this.userRepository.save(user);
		
		
		
		
	}
	
//	public UserServiceImpl(UserReposiroty userReposiroty) {
//		this.userRepository = userReposiroty;
//	}
	

	

}
