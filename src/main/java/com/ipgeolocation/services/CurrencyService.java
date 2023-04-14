package com.ipgeolocation.services;

import java.beans.JavaBean;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipgeolocation.repositories.Currency;
import com.ipgeolocation.repositories.CurrencyRepository;

@Service
public class CurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	public void getCurrencies() {
		 for (Currency currency : this.currencyRepository.findAll()) {
		        System.out.println(currency.getName());
		  }
	}

}
