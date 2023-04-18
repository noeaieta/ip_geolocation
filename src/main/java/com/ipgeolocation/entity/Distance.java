package com.ipgeolocation.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "distances")
public class Distance {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="to_buenos_aires")
	private Double toBuenosAires;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "country")
	private Country country;

	
	public Distance() {
		super();
	}


	public Distance(Country country, Double toBuenosAires) {
		super();
		this.toBuenosAires = toBuenosAires;
		this.country = country;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Double getToBuenosAires() {
		return toBuenosAires;
	}


	public void setToBuenosAires(Double toBuenosAires) {
		this.toBuenosAires = toBuenosAires;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}
}
