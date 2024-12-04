package com.digitalojt.web.validation;

import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.RegisterParams;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.util.ParmCheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 在庫センター情報画面のバリデーションチェック 実装クラス
 * 
 * @author haruka matano
 */
public class CenterInfoFormValidatorImpl implements ConstraintValidator<CenterInfoFormValidator, CenterInfoForm> 
{

	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(CenterInfoForm form, ConstraintValidatorContext context) 
	{	

		if (areSearchFieldsEmpty(form, context)) 
		{
	        return false;
	    }

	    if (areRegisterFieldsEmpty(form, context)) 
	    {
	        return false;
	    }
   	    
		return true;
	}
	
	//不正文字列と文字数バリデーションメソッド
	private static boolean validateStringField(String field, ConstraintValidatorContext context) 
	{
	    if (ParmCheckUtil.isParameterInvalid(field)) 
	    {
	        context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
	                .addConstraintViolation();
	        return false;
	    }

	    if (field.length() > RegisterParams.REGISTER_MAX_LENGTH) 
	    {
	        context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_NAME_LENGTH_ERROR_MESSAGE)
	                .addConstraintViolation();
	        return false;
	    }
	    return true;
	}
	
	//検索バリデーション
	private boolean areSearchFieldsEmpty(CenterInfoForm form, ConstraintValidatorContext context) 
	{
		System.out.println("検索バリデーション開始");
		
		boolean allFieldsEmpty = 
	            StringUtils.isEmpty(form.getCenterName()) &&
	            StringUtils.isEmpty(form.getRegion()) &&
	            form.getStorageCapacityFrom() == null &&
	            form.getStorageCapacityTo() == null;
		
		System.out.println("allFieldsEmpty:"+allFieldsEmpty);
		
		if (allFieldsEmpty) 
	    {
		     context.disableDefaultConstraintViolation();
		     context.buildConstraintViolationWithTemplate(ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE)
		     .addConstraintViolation();
		     return false;
	    }
		
		// センター名のチェック
 		if (form.getCenterName() != null) 
 		{
 			if(validateStringField(form.getCenterName(), context)){return false;}
 		}

 		// 都道府県のチェック
 		if (form.getRegion() != null) 
 		{
 			// 不正文字列チェック
 			if (ParmCheckUtil.isParameterInvalid(form.getRegion())) 
 			{
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
 	    
 	    if(form.getStorageCapacityFrom() != null &&form.getStorageCapacityTo() != null)
 	    {
 		    if(form.getStorageCapacityFrom()>form.getStorageCapacityTo())
 		    {
 		    	context.disableDefaultConstraintViolation();
 	            context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_SEARCH_UPDOWN_INVALID_MESSAGE)
 	                    .addConstraintViolation();
 	            return false;
 		    }
 	    }
 	   System.out.println("検索バリデーション終了");
		return true;
	}
	
	//登録バリデーション
	private boolean areRegisterFieldsEmpty(CenterInfoForm form, ConstraintValidatorContext context) 
	{
		System.out.println("登録バリデーション開始");
		
		boolean allFieldsEmptyRegister = 
	            StringUtils.isEmpty(form.getCenterName()) ||
	            StringUtils.isEmpty(form.getPostCode()) ||
	            StringUtils.isEmpty(form.getAddress()) ||
	            StringUtils.isEmpty(form.getPhoneNumber()) ||
	            StringUtils.isEmpty(form.getManagerName()) ||
	            form.getOperationalStatus() == null ||
	            form.getMaxStorageCapacity() == null ||
	            form.getCurrentStorageCapacity() == null;

		System.out.println("allFieldsEmptyRegister:"+allFieldsEmptyRegister);
		
		 if (allFieldsEmptyRegister) 
		 {
		      context.disableDefaultConstraintViolation();
		      context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_REGISTER_MAST)
		      .addConstraintViolation();
		       return false;
		 }

			//センター名のチェック
		 		if (form.getCenterName()!= null) 
		 		{
		 			if(validateStringField(form.getCenterName(), context))
		 			{return false;}
		 		}
	     	//郵便番号のチェック
	     	  	 		if (form.getPostCode()!= null) 
	     	  	 		{
	     	  	 			if(validateStringField(form.getPostCode(), context))
	     	  	 			{return false;}
	     	  	 		}
	     	  	    
	     	  	  //住所のチェック
	     	  	 		if (form.getAddress()!= null) 
	     	  	 		{
	     	  	 			if(validateStringField(form.getAddress(), context))
	     	  	 			{return false;}
	     	  	 		}
	     	  	 		
	     	  	  //電話番号
	     	  	 		if (form.getPhoneNumber()!= null) 
	     	  	 		{
	     	  	 			if(validateStringField(form.getPhoneNumber(), context))
	     	  	 			{return false;}
	     	  	 		}
	     	  	  //管理者名
	     	  	 		if (form.getManagerName()!= null) 
	     	  	 		{
	     	  	 			if(validateStringField(form.getManagerName(), context))
	     	  	 			{return false;}
	     	  	 		}
	     	  	 		
	     	  	  //備考
	     	  	 		if (form.getNotes()!= null) 
	     	  	 		{
	     	  	 			if (ParmCheckUtil.isParameterInvalid(form.getNotes())) 
	     	  	 		    {
	     	  	 		        context.disableDefaultConstraintViolation();
	     	  	 		        context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
	     	  	 		                .addConstraintViolation();
	     	  	 		        return false;
	     	  	 		    }
	     	  	 		}

	     	  	  //最大容量
	     	  	 		if (form.getMaxStorageCapacity() != null) 
	     	  		    {
	     	  		        try 
	     	  		        {
	     	  		            Integer.parseInt(form.getMaxStorageCapacity().toString());
	     	  		        } 
	     	  		        catch (NumberFormatException e) 
	     	  		        {
	     	  		            context.disableDefaultConstraintViolation();
	     	  		            context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
	     	  		                    .addConstraintViolation();
	     	  		            return false;
	     	  		        }
	     	  		    }
	     	  	  //現在容量
	     	  	 		if (form.getCurrentStorageCapacity()!= null) 
	     	  		    {
	     	  		        try 
	     	  		        {
	     	  		            Integer.parseInt(form.getCurrentStorageCapacity().toString());
	     	  		        } 
	     	  		        catch (NumberFormatException e) 
	     	  		        {
	     	  		            context.disableDefaultConstraintViolation();
	     	  		            context.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_INPUT_ERROR_MESSAGE)
	     	  		                    .addConstraintViolation();
	     	  		            return false;
	     	  		        }
	     	  		    }
	     	  	 //最大容量と現在容量
	     	  	 		if(form.getMaxStorageCapacity() != null &&form.getCurrentStorageCapacity() != null)
	     	  		    {
	     	  			    if(form.getMaxStorageCapacity()<form.getCurrentStorageCapacity())
	     	  			    {
	     	  			    	context.disableDefaultConstraintViolation();
	     	  		            context.buildConstraintViolationWithTemplate(ErrorMessage.CENTER_REGISTER_UPDOWN_INVALID_MESSAGE)
	     	  		                    .addConstraintViolation();
	     	  		            return false;
	     	  			    }
	     	  		    }
	     	  	 	System.out.println("登録バリデーション終了");
		 return true;
		 
	}
	
}
