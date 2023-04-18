package com.ipgeolocation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipgeolocation.entity.Currency;
import com.ipgeolocation.repositories.CurrencyRepository;

@Service
public class CurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	public Iterable<Currency> getCurrencies() {
		 return this.currencyRepository.findAll();
	}
	
	public Optional<Currency> getByCode(String code) {
		return this.currencyRepository.findById(code);
	}
	
	public Currency saveCurrency(Currency currency) {
        return this.currencyRepository.save(currency);
	}

}
