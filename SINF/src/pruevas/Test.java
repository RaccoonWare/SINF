package pruevas;

//import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.io.File;
import java.sql.Date;
import javax.swing.JOptionPane;
import controlador.ControladorConsultaInfracciones;
import controlador.ControladorInfraccion;
import controlador.MVC;
import exepciones.ErroresCaptura;
import modelo.ModeloConsultaInfracciones;
import modelo.ModeloInfraccion;
import vista.VistaConsultaInfraccion;
import vista.VistaInfraccion;

public class Test {
	ModeloInfraccion modeloInfraccion = new ModeloInfraccion();
	VistaInfraccion vistaInfraccion =  new VistaInfraccion();
	ControladorInfraccion controladorInfraccion;
	ModeloConsultaInfracciones modeloConsultaInfracciones=new ModeloConsultaInfracciones();
	VistaConsultaInfraccion vistaConsultaInfraccion=new VistaConsultaInfraccion();
	ControladorConsultaInfracciones controladorConsultaInfracciones = new ControladorConsultaInfracciones(vistaConsultaInfraccion, modeloConsultaInfracciones);
	//@org.junit.Test
	public void testModeloInfracciones() {
		try {
			modeloInfraccion.setEstado("Zacatecas");
			modeloInfraccion.setMunicipio("Calera");
			modeloInfraccion.setNplacas("09-02-02");
			modeloInfraccion.setNserie("A34S58Q8A9");
			modeloInfraccion.setModelo("se desconoce");
			modeloInfraccion.setReferencias("Calle libertad 509");
			modeloInfraccion.setPlacasEstado("zacatecas");
			modeloInfraccion.setArticulosViolados("IV 16 17");
			modeloInfraccion.setNpolicia("111");
			modeloInfraccion.setMarcaSubmarca("Se desconoce");
			modeloInfraccion.setNeconomico("Se desconoce");
			modeloInfraccion.setRutaSitio("Se desconoce");
			modeloInfraccion.setColor("rojo");
			modeloInfraccion.setNombreConductor("Se desconoce");
			modeloInfraccion.setDomicilioConductor("Se desconoce");
			modeloInfraccion.setNlicenciaConductor("Se desconoce");
			modeloInfraccion.setNombrePropietario("Se desconoce");
			modeloInfraccion.setDomicilioPropietario("Se desconoce");
			modeloInfraccion.setMarcaModelo("ford");
			modeloInfraccion.setRetencion("Vehiculo");
			modeloInfraccion.setMotivo("Choque");
			modeloInfraccion.setNboleta("111");
			modeloInfraccion.setInfraccion("Se desconoce");
			Date fec = new Date(02/02/1973);
			modeloInfraccion.setFecha(fec);
			//MVC.loadConfig();
			File archivo=new File(MVC.getConfig().getProperty("infracciones"));
			JOptionPane.showMessageDialog(null, modeloInfraccion.Exportar(archivo));
		} catch (ErroresCaptura e) {
			// TODO Bloque catch generado autom�ticamente
			fail("Codigo de la exepxion = "+e.getCodigo());
		}
	}
	//@org.junit.Test
	public void testModeloConsultaInfracciones(){
		//MVC.loadConfig();
		Date fecIni = new Date(02/02/1969);
		Date fecFin = new Date(02/02/1970);
		modeloConsultaInfracciones.setCal1(fecIni);
		modeloConsultaInfracciones.setCal2(fecFin);
		/*try {
			modeloConsultaInfracciones.ImportarEspesifico(archivo, tabla, "555", "");
		} catch (ParseException e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
		}*/
	}
}

