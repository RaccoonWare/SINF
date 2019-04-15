package pruevas;

import static org.junit.Assert.assertNotNull;

import modelo.ModeloInfraccion;

public class Test {
	@org.junit.Test
	public void testModeloInfracciones() {
		ModeloInfraccion modeloInfraccion = new ModeloInfraccion();
		modeloInfraccion.setColor("");
		assertNotNull(modeloInfraccion.getColor());
	}
	
		
}

