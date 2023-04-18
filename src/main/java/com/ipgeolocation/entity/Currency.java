package com.ipgeolocation.entity;

import javax.persistence.*;

@Entity
@Table(name = "currencies")
public class Currency {
	
	@Id
	@Column
	private String code;
	
	@Column
	private String name;
	
	public Currency(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public Currency() {
		super();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
