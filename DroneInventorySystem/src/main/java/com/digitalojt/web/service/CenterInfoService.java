package com.digitalojt.web.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.web.entity.CenterInfo;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.repository.CenterInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 在庫センター情報画面のサービスクラス
 *
 * @author haruka matano
 * 
 */
@Service
@RequiredArgsConstructor
public class CenterInfoService 
{

	/** センター情報テーブル リポジトリー */
	private final CenterInfoRepository repository;
	
	/**
	 * 在庫センター情報を全件検索で取得
	 * 
	 * @return
	 */
	public List<CenterInfo> getCenterInfoData() 
	{

		// 在庫センター情報作成
//		List<CenterInfo> centerInfoList = repository.findAll();
		
		//削除フラグと稼働フラグのフィルター
		List<CenterInfo> centerInfoList = repository.findByOperrationalStatusAndDeleteFlag();

		return centerInfoList;
	}

	/**
	 * 引数に合致する在庫センター情報を取得
	 * 
	 * @param centerName
	 * @param region 
	 * @param storageCapacityFrom 
	 * @param storageCapacityTo
	 * @return 
	 */
	public List<CenterInfo> getCenterInfoData(String centerName, String region,Integer storageCapacityFrom,Integer storageCapacityTo) 
	{
		// 検索処理
		List<CenterInfo> centerInfoList = repository.findByCenterNameAndRegionAndStorageCapacity(centerName, region, storageCapacityFrom, storageCapacityTo);
		
		return centerInfoList;
	}
	
	//登録
	public void register(CenterInfoForm form) 
    {
        CenterInfo centerInfo = new CenterInfo();
        centerInfo.setCenterName(form.getCenterName());
        centerInfo.setPostCode(form.getPostCode());
        centerInfo.setAddress(form.getAddress());
        centerInfo.setPhoneNumber(form.getPhoneNumber());
        centerInfo.setManagerName(form.getManagerName());
        centerInfo.setOperationalStatus(form.getOperationalStatus());
        centerInfo.setMaxStorageCapacity(String.valueOf(form.getMaxStorageCapacity()));
        centerInfo.setCurrentStorageCapacity(String.valueOf(form.getCurrentStorageCapacity()));
        centerInfo.setNotes(form.getNotes());
        centerInfo.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        centerInfo.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
        centerInfo.setDeleteFlag("0");      
        repository.save(centerInfo); // データベースに保存
    }
	
	//更新
		public void update(CenterInfoForm form) 
	    {
	        CenterInfo centerInfo = repository.findByCenterId(form.getCenterId());
	        
	        centerInfo.setCenterName(form.getCenterName());
	        centerInfo.setPostCode(form.getPostCode());
	        centerInfo.setAddress(form.getAddress());
	        centerInfo.setPhoneNumber(form.getPhoneNumber());
	        centerInfo.setManagerName(form.getManagerName());
	        centerInfo.setOperationalStatus(form.getOperationalStatus());
	        centerInfo.setMaxStorageCapacity(String.valueOf(form.getMaxStorageCapacity()));
	        centerInfo.setCurrentStorageCapacity(String.valueOf(form.getCurrentStorageCapacity()));
	        centerInfo.setNotes(form.getNotes());
	        centerInfo.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
	        centerInfo.setDeleteFlag("0");        
	        repository.save(centerInfo); // データベースに保存
	    }
		
		//削除
		public void delete(CenterInfoForm form) 
	    {	
			CenterInfo centerInfo = repository.findByCenterId(form.getCenterId());
			centerInfo.setCenterName(form.getCenterName());
	        centerInfo.setPostCode(form.getPostCode());
	        centerInfo.setAddress(form.getAddress());
	        centerInfo.setPhoneNumber(form.getPhoneNumber());
	        centerInfo.setManagerName(form.getManagerName());
	        centerInfo.setOperationalStatus(0);
	        centerInfo.setMaxStorageCapacity(String.valueOf(form.getMaxStorageCapacity()));
	        centerInfo.setCurrentStorageCapacity(String.valueOf(form.getCurrentStorageCapacity()));
	        centerInfo.setNotes(form.getNotes());
	        centerInfo.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
	        centerInfo.setDeleteFlag("1");        
	        repository.save(centerInfo); // データベースに保存
	    }
	
	//ID検索
	public CenterInfo getCenterInfoDataById(Long id)
	{
		CenterInfo centerInfoList =repository.findByCenterId(id);
		return centerInfoList;
	}
	
}

	