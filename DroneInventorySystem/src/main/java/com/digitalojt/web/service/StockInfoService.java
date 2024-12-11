package com.digitalojt.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.digitalojt.web.controller.StockListController;
import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.form.StockInfoForm;
import com.digitalojt.web.repository.StockInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockInfoService 
{

	// ロガーの追加
    private static final Logger logger = LoggerFactory.getLogger(StockListController.class);
	
    /** 在庫情報テーブル リポジトリー */
    private final StockInfoRepository repository;

    /**
     * 条件に基づいて在庫情報を取得
     * 
     * @return List<StockInfo>
     */
    public List<StockInfo> getActiveStockInfoData() 
    {
        return repository.findActiveStockInfo();
    }
    
    
    /**
     * 検索に基づいて在庫情報を取得
     * 
     * @return List<StockInfo>
     */
    public List<StockInfo> searchStockInfo(StockInfoForm form) 
    {
    	// パラメータの値をログに出力
        logger.debug("Service - Category: " + form.getCategory());
        logger.debug("Service - Name: " + form.getName());
        logger.debug("Service - Amount: " + form.getAmount());
        logger.debug("Service - Than: " + form.getThan());
    	
        
        // 検索条件に基づいてセンター情報を取得するロジックを実装
        return repository.findByCategoryAndNameAndAmount(
        		form.getCategory(),
        		form.getName(),
        		form.getAmount(),
        		form.getThan()
        	);
        

    }

    //削除可否
    public boolean isCenterUsedInStockInfo(Integer centerId) 
    {
        return repository.existsByCenterId(centerId);
    }
}