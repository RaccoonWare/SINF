package pruevas;

import javax.swing.text.JTextComponent;

public class FuncionesMisc {
	public static void printArray(Object[] campos) {
		for(Object t: campos) {
			if (t instanceof JTextComponent) {
				System.out.print("[");
				if (!((JTextComponent)t).getText().equals(null)) System.out.print(((JTextComponent)t).getText());
				else System.out.print("null");
				System.out.println("]");
			}else if(t==null) System.out.println("null");
		}
	}
}
