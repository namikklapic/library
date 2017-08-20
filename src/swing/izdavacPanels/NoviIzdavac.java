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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.IzdavacServiceBean;
import jpa.Izdavac;
import swing.autorPanels.NoviAutor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class NoviIzdavac extends JFrame {

	public NoviIzdavac() {
		
		setTitle("Add a new publisher");
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 400;
		int sirinaProzora = 400;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(400, 300);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 16, 376, 271);
		panel.setBackground(new Color(255,255,255,70));
		
		imeIzdavaca = new JLabel("Name of publisher");
		imeIzdavaca.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		imeIzdavaca.setForeground(Color.BLACK);
		imeIzdavaca.setBounds(12, 86, 160, 35);
		
		ponisti = new JButton("Cancel");
		ponisti.setBorder(null);
		ponisti.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		ponisti.setForeground(new Color(255, 255, 255));
		ponisti.setBackground(Color.DARK_GRAY);
		ponisti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ponisti.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ponisti.setBackground(Color.DARK_GRAY);

			}
		});
		ponisti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtIzdavac.setText("");
				txtIzdavac.setBackground(Color.WHITE);
				dispose();
			}
		});
		ponisti.setBounds(202, 214, 97, 41);
		panel.setLayout(null);
		
		
		panel.add(imeIzdavaca);
		
		
		potvrdi = new JButton ("Save");
		potvrdi.setBorder(null);
		potvrdi.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		potvrdi.setForeground(new Color(255, 255, 255));
		potvrdi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				potvrdi.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				potvrdi.setBackground(Color.DARK_GRAY);

			}
		});
		potvrdi.setBackground(Color.DARK_GRAY);
		potvrdi.setBounds(80, 214, 97, 41);
		potvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {				
				if(isValidIzdavac())
					saveIzdavac();
				displayMessageDialogBox();
			}
		});
		
		panel.add(potvrdi);
		txtIzdavac.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtIzdavac.setBounds(167, 90, 197, 27);
		panel.add(txtIzdavac);
		panel.add(ponisti);
		getContentPane().add(panel);
		
		JLabel backgroundPicture = new JLabel("");
		backgroundPicture.setIcon(new ImageIcon(NoviAutor.class.getResource("/swing/images/background.jpg")));
		backgroundPicture.setBounds(0, 0, 400, 300);
		getContentPane().add(backgroundPicture);
		
		panel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent event){
				txtIzdavac.setBackground(Color.WHITE);
			}
		});
	}
	
	public NoviIzdavac(Izdavac i) {
		this();
		txtIzdavac.setText(i.getNazivIzdavaca());
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem menuItem = new JMenuItem("New publisher");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				prikazi();
			}
		});
		return menuItem;
	}
	
	public void prikazi() {
		setVisible(true);
	}
	
	public void saveIzdavac(){
		izdavacServiceBean.save(new Izdavac(izdavacServiceBean.getCount()+1, txtIzdavac.getText()));
		message = "The publisher has been successfully saved!";
	}
	
	private boolean isValidIzdavac(){
		if(txtIzdavac.getText().equals("") || txtIzdavac.getText().equals(null)){
			txtIzdavac.setBackground(Color.RED);
			message = "Publisher name is missing!";
			return false;
		}
		if(izdavacServiceBean.existsIzdavac(txtIzdavac.getText())){
			message = "Entered publisher alreday exists!";
			return false;
		}
		return true;
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private JPanel panel;
	
	private String message;
	private IzdavacServiceBean izdavacServiceBean = new IzdavacServiceBean();
	
	private JTextField txtIzdavac = new JTextField(15);
	private JLabel imeIzdavaca;
	private JButton ponisti;
	private JButton potvrdi;
}
