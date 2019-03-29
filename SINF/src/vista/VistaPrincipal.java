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

public class VistaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5768440461778667680L;
	private JPanel contentPane;
	public JButton btnInfracciones, btnEstadisticas, btnConsultar, btnRespaldar;
	public JButton btnEtiquetas;
	public static JDesktopPane dpEscritorio;
	public JButton btnRestaurar;
	public JButton btnArticulos;
	public JButton btnConfiguracin;

	public VistaPrincipal() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 521);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(58, 63, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow,fill]", "[grow][grow,fill][grow][grow,fill][grow,fill][grow,fill][grow][grow]"));

		btnEtiquetas = new JButton("  ");
		btnEtiquetas.setContentAreaFilled(false);
		contentPane.add(btnEtiquetas, "cell 0 0,grow");
		btnEtiquetas.setOpaque(false);
		btnEtiquetas.setBorder(null);
		btnEtiquetas.setFont(new Font("Arial", Font.BOLD, 14));
		btnEtiquetas.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-men\u00FA-filled-42.png")));

		dpEscritorio = new JDesktopPane();
		dpEscritorio.setBorder(new LineBorder(Color.BLACK));
		dpEscritorio.setBackground(Color.WHITE);
		contentPane.add(dpEscritorio, "cell 1 0 1 8,grow");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/logoTransito.png")));
		lblNewLabel.setBounds(345, 168, 553, 386);
		dpEscritorio.add(lblNewLabel);

		btnInfracciones = new JButton("Infracciones");
		btnInfracciones.setBorder(null);
		btnInfracciones.setForeground(new Color(234, 254, 255));
		btnInfracciones.setContentAreaFilled(false);
		contentPane.add(btnInfracciones, "cell 0 1,grow");
		btnInfracciones.setHorizontalAlignment(SwingConstants.LEADING);
		btnInfracciones.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-formulario-42(1).png")));
		btnInfracciones.setFont(new Font("Arial", Font.BOLD, 14));
		btnInfracciones.setBackground(Color.GRAY);

		btnArticulos = new JButton("Art\u00EDculos");
		btnArticulos.setForeground(new Color(234, 254, 255));
		btnArticulos.setContentAreaFilled(false);
		contentPane.add(btnArticulos, "cell 0 2,grow");
		btnArticulos.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-lista-de-verificaci\u00F3n-42.png")));
		btnArticulos.setHorizontalAlignment(SwingConstants.LEADING);
		btnArticulos.setFont(new Font("Arial", Font.BOLD, 14));
		btnArticulos.setBorder(null);
		btnArticulos.setBackground(Color.GRAY);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setForeground(new Color(234, 254, 255));
		btnConsultar.setContentAreaFilled(false);
		contentPane.add(btnConsultar, "cell 0 3,grow");
		btnConsultar.setBorder(null);
		btnConsultar.setHorizontalAlignment(SwingConstants.LEADING);
		btnConsultar.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-ver-archivo-filled-42.png")));
		btnConsultar.setFont(new Font("Arial", Font.BOLD, 14));
		btnConsultar.setBackground(Color.GRAY);

		btnEstadisticas = new JButton("Estad\u00EDsticas");
		btnEstadisticas.setForeground(new Color(234, 254, 255));
		btnEstadisticas.setContentAreaFilled(false);
		contentPane.add(btnEstadisticas, "cell 0 4,grow");
		btnEstadisticas.setBorder(null);
		btnEstadisticas.setHorizontalAlignment(SwingConstants.LEADING);
		btnEstadisticas.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-estad\u00EDsticas-42(1).png")));
		btnEstadisticas.setFont(new Font("Arial", Font.BOLD, 14));
		btnEstadisticas.setBackground(Color.GRAY);

		btnRespaldar = new JButton("Respaldar");
		btnRespaldar.setForeground(new Color(234, 254, 255));
		btnRespaldar.setContentAreaFilled(false);
		contentPane.add(btnRespaldar, "cell 0 5,grow");
		btnRespaldar.setBorder(null);
		btnRespaldar.setHorizontalAlignment(SwingConstants.LEADING);
		btnRespaldar.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-base-de-datos-filled-42(1).png")));
		btnRespaldar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRespaldar.setBackground(Color.GRAY);

		btnRestaurar = new JButton("Restaurar");
		btnRestaurar.setForeground(new Color(234, 254, 255));
		btnRestaurar.setContentAreaFilled(false);
		contentPane.add(btnRestaurar, "cell 0 6,grow");
		btnRestaurar.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-base-de-datos-filled-42.png")));
		btnRestaurar.setHorizontalAlignment(SwingConstants.LEADING);
		btnRestaurar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRestaurar.setBorder(null);
		btnRestaurar.setBackground(Color.GRAY);

		btnConfiguracin = new JButton("Configuraci\u00F3n  ");
		btnConfiguracin.setForeground(new Color(234, 254, 255));
		btnConfiguracin.setContentAreaFilled(false);
		contentPane.add(btnConfiguracin, "cell 0 7,grow");
		btnConfiguracin.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos/icons8-ajustes-42.png")));
		btnConfiguracin.setHorizontalAlignment(SwingConstants.LEADING);
		btnConfiguracin.setFont(new Font("Arial", Font.BOLD, 14));
		btnConfiguracin.setBorder(null);
		btnConfiguracin.setBackground(Color.GRAY);
	}
}
