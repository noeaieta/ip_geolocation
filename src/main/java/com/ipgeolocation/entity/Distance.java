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
	
	@Column
	private Double distance;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "geolocatedip",referencedColumnName="ip")
	private GeolocatedIP geolocatedIp;

	
	public Distance() {
		super();
	}

	public Distance(Double distance, GeolocatedIP geolocatedIp) {
		super();
		this.distance = distance;
		this.geolocatedIp = geolocatedIp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public GeolocatedIP getGeolocatedIp() {
		return geolocatedIp;
	}

	public void setGeolocatedIp(GeolocatedIP geolocatedIp) {
		this.geolocatedIp = geolocatedIp;
	}

}
