package com.ceiba.parkingceiba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.parkingceiba.model.entity.Vehiculo;

@Repository
public interface VehiculoDao extends CrudRepository<Vehiculo, Long>{
	
}