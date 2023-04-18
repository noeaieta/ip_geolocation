package com.ipgeolocation.statistics;

import java.io.Serializable;

public class StatisticsResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String country;
	private Double distanceToBsAs;
	private Long invocations;

	public StatisticsResponse(String country, Double distanceToBsAs, Long invocations) {
		super();
		this.country = country;
		this.distanceToBsAs = distanceToBsAs;
		this.invocations = invocations;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Double getDistanceToBsAs() {
		return distanceToBsAs;
	}
	public void setDistanceToBsAs(Double distanceToBsAs) {
		this.distanceToBsAs = distanceToBsAs;
	}
	public Long getInvocations() {
		return invocations;
	}
	public void setInvocations(Long invocations) {
		this.invocations = invocations;
	}
}
