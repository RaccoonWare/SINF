package controlador;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.ModeloConfiguracion;
import vista.VistaConfiguracion;
import vista.VistaPrincipal;

public class ControladorConfiguracion implements ActionListener, MouseListener {
	private VistaConfiguracion vistaConfiguracion;
	public ModeloConfiguracion modeloconfiguracion;
	
	public ControladorConfiguracion(VistaConfiguracion vc, ModeloConfiguracion mc) {
		this.vistaConfiguracion=vc;
		this.modeloconfiguracion=mc;
		agregarListeners();
	}
	
	private void agregarListeners() {
		//actionListenres
		this.vistaConfiguracion.btnRutaArticulos.addActionListener(this);
		this.vistaConfiguracion.btnRutaInfracciones.addActionListener(this);
		this.vistaConfiguracion.btnUusarioContrase�a.addActionListener(this);
				
	}//fin agregarListeners
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		if (arg0.getSource()==vistaConfiguracion.btnRutaArticulos) {
			modeloconfiguracion.establecerRuta((Container)vistaConfiguracion, "articulos");
		}else if (arg0.getSource()==vistaConfiguracion.btnRutaInfracciones) {
			modeloconfiguracion.establecerRuta((Container)vistaConfiguracion, "infracciones");
		}else if (arg0.getSource()==vistaConfiguracion.btnUusarioContrase�a) {
			modeloconfiguracion.usuarioContrase�a();
		}
	}

	public void iniciar() {
		VistaPrincipal.dpEscritorio.add(vistaConfiguracion);
		vistaConfiguracion.show();
	}

}
