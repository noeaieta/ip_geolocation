package com.ipgeolocation;



import java.io.IOException;
import java.util.ArrayList;
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
import com.ipgeolocation.entity.Currency;
import com.ipgeolocation.entity.GeolocatedIP;
import com.ipgeolocation.services.CountryService;
import com.ipgeolocation.services.CurrencyService;
import com.ipgeolocation.utils.GeolocationUtils;

@SpringBootApplication
public class IPGeolocationAPI {
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private CountryService countryService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(IPGeolocationAPI.class, args);
	}
	
	@Bean
	public CommandLineRunner cmd() {
		return (args) -> {
			
			String ip = "200.123.140.97";
			
			IPGeolocationAPIClient ipGeolocationClient = new IPGeolocationAPIClient(ip);
			Country country = ipGeolocationClient.callIPGeolocationAPI(); //Call API of Geolocation IP
			
			//TODO falta obtener los lenguajes y la zona horaria.
			GeolocatedIP geolocatedIP = new GeolocatedIP(ip);
		
			CurrencyAPIClient currencyAPIClient = new CurrencyAPIClient(country.getCurrency().getName());
			try {
				country.getCurrency().setPrice(currencyAPIClient.getPriceInDollars()); //Call API of Currency
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			geolocatedIP.setCountry(country);
			GeolocationUtils.showResults(geolocatedIP); //Print in console the results
			
			
			//Caso de prueba para guardar en la base Country y Currency
			//this.currencyService.getCurrencies();
			//this.currencyService.saveCurrency(new Currency("PESO URUGUAYO", 98.5));
			/*List<String> languages = new ArrayList<>();
			languages.add("CHINO");
			
			List<String> timezones = new ArrayList<>();
			timezones.add("UTC");
			String[] languages2 = new String[10] ;
			languages2[0] = "Inglés";
			languages2[1] = "Chino Mandarín";
			
			String[] timezones2 = new String[10];
			timezones2[0] = "UTC";
			Country country = new Country("China", "CHI", languages2, timezones2, new Currency("YEN", 100.87));
			
			
			this.countryService.saveCountry(country);*/
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
