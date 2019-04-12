package exepciones;

public class ErroresInit extends Exception{
	public int codigo=0;
	public ErroresInit() {
		super();		
	}
	public ErroresInit(int i) {
		/**
		 * 0,	Dato en archivo configuración no encontrado, error generico7
		 * -1,	Dato vacio;
		 * 1,	ruta o nombre archivo no encontrado
		 * 2,	valor RGB invalido
		 * 3,   tipo fuente invalido
		 */
		super();
		codigo=i;
	}
}
