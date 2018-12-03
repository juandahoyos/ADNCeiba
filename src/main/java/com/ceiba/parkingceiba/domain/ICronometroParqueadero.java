package com.ceiba.parkingceiba.domain;

import java.util.Date;

import com.ceiba.parkingceiba.domain.imp.TiempoParqueaderoImp;

public interface ICronometroParqueadero {

	public int getObtenerMinutosDeParqueo(Date fechaIngreso, Date fechaSalida);
	public TiempoParqueaderoImp obtenerTiempoDeParqueo(int minutosParqueo);
}
