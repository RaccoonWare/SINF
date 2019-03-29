package exepciones;

public class ErroresCaptura extends Exception{
	public int codigo=0;
	public ErroresCaptura(int i) {
		//exepxion para letras 1
		codigo=i;
	}
}
