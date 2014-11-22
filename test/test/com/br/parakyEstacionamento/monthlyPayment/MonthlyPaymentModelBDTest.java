package test.com.br.parakyEstacionamento.monthlyPayment;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import br.com.parakyestacionamento.dominio.MonthlyPayment;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentBDModel;

public class MonthlyPaymentModelBDTest {

	@Test
	public void shouldSelectAllMonthlyPayment() throws  ClassNotFoundException, SQLException {
		
		try{
			MonthlyPaymentBDModel newModelBD = new MonthlyPaymentBDModel();
			List<MonthlyPayment> MPList = newModelBD.selectAll();
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
	
	@Test
	public void shouldSelectMonthlyPaymentId_1() throws  ClassNotFoundException, SQLException {
		
		try{
			
			MonthlyPaymentBDModel newModelBD = new MonthlyPaymentBDModel();
			MonthlyPayment monthlyPayment = newModelBD.select(1);
			System.out.println("Esse eh status do 1: "+monthlyPayment.getPaymentStatus());
			assertTrue(true);
			
		}
		catch(Exception e){
			fail("Erro no teste shouldSelectMonthlyPaymentId_1 "+e.getMessage());
		}
		
	    
	}
	

}
