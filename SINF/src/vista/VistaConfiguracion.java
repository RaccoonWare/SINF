/**
 * vistaConfig ventana para guardar la configuracion;
 * @author David
 */
//Librerias
package vista;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controlador.MVC;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Dimension;

/**
 * Clase Principal
 * @author David
 *
 */
public class VistaConfiguracion extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5132988294997632013L;
	//variables de Instanicia
	public JTextField txtRutaInfs;
	public JTextField txtRutaArts;
	public JTextField txtNewName;
	public JTextField txtName;
	public JTextField txtPass;
	public JTextField txtNewPass;
	public JTextField txtNewConf;
	public JTextField txtConf;
	public JButton btnUsuario,btnRutas;
	public JButton btnInfs;
	public JButton btnArts;
	public JButton btnConfirmar;	
	public JButton btnDefault;
	public JPanel panelDatosUsuario;
	public JPanel panelDatosUbicacion;
	public JPanel panelImagen;
	public JPanel panelRutaImagen;	
	public JButton btnImagen;	
	public JTextField txtRutaImg;
	public JButton btnImg;
		/**
	 * Launch the application.
	 */
	/*blic static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaConfig frame = new VistaConfig();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public VistaConfiguracion() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setMaximumSize(new Dimension(2147483647, 310));
		setVisible(true);
		setMinimumSize(new Dimension(725, 70));
		setTitle("Configuraci\u00F3n");
		setBounds(100, 100, 730, 330);
		getContentPane().setLayout(new MigLayout("gap rel 0, hidemode 3", "[grow,fill]", "[grow 0,fill][grow 0,fill][grow][]"));
		getContentPane().setBackground(Color.WHITE);
		setBorder(new LineBorder(MVC.COLOR_BG, 5, true));
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		//getContentPane().set
		
		JPanel panelUbicaciones = new JPanel();
		panelUbicaciones.setMaximumSize(new Dimension(32767, 280));
		getContentPane().add(panelUbicaciones, "cell 0 0,growx");
		//panelUbicaciones.setLayout(new MigLayout("gap rel 0, insets 0", "[][][right]", "[][99.00]"));
		panelUbicaciones.setLayout(new MigLayout("gap rel 0, insets 0 , hidemode 3", "[][grow][right]", "[][]"));
		panelUbicaciones.setBackground(Color.WHITE);
		
		JLabel lblUbicacionesDeArchivos = new JLabel("Ubicaciones de Archivos");
		lblUbicacionesDeArchivos.setFont(MVC.FUENTE);
		lblUbicacionesDeArchivos.setForeground(MVC.COLOR_BG);
		panelUbicaciones.add(lblUbicacionesDeArchivos, "cell 0 0");
		
		btnRutas = new JButton("\u25B2");
		btnRutas.setActionCommand("");
		panelUbicaciones.add(btnRutas, "cell 2 0");
		btnRutas.setBorder(null);
		btnRutas.setBackground(Color.WHITE);
		btnRutas.setForeground(MVC.COLOR_BG);
		//button1
		
		panelDatosUbicacion = new JPanel();
		panelDatosUbicacion.setBackground(MVC.COLOR_BG);
		panelUbicaciones.add(panelDatosUbicacion, "cell 0 1 3 1,growx");
		panelDatosUbicacion.setLayout(new MigLayout("", "[][grow][]", "[][fill]"));
		
		JLabel lblUbicacinInfracciones = new JLabel("Ruta Infracciones");
		lblUbicacinInfracciones.setFont(MVC.FUENTE);
		lblUbicacinInfracciones.setForeground(MVC.COLOR_HIGHLIGHT);
		panelDatosUbicacion.add(lblUbicacinInfracciones, "cell 0 0");
		
		txtRutaInfs = new JTextField();
		txtRutaInfs.setFont(MVC.FUENTE);
		txtRutaInfs.setBackground(MVC.COLOR_VALID);
		txtRutaInfs.setForeground(MVC.COLOR_BG);
		panelDatosUbicacion.add(txtRutaInfs, "cell 1 0,growx");
		txtRutaInfs.setColumns(10);
		
		btnInfs = new JButton("Establecer");
		btnInfs.setBackground(Color.WHITE);
		btnInfs.setForeground(MVC.COLOR_BG);
		btnInfs.setFont(MVC.FUENTE);
		//btnInfs.setBorder(null);
		panelDatosUbicacion.add(btnInfs, "cell 2 0");
		
		JLabel lblUbicacinArticulos = new JLabel("Ruta Articulos");
		lblUbicacinArticulos.setFont(MVC.FUENTE);
		lblUbicacinArticulos.setForeground(MVC.COLOR_HIGHLIGHT);
		panelDatosUbicacion.add(lblUbicacinArticulos, "cell 0 1");
		
		txtRutaArts = new JTextField();
		txtRutaArts.setFont(MVC.FUENTE);
		txtRutaArts.setBackground(MVC.COLOR_VALID);
		txtRutaArts.setForeground(MVC.COLOR_BG);
		panelDatosUbicacion.add(txtRutaArts, "cell 1 1,growx");
		txtRutaArts.setColumns(10);
		
		btnArts = new JButton("Establecer");
		btnArts.setFont(MVC.FUENTE);
		btnArts.setBackground(Color.WHITE);
		btnArts.setForeground(MVC.COLOR_BG);
		//btnArts.setBorder(null);
		panelDatosUbicacion.add(btnArts, "cell 2 1");
		
		
				
		JPanel panelUsuario = new JPanel();
		panelUsuario.setBackground(Color.WHITE);
		getContentPane().add(panelUsuario, "cell 0 1");
		panelUsuario.setLayout(new MigLayout("gap rel 0, insets 0 , hidemode 3", "[][grow,fill][right]", "[][]"));
		
		JLabel lblCambioDeUsuario = new JLabel("Cambio de Usuario");
		lblCambioDeUsuario.setFont(MVC.FUENTE);
		lblCambioDeUsuario.setBackground(Color.WHITE);
		lblCambioDeUsuario.setForeground(MVC.COLOR_BG);
		panelUsuario.add(lblCambioDeUsuario, "cell 0 0,alignx left,aligny top");
		
		btnUsuario = new JButton("\u25B2");
		btnUsuario.setActionCommand("");
		panelUsuario.add(btnUsuario, "cell 2 0,alignx left,aligny top");
		btnUsuario.setBackground(Color.WHITE);
		btnUsuario.setForeground(MVC.COLOR_BG);
		btnUsuario.setBorder(null);
				
		panelDatosUsuario = new JPanel();
		panelDatosUsuario.setBackground(MVC.COLOR_BG);
		panelUsuario.add(panelDatosUsuario, "cell 0 1 3 1,growx");
		panelDatosUsuario.setLayout(new MigLayout("", "[][grow,fill][][grow,fill]", "[][][][]"));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(MVC.FUENTE);
		lblNombre.setBackground(MVC.COLOR_BG);
		lblNombre.setForeground(MVC.COLOR_HIGHLIGHT);
		panelDatosUsuario.add(lblNombre, "cell 0 0,alignx trailing");
		
		txtName = new JTextField();
		txtName.setFont(MVC.FUENTE);
		txtName.setBackground(MVC.COLOR_VALID);
		txtName.setForeground(MVC.COLOR_BG);
		panelDatosUsuario.add(txtName, "cell 1 0,growx");
		txtName.setColumns(10);
		
		JLabel lblNombreNuevo = new JLabel("Nombre Nuevo");
		lblNombreNuevo.setFont(MVC.FUENTE);
		lblNombreNuevo.setBackground(MVC.COLOR_BG);
		lblNombreNuevo.setForeground(MVC.COLOR_HIGHLIGHT);
		panelDatosUsuario.add(lblNombreNuevo, "cell 2 0,alignx left");
		
		txtNewName = new JTextField();
		txtNewName.setFont(MVC.FUENTE);
		txtNewName.setBackground(MVC.COLOR_VALID);
		txtNewName.setForeground(MVC.COLOR_BG);
		panelDatosUsuario.add(txtNewName, "cell 3 0,growx");
		txtNewName.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(MVC.FUENTE);
		lblContrasea.setBackground(MVC.COLOR_BG);
		lblContrasea.setForeground(MVC.COLOR_HIGHLIGHT);
		panelDatosUsuario.add(lblContrasea, "cell 0 1,alignx trailing");
		
		txtPass = new JPasswordField();
		txtPass.setFont(MVC.FUENTE);
		txtPass.setBackground(MVC.COLOR_VALID);
		txtPass.setForeground(MVC.COLOR_BG);
		panelDatosUsuario.add(txtPass, "cell 1 1,growx");
		txtPass.setColumns(10);
		
		JLabel lblNuevaContrasea = new JLabel("Contrase\u00F1a Nueva");
		lblNuevaContrasea.setFont(MVC.FUENTE);
		lblNuevaContrasea.setBackground(MVC.COLOR_BG);
		lblNuevaContrasea.setForeground(MVC.COLOR_HIGHLIGHT);
		panelDatosUsuario.add(lblNuevaContrasea, "cell 2 1,alignx left");
		
		txtNewPass = new JPasswordField();
		txtNewPass.setFont(MVC.FUENTE);
		txtNewPass.setBackground(MVC.COLOR_VALID);
		txtNewPass.setForeground(MVC.COLOR_BG);
		panelDatosUsuario.add(txtNewPass, "cell 3 1,growx");
		txtNewPass.setColumns(10);
		
		JLabel lblConfirmarContrasea_1 = new JLabel("Confirmar Contrase\u00F1a");
		lblConfirmarContrasea_1.setFont(MVC.FUENTE);
		lblConfirmarContrasea_1.setBackground(MVC.COLOR_BG);
		lblConfirmarContrasea_1.setForeground(MVC.COLOR_HIGHLIGHT);
		panelDatosUsuario.add(lblConfirmarContrasea_1, "cell 0 2,alignx trailing");
		
		txtConf = new JPasswordField();
		txtConf.setFont(MVC.FUENTE);
		txtConf.setBackground(MVC.COLOR_VALID);
		txtConf.setForeground(MVC.COLOR_BG);
		panelDatosUsuario.add(txtConf, "cell 1 2,growx");
		txtConf.setColumns(10);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar Contrase\u00F1a");
		lblConfirmarContrasea.setFont(MVC.FUENTE);
		lblConfirmarContrasea.setBackground(MVC.COLOR_BG);
		lblConfirmarContrasea.setForeground(MVC.COLOR_HIGHLIGHT);
		panelDatosUsuario.add(lblConfirmarContrasea, "cell 2 2,alignx trailing");
		
		txtNewConf = new JPasswordField();
		txtNewConf.setFont(MVC.FUENTE);
		txtNewConf.setBackground(MVC.COLOR_VALID);
		txtNewConf.setForeground(MVC.COLOR_BG);
		panelDatosUsuario.add(txtNewConf, "cell 3 2,growx");
		txtNewConf.setColumns(10);
		
		btnConfirmar = new JButton("Cambiar usuario y contrase\u00F1a");
		btnConfirmar.setBackground(Color.WHITE);
		btnConfirmar.setFont(MVC.FUENTE);
		btnConfirmar.setForeground(MVC.COLOR_BG);
		panelDatosUsuario.add(btnConfirmar, "cell 0 3 4 1,growx");
		
		btnDefault = new JButton("Restaurar valore predeterminados");
		btnDefault.setBackground(Color.WHITE);
		btnDefault.setFont(MVC.FUENTE);
		btnDefault.setForeground(MVC.COLOR_BG);
		btnDefault.setVisible(false);
		
		panelImagen = new JPanel();
		getContentPane().add(panelImagen, "cell 0 2,grow");
		panelImagen.setLayout(new MigLayout("gap rel 0, insets 0 , hidemode 3", "[left][grow][right]", "[][]"));
		panelImagen.setBackground(Color.WHITE);
		
		JLabel lblImagenDeFondo = new JLabel("Imagen de fondo");
		lblImagenDeFondo.setFont(MVC.FUENTE);
		lblImagenDeFondo.setForeground(MVC.COLOR_BG);
		panelImagen.add(lblImagenDeFondo, "cell 0 0");
		
		panelRutaImagen = new JPanel();
		panelImagen.add(panelRutaImagen, "cell 0 1 3 1,grow");
		panelRutaImagen.setLayout(new MigLayout("", "[left][grow,fill][right]", "[]"));
		panelRutaImagen.setBackground(MVC.COLOR_BG);
		
		JLabel lblArchivoImagen = new JLabel("Archivo Imagen");
		lblArchivoImagen.setFont(MVC.FUENTE);
		lblArchivoImagen.setForeground(MVC.COLOR_HIGHLIGHT);
		panelRutaImagen.add(lblArchivoImagen, "cell 0 0,alignx left,aligny center");
		
		txtRutaImg = new JTextField();
		panelRutaImagen.add(txtRutaImg, "cell 1 0,growx,aligny top");
		txtRutaImg.setBackground(MVC.COLOR_VALID);
		txtRutaImg.setForeground(MVC.COLOR_BG);
		txtRutaImg.setFont(MVC.FUENTE);
		txtRutaImg.setColumns(10);
		
		btnImg = new JButton("Establecer");
		btnImg.setFont(MVC.FUENTE);
		btnImg.setBackground(Color.WHITE);
		btnImg.setForeground(MVC.COLOR_BG);
		panelRutaImagen.add(btnImg, "cell 2 0");
		
		btnImagen = new JButton("\u25B2");
		btnImagen.setBackground(Color.WHITE);
		btnImagen.setForeground(MVC.COLOR_BG);
		btnImagen.setBorder(null);
		panelImagen.add(btnImagen, "cell 2 0");
		getContentPane().add(btnDefault, "cell 0 3");
		
	}

}//Fin Clase Principal