package br.com.parakyestacionamento.ManageBeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.primefaces.event.RowEditEvent;

import br.com.parakyestacionamento.dominio.Adress;
import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.modeloBD.AdressModelBD;
import br.com.parakyestacionamento.modeloBD.CarModelBD;
import br.com.parakyestacionamento.modeloBD.ParkingSpaceModelBD;

@ManagedBean(name="adressBean")
public class AdressBean {
	
	private int idClientSelected = 0;
	private Adress newAdress;
	private List<Adress> adressList;

	
	
	
	public void saveNewAdress(ActionEvent event){
	
		AdressModelBD model = new AdressModelBD();
		
		String errorMessage = allTheFieldsAreCorrets();
		if(errorMessage != null)
			ParakyMessage.addErrorMessage(errorMessage);
		else{
			newAdress.setCountry("Brasil");
			newAdress.setIdClientAdress(idClientSelected);
			try {
				model.insert(newAdress);
				ParakyMessage.addMessage("Endereco salvo com sucesso!");
			} catch (SQLException e) {
				ParakyMessage.addErrorMessageSub("Erro ao salvar endereco!"," Nao foi possivel inserir dado no banco de dados. Contate o administrador do sistema.");
				System.out.println("Erro ao inserir novo endereco: "+e.getMessage());
				System.out.println(e);
			}
		}
	}


	private String allTheFieldsAreCorrets() {
		if(idClientSelected == 0)
			return "Um cliente deve ser escolhido para cadastrar esse endereco.";
		return null;
	}

	public void filterClientList(ActionEvent event){
		
		if(idClientSelected != 0){
			
			AdressModelBD model = new AdressModelBD();
			
			try {
				adressList = model.selectAllAdressForThisClient(idClientSelected);
			} catch (SQLException e) {
				ParakyMessage.addErrorMessageSub("Erro ao filtrar lista de enderecos!"," Contate o administrador do sistema.");
				System.out.println("Erro ao filtrar lista de endereco do cliente de id "+idClientSelected+" : "+e.getMessage());
				System.out.println(e);
			}
		}
		else{
			adressList = null;
		}
	}
	
	
	 public void onRowEdit(RowEditEvent event) {
		 
		 	AdressModelBD model = new AdressModelBD();
		 	Adress adressEdited =  ((Adress) event.getObject());
		 	String message = verifyIfEditCarIsCorrect(adressEdited);
		 	if(message!=null)
		 		ParakyMessage.addErrorMessage(message);
		 	else{
			 	try {
					model.update(adressEdited);
					ParakyMessage.addMessage(" Endereco do cliente "+adressEdited.getClientName()+" editado com sucesso!");
				} catch (SQLException e) {
					ParakyMessage.addErrorMessageSub("Erro ao editar endereco!","Contate o administrador do sistema.");
					System.out.println("Erro ao editar endereco: "+e.getMessage());
					System.out.println(e);			}
		 	}
		 	 
	    }
	
	
	private String verifyIfEditCarIsCorrect(Adress adressEdited) {
		// TODO Auto-generated method stub
		return null;
	}


	public int getIdClientSelected() {
		return idClientSelected;
	}


	public void setIdClientSelected(int idClientSelected) {
		this.idClientSelected = idClientSelected;
	}


	public Adress getNewAdress() {
		if(newAdress == null){
			newAdress = new Adress(idClientSelected);
		}
		return newAdress;
	}


	public void setNewAdress(Adress newAdress) {
		this.newAdress = newAdress;
	}


	public List<Adress> getAdressList() {
		if(adressList == null){
			AdressModelBD model = new AdressModelBD();
			try {
				adressList = model.selectAll();
			} catch (SQLException e) {
				ParakyMessage.addErrorMessageSub("Erro ao buscar por lista de enderecos!"," Contate o administrador do sistema.");
				System.out.println("Erro ao buscar por lista de enderecos: "+e.getMessage());
				System.out.println(e);
			}
		}
			
		
		return adressList;
	}


	public void setAdressList(List<Adress> adressList) {
		this.adressList = adressList;
	}
	
	
	
}
