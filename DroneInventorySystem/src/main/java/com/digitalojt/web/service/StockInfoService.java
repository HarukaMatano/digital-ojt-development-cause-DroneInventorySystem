package com.digitalojt.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.repository.StockInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockInfoService {

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
}