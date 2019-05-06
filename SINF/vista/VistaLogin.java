package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.MVC;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaLogin extends JDialog {
	
	private final JPanel contentPane;	
	private JLabel lblUsuario;
	private JLabel lblPass;
	public JTextField txtUsuario;
	public JPasswordField txtPass;
	public JButton btnEnviar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VistaLogin dialog = new VistaLogin();
			//dialog.setDefaultCloseOperation(JDialog.);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the frame.
	 */
	public VistaLogin() {
		setTitle("Iniciar Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 435, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(MVC.COLOR_BG);
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][186.00][grow]", "[grow][][][][][][][][][grow]"));
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(MVC.FUENTE);
		lblUsuario.setForeground(MVC.COLOR_HIGHLIGHT);
		contentPane.add(lblUsuario, "cell 1 4,alignx trailing");
		
		txtUsuario = new JFormattedTextField();
		//txtUsuario.focus
		txtUsuario.setFont(MVC.FUENTE);
		txtUsuario.setBackground(MVC.COLOR_VALID);
		contentPane.add(txtUsuario, "cell 2 4,growx");
		txtUsuario.setColumns(10);
		
		lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setFont(MVC.FUENTE);
		lblPass.setForeground(MVC.COLOR_HIGHLIGHT);
		contentPane.add(lblPass, "cell 1 5");
		
		txtPass = new JPasswordField();
		txtPass.setFont(MVC.FUENTE);
		txtPass.setBackground(MVC.COLOR_VALID);
		contentPane.add(txtPass, "cell 2 5,growx");
		txtPass.setColumns(10);
		
		btnEnviar = new JButton("Iniciar Sesi\u00F3n");
		btnEnviar.setFont(MVC.FUENTE);
		btnEnviar.setBackground(Color.WHITE);
		contentPane.add(btnEnviar, "cell 1 6 2 1,growx");
	}


}
