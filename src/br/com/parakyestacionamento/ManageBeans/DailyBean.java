package br.com.parakyestacionamento.ManageBeans;

import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


import br.com.parakyestacionamento.domain.TicketParaky;
import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.DailyPayment;
import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.modeloBD.CarModelBD;
import br.com.parakyestacionamento.modeloBD.DailyPaymentModelBD;
import br.com.parakyestacionamento.modeloBD.TicketModelBD;
import br.com.parakyestacionamento.printer.PrinterAWT;
import br.com.parakyestacionamento.properties.AppProperties;

@ViewScoped
@ManagedBean(name="dailyBean")
public class DailyBean {

	private Car newCar;
	private DailyPayment newDaily;
	private int idClosedTicket =0;
	private List<TicketParaky> ticketList;
	private String costPerDay = null;
	private String costPerHour = null;
	
	public void saveNewDaily(ActionEvent event){
		CarModelBD carModel = new CarModelBD();
		DailyPaymentModelBD dailyModel = new DailyPaymentModelBD();
		
		try {
			String errorMessage = allTheFieldsAreCorrets();
			if(errorMessage != null)
				ParakyMessage.addErrorMessage(errorMessage);
			else{
				int idCar = thisCarExistAtBD();
				if(idCar == 0)
					idCar = carModel.insert(newCar);
				else
					ParakyMessage.addMessage("Placa já cadastrada no sistema.");
				
				newDaily.setIdCarCharged(idCar);
				newDaily.setCheckin(Calendar.getInstance().getTime());
				if(newDaily.isChargedPerHour()){
					newDaily.setCost(getHourCost());
				}
				else
					newDaily.setCost(getDayCost());
				
				dailyModel.insert(newDaily);
				
								
				PrinterAWT printer = new PrinterAWT();
				try {
					printer.printNewDailyTicketForCheckin(newCar, newDaily);
					ParakyMessage.addMessage("Diária cadastrada com sucesso.");
				} catch (PrinterException e) {
					ParakyMessage.addErrorMessage("Erro ao imprimir ticket!"," Contate o administrador do sistema.");
					System.out.println("Erro ao imprimir nova diaria: "+e.getMessage());
					System.out.println(e);
				}
			}
		} catch (Exception e) {
			ParakyMessage.addErrorMessage("Erro ao salvar diária!"," Nao foi possivel inserir dado no banco de dados. Contate o administrador do sistema.");
			System.out.println("Erro ao inserir novo diaria: "+e.getMessage());
			System.out.println(e);
		}
	}
	
	private double getDayCost() {
		return Double.valueOf(AppProperties.defaultProps.getProperty("custo.diaria"));
	}

	private double getHourCost() {
		return Double.valueOf(AppProperties.defaultProps.getProperty("custo.porHora"));
	}

	public void closeTicket(ActionEvent event){
		
		if(idClosedTicket == 0){
			ParakyMessage.addMessage("Digite o número de identificação do ticket!");
		}
		else{
			DailyPaymentModelBD dailyModel = new DailyPaymentModelBD();
			CarModelBD carModel = new CarModelBD();
			
			try {
					DailyPayment daily = dailyModel.select(idClosedTicket);
					Car car = carModel.select(daily.getIdCarCharged());
					PrinterAWT printer = new PrinterAWT();
					try {
						printer.printTicketValueForCheckout(car, daily);
						ParakyMessage.addMessage("Diária fechada com sucesso.");
					} catch (PrinterException e) {
						ParakyMessage.addErrorMessage("Erro ao imprimir ticket!"," Contate o administrador do sistema.");
						System.out.println("Erro ao imprimir nova diaria: "+e.getMessage());
						System.out.println(e);
					}
			} catch (SQLException e) {
				ParakyMessage.addErrorMessage("Erro ao salvar diária!"," Nao foi possivel inserir dado no banco de dados. Contate o administrador do sistema.");
				System.out.println("Erro ao inserir novo diaria: "+e.getMessage());
				System.out.println(e);
			}
		}
	}
	
	private String allTheFieldsAreCorrets() throws SQLException {
		return null;
	}

	private int thisCarExistAtBD() throws SQLException{
		CarModelBD model = new CarModelBD();
		
		
		return model.verifyIfCarPlateExists(newCar.getCarPlate());
			
	}
	public void searchForTicket(ActionEvent event){
		try {
			DailyPayment ticket = thisTicketExist(idClosedTicket); 
			if(ticket == null)
				ParakyMessage.addErrorMessage("Ticket inexistente!"," NÃºmero de indentificaÃ§Ã£o "+ idClosedTicket+" inexistente na base.");
			else{
				newDaily = ticket; 
				newCar = searchForTicketCar();
			}
		} catch (SQLException e) {
			ParakyMessage.addErrorMessage("Erro ao buscar ticket!"," Nao foi possivel inserir dado no banco de dados. Contate o administrador do sistema.");
			System.out.println("Erro ao inserir novo diaria: "+e.getMessage());
			System.out.println(e);
		}
	}
	
	private Car searchForTicketCar() throws SQLException {
		CarModelBD model = new CarModelBD();
		Car car = new Car();
		
		car = model.select(newDaily.getIdCarCharged());
		return car;
	}



	private DailyPayment thisTicketExist(int idClosedTicket) throws SQLException {
		DailyPaymentModelBD model= new DailyPaymentModelBD();
		DailyPayment daily = model.select(idClosedTicket);
		
		return daily;
	}
	public Car getNewCar() {
		if(newCar == null)
			newCar = new Car();
		return newCar;
	}
	public void setNewCar(Car newCar) {
		this.newCar = newCar;
	}
	public DailyPayment getNewDaily() {
		if(newDaily == null)
			newDaily = new DailyPayment(Calendar.getInstance().getTime());
		return newDaily;
	}
	public void setNewDaily(DailyPayment newDaily) {
		this.newDaily = newDaily;
	}


	public int getIdClosedTicket() {
		return idClosedTicket;
	}



	public void setIdClosedTicket(int idClosedTicket) {
		this.idClosedTicket = idClosedTicket;
	}

	public List<TicketParaky> getTicketList() {
		if(ticketList == null){
			TicketModelBD model = new TicketModelBD(); 
			try {
				ticketList = model.selectAll();
			} catch (SQLException e) {
				ParakyMessage.addErrorMessage("Erro ao buscar lista de ticket!","Contate o administrador do sistema.");
				System.out.println("Erro ao buscar lista de ticket: "+e.getMessage());
				System.out.println(e);
			}
		}
		return ticketList;
	}

	public void setTicketList(List<TicketParaky> ticketList) {
		this.ticketList = ticketList;
	}

	
	public String getCostPerHour() {
		if(costPerHour == null)
			costPerHour = AppProperties.defaultProps.getProperty("custo.porHora");
		return costPerHour;
	}


	public String getCostPerDay() {
		if(costPerDay == null)
			costPerDay = AppProperties.defaultProps.getProperty("custo.diaria"); 
		return costPerDay;
	}

	public void setCostPerDay(String costPerDay) {
		this.costPerDay = costPerDay;
	}

	public void setCostPerHour(String costPerHour) {
		this.costPerHour = costPerHour;
	}
	
}
