package com.digitalojt.web.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 在庫情報Entity
 * 
 * @author haruka matano
 *
 */
@Data
@Getter
@Setter
@Entity
public class StockInfo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stockId;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryInfo categoryinfo;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private CenterInfo centerinfo;
	
	private String name;
	private String description;
	private int amount;
	private String deleteFlag;
	private Timestamp createDate;
	private Timestamp updateDate;

}
