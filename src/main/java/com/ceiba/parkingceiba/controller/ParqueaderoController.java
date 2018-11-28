package com.ceiba.parkingceiba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.service.ControlParqueaderoService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/parqueadero")
public class ParqueaderoController {
	
	@Autowired
	private ControlParqueaderoService controlParqueaderoService;
	
	@RequestMapping(value = "/registroEntrada", method = RequestMethod.POST)
	public ResponseEntity<Object> entradaVehiculo (@RequestBody Vehiculo vehiculo){
		
		controlParqueaderoService.registroVehiculo(vehiculo);
		
		return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
	}

}
