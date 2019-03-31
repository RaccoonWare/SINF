package controlador;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import javax.swing.JOptionPane;
import vista.VistaPrincipal;

public class MVC {
	static Properties config = new Properties();
	static FileInputStream configInput = null;
	
	public static void main(String[] args) {
		VistaPrincipal vista = new VistaPrincipal();
		ControladorPrincipal controlador= new ControladorPrincipal(vista);
		controlador.iniciar();
		vista.setVisible(true);
		 loadConfig();
	}
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
	

}
