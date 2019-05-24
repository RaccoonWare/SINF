/**
 * Controlador de eventos de la ventana de Login
 * @author David
 */
package controlador;
/* importar librerias */
import vista.VistaLogin;
import vista.VistaPrincipal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

import modelo.ModeloLogin;
import modelo.ModeloPrincipal;

/**
 * 
 * Clase principal
 * @author David
 * @see ModeloLogin
 * @see VistaLogin
 */
public class ControladorLogin implements ActionListener, FocusListener, KeyListener, MouseListener {
	//variables de instancia
	private VistaLogin vistaLogin;
	private ModeloLogin modeloLogin;
	
	/////////////////Consntructores e iniciadores
	/**
	 * 
	 * @param vista
	 * @param modelo
	 */
	public ControladorLogin(VistaLogin vista, ModeloLogin modelo) {
		vistaLogin= vista;
		modeloLogin= modelo;
		iniciarListeners();
	}
	/**
	 * inicia los listeners en la ventana
	 */
	private void iniciarListeners() {
		//listeners del campo de usuaro
		vistaLogin.txtUsuario.addActionListener(this);
		vistaLogin.txtUsuario.addFocusListener(this);
		vistaLogin.txtUsuario.addKeyListener(this);
		vistaLogin.txtUsuario.addMouseListener(this);		
		//listeners del campo de contraseña
		vistaLogin.txtPass.addActionListener(this);
		vistaLogin.txtPass.addFocusListener(this);
		vistaLogin.txtPass.addKeyListener(this);
		vistaLogin.txtPass.addMouseListener(this);
		//listener del boton
		vistaLogin.btnEnviar.addActionListener(this);
		
		
	}//fin Iniciar Listeners
	
