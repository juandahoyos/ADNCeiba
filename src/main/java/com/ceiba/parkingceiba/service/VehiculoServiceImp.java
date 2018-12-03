package com.ceiba.parkingceiba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.repository.VehiculoDao;

@Service
public class VehiculoServiceImp implements IVehiculoService {

	@Autowired
	private VehiculoDao vehiculoDao;

	@Override
	@Transactional
	public Vehiculo getVehiculoAParquear(Vehiculo vehiculo) {
		if (!vehiculoDao.existsByPlaca(vehiculo.getPlaca())) {
			vehiculo = vehiculoDao.save(vehiculo);
		} else {
			vehiculo = vehiculoDao.findByPlaca(vehiculo.getPlaca());
		}
		return vehiculo;
	}
}
