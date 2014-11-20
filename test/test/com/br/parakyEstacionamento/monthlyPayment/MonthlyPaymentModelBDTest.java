package test.com.br.parakyEstacionamento.monthlyPayment;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import br.com.parakyestacionamento.dominio.MonthlyPayment;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentBDModel;

public class MonthlyPaymentModelBDTest {

	@Test
	public void shouldSelectAllMonthlyPayment() throws  ClassNotFoundException, SQLException {
		
		try{
			MonthlyPaymentBDModel newModelBD = new MonthlyPaymentBDModel();
			List<Object> MPList = newModelBD.selectAll();
			for (Object monthlyPaymentObject : MPList) {
				MonthlyPayment monthlyPayment = (MonthlyPayment)monthlyPaymentObject;
				System.out.println("Esse eh a idMensalidade : "+monthlyPayment.getIdMonthlyPayment()+" idVaga: "+monthlyPayment.getIdParkingSpace()+" data pagamento: "+monthlyPayment.getPaymentDate().toString()+" status: "+monthlyPayment.getPaymentStatus());
			}
			assertTrue(true);
			
		}
		catch(Exception e){
			fail("Erro no teste shouldSelectAllMonthlyPayment "+e.getMessage());
		}
		
	    
	}
	

}
