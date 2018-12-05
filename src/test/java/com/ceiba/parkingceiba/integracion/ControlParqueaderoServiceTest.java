package com.ceiba.parkingceiba.integracion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parkingceiba.domain.IControlParqueadero;
import com.ceiba.parkingceiba.domain.IRestriccionPlaca;
import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;
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

	private static final String DIA_VALIDO = "";

	@Test
	public void registrarVehiculoTest() {
		try {

			Vehiculo vehiculo = new Vehiculo("GSD471", 0, EnumTipoVehiculo.CARRO);
			
			control = spy(new ControlParqueaderoServiceImp(controlParqueadero, restriccionPlaca, parqueaderoService, vehiculoService, vehiculoDao)); 
			Mockito.when(restriccionPlaca.validadSiEsDomingoOLunes()).thenReturn(true);
			Mockito.when(controlParqueadero.validarPlacaIniciaPorLetraA("GSD471")).thenReturn(true);
			Mockito.when(controlParqueadero.buscarEspacioPorTipoVehiculo(EnumTipoVehiculo.CARRO)).thenReturn(true);
			Mockito.when(controlParqueadero.buscarVehiculoEstacionado("ASD471")).thenReturn(false);
			control.registroVehiculo(vehiculo);

		} catch (ParqueaderoErrorBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
