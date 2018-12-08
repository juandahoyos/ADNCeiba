package com.ceiba.parkingceiba.integracion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControlParqueaderoService {
	
	private static final int CILINDRAJE = 180;
	
	private static final String TIPO_VEHICULO_AVION = "Avion";

	private static final String PLACA = "XCD123";

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@LocalServerPort
	private int localServerPort; 
	
	@Test
	public void registrarIngreso() {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE).conTipoVehiculo(EnumTipoVehiculo.MOTO).build();
		ResponseEntity<Parqueadero> parqueadero = restTemplate.postForEntity("http://localhost:"+localServerPort+"/parqueadero/registroEntrada", vehiculo,Parqueadero.class);
		System.out.println("Puerto: " + localServerPort);
		assertEquals(HttpStatus.CREATED, parqueadero.getStatusCode());
	}
	
	/*@Test
	public void registrarIngresoFallido() {
			Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE).conTipoVehiculo(TIPO_VEHICULO_AVION).build();
			ResponseEntity<String> parqueadero = restTemplate.postForEntity("http://localhost:"+localServerPort+"/parqueadero/registroEntrada", vehiculo,String.class);
			System.out.println("Puerto: " + localServerPort);
			assertEquals(CatalogoMensajes.INGRESO_VEHICULO_DIFERENTE_A_CARRO_O_MOTO,parqueadero.getBody());

	}*/
}
