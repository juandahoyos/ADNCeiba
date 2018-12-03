package com.ceiba.parkingceiba.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parkingceiba.dto.VehiculoDTO;
import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
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

	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/buscarVehiculos", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllVehicleParked() throws ParqueaderoErrorBuilderException {
		
		List<Parqueadero> parking = controlParqueaderoService.buscarVehiculos();

		return new ResponseEntity<>(parking, HttpStatus.OK);
	}

}
