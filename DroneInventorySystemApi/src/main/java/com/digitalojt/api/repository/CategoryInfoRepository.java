package com.digitalojt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalojt.api.entity.CategoryInfo;

public interface CategoryInfoRepository extends JpaRepository<CategoryInfo,Integer>
{
	//名前検索による取得
	List<CategoryInfo> findByCategoryName(String categoryName);
}
