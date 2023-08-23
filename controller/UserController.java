package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import javax.naming.CannotProceedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Service.UserService;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;

import javassist.NotFoundException;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;

	// creating user
	@PostMapping("/")
	public ResponseEntity<?> addingUser(@RequestBody User user) throws Exception {

		try {
			Set<UserRole> roles = new HashSet<>();
			Role role = new Role();

			role.setRoleName("NORMAL");

			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(role);

			roles.add(userRole);
			return ResponseEntity.ok(this.userService.createUser(user, roles));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// get the user
	@GetMapping("/{username}")
	public ResponseEntity<?> getUser(@PathVariable("username") String username) throws NotFoundException {
		try {
			User user = userService.getUser(username);
			return ResponseEntity.ok(user);
			// user not Notfoundexception can occur here
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
	}

	// delete user by id
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) throws CannotProceedException {

		userService.deleteUser(userId);

	}

	// update the user
	@PutMapping("/{userId}")
	public void updateUser(@RequestBody User user, @PathVariable("userId") Long userId) throws Exception {
		try {
			if (userId == null) {
				throw new IllegalArgumentException("Id must not be null");
			}
			user.setId(userId);

			this.userService.updateUserData(user);

		} catch (IllegalArgumentException e) {
			e.getMessage();
			// TODO: handle exception
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	//getting authenticated user details
	//n this example, the /user/details endpoint is intended to return 
	//the details of the authenticated user. The Authentication object is automatically 
	//injected into the method, allowing you to extract the authenticated user's username and then fetch the user details using your userService.
	@GetMapping("/details")
    public ResponseEntity<?> getUserDetails(Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userService.getUser(username);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
