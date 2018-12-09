package com.ceiba.parkingceiba.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parkingceiba.domain.IControlParqueadero;
import com.ceiba.parkingceiba.domain.IRestriccionPlaca;
import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;
import com.ceiba.parkingceiba.mensajes.CatalogoMensajes;
import com.ceiba.parkingceiba.model.entity.Parqueadero;
import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.repository.VehiculoDao;
import com.ceiba.parkingceiba.service.ControlParqueaderoServiceImp;
import com.ceiba.parkingceiba.service.IParqueaderoService;
import com.ceiba.parkingceiba.service.IVehiculoService;
import com.ceiba.parkingceiba.util.EnumTipoVehiculo;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class ControlParqueaderoServiceTest {
	
	@Mock
	public IControlParqueadero controlParqueadero;
	
	@Mock
	public IRestriccionPlaca restriccionPlaca;
	
	@Mock
	public IParqueaderoService parqueaderoService;
	
	@Mock
	public IVehiculoService vehiculoService;
	
	@Mock
	public VehiculoDao vehiculoDao;
	
	@InjectMocks
	ControlParqueaderoServiceImp control;
	
	@org.junit.Before
	public void inicializarMocks() {
		MockitoAnnotations.initMocks(this);
		 control = mock( ControlParqueaderoServiceImp.class); 
		 control = spy(new ControlParqueaderoServiceImp(controlParqueadero, restriccionPlaca, parqueaderoService, vehiculoService, vehiculoDao)); 
	}

	private static final String PLACA = "XCD123";
	private static final int MAS_HORAS_EN_PARQUEADERO = 11;

	/*@Test
	public void registrarVehiculoPlacaIniciaPorLetraAYDiaEsDomingoOLunesTest() {
		try {
			//Arrange
			IParqueaderoService parqueaderoService = mock(IParqueaderoService.class);
			IVehiculoService vehiculoService = mock(IVehiculoService.class);
			IControlParqueadero controlParqueadero = mock(IControlParqueadero.class);
			IRestriccionPlaca restriccionPlaca = mock(IRestriccionPlaca.class);
			VehiculoDao vehiculoDao = mock(VehiculoDao.class);
			
			Vehiculo vehiculo = new Vehiculo("AAD101", 0, EnumTipoVehiculo.CARRO);

			//control = spy(new ControlParqueaderoServiceImp(controlParqueadero, restriccionPlaca, parqueaderoService, vehiculoService, vehiculoDao)); 
			
			Mockito.doReturn(true).when(restriccionPlaca).validadSiEsDomingoOLunes();
			Mockito.doReturn(true).when(controlParqueadero).validarPlacaIniciaPorLetraA("AAD101");
			Mockito.doReturn(true).when(controlParqueadero).buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO);
			Mockito.doReturn(false).when(controlParqueadero).buscarVehiculoEstacionado("GGB471");
			Mockito.doReturn(vehiculo).when(vehiculoService).getVehiculoAParquear(new Vehiculo());
			Mockito.doReturn(new Parqueadero()).when(parqueaderoService).registrarParqueoVehiculo(new Parqueadero());
			
			/*Mockito.when(restriccionPlaca.validadSiEsDomingoOLunes()).thenReturn(true);
			Mockito.when(controlParqueadero.validarPlacaIniciaPorLetraA("ASD471")).thenReturn(true);
			Mockito.when(controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO)).thenReturn(true);
			Mockito.when(controlParqueadero.buscarVehiculoEstacionado("GGB471")).thenReturn(false);
			Mockito.when(vehiculoService.getVehiculoAParquear(new Vehiculo())).thenReturn(vehiculo);
			Mockito.when(parqueaderoService.registrarParqueoVehiculo(new Parqueadero())).thenReturn(new Parqueadero());*/
			
			//Act
			//Parqueadero parqueadero = control.registroVehiculo(vehiculo);
			/*ControlParqueaderoServiceImp cImp = new ControlParqueaderoServiceImp(controlParqueadero, restriccionPlaca, parqueaderoService, vehiculoService, vehiculoDao); 
			Parqueadero parqueadero = cImp.registroVehiculo(vehiculo);
			
			assertNotNull(parqueadero);
	
		} catch (ParqueaderoErrorBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void registrarVehiculoPlacaIniciaPorLetraAYDiaNoEsDomingoOLunesTest() {
		try {

			Vehiculo vehiculo = new Vehiculo("ASD471", 0, EnumTipoVehiculo.CARRO);
			
			control = spy(new ControlParqueaderoServiceImp(controlParqueadero, restriccionPlaca, parqueaderoService, vehiculoService, vehiculoDao)); 
			Mockito.when(restriccionPlaca.validadSiEsDomingoOLunes()).thenReturn(false);
			Mockito.when(controlParqueadero.validarPlacaIniciaPorLetraA("ASD471")).thenReturn(true);
			Mockito.when(controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO)).thenReturn(true);
			Mockito.when(controlParqueadero.buscarVehiculoEstacionado("GGB471")).thenReturn(false);
			control.registroVehiculo(vehiculo);

		} catch (ParqueaderoErrorBuilderException e) {
			assertEquals(CatalogoMensajes.PLACA_INVALIDA_PARA_INGRESO, e.getMensaje());
		}
	}
	
	@Test
	public void registrarVehiculoPlacaNoIniciaConLetraAYDiaEsDomingoOLunesTest() {
		try {

			Vehiculo vehiculo = new Vehiculo("BNM101", 0, EnumTipoVehiculo.CARRO);
			
			control = spy(new ControlParqueaderoServiceImp(controlParqueadero, restriccionPlaca, parqueaderoService, vehiculoService, vehiculoDao)); 
			Mockito.when(restriccionPlaca.validadSiEsDomingoOLunes()).thenReturn(true);
			Mockito.when(controlParqueadero.validarPlacaIniciaPorLetraA("BNM101")).thenReturn(false);
			Mockito.when(controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO)).thenReturn(true);
			Mockito.when(controlParqueadero.buscarVehiculoEstacionado("GGB471")).thenReturn(false);
			control.registroVehiculo(vehiculo);

		} catch (ParqueaderoErrorBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void registrarVehiculoPlacaNoIniciaConLetraAYDiaNoEsDomingoOLunesTest() {
		try {

			Vehiculo vehiculo = new Vehiculo("BNM101", 0, EnumTipoVehiculo.CARRO);
			
			control = spy(new ControlParqueaderoServiceImp(controlParqueadero, restriccionPlaca, parqueaderoService, vehiculoService, vehiculoDao)); 
			Mockito.when(restriccionPlaca.validadSiEsDomingoOLunes()).thenReturn(false);
			Mockito.when(controlParqueadero.validarPlacaIniciaPorLetraA("BNM101")).thenReturn(false);
			Mockito.when(controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO)).thenReturn(true);
			Mockito.when(controlParqueadero.buscarVehiculoEstacionado("GGB471")).thenReturn(false);
			control.registroVehiculo(vehiculo);

		} catch (ParqueaderoErrorBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void registrarVehiculoPlacaIniciaPorLetraADiaEsDomingoOLunesYValidaEspacioPorTipoVehiculoTest() {
		try {

			Vehiculo vehiculo = new Vehiculo("AAD101", 0, EnumTipoVehiculo.CARRO);
			
			control = spy(new ControlParqueaderoServiceImp(controlParqueadero, restriccionPlaca, parqueaderoService, vehiculoService, vehiculoDao)); 
			Mockito.when(restriccionPlaca.validadSiEsDomingoOLunes()).thenReturn(true);
			Mockito.when(controlParqueadero.validarPlacaIniciaPorLetraA("AAD101")).thenReturn(true);
			Mockito.when(controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO)).thenReturn(false);
			Mockito.when(controlParqueadero.buscarVehiculoEstacionado("GGB471")).thenReturn(false);
			control.registroVehiculo(vehiculo);

		} catch (ParqueaderoErrorBuilderException e) {
			// TODO Auto-generated catch block
			assertEquals(CatalogoMensajes.NO_HAY_ESPACIO_PARA_EL_TIPO_DE_VEHICULO, e.getMensaje());
		}
	}

	@Test
	public void registrarVehiculoPlacaNoIniciaConLetraAYDiaEsDomingoOLunesYValidaEspacioPorTipoVehiculoTest() {
		try {

			Vehiculo vehiculo = new Vehiculo("BNM101", 0, EnumTipoVehiculo.CARRO);
			
			control = spy(new ControlParqueaderoServiceImp(controlParqueadero, restriccionPlaca, parqueaderoService, vehiculoService, vehiculoDao)); 
			Mockito.when(restriccionPlaca.validadSiEsDomingoOLunes()).thenReturn(false);
			Mockito.when(controlParqueadero.validarPlacaIniciaPorLetraA("BNM101")).thenReturn(false);
			Mockito.when(controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO)).thenReturn(false);
			Mockito.when(controlParqueadero.buscarVehiculoEstacionado("GGB471")).thenReturn(false);
			control.registroVehiculo(vehiculo);

		} catch (ParqueaderoErrorBuilderException e) {
			// TODO Auto-generated catch block
			assertEquals(CatalogoMensajes.NO_HAY_ESPACIO_PARA_EL_TIPO_DE_VEHICULO, e.getMensaje());
		}
	}
	
}
