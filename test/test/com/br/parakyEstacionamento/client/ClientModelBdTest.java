package test.com.br.parakyEstacionamento.client;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.dominio.MonthlyPayment;
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

}
