package exepciones;

public class InvalidPropertyException extends ErroresInit{
	private String[] missingAtribute;	
	/**
	 * 
	 */
	public InvalidPropertyException() {
		super(1);
	}
	
	public InvalidPropertyException(String atribute){
		super(1);
		missingAtribute = new String[]{atribute};		
	}
	
	public String getAtribute() {
		return missingAtribute[0];
	}
	public String[] getMissingAtribute() {
		return missingAtribute;
	}
	
}
