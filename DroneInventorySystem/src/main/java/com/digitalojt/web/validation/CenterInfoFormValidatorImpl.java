package com.digitalojt.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.RegisterParams;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.util.MessageUtil;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class CenterInfoFormValidatorImpl implements ConstraintValidator<CenterInfoFormValidator, CenterInfoForm> {

	@Override
    public boolean isValid(CenterInfoForm form, ConstraintValidatorContext context) 
	{
        // バリデーションロジックをここに記載
        return true;
    }
	
	
	// 検索バリデーション
	public void validateSearch(CenterInfoForm form, BindingResult bindingResult) 
	{
	    System.out.println("検索バリデーション開始");

	    boolean allFieldsEmpty =
	        StringUtils.isEmpty(form.getCenterName()) &&
	        StringUtils.isEmpty(form.getRegion()) &&
	        form.getStorageCapacityFrom() == null &&
	        form.getStorageCapacityTo() == null;

	    System.out.println("allFieldsEmpty:" + allFieldsEmpty);

	    //空検索
	    if (allFieldsEmpty) 
	    {
	        bindingResult.rejectValue("centerName", "ALL_FIELDS_EMPTY_ERROR_MESSAGE", MessageUtil.getMessage(ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE));
	        System.out.println("Error set for centerName: " + ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE);
	        return;
	    }

	    //不正文字列
	    if (form.getCenterName() != null && validateStringField(form.getCenterName(), bindingResult, "centerName")) return;
	    if (form.getRegion() != null && ParmCheckUtil.isParameterInvalid(form.getRegion())) 
	    {
	        bindingResult.rejectValue("region", "INVALID_INPUT_ERROR_MESSAGE",MessageUtil.getMessage(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE));
	        return;
	    }

	    if (form.getStorageCapacityFrom() != null) 
	    {
	        try {
	            Integer.parseInt(form.getStorageCapacityFrom().toString());
	        } catch (NumberFormatException e) {
	            bindingResult.rejectValue("storageCapacityFrom", "INVALID_INPUT_ERROR_MESSAGE", MessageUtil.getMessage(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE));
	            return;
	        }
	    }

	    if (form.getStorageCapacityTo() != null) {
	        try {
	            Integer.parseInt(form.getStorageCapacityTo().toString());
	        } catch (NumberFormatException e) {
	            bindingResult.rejectValue("storageCapacityTo", "INVALID_INPUT_ERROR_MESSAGE", MessageUtil.getMessage(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE));
	            return;
	        }
	    }

	    if (form.getStorageCapacityFrom() != null && form.getStorageCapacityTo() != null) {
	        if (form.getStorageCapacityFrom() > form.getStorageCapacityTo()) {
	            bindingResult.rejectValue("storageCapacityTo", "CENTER_SEARCH_UPDOWN_INVALID_MESSAGE", MessageUtil.getMessage(ErrorMessage.CENTER_SEARCH_UPDOWN_INVALID_MESSAGE));
	            return;
	        }
	    }

	    System.out.println("検索バリデーション終了");
	}

	public void validateRegister(CenterInfoForm form, BindingResult bindingResult) {
	    System.out.println("登録バリデーション開始");

	    //必須項目
	    if (StringUtils.isEmpty(form.getCenterName())) {
	        bindingResult.rejectValue("centerName", "CENTER_NAME_EMPTY_ERROR_MESSAGE", MessageUtil.getMessage(ErrorMessage.CENTER_REGISTER_MAST));
	        System.out.println("Error set for centerName: " + ErrorMessage.CENTER_SEARCH_NOT_RESULT_MESSAGE);
	        return;
	    }
	    
	    //追記
	    if (form.getCenterName() != null && validateStringField(form.getCenterName(), bindingResult, "centerName")) return;
	    
	    //必須項目
	    if (StringUtils.isEmpty(form.getPostCode())) {
	        bindingResult.rejectValue("postCode", "POST_CODE_EMPTY_ERROR_MESSAGE", MessageUtil.getMessage(ErrorMessage.CENTER_REGISTER_MAST));
	        return;
	    }
	    
	    //追記
	    if (form.getPostCode() != null && validateStringField(form.getPostCode(), bindingResult, "postCode")) return;

	    //必須項目
	    if (StringUtils.isEmpty(form.getAddress())) {
	        bindingResult.rejectValue("address", "ADDRESS_EMPTY_ERROR_MESSAGE", MessageUtil.getMessage(ErrorMessage.CENTER_REGISTER_MAST));
	        return;
	    }

	    //追記
	    if (form.getAddress() != null && validateStringField(form.getAddress(), bindingResult, "Address")) return;
	    
	    //必須項目
	    if (StringUtils.isEmpty(form.getPhoneNumber())) {
	        bindingResult.rejectValue("phoneNumber", "PHONE_NUMBER_EMPTY_ERROR_MESSAGE", MessageUtil.getMessage(ErrorMessage.CENTER_REGISTER_MAST));
	        return;
	    }

	    //追記
	    if (form.getPhoneNumber() != null && validateStringField(form.getPhoneNumber(), bindingResult, "phoneNumber")) return;
	    
	    //必須項目
	    if (StringUtils.isEmpty(form.getManagerName())) {
	        bindingResult.rejectValue("managerName", "MANAGER_NAME_EMPTY_ERROR_MESSAGE", MessageUtil.getMessage(ErrorMessage.CENTER_REGISTER_MAST));
	        return;
	    }

	    //追記
	    if (form.getManagerName() != null && validateStringField(form.getManagerName(), bindingResult, "managerName")) return;
	    
	    //必須項目
	    if (form.getMaxStorageCapacity() == null) {
	        bindingResult.rejectValue("maxStorageCapacity", "MAX_STORAGE_CAPACITY_EMPTY_ERROR_MESSAGE", MessageUtil.getMessage(ErrorMessage.CENTER_REGISTER_MAST));
	        return;
	    }
	    
	    //必須項目
	    if (form.getCurrentStorageCapacity() == null) {
	        bindingResult.rejectValue("currentStorageCapacity", "CURRENT_STORAGE_CAPACITY_EMPTY_ERROR_MESSAGE", MessageUtil.getMessage(ErrorMessage.CENTER_REGISTER_MAST));
	        return;
	    }
	    
	    //大小関係
	    if (form.getMaxStorageCapacity() != null && form.getCurrentStorageCapacity() != null) 
	    {
	    	if(form.getCurrentStorageCapacity() > form.getMaxStorageCapacity() )
	    	{
		        bindingResult.rejectValue("currentStorageCapacity", "CURRENT_STORAGE_CAPACITY_ERROR_MESSAGE",MessageUtil.getMessage(ErrorMessage.CENTER_REGISTER_UPDOWN_INVALID_MESSAGE));
		        return;
	    	}
	    }
	    
	    //備考の不正文字列バリデーション
	    if (form.getNotes() != null && validateStringField(form.getNotes(), bindingResult, "Notes")) return;

	    System.out.println("登録バリデーション終了");
	}
	
    private boolean validateStringField(String field, BindingResult bindingResult, String fieldName) {
        if (ParmCheckUtil.isParameterInvalid(field)) 
        {
        	bindingResult.rejectValue(fieldName, "INVALID_INPUT_ERROR_MESSAGE",MessageUtil.getMessage(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE));
            return true;
        }

        if (field.length() > RegisterParams.REGISTER_MAX_LENGTH) 
        {
            bindingResult.rejectValue(fieldName,"CENTER_NAME_LENGTH_ERROR_MESSAGE", MessageUtil.getMessage(ErrorMessage.CENTER_NAME_LENGTH_ERROR_MESSAGE));
            return true;
        }

        return false;
    }
}