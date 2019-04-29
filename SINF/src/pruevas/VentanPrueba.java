/**
 * programa donde pruebo comportamientos o funciones que quiero implementar
 */
package pruevas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanPrueba {

	private JFrame frame;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanPrueba window = new VentanPrueba();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanPrueba() {
		new JFileChooser().showOpenDialog(frame);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frame.getContentPane().setFocusable(true);
				frame.getContentPane().requestFocus();
				lblNewLabel.setText("DERP");
			}
		});
		frame.getContentPane().setFocusable(true);
		frame.getContentPane().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				((JPanel)e.getSource()).setBackground(Color.yellow);								
			}
			@Override
			public void focusLost(FocusEvent e) {
				((JPanel)e.getSource()).setBackground(Color.green);
				//for(Component c:frame.getComponents())
				if (findFocus(frame)!=null)btnNewButton.setText(findFocus(frame).toString());
					else btnNewButton.setText("null");
				//btnNewButton.setText(frame.getMostRecentFocusOwner().toString());
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][grow][grow][][]", "[][grow][][][grow][][]"));
		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				scrollPane.requestFocus();
			}
		});
		scrollPane.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				scrollPane.setBackground(Color.ORANGE);
			}
			@Override
			public void focusLost(FocusEvent e) {
				scrollPane.setBackground(Color.magenta);
				//for(Component c:frame.getComponents())
				if (findFocus(frame)!=null)btnNewButton.setText(findFocus(frame).toString());
					else btnNewButton.setText("null");
				//btnNewButton.setText(frame.getMostRecentFocusOwner().toString());
			}
		});
		frame.getContentPane().add(scrollPane, "cell 2 1,grow");
		
		table = new JTable();
		table.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setText(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "2", "3", "4", "5"},
				{"a", "b", "c", "d", "e"},
				{"a1", "b2", "c3", "d4", "e5"},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.addFocusListener(new FocusAdapter() {
				@Override
			public void focusGained(FocusEvent arg0) {
				panel_1.setBackground(Color.black);
			}
			@Override
			public void focusLost(FocusEvent e) {
				panel_1.setBackground(Color.white);
				//for(Component c:frame.getComponents())
				if (findFocus(frame)!=null)btnNewButton.setText(findFocus(frame).toString());
					else btnNewButton.setText("null");
				//btnNewButton.setText(frame.getMostRecentFocusOwner().toString());
			}
		});
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				lblNewLabel.setForeground(Color.white);
				lblNewLabel.setText(""+(int)arg0.getKeyChar());
				//label.
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panel_1.setBackground(Color.blue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_1.setBackground(Color.red);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblNewLabel.setForeground(Color.blue);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panel.requestFocus();
			}
		});
		frame.getContentPane().add(panel, "cell 3 1,grow");
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				frame.getContentPane().setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void focusLost(FocusEvent e) {
				frame.getContentPane().setBackground(Color.DARK_GRAY);
				//for(Component c:frame.getComponents())
				if (findFocus(frame)!=null)btnNewButton.setText(findFocus(frame).toString());
					else btnNewButton.setText("null");
				//btnNewButton.setText(frame.getMostRecentFocusOwner().toString());
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				lblNewLabel.setForeground(Color.black);
				lblNewLabel.setText(""+(int)arg0.getKeyChar());
			}
		});
		frame.getContentPane().add(textField, "cell 2 2,growx");
		textField.setColumns(10);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton.setText(lblNewLabel.getText());
				int j=5;
				for(int i=0; i<j; i++) {
					if(j<15)
						j++;
					btnNewButton.setText(""+i);
				}
					
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 3 2");
		
		panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel.setText("Herp");
				frame.getContentPane().requestFocus();
				if (findFocus(frame)!=null)btnNewButton.setText(findFocus(frame).toString());
				else btnNewButton.setText("null");
			}
			
		});
		frame.getContentPane().add(panel_1, "cell 2 3,grow");
		
		lblNewLabel = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel, "cell 3 3");
	}
	public Component findFocus(Component c) {
		System.out.println("ID: "+c.hashCode()+" class: "+c.getClass()+" container :"+ (c instanceof Container)+" hasFocus: "+c.hasFocus());
		if(c instanceof Container) {
			//System.out.println("ID: "+c.hashCode()+" class: "+c.getClass());
			Component comps[] = ((Container)c).getComponents();
			for (int i = 0; i < comps.length; i++) {
				if (comps[i].hasFocus()) {
				return comps[i];
				}
	
				if (comps[i] instanceof Container) {
					Component subcomp = findFocus((Container)comps[i]);
					if (subcomp != null) return c;
				}
			}
		}
		return null;
		}
}
