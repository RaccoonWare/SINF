package modelo;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import org.apache.commons.io.FileUtils;

import controlador.MVC;
import vista.VistaConfiguracion;

public class ModeloConfiguracion {
	private VistaConfiguracion vistaConfig;
	private static String rutaArticulos;
	private static String rutaInfracciones;
	//private JFileChooser selectorRuta;
	
	public ModeloConfiguracion(VistaConfiguracion vista) {
		vistaConfig= vista;		
		//iniciar();		
				
	}
	
	public void iniciar() {
		rutaArticulos = getRutaArts();
		rutaInfracciones = getRutaInfs();
		vistaConfig.txtRutaInfs.setText(rutaInfracciones);
		vistaConfig.txtRutaArts.setText(rutaArticulos);
		vistaConfig.txtName.setText(MVC.getConfig().getProperty("usuario"));
		vistaConfig.txtName.setEditable(false);
		vistaConfig.txtName.setForeground(MVC.COLOR_HIGHLIGHT);
		vistaConfig.txtName.setBackground(MVC.COLOR_BG);
		vistaConfig.txtPass.setText(MVC.getConfig().getProperty("contraseña"));
		vistaConfig.txtPass.setEditable(false);
		vistaConfig.txtPass.setForeground(MVC.COLOR_HIGHLIGHT);
		vistaConfig.txtPass.setBackground(MVC.COLOR_BG);		
		vistaConfig.txtConf.setText("");
		vistaConfig.txtConf.setBackground(MVC.COLOR_VALID);
		vistaConfig.txtNewName.setText("");
		vistaConfig.txtNewName.setBackground(MVC.COLOR_VALID);
		vistaConfig.txtNewPass.setText("");
		vistaConfig.txtNewPass.setBackground(MVC.COLOR_VALID);
		vistaConfig.txtNewConf.setText("");
		vistaConfig.txtNewConf.setBackground(MVC.COLOR_VALID);
	}
	
	
	
