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

	public static boolean isHalfWidthDigit(String value) 
	{
		return value != null && value.matches("^[0-9]+$");
	}
	
	
	
}
