package controlador;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import org.apache.commons.io.FileUtils;
import org.jfree.io.FileUtilities;
import modelo.ModeloLogin;
import modelo.ModeloPrincipal;
import vista.VistaLogin;
import vista.VistaPrincipal;

public class MVC {
	static Properties config = new Properties();
	static FileInputStream configInput = null;
	public static final Color COLOR_BG; 
    public static final Color COLOR_HIGHLIGHT;
	public static final Color COLOR_VALID;
    public static final Color COLOR_INVALID;
    public static final Font FUENTE;
    static {
    	{
    		loadConfig();
    		COLOR_BG		= iniciarColor(MVC.getConfig().getProperty("color_fondo"));            
            COLOR_VALID	=iniciarColor(MVC.getConfig().getProperty("color_campoNormal"));
            COLOR_INVALID= iniciarColor(MVC.getConfig().getProperty("color_campoError"));
            COLOR_HIGHLIGHT	= iniciarColor(MVC.getConfig().getProperty("color_letraClara"));
            FUENTE= new Font("Arial", Font.BOLD, 14);//implementaión del estilo mediante archivo sigue incompleta, por ahora solo la declaro aquí
            
    	}
    }
    
	public static void main(String[] args) {
		//new MVC();
		try {
			VistaLogin vista = new VistaLogin();
			ModeloLogin modelo= new ModeloLogin(vista);
			ControladorLogin controlador= new ControladorLogin(vista,modelo);
			
			//dialog.setDefaultCloseOperation(JDialog.);
			vista.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*VistaPrincipal vista = new VistaPrincipal();
		ModeloPrincipal modelo= new ModeloPrincipal(vista);
		ControladorPrincipal controlador= new ControladorPrincipal(vista,modelo);
		controlador.iniciar();
		vista.setVisible(true);*/
		 
	}
	//private MVC()

	public static void loadConfig(){
		try{
			URL is = ClassLoader.getSystemResource("datos/configuracion.properties");
            configInput = new FileInputStream(is.getFile());
            config.load(configInput);
            setConfig(config);
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error cargando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static Properties getConfig() {
		return config;
	}
	public static void setConfig(Properties config) {
		MVC.config = config;
	}
	
	
	private static Color iniciarColor(String rgb) {
		//System.out.println("rgb= "+rgb);
		Color c= Color.WHITE;
		try{
			String[] vals = rgb.split(",");
			int r=Integer.parseInt(vals[0]);
			int g=Integer.parseInt(vals[1]);
			int b=Integer.parseInt(vals[2]);
			c= new Color(r,g,b);
		}catch(NullPointerException e) {
			 
		}
		
		return c;
	}
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
	
	public static void coloreaCampos(Object[] o, Color c) {
		for(Object comp: o) {
			if(comp instanceof JTextComponent)
				((JTextComponent)comp).setBackground(c);
		}
	}
	
	public static void coloreaCampo(Object comp, Color c) {
		if(comp instanceof JTextComponent) {
			((JTextComponent)comp).setBackground(c);
		}
	}

	public static void respaldar() throws IOException{
		// TODO Auto-generated method stub
		File articulos= new File(config.getProperty("articulos"));
		File infracciones= new File(config.getProperty("infracciones"));
		LocalDateTime now= LocalDateTime.now(); 
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		File respArticulos= new File(config.getProperty("respaldos")+"Articulos-"+now.format(formatoFecha)+".xlsx");		
		File respInfracciones = new File(config.getProperty("respaldos")+"Infracciones-"+now.format(formatoFecha)+".xlsx");
		try {		    
		    FileUtils.copyFile(articulos,respArticulos);
		    FileUtils.copyFile(infracciones,respInfracciones);
		} catch (IOException e) {
		    e.printStackTrace();
		    throw e;
		}
	}

	public static void restaurar(Container c) {
		// TODO Auto-generated method stub
		File directorio= new File(MVC.getConfig().getProperty("respaldos"));	    
	    JFileChooser seleccionador= new JFileChooser(directorio);
	    FileNameExtensionFilter filtroExcel= new FileNameExtensionFilter("Archivos Excel", "xlsx");
	    FileNameExtensionFilter filltroNada= new FileNameExtensionFilter("Todos los archivos", "*.*");	    
	    seleccionador.addChoosableFileFilter(filtroExcel);
	    seleccionador.addChoosableFileFilter(filtroExcel);
	    if(seleccionador.showOpenDialog(c) == JFileChooser.APPROVE_OPTION) {
	    	File seleccionado= seleccionador.getSelectedFile();
	    	if(seleccionado.getName().endsWith(".xlsx")){
	    		File articulos= new File(config.getProperty("articulos"));
	    		File infracciones= new File(config.getProperty("infracciones"));
	    		try {
		    		if(seleccionado.getName().startsWith(articulos.getName().substring(0, articulos.getName().length()-5))) {
						FileUtils.copyFile(seleccionado,articulos);
						JOptionPane.showMessageDialog(c, "Archivo articulos restaurado exitosamente");
		    		}else if(seleccionado.getName().startsWith(infracciones.getName().substring(0, infracciones.getName().length()-5))) {
		    			FileUtils.copyFile(seleccionado,infracciones);
		    			JOptionPane.showMessageDialog(c, "Archivo infracciones restaurado exitosamente");
		    		}else  
		    			JOptionPane.showMessageDialog((Component)c, (Object)"Error, Archivo no reconocido", "Error de archivo", JOptionPane.ERROR_MESSAGE);
	    		} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog((Component)c, (Object)"Error, No se pudo copiar el archivo", "Error de apertura", JOptionPane.ERROR_MESSAGE);
				}
	    	}else {	    		
	    		JOptionPane.showMessageDialog((Component)c, (Object)(new JLabel("Error, Tipo de Archivo no valido")), "Error de extensión", JOptionPane.ERROR_MESSAGE);
	    	}
	    			
	    };
	    
	}
	
}
