package test.com.br.parakyEstacionamento.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.model.CarModelBD;
import br.com.parakyestacionamento.model.ClientModelBD;

public class ClientModelBdTest {

	@Test
	public void shouldSelectAllMonthlyPayment() throws  ClassNotFoundException, SQLException {
		
		try{
			ClientModelBD clientModel = new ClientModelBD();
			List<Client> clientList = clientModel.selectAll();
			for (Client client : clientList) {
				System.out.println("Esse eh o nome completo: "+client.getName()+" "+client.getLastName()+" data Nascimento: "+client.getBirthdayDate().toString());
			}
			assertTrue(true);
			
		}
		catch(Exception e){
			fail("Erro no teste shouldSelectAllMonthlyPayment "+e.getMessage());
		}
		
	    
	}

	@Test
	public void shouldInsertAClientWithADifferentCPFEveryTime() throws  ClassNotFoundException, SQLException {
		
		try{
			ClientModelBD newModelBD = new ClientModelBD();
			Client client = new Client();
			
			client.setIdOwnerParkingSpace(0);
			client.setName("testeInsert");
			client.setLastName("testeInsert");
			
			client.setEmail("testeemail");
			client.setTel_1("teste_tel1");
			client.setTel_2("teste_tel2");
			
			Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	client.setBirthdayDate(cal.getTime());
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    	client.setCpf("testeCPF"+sdf.format(cal.getTime()));
			
			newModelBD.insert(client);
			
			List<Client> clientList = newModelBD.selectAll();
			
			for (Client client2 : clientList) {
				System.out.println(" Client id: "+client2.getIdClient()+" Nome completo:"+client2.getName()+
						" "+client2.getLastName()+
						" CPF: "+client2.getCpf());
			}
			
			assertTrue(true);
			
		}
		catch(Exception e){
			fail("Erro no teste shouldInsertATesteCar "+e.getMessage() + e);
		}
			
		}
		
		@Test
		public void shouldverifyClientCPFAlreadyExists() throws  ClassNotFoundException, SQLException {
			
			try{
				ClientModelBD newModelBD = new ClientModelBD();
				assertEquals(newModelBD.verifyIfClientCPFExists("123456789"),true);
				
			}
			catch(Exception e){
				fail("Erro no teste shouldverifyThatCarPlateAlreadyExists "+e.getMessage() + e);
				
			}
		    
	}
//
//		@Test
//		public void shouldUpdateCarId_1() throws  ClassNotFoundException, SQLException {
//			
//			try{
//				CarModelBD newModelBD = new CarModelBD();
//				Car car = new Car();
//				car.setIdCar(1);
//				car.setIdClientCar(1);
//				car.setYearManufacture(1900);
//				car.setColor("teste");
//				car.setModel("teste");
//		    	
//				car.setCarPlate("testeDeUpdate");
//				car.setCarBrand("teste");
//				
//				newModelBD.update(car);
//				
//				List<Car> carList = newModelBD.selectAll();
//				
//				for (Car car2 : carList) {
//					System.out.println(" Carro id: "+car2.getIdCar()+" carOwner :"+car2.getIdClientCar()+
//							" placa: "+car2.getCarPlate()+" cor :"+car2.getColor());
//				}
//				
//				assertTrue(true);
//				
//			}
//			catch(Exception e){
//				fail("Erro no teste shouldInsertATesteCar "+e.getMessage() + e);
//			}
//				
//		}
//	
	
}