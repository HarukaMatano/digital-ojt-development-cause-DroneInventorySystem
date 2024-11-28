package com.digitalojt.web.form;

import com.digitalojt.web.validation.StockInfoFormValidator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 在庫一覧画面のフォームクラス
 * 
 * @author haruka matano
 *
 */
@Data
@StockInfoFormValidator
/*追記箇所*/
@Getter
@Setter
public class StockInfoForm 
{
	private String category;
    private String name;
    private Integer amount;
    private String than;
}
