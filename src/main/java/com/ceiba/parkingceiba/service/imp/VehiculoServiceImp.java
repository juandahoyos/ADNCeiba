package com.ceiba.parkingceiba.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.parkingceiba.model.dao.VehiculoDao;
import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.service.VehiculoService;

@Service
public class VehiculoServiceImp implements VehiculoService{
	
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@Override
	@Transactional
	public Vehiculo getVehiculoParquedado(Vehiculo vehiculo) {
		if(!vehiculoDao.placaExiste(vehiculo.getPlaca())) {
			vehiculo = vehiculoDao.save(vehiculo);
		}else {
			vehiculo = vehiculoDao.getVehiculoPorPlaca(vehiculo.getPlaca());
		}
		return vehiculo;
	}

}
