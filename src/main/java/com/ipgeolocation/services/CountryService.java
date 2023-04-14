package com.ipgeolocation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.Currency;
import com.ipgeolocation.repositories.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public void getCountries() {
		 for (Country country : this.countryRepository.findAll()) {
		        System.out.println(country.getName());
		  }
	}
	
	public void saveCountry(Country country) {
       this.countryRepository.save(country);
       System.out.println("Country saved OK");
	}
	
}
