package com.ipgeolocation.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ipgeolocation.entity.GeolocatedIP;

public interface GeolocatedIPRepository extends CrudRepository<GeolocatedIP, String>{

}
