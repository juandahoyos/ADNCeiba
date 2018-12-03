package com.ceiba.parkingceiba.domain;

import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;

public interface IRestriccionPlaca {

	public boolean validadSiEsDomingoOLunes() throws ParqueaderoErrorBuilderException;
}
