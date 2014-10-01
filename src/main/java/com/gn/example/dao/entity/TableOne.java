package com.gn.example.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TABLE_ONE")
public class TableOne {
	
	@Id
    @Column(name = "TABLE_ONE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int tableOneId;
	
	@Column(name = "VALUE", nullable = false)
	private String value;

	public int getTableOneId() {
		return tableOneId;
	}

	public void setTableOneId(int tableOneId) {
		this.tableOneId = tableOneId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
