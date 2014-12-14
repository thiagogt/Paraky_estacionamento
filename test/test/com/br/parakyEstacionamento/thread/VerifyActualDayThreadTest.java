package test.com.br.parakyEstacionamento.thread;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;
import org.junit.Before;
import org.junit.Test;

import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.dominio.MonthlyPayment;
import br.com.parakyestacionamento.dominio.MonthlyPaymentPerClient;
import br.com.parakyestacionamento.dominio.ParkingSpace;
import br.com.parakyestacionamento.modeloBD.ClientModelBD;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentBDModel;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentPerClientModelBD;
import br.com.parakyestacionamento.modeloBD.ParkingSpaceModelBD;
import br.com.parakyestacionamento.thread.VerifyActualDayThread;

public class VerifyActualDayThreadTest {


	public VerifyActualDayThread dayThread;
	public List<MonthlyPaymentPerClient> paymentMonthlyPerClientList;
	public ParkingSpace parkingSpace;
	public Date actualDate;
	
	@Before
	public void init(){
		

		actualDate  = Calendar.getInstance().getTime();
		
		parkingSpace = new ParkingSpace(1);
		parkingSpace.setIdParkingSpace(3);
		parkingSpace.setContractDate(actualDate);
		parkingSpace.setParkingSpaceCost(150.00);
		parkingSpace.setPayDay(14);
		parkingSpace.setTypeParkingSpace("carro");
		
		dayThread = new VerifyActualDayThread();
		MonthlyPaymentPerClientModelBD modelPerClient = new MonthlyPaymentPerClientModelBD();
		try {
			paymentMonthlyPerClientList = modelPerClient.selectAllDebtPerClient();
		} catch (SQLException e) {
			fail("Erro ao selecionar a lista de devedores no init() da classe VerifyActualDayThread: "+e);
		}
	}
	
	
	@Test
	public void shouldSetThisParkingWasNotCharged() {
		
		Boolean result = dayThread.thisParkingSpaceWasNotCharged(parkingSpace, paymentMonthlyPerClientList);
		assert(result);
	}
	@Test
	public void shouldSetThisParkingSpaceWasAlreadyCharged(){
		
		
		MonthlyPayment monthlyPayment = new MonthlyPayment(1);
		monthlyPayment.setPaymentDate(actualDate);
		monthlyPayment.setPaymentStatus("false");
		
		MonthlyPaymentBDModel modelPayment = new MonthlyPaymentBDModel();
		try {
			modelPayment.insert(monthlyPayment);
		} catch (SQLException e) {
			fail("Nao conseguiu inserir mensalidade no teste shouldSetThisParkingSpaceWasAlreadyCharged : "+e);
		}
		
		Boolean result = dayThread.thisParkingSpaceWasNotCharged(parkingSpace, paymentMonthlyPerClientList);
		
		
		if(result == false)
			assert(true);
		else
			fail("Retornou valor errado");
		
		
	}
	
	@Test
	public void shouldCreateMonthlyPaymentForThisParkingSpace(){
		
		
		dayThread.createMonthlyPaymentForThisParkingSpace(parkingSpace);
		
		MonthlyPaymentPerClientModelBD modelPerClient = new MonthlyPaymentPerClientModelBD();
		try {
			paymentMonthlyPerClientList = modelPerClient.selectAllDebtPerClient();
			
			for (MonthlyPaymentPerClient paymentMonthlyPerClient : paymentMonthlyPerClientList) {
				System.out.println("Cliente: "+paymentMonthlyPerClient.getName()+" "+paymentMonthlyPerClient.getLastName()+
						" eMAIL: "+paymentMonthlyPerClient.getEmail()+
						" idVaga: "+paymentMonthlyPerClient.getIdParkingSpace()+
						" idMensalidade : "+ paymentMonthlyPerClient.getIdMonthlyPayment()+
						" data do pagamento: "+paymentMonthlyPerClient.getPaymentDate());
			}
			
		} catch (SQLException e) {
			fail("Erro ao selecionar a lista de devedores no shouldCreateMonthlyPaymentForThisParkingSpace() da classe VerifyActualDayThread: "+e);
		}		
	}
	
	@Test
	public void shouldSearchAndChargeParkingSpaceThisMonth(){
		
		DateTime time  = new DateTime(actualDate);
		int actualDay = time.getDayOfMonth();
		ClientModelBD modelClient = new ClientModelBD();
		ParkingSpaceModelBD parkingModel = new ParkingSpaceModelBD();
		Client client_1;
		try {
			try{
				parkingModel.insert(parkingSpace);
			}
			catch(Exception e){
				System.out.println("Problema ao inserir nova vaga para o cliente 1 no teste shouldSearchAndChargeParkingSpaceThisMonth: "+e.getMessage());
			}
			client_1 = modelClient.select(1);
			
			
			
			dayThread.searchAndChargeParkingSpaceThisMonth(client_1, paymentMonthlyPerClientList, actualDay);
			
			MonthlyPaymentPerClientModelBD modelPerClient = new MonthlyPaymentPerClientModelBD();
			try {
				paymentMonthlyPerClientList = modelPerClient.selectAllDebtPerClient();
				
				for (MonthlyPaymentPerClient paymentMonthlyPerClient : paymentMonthlyPerClientList) {
					System.out.println("Cliente: "+paymentMonthlyPerClient.getName()+" "+paymentMonthlyPerClient.getLastName()+
							" eMAIL: "+paymentMonthlyPerClient.getEmail()+
							" idVaga: "+paymentMonthlyPerClient.getIdParkingSpace()+
							" idMensalidade : "+ paymentMonthlyPerClient.getIdMonthlyPayment()+
							" data do pagamento: "+paymentMonthlyPerClient.getPaymentDate());
				}
				
			} catch (SQLException e) {
				fail("Erro ao selecionar a lista de devedores no shouldSearchAndChargeParkingSpaceThisMonth() da classe VerifyActualDayThread: "+e);
			}
			
		} catch (SQLException e) {
			fail("Erro ao buscar por cliente 1: "+e.getMessage());
		}
		
		
	}
}
