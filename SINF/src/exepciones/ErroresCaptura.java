package exepciones;

public class ErroresCaptura extends Exception{
	public int codigo=0;
	public ErroresCaptura(int i) {
		/**
		 * 0,	fallo de captura, error generico
		 * 1,	exepxion para letras 
		 * 2,	Campos vacios 
		 */
		codigo=i;
	}
}
