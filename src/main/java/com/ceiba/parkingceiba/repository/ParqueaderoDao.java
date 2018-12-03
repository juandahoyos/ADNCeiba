package com.ceiba.parkingceiba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;

@Repository
public interface ParqueaderoDao extends CrudRepository<Parqueadero, Long>{
	
	List <Parqueadero> findByEstado(boolean estado);
	
	@Query("SELECT Count(*) FROM Parqueadero p JOIN p.vehiculo v "
			+ "WHERE v.tipoVehiculo = :tipoVehiculo AND p.estado = true")
	 int buscarEspacioPorTipoVehiculo(@Param("tipoVehiculo") EnumTipoVehiculo tipoVehiculo);
	
	@Query("SELECT case when count(*)>0 then true else false end FROM Parqueadero p Join p.vehiculo v " 
			+ "WHERE v.placa = :placa AND p.estado = true")
	 boolean buscarVehiculoYaEstacionado(@Param("placa") String placa);
	
	@Query(value = "SELECT Count(*) <=0 FROM parqueadero p Inner JOIN vehiculo v On p.id_vehiculo=v.id_vehiculo" 
			+ "WHERE p.estado = true", nativeQuery = true)
	 boolean paqueaderoVacio();
	
	@Query("FROM Parqueadero p JOIN p.vehiculo v WHERE v.placa = :placa AND p.estado = true")
	 Parqueadero encontrarVehiculoEnParqueadero(@Param("placa") String placa);
}
