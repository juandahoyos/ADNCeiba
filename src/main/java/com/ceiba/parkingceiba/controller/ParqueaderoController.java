package com.ceiba.parkingceiba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parkingceiba.dto.VehiculoDTO;
import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;
import com.ceiba.parkingceiba.service.IControlParqueaderoService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/parqueadero")
public class ParqueaderoController {
	
	@Autowired
	private IControlParqueaderoService controlParqueaderoService;
	
	@RequestMapping("/registroEntrada")
	public ResponseEntity<VehiculoDTO> entradaVehiculo (@RequestBody VehiculoDTO vehiculoDTO) throws ParqueaderoErrorBuilderException{
		
		controlParqueaderoService.registroVehiculo(vehiculoDTO);
		
		return new ResponseEntity<VehiculoDTO>(vehiculoDTO, HttpStatus.CREATED);
	}
	
}
