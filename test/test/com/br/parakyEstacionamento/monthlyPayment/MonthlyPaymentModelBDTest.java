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
	@Test
	public void shouldUpdateStatusMonthlyPaymentId_1_toTrue() throws  ClassNotFoundException, SQLException {
		
		try{
			
			MonthlyPaymentBDModel newModelBD = new MonthlyPaymentBDModel();
			newModelBD.updateDebt(1, true);
			MonthlyPayment monthlyPayment = newModelBD.select(1);
			System.out.println("Esse eh status do 1: "+monthlyPayment.getPaymentStatus()+" com a nova data pagamento: "+monthlyPayment.getPaymentDate().toString());
			assertTrue(true);
			
		}
		catch(Exception e){
			fail("Erro no teste shouldUpdateStatusMonthlyPaymentId_2_toTrue "+e.getMessage());
		}
		
	    
	}
	
	@Test
	public void shouldUpdateStatusMonthlyPaymentId_1_toFalse() throws  ClassNotFoundException, SQLException {
		
		try{
			
			MonthlyPaymentBDModel newModelBD = new MonthlyPaymentBDModel();
			newModelBD.updateDebt(1, false);
			MonthlyPayment monthlyPayment = newModelBD.select(1);
			System.out.println("Esse eh status do 1: "+monthlyPayment.getPaymentStatus()+" com a nova data pagamento: "+monthlyPayment.getPaymentDate().toString());
			assertTrue(true);
			
		}
		catch(Exception e){
			fail("Erro no teste shouldUpdateStatusMonthlyPaymentId_2_toFalse "+e.getMessage());
		}
		
	    
	}
	
	

}
