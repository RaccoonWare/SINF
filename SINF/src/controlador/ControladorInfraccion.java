package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractButton;
//import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import controlador.ControladorInfraccion;
import modelo.ModeloInfraccion;
import vista.VistaInfraccion;
import vista.VistaPrincipal;

public class ControladorInfraccion implements ActionListener, MouseListener, KeyListener, FocusListener{
	private VistaInfraccion vistaInfraccion;
	private ModeloInfraccion modeloBoleta;
	File archivo;

	public ControladorInfraccion(VistaInfraccion VistaBoleta, ModeloInfraccion ModeloBoleta) {
		this.vistaInfraccion=VistaBoleta;
		this.modeloBoleta=ModeloBoleta;
		addListeners();
	}
	
	public void iniciar() {
		VistaPrincipal.dpEscritorio.add(vistaInfraccion);
		vistaInfraccion.show();
	}
	///////////Configuraciones
	private void addListeners() {
		vistaInfraccion.btnInsertar.addActionListener(this);
		vistaInfraccion.btnImprimir.addActionListener(this);
		vistaInfraccion.btnLimpiar.addActionListener(this);
		vistaInfraccion.btnAnular.addActionListener(this);
		
		vistaInfraccion.btnInsertar.addMouseListener(this);
		vistaInfraccion.btnImprimir.addMouseListener(this);
		vistaInfraccion.btnLimpiar.addMouseListener(this);
		vistaInfraccion.btnAnular.addMouseListener(this);

		vistaInfraccion.cbInfraccion.addFocusListener(this);
		vistaInfraccion.cbMunicipio.addFocusListener(this);
		vistaInfraccion.txtMarca.addFocusListener(this);
		vistaInfraccion.txtEstado.addFocusListener(this);
		vistaInfraccion.txtNplacas.addFocusListener(this);
		vistaInfraccion.txtNserie.addFocusListener(this);
		vistaInfraccion.txtModelo.addFocusListener(this);
		vistaInfraccion.txtReferencias.addFocusListener(this);
		vistaInfraccion.txtPlacasEstado.addFocusListener(this);
		vistaInfraccion.txtArticulosViolados.addFocusListener(this);
		vistaInfraccion.txtMotivo.addFocusListener(this);
		vistaInfraccion.txtNpolicia.addFocusListener(this);
		vistaInfraccion.txtNeconomico.addFocusListener(this);
		vistaInfraccion.txtRutaSitio.addFocusListener(this);
		vistaInfraccion.txtColor.addFocusListener(this);
		vistaInfraccion.txtNombreConductor.addFocusListener(this);
		vistaInfraccion.txtDomicilioConductor.addFocusListener(this);
		vistaInfraccion.txtNlicenciaConductor.addFocusListener(this);
		vistaInfraccion.txtNombrePropietario.addFocusListener(this);
		vistaInfraccion.txtDomicilioPropietario.addFocusListener(this);
		vistaInfraccion.txtMarcaModelo.addFocusListener(this);
		vistaInfraccion.txtRetencion.addFocusListener(this);
		vistaInfraccion.txtNboleta.addFocusListener(this);
		vistaInfraccion.timeChooser.addFocusListener(this);
		
		vistaInfraccion.cbInfraccion.addMouseListener(this);
		vistaInfraccion.cbMunicipio.addMouseListener(this);
		vistaInfraccion.txtMarca.addMouseListener(this);
		vistaInfraccion.txtEstado.addMouseListener(this);
		vistaInfraccion.txtNplacas.addMouseListener(this);
		vistaInfraccion.txtNserie.addMouseListener(this);
		vistaInfraccion.txtModelo.addMouseListener(this);
		vistaInfraccion.txtReferencias.addMouseListener(this);
		vistaInfraccion.txtPlacasEstado.addMouseListener(this);
		vistaInfraccion.txtArticulosViolados.addMouseListener(this);
		vistaInfraccion.txtMotivo.addMouseListener(this);
		vistaInfraccion.txtNpolicia.addMouseListener(this);
		vistaInfraccion.txtNeconomico.addMouseListener(this);
		vistaInfraccion.txtRutaSitio.addMouseListener(this);
		vistaInfraccion.txtColor.addMouseListener(this);
		vistaInfraccion.txtNombreConductor.addMouseListener(this);
		vistaInfraccion.txtDomicilioConductor.addMouseListener(this);
		vistaInfraccion.txtNlicenciaConductor.addMouseListener(this);
		vistaInfraccion.txtNombrePropietario.addMouseListener(this);
		vistaInfraccion.txtDomicilioPropietario.addMouseListener(this);
		vistaInfraccion.txtMarcaModelo.addMouseListener(this);
		vistaInfraccion.txtRetencion.addMouseListener(this);
		vistaInfraccion.txtNboleta.addMouseListener(this);
		vistaInfraccion.timeChooser.addMouseListener(this);
		
		vistaInfraccion.txtArticulosViolados.addKeyListener(this);
		vistaInfraccion.txtMotivo.addKeyListener(this);
	}
	///////////Manejo de Eventos
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vistaInfraccion.btnLimpiar){
			modeloBoleta.limpiarDatos(vistaInfraccion);
		}else if (e.getSource() == vistaInfraccion.btnInsertar) {
			obtenerDatos();
			archivo=new File(MVC.getConfig().getProperty("infracciones"));
			JOptionPane.showMessageDialog(null, modeloBoleta.Exportar(archivo));
		}else if (e.getSource() == vistaInfraccion.btnImprimir) {

		}else if (e.getSource() == vistaInfraccion.btnAnular) {

		}

	}
	

	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource() instanceof JTextComponent) {
			if(!((JComponent)arg0.getSource()).hasFocus())
			((JTextComponent)arg0.getSource()).setBackground(MVC.COLOR_LETRA);
		}if(arg0.getSource() instanceof AbstractButton) {
			((AbstractButton)arg0.getSource()).setBorder(new LineBorder(Color.WHITE,2,true));
		}
		
		
		else if (arg0.getSource() == vistaInfraccion.cbInfraccion) {
			vistaInfraccion.cbInfraccion.setBackground(MVC.COLOR_LETRA);
		}else if (arg0.getSource() == vistaInfraccion.cbMunicipio) {
			vistaInfraccion.cbMunicipio.setBackground(MVC.COLOR_LETRA);
		}else if (arg0.getSource() == vistaInfraccion.dcFecha) {
			vistaInfraccion.dcFecha.setBackground(Color.BLUE);
		}else if (arg0.getSource() == vistaInfraccion.cbInfraccion) {
			vistaInfraccion.cbInfraccion.setBorder(new LineBorder(Color.WHITE, 2, true));
		}else if (arg0.getSource() == vistaInfraccion.timeChooser) {
			if(vistaInfraccion.timeChooser.hasFocus())
				vistaInfraccion.timeChooser.setBackground(MVC.COLOR_LETRA);
		}
	}
	
	public void mouseExited(MouseEvent e) {

			if(e.getSource() instanceof JTextComponent) {
				if(!((JTextComponent)e.getSource()).getBackground().equals(MVC.COLOR_INVALID))
					((JTextComponent)e.getSource()).setBackground(MVC.COLOR_VALID);
				if(((JTextComponent)e.getSource()).hasFocus())
					((JTextComponent)e.getSource()).setBackground(Color.WHITE);
			}else if(e.getSource() instanceof AbstractButton){
				((AbstractButton)e.getSource()).setBorder(new LineBorder(MVC.COLOR_VALID,2,true));
			}if(e.getSource() instanceof JComboBox) {
				((JComponent)e.getSource()).setBackground(MVC.COLOR_VALID);
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
		if(e.getSource() instanceof JComboBox) {
			((JComponent)e.getSource()).setBackground(MVC.COLOR_VALID);
		}
	}
	public void obtenerDatos() {
		modeloBoleta.setEstado(vistaInfraccion.txtEstado.getText());
		modeloBoleta.setNplacas(vistaInfraccion.txtNplacas.getText());
		modeloBoleta.setNserie(vistaInfraccion.txtNserie.getText());
		modeloBoleta.setModelo(vistaInfraccion.txtModelo.getText());
		modeloBoleta.setReferencias(vistaInfraccion.txtReferencias.getText());
		modeloBoleta.setPlacasEstado(vistaInfraccion.txtPlacasEstado.getText());
		modeloBoleta.setArticulosViolados(vistaInfraccion.txtArticulosViolados.getText());
		modeloBoleta.setNpolicia(vistaInfraccion.txtNpolicia.getText());
		modeloBoleta.setMarca(vistaInfraccion.txtMarca.getText());
		modeloBoleta.setNeconomico(vistaInfraccion.txtNeconomico.getText());
		modeloBoleta.setRutaSitio(vistaInfraccion.txtRutaSitio.getText());
		modeloBoleta.setColor(vistaInfraccion.txtColor.getText());
		modeloBoleta.setNombreConductor(vistaInfraccion.txtNombreConductor.getText());
		modeloBoleta.setDomicilioConductor(vistaInfraccion.txtDomicilioConductor.getText());
		modeloBoleta.setNlicenciaConductor(vistaInfraccion.txtNlicenciaConductor.getText());
		modeloBoleta.setNombrePropietario(vistaInfraccion.txtNombrePropietario.getText());
		modeloBoleta.setDomicilioPropietario(vistaInfraccion.txtDomicilioPropietario.getText());
		modeloBoleta.setMarcaModelo(vistaInfraccion.txtMarcaModelo.getText());
		modeloBoleta.setRetencion(vistaInfraccion.txtRetencion.getText());
		modeloBoleta.setMotivo(vistaInfraccion.txtMotivo.getText());
		modeloBoleta.setInfraccion(String.valueOf(vistaInfraccion.cbInfraccion.getSelectedItem()));
		modeloBoleta.setMunicipio(String.valueOf(vistaInfraccion.cbMunicipio.getSelectedItem()));
		modeloBoleta.setFecha(vistaInfraccion.dcFecha.getDate());
		modeloBoleta.setHora(vistaInfraccion.timeChooser.getText());
		//ModeloBoleta.setNboleta(VistaInfraccion.txtNboleta.getText());
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
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JTextComponent) {
			((JTextComponent)e.getSource()).setBackground(Color.WHITE);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JTextComponent) {
			if(!((JTextComponent)e.getSource()).getBackground().equals(MVC.COLOR_INVALID))
				((JTextComponent)e.getSource()).setBackground(MVC.COLOR_VALID);
		}
	}

}
