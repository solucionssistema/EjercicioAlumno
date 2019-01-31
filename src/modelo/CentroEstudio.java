package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class CentroEstudio{
	private IntegerProperty codigocentro;
	private StringProperty nombreCentro;

	public CentroEstudio(int codigocentro, String nombreCentro) { 
		this.codigocentro = new SimpleIntegerProperty(codigocentro);
		this.nombreCentro = new SimpleStringProperty(nombreCentro);
	}

	//Metodos atributo: codigocentro
	public int getCodigocentro() {
		return codigocentro.get();
	}
	public void setCodigocentro(int codigocentro) {
		this.codigocentro = new SimpleIntegerProperty(codigocentro);
	}
	public IntegerProperty CodigocentroProperty() {
		return codigocentro;
	}
	//Metodos atributo: nombreCentro
	public String getNombreCentro() {
		return nombreCentro.get();
	}
	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = new SimpleStringProperty(nombreCentro);
	}
	public StringProperty NombreCentroProperty() {
		return nombreCentro;
	}
	
	public static void llenarInformacion(Connection connection, ObservableList<CentroEstudio> lista){
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery("SELECT codigo_centro, nombre_estudio FROM tbl_centros_estudio;");
			while (resultado.next()){
				lista.add(new CentroEstudio(resultado.getInt("codigo_centro"), resultado.getString("nombre_estudio")));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al llenar información en centros de estudios.");
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return nombreCentro.get();
	}
}