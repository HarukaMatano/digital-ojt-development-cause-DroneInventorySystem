package com.digitalojt.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalojt.web.consts.FeatureName;
import com.digitalojt.web.consts.ScreenName;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.service.StockInfoService;

import lombok.RequiredArgsConstructor;

/**
 * 在庫一覧画面コントローラークラス
 * 
 * @author haruka matano
 *
 */
@Controller
@RequiredArgsConstructor
public class StockListController extends AbstractController 
{

    // ロガーの追加
    private static final Logger logger = LoggerFactory.getLogger(StockListController.class);

    /** 在庫情報 サービス */
    private final StockInfoService stockInfoService;

    /**
     * 初期表示
     * 
     * @return String(path)
     */
    @GetMapping(UrlConsts.STOCK_LIST)
    public String index(Model model) 
    {
        // ログの追加
        logger.info(ScreenName.STOCK+"の"+FeatureName.LIST+"を開始します。");

        List<StockInfo> stockInfoList = stockInfoService.getActiveStockInfoData();
        model.addAttribute("stockInfoList", stockInfoList);

        // ログの追加
        logger.info(ScreenName.STOCK+"の"+FeatureName.LIST+"を終了します。");
        
        return "admin/stockList/index";
    }
}