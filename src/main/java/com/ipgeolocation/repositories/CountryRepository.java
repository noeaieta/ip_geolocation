package com.ipgeolocation.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ipgeolocation.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Integer>{

}
