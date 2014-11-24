package br.com.parakyestacionamento.ManageBeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.model.CarModelBD;
import br.com.parakyestacionamento.model.ClientModelBD;

@SessionScoped
@ManagedBean(name="clientBean")
public class ClientBean {

	
	private List<Client> clientList;
	private Client clientSelected;
	
	public List<Client> getClientList(){
		if(clientList == null){
			ClientModelBD clientModel = new ClientModelBD();	
			try {
				clientList =  clientModel.selectAll();
			} catch (SQLException e) {
				ParakyMessage.addMessage("Nao foi possivel gerar lista de clientes. Contate seu Administrador");
			}
		}
		return clientList;
	}
	
	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}

	public Client getClientSelected() {
		return clientSelected;
	}

	public void setClientSelected(Client clientSelected) {
		this.clientSelected = clientSelected;
	}

}
