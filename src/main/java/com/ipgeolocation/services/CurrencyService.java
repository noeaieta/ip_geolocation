package com.ipgeolocation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipgeolocation.entity.Currency;
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
	
	public void saveCurrency(Currency currency) {
        this.currencyRepository.save(currency);
        System.out.println("Currency saved OK");
	}

}
