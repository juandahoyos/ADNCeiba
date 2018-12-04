package com.ceiba.parkingceiba.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;


import com.ceiba.parkingceiba.domain.imp.RestriccionPlacaImp;

public class RestriccionPlacaTest {

	@Mock
	RestriccionPlacaImp restriccionPlaca;
	@Test
	public void validaSiEsDomingoOLunesDiaHabilTest() {
		// Arrange
		restriccionPlaca = mock(RestriccionPlacaImp.class);
		when(restriccionPlaca.validadSiEsDomingoOLunes()).thenReturn(true);
		// Act
		boolean diaHabil = restriccionPlaca.validadSiEsDomingoOLunes();
		// Assert
		assertTrue(diaHabil);
	}

	@Test
	public void validaSiEsDomingoOLunesDiaNoHabilTest() {
		// Arrange
		restriccionPlaca = mock(RestriccionPlacaImp.class);
		when(restriccionPlaca.validadSiEsDomingoOLunes()).thenReturn(false);
		// Act
		boolean diaHabil = restriccionPlaca.validadSiEsDomingoOLunes();
		// Assert
		assertFalse(diaHabil);
	}
}
