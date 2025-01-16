package com.digitalojt.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalojt.api.entity.StockInfo;
import com.digitalojt.api.service.StockInfoService;

import lombok.RequiredArgsConstructor;
/**
 * 在庫画面コントローラークラス
 * 
 * @author haruka matano
 *
 */
@RestController
@RequestMapping("/stock-info")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class StockInfoController 
{
	private final StockInfoService stockInfoService;
	
	//全件取得
	@GetMapping 
	public List<StockInfo> getAll()
	{
		return stockInfoService.getStockInfoAll();
	}
	
	//稼働中の保管場所の在庫情報
	@GetMapping("/active")
	public List<StockInfo> getByActive()
	{
		 return stockInfoService.getActiveStockInfoData();
	}
	
	//カテゴリ名、名前、個数による取得
	@GetMapping("/category/{category}/name/{name}/amount/{amount}/than/{than}")
	public List<StockInfo> getByCategoryAndNameAndAmount(@PathVariable String category,@PathVariable String name,@PathVariable Integer amount,@PathVariable String than)
	{
		 return stockInfoService.getStockInfoByCategoryAndNameAndAmount(category,name,amount,than);
	}
	
	//カテゴリ名、名前による取得
	@GetMapping("/category/{category}/name/{name}")
	public List<StockInfo> getByCategoryAndName(@PathVariable String category,@PathVariable String name)
	{
		return stockInfoService.getStockInfoByCategoryAndName(category,name);
	}
	
	//カテゴリ名、個数による取得
	@GetMapping("/category/{category}/amount/{amount}/than/{than}")
	public List<StockInfo> getByCategoryAndAmount(@PathVariable String category,@PathVariable Integer amount,@PathVariable String than)
	{
		 return stockInfoService.getStockInfoByCategoryAndAmount(category,amount,than);
	}	
	
	//名前、個数による取得
	@GetMapping("/name/{name}/amount/{amount}/than/{than}")
	public List<StockInfo> getByNameAndAmount(@PathVariable String name,@PathVariable Integer amount,@PathVariable String than)
	{
		 return stockInfoService.getStockInfoByNameAndAmount(name,amount,than);
	}
	
	//カテゴリ名による取得
	@GetMapping("/category/{category}")
	public List<StockInfo> getByCategory(@PathVariable String category)
	{
		 return stockInfoService.getStockInfoByCategory(category);
	}
	
	//名前による取得
	@GetMapping("/name/{name}")
	public List<StockInfo> getByCName(@PathVariable String name)
	{
		 return stockInfoService.getStockInfoByName(name);
	}		
				 
	//個数による取得
	@GetMapping("/amount/{amount}/than/{than}")
	public List<StockInfo> getByAmount(@PathVariable Integer amount,@PathVariable String than)
	{
		return stockInfoService.getStockInfoByAmount(amount,than);
	}				
	
}
