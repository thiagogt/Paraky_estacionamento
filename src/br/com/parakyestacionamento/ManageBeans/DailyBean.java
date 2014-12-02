package br.com.parakyestacionamento.ManageBeans;

import java.sql.SQLException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.RowEditEvent;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.DailyPayment;
import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.modeloBD.CarModelBD;

@ViewScoped
@ManagedBean(name="dailyBean")
public class DailyBean {

	private Car newCar;
	private DailyPayment newDaily;
	private boolean isChargedPerHour;
	
	public void saveNewDaily(ActionEvent event){
		CarModelBD carModel = new CarModelBD();
		try {
			String errorMessage = allTheFieldsAreCorrets();
			if(errorMessage != null)
				ParakyMessage.addErrorMessage(errorMessage);
			else{
				carModel.insert(newCar);
				ParakyMessage.addMessage("Cadastro realizado com sucesso!");
			}
		} catch (SQLException e) {
			ParakyMessage.addErrorMessage("Erro ao salvar carro!"," Nao foi possivel inserir dado no banco de dados. Contate o administrador do sistema.");
			System.out.println("Erro ao inserir novo carro: "+e.getMessage());
			System.out.println(e);
		}
	}
	
	private String allTheFieldsAreCorrets() throws SQLException {
		CarModelBD model = new CarModelBD();
		
		
		if(model.verifyIfCarPlateExists(newCar.getCarPlate()))
			return "Essa placa ja existe cadastrada na base de dados.";
		
				
		return null;
	}

	
	 public void onRowEdit(RowEditEvent event) {
		 
		 	CarModelBD model = new CarModelBD();
		 	Car carEdited =  ((Car) event.getObject());
		 	try {
				model.update(carEdited);
				ParakyMessage.addMessage(" "+carEdited.getCarBrand()+" "+carEdited.getModel()+" editado com sucesso!");
			} catch (SQLException e) {
				ParakyMessage.addErrorMessage("Erro ao editar carro!","Contate o administrador do sistema.");
				System.out.println("Erro ao inserir novo carro: "+e.getMessage());
				System.out.println(e);			}
		 	 
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
		return isChargedPerHour;
	}

	public void setChargedPerHour(boolean isChargedPerHour) {
		this.isChargedPerHour = isChargedPerHour;
	}
	
	
}
