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
	/*@author Mario Rangel Alvarado
	 *@version 1.0
	 *@param obtener datos del formulario de boleta de infracciones
	 *@param establecer datos en la hoja de exel para la informacion de boleta de infraciones
	 *@throws ErroresCaptura
	 * */

	/*@param constructor donde se obtiene la vista y el modelo perteneciente al modulo de infracciones
	 */
	//Constructor sobrecargado
	public ControladorInfraccion(VistaInfraccion VistaBoleta, ModeloInfraccion ModeloBoleta) {
		this.VistaInfraccion=VistaBoleta;
		this.ModeloBoleta=ModeloBoleta;
		VistaInfraccion.btnInsertar.addActionListener(this);
		VistaInfraccion.btnLimpiar.addActionListener(this);
		VistaInfraccion.btnInsertar.addMouseListener(this);
		VistaInfraccion.btnLimpiar.addMouseListener(this);
		VistaInfraccion.cbInfraccion.addMouseListener(this);
		VistaInfraccion.cbMunicipio.addMouseListener(this);
		VistaInfraccion.txtMarcaModelo.addMouseListener(this);
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
		VistaInfraccion.dcFecha.addMouseListener(this);
	}
	//Metodos publicos
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == VistaInfraccion.btnLimpiar){
			VistaInfraccion.txtNboleta.setText("");
			VistaInfraccion.timeChooser.setText("");
			VistaInfraccion.txtEstado.setText("");
			VistaInfraccion.cbMunicipio.setSelectedIndex(0);
			VistaInfraccion.txtReferencias.setText("");
			VistaInfraccion.cbInfraccion.setSelectedIndex(0);
			VistaInfraccion.txtNplacas.setText("");
			VistaInfraccion.txtPlacasEstado.setText("");
			VistaInfraccion.txtMarcaModelo.setText("");
			VistaInfraccion.txtModelo.setText("");
			VistaInfraccion.txtNserie.setText("");
			VistaInfraccion.txtNeconomico.setText("");
			VistaInfraccion.txtRutaSitio.setText("");
			VistaInfraccion.txtColor.setText("");
			VistaInfraccion.txtNombreConductor.setText("");
			VistaInfraccion.txtNombrePropietario.setText("");
			VistaInfraccion.txtDomicilioConductor.setText("");
			VistaInfraccion.txtDomicilioPropietario.setText("");
			VistaInfraccion.txtNlicenciaConductor.setText("");
			VistaInfraccion.txtArticulosViolados.setText("");
			VistaInfraccion.txtRetencion.setText("");
			VistaInfraccion.txtMarcaModelo.setText("");
			VistaInfraccion.txtMotivo.setText("");
			VistaInfraccion.txtNpolicia.setText("");
		}else if (e.getSource() == VistaInfraccion.btnInsertar) {
			if (VistaInfraccion.btnInsertar.getText().equals("Insertar")) {
				/*Control de excepciones para los errores de captura*/
				try {
					obtenerdatos();
					archivo=new File(MVC.getConfig().getProperty("infracciones"));

					try {
						ModeloBoleta.Exportar(archivo);
					} catch (Exception e1) {
						// TODO Bloque catch generado automáticamente
						JOptionPane.showMessageDialog(null,"Algo salio mal al guardar los datos");
					}
				} catch (ErroresCaptura cod) {
					// CONTROL DE EXCEPCIONES
					switch (cod.getCodigo()) {
					case "nBoleta":
						VistaInfraccion.txtNboleta.setBackground(new Color(201,79,76));
						break;
					case "nPolocia":
						VistaInfraccion.txtNpolicia.setBackground(new Color(201,79,76));
						break;
					case "hora":
						VistaInfraccion.timeChooser.setBackground(new Color(201,79,76));
						break;
					case "estado":
						VistaInfraccion.txtEstado.setBackground(new Color(201,79,76));
						break;
					case "placas":
						VistaInfraccion.txtNplacas.setBackground(new Color(201,79,76));
						break;
					case "nPolicia":
						VistaInfraccion.txtNpolicia.setBackground(new Color(201,79,76));
						break;
					case "color":
						VistaInfraccion.txtColor.setBackground(new Color(201,79,76));
						break;
					case "fecha":
						VistaInfraccion.dcFecha.setBorder(new LineBorder(new Color(201,79,76), 1, true));
						break;
					case "serie":
						VistaInfraccion.txtNserie.setBackground(new Color(201,79,76));
						break;
					case "referencias":
						VistaInfraccion.txtReferencias.setBackground(new Color(201,79,76));
						break;
					case "placasEstado":
						VistaInfraccion.txtPlacasEstado.setBackground(new Color(201,79,76));
						break;
					case "articulos":
						VistaInfraccion.txtArticulosViolados.setBackground(new Color(201,79,76));
						break;
					case "marcaSubmarca":
						VistaInfraccion.txtMarcaModelo.setBackground(new Color(201,79,76));
						break;
					case "retencion":
						VistaInfraccion.txtRetencion.setBackground(new Color(201,79,76));
						break;
					case "motivo":
						VistaInfraccion.txtMotivo.setBackground(new Color(201,79,76));
						break;
					case "repetido":
						JOptionPane.showMessageDialog(null, "El numero de boleta ya existe");
						break;
					}
				}
			}else if (VistaInfraccion.btnInsertar.getText().equals("Actualizar")) {
				
			}
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
		}else if (arg0.getSource() == VistaInfraccion.txtMarcaModelo) {
			VistaInfraccion.txtMarcaModelo.setBackground(new Color(234, 253, 255));
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
			VistaInfraccion.dcFecha.setBorder(null);
		}else if(arg0.getSource() == VistaInfraccion.btnLimpiar){
			VistaInfraccion.btnLimpiar.setBorder(new LineBorder(new Color(65,122,127), 3, true));
		}else if (arg0.getSource() == VistaInfraccion.btnInsertar) {
			//VistaInfraccion.btnInsertar.setBorder(null);
			VistaInfraccion.btnInsertar.setBorder(new LineBorder(new Color(65,122,127), 3, true));
		}else if (arg0.getSource() == VistaInfraccion.cbInfraccion) {
			VistaInfraccion.cbInfraccion.setBorder(new LineBorder(new Color(234, 253, 255), 3, true));
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
		}else if (e.getSource() == VistaInfraccion.txtMarcaModelo) {
			VistaInfraccion.txtMarcaModelo.setBackground(new Color(187,202,204));
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
			VistaInfraccion.dcFecha.setBorder(null);
		}else if (e.getSource() == VistaInfraccion.btnInsertar) {
			VistaInfraccion.btnInsertar.setBorder(null);
		}else if (e.getSource() == VistaInfraccion.btnLimpiar) {
			VistaInfraccion.btnLimpiar.setBorder(null);
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
	//Metodos privados
	/*@param iniciar instancia la vista y la muestra*/
	public void iniciar() {
		try {
			VistaPrincipal.dpEscritorio.add(VistaInfraccion);
			VistaInfraccion.show();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Al parecer estas modificando dos boletas a la vez se serrará de \n" + 
					"manera automática la otra ventana y puedes intentarlo nuevamente. ",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}

	}
	/*@param Establecer los datos para la tabla de boleta de infracciones
	 * <p>Regra los datos</p>
	 * */
	public void obtenerdatos() throws ErroresCaptura {
		ModeloBoleta.setNboleta(VistaInfraccion.txtNboleta.getText().trim());
		ModeloBoleta.setFecha(VistaInfraccion.dcFecha.getDate());
		ModeloBoleta.setHora(VistaInfraccion.timeChooser.getText().trim());
		ModeloBoleta.setEstado(VistaInfraccion.txtEstado.getText().trim());
		ModeloBoleta.setMunicipio(String.valueOf(VistaInfraccion.cbMunicipio.getSelectedItem()));
		ModeloBoleta.setReferencias(VistaInfraccion.txtReferencias.getText().trim());
		ModeloBoleta.setInfraccion(String.valueOf(VistaInfraccion.cbInfraccion.getSelectedItem()));
		ModeloBoleta.setNplacas(VistaInfraccion.txtNplacas.getText().trim());
		ModeloBoleta.setPlacasEstado(VistaInfraccion.txtPlacasEstado.getText().trim());
		ModeloBoleta.setMarcaSubmarca(VistaInfraccion.txtMarcaModelo.getText().trim());
		ModeloBoleta.setModelo(VistaInfraccion.txtModelo.getText().trim());
		ModeloBoleta.setNserie(VistaInfraccion.txtNserie.getText().trim());
		ModeloBoleta.setNeconomico(VistaInfraccion.txtNeconomico.getText().trim());
		ModeloBoleta.setRutaSitio(VistaInfraccion.txtRutaSitio.getText().trim());
		ModeloBoleta.setColor(VistaInfraccion.txtColor.getText().trim());
		ModeloBoleta.setNombreConductor(VistaInfraccion.txtNombreConductor.getText().trim());
		ModeloBoleta.setNombrePropietario(VistaInfraccion.txtNombrePropietario.getText().trim());
		ModeloBoleta.setDomicilioConductor(VistaInfraccion.txtDomicilioConductor.getText().trim());
		ModeloBoleta.setDomicilioPropietario(VistaInfraccion.txtDomicilioPropietario.getText().trim());
		ModeloBoleta.setNlicenciaConductor(VistaInfraccion.txtNlicenciaConductor.getText().trim());
		ModeloBoleta.setArticulosViolados(VistaInfraccion.txtArticulosViolados.getText().trim());
		ModeloBoleta.setRetencion(VistaInfraccion.txtRetencion.getText().trim());
		ModeloBoleta.setMarcaModelo(VistaInfraccion.txtMarcaModelo.getText().trim());
		ModeloBoleta.setMotivo(VistaInfraccion.txtMotivo.getText().trim());
		ModeloBoleta.setNpolicia(VistaInfraccion.txtNpolicia.getText().trim());
	}
}
