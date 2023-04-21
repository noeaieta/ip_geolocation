package com.ipgeolocation.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipgeolocation.entity.Distance;

@Repository
public interface DistanceRepository extends CrudRepository<Distance, String>{

}