	public void defaultValues() {
		/*MVC.getConfig().setProperty("ruta_articulos", MVC.getConfig().getProperty("respaldos"));
		MVC.getConfig().setProperty("ruta_infracciones", MVC.getConfig().getProperty("respaldos"));
		MVC.getConfig().setProperty("usuario", MVC.getConfig().getProperty("usuario_Default"));
		MVC.getConfig().setProperty("contraseña", MVC.getConfig().getProperty("contraseña_Default"));*/
		MVC.setDefaultConfig();
		rutaArticulos = getRutaDefault();
		rutaInfracciones = getRutaDefault();
		iniciar();
		try {
			MVC.saveConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(vistaConfig, "Error, no se pudo Guardando el Archivo", "Error guardado",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static  String getRutaArts() {
		return (new File(MVC.getConfig().getProperty("ruta_articulos"))).getAbsolutePath().toString();
	}
	
	public void setRutaArts(String nuevaRuta) {
		String prevRoute= MVC.getConfig().getProperty("ruta_articulos");
		MVC.getConfig().setProperty("ruta_articulos", nuevaRuta);
		rutaArticulos = getRutaArts();
		try {
			MVC.saveConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(vistaConfig, "Ocurrio un error, no se pudieron guardar los cambios", "Error de Guardado", JOptionPane.ERROR_MESSAGE);
			MVC.getConfig().setProperty("ruta_articulos", prevRoute);
			rutaArticulos = getRutaArts();
			e.printStackTrace();
		}		
	}
	
	public static String getRutaInfs() {
		return (new File(MVC.getConfig().getProperty("ruta_infracciones"))).getAbsolutePath().toString();
	}
	
	public void setRutaInfs(String nuevaRuta) {
		String prevRoute= MVC.getConfig().getProperty("ruta_articulos");
		MVC.getConfig().setProperty("ruta_infracciones", nuevaRuta);
		rutaInfracciones= getRutaInfs();
		try {
			MVC.saveConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(vistaConfig, "Ocurrio un error, no se pudieron guardar los cambios", "Error de Guardado", JOptionPane.ERROR_MESSAGE);
			MVC.getConfig().setProperty("ruta_infracciones", prevRoute);
			rutaInfracciones= getRutaInfs();
			e.printStackTrace();
		}		
	}
	
	
	public void setRutaArticulo(String nuevaRuta) {
		File previousLocation= MVC.ARTICULO;
	    MVC.setRutaArticulo(nuevaRuta);
	    MVC.setArchivoArticulos();
	      if(!MVC.ARTICULO.exists())		    	  
	    	  try {
				FileUtils.copyFile(previousLocation,MVC.ARTICULO);
				if(!MVC.getConfig().getProperty("ruta_articulos").equals(MVC.getConfig().getProperty("ruta_default")));
					previousLocation.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void setRutaInfraccion(String nuevaRuta) {
		File previousLocation= MVC.INFRACCIONES;
	    MVC.setRutaInfraccion(nuevaRuta);
	    MVC.setArchivoInfracciones();
	      if(!MVC.INFRACCIONES.exists())		    	  
	    	  try {	    		
				FileUtils.copyFile(previousLocation,MVC.INFRACCIONES);
				if(!MVC.getConfig().getProperty("ruta_infracciones").equals(MVC.getConfig().getProperty("ruta_default")));
					previousLocation.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void selectRutaArticulo() {
		//varialbes de función
		File directorio= new File(MVC.getConfig().getProperty("ruta_articulos"));//directorio prederterminado para el fileChooser	    
		JFileChooser seleccionador= new JFileChooser(directorio);//selector de archivos(L&F standard, pero se modifica a la hora de llamarlo
		//Establece el seleccionador en modo directorio
		seleccionador.setDialogTitle("Seleccionar Archivo");
		seleccionador.setMultiSelectionEnabled(false);//se establece que solo puede seleccionarse un archivo
		seleccionador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//se modifica los atributos de interfaz estandard para cuando se llamen los cuadors de selección o dialogo
		UIManager.put("OptionPane.background", MVC.COLOR_BG);
		UIManager.put("Panel.background", MVC.COLOR_BG);
		UIManager.put("Button.background",Color.WHITE);
		UIManager.put("OptionPane.messageFont", MVC.FUENTE);
		UIManager.put("OptionPane.messageForeground", MVC.COLOR_HIGHLIGHT);
		UIManager.put("OptionPane.buttonFont", MVC.FUENTE);
		UIManager.put("Label.foreground", MVC.COLOR_HIGHLIGHT);		    
		UIManager.put("TextField.Background", MVC.COLOR_VALID);
		
		//seleccionador
		if (seleccionador.showOpenDialog(vistaConfig) == JFileChooser.APPROVE_OPTION) {		      
		      setRutaArticulo(seleccionador.getSelectedFile().getAbsolutePath());
		      iniciar();
		} else {
		      System.out.println("No se selecciono nada");
		}
	};
	
	public void selectRutaInfraccion() {
		//varialbes de función
		File directorio= new File(MVC.getConfig().getProperty("ruta_articulos"));//directorio prederterminado para el fileChooser	    
		JFileChooser seleccionador= new JFileChooser(directorio);//selector de archivos(L&F standard, pero se modifica a la hora de llamarlo
		//Establece el seleccionador en modo directorio
		seleccionador.setDialogTitle("Seleccionar Archivo");
		seleccionador.setMultiSelectionEnabled(false);//se establece que solo puede seleccionarse un archivo
		seleccionador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//se modifica los atributos de interfaz estandard para cuando se llamen los cuadors de selección o dialogo
		UIManager.put("OptionPane.background", MVC.COLOR_BG);
		UIManager.put("Panel.background", MVC.COLOR_BG);
		UIManager.put("Button.background",Color.WHITE);
		UIManager.put("OptionPane.messageFont", MVC.FUENTE);
		UIManager.put("OptionPane.messageForeground", MVC.COLOR_HIGHLIGHT);
		UIManager.put("OptionPane.buttonFont", MVC.FUENTE);
		UIManager.put("Label.foreground", MVC.COLOR_HIGHLIGHT);		    
		UIManager.put("TextField.Background", MVC.COLOR_VALID);
		
		//seleccionador
		if (seleccionador.showOpenDialog(vistaConfig) == JFileChooser.APPROVE_OPTION) {
		      setRutaInfraccion(seleccionador.getSelectedFile().getAbsolutePath());
		      
		      iniciar();
		} else {
		      System.out.println("No se selecciono nada");
		}
	};
	
	public static String getRutaDefault() {
		return (new File(MVC.getConfig().getProperty("ruta_default"))).getAbsolutePath().toString();
	}
	
	public void hidePanel(JPanel panel) {
		vistaConfig.setSize(vistaConfig.getWidth(), vistaConfig.getHeight()-panel.getHeight());
		panel.setVisible(false);
		//vistaConfig.remove(panel);
		
	}
	
	public void showPanel(JPanel panel) {
		vistaConfig.setSize(vistaConfig.getWidth(), vistaConfig.getHeight()+panel.getHeight());
		panel.setVisible(true);
		//vistaConfig.remove(panel);
		
	}
	
	public void switchPanel(JButton b, JPanel panel) {
		if(b.getText().equals("▲")) {
			hidePanel(panel);
			b.setText("▼");
		}else {
			b.setText("▲");
			showPanel(panel);
		}
	}

	public boolean validaRuta(String ruta) {
		File directorio= new File(ruta);
		return directorio.exists();
	}
	
	public boolean comparaPasswords(JPasswordField p1, JPasswordField p2) {
		String pass1= new String(p1.getPassword());
		String pass2= new String(p1.getPassword());
		return pass1.contentEquals(pass2);
	}
	
	public void cambiaUsuario() {
		if(comparaPasswords((JPasswordField)vistaConfig.txtPass,(JPasswordField)vistaConfig.txtConf)) {
			if(!vistaConfig.txtNewName.getText().isEmpty() && !vistaConfig.txtNewPass.getText().isEmpty() && !vistaConfig.txtNewConf.getText().isEmpty() ) {
				if(comparaPasswords((JPasswordField)vistaConfig.txtNewPass,(JPasswordField)vistaConfig.txtNewConf)) {
					String prevUser = vistaConfig.txtName.getText();
					String prevPass= new String(((JPasswordField)vistaConfig.txtPass).getPassword());
					MVC.getConfig().setProperty("usuario", vistaConfig.txtNewName.getText());
					MVC.getConfig().setProperty("contraseña", new String(((JPasswordField)vistaConfig.txtNewPass).getPassword()));
					try {
						MVC.saveConfig();						
					} catch (IOException e) {
						// TODO Auto-generated catch block						
						JOptionPane.showMessageDialog(vistaConfig, "Ocurrio un error, no se pudieron guardar los cambios", "Error de Guardado", JOptionPane.ERROR_MESSAGE);
						MVC.getConfig().setProperty("usuario", prevUser);
						MVC.getConfig().setProperty("contraseña", prevPass);
						e.printStackTrace();
					}finally {
						iniciar();
					}
				}else 
					JOptionPane.showMessageDialog(vistaConfig, "Verifique su contraseña", "Error de validación", JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(vistaConfig, "Faltan datos", "Error de validación", JOptionPane.ERROR_MESSAGE);
			}
		}else JOptionPane.showMessageDialog(vistaConfig, "Confirmación de contaseña no coincide", "Error de validación", JOptionPane.ERROR_MESSAGE);
	}
	
}
