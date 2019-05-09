package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

import controlador.ControladorBoletaInfraccion;
//import vista.BoletaInfraccion;
import vista.VistaInfraccion;

public class ModeloBoletaInfraccion implements ActionListener{
	private VistaInfraccion VistaBoleta;
	private ControladorBoletaInfraccion ControladorBoleta;
	public ModeloBoletaInfraccion(VistaInfraccion VistaBoleta,ControladorBoletaInfraccion ControladorBoleta) {
		this.VistaBoleta=VistaBoleta;
		this.ControladorBoleta=ControladorBoleta;
		this.VistaBoleta.btnInsertar.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Apéndice de método generado automáticamente
		
		
	}

}
