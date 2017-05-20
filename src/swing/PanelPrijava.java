package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

//import bussines.OsobljeServiceBean;
//import ejb.Osoblje;


public class PanelPrijava extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField_1;
	private JTextField passwordField;
	private JLabel lblUsername;
	private JLabel lblUspjesanLogin;
	private JLabel lblPoruka;
	private JLabel lblRasporedSati;
	private JButton prijava;
	private JButton odjava;
//	private OsobljeServiceBean osoba = new OsobljeServiceBean();
	private String username;
	private String password;
//	private Osoblje osoblje = new Osoblje();
	public JPanel panelLogIn = new JPanel();
	public JPanel panelLogOut = new JPanel();
	JLabel tekst;
//	Osoblje o;
	String str;
	public PanelPrijava() {
		
		prijava = new JButton("Prijavi se");
		prijava.setBounds(270, 205, 117, 20);
		prijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				username = textField_1.getText();
				password = passwordField.getText();
//				o = osoba.findByAliasAndPassword(username, password);
				int o = 3;
				
				if (o != 0) {
//					osoblje = o;			
//					str = "Postovani "+ o.getIme() + " " + o.getPrezime()+ ",";
					tekst.setText(str);
					remove(panelLogIn);
					add(panelLogOut);
					getRootPane().repaint();
				}
				else
	            {
	                JOptionPane.showMessageDialog(null,
	                   "Incorrect username or password.");
	            }

			}
		});

		textField_1 = new JTextField();
		textField_1.setBounds(271, 143, 114, 19);
		textField_1.setColumns(10);

		lblUsername = new JLabel("Username");
		lblUsername.setBounds(188, 145, 72, 15);

		passwordField = new JPasswordField(20);
		passwordField.setBounds(271, 174, 114, 19);

		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setBounds(25, 75, 207, 133);
//		ImageIcon image = new ImageIcon(
//		PanelPrijava.class.getResource("/resource/ikone/rsz_untz.png"));
//		lblNewLabel.setIcon(image);

		lblRasporedSati = new JLabel("Raspored sati");
		lblRasporedSati.setBounds(204, 75, 157, 24);
		lblRasporedSati.setForeground(new Color(153, 0, 0));
		lblRasporedSati.setFont(new Font("SansSerif", Font.BOLD, 20));
		panelLogIn.setLayout(null);
		panelLogIn.add(lblNewLabel);
		
		panelLogIn.add(lblRasporedSati);
		panelLogIn.add(lblUsername);
		panelLogIn.add(textField_1);
		panelLogIn.add(passwordField);
		panelLogIn.add(prijava);
		panelLogIn.setVisible(true);

		tekst = new JLabel();
		tekst.setForeground(new Color(153, 0, 0));
		tekst.setFont(new Font("SansSerif", Font.BOLD, 16));
		tekst.setBounds(150, 71, 500, 24);
		panelLogOut.add(tekst);
		
		JLabel lblslika = new JLabel(" ");
		lblslika.setBounds(25, 75, 207, 133);
//		ImageIcon slika = new ImageIcon(PanelPrijava.class.getResource("/resource/ikone/rsz_untz.png"));
//		lblslika.setIcon(slika);
		panelLogOut.add(lblslika);

		lblPoruka = new JLabel("Dobrodosli u aplikaciju Raspored sati!");
		lblPoruka.setBounds(150, 100, 500, 30);
		lblPoruka.setForeground(new Color(153, 0, 0));
		lblPoruka.setFont(new Font("SansSerif", Font.BOLD, 16));
		panelLogOut.add(lblPoruka);
		// panelLogOut.setVisible(false);

		odjava = new JButton("Odjavi se");
		odjava.setBounds(250, 193, 106, 25);
		odjava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == odjava) {
					username = null;
					password = null;
					remove(panelLogOut);
					add(panelLogIn);
//					osoblje = new Osoblje();
					getRootPane().repaint();
				
				}
			}
		});
		panelLogOut.setLayout(null);
		panelLogOut.add(odjava);

		panelLogOut.setBackground(UIManager.getColor("Panel.background"));
		setLayout(new BorderLayout(0, 0));
		add(panelLogIn);

		JLabel label = new JLabel("Password");
		label.setBounds(188, 176, 70, 15);
		panelLogIn.add(label);
	}

//	public Osoblje getOsoblje() {
//		return osoblje;
//	}

//	public void setOsoblje(Osoblje osoblje) {
//		this.osoblje = osoblje;
//	}
}