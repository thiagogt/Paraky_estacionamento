package test.com.br.parakyEstacionamento.client;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class ClientBDTest {

	@Test
	public void shouldConsultClient() throws  ClassNotFoundException, SQLException {
		
		Connection connection = null;
		try{
			connection =	ConnectionDBFactory.getDataBaseConnection();
			ResultSet rs = null;
			rs = connection.prepareStatement("select * from client").executeQuery();
		    rs.next();
		    
		    System.out.println(String.format("cliente cpf %s", rs.getString(4)));
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
