package br.com.parakyestacionamento.ManageBeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.RowEditEvent;

import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.model.ClientModelBD;

@ViewScoped
@ManagedBean(name="clientBean")
public class ClientBean {

	
	private List<Client> clientList;
	private Client clientSelected;
	private Client newClient;
	
	
	public void saveNewClient(ActionEvent event){
		ClientModelBD clientModel = new ClientModelBD();
		try {
			String errorMessage = allTheFieldsAreCorrets();
			if(errorMessage != null)
				ParakyMessage.addErrorMessage(errorMessage);
			else{
				newClient.setIdOwnerParkingSpace(0);
				clientModel.insert(newClient);
				ParakyMessage.addMessage("Cadastro realizado com sucesso!");
			}
		} catch (SQLException e) {
			ParakyMessage.addErrorMessage("Erro ao salvar cliente!"," Nao foi possivel inserir dado no banco de dados. Contate o administrador do sistema.");
			System.out.println("Erro ao inserir novo cliente: "+e.getMessage());
			System.out.println(e);
		}
	}
	
	private String allTheFieldsAreCorrets() throws SQLException {
		ClientModelBD model = new ClientModelBD();
		
		
		if(model.verifyIfClientCPFExists(newClient.getCpf()))
			return "Esse CPF ja existe cadastrado na base de dados.";
		
				
		return null;
	}

	 public void onRowEdit(RowEditEvent event) {
		 
		 	ClientModelBD model = new ClientModelBD();
		 	Client clientEdited =  ((Client) event.getObject());
		 	String message = verifyIfEditClientIsCorrect(clientEdited);
		 	if(message!=null)
		 		ParakyMessage.addErrorMessage(message);
		 	else{
			 	try {
					model.update(clientEdited);
					ParakyMessage.addMessage("Cliente "+clientEdited.getName()+" "+clientEdited.getLastName()+" editado com sucesso!");
				} catch (SQLException e) {
					ParakyMessage.addErrorMessage("Erro ao editar cliente!","Contate o administrador do sistema.");
					System.out.println("Erro ao editar cliente:"+clientEdited.getName()+" "+clientEdited.getLastName()+" "+e.getMessage());
					System.out.println(e);			}
		 	}
		 	 
	    }
	
	private String verifyIfEditClientIsCorrect(Client clientEdited) {
		// TODO Auto-generated method stub
		return null;
	}

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

	public Client getNewClient() {
		if(newClient == null)
			newClient = new Client();
		return newClient;
	}

	public void setNewClient(Client newCLient) {
		this.newClient = newCLient;
	}

}
