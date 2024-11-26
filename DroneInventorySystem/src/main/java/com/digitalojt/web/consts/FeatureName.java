package com.digitalojt.web.consts;

/**
 * 機能 Enumクラス
 * 
 * @author haruka matano
 */

public enum FeatureName 
{	
	LIST("初期表示処理"),
	SEARCH("検索処理"),
	REGISTER("登録処理"),
	UPDATE("更新処理"),
	DELETE("削除処理");
	
	private final String name;

    FeatureName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

}
