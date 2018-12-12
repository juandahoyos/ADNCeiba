package com.ceiba.parkingceiba.domain.imp;

public class TiempoParqueaderoImp {

	private int dias;
	private int horas;

	public TiempoParqueaderoImp(int dias, int horas) {
		super();
		this.dias = dias;
		this.horas = horas;
	}

	public int getDias() {
		return dias;
	}

	public int getHoras() {
		return horas;
	}
}
