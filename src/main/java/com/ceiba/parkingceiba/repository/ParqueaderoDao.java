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
	
	@Query("SELECT case when count(p)>0 then true else false end FROM Parqueadero p Join p.vehiculo v " 
			+ "WHERE v.placa = :placa AND p.estado = true")
	 boolean buscarVehiculoYaEstacionado(@Param("placa") String placa);
	
	@Query("SELECT CASE WHEN COUNT(p) <= 0 THEN true ELSE false END FROM Parqueadero p JOIN p.vehiculo v WHERE p.estado = true")
	 boolean paqueaderoVacio();
	
	@Query("FROM Parqueadero p JOIN p.vehiculo v WHERE v.placa = :placa AND p.estado = true")
	 Parqueadero encontrarVehiculoEnParqueaderoPorPlaca(@Param("placa") String placa);

}
