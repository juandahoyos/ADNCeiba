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
	@Column(name = "id_vehiculo", unique = true, nullable = false)
	private int idVehiculo;

	@Column(name = "Placa")
	private String placa;

	@Column(name = "Cilindraje")
	private int cilindraje;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_vehiculo", nullable = false)
	private EnumTipoVehiculo tipoVehiculo;

	public Vehiculo() {
		super();
	}

	public Vehiculo(String placa, int cilindraje, EnumTipoVehiculo tipoVehiculo) {
		super();
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
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

	public EnumTipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setEnumTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}
