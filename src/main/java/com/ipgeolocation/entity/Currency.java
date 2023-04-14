package com.ipgeolocation.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "currencies")
public class Currency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_currency;
	
	@Column
	private String code;
	
	@Column
	private String name;
	
	@Column
	private Double price;
	
	public Currency() {
		super();
	}
	
	public Currency(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public Integer getId_currency() {
		return id_currency;
	}

	public void setId_currency(Integer id_currency) {
		this.id_currency = id_currency;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Currency [id_currency=" + id_currency + ", name=" + name + ", price=" + price + "]";
	}

	

}
