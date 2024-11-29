package com.digitalojt.web.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String index(Model model) 
	{

		// ログの追加
        logger.info(ScreenName.STOCK_CENETR+"の"+FeatureName.LIST+"を開始します。");
        
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

			return "admin/centerInfo/index";
		}

		// 在庫センター情報画面に表示するデータを取得→画面表示用に商品情報リストをセット
		List<CenterInfo> centerInfoList = centerInfoService.getCenterInfoData(form.getCenterName(), form.getRegion(), form.getStorageCapacityFrom(),form.getStorageCapacityTo());
		model.addAttribute("centerInfoList", centerInfoList);

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
	@PostMapping(UrlConsts.CENTER_REGISTER)
	public String register(Model model, @Valid CenterInfoForm form, BindingResult bindingResult) 
	{

		// ログの追加
        logger.info(ScreenName.STOCK_CENETR+"の"+FeatureName.REGISTER+"を開始します。");
        		
		

		// ログの追加
        logger.info(ScreenName.STOCK_CENETR+"の"+FeatureName.REGISTER+"を終了します。");
		
		return "admin/centerInfo/register";
	}
	
	/**
	 * 更新
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping(UrlConsts.CENTER_REGISTER)
	public String update(Model model, @Valid CenterInfoForm form, BindingResult bindingResult) 
	{

		// ログの追加
        logger.info(ScreenName.STOCK_CENETR+"の"+FeatureName.UPDATE+"を開始します。");
        		
		

		// ログの追加
        logger.info(ScreenName.STOCK_CENETR+"の"+FeatureName.UPDATE+"を終了します。");
		
		return "admin/centerInfo/register";
	}
}
