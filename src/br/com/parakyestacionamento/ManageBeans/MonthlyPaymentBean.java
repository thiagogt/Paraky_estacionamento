package br.com.parakyestacionamento.ManageBeans;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.mail.EmailException;

import mailService.MailService;

import br.com.parakyestacionamento.dominio.MonthlyPaymentPerClient;
import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentBDModel;
import br.com.parakyestacionamento.modeloBD.MonthlyPaymentPerClientModelBD;
import br.com.parakyestacionamento.properties.AppProperties;

@ViewScoped
@ManagedBean(name="monthlyPayment")
public class MonthlyPaymentBean {

	private List<MonthlyPaymentPerClient> debtList;
	
	public void sendMail(MonthlyPaymentPerClient clientDebtSelected){
		
		String bank = AppProperties.defaultProps.getProperty("nome.banco");
		String count = AppProperties.defaultProps.getProperty("conta.banco");
		String agency = AppProperties.defaultProps.getProperty("agencia.banco");
		
		SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = spf.format(clientDebtSelected.getPaymentDate()); 
		
		//TODO: JOgar isso para uma parte de configuracao do site. Nao faz sentido o dono nao poder mudar sua mensagem de email. 
		String message = "Sr(a). "+clientDebtSelected.getName()+" "+clientDebtSelected.getLastName()+",\n\nPor favor, entre em contato com o Sr. Joao pois a data de pagamento de sua vaga venceu no dia" +
				" "+dateString+"\n\n.O valor de R$"+clientDebtSelected.getParkingSpaceCost()+" da sua mensalidade deve ser pago no:" +
				"\n\nBanco: " +bank+
				"\nAgência: " +agency+
				"\nConta: "+count+
				"\n\nEssa mensagem foi gerada automaticamente pelo sistema Paraky estacionamento. Apenas responda caso tenha alguma dúvida." +
				"\n\nO estacionamento Paraky agradece sua preferência.";
		
		String subject = "Pagamento mensal do estacionamento Paraky";
		
		
		String receiver = clientDebtSelected.getEmail();
		try {
			MailService.sendMail(message, subject, receiver);
			ParakyMessage.addMessage("Foi enviado a cobranca para o email "+receiver);
		} catch (EmailException e) {
			ParakyMessage.addErrorMessageSub("Erro ao enviar email para "+receiver, "Por favor, contate seu administrador");
			System.out.println( "Erro ao enviar email para "+receiver+" : "+e.getMessage());
			System.out.println(e);
		}
		
	}
	
	
	public List<MonthlyPaymentPerClient> selectAllMounthPayers(){
		
		MonthlyPaymentPerClientModelBD monthlyPaymentBdPerClientModel = new MonthlyPaymentPerClientModelBD();
		List<MonthlyPaymentPerClient> actualMounthPaymentList = new ArrayList<MonthlyPaymentPerClient>();
		try {
			List<MonthlyPaymentPerClient> allPayers = monthlyPaymentBdPerClientModel.selectAllPayersClient();
			for (MonthlyPaymentPerClient monthlyPaymentPerClient : allPayers) {
				if(theClientPaysthisMounth(monthlyPaymentPerClient)){
					actualMounthPaymentList.add(monthlyPaymentPerClient);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar por mensalidades desse mes: "+e.getMessage());
			System.out.println(e);
		}
		return actualMounthPaymentList;
	}

	
	private boolean theClientPaysthisMounth(MonthlyPaymentPerClient monthlyPaymentPerClient) {
		
		 int actualMounth = Calendar.getInstance().get(Calendar.MONTH);
		 int actualYear = Calendar.getInstance().get(Calendar.YEAR);
		 
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(monthlyPaymentPerClient.getPaymentDate());
		 int monthFromPayment = cal.get(Calendar.MONTH);
		 int yaerFromPayment = cal.get(Calendar.YEAR);
		 
		 if(actualMounth == monthFromPayment)
			 if(actualYear == yaerFromPayment)
				 return true;
		return false;
	}


	private String actualMounth;
	
	public void selectAllClientsDebt(){
		
		MonthlyPaymentPerClientModelBD monthlyPaymentBdPerClientModel = new MonthlyPaymentPerClientModelBD();
		try {
			debtList = monthlyPaymentBdPerClientModel.selectAllDebtPerClient();
			
			
		} catch (SQLException e) {
			ParakyMessage.addErrorMessageSub("Erro ao buscar por devedores.", "Por favor, contate seu administrador");
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
				System.out.println("Nao foi possivel salvar lista de devedores: "+e.getMessage());
				System.out.println(e);
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


	public List<MonthlyPaymentPerClient> getActualMounthPaymentList() {
		return selectAllMounthPayers();
	}


}
