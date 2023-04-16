package com.ipgeolocation.services;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.ipgeolocation.clients.ipapi.IPAPIClient;
import com.ipgeolocation.clients.ipapi.IPAPIResponse;
import com.ipgeolocation.clients.restcountries.RestCountriesAPIClient;
import com.ipgeolocation.clients.restcountries.RestCountriesResponse;
import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.Currency;
import com.ipgeolocation.entity.Distance;
import com.ipgeolocation.entity.GeolocatedIP;
import com.ipgeolocation.repositories.GeolocatedIPRepository;
import com.ipgeolocation.statistics.StatisticsService;
import com.ipgeolocation.utils.GeolocationUtils;

@Service
public class GeolocatedIPService {

	@Autowired
	private GeolocatedIPRepository geolocatedIPRepository;
	
	@Autowired
	private IPAPIClient ipAPIClient;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private DistanceService distanceService;
	
	@Autowired
	private RestCountriesAPIClient restCountriesAPIClient;
	
	public void getGeolocatedIps() {
		 for (GeolocatedIP geolocatedIP : this.geolocatedIPRepository.findAll()) {
		        System.out.println(geolocatedIP.getIp());
		  }
	}
	
	public Optional<GeolocatedIP> getByIP(String ip) {
		return this.geolocatedIPRepository.findById(ip);
	}
	
	public void saveGeolocatedIp(GeolocatedIP geolocatedIP) {
        this.geolocatedIPRepository.save(geolocatedIP);
        System.out.println("GeolocatedIP saved OK");
	}
	
	public void getCallsByCountry(Country country) {
		this.geolocatedIPRepository.findById(country.getCodeISO());
		
	}
	
	public Integer getCountriesCount(String code) {
		return this.geolocatedIPRepository.findCount(code);
	}
	
	public List<Map<String, Integer>> getInvocationsPerCountry() {
		return this.geolocatedIPRepository.getInvocationsPerCountry();	
	}
	
	 
	public void calculateAndSaveDistance(GeolocatedIP geolocatedIP) {
		Double distance = this.distanceService.getDistanceTo(geolocatedIP.getCountry());
		
		this.distanceService.saveDistance(new Distance(distance, geolocatedIP));
	}
	
	public GeolocatedIP geolocateIP(String ip) throws Exception {
		Country country;
		String countryCode, currencyCode, currencyName;
		IPAPIResponse responseIPAPI;
		RestCountriesResponse restCountriesResponse;
		
		Optional<GeolocatedIP> geolocatedIPReq = this.getByIP(ip);
		if (geolocatedIPReq.isPresent()) {
			return geolocatedIPReq.get();
		}
		
		try {
			responseIPAPI = this.ipAPIClient.locateIP(ip);
			countryCode = responseIPAPI.getCountryCode();
		} catch (Exception exception){
			throw new Exception("Error while trying to call to IPAPI: " + exception.getMessage());
		}

		Optional<Country> countryReq = this.countryService.getByCode(countryCode);
		
		if (!countryReq.isPresent()) {
			try {
				restCountriesResponse = this.restCountriesAPIClient.getCountryTimeZoneAndCurrency(countryCode);	
				currencyCode = restCountriesResponse.getCurrencyCode();
				currencyName = restCountriesResponse.getCurrencyName();
			} catch (Exception exception) {
				throw new Exception("Error while trying to call RestCountryAPIClient :" + exception.getMessage());
			}
			
			//Veo si la moneda está en la base
			//Optional<Currency> currencyReq = this.currencyService.getByCode(currencyCode);
			/*if (!currencyReq.isPresent()) { //si la moneda no existe la guardo en la base de datos.
				this.currencyService.saveCurrency(currencyReq.get());
			}*/
			country = new Country();
			country.setCodeISO(responseIPAPI.getCountryCode());
			country.setName(responseIPAPI.getCountryName());
			country.setLanguages(responseIPAPI.getLanguages());
			country.setLatitude(responseIPAPI.getLatitude());
			country.setLongitude(responseIPAPI.getLongitude());
			country.setCurrency(new Currency(currencyCode, currencyName));
			country.setTimezones(restCountriesResponse.getTimezones());
			
			//Guardo los datos del nuevo país en la base
			this.countryService.saveCountry(country);
		} else {
			country = countryReq.get();
		} 
			
		GeolocatedIP geolocatedIP = new GeolocatedIP();
		geolocatedIP.setIp(ip);
		geolocatedIP.setCountry(country);
		this.saveGeolocatedIp(geolocatedIP);
		
		//Distance to Buenos Aires
		this.calculateAndSaveDistance(geolocatedIP);	
		
		return geolocatedIP;
	}
	

}
