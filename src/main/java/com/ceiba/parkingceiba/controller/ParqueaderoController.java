package com.ceiba.parkingceiba.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.service.IControlParqueaderoService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/parqueadero")
public class ParqueaderoController {

	private static final Logger LOGGER = Logger.getLogger("loggerController");

	@Autowired
	private IControlParqueaderoService controlParqueaderoService;

	public ParqueaderoController(IControlParqueaderoService controlParqueaderoService) {
		this.controlParqueaderoService = controlParqueaderoService;
	}

	@RequestMapping(value = "/registroEntrada")
	public ResponseEntity<Object> entradaVehiculo(@RequestBody Vehiculo vehiculo) {
		Parqueadero parqueadero;
		try {
			parqueadero = controlParqueaderoService.registroVehiculo(vehiculo);
		} catch (ParqueaderoErrorBuilderException e) {
			LOGGER.log(Level.INFO, e.getMensaje(),e);
			return new ResponseEntity<>(e.getMensaje(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(parqueadero, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/registroSalida")
	public ResponseEntity<Object> salidaVehiculo(@RequestBody String placa) {

		try {
			Parqueadero parqueadero = controlParqueaderoService.salidaVehiculo(placa);
			return new ResponseEntity<>(parqueadero, HttpStatus.OK);
		} catch (ParqueaderoErrorBuilderException e) {
			LOGGER.info(e.getMensaje());
			return new ResponseEntity<>(e.getMensaje(), HttpStatus.NOT_ACCEPTABLE);

		}
	}

	@GetMapping(value = "/buscarVehiculos")
	public ResponseEntity<Object> buscarTodosLosVehiculos() {
		List<Parqueadero> parqueadero;
		try {
			parqueadero = controlParqueaderoService.consultarTodosLosVehiculos();
		} catch (ParqueaderoErrorBuilderException e) {
			LOGGER.info(e.getMensaje());
			return new ResponseEntity<>(e.getMensaje(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(parqueadero, HttpStatus.OK);
	}
}