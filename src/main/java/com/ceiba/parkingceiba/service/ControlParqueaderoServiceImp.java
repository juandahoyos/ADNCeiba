package com.ceiba.parkingceiba.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ceiba.parkingceiba.domain.IControlParqueadero;
import com.ceiba.parkingceiba.domain.IRestriccionPlaca;
import com.ceiba.parkingceiba.dto.VehiculoDTO;
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
	public ParqueaderoDao parqueaderoDao;
	
	@Autowired
	public IVehiculoService vehiculoService;
	
	@Autowired
	public VehiculoDao vehiculoDao;

	
	@Override
	public void registroVehiculo(VehiculoDTO vehiculoDTO) throws ParqueaderoErrorBuilderException{
			Vehiculo vehiculo = new Vehiculo(vehiculoDTO.getPlaca(), vehiculoDTO.getCilindraje(), vehiculoDTO.getTipoVehiculo());
			vehiculoDao.save(vehiculo);
	}

	@Override
	public Parqueadero salidaVehiculo(String placa) throws ParqueaderoErrorBuilderException {
		if(!controlParqueadero.validaVehiculoEstacionado(placa)) {
		throw new ParqueaderoErrorBuilderException(CatalogoMensajes.VEHICULO_NO_ESTA_ESTACIONADO, HttpStatus.NOT_ACCEPTABLE);
		}
		
		Date fechaSalida;
		int cobro;
		Parqueadero parqueadero;
		
		fechaSalida = Calendar.getInstance().getTime();
		parqueadero = parqueaderoService.getParqueaderoParaAsignar(placa);
		cobro = controlParqueadero.generarCobro(parqueadero.getVehiculo().getTipoVehiculo(), parqueadero.getFechaIngreso(), parqueadero.getFechaSalida(), parqueadero.getVehiculo().getCilindraje());
		
		return parqueaderoService.asignarParqueaderoPorId(parqueadero, fechaSalida, cobro);
	}

	public List<Parqueadero> buscarVehiculos() throws ParqueaderoErrorBuilderException{
		return (List<Parqueadero>) parqueaderoDao.findAll();
 }
}