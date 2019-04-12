package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.border.MatteBorder;

import modelo.*;
import vista.*;

public class ControladorPrincipal implements ActionListener, MouseListener{
	private VistaPrincipal vistaPrincipal;
	private ModeloPrincipal modeloPrincipal;
	public ControladorPrincipal(VistaPrincipal VistaPrincipal, ModeloPrincipal modeloPrincipal) {
		this.vistaPrincipal=VistaPrincipal;
		this.modeloPrincipal= modeloPrincipal;
		agregarListeners();
	}
	
	/////////////Inicializacion de elementos
	
	public void iniciar() {
		modeloPrincipal.iniciar(vistaPrincipal);
	}
	
	private void agregarListeners() {
		this.vistaPrincipal.btnInfracciones.addActionListener(this);
		this.vistaPrincipal.btnConsultar.addActionListener(this);
		this.vistaPrincipal.btnEtiquetas.addActionListener(this);
		this.vistaPrincipal.btnArticulos.addActionListener(this);
		this.vistaPrincipal.btnRestaurar.addActionListener(this);
		this.vistaPrincipal.btnConfiguracin.addActionListener(this);
		this.vistaPrincipal.btnRespaldar.addActionListener(this);
		this.vistaPrincipal.btnEstadisticas.addActionListener(this);
		
		this.vistaPrincipal.btnInfracciones.addMouseListener(this);
		this.vistaPrincipal.btnConsultar.addMouseListener(this);
		this.vistaPrincipal.btnEtiquetas.addMouseListener(this);
		this.vistaPrincipal.btnArticulos.addMouseListener(this);
		this.vistaPrincipal.btnRestaurar.addMouseListener(this);
		this.vistaPrincipal.btnConfiguracin.addMouseListener(this);
		this.vistaPrincipal.btnRespaldar.addMouseListener(this);
		this.vistaPrincipal.btnEstadisticas.addMouseListener(this);
				
	}
	
	
	/////////////Manejo de Eventos
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==vistaPrincipal.btnEtiquetas) {
			if (vistaPrincipal.btnConsultar.getText().equals("")) {
				modeloPrincipal.vaciarEtiquetas(vistaPrincipal);
			}else {
				modeloPrincipal.llenarEtiquetas(vistaPrincipal);
			}
		}else if (arg0.getSource()==vistaPrincipal.btnInfracciones) {
			modeloPrincipal.iniciarInfracciones(vistaPrincipal);
		}else if (arg0.getSource()==vistaPrincipal.btnConsultar) {
			modeloPrincipal.iniciarConsultaInfracciones(vistaPrincipal);
		}else if (arg0.getSource()==vistaPrincipal.btnArticulos) {
			modeloPrincipal.iniciarConsultaArticulos(vistaPrincipal);
		}else if (arg0.getSource()==vistaPrincipal.btnRestaurar) {

		}else if (arg0.getSource()==vistaPrincipal.btnRespaldar) {

		}else if (arg0.getSource()==vistaPrincipal.btnConfiguracin){

		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		if (arg0.getSource() instanceof AbstractButton) {
			((AbstractButton)arg0.getSource()).setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		}
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		if (arg0.getSource() instanceof AbstractButton) {
			((AbstractButton)arg0.getSource()).setBorder(null);
		}
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}

}
