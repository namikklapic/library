package swing.izdavacPanels;

import java.awt.Panel;
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

public class NoviIzdavac extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public NoviIzdavac() {
		
		setTitle("Novi izdavac");
		setSize(300, 300);
		
		JPanel panel = new JPanel();
		panel.setSize(300, 300);
		
		JLabel imeIzdavaca = new JLabel("Ime izdavaca");
		JTextField txtIzdavac = new JTextField(15);
		
		JButton potvrdi = new JButton ("Dodaj");
		potvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {				
				izdavacServiceBean.save(new Izdavac(izdavacServiceBean.getCount() + 1, txtIzdavac.getText()));
			}
		});
		JButton ponisti = new JButton("Ponisti");
		
		
		panel.add(imeIzdavaca);
		panel.add(txtIzdavac);
		panel.add(potvrdi);
		panel.add(ponisti);
		add(panel);
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem menuItem = new JMenuItem("Novi izdavac");
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
	
	private IzdavacServiceBean izdavacServiceBean = new IzdavacServiceBean();
	
}
