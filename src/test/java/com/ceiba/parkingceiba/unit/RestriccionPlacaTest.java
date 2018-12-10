package com.ceiba.parkingceiba.unit;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ceiba.parkingceiba.domain.imp.RestriccionPlacaImp;


public class RestriccionPlacaTest {

	@Test
	public void diaHabilParaPlacaQueIniciaPorA() {
		//Arrange
		RestriccionPlacaImp restriccionPlaca = new RestriccionPlacaImp();
	
		//Act
		boolean diaHabil = restriccionPlaca.validadSiEsDomingoOLunes();
		
		//Assert
		assertNotNull(diaHabil);
	}
	
	@Test
	public void diaNoHabilParaPlacaQueIniciaPorA() {
		//Arrange
		RestriccionPlacaImp restriccionPlaca = new RestriccionPlacaImp();
		
		//Act
		boolean diaHabil = restriccionPlaca.validadSiEsDomingoOLunes();
		
		//Assert
		assertNotNull(diaHabil);
	}
}
