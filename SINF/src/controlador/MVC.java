package controlador;
import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

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
	
}
