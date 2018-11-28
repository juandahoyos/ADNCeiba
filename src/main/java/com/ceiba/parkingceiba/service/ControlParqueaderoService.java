package com.ceiba.parkingceiba.service;

import java.util.List;

import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.model.entity.Vehiculo;

public interface ControlParqueaderoService {

	public void registroVehiculo(Vehiculo vehiculo);
	public Parqueadero salidaVehiculo(Vehiculo vehiculo);
	public List<Parqueadero> buscarLosVehiculos();
}
