package com.ceiba.parkingceiba.domain.imp;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.ceiba.parkingceiba.domain.RestriccionPlaca;

@Component
public class RestricionPlacaImp implements RestriccionPlaca {

	public boolean validadSiEsDomingoOLunes() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) < 2;
	}
}