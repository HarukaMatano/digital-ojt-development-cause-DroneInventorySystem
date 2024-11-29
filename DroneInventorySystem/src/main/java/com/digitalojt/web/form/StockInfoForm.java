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
//	@NotEmpty(message = "Not Empty Category")
	private String category;
	
//	@NotEmpty(message = "Not Empty Name")
    private String name;
	
//	@NotNull(message = "Not Empty Amount")
    private Integer amount;
	
//	@NotEmpty(message = "Not Empty Than")
    private String than;
}
