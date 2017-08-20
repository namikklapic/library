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
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class NoviIzdavac extends JFrame {

	public NoviIzdavac() {
		
		setTitle("Add a new publisher");
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x-300, y-200);
		setSize(500, 500);

	    setResizable(false);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 16, 470, 436);
		panel.setBackground(new Color(255,255,255,70));
		
		imeIzdavaca = new JLabel("Name of publisher");
		imeIzdavaca.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		imeIzdavaca.setForeground(Color.BLACK);
		imeIzdavaca.setBounds(167, 41, 160, 35);
		
		ponisti = new JButton("Cancel");
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
				txtIzdavac.setText("");
				txtIzdavac.setBackground(Color.WHITE);
				dispose();
			}
		});
		ponisti.setBounds(297, 349, 97, 41);
		panel.setLayout(null);
		
		
		panel.add(imeIzdavaca);
		
		
		potvrdi = new JButton ("Save");
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
				if(isValidIzdavac())
					saveIzdavac();
				displayMessageDialogBox();
			}
		});
		
		panel.add(potvrdi);
		txtIzdavac.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		txtIzdavac.setBounds(155, 89, 183, 27);
		panel.add(txtIzdavac);
		panel.add(ponisti);
		getContentPane().add(panel);
		
		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon(NoviIzdavac.class.getResource("/swing/images/test.jpg")));
		Background.setBounds(0, 0, 494, 465);
		getContentPane().add(Background);
		
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
