package com.digitalojt.web.validation;

import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.SearchParams;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 在庫センター情報画面のバリデーションチェック 実装クラス
 * 
 * @author haruka matano
 */
public class CenterInfoFormValidatorImpl implements ConstraintValidator<CenterInfoFormValidator, CenterInfoForm> {

	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(CenterInfoForm form, ConstraintValidatorContext context) {

		boolean allFieldsEmpty = 
				StringUtils.isEmpty(form.getCenterName()) &&
				StringUtils.isEmpty(form.getRegion())&&
				form.getStorageCapacityFrom()==null&&
				form.getStorageCapacityTo()==null
				;

		// すべてのフィールドが空かをチェック
		if (allFieldsEmpty) 
		{
			System.out.println("フィールドは空です。"); 
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE)
					.addConstraintViolation();
			return false;
		}


		// センター名のチェック
		if (form.getCenterName() != null) 
		{

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getCenterName())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
			
			// 文字数チェック
			if (form.getCenterName().length() > SearchParams.SEARCH_MAX_LENGTH) 
			{
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_NAME_LENGTH_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// 都道府県のチェック
		if (form.getRegion() != null) {

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getRegion())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}
		
		// 現在容量のチェック
	    if (form.getStorageCapacityFrom() != null) 
	    {
	        try 
	        {
	            Integer.parseInt(form.getStorageCapacityFrom().toString());
	        } 
	        catch (NumberFormatException e) 
	        {
	            context.disableDefaultConstraintViolation();
	            context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
	                    .addConstraintViolation();
	            return false;
	        }
	    }

	    if (form.getStorageCapacityTo() != null) 
	    {
	        try 
	        {
	            Integer.parseInt(form.getStorageCapacityTo().toString());
	        } 
	        catch (NumberFormatException e) 
	        {
	            context.disableDefaultConstraintViolation();
	            context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
	                    .addConstraintViolation();
	            return false;
	        }
	    }
		

		// その他のバリデーションに問題なければtrueを返す
		return true;
	}
}
