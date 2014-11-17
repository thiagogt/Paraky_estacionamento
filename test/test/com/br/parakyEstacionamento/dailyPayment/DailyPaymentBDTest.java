package test.com.br.parakyEstacionamento.dailyPayment;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class DailyPaymentBDTest {

	@Test
	public void shouldConsultDailyPayment() throws  ClassNotFoundException, SQLException {
		
		Connection connection = null;
		try{
			connection =	ConnectionDBFactory.getDataBaseConnection();
			ResultSet rs = null;
			rs = connection.prepareStatement("select * from daily_payment").executeQuery();
		    rs.next();
		    
		    System.out.println(String.format("Pagamento de R$ %f. Checkin: %s. Checkout: %s", rs.getDouble(3),rs.getString(4),rs.getString(5)));
		    assertTrue("", true);
		}
		catch(Exception e){
			fail("Erro no teste shouldConsultClient "+e.getMessage());
		}
		finally{
			connection.close();	
		}
	    
	}

}
