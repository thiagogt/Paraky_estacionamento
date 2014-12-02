package br.com.parakyestacionamento.ManageBeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.RowEditEvent;

import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.modeloBD.ClientModelBD;

@ViewScoped
@ManagedBean(name="parentBean")
public class ParentBean {

	private Client clientSelected;
	private Client newParent;
	private int idClientSelected=0;
	private List<Client> parentList;
	private int idClientSelectedForFilter;
	
	public void filterParentList(ActionEvent event){
		if(idClientSelectedForFilter != 0){
			ClientModelBD parentModel = new ClientModelBD();	
			try {
				parentList =  parentModel.selectAllParentFromThisClient(idClientSelectedForFilter);
			} catch (SQLException e) {
				ParakyMessage.addMessage("Nao foi possivel gerar lista de familiares filtrados. Contate seu Administrador");
			}
		}
		else{
			parentList =null;
		}
	}
	
	public void saveNewParent(ActionEvent event){
		ClientModelBD parentModel = new ClientModelBD();
		try {
			String errorMessage = allTheFieldsAreCorrets();
			if(errorMessage != null)
				ParakyMessage.addErrorMessage(errorMessage);
			else{
				newParent.setIdOwnerParkingSpace(idClientSelected);
				parentModel.insert(newParent);
				ParakyMessage.addMessage("Cadastro realizado com sucesso!");
			}
		} catch (SQLException e) {
			ParakyMessage.addErrorMessage("Erro ao salvar familiar!"," Nao foi possivel inserir dado no banco de dados. Contate o administrador do sistema.");
			System.out.println("Erro ao inserir novo familiar: "+e.getMessage());
			System.out.println(e);
		}
	}
	
	private String allTheFieldsAreCorrets() throws SQLException {
		ClientModelBD model = new ClientModelBD();
		
		if(idClientSelected == 0)
			return "Escolha um cliente para ser responsavel por esse familiar.";
		
		if(model.verifyIfClientCPFExists(newParent.getCpf()))
			return "Esse CPF ja existe cadastrado na base de dados.";
		
				
		return null;
	}

	 public void onRowEdit(RowEditEvent event) {
		 
		 	ClientModelBD model = new ClientModelBD();
		 	Client parentEdited =  ((Client) event.getObject());
		 	String message = verifyIfEditClientIsCorrect(parentEdited);
		 	if(message!=null)
		 		ParakyMessage.addErrorMessage(message);
		 	else{
			 	try {
					model.update(parentEdited);
					ParakyMessage.addMessage("Familiar "+parentEdited.getName()+" "+parentEdited.getLastName()+" editado com sucesso!");
				} catch (SQLException e) {
					ParakyMessage.addErrorMessage("Erro ao editar familiar!","Contate o administrador do sistema.");
					System.out.println("Erro ao editar familiar:"+parentEdited.getName()+" "+parentEdited.getLastName()+" "+e.getMessage());
					System.out.println(e);			}
		 	}
		 	 
	    }
	
	private String verifyIfEditClientIsCorrect(Client parentEdited) {
		// TODO Auto-generated method stub
		return null;
	}


	public Client getClientSelected() {
		return clientSelected;
	}

	public void setClientSelected(Client clientSelected) {
		this.clientSelected = clientSelected;
	}

	public Client getNewParent() {
		if(newParent == null)
			newParent = new Client();
		return newParent;
	}

	public void setNewParent(Client newParent) {
		this.newParent = newParent;
	}

	public int getIdClientSelected() {
		return idClientSelected;
	}

	public void setIdClientSelected(int idClientSelected) {
		this.idClientSelected = idClientSelected;
	}

	public List<Client> getParentList() {
		if(parentList == null){
			ClientModelBD parentModel = new ClientModelBD();	
			try {
				parentList =  parentModel.selectAllParent();
			} catch (SQLException e) {
				ParakyMessage.addMessage("Nao foi possivel gerar lista de familiares. Contate seu Administrador");
			}
		}
		return parentList;
	}

	public void setParentList(List<Client> parentList) {
		this.parentList = parentList;
	}

	public int getIdClientSelectedForFilter() {
		return idClientSelectedForFilter;
	}

	public void setIdClientSelectedForFilter(int idClientSelectedForFilter) {
		this.idClientSelectedForFilter = idClientSelectedForFilter;
	}

}


