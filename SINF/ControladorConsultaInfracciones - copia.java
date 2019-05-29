package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.Date;
import java.text.ParseException;

import modelo.ModeloConsultaInfracciones;
import modelo.ModeloInfraccion;
import vista.VistaConsultaInfraccion;
import vista.VistaInfraccion;
import vista.VistaPrincipal;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class ControladorConsultaInfracciones implements ActionListener, MouseListener, KeyListener{
	ModeloConsultaInfracciones modeloConsultas = new ModeloConsultaInfracciones();
	VistaConsultaInfraccion vistaConsultas= new VistaConsultaInfraccion();
	JFileChooser selecArchivo = new JFileChooser();
	ModeloInfraccion MI = new ModeloInfraccion();
	static VistaInfraccion VI = new VistaInfraccion();	
	File archivo;
	int contAccion=0;

	public ControladorConsultaInfracciones(VistaConsultaInfraccion vistaConsultas, ModeloConsultaInfracciones modeloConsultas){
		this.vistaConsultas= vistaConsultas;
		this.modeloConsultas=modeloConsultas;
		this.vistaConsultas.btnConsultar.addActionListener(this);
		this.vistaConsultas.btnAnular.addActionListener(this);
		this.vistaConsultas.btnEliminar.addActionListener(this);
		this.vistaConsultas.btnBuscar.addActionListener(this);
		this.vistaConsultas.cbRegistros.addActionListener(this);

		this.vistaConsultas.txtFolio.addMouseListener(this);
		this.vistaConsultas.txtPlaca.addMouseListener(this);
		this.vistaConsultas.dcFin.addMouseListener(this);
		this.vistaConsultas.dcInicio.addMouseListener(this);
		this.vistaConsultas.cbRegistros.addMouseListener(this);

		this.vistaConsultas.btnConsultar.addMouseListener(this);
		this.vistaConsultas.btnAnular.addMouseListener(this);
		this.vistaConsultas.btnEliminar.addMouseListener(this);
		this.vistaConsultas.btnBuscar.addMouseListener(this);

		this.vistaConsultas.txtFolio.addKeyListener(this);
		this.vistaConsultas.txtPlaca.addKeyListener(this);
	}
	public void iniciar() {
		VistaPrincipal.dpEscritorio.add(vistaConsultas);
		vistaConsultas.show();
		archivo=MVC.INFRACCIONES;
		modeloConsultas.Importar(archivo, vistaConsultas.tabla);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//contAccion++;
		archivo= MVC.INFRACCIONES;		
		if(e.getSource() == vistaConsultas.btnBuscar | e.getSource() == vistaConsultas.cbRegistros){
			try {
				if (vistaConsultas.dcInicio.getDate()!= null && vistaConsultas.dcFin.getDate()!= null) {
					modeloConsultas.setCal1(vistaConsultas.dcInicio.getDate());
					modeloConsultas.setCal2(vistaConsultas.dcFin.getDate());
				}else {
					modeloConsultas.setCal1(null);
					modeloConsultas.setCal2(null);
				}
				modeloConsultas.ImportarEspesifico(
						archivo, 
						vistaConsultas.tabla, 
						vistaConsultas.txtFolio.getText().trim(), 
						vistaConsultas.txtPlaca.getText().trim(),
						(String)vistaConsultas.cbRegistros.getSelectedItem());
			} catch (ParseException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			}
		}if (e.getSource()==vistaConsultas.btnConsultar) {
			try {
				if (vistaConsultas.dcInicio.getDate()!= null && vistaConsultas.dcFin.getDate()!= null) {
					modeloConsultas.setCal1(vistaConsultas.dcInicio.getDate());
					modeloConsultas.setCal2(vistaConsultas.dcFin.getDate());
				}else {
					modeloConsultas.setCal1(null);
					modeloConsultas.setCal2(null);
				}
				if (vistaConsultas.tabla.getSelectedRow()!=-1) {
					VI.dispose();
					ControladorInfraccion CI= new ControladorInfraccion(VI, MI);
					CI.iniciar();
					Object[] datos= modeloConsultas.ImportarParaModificar(
							archivo, 
							vistaConsultas.tabla, 
							String.valueOf(vistaConsultas.tabla.getValueAt(vistaConsultas.tabla.getSelectedRow(),0)));
					VI.btnInsertar.setText("Actualizar");
					VI.txtNboleta.setEditable(false);
					establecerdatos(datos);
				}else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			} catch (ParseException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			}
		}if (e.getSource()==vistaConsultas.btnAnular) {
			
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Apéndice de método generado automáticamente

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == vistaConsultas.txtFolio){
			vistaConsultas.txtFolio.setBackground(new Color(234, 253, 255));			
		}else if (e.getSource() == vistaConsultas.txtPlaca) {
			vistaConsultas.txtPlaca.setBackground(new Color(234, 253, 255));
		}else if(e.getSource() == vistaConsultas.btnEliminar){
			vistaConsultas.btnEliminar.setBorder(new LineBorder(new Color(65,122,127), 3, true));
		}else if(e.getSource() == vistaConsultas.btnAnular){
			vistaConsultas.btnAnular.setBorder(new LineBorder(new Color(65,122,127), 3, true));
		}else if(e.getSource() == vistaConsultas.btnConsultar){
			vistaConsultas.btnConsultar.setBorder(new LineBorder(new Color(65,122,127), 3, true));
		}else if(e.getSource() == vistaConsultas.btnBuscar){
			vistaConsultas.btnBuscar.setBorder(new LineBorder(new Color(65,122,127), 3, true));
		}else if (e.getSource() == vistaConsultas.cbRegistros) {
			vistaConsultas.cbRegistros.setBackground(new Color(234, 253, 255));
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if((e.getSource() == vistaConsultas.txtFolio)){
			vistaConsultas.txtFolio.setBackground(new Color(187,202,204));
		}else if (e.getSource() == vistaConsultas.txtPlaca) {
			vistaConsultas.txtPlaca.setBackground(new Color(187,202,204));
		}else if (e.getSource() == vistaConsultas.dcFin) {
			vistaConsultas.dcFin.setBackground(new Color(187,202,204));
			vistaConsultas.dcFin.setBorder(null);
		}else if (e.getSource() == vistaConsultas.dcInicio) {
			vistaConsultas.dcInicio.setBackground(new Color(187,202,204));
			vistaConsultas.dcInicio.setBorder(null);
		}else if (e.getSource() == vistaConsultas.btnAnular) {
			vistaConsultas.btnAnular.setBorder(null);
		}else if (e.getSource() == vistaConsultas.btnBuscar) {
			vistaConsultas.btnBuscar.setBorder(null);
		}else if (e.getSource() == vistaConsultas.btnConsultar) {
			vistaConsultas.btnConsultar.setBorder(null);
		}else if (e.getSource() == vistaConsultas.btnEliminar) {
			vistaConsultas.btnEliminar.setBorder(null);
		}else if (e.getSource() == vistaConsultas.cbRegistros) {
			vistaConsultas.cbRegistros.setBackground(new Color(187,202,204));
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Apéndice de método generado automáticamente

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Apéndice de método generado automáticamente

	}
	@Override
	public void keyPressed(KeyEvent e) {
		archivo=new File(MVC.getConfig().getProperty("ruta_infracciones")+MVC.getConfig().getProperty("infracciones"));
		try {
			modeloConsultas.setCal1(vistaConsultas.dcInicio.getDate());
			modeloConsultas.setCal2(vistaConsultas.dcFin.getDate());
			modeloConsultas.ImportarEspesifico(archivo, vistaConsultas.tabla, 
					vistaConsultas.txtFolio.getText().trim(), 
					vistaConsultas.txtPlaca.getText().trim(),
					(String)vistaConsultas.cbRegistros.getSelectedItem());
		} catch (ParseException e1) {
			// TODO Bloque catch generado automáticamente
			e1.printStackTrace();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Apéndice de método generado automáticamente

	}
	public void establecerdatos(Object[] datos) {
		Date fec = new Date((Integer)datos[1]/(Integer)datos[2]/(Integer)datos[3]);
		VI.txtNboleta.setText(datos[0].toString());
		VI.dcFecha.setDate(fec);
		VI.timeChooser.setText((String)datos[4]);
		VI.cbMunicipio.setSelectedItem(datos[5].toString().split(", ")[0]);
		VI.txtEstado.setText(datos[5].toString().split(", ")[1]);
		VI.txtReferencias.setText((String)datos[6]);
		VI.cbInfraccion.setSelectedItem(datos[7]);
		VI.txtNplacas.setText((String)datos[8]);
		VI.txtPlacasEstado.setText((String)datos[9]);
		VI.txtMarcaModelo.setText((String)datos[10]);
		VI.txtModelo.setText((String)datos[11]);
		VI.txtNserie.setText((String)datos[12]);
		VI.txtNeconomico.setText((String)datos[13]);
		VI.txtRutaSitio.setText((String)datos[14]);
		VI.txtColor.setText((String)datos[15]);
		VI.txtNombreConductor.setText((String)datos[16]);
		VI.txtNombrePropietario.setText((String)datos[17]);
		VI.txtDomicilioConductor.setText((String)datos[18]);
		VI.txtDomicilioPropietario.setText((String)datos[19]);
		VI.txtNlicenciaConductor.setText((String)datos[20]);
		VI.txtArticulosViolados.setText((String)datos[21]);
		VI.txtRetencion.setText((String)datos[22]);
		VI.txtMarcaModelo.setText((String)datos[23]);
		VI.txtMotivo.setText((String)datos[24]);
		VI.txtNpolicia.setText((String)datos[25]);
	}
}
