package com.br.parakyEstacionamento.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.parakyestacionamento.main.Main;

public class ParakyListener implements ServletContextListener {
    
	public void contextInitialized(ServletContextEvent event) {
	    System.out.println("Contexto iniciado...");
	    Main.main(null);
	}
  
    public void contextDestroyed(ServletContextEvent event) {
    	System.out.println("Contexto desligado...");
    }

}
