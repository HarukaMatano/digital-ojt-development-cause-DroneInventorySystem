package com.digitalojt.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_info")
public class StockInfo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "stock_id")
	private int stockId;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryInfo categoryinfo;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private CenterInfo centerinfo;
	
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
	private String description;
    
    @Column(name = "amount")
	private int amount;
    
    @Column(name = "delete_flag")
	private String deleteFlag;
    
    @Column(name = "create_date")
	private LocalDateTime createDate;
    
    @Column(name = "update_date")
	private LocalDateTime updateDate;

}
