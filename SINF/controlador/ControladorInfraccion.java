package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import controlador.ControladorInfraccion;
import exepciones.ErroresCaptura;
import modelo.ModeloInfraccion;
import vista.VistaInfraccion;
import vista.VistaPrincipal;

public class ControladorInfraccion implements ActionListener, MouseListener{
	private VistaInfraccion VistaInfraccion;
	private ModeloInfraccion ModeloBoleta;
	File archivo;

	public ControladorInfraccion(VistaInfraccion VistaBoleta, ModeloInfraccion ModeloBoleta) {
		this.VistaInfraccion=VistaBoleta;
		this.ModeloBoleta=ModeloBoleta;
		VistaInfraccion.btnInsertar.addActionListener(this);
		VistaInfraccion.btnImprimir.addActionListener(this);
		VistaInfraccion.btnLimpiar.addActionListener(this);
		VistaInfraccion.btnAnular.addActionListener(this);

		VistaInfraccion.btnInsertar.addMouseListener(this);
		VistaInfraccion.btnImprimir.addMouseListener(this);
		VistaInfraccion.btnLimpiar.addMouseListener(this);
		VistaInfraccion.btnAnular.addMouseListener(this);

		VistaInfraccion.cbInfraccion.addMouseListener(this);
		VistaInfraccion.cbMunicipio.addMouseListener(this);
		VistaInfraccion.txtMarca.addMouseListener(this);
		VistaInfraccion.txtEstado.addMouseListener(this);
		VistaInfraccion.txtNplacas.addMouseListener(this);
		VistaInfraccion.txtNserie.addMouseListener(this);
		VistaInfraccion.txtModelo.addMouseListener(this);
		VistaInfraccion.txtReferencias.addMouseListener(this);
		VistaInfraccion.txtPlacasEstado.addMouseListener(this);
		VistaInfraccion.txtArticulosViolados.addMouseListener(this);
		VistaInfraccion.txtMotivo.addMouseListener(this);
		VistaInfraccion.txtNpolicia.addMouseListener(this);
		VistaInfraccion.txtNeconomico.addMouseListener(this);
		VistaInfraccion.txtRutaSitio.addMouseListener(this);
		VistaInfraccion.txtColor.addMouseListener(this);
		VistaInfraccion.txtNombreConductor.addMouseListener(this);
		VistaInfraccion.txtDomicilioConductor.addMouseListener(this);
		VistaInfraccion.txtNlicenciaConductor.addMouseListener(this);
		VistaInfraccion.txtNombrePropietario.addMouseListener(this);
		VistaInfraccion.txtDomicilioPropietario.addMouseListener(this);
		VistaInfraccion.txtMarcaModelo.addMouseListener(this);
		VistaInfraccion.txtRetencion.addMouseListener(this);
		VistaInfraccion.txtNboleta.addMouseListener(this);
		VistaInfraccion.timeChooser.addMouseListener(this);
	}
	public void iniciar() {
		VistaPrincipal.dpEscritorio.add(VistaInfraccion);
		VistaInfraccion.show();
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == VistaInfraccion.btnLimpiar){

		}else if (e.getSource() == VistaInfraccion.btnInsertar) {

			try {
				obtenerdatos();
			} catch (ErroresCaptura cod) {
				// CONTROL DE EXCEPCIONES
				switch (cod.getCodigo()) {
				case "nBoletaa":
					VistaInfraccion.txtNboleta.setBackground(Color.red);
					break;
				case "nPolocia":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "hora":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "estado":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "placas":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "nPolicia":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "color":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "fecha":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "serie":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "referencias":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "placasEstado":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "articulos":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "marcaModelo":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				case "retencion":
					VistaInfraccion.txtNpolicia.setBackground(Color.red);
					break;
				}
			}
			archivo=new File(MVC.getConfig().getProperty("infracciones"));
			JOptionPane.showMessageDialog(null, ModeloBoleta.Exportar(archivo));
		}else if (e.getSource() == VistaInfraccion.btnImprimir) {

		}else if (e.getSource() == VistaInfraccion.btnAnular) {

		}

	}
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource() == VistaInfraccion.txtEstado){
			VistaInfraccion.txtEstado.setBackground(new Color(234, 253, 255));			
		}else if (arg0.getSource() == VistaInfraccion.txtNplacas) {
			VistaInfraccion.txtNplacas.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtNserie) {
			VistaInfraccion.txtNserie.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtModelo) {
			VistaInfraccion.txtModelo.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtReferencias) {
			VistaInfraccion.txtReferencias.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtPlacasEstado) {
			VistaInfraccion.txtPlacasEstado.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtArticulosViolados) {
			VistaInfraccion.txtArticulosViolados.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtNpolicia) {
			VistaInfraccion.txtNpolicia.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtMarca) {
			VistaInfraccion.txtMarca.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtNeconomico) {
			VistaInfraccion.txtNeconomico.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtRutaSitio) {
			VistaInfraccion.txtRutaSitio.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtColor) {
			VistaInfraccion.txtColor.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtNombreConductor) {
			VistaInfraccion.txtNombreConductor.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtDomicilioConductor) {
			VistaInfraccion.txtDomicilioConductor.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtNlicenciaConductor) {
			VistaInfraccion.txtNlicenciaConductor.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtNombrePropietario) {
			VistaInfraccion.txtNombrePropietario.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtDomicilioPropietario) {
			VistaInfraccion.txtDomicilioPropietario.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtMarcaModelo) {
			VistaInfraccion.txtMarcaModelo.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtRetencion) {
			VistaInfraccion.txtRetencion.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtMotivo) {
			VistaInfraccion.txtMotivo.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.txtNboleta) {
			VistaInfraccion.txtNboleta.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.cbInfraccion) {
			VistaInfraccion.cbInfraccion.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.cbMunicipio) {
			VistaInfraccion.cbMunicipio.setBackground(new Color(234, 253, 255));
		}else if (arg0.getSource() == VistaInfraccion.dcFecha) {
			VistaInfraccion.dcFecha.setBackground(new Color(234, 253, 255));
		}else if(arg0.getSource() == VistaInfraccion.btnLimpiar){
			VistaInfraccion.btnLimpiar.setBorder(new LineBorder(new Color(234, 253, 255), 2, true));
		}else if (arg0.getSource() == VistaInfraccion.btnInsertar) {
			VistaInfraccion.btnInsertar.setBorder(new LineBorder(new Color(234, 253, 255), 2, true));
		}else if (arg0.getSource() == VistaInfraccion.btnAnular) {
			VistaInfraccion.btnAnular.setBorder(new LineBorder(new Color(234, 253, 255), 2, true));
		}else if (arg0.getSource() == VistaInfraccion.btnImprimir) {
			VistaInfraccion.btnImprimir.setBorder(new LineBorder(new Color(234, 253, 255), 2, true));
		}else if (arg0.getSource() == VistaInfraccion.cbInfraccion) {
			VistaInfraccion.cbInfraccion.setBorder(new LineBorder(new Color(234, 253, 255), 2, true));
		}else if (arg0.getSource() == VistaInfraccion.timeChooser) {
			VistaInfraccion.timeChooser.setBackground(new Color(234, 253, 255));
		}
	}
	public void mouseExited(MouseEvent e) {
		if((e.getSource() == VistaInfraccion.txtEstado)){
		VistaInfraccion.txtEstado.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtNplacas) {
		VistaInfraccion.txtNplacas.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtNserie) {
		VistaInfraccion.txtNserie.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtModelo) {
		VistaInfraccion.txtModelo.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtReferencias) {
		VistaInfraccion.txtReferencias.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtPlacasEstado) {
		VistaInfraccion.txtPlacasEstado.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtArticulosViolados) {
		VistaInfraccion.txtArticulosViolados.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtNpolicia) {
		VistaInfraccion.txtNpolicia.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtMarca) {
		VistaInfraccion.txtMarca.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtNeconomico) {
		VistaInfraccion.txtNeconomico.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtRutaSitio) {
		VistaInfraccion.txtRutaSitio.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtColor) {
		VistaInfraccion.txtColor.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtNombreConductor) {
		VistaInfraccion.txtNombreConductor.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtDomicilioConductor) {
		VistaInfraccion.txtDomicilioConductor.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtNlicenciaConductor) {
		VistaInfraccion.txtNlicenciaConductor.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtNombrePropietario) {
		VistaInfraccion.txtNombrePropietario.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtDomicilioPropietario) {
		VistaInfraccion.txtDomicilioPropietario.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtMarcaModelo) {
		VistaInfraccion.txtMarcaModelo.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtRetencion) {
		VistaInfraccion.txtRetencion.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtMotivo) {
		VistaInfraccion.txtMotivo.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.txtNboleta) {
		VistaInfraccion.txtNboleta.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.cbInfraccion) {
		VistaInfraccion.cbInfraccion.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.cbMunicipio) {
		VistaInfraccion.cbMunicipio.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.dcFecha) {
		VistaInfraccion.dcFecha.setBackground(new Color(187,202,204));
		}else if (e.getSource() == VistaInfraccion.btnInsertar) {
		VistaInfraccion.btnInsertar.setBorder(new LineBorder(new Color(187,202,204), 2, true));
		}else if (e.getSource() == VistaInfraccion.btnAnular) {
		VistaInfraccion.btnAnular.setBorder(new LineBorder(new Color(187,202,204), 2, true));
		}else if (e.getSource() == VistaInfraccion.btnImprimir) {
		VistaInfraccion.btnImprimir.setBorder(new LineBorder(new Color(187,202,204), 2, true));
		}else if (e.getSource() == VistaInfraccion.btnLimpiar) {
		VistaInfraccion.btnLimpiar.setBorder(new LineBorder(new Color(187,202,204), 2, true));
		}else if (e.getSource() == VistaInfraccion.timeChooser) {
		VistaInfraccion.timeChooser.setBackground(new Color(187,202,204));
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Apéndice de método generado automáticamente

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Apéndice de método generado automáticamente

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Apéndice de método generado automáticamente

	}
	public void obtenerdatos() throws ErroresCaptura {
		ModeloBoleta.setEstado(VistaInfraccion.txtEstado.getText().trim());
		ModeloBoleta.setNplacas(VistaInfraccion.txtNplacas.getText().trim());
		ModeloBoleta.setNserie(VistaInfraccion.txtNserie.getText().trim());
		ModeloBoleta.setModelo(VistaInfraccion.txtModelo.getText().trim());
		ModeloBoleta.setReferencias(VistaInfraccion.txtReferencias.getText().trim());
		ModeloBoleta.setPlacasEstado(VistaInfraccion.txtPlacasEstado.getText().trim());
		ModeloBoleta.setArticulosViolados(VistaInfraccion.txtArticulosViolados.getText().trim());
		ModeloBoleta.setNpolicia(VistaInfraccion.txtNpolicia.getText().trim());
		ModeloBoleta.setMarca(VistaInfraccion.txtMarca.getText().trim());
		ModeloBoleta.setNeconomico(VistaInfraccion.txtNeconomico.getText().trim());
		ModeloBoleta.setRutaSitio(VistaInfraccion.txtRutaSitio.getText().trim());
		ModeloBoleta.setColor(VistaInfraccion.txtColor.getText().trim());
		ModeloBoleta.setNombreConductor(VistaInfraccion.txtNombreConductor.getText().trim());
		ModeloBoleta.setDomicilioConductor(VistaInfraccion.txtDomicilioConductor.getText().trim());
		ModeloBoleta.setNlicenciaConductor(VistaInfraccion.txtNlicenciaConductor.getText().trim());
		ModeloBoleta.setNombrePropietario(VistaInfraccion.txtNombrePropietario.getText().trim());
		ModeloBoleta.setDomicilioPropietario(VistaInfraccion.txtDomicilioPropietario.getText().trim());
		ModeloBoleta.setMarcaModelo(VistaInfraccion.txtMarcaModelo.getText().trim());
		ModeloBoleta.setRetencion(VistaInfraccion.txtRetencion.getText().trim());
		ModeloBoleta.setMotivo(VistaInfraccion.txtMotivo.getText().trim());
		ModeloBoleta.setInfraccion(String.valueOf(VistaInfraccion.cbInfraccion.getSelectedItem()));
		ModeloBoleta.setMunicipio(String.valueOf(VistaInfraccion.cbMunicipio.getSelectedItem()));
		ModeloBoleta.setFecha(VistaInfraccion.dcFecha.getDate());
		ModeloBoleta.setHora(VistaInfraccion.timeChooser.getText().trim());
		ModeloBoleta.setNboleta(VistaInfraccion.txtNboleta.getText().trim());

	}
}
