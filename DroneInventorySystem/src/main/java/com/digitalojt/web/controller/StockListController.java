package com.digitalojt.web.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.FeatureName;
import com.digitalojt.web.consts.ScreenName;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.form.StockInfoForm;
import com.digitalojt.web.service.CategoryInfoService;
import com.digitalojt.web.service.StockInfoService;
import com.digitalojt.web.util.MessageManager;

import jakarta.validation.Valid;
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

    /** 在庫情報 サービス */
    private final CategoryInfoService categoryInfoService;
    
	/** メッセージソース */
	private final MessageSource messageSource;
    
    /**
     * 初期表示
     * 
     * @return String(path)
     */
    @GetMapping(UrlConsts.STOCK_LIST)
    public String index(Model model,StockInfoForm form) 
    {
        // ログの追加
        logger.info(ScreenName.STOCK+"の"+FeatureName.LIST+"を開始します。");

        //検索条件保持の為の記述
        model.addAttribute("StockInfoForm", form);
        
        addCommonAttributes(model);
        
        // ログの追加
        logger.info(ScreenName.STOCK+"の"+FeatureName.LIST+"を終了します。");
        
        return "admin/stockList/index";
    }
    
    /**
     * 検索処理
     * 
     * @param form 検索フォーム
     * @param model モデル
     * @return 検索結果画面のパス
     */
    @PostMapping(UrlConsts.STOCK_LIST_SEARCH)
    public String search(
            @Valid StockInfoForm form,BindingResult bindingResult, Model model) 
    {
        // ログの追加
        logger.info(ScreenName.STOCK + "の" + FeatureName.SEARCH + "を開始します。");
        
        model.addAttribute("StockInfoForm", form);
        
        // Valid項目チェック
        if (bindingResult.hasErrors()) 
        { 
        	addCommonAttributes(model);
            
            // エラーメッセージをプロパティファイルから取得
            String errorMsg;
            if (bindingResult.hasFieldErrors()) 
            {
            	// エラーメッセージをプロパティファイルから取得
    			errorMsg = MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage());
    			model.addAttribute("errorMsg", errorMsg);
            } 
            else if (bindingResult.getGlobalError() != null) 
            {
            	errorMsg = MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage());
            } 
            else 
            {
                errorMsg = "Validation errors occurred";
            }
            
            model.addAttribute("errorMsg", errorMsg);
            
            return "admin/stockList/index";
        }
                       
        // 検索条件に基づいてセンター情報を取得
        List<StockInfo> stockInfoList = stockInfoService.searchStockInfo(form);
        model.addAttribute("stockInfoList", stockInfoList);
        
        if (stockInfoList.isEmpty()) 
        {
            String errorMsg = messageSource.getMessage(ErrorMessage.STOCK_SEARCH_NOT_RESULT_MESSAGE, null, Locale.getDefault());
            model.addAttribute("errorMsg", errorMsg);
        }
        
        // 検索条件の表示(分類情報)
        List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoData();
        model.addAttribute("categoryInfoList", categoryInfoList);
        
        //検索条件の表示(名称情報)
        List<StockInfo> stockInfoListSearch = stockInfoService.getActiveStockInfoData();
        model.addAttribute("stockInfoListSearch", stockInfoListSearch);
        
        // ログの追加
        logger.info(ScreenName.STOCK + "の" + FeatureName.SEARCH + "を終了します。");

        return "admin/stockList/index";
    }
    
    //検索機能のプルダウン及び一覧の表示
    private void addCommonAttributes(Model model) 
    {
        List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoData();
        model.addAttribute("categoryInfoList", categoryInfoList);

        List<StockInfo> stockInfoListSearch = stockInfoService.getActiveStockInfoData();
        model.addAttribute("stockInfoListSearch", stockInfoListSearch);

        List<StockInfo> stockInfoList = stockInfoService.getActiveStockInfoData();
        model.addAttribute("stockInfoList", stockInfoList);
    }
}