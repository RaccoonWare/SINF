package exepciones;

public class InvalidPropertyException extends ErroresInit{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5567373661484033821L;
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
