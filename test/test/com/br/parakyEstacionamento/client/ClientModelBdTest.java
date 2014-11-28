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
import br.com.parakyestacionamento.modeloBD.CarModelBD;
import br.com.parakyestacionamento.modeloBD.ClientModelBD;

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
			fail("Erro no teste shouldInsertAClientWithADifferentCPFEveryTime "+e.getMessage() + e);
		}
			
		}
		
		@Test
		public void shouldverifyClientCPFAlreadyExists() throws  ClassNotFoundException, SQLException {
			
			try{
				ClientModelBD newModelBD = new ClientModelBD();
				assertEquals(newModelBD.verifyIfClientCPFExists("123456789"),true);
				
			}
			catch(Exception e){
				fail("Erro no teste shouldverifyClientCPFAlreadyExists "+e.getMessage() + e);
				
			}
		    
	}

		@Test
		public void shouldUpdateClient_1() throws  ClassNotFoundException, SQLException {
			
			try{
				ClientModelBD model = new ClientModelBD();
				Client client = new Client();
				client.setIdClient(2);
				client.setIdOwnerParkingSpace(0);
				client.setName("testeUpdate");
				client.setLastName("testeUPDATE");
				
				client.setEmail("testeemailUPDATE");
				client.setTel_1("teste_tel1UPDATE");
				client.setTel_2("teste_tel2UPDATE");
				
				Calendar cal = Calendar.getInstance();
		    	cal.getTime();
		    	client.setBirthdayDate(cal.getTime());
		    	
		    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		    	client.setCpf("testeCPF_UPDATE"+sdf.format(cal.getTime()));
								
				model.update(client);
				
				List<Client> clientList = model.selectAll();
				
				System.out.println(" ========= teste de update ========= ");
				for (Client client2: clientList) {
					System.out.println(" Client id: "+client2.getIdClient()+" Nome completo:"+client2.getName()+
							" "+client2.getLastName()+
							" CPF: "+client2.getCpf());
				}
				
				assertTrue(true);
				
			}
			catch(Exception e){
				fail("Erro no teste shouldUpdateClient_1 "+e.getMessage() + e);
			}
				
		}
	
	
}
