package com.ceiba.parkingceiba.testdatabuilder;

import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;

public class VehiculoTestDataBuilder {

	public static final String PLACA = "";
	public static final int CILINDRAJE = 0;
	public static final EnumTipoVehiculo TIPOVEHICULO = EnumTipoVehiculo.CARRO;
	
	private String placa;
	private int cilindraje;
	private EnumTipoVehiculo tipoVehiculo;

	
	public VehiculoTestDataBuilder() {
		super();
		this.placa = PLACA;
		this.cilindraje = CILINDRAJE;
		this.tipoVehiculo = TIPOVEHICULO;
	}
	
	public VehiculoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public VehiculoTestDataBuilder conTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public Vehiculo build() {
		return new Vehiculo(this.placa, this.cilindraje, this.tipoVehiculo);
	} 
}
