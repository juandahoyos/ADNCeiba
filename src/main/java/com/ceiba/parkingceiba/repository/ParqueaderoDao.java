package com.ceiba.parkingceiba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.parkingceiba.model.entity.Parqueadero;

@Repository
public interface ParqueaderoDao extends CrudRepository<Parqueadero, Long>{
	
}
