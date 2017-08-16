package swing.predmetPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.PredmetServiceBean;
import jpa.Autor;
import jpa.Predmet;

public class NoviPredmet extends JFrame {

	public NoviPredmet() {
		setTitle("NoviPredmet");
		setSize(300, 300);
		
		JPanel panel = new JPanel();
		panel.setSize(300, 300);
		
		JLabel sifraPredmeta = new JLabel("Šifra predmeta");
		
		JLabel nazivPredmeta = new JLabel("Naziv predmeta");
		
		JLabel skraceniNazivPredmeta = new JLabel("Skraceni naziv predmeta");
		
		JLabel brojSemestra = new JLabel("Semestar");
		
		JButton potvrdi = new JButton("Potvrdi");
		JButton ponisti =  new JButton("Poništi");
		
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				String sifra = txtSifra.getText();
				String naziv = txtNaziv.getText();
				String skrNaziv = txtSkrNaziv.getText();
				int semestar = Integer.parseInt(txtSemestar.getText());
				predmetServiceBean.save(new Predmet(sifra, naziv, skrNaziv, semestar));
			}
		});
		
		ponisti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				txtSifra.setText("");
				txtNaziv.setText("");
				txtSkrNaziv.setText("");
				txtSemestar.setText("");
				dispose();
			}
		});
		
		panel.add(sifraPredmeta);
		panel.add(txtSifra);
		panel.add(nazivPredmeta);
		panel.add(txtNaziv);
		panel.add(skraceniNazivPredmeta);
		panel.add(txtSkrNaziv);
		panel.add(brojSemestra);
		panel.add(txtSemestar);
		panel.add(potvrdi);
		panel.add(ponisti);
		
		add(panel);
		
	}
	
	public NoviPredmet(Predmet p) {
		this();
		txtSifra.setText(p.getSifraPredmeta());
		txtNaziv.setText(p.getNazivPredmeta());
		txtSkrNaziv.setText(p.getSkraceniNazivPredmeta());
		txtSemestar.setText(Integer.toString(p.getBrojSemestra()));
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem menuItem = new JMenuItem("New subject");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent evet) {
				prikazi();
			}
		});
		return menuItem;
	}
	public void prikazi() {
		setVisible(true);
	}
	
	private PredmetServiceBean predmetServiceBean = new PredmetServiceBean();
	private JTextField txtSifra = new JTextField(15);
	private JTextField txtNaziv = new JTextField(15);
	private JTextField txtSkrNaziv = new JTextField(15);
	private JTextField txtSemestar = new JTextField(15);
}
