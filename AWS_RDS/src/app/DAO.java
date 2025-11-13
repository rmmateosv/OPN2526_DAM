package app;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

	public boolean crearTarea(Tarea t) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Creamos la consulta insert con parámetros
			PreparedStatement c = 
					conexion.prepareStatement("INSERT into tareas "
						+ "values (default,?,?,now(),?,default)",
						Statement.RETURN_GENERATED_KEYS);
			//Rellenamos parámetros
			c.setString(1, t.getTitulo());
			c.setString(2, t.getDescripcion());
			c.setString(3, t.getPrioridad());
			//Ejecutar consulta
			int numReg=c.executeUpdate();
			if(numReg==1) {
				resultado=true;
				//Recuperar el id de la tarea creada
				ResultSet idS = c.getGeneratedKeys();
				if(idS.next()) {
					t.setId(idS.getInt(1));
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Tarea> obtenerTareas() {
		// TODO Auto-generated method stub
		ArrayList<Tarea> resultado = new ArrayList<Tarea>();
		try {
			Statement c = conexion.createStatement();
			ResultSet r = c.executeQuery("SELECT * from tareas");
			while(r.next()) {
				//Convertimos la fecha de MySQL a una marca de tiempo
				Timestamp ts = r.getTimestamp("fechaC");
				//Generar una fecha de tipo LocalDateTime
				LocalDateTime f = ts.toLocalDateTime();
				
				resultado.add(new Tarea(r.getInt("id"), 
						r.getString("titulo"), 
						r.getString("descripcion"),
						f, 
						r.getString("prioridad"), 
						r.getString("estado")));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Tarea obtenerTareas(int id) {
		// TODO Auto-generated method stub
		Tarea resultado = null;
		try {
			PreparedStatement c = 
					conexion.prepareStatement("SELECT * "
							+ "from tareas where id = ?");
			c.setInt(1, id);
			ResultSet r = c.executeQuery();
			if(r.next()) {
				//Convertimos la fecha de MySQL a una marca de tiempo
				Timestamp ts = r.getTimestamp("fechaC");
				//Generar una fecha de tipo LocalDateTime
				LocalDateTime f = ts.toLocalDateTime();
				
				resultado= new Tarea(r.getInt("id"), 
						r.getString("titulo"), 
						r.getString("descripcion"),
						f, 
						r.getString("prioridad"), 
						r.getString("estado"));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Tarea> obtenerTareas(String estado) {
		// TODO Auto-generated method stub
		ArrayList<Tarea> resultado = new ArrayList<Tarea>();
		try {
			PreparedStatement c = 
					conexion.prepareStatement("SELECT * "
							+ "from tareas where estado = ?");
			c.setString(1, estado);
			ResultSet r = c.executeQuery();
			while(r.next()) {
				//Convertimos la fecha de MySQL a una marca de tiempo
				Timestamp ts = r.getTimestamp("fechaC");
				//Generar una fecha de tipo LocalDateTime
				LocalDateTime f = ts.toLocalDateTime();
				
				resultado.add(new Tarea(r.getInt("id"), 
						r.getString("titulo"), 
						r.getString("descripcion"),
						f, 
						r.getString("prioridad"), 
						r.getString("estado"))
				);
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	
	
}
