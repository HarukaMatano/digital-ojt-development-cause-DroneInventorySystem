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
	
	//名前検索による取得
	public List<StockInfo> getStockInfoByName(String name)
	{
		return stockInfoRepository.findByName(name);
	}
	
	//カテゴリ名、名前、個数による取得
	public List<StockInfo> getStockInfoByCategoryAndNameAndAmount(String category,String name,Integer amount,String than)
	{
		return stockInfoRepository.findByCategoryAndNameAndAmount(category, name, amount, than);
	}
	
}
