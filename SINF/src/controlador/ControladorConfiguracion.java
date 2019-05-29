package controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modelo.ModeloConfiguracion;
import modelo.ModeloLogin;
import modelo.ModeloPrincipal;
import vista.VistaConfiguracion;
import vista.VistaConsultaInfraccion;
import vista.VistaPrincipal;

public class ControladorConfiguracion implements ActionListener, FocusListener, KeyListener, MouseListener {
	//Variables de Instancia
	private ModeloConfiguracion modeloConfig;
	private VistaConfiguracion vistaConfig;
	private VistaPrincipal ventana;
	//private 
	//////////////////Constructores e iniciadores////////////////////
	
	public ControladorConfiguracion(VistaConfiguracion vistaConfig, ModeloConfiguracion modeloConfig, VistaPrincipal vistaPrincipal) {	
		
		this.vistaConfig= vistaConfig;		
		this.modeloConfig= modeloConfig;		
		ventana= vistaPrincipal;
		
		iniciar();		
		addListeners();
		VistaPrincipal.dpEscritorio.add(vistaConfig);
		vistaConfig.toFront();
	}
	
	public void iniciar() {
		modeloConfig.iniciar();
		vistaConfig.show();		
		modeloConfig.switchPanel(vistaConfig.btnRutas,vistaConfig.panelDatosUbicacion);
		modeloConfig.switchPanel(vistaConfig.btnUsuario,vistaConfig.panelDatosUsuario);
		
	}
	/**
	 * Agrega los listeners
	 */
	private void addListeners(){
		vistaConfig.txtRutaArts.addMouseListener(this);
		vistaConfig.txtRutaArts.addKeyListener(this);
		vistaConfig.txtRutaArts.addFocusListener(this);
		vistaConfig.txtRutaInfs.addMouseListener(this);
		vistaConfig.txtRutaInfs.addKeyListener(this);
		vistaConfig.txtRutaInfs.addFocusListener(this);
		//vistaConfig.txtName.addMouseListener(this);
		//vistaConfig.txtName.addKeyListener(this);
		//vistaConfig.txtName.addFocusListener(this);
		//vistaConfig.txtPass.addMouseListener(this);
		//vistaConfig.txtPass.addKeyListener(this);
		//vistaConfig.txtPass.addFocusListener(this);
		vistaConfig.txtConf.addMouseListener(this);
		vistaConfig.txtConf.addKeyListener(this);
		vistaConfig.txtConf.addFocusListener(this);
		vistaConfig.txtNewName.addMouseListener(this);
		vistaConfig.txtNewName.addKeyListener(this);
		vistaConfig.txtNewName.addFocusListener(this);
		vistaConfig.txtNewPass.addMouseListener(this);
		vistaConfig.txtNewPass.addKeyListener(this);
		vistaConfig.txtNewPass.addFocusListener(this);
		vistaConfig.txtNewConf.addMouseListener(this);
		vistaConfig.txtNewConf.addKeyListener(this);
		vistaConfig.txtNewConf.addFocusListener(this);
		vistaConfig.txtNewConf.addMouseListener(this);
		vistaConfig.txtNewConf.addKeyListener(this);
		vistaConfig.txtNewConf.addFocusListener(this);
		vistaConfig.txtRutaImg.addMouseListener(this);
		vistaConfig.txtRutaImg.addKeyListener(this);
		vistaConfig.txtRutaImg.addFocusListener(this);
		
		vistaConfig.btnRutas.addActionListener(this);
		vistaConfig.btnUsuario.addActionListener(this);
		vistaConfig.btnImagen.addActionListener(this);
		vistaConfig.btnInfs.addActionListener(this);
		vistaConfig.btnArts.addActionListener(this);
		vistaConfig.btnConfirmar.addActionListener(this);
		vistaConfig.btnImg.addActionListener(this);
		vistaConfig.btnDefault.addActionListener(this);
		
		
	}
	//////////////////////Manejo de Eventos////////////////////
	
	////////////Acciones
	/* (non-Javadoc)
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	*/
	@Override
	public void actionPerformed(ActionEvent a) {
	// TODO Auto-generated method stub
		if(a.getSource().equals(vistaConfig.btnRutas)) {
			modeloConfig.switchPanel(vistaConfig.btnRutas,vistaConfig.panelDatosUbicacion);			
		}else if (a.getSource().equals(vistaConfig.btnUsuario)) {
			modeloConfig.switchPanel(vistaConfig.btnUsuario,vistaConfig.panelDatosUsuario);
		}else if (a.getSource().equals(vistaConfig.btnImagen)) {
			modeloConfig.switchPanel(vistaConfig.btnImagen,vistaConfig.panelRutaImagen);
		}else if(a.getSource().equals(vistaConfig.btnDefault))
			modeloConfig.defaultValues();
		else if(a.getSource().equals(vistaConfig.btnConfirmar)) {
			modeloConfig.cambiaUsuario();
		}else if(a.getSource().equals(vistaConfig.btnArts)){			
			modeloConfig.selectRutaArticulo();			
		}else if(a.getSource().equals(vistaConfig.btnInfs)){			
			modeloConfig.selectRutaInfraccion();			
		}else if(a.getSource().equals(vistaConfig.btnImg)) {
			modeloConfig.selectImagen(ventana);

		}
	}//fin actionPerformed
	
