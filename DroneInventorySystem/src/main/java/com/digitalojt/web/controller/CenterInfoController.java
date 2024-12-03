package com.digitalojt.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.FeatureName;
import com.digitalojt.web.consts.Region;
import com.digitalojt.web.consts.ScreenName;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.service.CenterInfoService;
import com.digitalojt.web.util.MessageManager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 在庫センター情報画面のコントローラークラス
 * 
 * @author haruka matano
 *
 */
@Controller
@RequiredArgsConstructor
public class CenterInfoController extends AbstractController 
{

	// ロガーの追加
    private static final Logger logger = LoggerFactory.getLogger(CenterInfoController.class);

	
	/** センター情報 サービス */
	private final CenterInfoService centerInfoService;

	/** メッセージソース */
	private final MessageSource messageSource;

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO)
	public String index(Model model,CenterInfoForm form) 
	{

		// ログの追加
        logger.info(ScreenName.STOCK_CENETR+"の"+FeatureName.LIST+"を開始します。");
        
        //検索条件保持の為
        model.addAttribute("CenterInfoForm", form);
        
		// 在庫センター情報画面に表示するデータを取得
		List<CenterInfo> centerInfoList = centerInfoService.getCenterInfoData();

		// 画面表示用に商品情報リストをセット
		model.addAttribute("centerInfoList", centerInfoList);

		// 都道府県Enumをリストに変換
		List<Region> regions = Arrays.asList(Region.values());

		// 都道府県プルダウン情報をセット
		model.addAttribute("regions", regions);

		// ログの追加
        logger.info(ScreenName.STOCK_CENETR+"の"+FeatureName.LIST+"を終了します。");
        
		return "admin/centerInfo/index";
	}

	/**
	 * 検索結果表示
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping(UrlConsts.CENTER_INFO_SEARCH)
	public String search(Model model, @Valid CenterInfoForm form, BindingResult bindingResult) 
	{

		// ログの追加
        logger.info(ScreenName.STOCK_CENETR+"の"+FeatureName.SEARCH+"を開始します。");
        		
        
		// Valid項目チェック
		if (bindingResult.hasErrors()) 
		{
			// エラーメッセージをプロパティファイルから取得
			String errorMsg = MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage());
			model.addAttribute("errorMsg", errorMsg);

			// 都道府県Enumをリストに変換
			List<Region> regions = Arrays.asList(Region.values());

			// 都道府県プルダウン情報をセット
			model.addAttribute("regions", regions);
			
			//検索条件保持の為
	        model.addAttribute("CenterInfoForm", form);

			return "admin/centerInfo/index";
		}

		// 在庫センター情報画面に表示するデータを取得→画面表示用に商品情報リストをセット
		List<CenterInfo> centerInfoList = centerInfoService.getCenterInfoData(form.getCenterName(), form.getRegion(), form.getStorageCapacityFrom(),form.getStorageCapacityTo());
		model.addAttribute("centerInfoList", centerInfoList);
		
		if (centerInfoList.isEmpty()) 
        {
            String errorMsg = messageSource.getMessage(ErrorMessage.CENTER_SEARCH_NOT_RESULT_MESSAGE, null, Locale.getDefault());
            model.addAttribute("errorMsg", errorMsg);
        }

		model.addAttribute("CenterInfoForm", form);
		
		// 都道府県Enumをリストに変換→都道府県プルダウン情報をセット
		List<Region> regions = Arrays.asList(Region.values());
		model.addAttribute("regions", regions);

		// ログの追加
        logger.info(ScreenName.STOCK_CENETR+"の"+FeatureName.SEARCH+"を終了します。");
		
		return "admin/centerInfo/index";
	}
	
	/**
	 * 登録
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	//新規登録画面移動
	@GetMapping(UrlConsts.CENTER_REGISTER)
    public String showCreateForm(Model model,CenterInfoForm form) 
    {
		// ログの追加
        logger.info(ScreenName.STOCK_CENETR+"の"+FeatureName.REGISTER+"を開始します。");
        
        model.addAttribute("CenterInfoForm", form);
        return "admin/centerInfo/register"; 
    }
	
	//新規登録処理
	@PostMapping(UrlConsts.CENTER_REGISTERED)
    public String register(@ModelAttribute @Valid CenterInfoForm form,BindingResult bindingResult,Model model) 
    {	
		
		if (bindingResult.hasErrors()) 
		{
			String errorMsg = MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage());
			model.addAttribute("errorMsg", errorMsg);
			model.addAttribute("CenterInfoForm", form);
	        // バリデーションエラーがある場合、エラーメッセージを表示する画面にリダイレクト
	        return "admin/centerInfo/register";
	    }
		
		centerInfoService.register(form);
		// ログの追加
        logger.info(ScreenName.STOCK_CENETR+"の"+FeatureName.REGISTER+"を終了します。");
        return "redirect:/admin/centerInfo"; // 登録後に一覧画面にリダイレクト
    }
	
	//更新画面移動

	
}
