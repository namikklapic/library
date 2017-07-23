package swing.VrstaKnjigePanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.VrstaKnjigeServiceBean;
import jpa.VrstaKnjige;

public class NovaVrstaKnjige extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NovaVrstaKnjige() {
		setTitle("Nova vrsta knjige");
		setSize(300, 300);
		JPanel panel = new JPanel();
		panel.setSize(300, 300);
		
		JLabel imeVrsteKnjige = new JLabel("Naziv vrste knjige:");
		JTextField txtVrstaKnjige = new JTextField(15);
		
		JButton potvrdi = new JButton("Dodaj");
		potvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				vrstaKnjigeServiceBean.
				save(new VrstaKnjige(vrstaKnjigeServiceBean.getCount() + 1, txtVrstaKnjige.getText()));
			}
		});
		JButton ponisti = new JButton("Ponisti");
		
		panel.add(imeVrsteKnjige);
		panel.add(txtVrstaKnjige);
		panel.add(potvrdi);
		panel.add(ponisti);
		add(panel);
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem item = new JMenuItem("Nova vrsta knjige");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				prikazi();
			}
		});
		return item;
	}
	public void prikazi() {
		setVisible(true);
	}
	
	private VrstaKnjigeServiceBean vrstaKnjigeServiceBean = new VrstaKnjigeServiceBean();

}
