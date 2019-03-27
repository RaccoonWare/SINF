package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import modelo.ModeloConsultaArticulos;
import vista.VistaConsultaArticulo;
import vista.VistaPrincipal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControladorConsultaArticulos implements ActionListener{
	ModeloConsultaArticulos modeloConsultas = new ModeloConsultaArticulos();
	VistaConsultaArticulo vistaConsultas= new VistaConsultaArticulo();
	JFileChooser selecArchivo = new JFileChooser();
	File archivo;
	int contAccion=0;

	public ControladorConsultaArticulos(VistaConsultaArticulo vistaConsultas, ModeloConsultaArticulos modeloConsultas){
		this.vistaConsultas= vistaConsultas;
		this.modeloConsultas=modeloConsultas;
		this.vistaConsultas.btnConsultar.addActionListener(this);
		//this.vistaConsultas.btnRecargar.addActionListener(this);
	}
	public void iniciar() {
		VistaPrincipal.dpEscritorio.add(vistaConsultas);
		vistaConsultas.show();
		archivo=new File(MVC.getConfig().getProperty("articulos"));
		modeloConsultas.Importar(archivo, vistaConsultas.tabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		contAccion++;

		if(e.getSource() == vistaConsultas.btnConsultar){
			archivo=new File(MVC.getConfig().getProperty("articulos"));
			modeloConsultas.Importar(archivo, vistaConsultas.tabla);
		}

//		if(e.getSource() == vistaConsultas.btnRecargar){
//			if(selecArchivo.showDialog(null, "Exportar")==JFileChooser.APPROVE_OPTION){
//				archivo=selecArchivo.getSelectedFile();
//				if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
//					JOptionPane.showMessageDialog(null, modeloConsultas.Exportar(archivo, vistaConsultas.tabla) + "\n Formato ."+ archivo.getName().substring(archivo.getName().lastIndexOf(".")+1));
//				}else{
//					JOptionPane.showMessageDialog(null, "Elija un formato valido.");
//				}
//			}
//		}
	}
}
