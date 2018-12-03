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
	
	@Autowired
	public CronometroParqueaderoImp controlParqueaderoImp;
	
	@Override
	public boolean validarPlacaIniciaPorLetraA(String placa) {
		return placa.startsWith(ParametrosGlobalesParqueadero.RESTRICCION_PLACA_POR_LETRA_INICIAL);
	}

	@Override
	public boolean buscarEspacioPorTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
		int vehiculosEnParqueadero = parqueaderoDao.buscarEspacioPorTipoVehiculo(tipoVehiculo);
		int maximoVehiculosEnParqueadero = (tipoVehiculo == EnumTipoVehiculo.CARRO ? ParametrosGlobalesParqueadero.MAXIMO_CARROS_PERMITIDOS : ParametrosGlobalesParqueadero.MAXIMO_MOTOS_PERMITIDOS);
		
		return vehiculosEnParqueadero < maximoVehiculosEnParqueadero;
	}

	@Override
	public boolean validarSiPaqueaderoEstaVacio() {
		return parqueaderoDao.paqueaderoVacio();
	}

	@Override
	public boolean buscarVehiculoEstacionado(String placa) {
		return parqueaderoDao.buscarVehiculoYaEstacionado(placa);
	}
	
	@Override
	public Parqueadero getObtenerParqueaderoParaAsignar(String placa) {
		return parqueaderoDao.encontrarVehiculoEnParqueadero(placa);
	}
	
	@Override
	public int generarCobro(EnumTipoVehiculo tipoVehiculo, Date fechaIngreso, Date fechaSalida, int cilindraje) {
		
		return 0;
	}

	@Override
	public int calcularCobro(Date fechaIngreso, Date fechaSalida, int valorDia, int valorHora) {
		int cobro = 0;
		TiempoParqueaderoImp tiempoParqueaderoImp = controlParqueaderoImp.obtenerTiempoDeParqueo(controlParqueaderoImp.getObtenerMinutosDeParqueo(fechaIngreso, fechaSalida));
		cobro = (tiempoParqueaderoImp.getDias() * valorDia) + (tiempoParqueaderoImp.getHoras() * valorHora);
		return cobro;
	}

}