	/////////Manejo de componentos 
	/**
	* Evento cuando un componente gana el foco
	*/
	@Override
	public void focusGained(FocusEvent f) {
		if(f.getComponent() instanceof JTextField && !f.getComponent().getBackground().equals(MVC.COLOR_INVALID))
			f.getComponent().setBackground(Color.WHITE);
	}//Fin  FocusGained
	
	/** 
	* Evento Perdida de Foco
	*/
	@Override
	public void focusLost(FocusEvent f) {
	// TODO Auto-generated method stub
		if(f.getComponent() instanceof JTextField && !f.getComponent().getBackground().equals(MVC.COLOR_INVALID))
			f.getComponent().setBackground(MVC.COLOR_VALID);
	}//Fin FocusLost
	
	/////////////Eventos del teclado
	
	/* (non-Javadoc)
	* @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	*/
	@Override
	public void keyPressed(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
	}
	
	/**
	* Manejador de ventos de tecla
	*/
	@Override
	public void keyReleased(KeyEvent k) {
	// TODO Auto-generated method stub
		if(k.getSource().equals(vistaConfig.txtRutaArts)){
			if(modeloConfig.validaRuta(vistaConfig.txtRutaArts.getText())) {
				k.getComponent().setBackground(Color.WHITE);
				modeloConfig.setRutaArticulo(vistaConfig.txtRutaArts.getText());
			}else
				k.getComponent().setBackground(MVC.COLOR_INVALID);
		}else if(k.getSource().equals(vistaConfig.txtRutaInfs)) {
			if(modeloConfig.validaRuta(vistaConfig.txtRutaInfs.getText())) {
				k.getComponent().setBackground(Color.WHITE);
				modeloConfig.setRutaInfraccion(vistaConfig.txtRutaInfs.getText());
				try {
					MVC.saveConfig();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
				k.getComponent().setBackground(MVC.COLOR_INVALID);
		}else if(k.getSource().equals(vistaConfig.txtRutaImg)) {
			if(modeloConfig.validaRuta(vistaConfig.txtRutaImg.getText())) {
				k.getComponent().setBackground(Color.WHITE);
				modeloConfig.setRutaArticulo(vistaConfig.txtRutaImg.getText());
				try {
					MVC.saveConfig();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
				k.getComponent().setBackground(MVC.COLOR_INVALID);
		}else { 
			if(ModeloLogin.validaCampo(k.getComponent()))
				k.getComponent().setBackground(Color.WHITE);
			else
				k.getComponent().setBackground(MVC.COLOR_INVALID);
		}
		if(k.getSource().equals(vistaConfig.txtNewPass )|| k.getSource().equals(vistaConfig.txtNewConf))
			if(!modeloConfig.comparaPasswords((JPasswordField)vistaConfig.txtNewPass,(JPasswordField) vistaConfig.txtNewConf))
				MVC.coloreaCampo(vistaConfig.txtNewConf, MVC.COLOR_INVALID);
	}//Fin KeyReleased
	
	/* (non-Javadoc)
	* @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	*/
	@Override
	public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
	}
	
	/////////////////Eventos Mouse
	/* (non-Javadoc)
	* @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	*/
	@Override
	public void mouseClicked(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
	}
	
	/* (non-Javadoc)
	* @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	*/
	@Override
	public void mouseEntered(MouseEvent m) {
		if(m.getComponent() instanceof JTextField && !m.getComponent().getBackground().equals(MVC.COLOR_INVALID) && !m.getComponent().hasFocus())
			m.getComponent().setBackground(MVC.COLOR_HIGHLIGHT);
	}
	
	/* (non-Javadoc)
	* @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	*/
	@Override
	public void mouseExited(MouseEvent m) {
		if(m.getComponent() instanceof JTextField && !m.getComponent().getBackground().equals(MVC.COLOR_INVALID) && !m.getComponent().hasFocus())
			m.getComponent().setBackground(MVC.COLOR_VALID);
	}
	
	/* (non-Javadoc)
	* @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	*/
	@Override
	public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
	}
	
	/* (non-Javadoc)
	* @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	*/
	@Override
	public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
	}

}//Fin Clase principal
