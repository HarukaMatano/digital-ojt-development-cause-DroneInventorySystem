package com.digitalojt.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.api.entity.CategoryInfo;
import com.digitalojt.api.repository.CategoryInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報画面のサービスクラス
 *
 * @author haruka matano
 */
@Service
@RequiredArgsConstructor
public class CategoryInfoService 
{
	private final CategoryInfoRepository categoryInfoRepository;
	
	//IDによる取得
	public CategoryInfo getCategoryInfoByCategoryId(int categoryId)
	{
		return categoryInfoRepository.findById(categoryId).orElse(null);
	}
	
	//名前による取得
	public List<CategoryInfo> getCategoryInfoByCategoryName(String categoryName)
	{
		return categoryInfoRepository.findByCategoryName(categoryName);
	}
	
	//全件取得
	public List<CategoryInfo> getCategoryInfoAll()
	{
		return categoryInfoRepository.findAll();
	}
	
	//保存
	public CategoryInfo saveCategoryInfo(CategoryInfo categoryInfo)
	{
		return categoryInfoRepository.save(categoryInfo);
	}
}
