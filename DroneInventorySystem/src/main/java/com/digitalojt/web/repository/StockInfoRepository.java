package com.digitalojt.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digitalojt.web.entity.StockInfo;

public interface StockInfoRepository extends JpaRepository<StockInfo, Integer>
{
	@Query("SELECT s FROM StockInfo s " +
	           "JOIN s.categoryinfo c " +
	           "JOIN s.centerinfo ci " +
	           "WHERE s.deleteFlag = '0' " +
	           "AND c.deleteFlag = '0' " +
	           "AND ci.operationalStatus = 0 " +
	           "AND ci.deleteFlag = '0'")
	List<StockInfo> findActiveStockInfo();
	
	@Query("SELECT s FROM StockInfo s " +
	           "JOIN s.categoryinfo c " +
	           "JOIN s.centerinfo ci " +
	           "WHERE s.deleteFlag = '0' " +
	           "AND c.deleteFlag = '0' " +
	           "AND ci.operationalStatus = 0 " +
	           "AND ci.deleteFlag = '0'"
				+ "AND (:category='' OR c.categoryName = :category)"
				+ "AND (:name='' OR s.name = :name)"
				
				+ "AND (:amount IS NULL OR "
				+ "( :than = '以上' AND s.amount >= :amount ) OR "
				+ "( :than = '以下' AND s.amount <= :amount ))"
			)
	List<StockInfo> findByCategoryAndNameAndAmount(
			@Param("category") String category,
			@Param("name") String name,
			@Param("amount") Integer amount,
			@Param("than") String than
	);

//	boolean existsByCenterId(Integer centerId);
}
