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

//		boolean allFieldsEmpty = 
//				StringUtils.isEmpty(form.getCategory()) &&
//				StringUtils.isEmpty(form.getName()) &&
//				form.getAmount()==null&&
//				StringUtils.isEmpty(form.getThan());

				
//		if(form.getAmount()==null&&!(StringUtils.isEmpty(form.getThan())))
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
//		if (allFieldsEmpty) 
		if (isAllFieldsEmpty(form))
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE)
					.addConstraintViolation();
			return false;
		}


		// 個数のチェック
//		if (form.getAmount() != null)
		if (form.getAmount() != null && !isHalfWidthDigit(form.getAmount().toString()))
		{

			// 不正文字列チェック(半角文字列でない場合はバリデーション対象の為コメントアウトしました。)
//			if (ParmCheckUtil.isParameterInvalid(form.getAmount())) 
//			{
//				context.disableDefaultConstraintViolation();
//				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
//				.addConstraintViolation();
//			return false;
//			}
			
 		    // 半角数字チェック
			if (isHalfWidthDigit(form.getAmount().toString())) 
			{
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(ErrorMessage.STOCK_NOTNUMBER_INPUT_ERROR_MESSAGE)
                        .addConstraintViolation();
                return false;
            }
		 }


		// その他のバリデーションに問題なければtrueを返す
		return true;
	}
	
	
	//メソッド
	private boolean isAllFieldsEmpty(StockInfoForm form) 
	{
		return StringUtils.isEmpty(form.getCategory()) &&
		StringUtils.isEmpty(form.getName()) &&
		form.getAmount() == null &&
		StringUtils.isEmpty(form.getThan());
	}
	
	private boolean isHalfWidthDigit(String value) 
	{
		return ParmCheckUtil.isHalfWidthDigit(value);
	}
}


