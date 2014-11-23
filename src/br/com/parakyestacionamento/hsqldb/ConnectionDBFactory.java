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

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

import br.com.parakyestacionamento.properties.AppProperties;

public class ConnectionDBFactory extends Server {

	public static Server hsqlServer = null;
	public static String createDataBaseFile;
	public static String populateDataBaseFile;
	public static String dataBaseBasePath; 
	public static String driver; 
	public static String url ;
	public static String username ;
	public static String password; 
	public static Connection connection;
	
	static{
		dataBaseBasePath = AppProperties.defaultProps.getProperty("baseFile");
		driver = AppProperties.defaultProps.getProperty("createDataBase.driver");
		url = AppProperties.defaultProps.getProperty("createDataBase.url");
		username = AppProperties.defaultProps.getProperty("createDataBase.username");
		password = AppProperties.defaultProps.getProperty("createDataBase.password");
	}
	
	public static void createDataBaseServer() throws IOException, SQLException, ClassNotFoundException, AclFormatException{

		
		String dataBaseBasePath = AppProperties.defaultProps.getProperty("baseFile");
        String dataBaseBaseName = dataBaseBasePath+AppProperties.defaultProps.getProperty("createDataBase.dataBaseName");
        String dataBaseBaseFullPath = AppProperties.defaultProps.getProperty("createDataBase.bdPath");
       
        HsqlProperties p = new HsqlProperties();
        p.setProperty("server.database.0", "file:"+dataBaseBaseFullPath);
        p.setProperty("server.dbname.0", dataBaseBaseName);
        p.setProperty("server.port", "9001");
        p.setProperty("hsqldb.lock_file", false);
        hsqlServer = new Server();
        hsqlServer.setProperties(p);
        hsqlServer.setLogWriter(null);
        hsqlServer.setSilent(true);
        hsqlServer.start();
        
	}
	
	
	
	public static Connection getDataBaseConnection() throws SQLException, ClassNotFoundException{
		
		
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		
       return  connection; // can through sql exception
      
	}

	public static void closeDataBaseServer() throws IOException, SQLException, ClassNotFoundException, AclFormatException{
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
				
				if(!databaseExists(connection)){
					CreateDB(connection);
					PopulateDB(connection);
				}
    		
        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
//		finally{
//			connection.close();
//		}
	}
	
 
	



	private static boolean databaseExists(Connection connection) throws SQLException {
		boolean dataBaseExist = false;
		try{
			connection =	getDataBaseConnection();
			ResultSet rs = null;
			rs = connection.prepareStatement("select * from version_bd").executeQuery();
		    rs.next();
		    dataBaseExist = true;
		    
		    
		}
		catch(Exception e){
			System.out.println("Nao existe tabela");
			System.out.println(e.getMessage());
			
		}
		finally{
			connection.close();	
		}
		return dataBaseExist;
	}


	private static void CreateDB(Connection connection) throws FileNotFoundException, SQLException, ClassNotFoundException {
		String dataBaseBasePath = AppProperties.defaultProps.getProperty("baseFile");
		createDataBaseFile = dataBaseBasePath+AppProperties.defaultProps.getProperty("createDataBase.ScriptPath");
		FileReader resourceAsStream = new FileReader(createDataBaseFile);
		executeScript(connection,resourceAsStream);
		AlteraDB(connection);
	}

	private static  void AlteraDB(Connection connection) throws FileNotFoundException, SQLException {
		String dataBaseBasePath = AppProperties.defaultProps.getProperty("baseFile");
		String alterDataBaseFile = dataBaseBasePath+AppProperties.defaultProps.getProperty("alterDataBase.ScriptPath");
		FileReader resourceAsStream = new FileReader(alterDataBaseFile);
		executeScript(connection,resourceAsStream);
		
	}
	private static void PopulateDB(Connection connection) throws FileNotFoundException, SQLException {
		String dataBaseBasePath = AppProperties.defaultProps.getProperty("baseFile");
		populateDataBaseFile = dataBaseBasePath+AppProperties.defaultProps.getProperty("populateDataBaseFile.ScriptPath");
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