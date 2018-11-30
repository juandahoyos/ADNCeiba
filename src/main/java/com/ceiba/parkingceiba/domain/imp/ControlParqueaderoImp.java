package com.ceiba.parkingceiba.domain.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parkingceiba.domain.IControlParqueadero;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.repository.ParqueaderoDao;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;
import com.ceiba.parkingceiba.util.ParametrosGlobalesParqueadero;

@Service
public class ControlParqueaderoImp implements IControlParqueadero {
	
	@Autowired
	public ParqueaderoDao parqueaderoDao;
	
	@Override
	public boolean validaPlacaIniciaPorLetraA(String placa) {
		return placa.startsWith(ParametrosGlobalesParqueadero.RESTIRCCION_PLACA_POR_LETRA_INICIAL);
	}

	@Override
	public boolean validaEspacioPorTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
		return false;
	}

	@Override
	public boolean validaVehiculoEstacionado(String placa) {
		return false;
	}

	@Override
	public boolean validaSiPaqueaderoEstaVacio() {
		return false;
	}

	@Override
	public int generarCobro(EnumTipoVehiculo tipoVehiculo, Date fechaIngreso, Date fechaSalida, int cilindraje) {
		
		return 0;
	}

	@Override
	public int calcularCobro(Date fechaIngreso, Date fechaSalida, int valorDia, int valorhora) {
		
		return 0;
	}

}
