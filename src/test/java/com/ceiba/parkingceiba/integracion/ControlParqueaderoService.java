package com.ceiba.parkingceiba.integracion;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parkingceiba.controller.ParqueaderoController;
import com.ceiba.parkingceiba.domain.IControlParqueadero;
import com.ceiba.parkingceiba.domain.IRestriccionPlaca;
import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.repository.ParqueaderoDao;
import com.ceiba.parkingceiba.repository.VehiculoDao;
import com.ceiba.parkingceiba.service.ControlParqueaderoServiceImp;
import com.ceiba.parkingceiba.service.IParqueaderoService;
import com.ceiba.parkingceiba.service.IVehiculoService;
import com.ceiba.parkingceiba.testdatabuilder.ParqueaderoTestDataBuilder;
import com.ceiba.parkingceiba.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class ControlParqueaderoService {


	private static final int CILINDRAJE = 180;

	// private static final String TIPO_VEHICULO_AVION = "Avion";

	private static final String PLACA = "XCD123";

	private TestRestTemplate restTemplate = new TestRestTemplate();
	
	Gson gson = new Gson();
	
	@Mock
	public ControlParqueaderoServiceImp control;
	
	@LocalServerPort
	private int localServerPort;

	@Test
	public void registrarIngreso() {
		try {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();
		Parqueadero parqueadero = new ParqueaderoTestDataBuilder().conFechaIngreso(new Date()).conFechaSalida(null)
				.conEstado(true).conCobro(0).conVehiculo(vehiculo).build();
			Mockito.when(control.registroVehiculo(vehiculo)).thenReturn(parqueadero);
			ResponseEntity<Parqueadero> parqueaderoResponse = restTemplate.postForEntity(
					"http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo, Parqueadero.class);
			// System.out.println("Puerto: " + localServerPort);
			assertEquals(HttpStatus.CREATED, parqueaderoResponse.getStatusCode());
		} catch (ParqueaderoErrorBuilderException e) {
			e.printStackTrace();
		}
	}

	/*@Test
	public void registroSalida() {
		ResponseEntity<Parqueadero> parqueadero = restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/parqueadero/registroSalida", PLACA, Parqueadero.class);
		System.out.println("Puerto: " + localServerPort);
		assertEquals(HttpStatus.OK, parqueadero.getStatusCode());
	}
	
	/*@Test
	public void buscarTodosLosVehiculos() {
	ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:"+localServerPort+"/parqueadero/buscarVehiculos",String.class);
	System.out.println("Puerto: " + localServerPort);
	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}*/
}
