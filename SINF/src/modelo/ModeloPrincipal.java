/**
 * Logica de la ventana Principal
 * @author Mario
 */
package modelo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
/* importación de librerias¨*/
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

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
	private VistaPrincipal vistaPrincipal;	

	//private Point cordImg= setCoordenadas(vistaPrincipal);
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
		//Apéndice de método generado automáticamente
		vistaPrincipal.setVisible(true);
		//vistaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		vistaPrincipal.setExtendedState(vistaPrincipal.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		vistaPrincipal.setLocationRelativeTo(null);
		//centra y escala la imagen
		vistaPrincipal.icnFondo= setImagen(vistaPrincipal, MVC.IMAGEN_FONDO);
		vistaPrincipal.lblNewLabel.setIcon(vistaPrincipal.icnFondo);
		centraImagen(vistaPrincipal);
		//llea los campos de las imagenes
		llenarEtiquetas(vistaPrincipal);
		
		
		
		
		//vistaPrincipal.lblNewLabel.setIcon(MVC.);
		/*JOptionPane.showMessageDialog(vistaPrincipal, "Ruta actual: "+ new File("").getAbsolutePath());
		JOptionPane.showMessageDialog(vistaPrincipal, "Ruta Articulos : "+ MVC.RUTA_ARTICULO);
		JOptionPane.showMessageDialog(vistaPrincipal, "Ruta INFRACCIONES : "+ MVC.RUTA_INFRACCION);
		JOptionPane.showMessageDialog(vistaPrincipal, "Articulos : "+ MVC.ARTICULO);
		JOptionPane.showMessageDialog(vistaPrincipal, "INFRACCIONES : "+ MVC.INFRACCIONES);
		JOptionPane.showMessageDialog(vistaPrincipal, "Config Articulos : "+ MVC.getConfig().getProperty("ruta_articulos"));
		JOptionPane.showMessageDialog(vistaPrincipal, "Config INFRACCIONES : "+ MVC.getConfig().getProperty("ruta_infracciones"));*/
		//JOptionPane.showMessageDialog(vistaPrincipal, "Config Imagen : "+ MVC.getConfig().getProperty("ruta_imagen"));
	}//fin iniciar
	
	/**
	 * Inicia una nueva instancia de una boleta de infracciones 
	 * @param vistaPrincipal
	 * @since 1.1
	 */
	public void iniciarInfracciones() {
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
	public void iniciarConsultaInfracciones() {
		ModeloConsultaInfracciones MCI = new ModeloConsultaInfracciones();
		VistaConsultaInfraccion VCI = new VistaConsultaInfraccion();
		ControladorConsultaInfracciones CCI= new ControladorConsultaInfracciones(VCI, MCI);
		CCI.iniciar();
	}//fin iniciarConsultaInfracciones
	
	/**
	 * Inicia una instancia de una ventana de consulta de articulo
	 * @param vistaPrincipal
	 * @since 1.2
	 */
	public void iniciarConsultaArticulos() {
		VistaConsultaArticulo VCA = new VistaConsultaArticulo();
		ModeloConsultaArticulos MCA = new ModeloConsultaArticulos(VCA);		
		ControladorConsultaArticulos CCA= new ControladorConsultaArticulos(VCA, MCA);
		CCA.iniciar();
		
	}//fin iniciarConsultaArticulos
	
	/**
	 * Inicia una instancia de una ventana de consulta de articulo
	 * @param vistaPrincipal
	 */
	public void iniciarConfiguracion(VistaPrincipal vistaPrincipal) {
		VistaConfiguracion VC= new VistaConfiguracion();
		ModeloConfiguracion MC= new ModeloConfiguracion(VC);
		ControladorConfiguracion CC= new ControladorConfiguracion(VC,MC,vistaPrincipal);		
		CC.iniciar();
		
	}//fin iniciarConfiguración
	
	///////////////Metodos y funciones //////////
	///////Accesores y modificadores
	/**
	 * obtiene la ruta de la imagen
	 * */
	public static String getRutaImagen() {
		return MVC.getConfig().getProperty("ruta_imagen");
	}
	
	/**
	 * establece el tamaño de la imagen
	 * si es una imagen pequeña no la altera
	 * si es mayor a un tercio de la ancho y/o un medio de altura de la ventana lo reajusta al tamaño mas proximo al la dimension mayor
	 */
	
	public static Dimension scaleImage(VistaPrincipal vistaPrincipal, ImageIcon imagen) {		
		//variables locales
		int altVent= vistaPrincipal.getHeight();//altura de la ventana maxisada
		int larVent= vistaPrincipal.getWidth();
		int largo= imagen.getIconWidth();//lago de imagen
		int alto= imagen.getIconHeight();//ancho de imagen
		int nuevoLargo= largo;//largo de imagen en caso de remidensionarse
		int nuevoAlto= alto;//alto de imagen en caso de remidensionarse
		double factor=1.0; //factor de escala
		//verifica el tamaño de la imagen, si lo largo y ancho menores a la escala deseadas (1/3 ancho pantalla/12 alto pantalla)
		////si es menor a las dimensiones deseadas, regresa las dimensioens originales
		if(alto <= altVent/2 && largo <= larVent/3) {			
		////Si la imagen es menor a las proporciones maximas permitidas, se regresa tal cuall 
			return new Dimension(largo,alto);
		} else {			
		//////checa si es mas largo que alto y estalece el factor		
			if(largo>=alto) {
				factor= larVent/(3.0*largo);
		//////en caso que sea mas alto que largo y establece el factor
			}else {
				factor= larVent/(2.0*alto);
			}
			//calcula los nuevos valores
			nuevoLargo= (int) (factor*largo);
			nuevoAlto= (int) (factor*alto);
		}
		return new Dimension(nuevoLargo,nuevoAlto);		
	}//fin metodo scaleImage
	/**
	 * Establece las coordenadas en donde se colocara el icono
	 * @param imagen
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Point setCoordenadas(VistaPrincipal vistaPrincipal) {
		ImageIcon imagen = vistaPrincipal.icnFondo;
		int x= (vistaPrincipal.dpEscritorio.getWidth()/2)-(imagen.getIconWidth()/2);
		int y= (vistaPrincipal.dpEscritorio.getHeight()/2)-(imagen.getIconHeight()/2);
		return new Point(x,y);
	}//fin 
	
	/**
	 * Establece una nueva imagen
	 */
	public static ImageIcon setImagen(VistaPrincipal vistaPrincipal, String ruta) {
		String rutaActual=MVC.IMAGEN_FONDO;
		ImageIcon iconoactual= (ImageIcon) vistaPrincipal.lblNewLabel.getIcon();
		Rectangle coordenadasActuales= vistaPrincipal.lblNewLabel.getBounds();
		ImageIcon icon= new ImageIcon(ruta);//crea una instancia de la imagen en un contenedor de icono
		Image imagen= icon.getImage();//extrae el contendio de la imagen
		Dimension newSize= scaleImage(vistaPrincipal, icon);//obtiene el tamaño remidensionado

		Image imagenEscalada = imagen.getScaledInstance(newSize.width,newSize.height,Image.SCALE_SMOOTH);//crea imaen a escala
		MVC.IMAGEN_FONDO=ruta;
		MVC.getConfig().setProperty("ruta_imagen", ruta);
		try {
			MVC.saveConfig();
			//manejor de errores
		} catch (IOException e) {
			// muestra mensaje de error y restaura los valores previos
			JOptionPane.showMessageDialog(null, "No se pudo guardar el cambio", "Error", JOptionPane.ERROR_MESSAGE);
			vistaPrincipal.lblNewLabel.setIcon(iconoactual);
			vistaPrincipal.lblNewLabel.setBounds(coordenadasActuales);
			MVC.IMAGEN_FONDO=rutaActual;
			MVC.getConfig().setProperty("ruta_imagen", rutaActual);
			e.printStackTrace();
			return iconoactual;
			
		}
		return new ImageIcon(imagenEscalada);
	}
	
	
	/**
	 * centra la imagen
	 */
	public static void centraImagen(VistaPrincipal vistaPrincipal) {
		Dimension size= new Dimension(vistaPrincipal.icnFondo.getIconWidth(),vistaPrincipal.icnFondo.getIconHeight());
		Point coord= setCoordenadas(vistaPrincipal);
		vistaPrincipal.lblNewLabel.setBounds(new Rectangle(coord, size));
	}
	///////Gestion de archivos
	/**
	 * respalda las bases de datos de los articulos y de las infracciones
	 * crea los archivos basado en la dirección de la ruta predeterminada en el archivo de propiedades
	 * @throws IOException lanza error en caso que falle la carga
	 * pre-condición: las carpeta de destino existe, de no existir se creara en la carpteta raiz
	 * @since 1.4
	 * originalmente se encotraba en MVC
	 * @see restaurar
	 * se modifico cuando se agrego el menu de configuración para que cada archivo peuda tener su propia ruta y para que se pueda personalizar la ruta de cada uno
	 */
	public static void respaldar(VistaPrincipal ventana) throws IOException{

		//varialbes de función
		File articulos= MVC.ARTICULO;//manejador de archivo de articulos actualmente en uso
		File infracciones= MVC.INFRACCIONES;//manejador de archivo de infracciones actualmente en uso
		LocalDateTime now= LocalDateTime.now();//fecha y hora actual 
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");//formateador de fecha, el archivo creado se crea con el esquema "NombreArchivo FechaCrecion.xlsx"
		File respArticulos= new File(MVC.getConfig().getProperty("respaldos")+"Articulos-"+now.format(formatoFecha)+".xlsx");//manejador de archvios de articulos de respaldo, modificado para que sea una ruta personalizable independiente a la de infracciones
		File respInfracciones = new File(MVC.getConfig().getProperty("respaldos")+"Infracciones-"+now.format(formatoFecha)+".xlsx");//manejador de archivos de infracciones de respaldo, modificado para que sea una ruta personalizable independiente a la de  articulos
		
		try {
					    
		    //lanza un cuadro de dialogo indicando el exito de la función, se esta sobreeescribiendo la interfaz
		    UIManager.put("OptionPane.background", MVC.COLOR_BG);
		    UIManager.put("Panel.background", MVC.COLOR_BG);
		    UIManager.put("Button.background",Color.WHITE);
		    UIManager.put("OptionPane.messageFont", MVC.FUENTE);
		    UIManager.put("OptionPane.messageForeground", MVC.COLOR_HIGHLIGHT);
		    UIManager.put("OptionPane.buttonFont", MVC.FUENTE);
		    
		  //copia el cotenido de los archivos en uso alos actuales
			//requiere libreria apache POI
		    //respalda el archivo de infracciones
		    FileUtils.copyFile(infracciones,respInfracciones);
		    //pregunta si se quiere guardar el archivo de articulos
		    Locale local = new Locale("ES_MX");//Establece formato de ventanas en español (Si/No en ves de Yes/No)
		    JOptionPane.setDefaultLocale(local);
		    if(JOptionPane.showConfirmDialog(ventana, "¿Desea respaldar tambien el archivo de articulos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE ) == JOptionPane.YES_OPTION) 
		    	FileUtils.copyFile(articulos,respArticulos);
		    
		    //confirma la operación
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
	public static void restaurar(Container c) {
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
	    		File articulos= MVC.ARTICULO	;//manejador de archivo de articulos en ubicación a restaurar
	    		File infracciones= MVC.INFRACCIONES;//manejador de infracciones en ubicación a restaurar
	    		
	    		//analiza el nombre del archivo seleccionado y trata de copiar el contenido de este al archivo correspondite
	    		try {
		    		//restaura archivo de articulos
	    			if(seleccionado.getName().startsWith(FilenameUtils.getBaseName(articulos.getName()))) {
						FileUtils.copyFile(seleccionado,articulos);
						JOptionPane.showMessageDialog(c, "Archivo articulos restaurado exitosamente");
		    		//restaura archivo de infraccioes
	    			}else if(seleccionado.getName().startsWith(FilenameUtils.getBaseName(infracciones.getName()))) {
		    			FileUtils.copyFile(seleccionado,infracciones);
		    			JOptionPane.showMessageDialog(c, "Archivo infracciones restaurado exitosamente");
		    		//si el archivo no tiene un nombre que se encuentre en el archivo de propiedades lanza un mensaje de error
		    		}else  
		    			JOptionPane.showMessageDialog((Component)c, (Object)"Error, Archivo no reconocido", "Error de archivo", JOptionPane.ERROR_MESSAGE);
	    		//manejo de errores
	    		} catch (IOException e) {
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
	
	

}//fin clase principal
