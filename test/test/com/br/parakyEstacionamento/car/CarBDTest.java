package test.com.br.parakyEstacionamento.car;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class CarBDTest {

	
	@Test
	public void shouldConsultCar() throws  ClassNotFoundException, SQLException {
		
		Connection connection = null;
		try{
			connection =	ConnectionDBFactory.getDataBaseConnection();
			ResultSet rs = null;
			
			rs = connection.prepareStatement("select * from car").executeQuery();
		    rs.next();
		    String carPlate = rs.getString(6);
		    
		    System.out.println(String.format("placa do carro %s", carPlate));
		    assertEquals("LCY1884", carPlate);
		}
		catch(Exception e){
			fail("Erro no teste shouldConsultCar "+e.getMessage());
		}
		finally{
			connection.close();	
		}
	    
	}
	
	@Test
	public void shouldSeeCarOwner() throws  ClassNotFoundException, SQLException {
		
		Connection connection = null;
		try{
			connection =	ConnectionDBFactory.getDataBaseConnection();
			ResultSet rs = null;
			rs = connection.prepareStatement("select * from car").executeQuery();
		    rs.next();
		    int idClient = rs.getInt(2);
		  
		    rs = connection.prepareStatement("select * from client where id_client="+idClient).executeQuery();
		    rs.next();
		    
		    System.out.println(String.format("Nome do dono: %s %s", rs.getString(2), rs.getString(3)));
		    assertTrue("", true);
		}
		catch(Exception e){
			fail("Erro no teste shouldSeeCarOwner "+e.getMessage());
		}
		finally{
			connection.close();	
		}
	    
	}
	
	
}
