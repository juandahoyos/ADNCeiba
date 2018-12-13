package com.ceiba.parkingceiba.unit;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parkingceiba.domain.IControlParqueadero;
import com.ceiba.parkingceiba.domain.ICronometroParqueadero;
import com.ceiba.parkingceiba.domain.imp.CronometroParqueaderoImp;
import com.ceiba.parkingceiba.domain.imp.TiempoParqueaderoImp;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.repository.ParqueaderoDao;
import com.ceiba.parkingceiba.testdatabuilder.ParqueaderoTestDataBuilder;
import com.ceiba.parkingceiba.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;
import com.ceiba.parkingceiba.util.ParametrosGlobalesParqueadero;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class ControlParqueaderoTest {

	@org.junit.Before
	public void inicializarMocks() {
		MockitoAnnotations.initMocks(this);
	}

	private static final String PLACA_INICIA_CON_A = "ASD451";
	private static final String PLACA_NO_INICIA_CON_A = "BUY987";
	private static final String PLACA_ESTACIONADA = "TSG541";
	private static final String PLACA_NO_ESTACIONADA = "PLJ458";

	private static final int MAS_HORAS_EN_PARQUEADERO = 11;
	private static final int CILINDRAJE_CARRO = 1200;
	private static final int PAGO_ESPERADO_PARA_CARRO = 8000;
	private static final int CILINDRAJE_MOTO = 125;
	private static final int PAGO_ESPERADO_PARA_MOTO = 4000;
	private static final int CILINDRAJE_MOTO_550CC = 900;
	private static final int PAGO_ESPERADO_PARA_MOTO_550CC = 6000;
	private static final int MINUTOS = 1880;

	public boolean respuesta;

	@Autowired
	private IControlParqueadero controlParqueadero;

	@MockBean
	private ParqueaderoDao parqueaderoDao;

	@Test
	public void validaPlacaIniciaConLetraATest() {
		// Act
		respuesta = controlParqueadero.validarPlacaIniciaPorLetraA(PLACA_INICIA_CON_A);
		// Assert
		assertTrue(respuesta);
	}

	@Test
	public void validaPlacaNoIniciaConLetraATest() {
		// Act
		respuesta = controlParqueadero.validarPlacaIniciaPorLetraA(PLACA_NO_INICIA_CON_A);
		// Assert
		assertFalse(respuesta);
	}

	@Test
	public void espacioDisponibleParaCarroTest() {
		// Arrange
		Mockito.when(parqueaderoDao.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO))
				.thenReturn(ParametrosGlobalesParqueadero.MAXIMO_CARROS_PERMITIDOS - 2);
		// Act
		respuesta = controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO);
		// Assert
		assertTrue(respuesta);
	}

	@Test
	public void espacioDisponibleParaMotoTest() {
		// Arrange
		Mockito.when(parqueaderoDao.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.MOTO))
				.thenReturn(ParametrosGlobalesParqueadero.MAXIMO_MOTOS_PERMITIDOS - 2);
		// Act
		respuesta = controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.MOTO);
		// Assert
		assertTrue(respuesta);
	}

	@Test
	public void sinEspacioDisponibleParaCarroTest() {
		// Arrange
		Mockito.when(parqueaderoDao.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO))
				.thenReturn(ParametrosGlobalesParqueadero.MAXIMO_CARROS_PERMITIDOS + 2);
		// Act
		respuesta = controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO);
		// Assert
		assertFalse(respuesta);
	}

	@Test
	public void sinEspacioDisponibleParaMotoTest() {
		// Arrange
		Mockito.when(parqueaderoDao.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.MOTO))
				.thenReturn(ParametrosGlobalesParqueadero.MAXIMO_MOTOS_PERMITIDOS + 2);
		// Act
		respuesta = controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.MOTO);
		// Assert
		assertFalse(respuesta);
	}

	@Test
	public void parqueaderoVacioTest() {
		// Arrange
		Mockito.when(parqueaderoDao.paqueaderoVacio()).thenReturn(true);
		// Act
		respuesta = controlParqueadero.validarSiPaqueaderoEstaVacio();
		// Assert
		assertTrue(respuesta);
	}

	@Test
	public void validaVehiculoParqueadoTest() {
		// Arrange
		Mockito.when(parqueaderoDao.buscarVehiculoYaEstacionado(PLACA_ESTACIONADA)).thenReturn(true);
		// Act
		respuesta = controlParqueadero.buscarVehiculoEstacionado(PLACA_ESTACIONADA);
		// Assert
		assertTrue(respuesta);
	}

	@Test
	public void validaVehiculoNoParqueadoTest() {
		// Arrange
		Mockito.when(parqueaderoDao.buscarVehiculoYaEstacionado(PLACA_NO_ESTACIONADA)).thenReturn(false);
		// Act
		respuesta = controlParqueadero.buscarVehiculoEstacionado(PLACA_NO_ESTACIONADA);
		// Assert
		assertFalse(respuesta);
	}

	@Test
	public void validarVehiculoEnParqueaderoTest() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA_ESTACIONADA).conCilindraje(CILINDRAJE_MOTO)
				.conTipoVehiculo(EnumTipoVehiculo.MOTO).build();
		Parqueadero parqueadero = new ParqueaderoTestDataBuilder().conFechaIngreso(new Date())
				.conFechaSalida(new Date()).conEstado(true).conCobro(0).conVehiculo(vehiculo).build();

		Mockito.when(parqueaderoDao.encontrarVehiculoEnParqueaderoPorPlaca(PLACA_ESTACIONADA)).thenReturn(parqueadero);
		// Act
		Parqueadero placa = controlParqueadero.getObtenerParqueaderoParaAsignar(PLACA_ESTACIONADA);
		// Assert
		assertEquals(placa.getVehiculo().getPlaca(), vehiculo.getPlaca());
	}

	@Test
	public void calcularCobroTest() {
		// Arrange
		ICronometroParqueadero cronometroParqueadero = mock(ICronometroParqueadero.class);
		int cobro = 0;

		Calendar calendar = Calendar.getInstance();
		Date fechaIngreso = calendar.getTime();

		calendar.add(Calendar.HOUR, MAS_HORAS_EN_PARQUEADERO);
		Date fechaSalida = calendar.getTime();

		// Act
		Mockito.when(cronometroParqueadero.obtenerTiempoDeParqueo(540)).thenReturn(new TiempoParqueaderoImp(1, 1));
		cobro = controlParqueadero.calcularCobro(fechaIngreso, fechaSalida,
				ParametrosGlobalesParqueadero.VALOR_DIA_CARRO, ParametrosGlobalesParqueadero.VALOR_HORA_CARRO);

		// Assert
		assertEquals(PAGO_ESPERADO_PARA_CARRO, cobro);
	}

	@Test
	public void calcularCobroParaCarroTest() {
		// Arrange
		IControlParqueadero spycontrolParqueadero = Mockito.spy(controlParqueadero);
		int cobro = 0;

		Calendar calendar = Calendar.getInstance();
		Date fechaIngreso = calendar.getTime();

		calendar.add(Calendar.HOUR, MAS_HORAS_EN_PARQUEADERO);
		Date fechaSalida = calendar.getTime();

		// Act
		Mockito.when(spycontrolParqueadero.calcularCobro(fechaIngreso, fechaSalida,
				ParametrosGlobalesParqueadero.VALOR_DIA_CARRO, ParametrosGlobalesParqueadero.VALOR_HORA_CARRO))
				.thenReturn(PAGO_ESPERADO_PARA_CARRO);
		cobro = controlParqueadero.generarCobro(EnumTipoVehiculo.CARRO, fechaIngreso, fechaSalida, CILINDRAJE_CARRO);

		// Assert
		assertEquals(PAGO_ESPERADO_PARA_CARRO, cobro);
	}

	@Test
	public void calcularCobroParaMotoTest() {
		// Arrange
		IControlParqueadero spycontrolParqueadero = Mockito.spy(controlParqueadero);
		int cobro = 0;

		Calendar calendar = Calendar.getInstance();
		Date fechaIngreso = calendar.getTime();

		calendar.add(Calendar.HOUR, MAS_HORAS_EN_PARQUEADERO);
		Date fechaSalida = calendar.getTime();

		// Act
		Mockito.when(spycontrolParqueadero.calcularCobro(fechaIngreso, fechaSalida,
				ParametrosGlobalesParqueadero.VALOR_DIA_MOTO, ParametrosGlobalesParqueadero.VALOR_HORA_MOTO))
				.thenReturn(PAGO_ESPERADO_PARA_MOTO);
		cobro = controlParqueadero.generarCobro(EnumTipoVehiculo.MOTO, fechaIngreso, fechaSalida, CILINDRAJE_MOTO);

		// Assert
		assertEquals(PAGO_ESPERADO_PARA_MOTO, cobro);
	}

	@Test
	public void calcularCobroParaMoto550CCTest() {
		// Arrange
		IControlParqueadero spyControlParqueadero = Mockito.spy(controlParqueadero);
		int cobro = 0;

		Calendar calendar = Calendar.getInstance();
		Date fechaIngreso = calendar.getTime();

		calendar.add(Calendar.HOUR, MAS_HORAS_EN_PARQUEADERO);
		Date fechaSalida = calendar.getTime();

		// Act
		Mockito.when(spyControlParqueadero.calcularCobro(fechaIngreso, fechaSalida,
				ParametrosGlobalesParqueadero.VALOR_DIA_MOTO, ParametrosGlobalesParqueadero.VALOR_HORA_MOTO))
				.thenReturn(PAGO_ESPERADO_PARA_MOTO_550CC);
		cobro = controlParqueadero.generarCobro(EnumTipoVehiculo.MOTO, fechaIngreso, fechaSalida,
				CILINDRAJE_MOTO_550CC);

		// Assert
		assertEquals(PAGO_ESPERADO_PARA_MOTO_550CC, cobro);
	}

	@Test
	public void obtenerTiempoParqueoTest() {
		TiempoParqueaderoImp tiempoParqueadero = null;
		CronometroParqueaderoImp cronometro = new CronometroParqueaderoImp();

		tiempoParqueadero = cronometro.obtenerTiempoDeParqueo(MINUTOS);

		assertThat(tiempoParqueadero, instanceOf(TiempoParqueaderoImp.class));
	}
}