	/////////////////Manejo de eventos//////////////////
	/**
	 * gestor de eventos
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub			
		vistaLogin.txtUsuario.setEnabled(false);
		vistaLogin.txtPass.setEnabled(false);
		//if(true){
		//Valida login
		if(modeloLogin.validaLogin()) {
			VistaPrincipal vistaMain=  new VistaPrincipal();
			ModeloPrincipal modeloMain= new ModeloPrincipal(vistaMain);
			ControladorPrincipal controladorMain= new ControladorPrincipal(vistaMain,modeloMain);
			controladorMain.iniciar();
			vistaLogin.dispose();
		//en caso que el login no funcione manda mensaje de error
		}else {
			UIManager.put("OptionPane.background", MVC.COLOR_BG);
		    UIManager.put("Panel.background", MVC.COLOR_BG);
		    UIManager.put("Button.background",Color.WHITE);
		    UIManager.put("OptionPane.messageFont", MVC.FUENTE);
		    UIManager.put("OptionPane.messageForeground", MVC.COLOR_HIGHLIGHT);
		    UIManager.put("OptionPane.buttonFont", MVC.FUENTE);
			JOptionPane.showMessageDialog(vistaLogin, "Error de Identificacion");
			vistaLogin.txtUsuario.setEnabled(true);
			vistaLogin.txtPass.setEnabled(true);
		}
	}//fin actionPerfomred

	///////////Eventos del Mouse
	
	/**
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * evento mouse entra al aera de un componente
	 * precondición: el componente no se encuentra con foco
	 */
	@Override
	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub
		if(m.getSource() instanceof JTextComponent && !m.getComponent().hasFocus() && !m.getComponent().getBackground().equals(MVC.COLOR_INVALID)) {
			m.getComponent().setBackground(MVC.COLOR_HIGHLIGHT);
		}
	}//fin mouseEntered
	
	/**
	 * evento mouse abandoneado componente
	 * restaura el comonenete a su color normal
	 * pre-condición, que el componente no se encuentre ene estado de error
	 */
	@Override
	public void mouseExited(MouseEvent m) {
		// TODO Auto-generated method stub
		if(m.getSource() instanceof JTextComponent && !m.getComponent().hasFocus()) {
			if(!m.getComponent().getBackground().equals(MVC.COLOR_INVALID)) MVC.coloreaCampo(m.getComponent(), MVC.COLOR_VALID);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	////////////Eventos Teclado
	/**
	 * tecla presionada
	 * originalemtne aqui trataba de realizar aquí las validaciones, pero debido a una desincronización con ls verificaciones se movieron las erificaciones a Keyreleased
	 */
	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		/*System.out.println(""+k.getKeyChar());
		System.out.println(""+ modeloLogin.validaCampo(k.getComponent()));
		if(Character.isLetterOrDigit(k.getKeyChar())){
			if(modeloLogin.validaCampo(k.getComponent()))
				MVC.coloreaCampo(k.getComponent(), Color.WHITE);
			else {
				MVC.coloreaCampo(k.getComponent(), MVC.COLOR_INVALID);
			}			
		}else if (k.getKeyCode() == KeyEvent.VK_BACK_SPACE) {			
			if(modeloLogin.validaCampo(k.getComponent()))
				MVC.coloreaCampo(k.getComponent(), Color.WHITE);
			else {
				MVC.coloreaCampo(k.getComponent(), MVC.COLOR_INVALID);
			}	
		}else {
			MVC.coloreaCampo(k.getComponent(), MVC.COLOR_INVALID);
		}*/
	}//fin KeyPressed
	/**
	 * Evento Tecla liberada
	 */
	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		/////Tecla numero o letra: valida campo
		if(Character.isLetterOrDigit(k.getKeyChar())){
			if(modeloLogin.validaCampo(k.getComponent()))
				MVC.coloreaCampo(k.getComponent(), Color.WHITE);
			else {
				MVC.coloreaCampo(k.getComponent(), MVC.COLOR_INVALID);
			}			
		//Tecla retroceso: valida si el texto reducido es valido o si o no
		}else if (k.getKeyCode() == KeyEvent.VK_BACK_SPACE) {			
			if(modeloLogin.validaCampo(k.getComponent()))
				MVC.coloreaCampo(k.getComponent(), Color.WHITE);
			else {
				MVC.coloreaCampo(k.getComponent(), MVC.COLOR_INVALID);
			}	
		//Tecla escape: cierra
		}else if (k.getKeyCode() == KeyEvent.VK_ESCAPE){
			vistaLogin.dispose();
		//teclas auxiliares: las ignora
			//todas las demas teclas:m da error
			//lo modifique para que haga el chequeo del texto en vez de las teclas para el caso que se presionen teclas especiales que no alteran el contenido del campo
		}else if(k.getSource() instanceof JTextField) {
			if(k.getKeyCode() != KeyEvent.VK_SHIFT && k.getKeyCode() != KeyEvent.VK_CONTROL && k.getKeyCode() != KeyEvent.VK_DEAD_TILDE && k.getKeyCode() != KeyEvent.VK_KP_UP && k.getKeyCode() != KeyEvent.VK_DOWN && k.getKeyCode() != KeyEvent.VK_LEFT && k.getKeyCode() != KeyEvent.VK_RIGHT ) {
				if(modeloLogin.validaCampo(k.getComponent()))
					MVC.coloreaCampo(k.getComponent(), MVC.COLOR_INVALID);
				else
					MVC.coloreaCampo(k.getComponent(), Color.WHITE);
			}
		}
			
	}//fin keyReleased
	/**
	 * tecla tipeada
	 * originalemtne aqui trataba de realizar aquí las validaciones, pero debido a una desincronización con ls verificaciones se movieron las erificaciones a Keyreleased
	 */
	@Override
	public void keyTyped(KeyEvent k) {
		//
		// TODO Auto-generated method stub		
		/*System.out.println(""+k.getKeyChar());
		System.out.println(""+ modeloLogin.validaCampo(k.getComponent()));
		if(Character.isLetterOrDigit(k.getKeyChar())){
			if(modeloLogin.validaCampo(k.getComponent()))
				MVC.coloreaCampo(k.getComponent(), Color.WHITE);
			else {
				MVC.coloreaCampo(k.getComponent(), MVC.COLOR_INVALID);
			}			
		}else if (k.getKeyCode() == KeyEvent.VK_BACK_SPACE) {			
			if(modeloLogin.validaCampo(k.getComponent()))
				MVC.coloreaCampo(k.getComponent(), Color.WHITE);
			else {
				MVC.coloreaCampo(k.getComponent(), MVC.COLOR_INVALID);
			}	
		}else {
			MVC.coloreaCampo(k.getComponent(), MVC.COLOR_INVALID);
		}	*/			
	}//fin keyTyped
	
	//////Eventos de foco
	/**
	 * evento Foco adquirido
	 * el elemento se colorea de blanco
	 * 
	 */
	@Override
	public void focusGained(FocusEvent f) {
		// TODO Auto-generated method stub
		if(f.getSource() instanceof JTextComponent)
			((JTextComponent)f.getSource()).setBackground(Color.white);
	}//fin focusGained
	/**
	 * Evento foco perdido
	 * El elemento se colorea dependiendo del estado de validez del contenido(verde si es valido, rojo si es invalido)
	 */
	@Override
	public void focusLost(FocusEvent f) {
		// TODO Auto-generated method stub
		if(f.getSource() instanceof JTextComponent && !f.getComponent().getBackground().equals(MVC.COLOR_INVALID))
			((JTextComponent)f.getSource()).setBackground(MVC.COLOR_VALID);	
	};//fin focusLost
	
}//fin clase principal
