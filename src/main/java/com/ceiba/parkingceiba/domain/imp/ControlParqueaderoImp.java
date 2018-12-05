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
	public CronometroParqueaderoImp conometroParqueaderoImp;
	
	@Override
	public boolean validarPlacaIniciaPorLetraA(String placa) {
		return placa.startsWith("A")|| placa.startsWith("a");
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
	public int calcularCobro(Date fechaIngreso, Date fechaSalida, int valorDia, int valorHora) {
		int cobro = 0;
		TiempoParqueaderoImp tiempoParqueaderoImp = conometroParqueaderoImp.obtenerTiempoDeParqueo(conometroParqueaderoImp.getObtenerMinutosDeParqueo(fechaIngreso, fechaSalida));
		cobro = (tiempoParqueaderoImp.getDias() * valorDia) + (tiempoParqueaderoImp.getHoras() * valorHora);
		return cobro;
	}
	
	@Override
	public int generarCobro(EnumTipoVehiculo tipoVehiculo, Date fechaIngreso, Date fechaSalida, int cilindraje) {
		int cobro = 0;
		
		if(tipoVehiculo.equals(EnumTipoVehiculo.MOTO)) {
			cobro = calcularCobro(fechaIngreso, fechaSalida, ParametrosGlobalesParqueadero.VALOR_DIA_MOTO, ParametrosGlobalesParqueadero.VALOR_HORA_MOTO);
			cobro += cilindraje > ParametrosGlobalesParqueadero.VALOR_MOTOS_CILINDRAJE_550CC ? ParametrosGlobalesParqueadero.VALOR_RECARGO_MOTOS_CILINDRAJE_MAYOR_550CC :0;
		}else if(tipoVehiculo.equals(EnumTipoVehiculo.CARRO)){
			cobro = calcularCobro(fechaIngreso, fechaSalida, ParametrosGlobalesParqueadero.VALOR_DIA_CARRO, ParametrosGlobalesParqueadero.VALOR_HORA_CARRO);
		}
		return cobro;
	}
}
