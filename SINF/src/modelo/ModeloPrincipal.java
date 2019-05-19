/**
 * Logica de la ventana Principal
 * @author Mario
 */
package modelo;
/*Importación de librerias*/
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FileUtils;

import controlador.ControladorConfiguracion;
import controlador.ControladorConsultaArticulos;
import controlador.ControladorConsultaInfracciones;
import controlador.ControladorInfraccion;
import controlador.MVC;
import vista.VistaConfiguracion;
import vista.VistaConsultaArticulo;
import vista.VistaConsultaInfraccion;
import vista.VistaInfraccion;
import vista.VistaPrincipal;

/**
 * Clase principal
 * @author Mario
 * @see VistaPrincipal
 * @see ControladorPrincipal
 */
public class ModeloPrincipal {
	//variables de instancia
	public VistaPrincipal vistaPrincipal;	
	//////////Cosntructores e inicializadores
	/**
	 * Constructor por defecto
	 * @param vista
	 */
	public ModeloPrincipal(VistaPrincipal vista) {
		this.vistaPrincipal=vista;
		iniciar(vista);
	}
	
	/**
	 * Inicializa la ventana
	 * @param vistaPrincipal
	 */
	public void iniciar(VistaPrincipal vistaPrincipal) {
		// TODO Apéndice de método generado automáticamente
		vistaPrincipal.setVisible(true);
		//vistaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		vistaPrincipal.setExtendedState(vistaPrincipal.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		vistaPrincipal.setLocationRelativeTo(null);
		llenarEtiquetas(vistaPrincipal);
	}//fin iniciar
	
	/**
	 * Inicia una nueva instancia de una boleta de infracciones 
	 * @param vistaPrincipal
	 * @since 1.1
	 */
	public void iniciarInfracciones(VistaPrincipal vistaPrincipal) {
		ModeloInfraccion MI = new ModeloInfraccion();
		VistaInfraccion VI = new VistaInfraccion();
		ControladorInfraccion CI= new ControladorInfraccion(VI, MI);
		CI.iniciar();
	}//fin iniciarInfracciones 
	
	/**
	 * Iniciar una nueva instancia de una ventanta de consulta de registro de ingracciones
	 * @param vistaPrincipal
	 * @since 1.1
	 */
	public void iniciarConsultaInfracciones(VistaPrincipal vistaPrincipal) {
		ModeloConsultaInfracciones MCI = new ModeloConsultaInfracciones();
		VistaConsultaInfraccion VCI = new VistaConsultaInfraccion();
		ControladorConsultaInfracciones CCI= new ControladorConsultaInfracciones(VCI, MCI);
		CCI.iniciar();
	}//fin iniciarConsultaInfracciones
	
	 /** Iniciar una nueva instancia de una ventanta de consulta de registro de ingracciones
	 * @param vistaPrincipal
	 * @since 1.1
	 */
	public void iniciarConfiguracion(VistaPrincipal vistaPrincipal) {
		ModeloConfiguracion MCI = new ModeloConfiguracion();
		VistaConfiguracion VCI = new VistaConfiguracion();
		ControladorConfiguracion CCI= new ControladorConfiguracion(VCI, MCI);
		CCI.iniciar();
	}//fin iniciarConsultaInfracciones

	/**
	 * Inicia una instancia de una ventana de consulta de articulo
	 * @param vistaPrincipal
	 * @since 1.2
	 */
	public void iniciarConsultaArticulos(VistaPrincipal vistaPrincipal) {
		VistaConsultaArticulo VCA = new VistaConsultaArticulo();
		ModeloConsultaArticulos MCA = new ModeloConsultaArticulos(VCA);		
		ControladorConsultaArticulos CCA= new ControladorConsultaArticulos(VCA, MCA);
		CCA.iniciar();
		
	}//fin iniciarConsultaArticulos
	
