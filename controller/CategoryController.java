 package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Service.CategoryService;
import com.exam.model.quizModel.Category;

import javassist.NotFoundException;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	//intrface ki autowiring ki kunki abstraction dedi and uski impl clss pr service dedi taaki jab ye interface use ho to ye interface
	//ke methods chl jaaenge impl class se . basically hmne for eg. ek built in interface jse implement krte and uski impl class backend mein kaam krti rehti 
	//wohi h ye bhi.
	@Autowired
	private CategoryService categoryService;
	
	//add handler
	@PostMapping("/")
	//requestBody is to handle the incoming http request and extract data from the requestbody.
	//When a client makes an HTTP POST or PUT request to a Spring MVC controller endpoint, it can send data as part of the request body. The @RequestBody annotation tells Spring to automatically bind the data from the request body to a method parameter in the controller method.
	public ResponseEntity<?> addCategory(@RequestBody Category category){
		 try {
		        Category addedCategory = categoryService.addCategory(category);
		        return ResponseEntity.ok(addedCategory);
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		    }
	}
	
	//get the category
	@GetMapping("/{cid}")
	//Path variable  data lia cid se and use long cid ke saath map kr dia and ab usko use krke data access kr skte aham
	public Category getCategory(@PathVariable("cid")Long cid) throws NotFoundException{
		return this.categoryService.getCategory(cid);
	}
	//get all categoris
	
	@GetMapping("/")
	public ResponseEntity<?> getCategories(){
		try {
			return ResponseEntity.ok(this.categoryService.getCategories());
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			// TODO: handle exception
		}
	}
	
	//In summary, @PathVariable deals with extracting specific values like  the page number or article ID.from the URL  and using it in a method to do something related to it., 
	//while @RequestBody deals with capturing data sent from a form or API call.and using it in a method to process or save the data.
	
	@PutMapping("/")
	public Category updateCategories(@RequestBody Category category) throws NotFoundException {
		
		return this.categoryService.updateCategory(category); 
	}
	//mapping mein wo variable dena jo db mein defined hai
	
	
	@DeleteMapping("/{cid}")
	public void deleteCtegories(@PathVariable("cid") Long cid) throws NotFoundException {
		this.categoryService.deleteCategory(cid);
	}
	
		
}
	
