package com.digitalojt.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.web.entity.CenterInfo;
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
public class CenterInfoService {

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
		List<CenterInfo> centerInfoList = repository.findAll();

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

}

	