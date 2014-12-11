package br.com.parakyestacionamento.thread;

import java.sql.SQLException;
import java.util.List;

import org.joda.time.DateTime;

import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.dominio.MonthlyPaymentPerClient;
import br.com.parakyestacionamento.modeloBD.ClientModelBD;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentPerClientModelBD;


public class VerifyActualDayThread extends Thread{

	static int payDay = 0;
	
	@Override
	public void run() {
		
		System.out.println(" verificando a data de hoje....");
		DateTime dt = new DateTime();  // current time
		int actualDay = dt.getDayOfMonth();
			if(payDay != actualDay || payDay == 0){
				payDay = actualDay;
				createMonthlyPayment(actualDay);
			}
			
			
	}

	private void createMonthlyPayment(int actualDay) {
		List<MonthlyPaymentPerClient> monthlyPerClientList = null;
		List<Client> clientList = null;
		
		MonthlyPaymentPerClientModelBD modelMonthlyPerClient = new MonthlyPaymentPerClientModelBD();
		ClientModelBD modelClient = new ClientModelBD();
		
		try {
			
			clientList = modelClient.selectAllOwners();
			monthlyPerClientList = modelMonthlyPerClient.selectAllDebtPerClient();
			
			for (Client client : clientList) {
				
				if(todayIsPayDay(client));
					if(thisClientWasNotAlreadyChargedToday(client))
			}
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
