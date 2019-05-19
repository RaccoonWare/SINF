package exepciones;

public class ErroresCaptura extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String codigo="";
	public int codigoN=0;

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String donde) {
		this.codigo = donde;
	}
	public ErroresCaptura(String donde) {
		codigo=donde;

	}

	public ErroresCaptura(int i) {
		/**
		 * 0,	fallo de captura, error generico
		 * 1,	exepxion para letras 
		 * 2,	Campos vacios 
		 */
		codigoN=i;
	}
}
