package com.ceiba.parkingceiba.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parkingceiba.domain.IControlParqueadero;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControlParqueaderoTest {

	private static final String PLACA_INICIA_CON_A = "ASD451";
	private static final String PLACA_NO_INICIA_CON_A = "BUY987";
	
	public boolean respuesta;
	
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
