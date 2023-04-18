package com.ipgeolocation.statistics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipgeolocation.entity.Distance;
import com.ipgeolocation.services.DistanceService;

@Service
public class StatisticsService {
	
	@Autowired
	private DistanceService distanceService;
		
	public Distance getTheFurthestDistance() {
		Iterable<Distance> distances = this.distanceService.getDistances();
	    
		List<Distance> allDistances = new ArrayList<>();
		distances.forEach(allDistances::add);
		
		Distance distanceMax = null;
		Double maxDistance = 0.0;
		int posicionMax = 0;
		for (int i = 0; i < allDistances.size(); i++) {	
			if (allDistances.get(i).getToBuenosAires() > maxDistance) {	
				posicionMax = i;
				maxDistance = allDistances.get(i).getToBuenosAires();
			}
			
		}
		distanceMax =  allDistances.get(posicionMax);	
		return distanceMax;
	}
	
	public Distance getTheNearestDistance() {
		Iterable<Distance> distances = this.distanceService.getDistances();
	    
		List<Distance> allDistances = new ArrayList<>();
		distances.forEach(allDistances::add);
		
		Distance distanceMin = null;
		Double minDistance = allDistances.get(0).getToBuenosAires();
		int posicionMin = 0;
		for (int i = 0; i < allDistances.size(); i++) {	
			if (allDistances.get(i).getToBuenosAires() < minDistance) {	
				posicionMin = i;
				minDistance = allDistances.get(i).getToBuenosAires();
			}
			
		}
		distanceMin =  allDistances.get(posicionMin);	
		return distanceMin;
	}
	
	public Double averageInvocations(List<StatisticsResponse> distancesInvocations) {
		Double averageInvocations = 0.0;
		Double sumInvocations = 0.0;
		for (int i = 0; i < distancesInvocations.size(); i++) {
			sumInvocations = sumInvocations + distancesInvocations.get(i).getDistanceToBsAs();
		}
		averageInvocations = sumInvocations / distancesInvocations.size();
		return averageInvocations;
	}

}
