package com.digitalojt.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.form.LoginForm;
import com.digitalojt.web.util.MessageManager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * ログイン画面のコントローラークラス
 * 
 * @author haruka matano
 *
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

    /** メッセージソース */
    private final MessageSource messageSource;

    /** 認証マネージャ */
    private final AuthenticationManager authenticationManager;

    /**
     * 初期表示
     * 
     * @param model
     * @return
     */
    @GetMapping(UrlConsts.LOGIN)
    public String index(Model model, LoginForm form) 
    {
        return "admin/login/index";
    }

    /**
     * 初期表示
     * 
     * @param model
     * @return 
     * @return
     */
    @PostMapping(UrlConsts.AUTHENTICATE)
    public String login(@Valid LoginForm form, BindingResult result, Model model, RedirectAttributes redirectAttributes) 
    {

    	 {

            // バリデーションエラーチェック
            if (result.hasErrors()) {
                System.out.println("Validation errors: " + result.getAllErrors());
                String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LOGIN_WRONG_INPUT);
                redirectAttributes.addFlashAttribute("errorMsg", errorMsg);
                return "redirect:" + UrlConsts.LOGIN;
            }

            try {
                // 認証トークンの作成
                UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(form.getAdminId(), form.getPassword());

                // 認証を実行
                System.out.println("Attempting to authenticate...");
                Authentication authentication = authenticationManager.authenticate(authenticationToken);

                // 認証成功時のリダイレクト
                if (authentication.isAuthenticated()) {
                    System.out.println("Authentication successful for: " + form.getAdminId());
                    return "redirect:" + UrlConsts.STOCK_LIST;
                }
            } 
            catch (AuthenticationException e) {
                System.out.println("Authentication failed: " + e.getMessage());
                String errorMsg = MessageManager.getMessage(messageSource, ErrorMessage.LOGIN_WRONG_INPUT);
                redirectAttributes.addFlashAttribute("errorMsg", errorMsg);
            }

            return "redirect:" + UrlConsts.LOGIN;
    	 }
    	}
    }
