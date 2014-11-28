package br.com.parakyestacionamento.ManageBeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.RowEditEvent;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.modeloBD.CarModelBD;

@ViewScoped
@ManagedBean(name="carBean")
public class CarBean {

	private int idClientSelected=0;
	private Car newCar;
	private List<Car> carList;
	
	
	public void saveNewCar(ActionEvent event){
		CarModelBD carModel = new CarModelBD();
		try {
			String errorMessage = allTheFieldsAreCorrets();
			if(errorMessage != null)
				ParakyMessage.addErrorMessage(errorMessage);
			else{
				newCar.setIdClientCar(idClientSelected);
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
		
		
		if(idClientSelected == 0)
			return "Escolha um cliente para ser dono desse novo carro.";
		if(model.verifyIfCarPlateExists(newCar.getCarPlate()))
			return "Essa placa ja existe cadastrada na base de dados.";
		
				
		return null;
	}

	
	 public void onRowEdit(RowEditEvent event) {
		 
		 	CarModelBD model = new CarModelBD();
		 	Car carEdited =  ((Car) event.getObject());
		 	String message = verifyIfEditCarIsCorrect(carEdited);
		 	if(message!=null)
		 		ParakyMessage.addErrorMessage(message);
		 	else{
			 	try {
					model.update(carEdited);
					ParakyMessage.addMessage(" "+carEdited.getCarBrand()+" "+carEdited.getModel()+" editado com sucesso!");
				} catch (SQLException e) {
					ParakyMessage.addErrorMessage("Erro ao editar carro!","Contate o administrador do sistema.");
					System.out.println("Erro ao inserir novo carro: "+e.getMessage());
					System.out.println(e);			}
		 	}
		 	 
	    }
	
	private String verifyIfEditCarIsCorrect(Car carEdited) {
		// TODO Auto-generated method stub
		return null;
	}

	public Car getNewCar() {
		if(newCar==null)
			newCar = new Car();
		return newCar;
	}

	public void setNewCar(Car newCar) {
		this.newCar = newCar;
	}

	public int getIdClientSelected() {
		return idClientSelected;
	}

	public void setIdClientSelected(int idClientSelected) {
		this.idClientSelected = idClientSelected;
	}

	public List<Car> getCarList() {
		if(carList == null){
			CarModelBD model = new CarModelBD();
			try {
				carList = model.selectAll();
			} catch (SQLException e) {
				ParakyMessage.addErrorMessage("Erro ao consultar todos os carros!","Contate o administrador do sistema.");
				System.out.println("Erro ao inserir novo carro: "+e.getMessage());
				System.out.println(e);
			}
		}
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}



	
}
