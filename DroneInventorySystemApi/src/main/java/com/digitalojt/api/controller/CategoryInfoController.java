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
/**
 * 分類画面コントローラークラス
 * 
 * @author haruka matano
 *
 */
@RestController
@RequestMapping("/category-info")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class CategoryInfoController 
{
	private final CategoryInfoService categoryInfoService;
	
	//IDによる取得
	@GetMapping("/{id}")
	public CategoryInfo getById(@PathVariable int id)
	{
		return categoryInfoService.getCategoryInfoByCategoryId(id);
	}
	
	//名前検索による取得
	@GetMapping("/name/{name}")
	public List<CategoryInfo> getByName(@PathVariable String name)
	{
		 return categoryInfoService.getCategoryInfoByCategoryName(name);
	}
	
	//全件取得
	@GetMapping 
	public List<CategoryInfo> getAll()
	{
		return categoryInfoService.getCategoryInfoAll();
	}
	
	//登録
	@PostMapping
	public CategoryInfo save(@RequestBody CategoryInfo categoryInfo)
	{
		return categoryInfoService.saveCategoryInfo(categoryInfo);
	}
}
