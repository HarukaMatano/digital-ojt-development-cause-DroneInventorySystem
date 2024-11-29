package com.digitalojt.web.validation;

import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.SearchParams;
import com.digitalojt.web.form.CategoryInfoForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryInfoFormValidatorImpl implements ConstraintValidator<CategoryInfoFormValidator, CategoryInfoForm>
{
	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(CategoryInfoForm form, ConstraintValidatorContext context) 
	{
		
		boolean allFieldsEmpty = StringUtils.isEmpty(form.getCategoryName());
	
		// フィールドが空かをチェック
		if (allFieldsEmpty) 
		{ 
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorMessage.CATEGORY_EMPTY_ERROR_MESSAGE)
					.addConstraintViolation();
			return false;
		}
		
		// 分類名のチェック
		if (form.getCategoryName() != null) 
		{
			
			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getCategoryName())) 
			{
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CATEGORY_INVALID_INPUT_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
			
			// 文字数チェック
			if (form.getCategoryName().length() > SearchParams.SEARCH_MAX_LENGTH) 
			{
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ErrorMessage.CATEGORY_LENGTH_ERROR_MESSAGE)
						.addConstraintViolation();
				return false;
			}
		}

		// その他のバリデーションに問題なければtrueを返す
		return true;
	}
}
