package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class BoletaInfracciones extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
	private JMenuBar barra;
	private JMenu menu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoletaInfracciones frame = new BoletaInfracciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BoletaInfracciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		barra = new JMenuBar();
		menu= new JMenu("Menu");
		menu.add(new JMenuItem("Derp"));
		barra.add(menu);
		JButton btnNewButton = new JButton("New button");
		contentPane.add(barra,BorderLayout.NORTH);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				
				//barra.setSize(101, 21);
				//menu.setSize(100, 20);
				//barra.setVisible(true);
				//menu.setVisible(true);
				//menu.show(true);
				//contentPane.add(menu,BorderLayout.NORTH);
			}
		});
		btnNewButton.setAction(action);
		contentPane.add(btnNewButton, BorderLayout.WEST);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
