package com.ceiba.parkingceiba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.parkingceiba.dto.VehiculoDTO;
import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.repository.VehiculoDao;

@Service
public class VehiculoServiceImp implements IVehiculoService{
	
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@Override
	@Transactional
	public Vehiculo getVehiculoParqueado(String placa) {
		/*if(!vehiculoDao.placaExiste(vehiculoDTO.getPlaca())) {
			vehiculoDTO = vehiculoDao.save(vehiculoDTO);
		}else {
			vehiculoDTO = vehiculoDao.getVehiculoPorPlaca(vehiculoDTO.getPlaca());
		}*/
		return null;
	}
		
}
