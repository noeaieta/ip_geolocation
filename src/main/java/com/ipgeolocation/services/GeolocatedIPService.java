package com.ipgeolocation.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ipgeolocation.statistics.StatisticsResponse;

@Service
public class GeolocatedIPService {

	private static final double LATITUDE_BS_AS = -34.6142;
	private static final double LONGITUDE_BS_AS = -58.3811;
	
	private static Logger logger = LogManager.getLogger(GeolocatedIPService.class);
	
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
	
	public Iterable<GeolocatedIP> getGeolocatedIps() {
		 return this.geolocatedIPRepository.findAll();
	}
	
	public Optional<GeolocatedIP> getByIP(String ip) {
		return this.geolocatedIPRepository.findById(ip);
	}
	
	public void saveGeolocatedIp(GeolocatedIP geolocatedIP) {
        this.geolocatedIPRepository.save(geolocatedIP);
        logger.info("GeolocatedIP saved OK");
	}
	
	public void getCallsByCountry(Country country) {
		this.geolocatedIPRepository.findById(country.getCodeISO());
		
	}
	
	public List<StatisticsResponse> getInvocationsPerCountry() {
		return this.geolocatedIPRepository.getInvocationsPerCountry();	
	}
	
	 
	public void calculateAndSaveDistance(GeolocatedIP geolocatedIP) {
		Double distance = this.distanceService.getDistanceTo(LATITUDE_BS_AS, LONGITUDE_BS_AS, geolocatedIP.getCountry().getLatitude(), geolocatedIP.getCountry().getLongitude());
		
		this.distanceService.saveDistance(new Distance(geolocatedIP.getCountry(), distance));
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
			country = new Country();
			country.setCodeISO(responseIPAPI.getCountryCode());
			country.setName(responseIPAPI.getCountryName());
			country.setLanguages(responseIPAPI.getLanguages());
			country.setLatitude(responseIPAPI.getLatitude());
			country.setLongitude(responseIPAPI.getLongitude());
			country.setCurrency(new Currency(currencyCode, currencyName));
			country.setTimezones(restCountriesResponse.getTimezones());
			
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
