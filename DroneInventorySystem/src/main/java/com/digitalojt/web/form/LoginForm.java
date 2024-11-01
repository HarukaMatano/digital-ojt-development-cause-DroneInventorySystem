package com.digitalojt.web.form;

import com.digitalojt.web.validation.AdminValidator;

import lombok.Data;

/**
 * ログイン画面のフォームクラス
 * 
 * @author haruka matano
 *
 */
@Data
@AdminValidator 
public class LoginForm {

	/**
	 * 管理者ID
	 */
	private String adminId;

	/**
	 * パスワード
	 */
	private String password;
}
