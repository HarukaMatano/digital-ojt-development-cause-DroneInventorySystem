package com.digitalojt.web.consts;

/**
 * エラーメッセージ定数クラス
 * 
 * @author haruka matano
 *
 */
public class ErrorMessage 
{
	
	// ログイン情報の入力に誤りがあった場合に、出力するエラーメッセージのID
	public static final String  LOGIN_WRONG_INPUT = "login.wrongInput";

	// すべての項目が空の場合のエラーメッセージ
	public static final String ALL_FIELDS_EMPTY_ERROR_MESSAGE = "allField.empty";

	// 空文字検索に関するエラーメッセージ
	public static final String UNEXPECTED_INPUT_ERROR_MESSAGE = "unexpected.input";

	// 不正な文字列を使用した検索に関するエラーメッセージ
	public static final String INVALID_INPUT_ERROR_MESSAGE = "invalid.input";

	// 文字超過に関するエラーメッセージ
	public static final String CENTER_NAME_LENGTH_ERROR_MESSAGE = "centerName.length.wrongInput";
	
	/*追記*/
	// 空文字検索に関するエラーメッセージ
	public static final String CATEGORY_EMPTY_ERROR_MESSAGE = "categoryName.empty";

	// 文字超過に関するエラーメッセージ
	public static final String CATEGORY_LENGTH_ERROR_MESSAGE = "categoryName.length.wrongInput";
	
	// 不正な文字列を使用した検索に関するエラーメッセージ
	public static final String CATEGORY_INVALID_INPUT_ERROR_MESSAGE = "categoryName.invalid.input";
	
	// 不正な文字列を使用した検索に関するエラーメッセージ
	public static final String STOCK_INVALID_INPUT_ERROR_MESSAGE = "amount.invalid.input";
		
	// 不正な文字列を使用した検索に関するエラーメッセージ
	public static final String STOCK_NOTNUMBER_INPUT_ERROR_MESSAGE = "amount.notNumber.input";
			
	// 不正な文字列を使用した検索に関するエラーメッセージ
	public static final String STOCK_NOT_INPUT_AMOUNT_MESSAGE = "amount.not.input";
	
	public static final String STOCK_NOT_INPUT_MESSAGE = "updown.not.input";
	
	public static final String STOCK_SEARCH_NOT_RESULT_MESSAGE = "stock.not.result";
	
	public static final String CENTER_SEARCH_NOT_RESULT_MESSAGE = "center.not.result";
	
	public static final String CENTER_SEARCH_UPDOWN_INVALID_MESSAGE = "center.invalid.updown";
	
	public static final String CENTER_REGISTER_UPDOWN_INVALID_MESSAGE = "center.invalid.updown.register";
	
	public static final String CENTER_REGISTER_MAST = "center.mast";
	
	public static final String STOCK_AMOUNT_MINUS = "amount.minus.input";

	public static final String CENTER_UPDAWN_MINUS = "center.minus.input";
	
	public static final String CENTER_CAPACITY_MINUS = "center.minus.input.cap";

}
