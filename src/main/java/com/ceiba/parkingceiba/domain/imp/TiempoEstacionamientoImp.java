package com.ceiba.parkingceiba.domain.imp;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ceiba.parkingceiba.domain.ITiempoEstacionamiento;
import com.ceiba.parkingceiba.util.ParametrosGlobalesParqueadero;

@Component
public class TiempoEstacionamientoImp implements ITiempoEstacionamiento{

	@Override
	public int getObtenerMinutosDeParqueo(Date fechaIngreso) {
		Date fechaSalida = Calendar.getInstance().getTime();
		return (int) ((fechaSalida.getTime() - fechaIngreso.getTime()) / ParametrosGlobalesParqueadero.MILI_SEGUNDOS);
	}
}