	///////////////Metodos y funciones //////////
	///////Gestion de archivos
	/**
	 * respalda las bases de datos de los articulos y de las infracciones
	 * crea los archivos basado en la dirección de la ruta predeterminada en el archivo de propiedades
	 * @throws IOException lanza error en caso que falle la carga
	 * pre-condición: las carpeta de destino existe, de no existir se creara en la carpteta raiz
	 * @since 1.4
	 * originalmente se encotraba en MVC
	 * @see restaurar
	 */
	public void respaldar(VistaPrincipal ventana) throws IOException{
		// TODO Auto-generated method stub
		//varialbes de función
		File articulos= new File(MVC.getConfig().getProperty("articulos"));//manejador de archivo de articulos actualmente en uso
		File infracciones= new File(MVC.getConfig().getProperty("infracciones"));//manejador de archivo de infracciones actualmente en uso
		LocalDateTime now= LocalDateTime.now();//fecha y hora actual 
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");//formateador de fecha, el archivo creado se crea con el esquema "NombreArchivo FechaCrecion.xlsx"
		File respArticulos= new File(MVC.getConfig().getProperty("respaldos")+"Articulos-"+now.format(formatoFecha)+".xlsx");//manejador de archvios de articulos de respaldo		
		File respInfracciones = new File(MVC.getConfig().getProperty("respaldos")+"Infracciones-"+now.format(formatoFecha)+".xlsx");//manejador de archivos de infracciones de respaldo
		
		try {
			//copia el cotenido de los archivos en uso alos actuales
			//requiere libreria apache POI
		    FileUtils.copyFile(articulos,respArticulos);
		    FileUtils.copyFile(infracciones,respInfracciones);
		    
		    //lanza un cuadro de dialogo indicando el exito de la función, se esta sobreeescribiendo la interfaz
		    UIManager.put("OptionPane.background", MVC.COLOR_BG);
		    UIManager.put("Panel.background", MVC.COLOR_BG);
		    UIManager.put("Button.background",Color.WHITE);
		    UIManager.put("OptionPane.messageFont", MVC.FUENTE);
		    UIManager.put("OptionPane.messageForeground", MVC.COLOR_HIGHLIGHT);
		    UIManager.put("OptionPane.buttonFont", MVC.FUENTE);
			JOptionPane.showMessageDialog(ventana, "Archivos repaldados correctamente ");
		//manejo de errores
		} catch (IOException e) {
		    e.printStackTrace();
		    throw e;
		}
	}//fin resppaldar

