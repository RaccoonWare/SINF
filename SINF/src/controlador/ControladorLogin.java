package controlador;

import vista.VistaLogin;
import vista.VistaPrincipal;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

import modelo.ModeloLogin;
import modelo.ModeloPrincipal;


public class ControladorLogin implements ActionListener, FocusListener, KeyListener, MouseListener {
	private VistaLogin vistaLogin;
	private ModeloLogin modeloLogin;
	
	public ControladorLogin(VistaLogin vista, ModeloLogin modelo) {
		vistaLogin= vista;
		modeloLogin= modelo;
		iniciarListeners();
	}
	private void iniciarListeners() {
		vistaLogin.txtUsuario.addActionListener(this);
		vistaLogin.txtUsuario.addFocusListener(this);
		vistaLogin.txtUsuario.addKeyListener(this);
		vistaLogin.txtUsuario.addMouseListener(this);		
		
		vistaLogin.txtPass.addActionListener(this);
		vistaLogin.txtPass.addFocusListener(this);
		vistaLogin.txtPass.addKeyListener(this);
		vistaLogin.txtPass.addMouseListener(this);
		
		vistaLogin.btnEnviar.addActionListener(this);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub			
		vistaLogin.txtUsuario.setEnabled(false);
		vistaLogin.txtPass.setEnabled(false);
		//if(true){
		if(modeloLogin.validaLogin(vistaLogin)) {
			VistaPrincipal vistaMain=  new VistaPrincipal();
			ModeloPrincipal modeloMain= new ModeloPrincipal(vistaMain);
			ControladorPrincipal controladorMain= new ControladorPrincipal(vistaMain,modeloMain);
			controladorMain.iniciar();
			vistaLogin.dispose();
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
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub
		if(m.getSource() instanceof JTextComponent && !m.getComponent().hasFocus() && !m.getComponent().getBackground().equals(MVC.COLOR_INVALID)) {
			m.getComponent().setBackground(MVC.COLOR_HIGHLIGHT);
		}
	}
	@Override
	public void mouseExited(MouseEvent m) {
		// TODO Auto-generated method stub
		if(m.getSource() instanceof JTextComponent && !m.getComponent().hasFocus()) {
			if(!m.getComponent().getBackground().equals(MVC.COLOR_INVALID)) MVC.coloreaCampo(m.getComponent(), MVC.COLOR_VALID);
		}
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
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
	}
	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
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
		}else if(k.getKeyCode() != KeyEvent.VK_SHIFT) {
			MVC.coloreaCampo(k.getComponent(), MVC.COLOR_INVALID);
		}
	}
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
	}
	@Override
	public void focusGained(FocusEvent f) {
		// TODO Auto-generated method stub
		if(f.getSource() instanceof JTextComponent)
			((JTextComponent)f.getSource()).setBackground(Color.white);
	}
	@Override
	public void focusLost(FocusEvent f) {
		// TODO Auto-generated method stub
		if(f.getSource() instanceof JTextComponent && !f.getComponent().getBackground().equals(MVC.COLOR_INVALID))
			((JTextComponent)f.getSource()).setBackground(MVC.COLOR_VALID);	
	};
	
}
