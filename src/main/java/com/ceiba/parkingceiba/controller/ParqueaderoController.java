package com.ceiba.parkingceiba.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@Autowired
	private IControlParqueaderoService controlParqueaderoService;
	
	@RequestMapping("/registroEntrada")
	public ResponseEntity<Object> entradaVehiculo(@RequestBody Vehiculo vehiculo){
		try {
			controlParqueaderoService.registroVehiculo(vehiculo);		
		} catch (ParqueaderoErrorBuilderException e) {
			return new ResponseEntity<Object>(e, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
	}

	
	@Produces(MediaType.APPLICATION_JSON)
	@PutMapping(value = "/registroSalida/{placa}")
	public ResponseEntity<Object> salidaVehiculo(@PathVariable("placa") String placa) throws ParqueaderoErrorBuilderException {
		
		Parqueadero parking = controlParqueaderoService.salidaVehiculo(placa);

		return new ResponseEntity<>(parking, HttpStatus.OK);
	}
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GetMapping(value = "/buscarVehiculos")
	public ResponseEntity<Object> findAllVehicleParked() throws ParqueaderoErrorBuilderException {
		
		List<Parqueadero> parking = controlParqueaderoService.consultarTodosLosVehiculos();

		return new ResponseEntity<>(parking, HttpStatus.OK);
	}

}
