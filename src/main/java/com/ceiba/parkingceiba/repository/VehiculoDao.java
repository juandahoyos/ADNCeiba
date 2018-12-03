package com.ceiba.parkingceiba.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.parkingceiba.model.entity.Vehiculo;

@Repository
public interface VehiculoDao extends CrudRepository<Vehiculo, Long>{
	
	@Query(value = "Select * From Vehiculo v Where v.placa = v.placa", nativeQuery = true)
	Vehiculo getVehiculoPorPlaca(@Param("placa") String placa);
	
	@Query(value = "SELECT placa FROM Vehiculo V WHERE V.placa = v.placa", nativeQuery = true)
	boolean existePlaca(@Param("placa") String placa);
}