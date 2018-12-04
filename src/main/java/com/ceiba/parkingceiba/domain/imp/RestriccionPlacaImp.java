package com.ceiba.parkingceiba.domain.imp;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.ceiba.parkingceiba.domain.IRestriccionPlaca;

@Component
public class RestriccionPlacaImp implements IRestriccionPlaca {

	public boolean validadSiEsDomingoOLunes() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY || 
				Calendar.getInstance().get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY;
	}
}