package com.ceiba.parkingceiba.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Parqueadero")
public class Parqueadero implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdParqueadero", unique = true, nullable = false)
	private int idParqueadero;

	@Column(name = "fechaIngreso", nullable = false)
	private Date fechaIngreso;
	
	@Column(name = "fechaSalida", nullable = false)
	private Date fechaSalida;
	
	@Column(name = "estado", nullable = false)
	private boolean estado;
	
	@Column(name = "cobro", nullable = false)
	private int cobro;
	
	@ManyToOne
	@JoinColumn(name = "IdVehiculo", nullable = false, referencedColumnName = "IdVehiculo")
	private Vehiculo vehiculo;

	public Parqueadero() {
		super();
	}



	public Parqueadero(Date fechaIngreso, Date fechaSalida, boolean estado, int cobro, Vehiculo vehiculo) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.estado = estado;
		this.cobro = cobro;
		this.vehiculo = vehiculo;
	}

	public int getIdParqueadero() {
		return idParqueadero;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getCobro() {
		return cobro;
	}

	public void setCobro(int cobro) {
		this.cobro = cobro;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
}
