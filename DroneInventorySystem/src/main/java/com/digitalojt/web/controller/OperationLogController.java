package com.digitalojt.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.OperationLog;
import com.digitalojt.web.service.OperationLogService;
import com.digitalojt.web.util.MessageManager;

import lombok.RequiredArgsConstructor;

/**
 * 操作履歴画面のコントローラークラス
 * 
 * @author dotlife kijima
 *
 */
@Controller
@RequiredArgsConstructor
public class OperationLogController 
{

	/** 操作履歴 サービス */
	private final OperationLogService operationLogService;

	/** メッセージソース */
	private final MessageSource messageSource;
	
    // ロガーの追加(不具合改修の問題の特定時に追記)
    private static final Logger logger = LoggerFactory.getLogger(StockListController.class);

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.OPERATION_LOG)
	public String index(Model model) 
	{
		// ログの追加(不具合改修の問題の特定時に追記)
        logger.info("操作履歴の初期表示を開始します。");
		
		try 
		{
			// 操作履歴情報の取得
			List<OperationLog> operationLogList = operationLogService.getOperationLogList();
			model.addAttribute("operationLogList", operationLogList);
		} 
		catch (Exception e) 
		{	
			String errorMsg = MessageManager.getMessage(messageSource, "unexpected.error");
            model.addAttribute("errorMsg", errorMsg);
            
            //追記
            List<OperationLog> operationLogList=null;
            model.addAttribute("operationLogList", operationLogList);
		}

		// ログの追加(不具合改修の問題の特定時に追記)
        logger.info("操作履歴の初期表示を終了します。");
		
		return "admin/operationLog/index";
	}
}
