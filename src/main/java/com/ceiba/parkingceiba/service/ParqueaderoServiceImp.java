package com.ceiba.parkingceiba.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.repository.ParqueaderoDao;

@Service
public class ParqueaderoServiceImp implements IParqueaderoService{

	@Autowired
    public ParqueaderoDao parqueaderoDao;
	
	@Override
	@Transactional
	public Parqueadero registrarParqueoVehiculo(Parqueadero parqueadero) {
		return parqueaderoDao.save(parqueadero);
	}

	@Override
	public Parqueadero getParqueaderoParaAsignar(String placa) {
		return parqueaderoDao.encontrarVehiculoEnParqueaderoPorPlaca(placa);
	}

	@Override
	public List<Parqueadero> encontrarTodosLosParqueaderos() {
		return parqueaderoDao.findByEstado(true);
	}

	@Override
	@Transactional
	public Parqueadero asignarParqueaderoPorId(Parqueadero parqueadero, Date fechaSalida, int cobro) {
		
		parqueadero.setFechaSalida(fechaSalida);
		parqueadero.setCobro(cobro);
		parqueadero.setEstado(false);
		
		registrarParqueoVehiculo(parqueadero);
		
		return parqueadero;
	}
}
