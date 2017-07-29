package swing.autorPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.AutorServiceBean;
import jpa.Autor;

public class NoviAutor extends JFrame {
	
	public NoviAutor() {
		setTitle("Autor");
		setSize(300, 300);
		
		JPanel panel = new JPanel();
		panel.setSize(300, 300);
		
		JLabel imeAutora = new JLabel("Ime autora:");
		JTextField txtIme = new JTextField(10);
		
		JLabel prezimeAutora = new JLabel("Prezime autora:");
		JTextField txtPrezime = new JTextField("Prezime");
		
		JButton potvrdi = new JButton("Dodaj");
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				autorServiceBean.save(new Autor(autorServiceBean.getCount() + 1, 
						txtIme.getText(), txtPrezime.getText()));
			}
		});
		JButton ponisti = new JButton("Ponisti");
		
		panel.add(imeAutora);
		panel.add(txtIme);
		panel.add(prezimeAutora);
		panel.add(txtPrezime);
		panel.add(potvrdi);
		panel.add(ponisti);
		
		add(panel);
		
		
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem item = new JMenuItem("Novi autor");
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
	
	private AutorServiceBean autorServiceBean = new AutorServiceBean();

}
