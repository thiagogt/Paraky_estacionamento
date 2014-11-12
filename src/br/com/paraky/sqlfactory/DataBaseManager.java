package br.com.paraky.sqlfactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.hsqldb.Server;

import br.com.paraky.properties.AppProperties;

public class DataBaseManager extends Server {

	public static Server hsqlServer = null;
	public static String createDataBaseFile;
	
	public static void createDataBase() throws IOException{
		
        createDataBaseFile = AppProperties.defaultProps.getProperty("createDataBase.ScriptPath");
		ResultSet rs = null;
		
		try {
			
			Connection connection = getDataBaseConnection();
			makeConnectionAcceptOracleSyntax(connection);
				
    		FileReader resourceAsStream = new FileReader(createDataBaseFile);
    			
//    			logger.debug("Executando comandos SQL do arquivo [" + createDataBaseFile + "]...");
    			Scanner scanner = new Scanner(resourceAsStream);
    			// separa os comandos SQL contidos no arquivo por ";"
    			scanner.useDelimiter(";");
    			while (scanner.hasNext()) {
    				String comando = scanner.next();
    				// elimina quebras de linha dos comandos SQL
    				comando = comando.replaceAll("\n", "").replaceAll("\r", "");
    				if (comando.equals("")) {
    					continue;
    				}
    				System.out.println("Comando: " + comando);
//    				logger.info(descricao + " - Executando comando SQL: [" + comando + "]...");
    				try{
    						connection.prepareStatement(comando).execute();
    				}catch(SQLException e){
    					System.out.println("Erro na query: " + comando);
    					e.printStackTrace();
    					throw e;
    				}
    			}
    			scanner.close();
    		
    		
    		
//    		connection.commit();
//    		if(dropa){
//    			connection.close();
//    			connection = null;
//    		}
            
            
            
            
//     EXEMPLO DE CHAMADA DE QUERY       
//            
//                         connection.prepareStatement("create table barcodes (id integer, barcode varchar(20) not null);").execute();
//            connection.prepareStatement("insert into CartaAtendimento (cod_atendimento, status, descricao)" 
//                    + "values (1, 'k','legal');").execute();
////            
//            // query from the db
//            rs = connection.prepareStatement("select cod_atendimento, status, descricao  from CartaAtendimento;").executeQuery();
//            rs.next();
//            
//           
//            System.out.println(String.format("cod: %d, Status: %s, Decricao: %s", rs.getInt(1), rs.getString(2),rs.getString(3)));
//     
        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        
       
        
        // end of stub code for in/out stub
	}
	
	private static void makeConnectionAcceptOracleSyntax(Connection connection) throws SQLException {
		
		connection.prepareStatement("SET DATABASE SQL SYNTAX ORA TRUE").execute();
		
	}

	public static void createDataBaseServer() throws FileNotFoundException{
	
         hsqlServer = new Server();
         hsqlServer.setLogWriter(null);
         hsqlServer.setSilent(true);
         hsqlServer.setDatabaseName(0, "SimuladorMFDB");
         hsqlServer.setDatabasePath(0, "mem:SimuladorDB");
         
         hsqlServer.start();
      
	}
	
	public static Connection getDataBaseConnection() throws SQLException, ClassNotFoundException{
		
		String driver = AppProperties.defaultProps.getProperty("createDataBase.driver");
		String url = AppProperties.defaultProps.getProperty("createDataBase.url");
		String username = AppProperties.defaultProps.getProperty("createDataBase.username");
		String password = AppProperties.defaultProps.getProperty("createDataBase.password");
		
		Class.forName(driver);
        return DriverManager.getConnection(url, username, password); // can through sql exception
       
	}
	
	
	public static void closeDataBaseServer(){
		
		hsqlServer.stop();
        hsqlServer = null;
        
	}
	
	public Server getHsqlServer() {
		return hsqlServer;
	}
	public void setHsqlServer(Server newHsqlServer) {
		hsqlServer = newHsqlServer;
	}
}