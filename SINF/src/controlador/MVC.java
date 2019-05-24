/**
 * MVC.java	Clase pricipal (y ubicación de funciones auxiliares, probablemtne deberia cambiarse a un archivo separado en el modelo)
 * @author Mario
 * @author David
 */
/* Importación de librerias */
package controlador;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import exepciones.ErroresInit;
import groovy.lang.Newify;
import modelo.ModeloLogin;
import modelo.ModeloPrincipal;
//import modelo.exportar;
//import modelo.importar;
import vista.VistaLogin;
import vista.VistaPrincipal;

/**
 * 
 * @author Mario
 *
 */
public class MVC {
	
	//Variables Globales
	/* Configuración */
	static Properties config = new Properties();
	public static File RUTA_ARTICULO;
	public static File RUTA_INFRACCION;
	public static File ARTICULO;
	public static File INFRACCIONES;
	
	/* Colores*/
	/////////////////Constantes de paquete
	public static final Color COLOR_BG; //Color oscuro de fondo    
	public static final Color COLOR_HIGHLIGHT;//Color Cyan, para letras con fondo oscuro o para cuando se coloca el cursor ecima de algun campo de texto
	public static final Color COLOR_VALID; // Color verde opaco, color default en los componentes de texto
    public static final Color COLOR_INVALID;//Color rojo opaco, color para indicar que un campo no cumple con el formato apropiado o esta vacio 
    public static final Color COLOR_LETRA;
    public static final String SRC= src();//parte de la cadena de ruta, si se encuentra en proyecto,se agrega, si no no
    /*Fuente*/
    public static final Font FUENTE;//Fuente de texto predeterminada
    
   
    /**
     * Costructor constantes de paquete
     * como las variables son static final pero requieren inicializarse desde un archivo externo se crea esta declaración especial
     * se edito el metodo para que maneje de valores por defecto en caso que no logre cargar el archivo
     */
    
    static {
    	//trata de carga el archivo
    	try{
    		
    		loadConfig();//llama al archivo y carga las propiedades, en caso de no cargar el archivo carga valores predeterminados de acuerdo a la peleta escogida en el diseño inicial
    		setRutaArticulo();
    		setArchivoArticulos();
    		setRutaInfraccion();
    		setArchivoInfracciones();
    	//manejo de errores
    	}catch(Exception e) {    		
    		e.printStackTrace();
    		//checar si es posible cargar propiedades desde codigo en vez de archivo, de ser así insertar valores predeterminados aquí
    	//indpendientemente si se logra cargar o no los datos, asigna los colores
	    }finally {
	    	COLOR_BG= iniciarColor(MVC.getConfig().getProperty("color_fondo"),new Color(58,63,64));            
		    COLOR_VALID	=iniciarColor(MVC.getConfig().getProperty("color_campoNormal"),new Color(187,202,204));
		    COLOR_INVALID= iniciarColor(MVC.getConfig().getProperty("color_campoError"),new Color(201,79,76));
		    COLOR_HIGHLIGHT	= iniciarColor(MVC.getConfig().getProperty("color_letraClara"),new Color(234,253,255));
		    COLOR_LETRA	= iniciarColor(MVC.getConfig().getProperty("color_letraClara"),new Color(234,253,255));
		    FUENTE= new Font("Arial", Font.BOLD, 14);//implementaión del estilo mediante archivo sigue incompleta, por ahora solo la declaro aquí
	    }

    }
    // 
    /* Archivo propiedades */
    
	//static FileInputStream configInput = null;
	///////////////// Metodos y funciones ///////////////
   
