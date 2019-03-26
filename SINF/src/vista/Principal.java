package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	public JButton btnInsertar, btnEstadisticas, btnConsultar, btnRespaldo;
	private JButton btnEtiquetas;
	private JDesktopPane dpEscritorio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 402);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[grow][grow,fill][grow,fill][grow,fill][grow,fill]"));
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BoletaInfraccion bi= new BoletaInfraccion();
				dpEscritorio.add(bi);
				bi.show();
			}
		});
		
		btnEtiquetas = new JButton("Etiquetas");
		btnEtiquetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnEtiquetas.getText().equals("")) {
					btnEstadisticas.setText("Estadisticas");
					btnConsultar.setText("Consultar");
					btnInsertar.setText("Insertar");
					btnRespaldo.setText("Respaldo");
					btnEtiquetas.setText("Etiquetas");	
				}else {
					btnEstadisticas.setText("");
					btnConsultar.setText("");
					btnInsertar.setText("");
					btnRespaldo.setText("");
					btnEtiquetas.setText("");
				}
			}
		});
		btnEtiquetas.setHorizontalAlignment(SwingConstants.LEADING);
		btnEtiquetas.setBackground(Color.WHITE);
		btnEtiquetas.setBorder(null);
		btnEtiquetas.setFont(new Font("Arial", Font.BOLD, 14));
		btnEtiquetas.setIcon(new ImageIcon(Principal.class.getResource("/iconos/icons8-start-menu-42.png")));
		contentPane.add(btnEtiquetas, "cell 0 0,grow");
		btnInsertar.setBorder(null);
		btnInsertar.setHorizontalAlignment(SwingConstants.LEADING);
		btnInsertar.setIcon(new ImageIcon(Principal.class.getResource("/iconos/icons8-form-42.png")));
		btnInsertar.setFont(new Font("Arial", Font.BOLD, 14));
		btnInsertar.setBackground(Color.WHITE);
		contentPane.add(btnInsertar, "cell 0 1,grow");
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBorder(null);
		btnConsultar.setHorizontalAlignment(SwingConstants.LEADING);
		btnConsultar.setIcon(new ImageIcon(Principal.class.getResource("/iconos/icons8-view-42.png")));
		btnConsultar.setFont(new Font("Arial", Font.BOLD, 14));
		btnConsultar.setBackground(Color.WHITE);
		contentPane.add(btnConsultar, "cell 0 2,grow");
		
		btnEstadisticas = new JButton("Estadisticas");
		btnEstadisticas.setBorder(null);
		btnEstadisticas.setHorizontalAlignment(SwingConstants.LEADING);
		btnEstadisticas.setIcon(new ImageIcon(Principal.class.getResource("/iconos/icons8-statistics-42.png")));
		btnEstadisticas.setFont(new Font("Arial", Font.BOLD, 14));
		btnEstadisticas.setBackground(Color.WHITE);
		contentPane.add(btnEstadisticas, "cell 0 3,grow");
		
		btnRespaldo = new JButton("Respaldo");
		btnRespaldo.setBorder(null);
		btnRespaldo.setHorizontalAlignment(SwingConstants.LEADING);
		btnRespaldo.setIcon(new ImageIcon(Principal.class.getResource("/iconos/icons8-database-restore-42.png")));
		btnRespaldo.setFont(new Font("Arial", Font.BOLD, 14));
		btnRespaldo.setBackground(Color.WHITE);
		contentPane.add(btnRespaldo, "cell 0 4,grow");
		
		dpEscritorio = new JDesktopPane();
		dpEscritorio.setBorder(new LineBorder(Color.BLACK));
		dpEscritorio.setBackground(Color.WHITE);
		contentPane.add(dpEscritorio, "cell 1 0 1 5,grow");
	}
}
