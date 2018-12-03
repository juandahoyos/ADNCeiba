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
	
	@Query(value = "SELECT Count(*) FROM parqueadero p Inner JOIN vehiculo v On p.id_vehiculo = v.id_vehiculo "
			+ "WHERE v.tipo_vehiculo = v.tipo_vehiculo AND p.estado = true", nativeQuery = true)
	 int buscarEspacioPorTipoVehiculo(@Param("tipoVehiculo") EnumTipoVehiculo tipoVehiculo);
	
	@Query(value = "SELECT case when count(*)>0 then 'true' else 'false' end As x FROM parqueadero p Inner Join Vehiculo v On p.id_vehiculo = v.id_vehiculo " 
			+ "WHERE v.placa = :placa AND p.estado = true", nativeQuery = true)
	 boolean buscarVehiculoYaEstacionado(@Param("placa") String placa);
	
	@Query(value = "SELECT Count(*) <=0 FROM parqueadero p Inner JOIN vehiculo v On p.id_vehiculo=v.id_vehiculo" 
			+ "WHERE p.estado = true", nativeQuery = true)
	 boolean paqueaderoVacio();
	
	@Query(value = "SELECT * FROM parqueadero P Inner JOIN vehiculo V On p.id_vehiculo = v.id_vehiculo "
			+ "WHERE V.placa = v.placa AND P.estado = true", nativeQuery = true)
	 Parqueadero encontrarVehiculoEnParqueadero(@Param("placa") String placa);
}
