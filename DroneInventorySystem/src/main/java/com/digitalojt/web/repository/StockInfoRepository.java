package com.digitalojt.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digitalojt.web.entity.StockInfo;

public interface StockInfoRepository extends JpaRepository<StockInfo, Integer>
{
	@Query("SELECT s FROM StockInfo s " +
	           "JOIN s.categoryinfo c " +
	           "JOIN s.centerinfo ci " +
	           "WHERE s.deleteFlag = '0' " +
	           "AND c.deleteFlag = 0 " +
	           "AND ci.operationalStatus = 0 " +
	           "AND ci.deleteFlag = '0'")
	List<StockInfo> findActiveStockInfo();
}
