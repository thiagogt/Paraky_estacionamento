package test.com.br.parakyEstacionamento.parkingSpace;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class ParkingSpaceBDTest {

	@Test
	public void shouldConsultParkingSpace() throws  ClassNotFoundException, SQLException {
		
		Connection connection = null;
		try{
			connection =	ConnectionDBFactory.getDataBaseConnection();
			ResultSet rs = null;
			
			rs = connection.prepareStatement("select * from parking_space").executeQuery();
		    rs.next();
		    
		    System.out.println(String.format("Custo da vaga R$ %f", rs.getDouble(6)));
		    assertTrue("", true);
		}
		catch(Exception e){
			fail("Erro no teste shouldConsultParkingSpace "+e.getMessage());
		}
		finally{
			connection.close();	
		}
	    
	}
	
	@Test
	public void shouldSeeOwnerOfParkingSpace() throws  ClassNotFoundException, SQLException {
		
		Connection connection = null;
		try{
			connection =	ConnectionDBFactory.getDataBaseConnection();
			ResultSet rs = null;
			
			rs = connection.prepareStatement("select * from parking_space").executeQuery();
		    rs.next();
		    int idOwnerSpace = rs.getInt(2);
		    
		    rs = connection.prepareStatement("select * from client where id_client="+idOwnerSpace).executeQuery();
		    rs.next();
		    
		    
		    
		    
		    System.out.println(String.format("Dono da vaga %s %s", rs.getString(2),rs.getString(3)));
		    assertTrue("", true);
		}
		catch(Exception e){
			fail("Erro no teste shouldConsultParkingSpace "+e.getMessage());
		}
		finally{
			connection.close();	
		}
	    
	}
	

}
