package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import modelo.Carrera;
import modelo.CentroEstudio;
import modelo.Conexion;

public class FormularioAlumnosController implements Initializable{
	
	//SE ENLAZAN CON LA VISTA INTERFAZ GRÁFICA DE USUARIO GUI
	@FXML private ComboBox<Carrera> cmbCarrera;
	@FXML private ComboBox<CentroEstudio>  cmbCentroEstudio;
	
	//Colecciones de datos
	private ObservableList<Carrera> listaCarreras;//Al utilizar ObservableList cualquier cambio se veria reflejado automáticamente en el cmbox
	private ObservableList<CentroEstudio> listaCentrosEstidos;
	private Conexion conexion;	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new Conexion();
		conexion.establecerConexion();
		listaCarreras = FXCollections.observableArrayList();//Inicializa la clase
		listaCentrosEstidos=FXCollections.observableArrayList();
		Carrera.llenarInformacion(conexion.getConnection(), listaCarreras);
		cmbCarrera.setItems(listaCarreras);
		CentroEstudio.llenarInformacion(conexion.getConnection(),listaCentrosEstidos);
		cmbCentroEstudio.setItems(listaCentrosEstidos);
		conexion.cerrarConexion();//Nunca dejar una conexión abierta
	}
	
	
} 
