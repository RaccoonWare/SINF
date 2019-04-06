package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import modelo.ModeloConsultaArticulos;
import vista.VistaConsultaArticulo;
import vista.VistaPrincipal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControladorConsultaArticulos implements ActionListener,FocusListener,KeyListener,MouseListener{
	ModeloConsultaArticulos modeloConsultas = new ModeloConsultaArticulos();
	VistaConsultaArticulo vistaConsultas= new VistaConsultaArticulo();
	JFileChooser selecArchivo = new JFileChooser();
	File archivo;
	int contAccion=0;

	public ControladorConsultaArticulos(VistaConsultaArticulo vistaConsultas, ModeloConsultaArticulos modeloConsultas){
		this.vistaConsultas= vistaConsultas;
		this.vistaConsultas.addFocusListener(this);
		this.vistaConsultas.addMouseListener(this);
		for(Component o: this.vistaConsultas.getComponents() ) {
			//if(o.equals(vistaConsultas.tabla) {
				((Component) o).addFocusListener(this);
				//((Component) o).addMouseListener(this);
			//}
			
		}
			
		this.modeloConsultas=modeloConsultas;
		this.vistaConsultas.btnAccion.addActionListener(this);
		this.vistaConsultas.tabla.addFocusListener((FocusListener) this);
		//this.vistaConsultas.tabla.addSelectionListener(this);
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

		if(e.getSource() == vistaConsultas.btnAccion){
			if(vistaConsultas.tabla.getSelectedRow()>-1) {
				System.out.println("agregar datos");
			}else {
				System.out.println("Eliminar datos");
			}
			System.out.println("Agregando");
			
			//archivo=new File(MVC.getConfig().getProperty("articulos"));
			//modeloConsultas.Importar(archivo, vistaConsultas.tabla);
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
	@Override
	public void focusGained(FocusEvent f) {
		// TODO Auto-generated method stub
		if(f.getSource().equals(vistaConsultas.tabla)||f.getSource().equals(vistaConsultas.tabla.getComponents())) {
			int row= vistaConsultas.tabla.getSelectedRow();
			vistaConsultas.btnAccion.setText("Quitar");
			vistaConsultas.txtArt.setText((String) vistaConsultas.tabla.getValueAt(row,0));
			vistaConsultas.txtDesc.setText((String) vistaConsultas.tabla.getValueAt(row,1));
			vistaConsultas.txtSanc.setText((String) vistaConsultas.tabla.getValueAt(row,2));
		
		}else {
			vistaConsultas.tabla.clearSelection();
			
		}
		
	}
	@Override
	public void focusLost(FocusEvent e) {		
		// TODO Auto-generated method stub
		if(e.getSource().equals(vistaConsultas.tabla)) {
			vistaConsultas.btnAccion.setText("Agregar");
			vistaConsultas.tabla.clearSelection();
			vistaConsultas.txtArt.setText("");
			vistaConsultas.txtDesc.setText("");
			vistaConsultas.txtSanc.setText("");
		}
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub		
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(vistaConsultas)) {
			if(vistaConsultas.tabla.getSelectedRow()>=0) {
				vistaConsultas.btnAccion.setText("Agregar");
				vistaConsultas.tabla.clearSelection();
				vistaConsultas.txtArt.setText("");
				vistaConsultas.txtDesc.setText("");
				vistaConsultas.txtSanc.setText("");
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
