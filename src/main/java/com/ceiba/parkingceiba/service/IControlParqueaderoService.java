package com.ceiba.parkingceiba.service;

import java.util.List;

import com.ceiba.parkingceiba.dto.VehiculoDTO;
import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;
import com.ceiba.parkingceiba.model.entity.Parqueadero;

public interface IControlParqueaderoService {

	public void registroVehiculo(VehiculoDTO vehiculoDTO) throws ParqueaderoErrorBuilderException;
	public Parqueadero salidaVehiculo(String placa) throws ParqueaderoErrorBuilderException;
	public List<Parqueadero> buscarVehiculos() throws ParqueaderoErrorBuilderException;
}
