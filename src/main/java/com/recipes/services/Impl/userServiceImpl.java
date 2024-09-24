package com.recipes.services.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.recipes.dtos.PageableResponse;
import com.recipes.dtos.userDto;
import com.recipes.entities.User;
import com.recipes.exceptions.ResourceNotFoundException;
import com.recipes.helper.helper;
import com.recipes.repositories.UserRepository;
import com.recipes.services.userService;



@Service
public class userServiceImpl implements userService
{

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private ModelMapper mapper;	
	

	
	@Override
	public userDto createUser(userDto userdto) {
		User user = mapper.map(userdto, User.class);
		User savedUser = userrepo.save(user);
		userDto newUser = mapper.map(savedUser, userDto.class);
			return newUser;
	}

	
	@Override
	public userDto updateUser(userDto userdto, String userId) {
		User user = userrepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given id"));
		user.setName(userdto.getName());
		user.setPassword(user.getPassword());
		user.setAbout(userdto.getAbout());
		user.setGender(user.getGender());
		
		User updateduser = userrepo.save(user);
		userDto newuser =  mapper.map(updateduser, userDto.class);
		return newuser;
	}
	
	@Override
	public void deleteUser(String userId) throws IOException {
		User user = userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found unable to delete"));
		userrepo.delete(user);	
		}

	@Override
	public PageableResponse<userDto> getAllUser(int pageNumber,int pageSize, String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
		
		Pageable pageble = PageRequest.of(pageNumber, pageSize,sort);
		
		   Page<User> page = userrepo.findAll(pageble);
		   PageableResponse<userDto> response = helper.getPagebleResponse(page, userDto.class);
		return response;
	}

	@Override
	public userDto getUserById(String userId) {
		// TODO Auto-generated method stub
		User user = userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found in getUserById method"));
		userDto usedto = mapper.map(user, userDto.class);
		
		return usedto;
	}

	@Override
	public userDto getUserByEmail(String userEmail) {
		// TODO Auto-generated method stub
		User user = userrepo.findByEmail(userEmail).orElseThrow(()->new ResourceNotFoundException("user not found by email id "));
		userDto userdto = mapper.map(user, userDto.class);
		return userdto;
	}

	@Override
	public List<userDto> serchUser(String keyword) {
		// TODO Auto-generated method stub
		List<User> users = userrepo.findByNameContaining(keyword);
		List<userDto> userdtos = users.stream()
							.map(user -> mapper.map(user, userDto.class))
							.collect(Collectors.toList());
		return userdtos;
	}

}
