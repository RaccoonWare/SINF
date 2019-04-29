/**
 * patalla login
 * @author David
 */
package vista;
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

/**
 * Clase pricipal
 * @author David 
 * @see ModeloLogin
 * @see ContoladorLogin
 */
public class VistaLogin extends JDialog {
	//constantes de clase
	private final JPanel contentPane;//contenedor general
	//varialbes de Instancia
	private JLabel lblUsuario;//etiqueta "usuario"
	private JLabel lblPass;//Etiqueta "Contraseña"
	public JTextField txtUsuario;//campo texto usuario
	public JPasswordField txtPass;//campo texto contraseña
	public JButton btnEnviar;//boton enviar

	/////////////Constructores e inicializadores
	/**
	 * clase pricipal
	 * solo para pruebas
	 * @param args
	 */
	public static void main(String[] args) {
		//inicializa una instancia de la clase
		try {		
			VistaLogin dialog = new VistaLogin();
			//dialog.setDefaultCloseOperation(JDialog.);
			dialog.setVisible(true);
		//maneho de errores
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Constructor por defecto.
	 */
	public VistaLogin() {
		//configuración de ventana		
		setTitle("Iniciar Sesi\u00F3n");//titulo de la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//acción por defecto del boton de cierre
		setBounds(100, 100, 435, 281);//tamaño por defecto
		
		//inicializa panel principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(MVC.COLOR_BG);
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][186.00][grow]", "[grow][][][][][][][][][grow]"));
		
		//inicializa etiquetas
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(MVC.FUENTE);
		lblUsuario.setForeground(MVC.COLOR_HIGHLIGHT);
		contentPane.add(lblUsuario, "cell 1 4,alignx trailing");
		
		lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setFont(MVC.FUENTE);
		lblPass.setForeground(MVC.COLOR_HIGHLIGHT);
		contentPane.add(lblPass, "cell 1 5");
		
		//inicializa campos de texto
		txtUsuario = new JFormattedTextField();
		//txtUsuario.focus
		txtUsuario.setFont(MVC.FUENTE);
		txtUsuario.setBackground(MVC.COLOR_VALID);
		contentPane.add(txtUsuario, "cell 2 4,growx");
		txtUsuario.setColumns(10);
		////campo de contraseña
		txtPass = new JPasswordField();
		txtPass.setFont(MVC.FUENTE);
		txtPass.setBackground(MVC.COLOR_VALID);
		contentPane.add(txtPass, "cell 2 5,growx");
		txtPass.setColumns(10);
		
		//inicializa boton
		btnEnviar = new JButton("Iniciar Sesi\u00F3n");
		btnEnviar.setFont(MVC.FUENTE);
		btnEnviar.setBackground(Color.WHITE);
		contentPane.add(btnEnviar, "cell 1 6 2 1,growx");
	}//fin constructor VistaLogin


}//Fin clase VistaLogin
