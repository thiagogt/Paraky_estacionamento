package br.com.parakyestacionamento.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {
		public static Properties defaultProps;
		public static FileInputStream in;
		static{
			defaultProps = new Properties();
		    
			try {
				in = new FileInputStream("doc/app.properties");
				defaultProps.load(in);
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
			
		}
}

