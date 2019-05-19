package modelo;

public class ModeloArticulos {
    private int articulosViolados;
	private String nomArticulo;
	
	public ModeloArticulos(int articulosViolados, String nomArticulo) {
		this.articulosViolados=articulosViolados;
		this.nomArticulo = nomArticulo;
	}
	
	public String getNomArticulo() {
		return nomArticulo;
	}
	public void setNomArticulo(String nomArticulo) {
		this.nomArticulo = nomArticulo;
	}
	public int getArticulosViolados() {
		return articulosViolados;
	}
	public void setArticulosViolados(int articulosViolados) {
		this.articulosViolados = articulosViolados;
	}
}
