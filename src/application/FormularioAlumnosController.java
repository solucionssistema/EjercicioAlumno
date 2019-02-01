package application;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Alumno;
import modelo.Carrera;
import modelo.CentroEstudio;
import modelo.Conexion;

public class FormularioAlumnosController implements Initializable{
	//Enlazar columnas con la interface gráfica de usuario.
	@FXML private TableColumn<Alumno,String> clmnnombre;
	@FXML private TableColumn<Alumno,String> clmnapellido;
	@FXML private TableColumn<Alumno,Number> clmnedad;
	@FXML private TableColumn<Alumno,String> clmngenero;
	@FXML private TableColumn<Alumno,Date> clmnfechaIngreso;
	@FXML private TableColumn<Alumno,CentroEstudio> clmncentroEstudio;
	@FXML private TableColumn<Alumno,Carrera> clmncarrera;
	
	//SE ENLAZAN CON LA VISTA INTERFAZ GRÁFICA DE USUARIO GUI
	@FXML private TextArea txtCodigo;
	@FXML private TextArea txtNombre;
	@FXML private TextArea txtApellido;
	@FXML private TextArea txtEdad;
	@FXML private RadioButton rbtFemenino;
	@FXML private RadioButton rbtMasculino;
	@FXML private DatePicker dtpkrFecha;
	
	
	@FXML private ComboBox<Carrera> cmbCarrera;
	@FXML private ComboBox<CentroEstudio>  cmbCentroEstudio;
	
	//Enlazo la tabla
	@FXML private TableView<Alumno> tblViewAlumnos;//Enlazo el tableview con la vista
	@FXML private TableColumn<Alumno, String> clmnNombre;
	@FXML private TableColumn<Alumno, String> clmnApellido;
	@FXML private TableColumn<Alumno, Integer> clmnEdad;
	@FXML private TableColumn<Alumno, String> clmnGenero;
	@FXML private TableColumn<Alumno, Date> clmnFechaIngreso;
	@FXML private TableColumn<Alumno, String> clmnCarrera;
	@FXML private TableColumn<Alumno, String> clmnCentroEstudio;
	@FXML private Button btnGuardar;
	@FXML private Button btnEliminar;
	@FXML private Button btnActualizar;
	
	
	
	
	//Colecciones de datos
	private ObservableList<Carrera> listaCarreras;//Al utilizar ObservableList cualquier cambio se veria reflejado automáticamente en el cmbox
	private ObservableList<CentroEstudio> listaCentrosEstidos;//Observable List para almacenar los centros de estudio
	private ObservableList<Alumno> listaAlumnos;
	private Conexion conexion;	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new Conexion();
		conexion.establecerConexion();
		
		//Inicializar listas
		listaCarreras = FXCollections.observableArrayList();//Inicializa la clase
		listaCentrosEstidos=FXCollections.observableArrayList();
		listaAlumnos=FXCollections.observableArrayList();//Inicializa la lsita alumnos
		
		//Llenar listas
		Carrera.llenarInformacion(conexion.getConnection(), listaCarreras);
		CentroEstudio.llenarInformacion(conexion.getConnection(),listaCentrosEstidos);
		Alumno.llenarInformacionAlumnos(conexion.getConnection(),listaAlumnos);//se llena la lista pero no esta enlazado con el table view
		
		
		//Enlazar listas con ComboBox y TableView
		cmbCarrera.setItems(listaCarreras);
		cmbCentroEstudio.setItems(listaCentrosEstidos);
		tblViewAlumnos.setItems(listaAlumnos);
		
		//Enlazar columnas con atributos
		clmnnombre.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nombre"));
		clmnapellido.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apellido"));
		clmnedad.setCellValueFactory( new PropertyValueFactory<Alumno,Number>("edad"));
		clmngenero.setCellValueFactory(new PropertyValueFactory<Alumno,String>("genero"));
		clmnfechaIngreso.setCellValueFactory(new PropertyValueFactory<Alumno,Date>("fechaIngreso"));
		clmncentroEstudio.setCellValueFactory(new PropertyValueFactory<Alumno,CentroEstudio>("centroEstudio"));
		clmncarrera.setCellValueFactory(new PropertyValueFactory<Alumno,Carrera>("carrera"));
		gestionarEventos();
		
		
		conexion.cerrarConexion();//Nunca dejar una conexión abierta
	}
	
	
	public void gestionarEventos(){
		tblViewAlumnos.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Alumno>() {

					@Override
					public void changed(ObservableValue<? extends Alumno> observable,
							Alumno valorAnterior, Alumno valorSeleccionado) {
						    
						    txtCodigo.setText(String.valueOf(valorSeleccionado.getCodigoAlumno()));
							txtNombre.setText(valorSeleccionado.getNombre());
							txtApellido.setText(valorSeleccionado.getApellido());
						    txtEdad.setText(String.valueOf(valorSeleccionado.getEdad()));
						    
						    if(valorSeleccionado.getGenero().equals("F")) {
						    	rbtFemenino.setSelected(true);
						    	rbtMasculino.setSelected(false);
						    }else {
						    	
						    	if(valorSeleccionado.getGenero().equals("M")) {
							    	rbtFemenino.setSelected(false);
							    	rbtMasculino.setSelected(true);
							    }
							    
						    } 
						    
						    dtpkrFecha.setValue(valorSeleccionado.getFechaIngreso().toLocalDate());
						    cmbCarrera.setValue(valorSeleccionado.getCarrera());//En la clase alumno el método getCarrera trae un objeto de tipo carrera
						    cmbCentroEstudio.setValue(valorSeleccionado.getCentroEstudio());
						    
						    btnGuardar.setDisable(true);
						    btnEliminar.setDisable(false);
						    btnActualizar.setDisable(false);
						   //System.out.println("Nombre alumno seleccionado: " + valorSeleccionado.getNombre());
						
						}
					
		});
	}
	
	@FXML
	public void limpiarComponentes() {
		txtCodigo.setText(null);
		txtNombre.setText(null);
		txtApellido.setText(null);
		txtEdad.setText(null);
		rbtFemenino.setSelected(false);
		rbtMasculino.setSelected(false);
		dtpkrFecha.setValue(null);
		cmbCarrera.setValue(null);
		cmbCentroEstudio.setValue(null);
		
		btnGuardar.setDisable(false);
		btnEliminar.setDisable(true);
		btnActualizar.setDisable(true);
		
	}
	
} 
