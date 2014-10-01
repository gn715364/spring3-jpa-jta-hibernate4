package com.gn.example.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TABLE_TWO")
public class TableTwo {
	
	@Id
    @Column(name = "TABLE_TWO_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int tableTwoId;
	
	@Column(name = "VALUE", nullable = false)
	private String value;

	public int getTableTwoId() {
		return tableTwoId;
	}

	public void setTableTwoId(int tableTwoId) {
		this.tableTwoId = tableTwoId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
