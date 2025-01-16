package com.digitalojt.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.api.entity.StockInfo;
import com.digitalojt.api.repository.StockInfoRepository;

import lombok.RequiredArgsConstructor;
/**
 * 在庫情報画面のサービスクラス
 *
 * @author haruka matano
 */
@Service
@RequiredArgsConstructor
public class StockInfoService 
{
	private final StockInfoRepository stockInfoRepository;
	
	//全件取得
	public List<StockInfo> getStockInfoAll()
	{
		return stockInfoRepository.findAll();
	}
	
	//稼働中保管場所の在庫情報
	public List<StockInfo> getActiveStockInfoData() 
	{
	    return stockInfoRepository.findActiveStockInfo();
	}
	
	//カテゴリ名、名前、個数による取得
	public List<StockInfo> getStockInfoByCategoryAndNameAndAmount(String category,String name,Integer amount,String than)
	{
		return stockInfoRepository.findByCategoryAndNameAndAmount(category, name, amount, than);
	}
	
	//カテゴリ名、名前による取得
	public List<StockInfo> getStockInfoByCategoryAndName(String category,String name)
	{
		return stockInfoRepository.findByCategoryAndName(category, name);
	}
	
	//カテゴリ名、個数による取得
	public List<StockInfo> getStockInfoByCategoryAndAmount(String category,Integer amount,String than)
	{
		return stockInfoRepository.findByCategoryAndAmount(category,amount, than);
	}
		
	//名前、個数による取得
	public List<StockInfo> getStockInfoByNameAndAmount(String name,Integer amount,String than)
	{
		return stockInfoRepository.findByNameAndAmount(name, amount, than);
	}
	
	//カテゴリ名による取得
	public List<StockInfo> getStockInfoByCategory(String category)
	{
		return stockInfoRepository.findByCategory(category);
	}
	
	//名前による取得
	public List<StockInfo> getStockInfoByName(String name)
	{
		return stockInfoRepository.findByName(name);
	}
	
	//個数による取得
	public List<StockInfo> getStockInfoByAmount(Integer amount,String than)
	{
		return stockInfoRepository.findByAmount(amount, than);
	}
	
}
