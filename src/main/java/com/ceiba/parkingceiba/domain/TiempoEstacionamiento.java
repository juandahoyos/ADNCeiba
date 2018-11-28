package com.ceiba.parkingceiba.domain;

import java.util.Date;

public interface TiempoEstacionamiento {

	public int getObtenerMinutosDeParqueo(Date fechaIngreso, Date fechaSalida);

	public TiempoEstacionamiento ajusteTiempoParqueo(int minutosEnParqueadero);
}
