package com.digitalojt.web.validation;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.form.LoginForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdminValidatorImpl implements ConstraintValidator<AdminValidator, LoginForm>
{
	public boolean isValid(String adminId, ConstraintValidatorContext context) {
        
        // 1. adminIdがnullまたは空かをチェック
        if (adminId == null || adminId.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorMessage.LOGIN_WRONG_INPUT)
                    .addConstraintViolation();
            return false;
        }

        // 2. 不正文字列チェック
        if (ParmCheckUtil.isParameterInvalid(adminId)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorMessage.LOGIN_WRONG_INPUT)
                    .addConstraintViolation();
            return false;
        }

        // バリデーション通過
        return true;
    }
}
