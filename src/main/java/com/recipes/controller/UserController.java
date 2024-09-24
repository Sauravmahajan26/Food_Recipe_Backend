package com.recipes.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.recipes.dtos.ApiResponceMessage;
import com.recipes.dtos.PageableResponse;
import com.recipes.dtos.userDto;
import com.recipes.services.userService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private userService userservice;
	
		
	
	
	//create
	//done
	@PostMapping("/user")
	public ResponseEntity<userDto> createUser( @RequestBody userDto userdto) {
		userDto user = userservice.createUser(userdto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	//update
	//done
	@PutMapping("/user/{id}")
	public ResponseEntity<userDto> updateUser(@Valid @RequestBody userDto userdto, @PathVariable("id") String userid){
		userDto updatedUserdto = userservice.updateUser(userdto, userid);
		return new ResponseEntity<>(updatedUserdto,HttpStatus.OK);
		
	}
	
	//delete
	//done
	@DeleteMapping("/user/{id}")
	public ResponseEntity<ApiResponceMessage> deleteUser(@PathVariable String id) throws IOException {	
	userservice.deleteUser(id);
	ApiResponceMessage message = ApiResponceMessage.builder()
													.message("user deleted Successfullt !")
													.success(true)
													.status(HttpStatus.OK)
													.build();
	return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	//get all user
	//done
	@GetMapping("/user")
	public ResponseEntity<PageableResponse<userDto>> getAllUser(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = "name",required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
			){
		return new ResponseEntity<>(userservice.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
	}
	
	//get single user by id
	//done
	@GetMapping("/user/{id}")
	public ResponseEntity<userDto> getUserById(@PathVariable String id){
		userDto userdto = userservice.getUserById(id);
		return new ResponseEntity<>(userdto,HttpStatus.OK);
	}
	
	//get single user by email{
	//done
	@GetMapping("/user/email/{email}")
	public ResponseEntity<userDto> getUserByEmail(@PathVariable String email){
		userDto userdto = userservice.getUserByEmail(email);
		return new ResponseEntity<>(userdto,HttpStatus.OK);
	}
	
	//search user
	//done
	@GetMapping("/user/search/{keyword}")
	public ResponseEntity<List<userDto>> searchUser(@PathVariable String keyword){
		List<userDto> userdto = userservice.serchUser(keyword);
		return new ResponseEntity<>(userdto,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}
