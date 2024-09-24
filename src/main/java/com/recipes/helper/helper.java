package com.recipes.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.recipes.dtos.PageableResponse;


public class helper {

	
	public static <U,V> com.recipes.dtos.PageableResponse<V> getPagebleResponse(Page<U> page, Class<V> type){
		List<U> entity =  page.getContent();	
		   
        List<V> dtos = entity.stream()
                                     .map(object -> new ModelMapper().map(object,type))
                                     .collect(Collectors.toList());
        
        PageableResponse<V> response = new PageableResponse<>();
        response.setContent(dtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        	return response;
	}
	
}
