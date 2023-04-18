package com.ipgeolocation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipgeolocation.entity.Distance;
import com.ipgeolocation.entity.GeolocatedIP;
import com.ipgeolocation.repositories.DistanceRepository;

@Service
public class DistanceService {
	
	private static final double RADIO_EARTH_KM = 6371.0;

	@Autowired
	private DistanceRepository distanceRepository;
	
	public Iterable<Distance> getDistances() {
		 return this.distanceRepository.findAll();
	}
	
	public Optional<Distance> getDistanceByIP(GeolocatedIP geolocatedIP) {
		 return this.distanceRepository.findById(geolocatedIP.getCountry().getName());
	}

	public Distance saveDistance(Distance distance) {
        return this.distanceRepository.save(distance);
	}
	
	/* Returns the distance between Buenos Aires and countryTo in kilometers from latitudes and longitudes
	 * References: https://donnierock.com/2015/03/16/calculando-la-distancia-entre-doos-coordenadas-en-java/ */
	public double getDistanceTo(Double latitudeFrom, Double longitudeFrom, Double latitudeTo, Double longitudeTo) {
		double latitudes = Math.toRadians(latitudeTo - latitudeFrom);
		double longitudes = Math.toRadians(longitudeTo - longitudeFrom);

		double sinLatitudes = Math.sin(latitudes / 2);
		double sinLongitudes = Math.sin(longitudes / 2);

		double calculationFirst = Math.pow(sinLatitudes, 2) + Math.pow(sinLongitudes, 2)
				* Math.cos(Math.toRadians(latitudeFrom)) * Math.cos(Math.toRadians(latitudeTo));
		double calculationSecond = 2 * Math.atan2(Math.sqrt(calculationFirst), Math.sqrt(1 - calculationFirst));

		double distance = RADIO_EARTH_KM * calculationSecond;

		return distance;

	}

}
