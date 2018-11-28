package com.ceiba.parkingceiba.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;

public interface ParqueaderoDao extends CrudRepository<Parqueadero, Long>{
	
	@Query("SELECT COUNT(*) FROM Parqueadero P JOIN P.Vehiculo V WHERE V.tipo_vehiculo = :tipo_vehiculo AND P.estado = true")
	int buscarTodosLosVehiculosEnParqueadero(@Param("tipo_vehiculo") EnumTipoVehiculo tipoVehiculo);
	
	@Query("SELECT CASE WHERE COUNT(p) >0 THEN true ELSE False END FROM Parqueadero p JOIN p.Vehiculo v WHERE v.placa = :placa AND p.estado = true")
	boolean vehiculoEstacionado(@Param ("placa")String placa);
	
	@Query("SELECT CASE WHERE COUNT(p) <=0 THEN true ELSE False END FROM Parqueadero p JOIN p.Vehiculo v WHERE p.estado = true")
	boolean parqueaderoVacio();
	
	@Query("SELECT P FROM Parqueadero P JOIN P.Vehiculo V WHERE V.placa = :placa AND P.estado = true")
	Parqueadero buscarVehiculoEstacionadoPorPlaca(@Param("placa") String placa);
}
