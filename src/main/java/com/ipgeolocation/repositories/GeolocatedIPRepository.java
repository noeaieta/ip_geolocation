package com.ipgeolocation.repositories;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ipgeolocation.entity.GeolocatedIP;
import com.ipgeolocation.statistics.StatisticsResponse;

public interface GeolocatedIPRepository extends CrudRepository<GeolocatedIP, String>{
	  @Transactional
	  @Query("""
	  		SELECT new com.ipgeolocation.statistics.StatisticsResponse(c.name, d.toBuenosAires, COUNT(*)) 
			FROM GeolocatedIP gip  
			INNER JOIN Country c ON c.codeISO=gip.country
	  		INNER JOIN Distance d ON gip.country=d.country
	  		GROUP BY c.name, d.toBuenosAires 
		    """)
	  public List<StatisticsResponse> getInvocationsPerCountry();
	  
}
