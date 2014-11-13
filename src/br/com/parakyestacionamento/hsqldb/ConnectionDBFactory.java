package br.com.parakyestacionamento.hsqldb;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.hsqldb.Server;

import br.com.parakyestacionamento.properties.AppProperties;

public class ConnectionDBFactory extends Server {

	public static Server hsqlServer = null;
	public static String createDataBaseFile;
	public static String populateDataBaseFile;
	
	public static void createDataBaseServer() throws IOException, SQLException, ClassNotFoundException{
		
		
        hsqlServer = new Server();
        hsqlServer.setLogWriter(null);
        hsqlServer.setSilent(true);
        hsqlServer.setDatabaseName(0, "ParakyEDB");
        hsqlServer.setDatabasePath(0, "file:doc/db/parakyDB");
        
	}
	
	
	public static void startDataBaseServer() throws IOException{
		hsqlServer.start();
		try{
			createDataBase();
			
		}
		catch(Exception e){
			System.out.println("Erro ao tentar criar Base de dados:");
			System.out.println(e.getMessage());
		}
   		
	}
	public static Connection getDataBaseConnection() throws SQLException, ClassNotFoundException{
		
		String driver = AppProperties.defaultProps.getProperty("createDataBase.driver");
		String url = AppProperties.defaultProps.getProperty("createDataBase.url");
		String username = AppProperties.defaultProps.getProperty("createDataBase.username");
		String password = AppProperties.defaultProps.getProperty("createDataBase.password");
		
		Class.forName(driver);
       return  DriverManager.getConnection(url, username, password); // can through sql exception
      
	}
	
	
	public static void closeDataBaseServer() throws IOException, SQLException, ClassNotFoundException{
		if(hsqlServer == null)
			createDataBaseServer();
		hsqlServer.stop();
       hsqlServer = null;
       
	}
	
	
	
	
	public static void createDataBase() throws IOException, SQLException{
		Connection connection = null;
        
		try {
			
				connection = getDataBaseConnection();
				makeConnectionAcceptOracleSyntax(connection);
				
				if(!databaseExists()){
					CreateDB(connection);
					PopulateDB(connection);
				}
    		
        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
		finally{
			connection.close();
		}
	}
	
 
	private static boolean databaseExists() {
		
		String dataBasePath = AppProperties.defaultProps.getProperty("populateDataBaseFile.dataBasePath");
		
		
		File f = new File(dataBasePath);
		if(f.exists() && !f.isDirectory())
			return true;
		return false;
	}


	private static void CreateDB(Connection connection) throws FileNotFoundException, SQLException, ClassNotFoundException {
		createDataBaseFile = AppProperties.defaultProps.getProperty("createDataBase.ScriptPath");
		FileReader resourceAsStream = new FileReader(createDataBaseFile);
		executeScript(connection,resourceAsStream);
	
	}

	private static void PopulateDB(Connection connection) throws FileNotFoundException, SQLException {
		populateDataBaseFile = AppProperties.defaultProps.getProperty("populateDataBaseFile.ScriptPath");
		FileReader resourceAsStream = new FileReader(populateDataBaseFile);
		executeScript(connection,resourceAsStream);
		
	}


	private static void executeScript(Connection connection,
			FileReader resourceAsStream) throws SQLException {
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
			try{
					connection.prepareStatement(comando).execute();
			}catch(SQLException e){
				System.out.println("Erro na query: " + comando);
				e.printStackTrace();
				throw e;
			}
		}
		scanner.close();

		//	connection.commit();
		//	if(dropa){
		//		connection.close();
		//		connection = null;
		//	}
		    
		    
		    
		  //testaConexao();
	}


	

	public static void testaConexao() throws  ClassNotFoundException, SQLException {
		
		Connection connection = null;
		
		try{
			connection =	getDataBaseConnection();
			ResultSet rs = null;
			rs = connection.prepareStatement("select * from sample_table").executeQuery();
		    rs.next();
		    
		    System.out.println(String.format("nome: %s, numero: %d", rs.getString(1), rs.getInt(2)));
		}
		catch(Exception e){
			System.out.println("Erro no teste");
			System.out.println(e.getMessage());
		}
		finally{
			connection.close();	
		}
	    
	}

	private static void makeConnectionAcceptOracleSyntax(Connection connection) throws SQLException {
		
		connection.prepareStatement("SET DATABASE SQL SYNTAX ORA TRUE").execute();
		
	}

	
	public Server getHsqlServer() {
		return hsqlServer;
	}
	public void setHsqlServer(Server newHsqlServer) {
		hsqlServer = newHsqlServer;
	}
}