package com.ipgeolocation.repositories;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ipgeolocation.entity.GeolocatedIP;

public interface GeolocatedIPRepository extends CrudRepository<GeolocatedIP, String>{
	 
	  @Transactional
	  @Query(value = """
		      SELECT COUNT(*) FROM geolocatedip WHERE country=(:code);
		      """, nativeQuery = true)
	  public Integer findCount(String code);

	  @Transactional
	  @Query(value = """
		      SELECT country, COUNT(*) FROM public.geolocatedip GROUP BY country
		      """, nativeQuery = true)
	  public List<Map<String, Integer>> getInvocationsPerCountry();
	  
}
