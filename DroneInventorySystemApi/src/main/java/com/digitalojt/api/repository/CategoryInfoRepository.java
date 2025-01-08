package com.digitalojt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalojt.api.entity.CategoryInfo;

public interface CategoryInfoRepository extends JpaRepository<CategoryInfo,Integer>
{
	List<CategoryInfo> findByCategoryName(String categoryName);
}
