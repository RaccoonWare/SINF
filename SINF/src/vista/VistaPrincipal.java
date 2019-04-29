/**
 * Vista ventana principal
 * @author Mario
 */
/* importaci�n librerias*/
package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

import controlador.MVC;
import javax.swing.JScrollPane;

/**
 * Clase principal
 * @see ModeloPrincipal
 * @see ModeloPrincipal
 */
public class VistaPrincipal extends JFrame {
	//Varables de clase
	private static final long serialVersionUID = 5768440461778667680L;
	//Variables de instancia	
	private JPanel contentPane;//contenedor principal
	public static JDesktopPane dpEscritorio;//panel contenedor de ventanas internas
	public JButton btnInfracciones,btnArticulos, btnConsultar, btnEstadisticas;//botones para llamar a otras venanas
	public JButton btnRestaurar, btnRespaldar;//boton funciones de respaldo y restauraci�n de datos
	public JButton btnEtiquetas;//boto para mostrar/ocultar etiquetas
	public JButton btnConfiguracin;//boton configuraci�n
	
	
	///////////////Constructores e inicializadores
	/**
	 * constructor por defecto
	 */
	public VistaPrincipal() {
		//configuraci�n ventana
		setBackground(Color.WHITE);//color de fondo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//accion de cierre
		setBounds(100, 100, 1036, 521);//tama�o por defecto
		
		
		
		//inicializaci�n panaeles
		////panel principal
		contentPane = new JPanel();
		contentPane.setBackground(MVC.COLOR_BG);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow,fill]", "[grow][grow,fill][grow][grow,fill][grow,fill][grow,fill][grow][grow]"));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/logoTransito.png")));
		lblNewLabel.setBounds(345, 168, 553, 386);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 0 2 8,grow");
		//panel escritorio
		dpEscritorio = new JDesktopPane();
		scrollPane.setViewportView(dpEscritorio);
		dpEscritorio.setBorder(new LineBorder(Color.BLACK));
		dpEscritorio.setBackground(Color.WHITE);
		dpEscritorio.add(lblNewLabel);
		
		
		
		//Inicializaci�n botontes
		////Boton  etiquetas
		btnEtiquetas = new JButton("  ");
		btnEtiquetas.setContentAreaFilled(false);
		contentPane.add(btnEtiquetas, "cell 0 0,grow");
		btnEtiquetas.setOpaque(false);
		btnEtiquetas.setBorder(null);
		btnEtiquetas.setFont(MVC.FUENTE);
		btnEtiquetas.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-men\u00FA-filled-42.png")));
		
		////boton infracciones
		btnInfracciones = new JButton("Infracciones");
		btnInfracciones.setBorder(null);
		btnInfracciones.setForeground(MVC.COLOR_HIGHLIGHT);
		btnInfracciones.setContentAreaFilled(false);
		contentPane.add(btnInfracciones, "cell 0 1,grow");
		btnInfracciones.setHorizontalAlignment(SwingConstants.LEADING);
		btnInfracciones.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-formulario-42(1).png")));
		btnInfracciones.setFont(MVC.FUENTE);
		btnInfracciones.setBackground(Color.GRAY);
		
		////boton articulos
		btnArticulos = new JButton("Art\u00EDculos");
		btnArticulos.setForeground(MVC.COLOR_HIGHLIGHT);
		btnArticulos.setContentAreaFilled(false);
		contentPane.add(btnArticulos, "cell 0 2,grow");
		btnArticulos.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-lista-de-verificaci\u00F3n-42.png")));
		btnArticulos.setHorizontalAlignment(SwingConstants.LEADING);
		btnArticulos.setFont(MVC.FUENTE);
		btnArticulos.setBorder(null);
		btnArticulos.setBackground(Color.GRAY);
		
		////boton consultas
		btnConsultar = new JButton("Consultar");
		btnConsultar.setForeground(MVC.COLOR_HIGHLIGHT);
		btnConsultar.setContentAreaFilled(false);
		contentPane.add(btnConsultar, "cell 0 3,grow");
		btnConsultar.setBorder(null);
		btnConsultar.setHorizontalAlignment(SwingConstants.LEADING);
		btnConsultar.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-ver-archivo-filled-42.png")));
		btnConsultar.setFont(MVC.FUENTE);
		btnConsultar.setBackground(Color.GRAY);

		////boton estadisticas
		btnEstadisticas = new JButton("Estad\u00EDsticas");
		btnEstadisticas.setForeground(MVC.COLOR_HIGHLIGHT);
		btnEstadisticas.setContentAreaFilled(false);
		contentPane.add(btnEstadisticas, "cell 0 4,grow");
		btnEstadisticas.setBorder(null);
		btnEstadisticas.setHorizontalAlignment(SwingConstants.LEADING);
		btnEstadisticas.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-estad\u00EDsticas-42(1).png")));
		btnEstadisticas.setFont(MVC.FUENTE);
		btnEstadisticas.setBackground(Color.GRAY);

		////boton respaldos
		btnRespaldar = new JButton("Respaldar");
		btnRespaldar.setForeground(MVC.COLOR_HIGHLIGHT);
		btnRespaldar.setContentAreaFilled(false);
		contentPane.add(btnRespaldar, "cell 0 5,grow");
		btnRespaldar.setBorder(null);
		btnRespaldar.setHorizontalAlignment(SwingConstants.LEADING);
		btnRespaldar.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-base-de-datos-filled-42(1).png")));
		btnRespaldar.setFont(MVC.FUENTE);
		btnRespaldar.setBackground(Color.GRAY);

		////boton restauraci�n
		btnRestaurar = new JButton("Restaurar");
		btnRestaurar.setForeground(MVC.COLOR_HIGHLIGHT);
		btnRestaurar.setContentAreaFilled(false);
		contentPane.add(btnRestaurar, "cell 0 6,grow");
		btnRestaurar.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-base-de-datos-filled-42.png")));
		btnRestaurar.setHorizontalAlignment(SwingConstants.LEADING);
		btnRestaurar.setFont(MVC.FUENTE);
		btnRestaurar.setBorder(null);
		btnRestaurar.setBackground(Color.GRAY);

		////boton configuraci�n
		btnConfiguracin = new JButton("Configuraci\u00F3n  ");
		btnConfiguracin.setForeground(MVC.COLOR_HIGHLIGHT);
		btnConfiguracin.setContentAreaFilled(false);
		contentPane.add(btnConfiguracin, "cell 0 7,grow");
		btnConfiguracin.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-ajustes-42.png")));
		btnConfiguracin.setHorizontalAlignment(SwingConstants.LEADING);
		btnConfiguracin.setFont(MVC.FUENTE);
		btnConfiguracin.setBorder(null);
		btnConfiguracin.setBackground(Color.GRAY);
	}//fin constructor VistaPrincipal
}//fin clase VistaPrincipal
