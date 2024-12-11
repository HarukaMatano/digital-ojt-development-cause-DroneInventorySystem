package com.digitalojt.web.validation;

import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.form.StockInfoForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 在庫一覧画面のバリデーションチェック 実装クラス
 * 
 * @author haruka matano
 */
public class StockInfoFormValidatorImpl implements ConstraintValidator<StockInfoFormValidator, StockInfoForm> 
{

	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(StockInfoForm form, ConstraintValidatorContext context) 
	{
				
		if (form.getAmount() == null && !StringUtils.isEmpty(form.getThan()))
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorMessage.STOCK_NOT_INPUT_AMOUNT_MESSAGE)
					.addConstraintViolation();
			return false;
		}
		
		if(form.getAmount()!=null&&(StringUtils.isEmpty(form.getThan())))
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorMessage.STOCK_NOT_INPUT_MESSAGE)
					.addConstraintViolation();
			return false;
		}
		
		// すべてのフィールドが空かをチェック
		if (isAllFieldsEmpty(form))
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE)
					.addConstraintViolation();
			return false;
		}


		// 個数のチェック
		if (form.getAmount() != null && !isHalfWidthDigit(form.getAmount().toString()))
		{			
 		    // 半角数字チェック
			if (isHalfWidthDigit(form.getAmount().toString())) 
			{
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(ErrorMessage.STOCK_NOTNUMBER_INPUT_ERROR_MESSAGE)
                        .addConstraintViolation();
                return false;
            }
			
			//個数のマイナス値
			if(form.getAmount()<0)
			{
				context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(ErrorMessage.STOCK_AMOUNT_MINUS)
                        .addConstraintViolation();
                return false;
			}
		 }


		// その他のバリデーションに問題なければtrueを返す
		return true;
	}
	
	
	//メソッド
	//全ての項目が空欄かどうかの論理
	private boolean isAllFieldsEmpty(StockInfoForm form) 
	{
		return StringUtils.isEmpty(form.getCategory()) &&
		StringUtils.isEmpty(form.getName()) &&
		form.getAmount() == null &&
		StringUtils.isEmpty(form.getThan());
	}
	
	//半角数字かどうかの論理
	private boolean isHalfWidthDigit(String value) 
	{
		return ParmCheckUtil.isHalfWidthDigit(value);
	}
}


