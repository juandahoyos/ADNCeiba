package com.ceiba.parkingceiba.domain;

import java.util.Date;

import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;

public interface ControlParqueadero {

	public boolean validaPlacaIniciaPorLetraA(String placa);
	public boolean validaEspacioPorTipoVehiculo(EnumTipoVehiculo enumTipoVehiculo);
	public boolean validaVehiculoEstacionado(String placa);
	public boolean validaSiPaqueaderoEstaVacio();
	
	public Parqueadero getParqueaderoParaAsignar(String placa);
	public int generarCobro (EnumTipoVehiculo enumTipoVehiculo, Date fechaIngreso, Date fechaSalida, int cilindraje);
	public int calcularCobro(Date fechaIngreso, Date fechaSalida, int valorDia, int valorhora);
}
