package com.ceiba.parkingceiba.service;

import java.util.Date;
import java.util.List;

import com.ceiba.parkingceiba.model.entity.Parqueadero;

public interface IParqueaderoService {

	public Parqueadero registrarParqueoVehiculo(Parqueadero parqueadero);
	public Parqueadero asignarParqueaderoPorId(Parqueadero parqueadero, Date fechaSalida, int cobro);
	public Parqueadero getParqueaderoParaAsignar(String placa);
	public List<Parqueadero> encontrarTodosLosParqueaderos();
}
