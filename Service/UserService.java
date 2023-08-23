package com.exam.Service;

import java.util.Optional;
import java.util.Set;

import javax.naming.CannotProceedException;

import com.exam.model.User;
import com.exam.model.UserRole;

import javassist.NotFoundException;


public interface UserService {
	//creating user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//get user by username
	public User getUser(String username) ;
	
	//delete user by id
	public void deleteUser(Long userId) ;
	
	//update the user by id
	public void updateUserData(User user) throws  Exception;
}
