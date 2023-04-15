package com.ipgeolocation.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "geolocatedip")
public class GeolocatedIP {
	
	@Id
	@Column(name="ip")
	private String ip;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "country")
	private Country country;
	
	public GeolocatedIP() {
		super();
	}

	public GeolocatedIP(String ip) {
		super();
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	

}
