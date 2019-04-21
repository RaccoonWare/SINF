package modelo;

import java.awt.Frame;
import java.io.File;

import javax.swing.JFrame;

import controlador.ControladorConsultaArticulos;
import controlador.ControladorConsultaInfracciones;
import controlador.ControladorInfraccion;
import vista.VistaConsultaArticulo;
import vista.VistaConsultaInfraccion;
import vista.VistaInfraccion;
import vista.VistaPrincipal;

public class ModeloPrincipal {
	//private VistaPrincipal vistaPrincipal;
	private File archivo;
	public ModeloPrincipal(VistaPrincipal vista) {
		
	}
	
	public void iniciar(VistaPrincipal vistaPrincipal) {
		// TODO Apéndice de método generado automáticamente
		vistaPrincipal.setVisible(true);
		//vistaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		vistaPrincipal.setExtendedState(JFrame.NORMAL);
		vistaPrincipal.setState(JFrame.NORMAL);
		vistaPrincipal.setState(JFrame.MAXIMIZED_BOTH);
		vistaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		vistaPrincipal.setLocationRelativeTo(null);

	}
	
	public void iniciarInfracciones(VistaPrincipal vistaPrincipal) {
		ModeloInfraccion MI = new ModeloInfraccion();
		VistaInfraccion VI = new VistaInfraccion();
		ControladorInfraccion CI= new ControladorInfraccion(VI, MI);
		CI.iniciar();
	}
	public void iniciarConsultaInfracciones(VistaPrincipal vistaPrincipal) {
		ModeloConsultaInfracciones MCI = new ModeloConsultaInfracciones();
		VistaConsultaInfraccion VCI = new VistaConsultaInfraccion();
		ControladorConsultaInfracciones CCI= new ControladorConsultaInfracciones(VCI, MCI);
		CCI.iniciar();
	}
	
	public void vaciarEtiquetas(VistaPrincipal vistaPrincipal) {
		vistaPrincipal.btnEstadisticas.setText("Estadisticas");
		vistaPrincipal.btnConsultar.setText("Consultar");
		vistaPrincipal.btnInfracciones.setText("Infracciones");
		vistaPrincipal.btnRespaldar.setText("Respaldo");
		vistaPrincipal.btnEtiquetas.setText("");
		vistaPrincipal.btnArticulos.setText("Articulos");
		vistaPrincipal.btnRestaurar.setText("Restaurar");
		vistaPrincipal.btnConfiguracin.setText("Configuración ");
	}
	
	public void llenarEtiquetas(VistaPrincipal vistaPrincipal){
		vistaPrincipal.btnEstadisticas.setText("");
		vistaPrincipal.btnConsultar.setText("");
		vistaPrincipal.btnInfracciones.setText("");
		vistaPrincipal.btnRespaldar.setText("");
		vistaPrincipal.btnEtiquetas.setText("");
		vistaPrincipal.btnArticulos.setText("");
		vistaPrincipal.btnRestaurar.setText("");
		vistaPrincipal.btnConfiguracin.setText("");
	}
	
	public void iniciarConsultaArticulos(VistaPrincipal vistaPrincipal) {
		ModeloConsultaArticulos MCA = new ModeloConsultaArticulos();
		VistaConsultaArticulo VCA = new VistaConsultaArticulo();
		ControladorConsultaArticulos CCA= new ControladorConsultaArticulos(VCA, MCA);
		CCA.iniciar();
	}
}
