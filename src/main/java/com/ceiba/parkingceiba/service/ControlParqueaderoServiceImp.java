package com.ceiba.parkingceiba.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ceiba.parkingceiba.domain.IControlParqueadero;
import com.ceiba.parkingceiba.domain.IRestriccionPlaca;
import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;
import com.ceiba.parkingceiba.mensajes.CatalogoMensajes;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.repository.ParqueaderoDao;
import com.ceiba.parkingceiba.repository.VehiculoDao;

@Service
public class ControlParqueaderoServiceImp implements IControlParqueaderoService{

	@Autowired
	public IControlParqueadero controlParqueadero;
	
	@Autowired
	public IRestriccionPlaca restriccionPlaca;
	
	@Autowired
	public IParqueaderoService parqueaderoService;
	
	@Autowired
	public IVehiculoService vehiculoService;
	
	@Autowired
	public VehiculoDao vehiculoDao;
	
	@Autowired
	public ParqueaderoDao parqueaderoDao;
	
	public ControlParqueaderoServiceImp(IControlParqueadero controlParqueadero, IRestriccionPlaca restriccionPlaca,
			IParqueaderoService parqueaderoService, IVehiculoService vehiculoService, VehiculoDao vehiculoDao, ParqueaderoDao parqueaderoDao) {
		super();
		this.controlParqueadero = controlParqueadero;
		this.restriccionPlaca = restriccionPlaca;
		this.parqueaderoService = parqueaderoService;
		this.vehiculoService = vehiculoService;
		this.vehiculoDao = vehiculoDao;
		this.parqueaderoDao = parqueaderoDao;
	}

	@Override
	public Parqueadero registroVehiculo(Vehiculo vehiculo) throws ParqueaderoErrorBuilderException{
		if(!restriccionPlaca.validadSiEsDomingoOLunes() && controlParqueadero.validarPlacaIniciaPorLetraA(vehiculo.getPlaca())){
			throw new ParqueaderoErrorBuilderException(CatalogoMensajes.PLACA_INVALIDA_PARA_INGRESO, HttpStatus.NOT_ACCEPTABLE);
		}
		if(!controlParqueadero.buscarEspacioPorTipoVehiculo(vehiculo.getTipoVehiculo())) {
			throw new ParqueaderoErrorBuilderException(CatalogoMensajes.NO_HAY_ESPACIO_PARA_EL_TIPO_DE_VEHICULO, HttpStatus.NOT_ACCEPTABLE);
		}
		if(controlParqueadero.buscarVehiculoEstacionado(vehiculo.getPlaca())){
			throw new ParqueaderoErrorBuilderException(CatalogoMensajes.VEHICULO_YA_SE_ENCUENTRA_ESTACIONADO, HttpStatus.NOT_ACCEPTABLE);
		}
		
		vehiculo = vehiculoService.getVehiculoAParquear(vehiculo);
		Parqueadero parqueadero = new Parqueadero(new Date(),null,true,0,vehiculo);
		
		return parqueaderoService.registrarParqueoVehiculo(parqueadero);
	}

	@Override
	public Parqueadero salidaVehiculo(String placa) throws ParqueaderoErrorBuilderException {
		Date fechaSalida;
		int cobro;
		Parqueadero parqueadero;
		if(!controlParqueadero.buscarVehiculoEstacionado(placa)) {
		throw new ParqueaderoErrorBuilderException(CatalogoMensajes.VEHICULO_NO_ESTA_ESTACIONADO, HttpStatus.NOT_ACCEPTABLE);
		}
		fechaSalida = Calendar.getInstance().getTime();
		parqueadero = parqueaderoService.getParqueaderoParaAsignar(placa);
		cobro = controlParqueadero.generarCobro(parqueadero.getVehiculo().getTipoVehiculo(), parqueadero.getFechaIngreso(), fechaSalida, parqueadero.getVehiculo().getCilindraje());
		
		return parqueaderoService.asignarParqueaderoPorId(parqueadero, fechaSalida, cobro);
	}

	@Override
	public List<Parqueadero> consultarTodosLosVehiculos() throws ParqueaderoErrorBuilderException {
		if(controlParqueadero.validarSiPaqueaderoEstaVacio()) {
			throw new ParqueaderoErrorBuilderException(CatalogoMensajes.PARQUEADERO_ESTA_VACIO, HttpStatus.NOT_ACCEPTABLE);
		}
		return parqueaderoService.encontrarTodosLosParqueaderos();
	}
}