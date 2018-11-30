package com.ceiba.parkingceiba.dto;

import java.io.Serializable;

import com.ceiba.parkingceiba.util.EnumTipoVehiculo;

public class VehiculoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idVehiculo;
	private String placa;
	private int cilindraje;
	private EnumTipoVehiculo tipoVehiculo;

	public VehiculoDTO() {
		super();
	}

	public VehiculoDTO(int idVehiculo, String placa, int cilindraje, EnumTipoVehiculo tipoVehiculo) {
		super();
		this.idVehiculo = idVehiculo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public EnumTipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
}
