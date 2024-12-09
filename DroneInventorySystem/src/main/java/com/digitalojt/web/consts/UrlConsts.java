package com.digitalojt.web.consts;

/**
 * URL定数クラス
 *
 * @author haruka matano
 * 
 */
public class UrlConsts {

	// ログイン
	public static final String LOGIN = "/login";

	// 認証
	public static final String AUTHENTICATE = "/authenticate";

	// 在庫一覧画面
	public static final String STOCK_LIST = "/admin/stockList";

	// 在庫一覧画面 検索
	public static final String STOCK_LIST_SEARCH = "/admin/stockList/search";
	
	// 在庫センター情報画面
	public static final String  CENTER_INFO = "/admin/centerInfo";

	// 在庫センター情報画面 検索
	public static final String CENTER_INFO_SEARCH = "/admin/centerInfo/search";
	
	// 在庫センター情報画面 登録
	public static final String CENTER_REGISTER = "/admin/centerInfo/register";
	
	// 在庫センター情報画面 登録後
		public static final String CENTER_REGISTERED = "/admin/centerInfo/register/after";

	// 在庫センター情報画面 更新
	public static final String CENTER_UPDATE = "/admin/centerInfo/update/{id}";
	
	// 在庫センター情報画面 更新
		public static final String CENTER_UPDATED = "/admin/centerInfo/update/after";
		
	// 在庫センター情報画面 削除
	public static final String CENTER_DELETE = "/admin/centerInfo/update/delete/{id}";
	
	// 在庫センター情報画面 削除
	public static final String CENTER_DELETED = "/admin/centerInfo/update/deleted";

	// 認証不要画面
	public static final String[] NO_AUTHENTICATION = {LOGIN, AUTHENTICATE};
	
	//以下追記↓
	//分類情報管理画面
	public static final String CATEGORY_INFO = "/admin/categoryInfo";
	
	//分類情報管理画面 検索
	public static final String CATEGORY_INFO_SEARCH = "/admin/categoryInfo/search";
}
