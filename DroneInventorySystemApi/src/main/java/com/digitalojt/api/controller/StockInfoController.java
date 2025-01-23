package com.digitalojt.api.controller;

import java.util.List;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalojt.api.consts.FeatureName;
import com.digitalojt.api.consts.ScreenName;
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
@CrossOrigin(origins = "*")
public class StockInfoController {
    private final StockInfoService stockInfoService;
    private static final Logger logger = LoggerFactory.getLogger(StockInfoController.class);

    @GetMapping
    public List<StockInfo> getAll() {
        return stockInfoService.getStockInfoAll();
    }

    @GetMapping("/active")
    public List<StockInfo> getByActive() {
        return logAndExecute(ScreenName.STOCK, FeatureName.LIST, stockInfoService::getActiveStockInfoData);
    }

    @GetMapping("/search")
    public List<StockInfo> searchStockInfo(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer amount,
            @RequestParam(required = false) String than) {
        return logAndExecute(ScreenName.STOCK, FeatureName.SEARCH,
                () -> stockInfoService.searchStockInfo(category, name, amount, than));
    }

    private List<StockInfo> logAndExecute(String screenName, String featureName, Supplier<List<StockInfo>> action) {
        logger.info(screenName + "の" + featureName + "を開始します。");
        try {
            List<StockInfo> result = action.get();
            logger.info(screenName + "の" + featureName + "が終了しました。");
            return result;
        } catch (DataAccessException e) {
            logger.error("データベースエラーが発生しました。", e);
            throw new RuntimeException("データベースが停止しています。");
        } catch (Exception e) {
            logger.error("サーバーエラーが発生しました。", e);
            throw new RuntimeException("サーバーが停止しています。");
        }
    }
}