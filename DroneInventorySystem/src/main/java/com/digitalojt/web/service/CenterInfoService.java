package com.digitalojt.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

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
	public List<CenterInfo> getCenterInfoData() {

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
	public List<CenterInfo> getCenterInfoData(String centerName, String region) {

		// 在庫センター情報作成
		List<CenterInfo> centerInfoList = repository.findAll();

		// 検索処理
		centerInfoList = searchCenterInfoData(centerInfoList, centerName, region);

		return centerInfoList;
	}

	/**
	 * 検索処理
	 * 
	 * @param centerInfoList
	 * @param centerName
	 * @param region 
	 * @return
	 */
	private List<CenterInfo> searchCenterInfoData(List<CenterInfo> centerInfoList, String centerName, String region) {

		List<CenterInfo> hitCenterInfoList = new ArrayList<>();
		
		// 引数の文字列と合致する要素のみリストに追加
		centerInfoList.forEach(item -> {
			if (centerName.equals(item.getCenterName()) && region.equals(item.getAddress())
					|| StringUtils.isEmpty(centerName) && item.getAddress().contains(region)
					|| StringUtils.isEmpty(region) && item.getCenterName().contains(centerName)) {
				hitCenterInfoList.add(item);
			}
		});

		return hitCenterInfoList;
	}
}

	