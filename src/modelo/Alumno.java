package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Alumno{
	private IntegerProperty codigoAlumno;
	private StringProperty nombre;
	private StringProperty apellido;
	private IntegerProperty edad;
	private StringProperty genero;
	private Date fechaIngreso;
	private CentroEstudio centroEstudio;
	private Carrera carrera;

	public Alumno(int codigoAlumno, String nombre, String apellido, 
int edad, String genero, Date fechaIngreso, 
CentroEstudio centroEstudio, Carrera carrera) { 
		this.codigoAlumno = new SimpleIntegerProperty(codigoAlumno);
		this.nombre = new SimpleStringProperty(nombre);
		this.apellido = new SimpleStringProperty(apellido);
		this.edad = new SimpleIntegerProperty(edad);
		this.genero = new SimpleStringProperty(genero);
		this.fechaIngreso = fechaIngreso;
		this.centroEstudio = centroEstudio;
		this.carrera = carrera;
	}

	//Metodos atributo: codigoAlumno
	public int getCodigoAlumno() {
		return codigoAlumno.get();
	}
	public void setCodigoAlumno(int codigoAlumno) {
		this.codigoAlumno = new SimpleIntegerProperty(codigoAlumno);
	}
	public IntegerProperty CodigoAlumnoProperty() {
		return codigoAlumno;
	}
	//Metodos atributo: nombre
	public String getNombre() {
		return nombre.get();
	}
	public void setNombre(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}
	public StringProperty NombreProperty() {
		return nombre;
	}
	//Metodos atributo: apellido
	public String getApellido() {
		return apellido.get();
	}
	public void setApellido(String apellido) {
		this.apellido = new SimpleStringProperty(apellido);
	}
	public StringProperty ApellidoProperty() {
		return apellido;
	}
	//Metodos atributo: edad
	public int getEdad() {
		return edad.get();
	}
	public void setEdad(int edad) {
		this.edad = new SimpleIntegerProperty(edad);
	}
	public IntegerProperty EdadProperty() {
		return edad;
	}
	//Metodos atributo: genero
	public String getGenero() {
		return genero.get();
	}
	public void setGenero(String genero) {
		this.genero = new SimpleStringProperty(genero);
	}
	public StringProperty GeneroProperty() {
		return genero;
	}
	//Metodos atributo: fechaIngreso
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	//Metodos atributo: centroEstudio
	public CentroEstudio getCentroEstudio() {
		return centroEstudio;
	}
	public void setCentroEstudio(CentroEstudio centroEstudio) {
		this.centroEstudio = centroEstudio;
	}
	//Metodos atributo: carrera
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	
	public int guardarRegistro(Connection connection) {
		try {
			PreparedStatement instruccion = connection.prepareStatement("INSERT INTO tbl_alumnos(nombre, apellido, edad, genero, fecha_ingreso,codigo_carrera, codigo_centro) VALUES ( ?, ?, ?, ?, ?, ?, ?)");
			
			//Elementos parametrizados
			instruccion.setString(1, nombre.get());
			instruccion.setString(2, apellido.get());
			instruccion.setInt(3, edad.get());
			instruccion.setString(4, genero.get());
			instruccion.setDate(5, fechaIngreso);
			instruccion.setInt(6, carrera.getCodigoCarrera());
			instruccion.setInt(7, centroEstudio.getCodigocentro());

			//Ejecutar
			instruccion.executeUpdate();//La cantidad de registros afectados al realizar la inserción 1 si es correcta 0 si es incorrecta la insercción.
			return instruccion.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error al guardar el alumno en la base de datos");
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public int actualizarRegistro(Connection connection) {
		try {
			PreparedStatement instruccion = connection.prepareStatement("UPDATE tbl_alumnos SET  nombre=?, apellido=?, edad=?, genero=?, fecha_ingreso=?, codigo_carrera=?, codigo_centro=? WHERE codigo_alumno = ?");
			
			//Elementos parametrizados
			instruccion.setString(1, nombre.get());
			instruccion.setString(2, apellido.get());
			instruccion.setInt(3, edad.get());
			instruccion.setString(4, genero.get());
			instruccion.setDate(5, fechaIngreso);
			instruccion.setInt(6, carrera.getCodigoCarrera());
			instruccion.setInt(7, centroEstudio.getCodigocentro());
			instruccion.setInt(8, codigoAlumno.get());
			
			//Ejecutar
			instruccion.executeUpdate();//La cantidad de registros afectados al realizar la inserción 1 si es correcta 0 si es incorrecta la insercción.
			return instruccion.executeUpdate();
		 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error al editar el alumno en la base de datos");
			e.printStackTrace();
			return 0;
		}
	}
	
	public int eliminarRegistro(Connection connection) {
		try {
			PreparedStatement instruccion = connection.prepareStatement("DELETE FROM tbl_alumnos WHERE codigo_alumno = ?");
			instruccion.setInt(1, codigoAlumno.get());
			return instruccion.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar el alumno en la base de datos");
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void llenarInformacionAlumnos(Connection connection, ObservableList<Alumno> lista){
		try {
			Statement instruccion = connection.createStatement();//Statement no sirve cuando hay que mandar datos por parametros para un insert
			ResultSet resultado = instruccion.executeQuery("SELECT A.codigo_alumno, A.nombre, A.apellido, A.edad, A.genero, A.fecha_ingreso, A.codigo_carrera, A.codigo_centro, B.nombre_carrera, B.cantidad_asignaturas, C.nombre_estudio FROM tbl_alumnos A INNER JOIN tbl_carerras B ON(A.codigo_carrera = B.codigo_carrera) INNER JOIN tbl_centros_estudio C ON (A.codigo_centro = C.codigo_centro)");
			while(resultado.next()) {
				lista.add(
						new Alumno(
							resultado.getInt("codigo_alumno"),
							resultado.getString("nombre"),
							resultado.getString("apellido"),
							resultado.getInt("edad"),
							resultado.getString("genero"),
							resultado.getDate("fecha_ingreso"),
							new CentroEstudio(resultado.getInt("codigo_centro"),resultado.getString("nombre_estudio")),
							new Carrera(resultado.getInt("codigo_carrera"),resultado.getString("nombre_carrera"),resultado.getInt("cantidad_asignaturas"))
						)
					);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	}
