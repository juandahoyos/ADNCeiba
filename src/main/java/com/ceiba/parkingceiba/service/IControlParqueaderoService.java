package com.ceiba.parkingceiba.service;

import java.util.List;

import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.model.entity.Vehiculo;

public interface IControlParqueaderoService {

	public Parqueadero registroVehiculo(Vehiculo vehiculo) throws ParqueaderoErrorBuilderException;
	public Parqueadero salidaVehiculo(String placa) throws ParqueaderoErrorBuilderException;
	public List<Parqueadero> consultarTodosLosVehiculos() throws ParqueaderoErrorBuilderException;
}
