package com.ceiba.parkingceiba.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

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
import com.ceiba.parkingceiba.domain.imp.ControlParqueaderoImp;
import com.ceiba.parkingceiba.domain.imp.RestriccionPlacaImp;
import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;
import com.ceiba.parkingceiba.mensajes.CatalogoMensajes;
import com.ceiba.parkingceiba.model.entity.Vehiculo;
import com.ceiba.parkingceiba.repository.ParqueaderoDao;
import com.ceiba.parkingceiba.repository.VehiculoDao;
import com.ceiba.parkingceiba.service.ControlParqueaderoServiceImp;
import com.ceiba.parkingceiba.service.IParqueaderoService;
import com.ceiba.parkingceiba.service.IVehiculoService;
import com.ceiba.parkingceiba.service.VehiculoServiceImp;
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
	
	@Mock
	public ParqueaderoDao parqueaderoDao;
	
	@InjectMocks
	ControlParqueaderoServiceImp control;
	
	@org.junit.Before
	public void inicializarMocks() {
		MockitoAnnotations.initMocks(this);
		 control = mock(ControlParqueaderoServiceImp.class); 
		 control = spy(new ControlParqueaderoServiceImp(controlParqueadero, restriccionPlaca, parqueaderoService, vehiculoService, vehiculoDao, parqueaderoDao)); 
	}

	private static final String PLACA = "XCD123";

	@Test
	public void registrarVehiculoPlacaIniciaPorLetraAYDiaEsDomingoOLunesTest() {
		try {
			//Arrange
			
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setPlaca(PLACA);
			IRestriccionPlaca restriccionPlacaImp = Mockito.mock(RestriccionPlacaImp.class);
			Mockito.when(restriccionPlacaImp.validadSiEsDomingoOLunes()).thenReturn(false);
			IControlParqueadero controlParqueaderoImp = Mockito.mock(ControlParqueaderoImp.class);
			Mockito.when(controlParqueaderoImp.validarPlacaIniciaPorLetraA(PLACA)).thenReturn(true);
			ControlParqueaderoServiceImp controlParqueadero = new ControlParqueaderoServiceImp(controlParqueaderoImp,restriccionPlacaImp,null,null,null,null);
			//Act
			controlParqueadero.registroVehiculo(vehiculo);
			fail();
		} catch (ParqueaderoErrorBuilderException e) {
			assertEquals(CatalogoMensajes.PLACA_INVALIDA_PARA_INGRESO, e.getMensaje());
		}
	}
	
	
	@Test
	public void registrarVehiculoValidaEspacioPorTipoVehiculoTest() {
		try {

			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setPlaca(PLACA);
			vehiculo.setEnumTipoVehiculo(EnumTipoVehiculo.CARRO);
			IRestriccionPlaca restriccionPlacaImp = Mockito.mock(RestriccionPlacaImp.class);
			Mockito.when(restriccionPlacaImp.validadSiEsDomingoOLunes()).thenReturn(false);
			IControlParqueadero controlParqueaderoImp = Mockito.mock(ControlParqueaderoImp.class);
			Mockito.when(controlParqueaderoImp.validarPlacaIniciaPorLetraA(PLACA)).thenReturn(false);
			Mockito.when(controlParqueaderoImp.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO)).thenReturn(false);
			ControlParqueaderoServiceImp controlParqueadero = new ControlParqueaderoServiceImp(controlParqueaderoImp,restriccionPlacaImp,null,null,null,parqueaderoDao);
			//Act
			controlParqueadero.registroVehiculo(vehiculo);
			fail();
		} catch (ParqueaderoErrorBuilderException e) {
			assertEquals(CatalogoMensajes.NO_HAY_ESPACIO_PARA_EL_TIPO_DE_VEHICULO, e.getMensaje());
		}

	}
	
	@Test
	public void registrarVehiculoEstacionadoTest() {
		try {

			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setPlaca(PLACA);
			vehiculo.setEnumTipoVehiculo(EnumTipoVehiculo.CARRO);
			IRestriccionPlaca restriccionPlacaImp = Mockito.mock(RestriccionPlacaImp.class);
			Mockito.when(restriccionPlacaImp.validadSiEsDomingoOLunes()).thenReturn(false);
			IControlParqueadero controlParqueaderoImp = Mockito.mock(ControlParqueaderoImp.class);
			Mockito.when(controlParqueaderoImp.validarPlacaIniciaPorLetraA(PLACA)).thenReturn(false);
			Mockito.when(controlParqueaderoImp.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO)).thenReturn(true);
			Mockito.when(controlParqueaderoImp.buscarVehiculoEstacionado(PLACA)).thenReturn(true);
			ControlParqueaderoServiceImp controlParqueadero = new ControlParqueaderoServiceImp(controlParqueaderoImp,restriccionPlacaImp,null,null,null,parqueaderoDao);
			//Act
			controlParqueadero.registroVehiculo(vehiculo);
			fail();
		} catch (ParqueaderoErrorBuilderException e) {
			assertEquals(CatalogoMensajes.VEHICULO_YA_SE_ENCUENTRA_ESTACIONADO, e.getMensaje());
		}
	}
	
	@Test
	
	public void validarIngresovehiculo() {
		
		Vehiculo vehiculo = new Vehiculo(PLACA, 0, EnumTipoVehiculo.CARRO);
		
		VehiculoServiceImp vehiculoService = new VehiculoServiceImp(vehiculoDao);
		Mockito.when(vehiculoDao.save(new Vehiculo())).thenReturn(vehiculo);
		Mockito.when(vehiculoDao.existsByPlaca(PLACA)).thenReturn(false);
		Mockito.when(vehiculoDao.findByPlaca(PLACA)).thenReturn(vehiculo);
		
		Vehiculo vehiculoResponse = vehiculoService.getVehiculoAParquear(vehiculo);
		
		assertEquals(vehiculoResponse.getPlaca(), vehiculo.getPlaca()); 
		
	}
}
