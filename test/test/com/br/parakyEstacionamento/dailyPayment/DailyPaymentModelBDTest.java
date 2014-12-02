package test.com.br.parakyEstacionamento.dailyPayment;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.dominio.DailyPayment;
import br.com.parakyestacionamento.modeloBD.ClientModelBD;
import br.com.parakyestacionamento.modeloBD.DailyPaymentModelBD;

public class DailyPaymentModelBDTest {

	@Test
	public void shouldSelectAllDailyPayment() throws  ClassNotFoundException, SQLException {
		
		try{
			DailyPaymentModelBD  model = new DailyPaymentModelBD();
			List<DailyPayment> dailyList = model.selectAll();
			for (DailyPayment daily : dailyList) {
				System.out.println("Esse eh o id daily : "+daily.getIdDailyPayment()
						+" o id do carro "+daily.getIdCarCharged()+" custo: "+daily.getCost()
						+" o checkin "+daily.getCheckin().toString()+" o checkout "+daily.getCheckout());
			}
			assertTrue(true);
			
		}
		catch(Exception e){
			fail("Erro no teste shouldSelectAllDailyPayment "+e.getMessage());
		}
		
	    
	}

	@Test
	public void shouldInsertADailyOnCar_1() throws  ClassNotFoundException, SQLException {
		
		try{
			DailyPaymentModelBD  model = new DailyPaymentModelBD();

			Calendar cal = Calendar.getInstance();
			DailyPayment newdaily = new DailyPayment(cal.getTime());
			
			newdaily.setIdCarCharged(1);
			newdaily.setCost(100.30);
			newdaily.setCheckout(cal.getTime());
			
			model.insert(newdaily);
			
			List<DailyPayment> dailyList = model.selectAll();
			
			for (DailyPayment daily : dailyList) {
				System.out.println("Esse eh o id daily : "+daily.getIdDailyPayment()
						+" o id do carro "+daily.getIdCarCharged()+" custo: "+daily.getCost()
						+" o checkin "+daily.getCheckin().toString()+" o checkout "+daily.getCheckout());
			}
			assertTrue(true);
		}
		catch(Exception e){
			fail("Erro no teste shouldInsertADailyOnCar_1 "+e.getMessage() + e);
		}
			
		}
		

		@Test
		public void shouldUpdateDaily_1() throws  ClassNotFoundException, SQLException {
			
			try{
				DailyPaymentModelBD  model = new DailyPaymentModelBD();

				Calendar cal = Calendar.getInstance();
				DailyPayment newdaily = new DailyPayment(cal.getTime());
				newdaily.setIdDailyPayment(1);
				newdaily.setCheckout(cal.getTime());
				
				model.updateCheckout(newdaily);
				
				List<DailyPayment> dailyList = model.selectAll();
				System.out.println("============== UPDATE checkout====================");
				for (DailyPayment daily : dailyList) {
					if(daily.getIdDailyPayment()==1)
						System.out.println("Esse eh o id daily : "+daily.getIdDailyPayment()
							+" o id do carro "+daily.getIdCarCharged()+" custo: "+daily.getCost()
							+" o checkin "+daily.getCheckin().toString()+" o checkout "+daily.getCheckout());
				}
				assertTrue(true);				
			}
			catch(Exception e){
				fail("Erro no teste shouldUpdateDaily_1 "+e.getMessage() + e);
			}
				
		}


}
