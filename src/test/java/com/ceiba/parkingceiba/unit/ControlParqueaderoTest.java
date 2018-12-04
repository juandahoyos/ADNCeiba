package com.ceiba.parkingceiba.unit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.parkingceiba.domain.IControlParqueadero;
public class ControlParqueaderoTest {

	private static final String PLACA_INICIA_CON_A = "ASD451";
	private static final String PLACA_NO_INICIA_CON_A = "BUY987";
	
	@Autowired
	private IControlParqueadero controlParqueadero;
	
	@Test
	public void validaPlacaIniciaConLetraATest() {
	//Arrange
		
	//Act
		
	//Assert
	}
	
	@Test
	public void validaPlacaNoIniciaConLetraATest() {
	//Arrange
		
	//Act
		
	//Assert

	}
}
