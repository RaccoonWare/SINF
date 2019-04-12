
package exepciones;

import java.awt.Component;

public final class EmptyFieldExcepton extends ErroresCaptura{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1794013030296835280L;
	public Object[] camposVacios;
	public EmptyFieldExcepton() {
		super(2);
		// TODO Auto-generated constructor stub
	}

	public EmptyFieldExcepton(Object[] campos) {
		super(2);
		this.camposVacios=campos;
	}
}
