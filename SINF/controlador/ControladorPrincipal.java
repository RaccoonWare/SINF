package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.border.MatteBorder;

import modelo.*;
import vista.*;

public class ControladorPrincipal implements ActionListener, MouseListener{
	private VistaPrincipal VistaPrincipal;
	private File archivo;
	public ControladorPrincipal(VistaPrincipal VistaPrincipal) {
		this.VistaPrincipal=VistaPrincipal;
		this.VistaPrincipal.btnInfracciones.addActionListener(this);
		this.VistaPrincipal.btnConsultar.addActionListener(this);
		this.VistaPrincipal.btnEtiquetas.addActionListener(this);
		this.VistaPrincipal.btnArticulos.addActionListener(this);
		this.VistaPrincipal.btnRestaurar.addActionListener(this);
		this.VistaPrincipal.btnConfiguracin.addActionListener(this);
		this.VistaPrincipal.btnRespaldar.addActionListener(this);
		this.VistaPrincipal.btnEstadisticas.addActionListener(this);
		
		this.VistaPrincipal.btnInfracciones.addMouseListener(this);
		this.VistaPrincipal.btnConsultar.addMouseListener(this);
		this.VistaPrincipal.btnEtiquetas.addMouseListener(this);
		this.VistaPrincipal.btnArticulos.addMouseListener(this);
		this.VistaPrincipal.btnRestaurar.addMouseListener(this);
		this.VistaPrincipal.btnConfiguracin.addMouseListener(this);
		this.VistaPrincipal.btnRespaldar.addMouseListener(this);
		this.VistaPrincipal.btnEstadisticas.addMouseListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==VistaPrincipal.btnInfracciones) {
			ModeloInfraccion MI = new ModeloInfraccion();
			VistaInfraccion VI = new VistaInfraccion();
			ControladorInfraccion CI= new ControladorInfraccion(VI, MI);
			CI.iniciar();
		}else if (arg0.getSource()==VistaPrincipal.btnConsultar) {
			ModeloConsultaInfracciones MCI = new ModeloConsultaInfracciones();
			VistaConsultaInfraccion VCI = new VistaConsultaInfraccion();
			ControladorConsultaInfracciones CCI= new ControladorConsultaInfracciones(VCI, MCI);
			CCI.iniciar();
		}else if (arg0.getSource()==VistaPrincipal.btnEtiquetas) {
			if (VistaPrincipal.btnConsultar.getText().equals("")) {
				VistaPrincipal.btnEstadisticas.setText("Estadisticas");
				VistaPrincipal.btnConsultar.setText("Consultar");
				VistaPrincipal.btnInfracciones.setText("Infracciones");
				VistaPrincipal.btnRespaldar.setText("Respaldo");
				VistaPrincipal.btnEtiquetas.setText("");
				VistaPrincipal.btnArticulos.setText("Articulos");
				VistaPrincipal.btnRestaurar.setText("Restaurar");
				VistaPrincipal.btnConfiguracin.setText("Configuración ");
			}else {
				VistaPrincipal.btnEstadisticas.setText("");
				VistaPrincipal.btnConsultar.setText("");
				VistaPrincipal.btnInfracciones.setText("");
				VistaPrincipal.btnRespaldar.setText("");
				VistaPrincipal.btnEtiquetas.setText("");
				VistaPrincipal.btnArticulos.setText("");
				VistaPrincipal.btnRestaurar.setText("");
				VistaPrincipal.btnConfiguracin.setText("");
			}

		}else if (arg0.getSource()==VistaPrincipal.btnArticulos) {
			ModeloConsultaArticulos MCA = new ModeloConsultaArticulos();
			VistaConsultaArticulo VCA = new VistaConsultaArticulo();
			ControladorConsultaArticulos CCA= new ControladorConsultaArticulos(VCA, MCA);
			CCA.iniciar();
		}else if (arg0.getSource()==VistaPrincipal.btnRestaurar) {

		}else if (arg0.getSource()==VistaPrincipal.btnRespaldar) {

		}else if (arg0.getSource()==VistaPrincipal.btnConfiguracin){

		}
	}
	public void iniciar() {
		// TODO Apéndice de método generado automáticamente
		VistaPrincipal.setVisible(true);
		VistaPrincipal.setLocationRelativeTo(null);

	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		if (arg0.getSource()==VistaPrincipal.btnInfracciones) {
			VistaPrincipal.btnInfracciones.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		}else if (arg0.getSource()==VistaPrincipal.btnConsultar) {
			VistaPrincipal.btnConsultar.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		}else if (arg0.getSource()==VistaPrincipal.btnEtiquetas) {
			VistaPrincipal.btnEtiquetas.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		}else if (arg0.getSource()==VistaPrincipal.btnArticulos) {
			VistaPrincipal.btnArticulos.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		}else if (arg0.getSource()==VistaPrincipal.btnRestaurar) {
			VistaPrincipal.btnRestaurar.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		}else if (arg0.getSource()==VistaPrincipal.btnRespaldar) {
			VistaPrincipal.btnRespaldar.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		}else if (arg0.getSource()==VistaPrincipal.btnConfiguracin){
			VistaPrincipal.btnConfiguracin.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		}else if (arg0.getSource()==VistaPrincipal.btnEstadisticas){
			VistaPrincipal.btnEstadisticas.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		}
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		if (arg0.getSource()==VistaPrincipal.btnInfracciones) {
			VistaPrincipal.btnInfracciones.setBorder(null);
		}else if (arg0.getSource()==VistaPrincipal.btnConsultar) {
			VistaPrincipal.btnConsultar.setBorder(null);
		}else if (arg0.getSource()==VistaPrincipal.btnEtiquetas) {
			VistaPrincipal.btnEtiquetas.setBorder(null);
		}else if (arg0.getSource()==VistaPrincipal.btnArticulos) {
			VistaPrincipal.btnArticulos.setBorder(null);
		}else if (arg0.getSource()==VistaPrincipal.btnRestaurar) {
			VistaPrincipal.btnRestaurar.setBorder(null);
		}else if (arg0.getSource()==VistaPrincipal.btnRespaldar) {
			VistaPrincipal.btnRespaldar.setBorder(null);
		}else if (arg0.getSource()==VistaPrincipal.btnConfiguracin){
			VistaPrincipal.btnConfiguracin.setBorder(null);
		}else if (arg0.getSource()==VistaPrincipal.btnEstadisticas){
			VistaPrincipal.btnEstadisticas.setBorder(null);
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
