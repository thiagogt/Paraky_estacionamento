package com.br.parakyEstacionamento.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;
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
