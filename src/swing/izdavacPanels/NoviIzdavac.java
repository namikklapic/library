package swing.izdavacPanels;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.IzdavacServiceBean;
import jpa.Izdavac;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NoviIzdavac extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public NoviIzdavac() {
		
		setTitle("Add a new publisher");
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x-300, y-200);
		setSize(500, 500);

	    setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(95, 158, 160));
		
		JLabel imeIzdavaca = new JLabel("Name of publisher");
		imeIzdavaca.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		imeIzdavaca.setForeground(new Color(255, 255, 255));
		imeIzdavaca.setBounds(167, 41, 160, 35);
		
		JButton ponisti = new JButton("Cancel");
		ponisti.setBorder(null);
		ponisti.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		ponisti.setForeground(new Color(255, 255, 255));
		ponisti.setBackground(new Color(0, 59, 70));
		ponisti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ponisti.setBackground(new Color(7, 87, 91));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ponisti.setBackground(new Color(0, 59, 70));

			}
		});
		ponisti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		ponisti.setBounds(297, 349, 97, 41);
		panel.setLayout(null);
		
		
		panel.add(imeIzdavaca);
		
		
		JButton potvrdi = new JButton ("Add");
		potvrdi.setBorder(null);
		potvrdi.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		potvrdi.setForeground(new Color(255, 255, 255));
		potvrdi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				potvrdi.setBackground(new Color(7, 87, 91));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				potvrdi.setBackground(new Color(0, 59, 70));

			}
		});
		potvrdi.setBackground(new Color(0, 59, 70));
		potvrdi.setBounds(100, 346, 97, 47);
		potvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {				
				izdavacServiceBean.save(new Izdavac(izdavacServiceBean.getCount() + 1, txtIzdavac.getText()));
			}
		});
		
		panel.add(potvrdi);
		txtIzdavac.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		txtIzdavac.setBounds(155, 89, 183, 27);
		panel.add(txtIzdavac);
		panel.add(ponisti);
		getContentPane().add(panel);
	}
	
	public NoviIzdavac(Izdavac i) {
		this();
		txtIzdavac.setText(i.getNazivIzdavaca());
	}
	
	public JMenuItem getMenuItem(JPanel current) {
		JMenuItem menuItem = new JMenuItem("Novi izdavac");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				CardLayout cl = (CardLayout)(current.getLayout());
				cl.show(current, "defaultPanel");
				prikazi();
			}
		});
		return menuItem;
	}
	
	
	public void prikazi() {
		setVisible(true);
	}
	
	private IzdavacServiceBean izdavacServiceBean = new IzdavacServiceBean();
	JTextField txtIzdavac = new JTextField(15);
	
}
