package com.digitalojt.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.api.entity.StockInfo;
import com.digitalojt.api.repository.StockInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockInfoService 
{
	private final StockInfoRepository stockInfoRepository;
	
	public List<StockInfo> getStockInfoAll()
	{
		return stockInfoRepository.findAll();
	}
	
	public List<StockInfo> getActiveStockInfoData() 
	{
	    return stockInfoRepository.findActiveStockInfo();
	}
	
	public List<StockInfo> getStockInfoByName(String name)
	{
		return stockInfoRepository.findByName(name);
	}
	
	public List<StockInfo> getStockInfoByCategoryAndNameAndAmount(String category,String name,Integer amount,String than)
	{
		return stockInfoRepository.findByCategoryAndNameAndAmount(category, name, amount, than);
	}
	
}
