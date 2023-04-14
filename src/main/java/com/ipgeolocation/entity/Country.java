package com.ipgeolocation.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringNVarcharType;




@Entity
@Table(name = "countries")
@TypeDefs({
    @TypeDef(
        name = "string-array", 
        typeClass = StringNVarcharType.class
    ),
    @TypeDef(
        name = "int-array", 
        typeClass = IntegerType.class
    )
})
public class Country {

	private static final double LATITUDE_BS_AS = -34.6142;
	private static final double LONGITUDE_BS_AS = -58.3811;
	private static final double RADIO_EARTH_KM = 6371.0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_country;
	
	@Column
	private String name;
	
	@Column(name="code_iso")
	private String codeISO;
	
	//@ElementCollection(fetch = FetchType.EAGER)
	//@Column(name = "languages", columnDefinition = "text[]")
	
	//@Transient
	//@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "languages", columnDefinition = "text[]")
	private String[] languages;
	
	//@Column(name = "strings", columnDefinition = "text[]")
	//@ElementCollection
	//@Transient
	@Column(name = "timezones", columnDefinition = "text[]")
	private String[] timezones;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_currency")
	private Currency currency;
	
	@Column
	private double latitude;
	
	@Column
	private double longitude;
	
	public Country() {
		super();
	}
	
	public Country(String name, String codeISO, String[] languages, String[] timezones, Currency currency) {
		super();
		this.name = name;
		this.codeISO = codeISO;
		this.languages = languages;
		this.timezones = timezones;
		this.currency = currency;
	}
	
	
	/* Returns the distance between Buenos Aires and countryTo in kilometers from latitudes and longitudes */
	public double getDistanceTo() {        
        double latitudes = Math.toRadians(this.getLatitude() - LATITUDE_BS_AS);  
        double longitudes = Math.toRadians(this.getLongitude() - LONGITUDE_BS_AS);  
        
        double sinLatitudes = Math.sin(latitudes / 2);  
        double sinLongitudes = Math.sin(longitudes / 2);
        
        double calculationFirst = Math.pow(sinLatitudes, 2) + Math.pow(sinLongitudes, 2)  
                * Math.cos(Math.toRadians(LATITUDE_BS_AS)) * Math.cos(Math.toRadians(this.getLatitude()));  
        double calculationSecond = 2 * Math.atan2(Math.sqrt(calculationFirst), Math.sqrt(1 - calculationFirst));
        
        double distance = RADIO_EARTH_KM * calculationSecond;  
   
        return distance;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeISO() {
		return codeISO;
	}

	public void setCodeISO(String codeISO) {
		this.codeISO = codeISO;
	}

	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages (String[] languages) {
		this.languages = languages;
	}

	public String[] getTimezones() {
		return timezones;
	}

	public void setTimezones(String[] timezones) {
		this.timezones = timezones;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}