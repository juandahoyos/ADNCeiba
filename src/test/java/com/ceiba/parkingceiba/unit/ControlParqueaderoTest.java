package com.ceiba.parkingceiba.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parkingceiba.domain.IControlParqueadero;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.repository.ParqueaderoDao;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;
import com.ceiba.parkingceiba.util.ParametrosGlobalesParqueadero;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ControlParqueaderoTest {
	
	@org.junit.Before
	public void inicializarMocks() {
		MockitoAnnotations.initMocks(this);
	}

	private static final String PLACA_INICIA_CON_A = "ASD451";
	private static final String PLACA_NO_INICIA_CON_A = "BUY987";
	
	public boolean respuesta;
	
	@Autowired
	private IControlParqueadero controlParqueadero;
	
	@MockBean
	private ParqueaderoDao parqueaderoDao;
	
	@Test
	public void validaPlacaIniciaConLetraATest() {
	//Arrange
		
	//Act
		respuesta = controlParqueadero.validarPlacaIniciaPorLetraA(PLACA_INICIA_CON_A);
	//Assert
		assertTrue(respuesta);
	}
	
	@Test
	public void validaPlacaNoIniciaConLetraATest() {
	//Arrange
		
	//Act
		respuesta = controlParqueadero.validarPlacaIniciaPorLetraA(PLACA_NO_INICIA_CON_A);
	//Assert
		assertFalse(respuesta);
	}
	
	@Test
	public void espacioDisponibleParaCarroTest() {
	//Arrange
		Mockito.when(parqueaderoDao.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO)).thenReturn(ParametrosGlobalesParqueadero.MAXIMO_CARROS_PERMITIDOS - 2);
	//Act
		respuesta = controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO);
	//Assert
		assertTrue(respuesta);
	}
	
	@Test
	public void espacioDisponibleParaMotoTest() {
	//Arrange
		Mockito.when(parqueaderoDao.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.MOTO)).thenReturn(ParametrosGlobalesParqueadero.MAXIMO_MOTOS_PERMITIDOS - 2);
	//Act
		respuesta = controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.MOTO);
	//Assert
		assertTrue(respuesta);
	}
	
	@Test
	public void sinEspacioDisponibleParaCarroTest() {
	//Arrange
		Mockito.when(parqueaderoDao.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO)).thenReturn(ParametrosGlobalesParqueadero.MAXIMO_CARROS_PERMITIDOS + 2);
	//Act
		respuesta = controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO);
	//Assert
		assertFalse(respuesta);
	}
	
	@Test
	public void sinEspacioDisponibleParaMotoTest() {
	//Arrange
		Mockito.when(parqueaderoDao.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.MOTO)).thenReturn(ParametrosGlobalesParqueadero.MAXIMO_MOTOS_PERMITIDOS + 2);
	//Act
		respuesta = controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.MOTO);
	//Assert
		assertFalse(respuesta);
	}
	
	@Test
	public void parqueaderoVacioTest() {
		//Arrange
		Mockito.when(parqueaderoDao.paqueaderoVacio()).thenReturn(true);
		//Act
		respuesta = controlParqueadero.validarSiPaqueaderoEstaVacio();
		//Assert
		assertTrue(respuesta);
	}
}
