package com.ipgeolocation.repositories;

import javax.persistence.*;

@Entity
@Table(name = "currencies")
public class Currency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_currency;
	
	@Column
	private String name;
	
	@Column
	private Double quotation;
	
	public Currency() {
		super();
	}
	
	public Currency(String name, Double quotation) {
		super();
		this.name = name;
		this.quotation = quotation;
	}
	
	public Integer getId_currency() {
		return id_currency;
	}

	public void setId_currency(Integer id_currency) {
		this.id_currency = id_currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getQuotation() {
		return quotation;
	}

	public void setQuotation(Double quotation) {
		this.quotation = quotation;
	}

	@Override
	public String toString() {
		return "Currency [id_currency=" + id_currency + ", name=" + name + ", quotation=" + quotation + "]";
	}

	

}
