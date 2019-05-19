package vista;

import javax.swing.ImageIcon;
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
import java.awt.Toolkit;

public class VistaLogin extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2250508784064964322L;
	private final JPanel contentPane;	
	private JLabel lblUsuario;
	private JLabel lblPass;
	public JTextField txtUsuario;
	public JPasswordField txtPass;
	public JButton btnEnviar;
	public VistaLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaLogin.class.getResource("/iconos/LogoSINF.png")));
		setTitle("Iniciar Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 415, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(MVC.COLOR_BG);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(182, 36, 54, 17);
		lblUsuario.setFont(MVC.FUENTE);
		lblUsuario.setForeground(MVC.COLOR_HIGHLIGHT);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JFormattedTextField();
		txtUsuario.setBounds(182, 64, 186, 23);
		txtUsuario.setFont(MVC.FUENTE);
		txtUsuario.setBackground(MVC.COLOR_VALID);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setBounds(182, 99, 81, 17);
		lblPass.setFont(MVC.FUENTE);
		lblPass.setForeground(MVC.COLOR_HIGHLIGHT);
		contentPane.add(lblPass);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(182, 127, 186, 23);
		txtPass.setFont(MVC.FUENTE);
		txtPass.setBackground(MVC.COLOR_VALID);
		contentPane.add(txtPass);
		txtPass.setColumns(10);
		
		btnEnviar = new JButton("Iniciar Sesi\u00F3n");
		btnEnviar.setBounds(26, 174, 344, 25);
		btnEnviar.setFont(MVC.FUENTE);
		btnEnviar.setBackground(MVC.COLOR_HIGHLIGHT);
		contentPane.add(btnEnviar);
		
		JLabel lbLogo = new JLabel("New label");
		lbLogo.setBounds(26, 25, 166, 141);
		ImageIcon img = new ImageIcon(getClass().getResource("/iconos/LogoSINF.png"));
		ImageIcon icono = new ImageIcon(img.getImage().getScaledInstance(lbLogo.getWidth(),lbLogo.getHeight(),100));
		lbLogo.setIcon(icono);
		contentPane.add(lbLogo);
	}
}
