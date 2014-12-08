package br.com.parakyestacionamento.dominio;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ParakyMessage {

	 public static void addMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	 
	 public static void addErrorMessageSub(String title,String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title,summary);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	 
	 public static void addErrorMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
}
