package br.com.parakyestacionamento.ManageBeans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.parakyestacionamento.dominio.ParakyMessage;
import br.com.parakyestacionamento.properties.AppProperties;


@ViewScoped
@ManagedBean(name="loginBean")
public class LoginBean {
	
	private String login;
	private String password;
	
	
	public void makeLogin(ActionEvent event){
		
		String correctLogin = AppProperties.defaultProps.getProperty("email.login");
		String correctPassword = AppProperties.defaultProps.getProperty("senha.login");
		
		if(correctLogin.equals(login)){
			if(correctPassword.equals(password)){
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("homeDebtList.jsf");
				} catch (IOException e) {
					ParakyMessage.addErrorMessageSub("Nao foi possivel realizar o login","Contate seu administrador");
					System.out.println("Nao foi possivel realizar o login: "+e.getMessage());
					System.out.println(e);
				}
			}
			else{
				ParakyMessage.addErrorMessage("Senha incorreta");
			}
		}
		else{
			ParakyMessage.addErrorMessage("Email incorreto");
		}
		
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
}
