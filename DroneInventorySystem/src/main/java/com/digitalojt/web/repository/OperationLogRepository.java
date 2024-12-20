package com.digitalojt.web.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.OperationLog;

/**
 * 操作履歴テーブルリポジトリー
 *
 * @author your name
 * 
 */
@Repository
public interface OperationLogRepository extends JpaRepository<OperationLog, Integer> 
{
	
	//1か月分のログのみ取得する必要がある。SQL
	
	@Query("SELECT o FROM OperationLog o  "
			+ "WHERE (o.createDate>= :month) AND"
			+"(o.deleteFlag='0')")
	List<OperationLog> findOneMonthLog(LocalDateTime month,PageRequest pageable);
}
