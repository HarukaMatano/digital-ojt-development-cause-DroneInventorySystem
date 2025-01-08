package com.digitalojt.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalojt.api.entity.CategoryInfo;
import com.digitalojt.api.service.CategoryInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category-info")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class CategoryInfoController 
{
	private final CategoryInfoService categoryInfoService;
	
	@GetMapping("/{id}")
	public CategoryInfo getById(@PathVariable int id)
	{
		return categoryInfoService.getCategoryInfoByCategoryId(id);
	}
	
	@GetMapping("/name/{name}")
	public List<CategoryInfo> getByName(@PathVariable String name)
	{
		 return categoryInfoService.getCategoryInfoByCategoryName(name);
	}
	
	@GetMapping 
	public List<CategoryInfo> getAll()
	{
		return categoryInfoService.getCategoryInfoAll();
	}
	
	@PostMapping
	public CategoryInfo save(@RequestBody CategoryInfo categoryInfo)
	{
		return categoryInfoService.saveCategoryInfo(categoryInfo);
	}
}
