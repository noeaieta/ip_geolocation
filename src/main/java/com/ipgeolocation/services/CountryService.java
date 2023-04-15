package com.ipgeolocation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipgeolocation.entity.Country;
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
	
	public Optional<Country> getByCode(String code) {
		return this.countryRepository.findById(code);
	}
	
	public void saveCountry(Country country) {
       this.countryRepository.save(country);
	}
	
}
