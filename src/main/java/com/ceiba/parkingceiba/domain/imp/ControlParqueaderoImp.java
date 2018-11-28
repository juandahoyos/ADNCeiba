package com.ceiba.parkingceiba.domain.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.parkingceiba.domain.ControlParqueadero;
import com.ceiba.parkingceiba.model.dao.ParqueaderoDao;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;
import com.ceiba.parkingceiba.util.ParametrosGlobalesParqueadero;

public class ControlParqueaderoImp implements ControlParqueadero {
	
	@Autowired
	public ParqueaderoDao parqueaderoDao;

	@Override
	public boolean validaPlacaIniciaPorLetraA(String placa) {
		return placa.startsWith(ParametrosGlobalesParqueadero.RESTIRCCION_PLACA_POR_LETRA_INICIAL);
	}

	@Override
	public boolean validaEspacioPorTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
		int vehiculosEnParqueadero = parqueaderoDao.buscarTodosLosVehiculosEnParqueadero(tipoVehiculo);
		int maximoVehiculosPermitidos = (tipoVehiculo == EnumTipoVehiculo.CARRO ? ParametrosGlobalesParqueadero.MAXIMO_CARROS_PERMITIDOS : ParametrosGlobalesParqueadero.MAXIMO_MOTOS_PERMITIDOS);
		
		return vehiculosEnParqueadero < maximoVehiculosPermitidos;
	}

	@Override
	public boolean validaVehiculoEstacionado(String placa) {
		return parqueaderoDao.vehiculoEstacionado(placa);
	}

	@Override
	public boolean validaSiPaqueaderoEstaVacio() {
		return parqueaderoDao.parqueaderoVacio();
	}

	@Override
	public Parqueadero getParqueaderoParaAsignar(String placa) {
		return parqueaderoDao.buscarVehiculoEstacionadoPorPlaca(placa);
	}

	@Override
	public int generarCobro(EnumTipoVehiculo enumTipoVehiculo, Date fechaIngreso, Date fechaSalida, int cilindraje) {
		
		return 0;
	}

	@Override
	public int calcularCobro(Date fechaIngreso, Date fechaSalida, int valorDia, int valorhora) {
		
		return 0;
	}

}
