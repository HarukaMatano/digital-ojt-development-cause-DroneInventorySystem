package com.digitalojt.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index(Model model) 
    {
        // ログの追加
        logger.info(ScreenName.STOCK+"の"+FeatureName.LIST+"を開始します。");

        //一覧表示用
        List<StockInfo> stockInfoList = stockInfoService.getActiveStockInfoData();
        model.addAttribute("stockInfoList", stockInfoList);
        
        //検索条件の表示(分類情報)
        List<CategoryInfo> categoryInfoList=categoryInfoService.getCategoryInfoData();
        model.addAttribute("categoryInfoList", categoryInfoList);

        //検索条件の表示(名称情報)
        List<StockInfo> stockInfoListSearch = stockInfoService.getActiveStockInfoData();
        model.addAttribute("stockInfoListSearch", stockInfoListSearch);
        
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
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "amount", required = false) Integer amount,
            @RequestParam(value = "than", required = false) String than,
            @Valid StockInfoForm form, Model model, BindingResult bindingResult) 
    {
        // ログの追加
        logger.info(ScreenName.STOCK + "の" + FeatureName.SEARCH + "を開始します。");
        
     // フォームにパラメータを設定
        form.setCategory(category);
        form.setName(name);
        form.setAmount(amount);
        form.setThan(than); 
        
        // Valid項目チェック
        if (bindingResult.hasErrors()) 
        { 
        	//検索条件の表示(分類情報)
            List<CategoryInfo> categoryInfoList=categoryInfoService.getCategoryInfoData();
            model.addAttribute("categoryInfoList", categoryInfoList);
            
            //検索条件の表示(名称情報)
            List<StockInfo> stockInfoListSearch = stockInfoService.getActiveStockInfoData();
            model.addAttribute("stockInfoListSearch", stockInfoListSearch);
            
            //一覧表示
            List<StockInfo> stockInfoList = stockInfoService.getActiveStockInfoData();
            model.addAttribute("stockInfoList", stockInfoList);
            
            // エラーメッセージをプロパティファイルから取得
            String errorMsg = MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage());
            model.addAttribute("errorMsg", errorMsg);
            
            return "admin/stockList/index";
        }
               
        
        // 検索条件に基づいてセンター情報を取得
        List<StockInfo> stockInfoList = stockInfoService.searchStockInfo(form);
        model.addAttribute("stockInfoList", stockInfoList);
        
        if (stockInfoList.isEmpty()) 
        {
            model.addAttribute("errorMsg", "該当する在庫情報が見つかりませんでした。");
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
}