	/**
	 * restaura loos archvios de articulos e infracciones a partir de un respaldo
	 * @param c		contenedor dueño del filechooser que se llamara
	 * pre-condición:	los archivos deben empezar con algun nombre de los archivos existentes en el achivo de propiedades, de lo contrario fallara restauarlo
	 * post-condición:	los archivos en la capeta current (que son los que las tablas referencian para importar datos) sustituyen su contendido por el contenido del archivo de respaldo seleccionado
	 * @since 1.4
	 * anteriormente estaba en MVC
	 * @see respadar  
	 */
	public void restaurar(Container c) {
		// TODO Auto-generated method stub
		//varialbes de función
		File directorio= new File(MVC.getConfig().getProperty("respaldos"));//directorio prederterminado para el fileChooser	    
	    JFileChooser seleccionador= new JFileChooser(directorio);//selector de archivos(L&F standard, pero se modifica a la hora de llamarlo
	    FileNameExtensionFilter filtroExcel= new FileNameExtensionFilter("Archivos Excel (*.xls)", "xlsx");//filtro para el filechooser
	    FileNameExtensionFilter filtroNada= new FileNameExtensionFilter("Todos los archivos (*.*)", "*.*");//"filtro" para todos los achivos
	    //añade filtros a file chooser
	    seleccionador.addChoosableFileFilter(filtroExcel);
	    seleccionador.addChoosableFileFilter(filtroNada);
	   
	    //se modifica los atributos de interfaz estandard para cuando se llamen los cuadors de selección o dialogo
	    UIManager.put("OptionPane.background", MVC.COLOR_BG);
	    UIManager.put("Panel.background", MVC.COLOR_BG);
	    UIManager.put("Button.background",Color.WHITE);
	    UIManager.put("OptionPane.messageFont", MVC.FUENTE);
	    UIManager.put("OptionPane.messageForeground", MVC.COLOR_HIGHLIGHT);
	    UIManager.put("OptionPane.buttonFont", MVC.FUENTE);
	    UIManager.put("Label.foreground", MVC.COLOR_HIGHLIGHT);		    
	    UIManager.put("TextField.Background", MVC.COLOR_VALID);
	    
	    //abre la ventada de seleccion de archivo
	    if(seleccionador.showOpenDialog(c) == JFileChooser.APPROVE_OPTION) {
	    	File seleccionado= seleccionador.getSelectedFile();//manejador del archivo seleccionado
	    	//verifica la extensión
	    	if(seleccionado.getName().endsWith(".xlsx")){
	    		File articulos= new File(MVC.getConfig().getProperty("articulos"));//manejador de archivo de articulos en ubicación a restaurar
	    		File infracciones= new File(MVC.getConfig().getProperty("infracciones"));//manejador de infracciones en ubicación a restaurar
	    		
	    		//analiza el nombre del archivo seleccionado y trata de copiar el contenido de este al archivo correspondite
	    		try {
		    		//restaura archivo de articulos
	    			if(seleccionado.getName().startsWith(articulos.getName().substring(0, articulos.getName().length()-5))) {
						FileUtils.copyFile(seleccionado,articulos);
						JOptionPane.showMessageDialog(c, "Archivo articulos restaurado exitosamente");
		    		//restaura archivo de infraccioes
	    			}else if(seleccionado.getName().startsWith(infracciones.getName().substring(0, infracciones.getName().length()-5))) {
		    			FileUtils.copyFile(seleccionado,infracciones);
		    			JOptionPane.showMessageDialog(c, "Archivo infracciones restaurado exitosamente");
		    		//si el archivo no tiene un nombre que se encuentre en el archivo de propiedades lanza un mensaje de error
		    		}else  
		    			JOptionPane.showMessageDialog((Component)c, (Object)"Error, Archivo no reconocido", "Error de archivo", JOptionPane.ERROR_MESSAGE);
	    		//manejo de errores
	    		} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog((Component)c, (Object)"Error, No se pudo copiar el archivo", "Error de apertura", JOptionPane.ERROR_MESSAGE);
				}
	    	//en caso de que se seleccione un archivo con extension erronea lanza un mensaje de error
	    	}else {	    		
	    		JOptionPane.showMessageDialog((Component)c, (Object)(new JLabel("Error, Tipo de Archivo no valido")), "Error de extensión", JOptionPane.ERROR_MESSAGE);
	    		
	    	}
	    			
	    };
	    
	}//fin clase restaurar
	
	/////////Funcioes Miscelaneas
	/**
	 * Muestra el texto de los botones
	 * @param vistaPrincipal
	 * since 1.0
	 * @see llenarEtiquetas 
	 */
	public void llenarEtiquetas(VistaPrincipal vistaPrincipal) {
		vistaPrincipal.btnConsultar.setText("Consultar");
		vistaPrincipal.btnInfracciones.setText("Infracciones");
		vistaPrincipal.btnRespaldar.setText("Respaldo");
		vistaPrincipal.btnEtiquetas.setText("");
		vistaPrincipal.btnArticulos.setText("Articulos");
		vistaPrincipal.btnRestaurar.setText("Restaurar");
		vistaPrincipal.btnConfiguracin.setText("Configuración ");
	}
	/**
	 * Oculta el texto de las etiquetas
	 * @param vistaPrincipal
	 * @since 1.0
	 */
	public void vaciarrEtiquetas(VistaPrincipal vistaPrincipal){
		vistaPrincipal.btnConsultar.setText("");
		vistaPrincipal.btnInfracciones.setText("");
		vistaPrincipal.btnRespaldar.setText("");
		vistaPrincipal.btnEtiquetas.setText("");
		vistaPrincipal.btnArticulos.setText("");
		vistaPrincipal.btnRestaurar.setText("");
		vistaPrincipal.btnConfiguracin.setText("");
	}
	
	
}
