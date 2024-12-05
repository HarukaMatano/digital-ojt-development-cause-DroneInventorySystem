package com.digitalojt.web.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.validation.CenterInfoFormValidatorImpl.SearchValidatorImpl;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.Payload;

@Constraint(validatedBy = SearchValidatorImpl.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public interface SearchValidator extends ConstraintValidator<SearchValidation, CenterInfoForm> 
{
	String message() default ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
