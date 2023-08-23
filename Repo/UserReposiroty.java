package com.exam.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.User;

public interface UserReposiroty extends JpaRepository<User, Long>{
	
	public User findByUsername(String username);
	

	

}
