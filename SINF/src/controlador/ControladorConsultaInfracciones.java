package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import modelo.ModeloConsultaInfracciones;
import vista.VistaConsultaInfraccion;
import vista.VistaPrincipal;
import javax.swing.*;

public class ControladorConsultaInfracciones implements ActionListener{
	private ModeloConsultaInfracciones modeloConsultas = new ModeloConsultaInfracciones();
	private VistaConsultaInfraccion vistaConsultas= new VistaConsultaInfraccion();
	private JFileChooser selecArchivo = new JFileChooser();
	private File archivo;
	int contAccion=0;

	public ControladorConsultaInfracciones(VistaConsultaInfraccion vistaConsultas, ModeloConsultaInfracciones modeloConsultas){
		this.vistaConsultas= vistaConsultas;
		this.modeloConsultas=modeloConsultas;
		this.vistaConsultas.btnConsultar.addActionListener(this);
		//this.vistaConsultas.btnAgregar.addActionListener(this);
	}
	public void iniciar() {
		VistaPrincipal.dpEscritorio.add(vistaConsultas);
		vistaConsultas.show();
		archivo=new File(MVC.getConfig().getProperty("infracciones"));
		modeloConsultas.Importar(archivo, vistaConsultas.tabla);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		contAccion++;
		if(e.getSource() == vistaConsultas.btnConsultar){
			archivo=new File(MVC.getConfig().getProperty("infracciones"));
			modeloConsultas.Importar(archivo, vistaConsultas.tabla);
		}

		if(e.getSource() == vistaConsultas.btnAnular){
			archivo=new File(MVC.getConfig().getProperty("infracciones"));
			modeloConsultas.Exportar(archivo, vistaConsultas.tabla);
		}
	}
}
