package br.com.parakyestacionamento.ManageBeans;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.RowEditEvent;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.DailyPayment;
import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.modeloBD.CarModelBD;
import br.com.parakyestacionamento.modeloBD.DailyPaymentModelBD;

@ViewScoped
@ManagedBean(name="dailyBean")
public class DailyBean {

	private Car newCar;
	private DailyPayment newDaily;
	private boolean chargedPerHour =false;
	private int idClosedTicket =0;
	
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
				dailyModel.insert(newDaily);
				ParakyMessage.addMessage("Cadastro realizado com sucesso!");
			}
		} catch (SQLException e) {
			ParakyMessage.addErrorMessage("Erro ao salvar diária!"," Nao foi possivel inserir dado no banco de dados. Contate o administrador do sistema.");
			System.out.println("Erro ao inserir novo diaria: "+e.getMessage());
			System.out.println(e);
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
				ParakyMessage.addErrorMessage("Ticket inexistente!"," Número de indentificação "+ idClosedTicket+" inexistente na base.");
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
			newDaily = new DailyPayment(new Date());
		return newDaily;
	}
	public void setNewDaily(DailyPayment newDaily) {
		this.newDaily = newDaily;
	}

	public boolean isChargedPerHour() {
		return chargedPerHour;
	}

	public void setChargedPerHour(boolean isChargedPerHour) {
		this.chargedPerHour = isChargedPerHour;
	}



	public int getIdClosedTicket() {
		return idClosedTicket;
	}



	public void setIdClosedTicket(int idClosedTicket) {
		this.idClosedTicket = idClosedTicket;
	}
	
	
}
