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
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

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
	
	/////////////////Constantes de paquete
	/* Configuración */
	static Properties config = new Properties();
	/* Colores*/
	public static final Color COLOR_BG; //Color oscuro de fondo    
	public static final Color COLOR_HIGHLIGHT;//Color Cyan, para letras con fondo oscuro o para cuando se coloca el cursor ecima de algun campo de texto
	public static final Color COLOR_VALID; // Color verde opaco, color default en los componentes de texto
    public static final Color COLOR_INVALID;//Color rojo opaco, color para indicar que un campo no cumple con el formato apropiado o esta vacio 
    public static final Color COLOR_LETRA;
    /*Fuente*/
    public static final Font FUENTE;//Fuente de texto predeterminada
    
    /**
     * Costructor constantes de paquete
     * como las variables son static final pero requieren inicializarse desde un archivo externo se crea esta declaración especial
     */
    
    static {
    	{
    		loadConfig();//llama al archivo
    		COLOR_BG		= iniciarColor(MVC.getConfig().getProperty("color_fondo"));            
            COLOR_VALID	=iniciarColor(MVC.getConfig().getProperty("color_campoNormal"));
            COLOR_INVALID= iniciarColor(MVC.getConfig().getProperty("color_campoError"));
            COLOR_HIGHLIGHT	= iniciarColor(MVC.getConfig().getProperty("color_letraClara"));
            COLOR_LETRA	= iniciarColor(MVC.getConfig().getProperty("color_letraClara"));
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
	public static void main(String[] args) {
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
	 */
	public static void loadConfig(){
		
		try{
			//variables de función
			FileInputStream configInput = null;
			
			File user = new File(System.getProperty("datos/configuracion.properties"));
			
			//URL is = new URL("C:/SINF/datos/configuracion.properties");
            configInput = new FileInputStream(user);
            //carrga las propiedades
            config.load(configInput);
            setConfig(config);
        //manejo de errores
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error cargando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//fin metodo loadConfig
	
	////////Manejo de archivos
	////////////////Manejo de Archivos
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
			@Override
			public boolean isCellEditable(int row, int column) {
			   //all cells false
			   return false;
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
	 * @return	El color equivalente al codigo RGB
	 * pre-condición, el texto biene en formato n,n,n; 0 <= n < 255
	 */
	private static Color iniciarColor(String rgb) {
		//System.out.println("rgb= "+rgb);
		//variables de funcion
		Color c= Color.WHITE;//color resultante, blanco por defecto
		
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
	
	
	
}// Fin de clase MVC
