package test.com.br.parakyEstacionamento.adress;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class AdressBDTest {

	@Test
	public void shouldConsultAdress() throws  ClassNotFoundException, SQLException {
		
		Connection connection = null;
		try{
			connection =	ConnectionDBFactory.getDataBaseConnection();
			ResultSet rs = null;
			
			rs = connection.prepareStatement("select * from adress").executeQuery();
		    rs.next();
		    
		    System.out.println(String.format("%s %s %s,%s %s", rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
		    assertTrue("", true);
		}
		catch(Exception e){
			fail("Erro no teste shouldConsultAdress "+e.getMessage());
		}
		finally{
			connection.close();	
		}
	    
	}

}
