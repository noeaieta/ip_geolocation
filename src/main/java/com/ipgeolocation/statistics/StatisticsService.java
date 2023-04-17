package com.ipgeolocation.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipgeolocation.entity.Distance;
import com.ipgeolocation.services.DistanceService;

@Service
public class StatisticsService {
	
	@Autowired
	private DistanceService distanceService;
	
	/* Returns the farthest and nearest distances from Bs As in a list, 
	 * The first element is the nearest, and the second is the farthest */
	public List<Distance> getTheFarthestAndNearestDistance() {		
		Iterable<Distance> distances = this.distanceService.getDistances();
		    
		List<Distance> allDistances = new ArrayList<>();
		distances.forEach(allDistances::add);
		
		double maxDistance = 0.0 , minDistance = 0.0;
		int positionMin = 0, positionMax = 0;
		for (int i = 0; i < allDistances.size(); i++) {	
			if (allDistances.get(i).getToBuenosAires() > maxDistance) {
				maxDistance = allDistances.get(i).getToBuenosAires();
				positionMax = i;
			}
			if (allDistances.get(i).getToBuenosAires() < minDistance) {
				minDistance = allDistances.get(i).getToBuenosAires();
				positionMin = i;
			}
		}
		List<Distance> farthestAndNearestDistances = new ArrayList<>();
		farthestAndNearestDistances.add(allDistances.get(positionMin));
		farthestAndNearestDistances.add(allDistances.get(positionMax));
		
		return farthestAndNearestDistances;
	}

}
