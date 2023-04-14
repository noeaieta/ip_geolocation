package com.ipgeolocation;



import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ipgeolocation.client.CurrencyAPIClient;
import com.ipgeolocation.client.IPGeolocationAPIClient;
import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.GeolocatedIP;
import com.ipgeolocation.repositories.Currency;
import com.ipgeolocation.services.CurrencyService;
import com.ipgeolocation.utils.GeolocationUtils;

@SpringBootApplication
public class IPGeolocationAPI {
	
	@Autowired
	private CurrencyService currencyService;
	
	public static void main(String[] args) {
		SpringApplication.run(IPGeolocationAPI.class, args);
	}
	
	@Bean
	public CommandLineRunner cmd() {
		return (args) -> {
			this.currencyService.getCurrencies();
		};
	}
	//public static void main(String[] args) {
		
		/*System.out.println("IP ingresada = "+ args[0]);
		//String ip = args[0];
		String ip = "200.123.140.97";
		
		IPGeolocationAPIClient ipGeolocationClient = new IPGeolocationAPIClient(ip);
		Country country = ipGeolocationClient.callIPGeolocationAPI(); //Call API of Geolocation IP
		
		//TODO falta obtener los lenguajes y la zona horaria.
		GeolocatedIP geolocatedIP = new GeolocatedIP(ip);
	
		CurrencyAPIClient currencyAPIClient = new CurrencyAPIClient(country.getCurrency().getName());
		try {
			country.getCurrency().setQuotation(currencyAPIClient.getQuotationToDollar()); //Call API of Currency
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		geolocatedIP.setCountry(country);
		GeolocationUtils.showResults(geolocatedIP); //Print in console the results
		
		*/
	//}
	
	
}
