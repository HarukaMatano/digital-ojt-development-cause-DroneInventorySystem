package com.digitalojt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digitalojt.api.entity.StockInfo;

public interface StockInfoRepository extends JpaRepository<StockInfo, Integer> {

    // 稼働中の保管場所の在庫情報取得
    @Query("SELECT s FROM StockInfo s " +
           "JOIN s.categoryinfo c " +
           "JOIN s.centerinfo ci " +
           "WHERE s.deleteFlag = '0' " +
           "AND c.deleteFlag = '0' " +
           "AND ci.operationalStatus = 0 " +
           "AND ci.deleteFlag = '0'")
    List<StockInfo> findActiveStockInfo();

    // カテゴリ名による取得
    @Query("SELECT s FROM StockInfo s " +
           "JOIN s.categoryinfo c " +
           "JOIN s.centerinfo ci " +
           "WHERE s.deleteFlag = '0' " +
           "AND c.deleteFlag = '0' " +
           "AND ci.operationalStatus = 0 " +
           "AND ci.deleteFlag = '0' " +
           "AND (:category = '' OR c.categoryName = :category) ")
    List<StockInfo> findByCategory(
        @Param("category") String category
    );

    // 名前による取得
    @Query("SELECT s FROM StockInfo s " +
           "JOIN s.categoryinfo c " +
           "JOIN s.centerinfo ci " +
           "WHERE s.deleteFlag = '0' " +
           "AND c.deleteFlag = '0' " +
           "AND ci.operationalStatus = 0 " +
           "AND ci.deleteFlag = '0' " +
           "AND (:name IS NULL OR :name = '' OR s.name = :name) ")
    List<StockInfo> findByName(
        @Param("name") String name
    );

    // 個数による取得
    @Query("SELECT s FROM StockInfo s " +
           "JOIN s.categoryinfo c " +
           "JOIN s.centerinfo ci " +
           "WHERE s.deleteFlag = '0' " +
           "AND c.deleteFlag = '0' " +
           "AND ci.operationalStatus = 0 " +
           "AND ci.deleteFlag = '0' " +
           "AND (:amount IS NULL OR " +
           "   (:than = '以上' AND s.amount >= :amount) OR " +
           "   (:than = '以下' AND s.amount <= :amount))")
    List<StockInfo> findByAmount(
        @Param("amount") Integer amount,
        @Param("than") String than
    );

    // カテゴリ名、名前、個数による取得
    @Query("SELECT s FROM StockInfo s " +
           "JOIN s.categoryinfo c " +
           "JOIN s.centerinfo ci " +
           "WHERE s.deleteFlag = '0' " +
           "AND c.deleteFlag = '0' " +
           "AND ci.operationalStatus = 0 " +
           "AND ci.deleteFlag = '0' " +
           "AND (:category IS NULL OR :category = '' OR c.categoryName = :category) " +
           "AND (:name IS NULL OR :name = '' OR s.name = :name) " +
           "AND (:amount IS NULL OR " +
           "   (:than = '以上' AND s.amount >= :amount) OR " +
           "   (:than = '以下' AND s.amount <= :amount))")
    List<StockInfo> findByCategoryAndNameAndAmount(
        @Param("category") String category,
        @Param("name") String name,
        @Param("amount") Integer amount,
        @Param("than") String than
    );

    // カテゴリ名、名前取得
    @Query("SELECT s FROM StockInfo s " +
           "JOIN s.categoryinfo c " +
           "JOIN s.centerinfo ci " +
           "WHERE s.deleteFlag = '0' " +
           "AND c.deleteFlag = '0' " +
           "AND ci.operationalStatus = 0 " +
           "AND ci.deleteFlag = '0' " +
           "AND (:category IS NULL OR :category = '' OR c.categoryName = :category) " +
           "AND (:name IS NULL OR :name = '' OR s.name = :name)")
    List<StockInfo> findByCategoryAndName(
        @Param("category") String category,
        @Param("name") String name
    );

    // カテゴリ名、個数による取得
    @Query("SELECT s FROM StockInfo s " +
           "JOIN s.categoryinfo c " +
           "JOIN s.centerinfo ci " +
           "WHERE s.deleteFlag = '0' " +
           "AND c.deleteFlag = '0' " +
           "AND ci.operationalStatus = 0 " +
           "AND ci.deleteFlag = '0' " +
           "AND (:category IS NULL OR :category = '' OR c.categoryName = :category) " +
           "AND (:amount IS NULL OR " +
           "   (:than = '以上' AND s.amount >= :amount) OR " +
           "   (:than = '以下' AND s.amount <= :amount))")
    List<StockInfo> findByCategoryAndAmount(
        @Param("category") String category,
        @Param("amount") Integer amount,
        @Param("than") String than
    );

    // 名前、個数による取得
    @Query("SELECT s FROM StockInfo s " +
           "JOIN s.categoryinfo c " +
           "JOIN s.centerinfo ci " +
           "WHERE s.deleteFlag = '0' " +
           "AND c.deleteFlag = '0' " +
           "AND ci.operationalStatus = 0 " +
           "AND ci.deleteFlag = '0' " +
           "AND (:name IS NULL OR :name = '' OR s.name = :name) " +
           "AND (:amount IS NULL OR " +
           "   (:than = '以上' AND s.amount >= :amount) OR " +
           "   (:than = '以下' AND s.amount <= :amount))")
    List<StockInfo> findByNameAndAmount(
        @Param("name") String name,
        @Param("amount") Integer amount,
        @Param("than") String than
    );
}