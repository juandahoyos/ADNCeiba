package com.ceiba.parkingceiba.model.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ceiba.parkingceiba.model.entity.Vehiculo;

public interface VehiculoDao extends CrudRepository<Vehiculo, Long>{
	
	//Buscar Vehiculo Por Placa
	@Query("Select V From Vehiculo V Where placa = :placa")
	Vehiculo getVehiculoPorPlaca(@Param("placa") String placa);
	
	//Buscar si vehiculo ya existe con esa placa
	@Query("Select Case When Count(V)> 0 Then true Else False End From Vehiculo Where V.placa = :placa")
	Boolean placaExiste(@Param("placa") String placa);
}