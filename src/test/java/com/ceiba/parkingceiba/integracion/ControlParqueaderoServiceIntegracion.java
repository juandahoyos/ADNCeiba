package com.ceiba.parkingceiba.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parkingceiba.domain.imp.RestriccionPlacaImp;
import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;
import com.ceiba.parkingceiba.mensajes.CatalogoMensajes;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.repository.ParqueaderoDao;
import com.ceiba.parkingceiba.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class ControlParqueaderoServiceIntegracion {

	private static final int CILINDRAJE = 180;

	private static final String PLACA = "XCD123";
	private static final String PLACA_INICIA_CON_A = "AAD547";

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Autowired
	public ParqueaderoDao parqueaderoDao;

	@LocalServerPort
	private int localServerPort;

	@Before
	public void inicializarMocks() {
		parqueaderoDao.deleteAll();
	}

	@Spy
	RestriccionPlacaImp restriccion = new RestriccionPlacaImp();

	@Test
	public void ingresoVehiculoPlacaIniciaConAYDiaNoEsHabilParaIngresoTest() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA_INICIA_CON_A).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();

		Mockito.doReturn(false).when(restriccion).validadSiEsDomingoOLunes();

		// Act
		ResponseEntity<String> parqueaderoResponse = restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo, String.class);

		// Assert
		assertEquals(HttpStatus.NOT_ACCEPTABLE, parqueaderoResponse.getStatusCode());
	}

	@Test
	public void registrarIngresoVehiculoTest() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();

		// Act
		ResponseEntity<Parqueadero> parqueaderoResponse = restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo, Parqueadero.class);

		// Assert
		assertEquals(HttpStatus.CREATED, parqueaderoResponse.getStatusCode());
	}

	@Test
	public void registroSalidaVehiculoTest() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();

		// Act
		restTemplate.postForEntity("http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo,
				Parqueadero.class);

		ResponseEntity<Parqueadero> parqueaderoResponse = restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/parqueadero/registroSalida", PLACA, Parqueadero.class);

		// Assert
		assertEquals(HttpStatus.OK, parqueaderoResponse.getStatusCode());
	}

	@Test
	public void registroIngresoVehiculoErrorTest() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();

		// Act
		restTemplate.postForEntity("http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo,
				Parqueadero.class);

		ResponseEntity<String> parqueaderoResponse = restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo, String.class);

		// Assert
		assertEquals(CatalogoMensajes.VEHICULO_YA_SE_ENCUENTRA_ESTACIONADO, parqueaderoResponse.getBody());

	}

	@Test
	public void registroSalidaVehiculoErrorTest() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();

		// Act
		ResponseEntity<String> parqueaderoResponse = restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/parqueadero/registroSalida", vehiculo.getPlaca(),
				String.class);

		// Assert
		assertEquals(CatalogoMensajes.VEHICULO_NO_ESTA_ESTACIONADO, parqueaderoResponse.getBody());
	}

	@Test
	public void consultarParqueaderosTest() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();

		// Act
		restTemplate.postForEntity("http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo,
				Parqueadero.class);

		ResponseEntity<List> parqueaderoResponse = restTemplate
				.getForEntity("http://localhost:" + localServerPort + "/parqueadero/buscarVehiculos", List.class);

		// Assert
		assertFalse(parqueaderoResponse.getBody().isEmpty());
	}

	@Test
	public void consultarParqueaderoVacioTest() {

		// Act
		ResponseEntity<String> parqueaderoResponse = restTemplate
				.getForEntity("http://localhost:" + localServerPort + "/parqueadero/buscarVehiculos", String.class);

		// Assert
		assertEquals(CatalogoMensajes.PARQUEADERO_ESTA_VACIO, parqueaderoResponse.getBody());
	}
}