package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	
	private Connection connection;
    String url ="jdbc:postgresql://localhost:5432/db_alumnos";
	private String usuario = "postgres";
	private String contrasena = "root";
	
	
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void establecerConexion() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, usuario, contrasena);
		} catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Hay un error en la conexión a la base de datos.");
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Hay un error en el controlador.");
			e.printStackTrace();
		}
	}
	
	//Para no sobrepasar el umbral del gestor de la base de datos.
	public void cerrarConexion() {
		try {
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Hay un error al cerrar la conexión.");
			e.printStackTrace();
		}
	}
	
}
