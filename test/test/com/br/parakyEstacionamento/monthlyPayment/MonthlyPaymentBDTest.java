package test.com.br.parakyEstacionamento.monthlyPayment;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class MonthlyPaymentBDTest {

	@Test
	public void shouldConsultMonthlyPayment() throws  ClassNotFoundException, SQLException {
		
		Connection connection = null;
		try{
			connection =	ConnectionDBFactory.getDataBaseConnection();
			ResultSet rs = null;
			
			rs = connection.prepareStatement("select * from monthly_payment").executeQuery();
		    rs.next();
		    
		    System.out.println(String.format("Status do pagamento da vaga : %s", rs.getString(3)));
		    assertTrue("", true);
		}
		catch(Exception e){
			fail("Erro no teste shouldConsultMonthlyPayment "+e.getMessage());
		}
		finally{
			connection.close();	
		}
	    
	}
	
	@Test
	public void shouldSeeOwnerMonthlyPayment() throws  ClassNotFoundException, SQLException {
		
		Connection connection = null;
		try{
			connection =	ConnectionDBFactory.getDataBaseConnection();
			ResultSet rs = null;
			rs = connection.prepareStatement("select * from monthly_payment").executeQuery();
		    rs.next(); 
			int idParkingClient = rs.getInt(2);
			rs = connection.prepareStatement("select client.name,client.last_name from client  join parking_space on client.id_client = parking_space.id_client_owner and parking_space.id_parking_space="+idParkingClient).executeQuery();
		    rs.next();
		    
		    System.out.println(String.format("Status do pagamento da vaga : %s %s", rs.getString(1), rs.getString(2)));
		    assertTrue("", true);
		}
		catch(Exception e){
			fail("Erro no teste shouldConsultMonthlyPayment "+e.getMessage());
		}
		finally{
			connection.close();	
		}
	    
	}

}
