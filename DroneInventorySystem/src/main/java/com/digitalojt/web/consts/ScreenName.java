package com.digitalojt.web.consts;

/**
 * 画面名 Enumクラス
 * 
 * @author haruka matano
 */

public enum ScreenName 
{	
	
	STOCK("在庫一覧画面"),
	STOCK_CENETR("在庫センター画面");
	
	
	private final String name;

    ScreenName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

}
