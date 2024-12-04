package com.digitalojt.web.form;

import com.digitalojt.web.validation.CenterInfoFormValidator;

import lombok.Data;

/**
 * 在庫センター情報画面のフォームクラス
 * 
 * @author haruka matano
 *
 */
@Data
@CenterInfoFormValidator
public class CenterInfoForm 
{
	/**
	 * センター名
	 */
	private String centerName;

	/**
	 * 都道府県
	 */
	private String region;

	/**
	 * 容量(From)
	 */
	private Integer storageCapacityFrom;

	/**
	 * 容量(To)
	 */
	private Integer storageCapacityTo;
	
	
	private Integer centerId;
	private String postCode;
	private String address;
	private String phoneNumber;
	private String managerName;
	private Integer operationalStatus;
	private Integer maxStorageCapacity;
	private Integer currentStorageCapacity;
	private String notes;
	
	
//	public CenterInfoForm(CenterInfo centerInfo) 
//	{
//		if (centerInfo == null) 
//		{
//	        throw new IllegalArgumentException("CenterInfo cannot be null");
//	    }
//		
//        this.centerId = centerInfo.getCenterId();
//        this.centerName = centerInfo.getCenterName();
//        this.postCode = centerInfo.getPostCode();
//        this.address = centerInfo.getAddress();
//        this.phoneNumber = centerInfo.getPhoneNumber();
//        this.managerName = centerInfo.getManagerName();
//        this.operationalStatus = centerInfo.getOperationalStatus();
//
//        // 文字列の容量をIntegerに変換する処理
//        try {
//            this.maxStorageCapacity = Integer.parseInt(centerInfo.getMaxStorageCapacity());
//        } catch (NumberFormatException e) {
//            this.maxStorageCapacity = null;  // 変換失敗時はnull
//        }
//
//        try {
//            this.currentStorageCapacity = Integer.parseInt(centerInfo.getCurrentStorageCapacity());
//        } catch (NumberFormatException e) {
//            this.currentStorageCapacity = null;  // 変換失敗時はnull
//        }
//
//        this.notes = centerInfo.getNotes();
//
//    }
	
}
