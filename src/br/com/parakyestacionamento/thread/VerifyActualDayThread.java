package br.com.parakyestacionamento.thread;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import mailService.MailService;

import org.apache.commons.mail.EmailException;
import org.joda.time.DateTime;

import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.dominio.MonthlyPayment;
import br.com.parakyestacionamento.dominio.MonthlyPaymentPerClient;
import br.com.parakyestacionamento.dominio.ParkingSpace;
import br.com.parakyestacionamento.modeloBD.ClientModelBD;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentBDModel;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentPerClientModelBD;
import br.com.parakyestacionamento.modeloBD.ParkingSpaceModelBD;
import br.com.parakyestacionamento.properties.AppProperties;


public class VerifyActualDayThread extends Thread{

	@Override
	public void run() {
		int hour = 60*1000;
		int definedHours = Integer.parseInt(AppProperties.defaultProps.getProperty("verificar.devedores.horas"));
		
		while(true){
			System.out.println(" verificando a data de hoje....");
			DateTime dt = new DateTime(Calendar.getInstance().getTime());  // current time
			int actualDay = dt.getDayOfMonth();
			createMonthlyPayment(actualDay);
			
			try {
				Thread.sleep(definedHours*hour);
			} catch (InterruptedException e) {
				System.out.println("Erro ao interromper thread de verificacao de devedores : "+e.getMessage());
				System.out.println(e);
			}
		}
			
	}

	public void createMonthlyPayment(int actualDay) {
		List<MonthlyPaymentPerClient> monthlyPerClientList = null;
		List<Client> clientList = null;
		
		MonthlyPaymentPerClientModelBD modelMonthlyPerClient = new MonthlyPaymentPerClientModelBD();
		ClientModelBD modelClient = new ClientModelBD();
		
		try {
			
			clientList = modelClient.selectAllOwners();
			monthlyPerClientList = modelMonthlyPerClient.selectAllDebtPerClient();
			
			for (Client client : clientList) {
				boolean montlhyCharged = searchAndChargeParkingSpaceThisMonth(client,monthlyPerClientList,actualDay);
				if(montlhyCharged)
					System.out.println("Mensalidades criadas para o cliente "+client.getName()+" "+client.getLastName());
				
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao verificar devedores de hoje: "+e.getMessage());
			System.out.println(e);
		}
		
	}

	public boolean searchAndChargeParkingSpaceThisMonth(Client client, List<MonthlyPaymentPerClient> monthlyPerClientList,int actualDay) {
		ParkingSpaceModelBD modelSpace = new ParkingSpaceModelBD();
		
		try {
			List<ParkingSpace> parkingList = modelSpace.selectAllSpaceForThisClient(client.getIdClient());
			for (ParkingSpace parkingSpace : parkingList) {
				if(parkingSpace.getPayDay() == actualDay)
					if(thisParkingSpaceWasNotCharged(parkingSpace,monthlyPerClientList)){
						createMonthlyPaymentForThisParkingSpace(parkingSpace);
						if(shouldSendAutomaticMail())
							sendPaymentEmail(client, parkingSpace);
						return true;
					}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar vagas do cliente id: "+client.getIdClient()+" = "+e.getMessage());
			System.out.println(e);		}
		return false;
		
	}

	private void sendPaymentEmail(Client client, ParkingSpace parkingSpace ) {
		
		DateTime dt = new DateTime(Calendar.getInstance().getTime());  // current time
		int actualDay = dt.getDayOfMonth();
		int actualMounth = dt.getMonthOfYear();
		
		String bank = AppProperties.defaultProps.getProperty("nome.banco");
		String count = AppProperties.defaultProps.getProperty("conta.banco");
		String agency = AppProperties.defaultProps.getProperty("agencia.banco");
		DecimalFormat fmt = new DecimalFormat("0.00");        
	    String cost = fmt.format(parkingSpace.getParkingSpaceCost())  ;
		String message = "Sr(a). " +client.getName()+" "+client.getLastName()+", \n\n" +
				"Conforme acordado com o estacionamento Paraky, todo dia "+actualDay+" será cobrada a mensalidade da sua vaga de valor de R$"+cost+
				".\nPor favor efetue o pagamento na seguinte conta:" +
				"\n\nBanco: " +bank+
				"\nAgência: " +agency+
				"\nConta: "+count+
				"\n\nEssa mensagem foi gerada automaticamente pelo sistema Paraky estacionamento. Apenas responda caso tenha alguma dúvida." +
				"\n\nO estacionamento Paraky agradece sua preferência.";
		
		String subject = "Mensalidade estacionamento Paraky "+actualDay+"/"+actualMounth;
		try {
			MailService.sendMail(message, subject, client.getEmail());
		} catch (EmailException e) {
			System.out.println("Nao foi possivel mandar email para : "+ client.getEmail());
			System.out.println("Motivo :"+e.getMessage());
			System.out.println(e);
		}
		
	}

	private boolean shouldSendAutomaticMail() {
		String shouldSendEmail = AppProperties.defaultProps.getProperty("mandar.email.automaticamente");
		if(shouldSendEmail.equals("sim"))
			return true;
		return false;
	}

	public void createMonthlyPaymentForThisParkingSpace(
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

	public boolean thisParkingSpaceWasNotCharged(ParkingSpace parkingSpace,
			List<MonthlyPaymentPerClient> monthlyPerClientList) {
		
		DateTime today = new DateTime(Calendar.getInstance().getTime());
		
		for (MonthlyPaymentPerClient monthlyPaymentPerClient : monthlyPerClientList) {
			if(monthlyPaymentPerClient.getIdParkingSpace() == parkingSpace.getIdParkingSpace()){
				
				DateTime monthlyDatePayment = new DateTime(monthlyPaymentPerClient.getPaymentDate());
				
				if(today.dayOfMonth().equals(monthlyDatePayment.dayOfMonth()) && today.monthOfYear().equals(monthlyDatePayment.monthOfYear()) && 	today.year().equals(monthlyDatePayment.year())){
					return false;
				}
				
			}
		}
		return true;
	}

	
}
