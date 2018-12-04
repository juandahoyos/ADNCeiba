package com.ceiba.parkingceiba.testdatabuilder;

import java.util.Date;

import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.model.entity.Vehiculo;

public class ParqueaderoTestDataBuilder {

	public static final Date FECHA_INGRESO = new Date();
	public static final Date FECHA_SALIDA= new Date();
	public static final boolean ESTADO = true;
	public static final int COBRO = 0;
	public static final Vehiculo VEHICULO = new Vehiculo();
	
	public Date fechaIngreso;
	public Date fechaSalida;
	public boolean estado;
	public int cobro;
	public Vehiculo vehiculo;
	
	public ParqueaderoTestDataBuilder() {
		super();
		this.fechaIngreso = FECHA_INGRESO;
		this.fechaSalida = FECHA_SALIDA;
		this.estado = ESTADO;
		this.cobro = COBRO;
		this.vehiculo = VEHICULO;
	}
	
	public ParqueaderoTestDataBuilder conFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public ParqueaderoTestDataBuilder conFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public ParqueaderoTestDataBuilder conEstado(boolean estado) {
		this.estado = estado;
		return this;
	}
	
	public ParqueaderoTestDataBuilder conCobro(int cobro) {
		this.cobro = cobro;
		return this;
	}
	
	public ParqueaderoTestDataBuilder conVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}
	
	public Parqueadero build() {
		return new Parqueadero(this.fechaIngreso, this.fechaSalida, this.estado, this.cobro, this.vehiculo);
	}
}
