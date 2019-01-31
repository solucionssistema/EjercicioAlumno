package modelo;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
	
	public void guardarRegistro() {
		
	}
	
	public void actualizarRegistro() {
		
	}
	
	public void eliminarRegistro() {
		
	}
}