package exepciones;

public class ErroresCaptura extends Exception{
	//public int codigo=0
	public String codigo="";
	public ErroresCaptura(String i) {
		/**
		 * 0,	fallo de captura, error generico
		 * 1,	exepxion para letras 
		 * 2,	Campos vacios 
		 */
		codigo=i;
	}
	public String getCodigo() {
		// TODO Auto-generated method stub
		return codigo;
	}
	/*public int getCodigo() {
		// TODO Auto-generated method stub
		return codigo;
	}*/
}
