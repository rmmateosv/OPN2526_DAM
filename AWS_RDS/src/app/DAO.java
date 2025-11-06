package app;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DAO {
	private Connection conexion;

	public DAO() {
		//Establecer conexión con la BD
		//configurada en .env
		try {
			//Obtener lo datos de acceso
			Properties datosC = new Properties();
			datosC.load(new FileInputStream(".env"));
			
			//Cargar la clase con la se va a trabajar en jdbc
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Conectar con la BD
			String url = "jdbc:mysql://"+
					datosC.getProperty("HOST")+
					":"+datosC.getProperty("PUERTO")+
					"/"+datosC.getProperty("NOMBREBD");
			
			conexion = DriverManager.getConnection(
					url, 
					datosC.getProperty("USUARIO"), 
					datosC.getProperty("PS"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	
}
