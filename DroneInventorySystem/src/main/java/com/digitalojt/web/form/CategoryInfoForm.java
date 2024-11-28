package com.digitalojt.web.form;

import com.digitalojt.web.validation.CategoryInfoFormValidator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 分類情報画面のフォームクラス
 * 
 * @author haruka matano
 *
 */
@Data

/*追記箇所*/
@Getter
@Setter

@CategoryInfoFormValidator
public class CategoryInfoForm 
{
	/**
	 * 分類名
	 */
	private String categoryName;

}
