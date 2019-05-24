/**
 * 
 */
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

/**
 * @author David
 *
 */
public class ControladorConfig implements ActionListener, MouseListener, KeyListener, FocusListener {
		
	////////////////////Constructores e iniciadores////////////////////
	//////////////////////Manejo de Eventos////////////////////
	
	////////////Acciones
	/* (non-Javadoc)
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	*/
	@Override
	public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
	}
	
	/////////Manejo de componentos 
	/**
	 * Evento cuando un componente gana el foco
	 */
	@Override
	public void focusGained(FocusEvent f) {
		// TODO Auto-generated method stub
		if((f.getSource() instanceof JTextField) && f.getComponent().getBackground().equals(MVC.COLOR_INVALID))
			f.getComponent().setBackground(Color.WHITE);
	}//Fin  FocusGained

	/** 
	 * Evento Perdida de Foco
	 */
	@Override
	public void focusLost(FocusEvent f) {
		// TODO Auto-generated method stub
		if((f.getSource() instanceof JTextField) && f.getComponent().getBackground().equals(MVC.COLOR_INVALID))
			f.getComponent().setBackground(MVC.COLOR_VALID);
	}//Fin FocusLost

	/////////////Eventos del teclado
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}//Fin KeyReleased

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/////////////////Eventos Mouse
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	

}
