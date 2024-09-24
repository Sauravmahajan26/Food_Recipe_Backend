package com.recipes.services;

import java.io.IOException;
import java.util.List;

import com.recipes.dtos.PageableResponse;
import com.recipes.dtos.userDto;



public interface userService {
	
	//create
	userDto createUser(userDto userdto);
	
	//delete
	void deleteUser(String userId) throws IOException;
	
	
	//update
	userDto updateUser(userDto userdto,String userId);
	 
	//get all user
	PageableResponse<userDto> getAllUser(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	//get single user by id
	userDto getUserById(String userId);
	
	//get single user by email
	userDto getUserByEmail(String userEmail);
	
	
	//search user
	List<userDto> serchUser(String keyword);

}