    ///////////Metodos constructores e inicializadores
	/** 
	 * Clase principal
	 * Inicia el programa, llamando la pantalla de login 
	 * */
	public static void main(String[] args){
		//new MVC();
		//trata de inicializar los compoentes
		try {
			//variables de función, MVC del login
			VistaLogin vista = new VistaLogin();
			ModeloLogin modelo= new ModeloLogin(vista);
			ControladorLogin controlador= new ControladorLogin(vista,modelo);
			
			//dialog.setDefaultCloseOperation(JDialog.);
			vista.setVisible(true);

			
			/*VistaPrincipal vista = new VistaPrincipal();
			ModeloPrincipal modelo= new ModeloPrincipal(vista);
			ControladorPrincipal controlador= new ControladorPrincipal(vista,modelo);
			controlador.iniciar();
			
			modelo.iniciarConsultaArticulos(vista);
			vista.setVisible(true);*/
			//manejo de errores
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		//versión previa
		//antes de la creación del login se llamava la pantalla principal directamente
		/*VistaPrincipal vista = new VistaPrincipal();
		ModeloPrincipal modelo= new ModeloPrincipal(vista);
		ControladorPrincipal controlador= new ControladorPrincipal(vista,modelo);
		controlador.iniciar();
		vista.setVisible(true);*/
		 
	}//fin función main
	
	/**
	 * Función para cargar la configuración desde un archivo de propiedades
	 * pre-condición: el archivo existe
	 * post-condición:  todas los atributos dependietes del archiov se cargan
	 * modificado para que capture la ruta mediante un Path en vez de URL
	 * tambien se añade una configuración que corre por defecto en caso de que 
	 */
	public static void loadConfig() throws Exception{
		
		try{
			//variables de función
			//variables de funci�n
			FileInputStream configInput = null;
			if(!(new File("datos\\").exists()) &&(new File("SINF.jar").exists()))
				extractJar();
			//URL is = ClassLoader.getSystemResource("datos/configuracion.properties");//previamente se accedia el archivo mediante una url, pero debido a la diferencia de formato entre texto url y texto Path esta puede causar errores
			File is= new File(src()+"\\datos\\configuracion.properties");//ahora se accesa a la ruta mediante un File, la ruta por defecto toma en cuenta la carpeta /src/ que es la que se en la que se programa
			if (!is.exists()){//verifica que exita el archivo
				is= new File("dato/configuracion.properties");//si no encuentra el archivo trata de leer el archivo basandose en la carpeta raiz, esta ruta existira en la version final portatil									
			}
			
			//configInput = new FileInputStream(is.getFile());//antes ccuando is era url con este metodo se iniciaba el inputStream
			configInput = new FileInputStream(is);//se crea un inputStream para lectura del archivo
            
            //carrga las propiedades
            config.load(configInput);//carga desde el archivo
            
            
            //codigo viejo
			/*FileInputStream configInput = null;
			
			File user = new File(System.getProperty("datos/configuracion.properties"));
			
			URL is = new URL("C:/SINF/datos/configuracion.properties");
            configInput = new FileInputStream(user);
            //carrga las propiedades
            config.load(configInput);
            setConfig(config);*/
        //manejo de errores
        }
		////Apuntador vacio
		catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No se Pudo Leer el archivo\n"+e.getMessage(),"Error de lectura",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			setDefaultConfig();//carga configuracción por defecto(probablemente haya que cambiar la bandera a false cuando se planee hacer la version instalable)
		}
		//No se tiene permiso para acceder a la ruta o archivo
		catch(SecurityException e) {
			JOptionPane.showMessageDialog(null, "El archivo o ruta de acceso estan protegidos, no se puede acceder al contenido\n"+e.getMessage(),"Error de Permisos",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			setDefaultConfig();//carga configuracción por defecto(probablemente haya que cambiar la bandera a false cuando se planee hacer la version instalable)
			//asumiendo que no se tenga permiso de escritura ni siquiera tratamos de crear el archivo secundario
		}
		////Archivo no encontrado
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Archivo no Encontrado, asegurese que la ruta de acceso sea correcta\n"+e.getMessage(),"Archivo no encontrado, se cargara la configuración por defecto",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			setDefaultConfig();//carga configuracción por defecto(probablemente haya que cambiar la bandera a false cuando se planee hacer la version instalable)
			saveConfig();
        }
		//Error Lectura Escritura
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Error con el manejador de archivo\n"+e.getMessage(),"Error I/O",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			setDefaultConfig();//carga configuracción por defecto(probablemente haya que cambiar la bandera a false cuando se planee hacer la version instalable)
			throw e;
		}
		//error Generico
		catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error cargando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            throw e;//si se llego a este error generico puede que el problema provenga de otra parte o sea algo no definido/contemplado, por lo que mejor lanzo el error para checar en su momento
        }
    }//fin metodo loadConfig
	
	
	/**
	 * Guarda el archivo de propiedades
	 * pre-condición: la ruta de acceso tiene permiso de escritua y hay espacio suficiente
	 * @param src boolean, bandera que determina si la carpeta src se considera parte de la ruta del archivo 
	 * @throws IOException 
	 */
	
	public static void saveConfig() throws IOException {
		//variables locales
		File archivoConfig= null;//ruta del archivo
		FileOutputStream fo = null;//escritor de achivo
		try {
			File rutaArchivo= new File(SRC+"datos/current/");			
			rutaArchivo.mkdirs();
			rutaArchivo = new File(SRC+"datos/Backup/");
			rutaArchivo.mkdirs();
			archivoConfig = new File(SRC+"datos/configuracion.properties");//carga la ruta ruta source depende si se esta trabajando en el prorama o la version release
			fo= new FileOutputStream(archivoConfig);//inicia escritor
			config.store(fo, "Generaddo desde Programa");//guarda los datos
		}
		//////Manejo de errores
		//No se tiene permiso para acceder a la ruta o archivo
		catch(SecurityException e) {
			JOptionPane.showMessageDialog(null, "El archivo o ruta de acceso estan protegidos, no se puede acceder al contenido\n"+e.getMessage(),"Error de Permisos",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		//error del stream
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error con el administrador de archivos\n"+e.getMessage(),"Error I/O",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		////Archivo no encontrado
		/*catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Archivo no Encontrado, asegurese que la ruta de acceso sea correcta\n"+e.getMessage(),"Archivo no encontrado",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();			
	    }*/
		//error Generico
		catch(Exception e){
	        JOptionPane.showMessageDialog(null, "Error cargando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        throw e;//si se llego a este error generico puede que el problema provenga de otra parte o sea algo no definido/contemplado, por lo que mejor lanzo el error para checar en su momento
	    }
		//al final cierra el archivo
		finally{
			fo.close();			
		}
	}//fin saveConfig
	////////Manejo de archivos
	
	////////////////Manejo de Archivos de la tabla
	/**
	* Carga contenido de tabla
	* @param archivo
	* @param tablaD
	* @return
	* @see exportar
	* antes las ventanas de consulta de archivo e infracciones tenian una instancias propia de la función, la transferi aquí para que las dos puesdan llamarlo
	*/
	public static String importar(File archivo, JTable tablaD){
		//variables de función
		String respuesta="No se pudo realizar la importación.";		
		//sobreEscribe el constructor del Modelo de datos para inhabilidar la edicion directa
		DefaultTableModel modeloT = new DefaultTableModel() {
			//variable de instancia
			public boolean editable= false;//bandera para posible habilitación de edición
			//la clase DefaultTableModel por defecto pone sus celdas como editables y sin metodo set para cambiar la opción, declarando editable y sobreescribiendo el metodo habilito su posible edición
			@Override
			public boolean isCellEditable(int row, int column) {
			   //all cells false
			   return editable;
			}
						
		};        
		tablaD.setModel(modeloT);//asigna a la tabla de la vista el modelo creado
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(archivo));//abre el archivo
			Sheet hoja = wb.getSheetAt(0);//obtiene la hoja 0
			Iterator filaIterator = hoja.rowIterator();//iterador de renglones
			int indiceFila=-1;//indice de fila, como el primer renglon esta reservado para los titulos para conpensar tiene un valor n-1
			while (filaIterator.hasNext()) {//recorre el archivo         
			    indiceFila++;
			    Row fila = (Row) filaIterator.next();
			    Iterator columnaIterator = fila.cellIterator();
			    Object[] listaColumna = new Object[hoja.getLastRowNum()];
			    int indiceColumna=-1;
			    //recorre la hoja
			    while (columnaIterator.hasNext()) {                    
			        indiceColumna++;
			        Cell celda = (Cell) columnaIterator.next();//recorre el renglon
			        if(indiceFila==0){
			            modeloT.addColumn(celda.getStringCellValue());
			        //si el indice es mayor o igual a 0 se considera parte del modelo t se agega al renglon de datos
			        }else{
			            if(celda!=null){
			            	//analiza el tipo de dato de la celda y extra el dato al modelo
			                switch(celda.getCellType()){
			                    case Cell.CELL_TYPE_NUMERIC:
			                        listaColumna[indiceColumna]= (int)Math.round(celda.getNumericCellValue());
			                        break;
			                    case Cell.CELL_TYPE_STRING:
			                        listaColumna[indiceColumna]= celda.getStringCellValue();
			                        break;
			                    case Cell.CELL_TYPE_BOOLEAN:
			                        listaColumna[indiceColumna]= celda.getBooleanCellValue();
			                        break;
			                    default:
			                        listaColumna[indiceColumna]=celda.getDateCellValue();
			                        break;
			                }
			            }                        
			        }
			    }
			    if(indiceFila!=0)modeloT.addRow(listaColumna);//agrega el nuevo renglon de datos
			}
			//Establece las longitudes predeterminadasde las celdas
			tablaD.getColumnModel().getColumn(0).setPreferredWidth(250);
			tablaD.getColumnModel().getColumn(1).setPreferredWidth(1000);
			tablaD.getColumnModel().getColumn(2).setPreferredWidth(250);
			wb.close();//cierra el archivo
			respuesta="Importación exitosa";            
		//manejo de errores
		} catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
		System.err.println(e.getMessage());
		}
		return respuesta;
	}//fin importar
	
	/**
	* Exporta la tabla a un archivo Excel
	* @param archivo
	* @param tablaD
	* @return
	* Antes los dos modelos de consulta de articulos e infracciones tenian una copia identica de la función, la movi aquí para
	* @see importar
	*/
	public static String exportar(File archivo, JTable tablaD){
		//variables locales
		Workbook wb;//maneador archivo office
		String respuesta="No se realizo con exito la exportación.";//mensaje
		int numFila=tablaD.getModel().getRowCount(), numColumna=tablaD.getModel().getColumnCount();//numero de filas (incluyendo espacios vacios
		
		//inicia el archivo como hoja de trabajo
		if(archivo.getName().endsWith("xls")){//hojas excel previo office 2010
			wb = new HSSFWorkbook();
		}else{
			wb = new XSSFWorkbook();//hojas excel psoteriores Office 2010
		}
		Sheet hoja = wb.createSheet("Pruebita");//crea hoja de trabao
		//recorre el modelo y va transfiriendo datos
		try {
			for (int i = -1; i < numFila; i++) {//recorre filas
			    Row fila = hoja.createRow(i+1);//se prepara para añadir una nueva columa de datos
			    for (int j = 0; j < numColumna; j++) {//recorre columnas
			        Cell celda = fila.createCell(j);//crea celda nueva
			        if(i==-1){//si el indice es -1 lee las cabeceras
			            celda.setCellValue(String.valueOf(tablaD.getColumnName(j)));
			        }else{//añade a el valor en la celda
			            celda.setCellValue(String.valueOf(tablaD.getModel().getValueAt(i, j)));
			        }
			        wb.write(new FileOutputStream(archivo));//guarda los cambios
			    }
			}
			wb.close();//cierra el documento
			respuesta="Exportación exitosa.";
		//Manejo de erores
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return respuesta;
	}//fin exportar
	
	////////importación de propiedades desde archivo
	
	/**
	 * importa un color basado en la cadena con los valores RGB
	 * @param rgb	codigo RGB (valores decimales)
	 * @param def	codigo RGB (color por defecto encaso de no poder cargarse)
	 * @return	El color equivalente al codigo RGB
	 * pre-condición, el texto biene en formato n,n,n; 0 <= n < 255
	 * editado para dar colors por defecto diferentes cuando se llame
	 */
	private static Color iniciarColor(String rgb, Color def) {
		//System.out.println("rgb= "+rgb);
		//variables de funcion
		Color c= def;//color resultante, se asigna el color por defecto en caso de no poder parsear
		
		try{
			//divide la cadena RGN
			String[] vals = rgb.split(",");
			
			//Si la cadena no se divide en 3 subcadeas lanza un error de formato
			if (vals.length != 3) throw new ErroresInit(2);
			//convierte las cadenas a valoeres numericos
			int r=Integer.parseInt(vals[0]);//valor rojo
			int g=Integer.parseInt(vals[1]);//valor verde
			int b=Integer.parseInt(vals[2]);//valor azul
			//si alguno de los valores o esta dentro del rango numerico laza un error de RGB invalido
			if(!inRange(r,0,255) || !inRange(g,0,255) || !inRange(b,0,255)) if (vals.length != 3) throw new ErroresInit(2);				
			//asigna el color resultante
			c= new Color(r,g,b);
		//Manejo de errores
		}
		//errores de iniciacion
		catch(ErroresInit e) {
			System.out.println("Error de inicialización, codigo de error " +e.codigo );
			e.printStackTrace();
		}
		//Null pointer, probablmente un error de parseo 
		catch(NullPointerException e) {
			e.printStackTrace(); 
		}
		
		return c;
	}//fin iniciarColor
	/**
	 * Función para cargar la fuente desde el archivo
	 * debido a los posibles modificadores aun no esta implementada direcaente
	 * por ahora esta desactivada y la fuente se declara en este archivo en vez de importarse desde archivo de propiedades	
	 */
	/*
	private static Font iniciarFuente(String font) {
		//System.out.println("Fuente= "+font);
		Font f= Color.WHITE;
		try{
			String[] vals = font.split(",");
			String fuente=vals[0];
			String estilo=vals[1];			//investigar sobre TextAtributes para mas uso avanzaddo o uso de multiples atributos de estilo, por mientras con esta basta
			int tamaño=Integer.parseInt(vals[2]);
			//f= new Font(fuente,Font.,tamaño);
		}catch(NullPointerException e) {
			
		}
		
		return c;
	}*/
	
	///////////////// accesores y modificadores
	/**
	 * Getter proipedades
	 * @return
	 */
    public static Properties getConfig() {
		return config;
	}//fin getConfig
    /**
     * Setter propiedades
     * @param config
     */
	public static void setConfig(Properties config) {
		MVC.config = config;		
	}//fin setConfig
	
	public static void setArchivoArticulos() {
		ARTICULO= new File(config.getProperty("ruta_articulos")+"\\\\"+config.getProperty("articulos"));
	}
	
	public static void setArchivoInfracciones() {
		INFRACCIONES= new File(config.getProperty("ruta_infracciones")+"\\\\"+config.getProperty("infracciones"));
	}
	
	public static void setRutaArticulo() {
		RUTA_ARTICULO= new File(config.getProperty("ruta_articulos"));
	}
	
	public static void setRutaArticulo(String nuevaRuta) {
		config.setProperty("ruta_articulos", nuevaRuta);
		RUTA_ARTICULO= new File(nuevaRuta);
		try {
			saveConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error, no se pudieron guardar los cambios", "Error de Escritura", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static void setRutaArticulo(File nuevaRuta) {
		config.setProperty("ruta_articulos",nuevaRuta.getAbsolutePath());
		RUTA_ARTICULO= nuevaRuta;
		try {
			saveConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error, no se pudieron guardar los cambios", "Error de Escritura", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static void setRutaInfraccion(File nuevaRuta) {
		config.setProperty("ruta_infracciones",nuevaRuta.getAbsolutePath());
		RUTA_INFRACCION= nuevaRuta;
		try {
			saveConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error, no se pudieron guardar los cambios", "Error de Escritura", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static void setRutaInfraccion(String nuevaRuta) {
		config.setProperty("ruta_infracciones", nuevaRuta);
		RUTA_INFRACCION= new File(nuevaRuta);
		try {
			saveConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error, no se pudieron guardar los cambios", "Error de Escritura", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static void setRutaInfraccion() {
		RUTA_INFRACCION= new File(config.getProperty("ruta_articulos"));
	}
	
	/**
	 * Setter que Carga datos por defecto
	 * @param src boolean ,bandera para indicar si se considera la carpeta source como parte de la ruta
	 */
	public static void setDefaultConfig() {
		config.clear();//se limpia los datos para asegurar una instalación limpia
		//carga los dats por defecto
		config.setProperty("articulos","Articulos.xlsx");
		config.setProperty("infracciones","Infracciones.xlsx");
		config.setProperty("respaldos",SRC+"datos\\\\backup\\\\");
		config.setProperty("ruta_articulos",SRC+"datos\\current\\\\");
		config.setProperty("ruta_infracciones",SRC+"datos\\\\current\\\\");
		config.setProperty("ruta_default",SRC+"datos\\\\current\\\\");
		config.setProperty("usuario","Transito");
		config.setProperty("contraseña","SINF");
		config.setProperty("usuario_Default","Transito");
		config.setProperty("contraseña_Default","SINF");
		config.setProperty("color_letraClara","234,253,255");
		config.setProperty("color_campoError","201,79,76");
		config.setProperty("color_campoNormal","187,202,204");
		config.setProperty("color_campoBlanco","255,255,255");
		config.setProperty("color_fondo", "58,63,64");
		config.setProperty("estiloFuente", "Arial, BOLD, 14");
	}
	
	
	private static void extractJar() {
		JarFile jar;
		try {
			JOptionPane.showMessageDialog(null, "extrayendo archivo");
			jar = new java.util.jar.JarFile(new File("SINF.jar"));
			Enumeration<JarEntry> enumEntries = jar.entries();
			while (enumEntries.hasMoreElements()) {
			    java.util.jar.JarEntry file = (java.util.jar.JarEntry) enumEntries.nextElement();
			    java.io.File f = new java.io.File(new File("").getAbsolutePath()+java.io.File.separator + file.getName());
			    if(!f.getPath().contains("datos"+java.io.File.separator))
			    	continue;
				else {
				    /*JOptionPane.showMessageDialog(null, "Nombre="+f.getName());
				    JOptionPane.showMessageDialog(null, "ruta="+ f.getParent());
				    JOptionPane.showMessageDialog(null, "ruta absoluta="+ f.getAbsolutePath());
				    JOptionPane.showMessageDialog(null, "Archivo o directorio: "+ (f.isDirectory()?"Directorio":"Archivo"));*/
				    if(!f.exists())
			        {
			            f.getParentFile().mkdirs();
			            f = new java.io.File(new File("").getAbsolutePath()+java.io.File.separator + file.getName());
			        }
				    if (file.isDirectory()) { // if its a directory, create it
				        f.mkdir();
				        continue;
				    }
				    //JOptionPane.showMessageDialog(null, "Estableciiendo Input Stream "+ f.getAbsolutePath());
				    java.io.InputStream is = jar.getInputStream(file); // get the input stream
				   // JOptionPane.showMessageDialog(null, "Estableciendo  Output Stream "+ f.getAbsolutePath());
				    java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
				    //JOptionPane.showMessageDialog(null, "tamaño input "+ file.getSize());
				    //JOptionPane.showMessageDialog(null, "tamaño IS "+ is.available());
				    while (is.available() > 0) {  // write contents of 'is' to 'fos'
				    	//JOptionPane.showMessageDialog(null, "Escribiendo ="+ is+ " en "+ fos );
				    	fos.write(is.read());
				    }
				    fos.close();
				    is.close();
				}
			}
			jar.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "error de extracción");
			e.printStackTrace();
		}
		
	}
	//anteriormente habia puesto aquí las funciones de importar y exportar, pero loas movi al modelo principal

	///////////////// Funciones Miscelaneas
	/////////////Funciones publicas
	
	
	/**
	 * función que determina si un numero esta dentro de un rango de otros dos numeros
	 * @param n	numero a evaluar
	 * @param m numero inferior
	 * @param M nuero mayor
	 * pre-condición: m<=M
	 * @return
	 */
	public static boolean inRange(int n,int m, int M) {
		return (n>=m? (n<=M):false);
	}//fin inRange
	
	/**
	 * Cpermite cambiar el fondo de varios componentes swing simultanemaente
	 * @param o arreglo de componentes a cambiar
	 * @param c color que se quiere asigar
	 */
	public static void coloreaCampos(Object[] o, Color c) {
		for(Object comp: o) {
			if(comp instanceof JTextComponent)
				((JTextComponent)comp).setBackground(c);
		}
	}//fin coloreaCampos
	
	/**
	 * colorea un solo componente swing
	 * @param comp compoente deseado
	 * @param c color que se quiere asignar
	 */
	public static void coloreaCampo(Object comp, Color c) {
		if(comp instanceof JTextComponent) {
			((JTextComponent)comp).setBackground(c);
		}
	}//fin coloreaCampo

	/////////////////funciones privadasd
	/**
	 * verifica si existe la carpeta source, si existe se guarda como parte de la ruta, si no la omite
	 * 
	 */
	private static String src() {
		//variable local
		File carpeta= new File("src\\\\");
		//System.out.println("src existe: "+ carpeta.exists());
		return carpeta.exists()? "src":"";
	}
	
	/**
	 * Compoara una cadena de texto con un arreglo de archvios
	 * @param cadena
	 * @param arreglo
	 * @return
	 */
	private boolean compara(String cadena, char[]arreglo) {
		boolean res=false;
		//char[] cadenaArreglo= cadena.toCharArray();
		if(cadena.length() == arreglo.length) {
			int i=0;
			while(res==false && i++ < cadena.length()) {
				if(Character.isLetterOrDigit(cadena.charAt(i))) res= cadena.charAt(i)== arreglo[i];
				else break;
			}
			return res;
		}else
			return res;
	}//fin compara
	
	
	//////////////SubClases
	/**
	* Clase para manejo de hilo del manejo de archivo
	* Si se realiza una ejecución lineal cuando se modifican las tablas causa un retraso considrable
	* por lo que se implementa este hilo para permmitir una ejeución mas rapida
	* @author David
	*
	*/
	public static class HiloArchivo implements Runnable {
	//variables de clase
		private boolean active;
		private JTable tabla;
		private File archivo;
		
		//constructores e inicializadores
		public HiloArchivo(JTable tabla,File archivo ) {
			active= false;
			this.tabla= tabla;
			this.archivo= archivo;
		}
		
		//setters y getters
		public boolean isActive() {
			return active; 
		}
		
		public void activate() {
			active= true;
		}
		
		public void deactivate() {
			active= false;
		}
		
		public void setActive(boolean active) {
			this.active=active;
		}
		
		
		/////////implementación Runnable
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(isActive()) {
				System.out.println("Guardando cambios");
				MVC.exportar(archivo, tabla);
				deactivate();
			}
		}
		
	}//fin clase HiloArchivo
	
	
}// Fin de clase MVC
