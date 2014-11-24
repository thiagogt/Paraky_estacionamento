package br.com.parakyestacionamento.ManageBeans;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.model.CarModelBD;

@SessionScoped
@ManagedBean(name="carBean")
public class CarBean {

	@Inject
	private ClientBean clientBean;
	private Car newCar;
	
	
	
	public void saveNewCar(ActionEvent event){
		CarModelBD carModel = new CarModelBD();
		try {
			newCar.setIdClientCar(clientBean.getClientSelected().getIdClient());
			carModel.insert(newCar);
		} catch (SQLException e) {
			ParakyMessage.addMessage("Erro ao salvar carro! Nao foi possivel inserir dado no banco de dados.");
		}
	}
	
	public Car getNewCar() {
		if(newCar==null)
			newCar = new Car();
		return newCar;
	}

	public void setNewCar(Car newCar) {
		this.newCar = newCar;
	}

	public ClientBean getClientBean() {
		return clientBean;
	}

	public void setClientBean(ClientBean clientBean) {
		this.clientBean = clientBean;
	}
	
}
