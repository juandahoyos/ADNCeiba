package com.ceiba.parkingceiba.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ceiba.parkingceiba.mensajes.CatalogoMensajes;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.repository.ParqueaderoDao;
import com.ceiba.parkingceiba.service.ControlParqueaderoServiceImp;
import com.ceiba.parkingceiba.service.IControlParqueaderoService;
import com.ceiba.parkingceiba.service.IParqueaderoService;
import com.ceiba.parkingceiba.service.IVehiculoService;
import com.ceiba.parkingceiba.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class ControlParqueaderoService {


	private static final int CILINDRAJE = 180;

	private static final String PLACA = "XCD123";
	

	private TestRestTemplate restTemplate = new TestRestTemplate();
	
	
	public IControlParqueadero controlParqueadero;

	@Mock
	public IRestriccionPlaca restriccionPlaca;

	@Mock
	public IParqueaderoService parqueaderoService;

	@Mock
	public IVehiculoService vehiculoService;

	@Autowired
	public ParqueaderoDao parqueaderoDao;

	@Mock
	ControlParqueaderoServiceImp control;
	
	@InjectMocks
	private ParqueaderoController parqueaderoController;
	
	@Mock
	public IControlParqueaderoService controlParqueaderoService;
	
	ControlParqueaderoServiceImp controlSpy;
	
	
	@LocalServerPort
	private int localServerPort;

	@Before
	public void inicializarMocks() {
		 parqueaderoController = new ParqueaderoController(controlParqueaderoService);
		 parqueaderoDao.deleteAll();
	}
	
	@Mock
	private ArrayList<Parqueadero> mockArrayList;
	
	@Test
	public void registrarIngreso() {
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();
		/*
		Parqueadero parqueadero = new ParqueaderoTestDataBuilder().conFechaIngreso(new Date()).conFechaSalida(null)
				.conEstado(true).conCobro(0).conVehiculo(vehiculo).build();
		
		//Act
		Mockito.doReturn(parqueadero).when(controlSpy).registroVehiculo(vehiculo);*/
		//Mockito.when(control.registroVehiculo(vehiculo)).thenReturn(parqueadero);
		ResponseEntity<Parqueadero> parqueaderoResponse = restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo,
				Parqueadero.class);
		
		//Assert
		assertEquals(HttpStatus.CREATED, parqueaderoResponse.getStatusCode());
	}

	@Test
	public void registroSalida() {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();
		/*Parqueadero parqueadero = new ParqueaderoTestDataBuilder().conFechaIngreso(new Date()).conFechaSalida(new Date())
				.conEstado(false).conCobro(3000).conVehiculo(vehiculo).build();
		Mockito.doReturn(parqueadero).when(controlSpy).salidaVehiculo(PLACA);*/
		//Mockito.when(control.salidaVehiculo(PLACA)).thenReturn(parqueadero);
		
		//IControlParqueadero controlParqueaderoImp = Mockito.mock(ControlParqueaderoImp.class);
		//Mockito.doReturn(false).when(controlParqueaderoImp).buscarVehiculoEstacionado(PLACA);
		//Mockito.when(controlParqueaderoImp.buscarVehiculoEstacionado(PLACA)).thenReturn(false);
		
		restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo,
				Parqueadero.class);
		
		ResponseEntity<Parqueadero> parqueaderoResponse = restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/parqueadero/registroSalida", PLACA, Parqueadero.class);
		System.out.println("Puerto: " + localServerPort);
		
		assertEquals(HttpStatus.OK, parqueaderoResponse.getStatusCode());
	}
	
	
	@Test
	public void registroIngresoErrorTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();
		/*Parqueadero parqueadero = new ParqueaderoTestDataBuilder().conFechaIngreso(new Date()).conFechaSalida(new Date())
				.conEstado(false).conCobro(3000).conVehiculo(vehiculo).build();
		Mockito.doReturn(parqueadero).when(controlSpy).salidaVehiculo(PLACA);*/
		//Mockito.when(control.salidaVehiculo(PLACA)).thenReturn(parqueadero);
		
		//IControlParqueadero controlParqueaderoImp = Mockito.mock(ControlParqueaderoImp.class);
		//Mockito.doReturn(false).when(controlParqueaderoImp).buscarVehiculoEstacionado(PLACA);
		restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo, Parqueadero.class);
		System.out.println("Puerto: " + localServerPort);
		
		ResponseEntity<String> parqueaderoResponse = restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo, String.class);
		System.out.println("Puerto: " + localServerPort);

		System.out.println("registroIngreso");
		assertEquals(CatalogoMensajes.VEHICULO_YA_SE_ENCUENTRA_ESTACIONADO, parqueaderoResponse.getBody());

	}
	
	@Test
	public void registroSalidaErrorTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();
		/*Parqueadero parqueadero = new ParqueaderoTestDataBuilder().conFechaIngreso(new Date()).conFechaSalida(new Date())
				.conEstado(false).conCobro(3000).conVehiculo(vehiculo).build();
		Mockito.doReturn(parqueadero).when(controlSpy).salidaVehiculo(PLACA);*/
		//Mockito.when(control.salidaVehiculo(PLACA)).thenReturn(parqueadero);
		
		//IControlParqueadero controlParqueaderoImp = Mockito.mock(ControlParqueaderoImp.class);
		//Mockito.doReturn(false).when(controlParqueaderoImp).buscarVehiculoEstacionado(PLACA);
		ResponseEntity<String> parqueaderoResponse = restTemplate.postForEntity(
					"http://localhost:" + localServerPort + "/parqueadero/registroSalida", vehiculo.getPlaca(), String.class);
			System.out.println("Puerto: " + localServerPort);

			System.out.println("registroSalida");
			assertEquals(CatalogoMensajes.VEHICULO_NO_ESTA_ESTACIONADO, parqueaderoResponse.getBody());
	}
	
	
	@Test
	public void consultarVehiculosTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();

			restTemplate.postForEntity(
					"http://localhost:" + localServerPort + "/parqueadero/registroEntrada", vehiculo, Parqueadero.class);
			System.out.println("Puerto: " + localServerPort);
			
			ResponseEntity<List> parqueaderoResponse = restTemplate.getForEntity(
					"http://localhost:" + localServerPort + "/parqueadero/buscarVehiculos", List.class);
			System.out.println("Puerto: " + localServerPort);

			System.out.println("registroSalida");
			assertFalse(parqueaderoResponse.getBody().isEmpty());
	}
	
	@Test
	public void consultarVehiculosVacioTest() {
			ResponseEntity<String> parqueaderoResponse = restTemplate.getForEntity(
					"http://localhost:" + localServerPort + "/parqueadero/buscarVehiculos", String.class);
			System.out.println("Puerto: " + localServerPort);

			System.out.println("registroSalida");
			assertEquals(CatalogoMensajes.PARQUEADERO_ESTA_VACIO,parqueaderoResponse.getBody());
	}
	


	/*@Test
	public void buscarTodosLosVehiculos() {
		
		List<Parqueadero> mockList = new ArrayList<>();
		Mockito.when(parqueaderoService.encontrarTodosLosParqueaderos()).thenReturn(mockList);
		mockList.addAll(mockArrayList);
		
	ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:"+localServerPort+"/parqueadero/buscarVehiculos",String.class);
	System.out.println("Puerto: " + localServerPort);
	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}*/
}
