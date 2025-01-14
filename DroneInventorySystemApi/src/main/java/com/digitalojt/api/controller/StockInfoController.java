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

@RestController
@RequestMapping("/stock-info")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class StockInfoController 
{
	private final StockInfoService stockInfoService;
	
	@GetMapping 
	public List<StockInfo> getAll()
	{
		return stockInfoService.getStockInfoAll();
	}
	
	@GetMapping("/active")
	public List<StockInfo> getByActive()
	{
		 return stockInfoService.getActiveStockInfoData();
	}
	
	@GetMapping("/name/{name}")
	public List<StockInfo> getByName(@PathVariable String name)
	{
		 return stockInfoService.getStockInfoByName(name);
	}
	
	@GetMapping("/category/{category}/name/{name}/amount/{amount}/than/{than}")
	public List<StockInfo> getByCategoryAndNameAndAmount(@PathVariable String category,@PathVariable String name,@PathVariable Integer amount,@PathVariable String than)
	{
		 return stockInfoService.getStockInfoByCategoryAndNameAndAmount(category,name,amount,than);
	}
	
}
