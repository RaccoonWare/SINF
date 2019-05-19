package modelo;

import java.awt.Color;
import java.awt.Container;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import controlador.MVC;

public class ModeloConfiguracion {
	
	public void establecerRuta(Container c, String propiedad) {
		// TODO Auto-generated method stub
		//varialbes de funci�n
		File directorio= new File(MVC.getConfig().getProperty("respaldos"));//directorio prederterminado para el fileChooser	    
	    JFileChooser seleccionador= new JFileChooser(directorio);//selector de archivos(L&F standard, pero se modifica a la hora de llamarlo
	    //a�ade filtros a file chooser
	    //se modifica los atributos de interfaz estandard para cuando se llamen los cuadors de selecci�n o dialogo
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
	    	
	    	//manejador de archivo de articulos en ubicaci�n a restaurar
	    	MVC.getConfig().setProperty(propiedad, seleccionador.getSelectedFile().getAbsolutePath());
	    	//manejador de infracciones en ubicaci�n a restaurar
	    				
	    };
	    
	}//fin clase restaurar
	
	public void usuarioContrase�a() {
		String contrase�aAct=JOptionPane.showInputDialog("Contrase�a actual:");
		String contrase�a=JOptionPane.showInputDialog("Nueva contrase�a:");
		String usuario=JOptionPane.showInputDialog("Nuevo usuario:");
		if (contrase�aAct.equals(MVC.getConfig().getProperty("contrase�a"))) {
			MVC.getConfig().setProperty("contrase�a", contrase�a);
			MVC.getConfig().setProperty("usuario", usuario);
		}else {
			JOptionPane.showMessageDialog(null, "Contrase�a incorrecta");
		}
	}
}
