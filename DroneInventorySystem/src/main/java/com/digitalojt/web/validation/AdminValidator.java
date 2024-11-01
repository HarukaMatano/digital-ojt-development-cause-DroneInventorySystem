package com.digitalojt.web.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.digitalojt.web.consts.ErrorMessage;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = AdminValidatorImpl.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminValidator 
{
	String message() default ErrorMessage.LOGIN_WRONG_INPUT;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
