package com.digitalojt.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 管理者情報Entity
 * 
 * @author haruka matano
 *
 */
@Data
@Getter
@Setter
@Entity
@Table(name="admin_info")
public class AdminInfo {

	/**
	 * 管理者ID
	 */
	@Id
	@Column(name = "admin_id")
	private String adminId;
	
	/**
	 * 管理者名
	 */
	@Column(name = "admin_name")
	private String adminName;
	
	/**
	 * パスワード
	 */
	@Column(name = "password")
	private String password;
}
