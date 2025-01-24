package com.digitalojt.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * センター情報Entity
 * 
 * @author haruka matano
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "center_info")
public class CenterInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "center_id")
	private int centerId;
	
	@Column(name = "center_name")
	private String centerName;
	
	@Column(name = "post_code")
	private String postCode;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "manager_name")
	private String managerName;
	
	@Column(name = "operational_status")
	private int operationalStatus;
	
	@Column(name = "max_storage_capacity")
	private String maxStorageCapacity;
	
	@Column(name = "current_storage_capacity")
	private String currentStorageCapacity;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "delete_flag")
	private String deleteFlag;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

}
