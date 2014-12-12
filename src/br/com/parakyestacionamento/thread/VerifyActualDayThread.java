package br.com.parakyestacionamento.thread;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;

import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.dominio.MonthlyPayment;
import br.com.parakyestacionamento.dominio.MonthlyPaymentPerClient;
import br.com.parakyestacionamento.dominio.ParkingSpace;
import br.com.parakyestacionamento.modeloBD.ClientModelBD;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentBDModel;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentPerClientModelBD;
import br.com.parakyestacionamento.modeloBD.ParkingSpaceModelBD;


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
				searchAndChargeParkingSpaceThisMonth(client,monthlyPerClientList,actualDay);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao verificar devedores de hoje: "+e.getMessage());
			System.out.println(e);
		}
		
	}

	private boolean searchAndChargeParkingSpaceThisMonth(Client client, List<MonthlyPaymentPerClient> monthlyPerClientList,int actualDay) {
		ParkingSpaceModelBD modelSpace = new ParkingSpaceModelBD();
		
		try {
			List<ParkingSpace> parkingList = modelSpace.selectAllSpaceForThisClient(client.getIdClient());
			for (ParkingSpace parkingSpace : parkingList) {
				if(parkingSpace.getPayDay() == actualDay)
					if(thisParkingSpaceWasNotCharged(parkingSpace,monthlyPerClientList)){
						createMonthlyPaymentForThisParkingSpace(parkingSpace);
					}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar vagas do cliente id: "+client.getIdClient()+" = "+e.getMessage());
			System.out.println(e);		}
		return false;
		
	}

	private void createMonthlyPaymentForThisParkingSpace(
			ParkingSpace parkingSpace) {
		
		MonthlyPayment monthlyPayment = new MonthlyPayment(parkingSpace.getIdParkingSpace());
		monthlyPayment.setPaymentDate(Calendar.getInstance().getTime());
		monthlyPayment.setPaymentStatus("false");
		
		MonthlyPaymentBDModel modelMonthly = new MonthlyPaymentBDModel();
		try {
			modelMonthly.insert(monthlyPayment);
		} catch (SQLException e) {
			System.out.println("Erro ao inserir a mensalidade da vaga : "+parkingSpace.getIdParkingSpace()+" = "+e.getMessage());
			System.out.println(e);
		}
		
		
	}

	private boolean thisParkingSpaceWasNotCharged(ParkingSpace parkingSpace,
			List<MonthlyPaymentPerClient> monthlyPerClientList) {
		
		DateTime today = new DateTime(Calendar.getInstance().getTime());
		
		for (MonthlyPaymentPerClient monthlyPaymentPerClient : monthlyPerClientList) {
			if(monthlyPaymentPerClient.getIdParkingSpace() == parkingSpace.getIdParkingSpace()){
				
				DateTime monthlyDatePayment = new DateTime(monthlyPaymentPerClient.getPaymentDate());
				if(today.dayOfMonth() == monthlyDatePayment.dayOfMonth() && 
						today.monthOfYear() == monthlyDatePayment.monthOfYear() &&
						 	today.year() == monthlyDatePayment.year()){
					return false;
				}
				
			}
		}
		return true;
	}

	
}
