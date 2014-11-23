package br.com.parakyestacionamento.ManageBeans;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.parakyestacionamento.dominio.MonthlyPaymentPerClient;
import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentBDModel;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentPerClientModelBD;

@SessionScoped
@ManagedBean(name="monthlyPayment")
public class MonthlyPaymentBean {

	private List<MonthlyPaymentPerClient> debtList;
	private String actualMounth;
	
	public void selectAllClientsDebt(){
		
		MonthlyPaymentPerClientModelBD monthlyPaymentBdPerClientModel = new MonthlyPaymentPerClientModelBD();
		try {
			debtList = monthlyPaymentBdPerClientModel.selectAllDebtPerClient();
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar por devedores: "+e.getMessage());
			System.out.println(e);
		}
	}

	public void saveDebtList(ActionEvent event){
		
		
		MonthlyPaymentBDModel monthlyPaymentBdModel = new MonthlyPaymentBDModel();
		for (MonthlyPaymentPerClient debtClient : debtList) {
			try {
				monthlyPaymentBdModel.updateDebt(debtClient.getIdMonthlyPayment(), debtClient.getPaymentStatus());
			} catch (SQLException e) {
				ParakyMessage.addMessage("Nao foi possivel salvar lista. Problema com a divida do cliente "+debtClient.getName()+" "+debtClient.getLastName());
			}
		}
		ParakyMessage.addMessage("Lista salva com sucesso!");
		debtList = null;
	}

	public List<MonthlyPaymentPerClient> getDebtList() {
		if(debtList == null)
			selectAllClientsDebt();
		return debtList;
	}


	public void setDebtList(List<MonthlyPaymentPerClient> debtList) {
		this.debtList = debtList;
	}


	public String getActualMounth() {
		if(actualMounth == null)
			actualMounth = Integer.toString(Calendar.MONTH);
		return actualMounth;
	}


	public void setActualMounth(String actualMounth) {
		this.actualMounth = actualMounth;
	}


	
	
}
