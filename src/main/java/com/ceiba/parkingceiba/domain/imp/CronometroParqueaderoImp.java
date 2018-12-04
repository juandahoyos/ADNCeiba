package com.ceiba.parkingceiba.domain.imp;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ceiba.parkingceiba.domain.ICronometroParqueadero;
import com.ceiba.parkingceiba.util.ParametrosGlobalesParqueadero;

@Component
public class CronometroParqueaderoImp implements ICronometroParqueadero {

	public int getObtenerMinutosDeParqueo(Date fechaIngreso, Date fechaSalida) {
		return (int) ((fechaSalida.getTime() - fechaIngreso.getTime()) / ParametrosGlobalesParqueadero.MILI_SEGUNDOS);
	}

	public TiempoParqueaderoImp obtenerTiempoDeParqueo(int minutosParqueo) {

		int dias = (minutosParqueo / 60) / 24;
		int horas = (minutosParqueo / 60) % 24;
		int minutos = minutosParqueo % 60;

		if (horas > ParametrosGlobalesParqueadero.NUMERO_HORAS_PARA_COBRO_DIA) {
			dias++;
			horas = 0;
		}

		if (minutos > 0) {
			horas++;
		}

		return new TiempoParqueaderoImp(dias, horas);
	}
}
