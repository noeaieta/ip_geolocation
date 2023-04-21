package com.ipgeolocation.utils;



import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ipgeolocation.services.DistanceService;

public class DistanceServiceTest {
	
	 	private DistanceService distanceService;
	    @BeforeEach
	    public void init() {
	    	distanceService = new DistanceService();
	    }
	

	    @Test
	    public void getDistanceToTest() {
	    	assertEquals(1226.7412989978268, distanceService.getDistanceTo(23.4,45.1,22.2,33.2), 0.001);
	    }

}
