package com.ceiba.parkingceiba.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ceiba.parkingceiba.util.EnumTipoVehiculo;

@Entity
@Table(name = "Vehiculo")
public class Vehiculo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdVehiculo", unique = true, nullable = false)
	private int idVehiculo;

	@Column(name = "Placa")
	private String placa;

	@Column(name = "Cilindraje")
	private int cilindraje;

	@Enumerated(EnumType.STRING)
	@Column(name = "TipoVehiculo", nullable = false)
	private EnumTipoVehiculo enumTipoVehiculo;

	public Vehiculo() {
		super();
	}

	public Vehiculo(String placa, int cilindraje, EnumTipoVehiculo enumTipoVehiculo) {
		super();
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.enumTipoVehiculo = enumTipoVehiculo;
	}

	public int getIdVehiculo() {
		return idVehiculo;
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

	public EnumTipoVehiculo getEnumTipoVehiculo() {
		return enumTipoVehiculo;
	}

	public void setEnumTipoVehiculo(EnumTipoVehiculo enumTipoVehiculo) {
		this.enumTipoVehiculo = enumTipoVehiculo;
	}

}
