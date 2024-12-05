package com.digitalojt.web.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.validation.CenterInfoFormValidatorImpl.RegisterValidatorImpl;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.Payload;

@Constraint(validatedBy = RegisterValidatorImpl.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public interface RegisterValidator extends ConstraintValidator<RegisterValidation, CenterInfoForm> 
{
	String message() default ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
