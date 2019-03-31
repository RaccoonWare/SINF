package pruevas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import controlador.ControladorInfraccion;
import exepciones.ErroresCaptura;
import modelo.ModeloInfraccion;
import vista.VistaInfraccion;

public class Test {
	ModeloInfraccion modeloInfraccion = new ModeloInfraccion();
	VistaInfraccion vistaInfraccion =  new VistaInfraccion();
	ControladorInfraccion controladorInfraccion;
	@org.junit.Test
	public void testModeloInfracciones() {
		try {
			modeloInfraccion.setEstado("Zac");
			modeloInfraccion.setNplacas("ZAC-03-02");
			modeloInfraccion.setNserie("A34S58Q8A9");
			modeloInfraccion.setModelo("se desconoce");
			modeloInfraccion.setReferencias("");
			modeloInfraccion.setNboleta("154");
			modeloInfraccion.setColor("rojo");
			modeloInfraccion.setArticulosViolados("IV");
			
			//assertNotNull(modeloInfraccion.getColor());
		} catch (ErroresCaptura e) {
			// TODO Bloque catch generado automáticamente
			fail("Codigo de la exepxion = "+e.getCodigo());
		}
	}
//	@org.junit.Test
//	public void testControladorInfracciones(){
//		 
//	}
}

