package com.digitalojt.web.util;

import java.util.Arrays;

import com.digitalojt.web.consts.InvalidCharacter;

/**
 * パラメーターチェックに関する処理を行うクラス
 * 
 * @author haruka matano
 *
 */
public class ParmCheckUtil {

	/**
	 * 不正文字チェック
	 *  
	 * @param val
	 * @return
	 */
	public static Boolean isParameterInvalid(String val) 
	{

		return Arrays.stream(InvalidCharacter.values())
                .anyMatch(invalidChar -> val.indexOf(invalidChar.getCharacter()) >= 0);
	}
	
	public static Boolean isParameterInvalid(Integer number) 
	{
	    String numberStr = number.toString();
	    return Arrays.stream(InvalidCharacter.values())
	                 .anyMatch(invalidChar -> numberStr.indexOf(invalidChar.getCharacter()) >= 0);
	}

	public static boolean isHalfWidthDigit(Integer amount) 
	{
		String amountStr = amount.toString();
	    for (char c : amountStr.toCharArray()) 
	    {
	        if (c < '0' || c > '9') 
	        {
	            return false; // 半角数字以外の文字が含まれている場合
	        }
	    }
	    return true; // すべて半角数字で構成されている場合
	}
	
}
