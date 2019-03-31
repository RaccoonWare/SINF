package exepciones;

public class ErroresCaptura extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String codigo="";
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String donde) {
		this.codigo = donde;
	}
	public ErroresCaptura(String donde) {
		codigo=donde;

	}
	public ErroresCaptura() {
		
	}
}
