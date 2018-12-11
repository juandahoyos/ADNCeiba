package com.ceiba.parkingceiba.domain;

import java.util.Date;
import java.util.List;

import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;

public interface IControlParqueadero {

	public boolean validarPlacaIniciaPorLetraA(String placa);
	public boolean buscarEspacioPorTipoVehiculo(EnumTipoVehiculo tipoVehiculo);
	public boolean buscarVehiculoEstacionado(String placa);
	public boolean validarSiPaqueaderoEstaVacio();
	
	public Parqueadero getObtenerParqueaderoParaAsignar(String placa);
	public int generarCobro (EnumTipoVehiculo tipoVehiculo, Date fechaIngreso, Date fechaSalida, int cilindraje);
	public int calcularCobro(Date fechaIngreso, Date fechaSalida, int valorDia, int valorHora);
}
