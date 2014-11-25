package test.com.br.parakyEstacionamento.car;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.primefaces.validate.bean.AssertTrueClientValidationConstraint;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.model.CarModelBD;

public class CarModelBDTest {


	@Test
	public void shouldSelectAllCars() throws  ClassNotFoundException, SQLException {
		
		try{
			CarModelBD newModelBD = new CarModelBD();
			List<Car> carList = newModelBD.selectAll();
			
			for (Car car2 : carList) {
				System.out.println(" Carro id: "+car2.getIdCar()+" carOwner :"+car2.getIdClientCar()+
						" placa: "+car2.getCarPlate()+" cor :"+car2.getColor());
			}
			
			assertTrue(true);
			
		}
		catch(Exception e){
			fail("Erro no teste shouldSelectAllCars "+e.getMessage() + e);
			
		}
		
	    
	}

	@Test
	public void shouldInsertACarWithADifferentPlateEveryTime() throws  ClassNotFoundException, SQLException {
		
		try{
			CarModelBD newModelBD = new CarModelBD();
			Car car = new Car();
			
			car.setIdClientCar(1);
			car.setYearManufacture(1900);
			car.setColor("teste");
			car.setModel("teste");
			
			Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    	
			car.setCarPlate("teste"+sdf.format(cal.getTime()));
			car.setCarBrand("teste");
			
			newModelBD.insert(car);
			
			List<Car> carList = newModelBD.selectAll();
			
			for (Car car2 : carList) {
				System.out.println(" Carro id: "+car2.getIdCar()+" carOwner :"+car2.getIdClientCar()+
						" placa: "+car2.getCarPlate()+" cor :"+car2.getColor());
			}
			
			assertTrue(true);
			
		}
		catch(Exception e){
			fail("Erro no teste shouldInsertATesteCar "+e.getMessage() + e);
		}
			
		}
		
		@Test
		public void shouldverifyThatCarPlateAlreadyExists() throws  ClassNotFoundException, SQLException {
			
			try{
				CarModelBD newModelBD = new CarModelBD();
				assertEquals(newModelBD.verifyIfCarPlateExists("teste"),true);
				
			}
			catch(Exception e){
				fail("Erro no teste shouldverifyThatCarPlateAlreadyExists "+e.getMessage() + e);
				
			}
		    
	}

		@Test
		public void shouldUpdateCarId_1() throws  ClassNotFoundException, SQLException {
			
			try{
				CarModelBD newModelBD = new CarModelBD();
				Car car = new Car();
				car.setIdCar(1);
				car.setIdClientCar(1);
				car.setYearManufacture(1900);
				car.setColor("teste");
				car.setModel("teste");
		    	
				car.setCarPlate("testeDeUpdate");
				car.setCarBrand("teste");
				
				newModelBD.update(car);
				
				List<Car> carList = newModelBD.selectAll();
				
				for (Car car2 : carList) {
					System.out.println(" Carro id: "+car2.getIdCar()+" carOwner :"+car2.getIdClientCar()+
							" placa: "+car2.getCarPlate()+" cor :"+car2.getColor());
				}
				
				assertTrue(true);
				
			}
			catch(Exception e){
				fail("Erro no teste shouldInsertATesteCar "+e.getMessage() + e);
			}
				
		}
			

}
