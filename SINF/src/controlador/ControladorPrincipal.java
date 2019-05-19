/**
 * Controlador de eventos de la ventna principal
 * @author Mario
 * @author David
 */
package controlador;
/* importación de librerias */
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import modelo.*;
import vista.*;
/**
 * 
 * @author Mario
 * @see ModeloPrincipal
 * @see VistaPrnicpal
 */
public class ControladorPrincipal implements ActionListener, MouseListener{
	//variables de instancia
	private VistaPrincipal vistaPrincipal;
	private ModeloPrincipal modeloPrincipal;
	
	//////////////Constructores e inicializadores
	/**
	 * constructor por defecto
	 * @param VistaPrincipal
	 * @param modeloPrincipal
	 */
	public ControladorPrincipal(VistaPrincipal VistaPrincipal, ModeloPrincipal modeloPrincipal) {
		this.vistaPrincipal=VistaPrincipal;
		this.modeloPrincipal= modeloPrincipal;
		agregarListeners();
	}//fin constructor por defecto

	/**
	 * Inicializador del modelo
	 */
	public void iniciar() {
		modeloPrincipal.iniciar(vistaPrincipal);
	}//fin iniciar
	
	/**
	 * Inicializa los listeners a la ventana
	 */
	private void agregarListeners() {
		//actionListenres
		this.vistaPrincipal.btnInfracciones.addActionListener(this);
		this.vistaPrincipal.btnConsultar.addActionListener(this);
		this.vistaPrincipal.btnEtiquetas.addActionListener(this);
		this.vistaPrincipal.btnArticulos.addActionListener(this);
		this.vistaPrincipal.btnRestaurar.addActionListener(this);
		this.vistaPrincipal.btnConfiguracin.addActionListener(this);
		this.vistaPrincipal.btnRespaldar.addActionListener(this);
		//MouseListeners
		this.vistaPrincipal.btnInfracciones.addMouseListener(this);
		this.vistaPrincipal.btnConsultar.addMouseListener(this);
		this.vistaPrincipal.btnEtiquetas.addMouseListener(this);
		this.vistaPrincipal.btnArticulos.addMouseListener(this);
		this.vistaPrincipal.btnRestaurar.addMouseListener(this);
		this.vistaPrincipal.btnConfiguracin.addMouseListener(this);
		this.vistaPrincipal.btnRespaldar.addMouseListener(this);
				
	}//fin agregarListeners
	
	
	/////////////Manejo de Eventos
	/**
	 * Action performed
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		/////////Origenn boton Etiquetas
		if (arg0.getSource()==vistaPrincipal.btnEtiquetas) {
			//si solo son los iconos agrega texto
			if (vistaPrincipal.btnConsultar.getText().equals("")) {
				modeloPrincipal.llenarEtiquetas(vistaPrincipal);
			//si los botones tienen texto lo oculta
			}else {
				modeloPrincipal.vaciarrEtiquetas(vistaPrincipal);
			}
		//////////////Origen botones ventanas, llama a la ventana correspondeinte
		/////////Origen boton Formulario de infraciones
		}else if (arg0.getSource()==vistaPrincipal.btnInfracciones) {
			modeloPrincipal.iniciarInfracciones(vistaPrincipal);
			/////////Origen boton Consulta infracciones
		}else if (arg0.getSource()==vistaPrincipal.btnConsultar) {
			modeloPrincipal.iniciarConsultaInfracciones(vistaPrincipal);
			/////////Origen boton Articulos
		}else if (arg0.getSource()==vistaPrincipal.btnArticulos) {			
			modeloPrincipal.iniciarConsultaArticulos(vistaPrincipal);			
		///////////////Origen botones respaldo archivos	
			///////Origen boton restaurar
		}else if (arg0.getSource()==vistaPrincipal.btnRestaurar) {								   
			modeloPrincipal.restaurar((Container)vistaPrincipal);
			/////////Origen boton REspaldar
		}else if (arg0.getSource()==vistaPrincipal.btnRespaldar) {
			try{
				modeloPrincipal.respaldar(vistaPrincipal);				
			//manejo de errores
			}catch(IOException e) {
				e.printStackTrace();
				//se lanzara un mensaje de error, se sobreescribe primero la interfaz
				UIManager.put("OptionPane.background", MVC.COLOR_BG);
			    UIManager.put("Panel.background", MVC.COLOR_BG);
			    UIManager.put("Button.background",Color.WHITE);
			    UIManager.put("OptionPane.messageFont", MVC.FUENTE);
			    UIManager.put("OptionPane.messageForeground", MVC.COLOR_HIGHLIGHT);
			    UIManager.put("OptionPane.buttonFont", MVC.FUENTE);
				JOptionPane.showMessageDialog(vistaPrincipal, "Error, no se pudo exportar los archivos");
			}
		}else if (arg0.getSource()==vistaPrincipal.btnConfiguracin){
			modeloPrincipal.iniciarConfiguracion(vistaPrincipal);
		}
	}//fin actionPerformed

	/////////Eventos del raton
	/**
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}
	
	/**
	 * Evento cuando el cursos se situa sobre un botón
	 * se le agrega un borde
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		if (arg0.getSource() instanceof AbstractButton) {
			((AbstractButton)arg0.getSource()).setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		}
	}//fin mouse Entered
	
	/**
	 * evento de cuando el cursor sale del area de un componente
	 * quita el borde
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		if (arg0.getSource() instanceof AbstractButton) {
			((AbstractButton)arg0.getSource()).setBorder(null);
		}		
	}//fin mouseExited	
	/**
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}
	/**
	 * 
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}

}//fin clase principal
