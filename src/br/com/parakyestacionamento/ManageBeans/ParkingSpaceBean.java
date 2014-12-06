package br.com.parakyestacionamento.ManageBeans;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.RowEditEvent;

import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.dominio.ParkingSpace;
import br.com.parakyestacionamento.modeloBD.ParkingSpaceModelBD;

@ViewScoped
@ManagedBean(name="parkingBean")
public class ParkingSpaceBean {
	
	private ParkingSpace newParkingSpace;
	private int  idClientSelected = 0;
	private List<ParkingSpace> parkingList;
	
	
	public void saveNewParkingSpace(ActionEvent event){
		ParkingSpaceModelBD model = new ParkingSpaceModelBD();
		try {
			String errorMessage = allTheFieldsAreCorrets();
			if(errorMessage != null)
				ParakyMessage.addErrorMessage(errorMessage);
			else{
				newParkingSpace.setIdClientOwner(idClientSelected);
				newParkingSpace.setContractDate(Calendar.getInstance().getTime());
				
				model.insert(newParkingSpace);
				ParakyMessage.addMessage("Cadastro realizado com sucesso!");
			}
		} catch (SQLException e) {
			ParakyMessage.addErrorMessage("Erro ao salvar vaga!"," Nao foi possivel inserir dado no banco de dados. Contate o administrador do sistema.");
			System.out.println("Erro ao inserir nova vaga: "+e.getMessage());
			System.out.println(e);
		}
	}

	public void filterClientList(ActionEvent event){
		
	}

	private String allTheFieldsAreCorrets() {
		if(idClientSelected == 0)
			return "Escolha um cliente para ser dono dessa vaga.";
		if(newParkingSpace.getParkingSpaceCost() == 0)
			return "Defina um custo para a mensalidade.";
		if(newParkingSpace.getPayDay() == 0)
			return "Defina um dia do mes para ser cobrada a mensalidade.";
		
		return null;
	}


	public void onRowEdit(RowEditEvent event) {
		 
	 	ParkingSpaceModelBD model = new ParkingSpaceModelBD();
	 	ParkingSpace parkingEdited =  ((ParkingSpace) event.getObject());
	 	String message = verifyIfEditParkingSpaceIsCorrect(parkingEdited);
	 	if(message!=null)
	 		ParakyMessage.addErrorMessage(message);
	 	else{
		 	try {
				model.update(parkingEdited);
				ParakyMessage.addMessage(" Vaga "+parkingEdited.getIdParkingSpace()+" editada com sucesso!");
			} catch (SQLException e) {
				ParakyMessage.addErrorMessage("Erro ao editar vaga!","Contate o administrador do sistema.");
				System.out.println("Erro ao inserir nova vaga: "+e.getMessage());
				System.out.println(e);			}
	 	}
	 	 
    }

	
	private String verifyIfEditParkingSpaceIsCorrect(ParkingSpace parkingEdited) {
		// TODO Auto-generated method stub
		return null;
	}


	public ParkingSpace getNewParkingSpace() {
		if(newParkingSpace == null)
			newParkingSpace  =  new ParkingSpace(idClientSelected);
		return newParkingSpace;
	}


	public void setNewParkingSpace(ParkingSpace newParkingSpace) {
		this.newParkingSpace = newParkingSpace;
	}


	public int getIdClientSelected() {
		return idClientSelected;
	}


	public void setIdClientSelected(int idClientSelected) {
		this.idClientSelected = idClientSelected;
	}


	public List<ParkingSpace> getParkingList() {
		if(parkingList == null){
			ParkingSpaceModelBD model = new ParkingSpaceModelBD();
			try {
				parkingList = model.selectAll();
			} catch (SQLException e) {
				System.out.println("Erro ao buscar por lista de vagas: "+e.getMessage());
				System.out.println(e);
			}
		}
			
		return parkingList;
	}


	public void setParkingList(List<ParkingSpace> parkingList) {
		this.parkingList = parkingList;
	}


	
	
	
	

}
