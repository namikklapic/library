package swing.nastavniciPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.NastavnikPredmetServiceBean;
import bussines.NastavnikServiceBean;
import bussines.PredmetServiceBean;
import jpa.Korisnik;
import jpa.Nastavnik;
import jpa.NastavnikPredmet;
import jpa.NastavnikPredmetPK;
import jpa.Predmet;
import util.Lookup;

public class NoviNastavnik extends JFrame{
	public NoviNastavnik(){
		setTitle("Novi nastavnik");
		setSize(500, 500);
		
		JPanel panel = new JPanel();
		panel.setSize(500, 500);
		
		JLabel ime = new JLabel("Ime nastavnika: ");
		panel.add(ime);
		panel.add(txtIme);
		
		JLabel prezime = new JLabel("Prezime nastavnika: ");
		panel.add(prezime);
		panel.add(txtPrezime);
		
		JLabel zvanje = new JLabel("Akademsko zvanje: ");
		panel.add(zvanje);
		panel.add(txtZvanje); //moze i ovo biti dropdown
			
		JLabel predmeti = new JLabel("Predmeti: ");
		panel.add(predmeti);
		panel.add(predmetiLookup);
		
		JLabel password = new JLabel( "JMBG: ");
		panel.add(password);
		panel.add(txtPassword);
		
		JButton potvrdi = new JButton("Potvrdi");
		potvrdi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				saveNastavnik();
			}
		});
		panel.add(potvrdi);		
		
		add(panel);
	}
	
	public JMenuItem getMenuItem(){
		JMenuItem item = new JMenuItem("Novi Nastavnik");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				prikazi();
			}
		});
		return item;
	}
	
	public void prikazi(){
		setVisible(true);
	}
	
	private void saveNastavnik(){
		String ime = txtIme.getText();
		String prezime = txtPrezime.getText();
		String zvanje = txtZvanje.getText();
		List<Predmet> predmeti = predmetiLookup.getSelectedValues();
		String password = txtPassword.getText();
		int sifra = nastavnikServiceBean.getCount() + 1;
		
		Korisnik k = new Korisnik(Integer.toString(sifra), ime, prezime, 0);
		
		Nastavnik n = nastavnikServiceBean
				.save(new Nastavnik(k, zvanje, password));
		
		for(Predmet p : predmeti)
		{
			int i = 1;
			nastavnikPredmetPK = new NastavnikPredmetPK(p.getSifraPredmeta(), n.getKorisnik().getSifraKorisnika());
			nastavnikPredmetServiceBean.save(new NastavnikPredmet(nastavnikPredmetPK, n, p));
		}
		
	}
	
	private NastavnikServiceBean nastavnikServiceBean = new NastavnikServiceBean();
	private PredmetServiceBean predmetServiceBean = new PredmetServiceBean();
	private NastavnikPredmetPK nastavnikPredmetPK;
	private NastavnikPredmetServiceBean nastavnikPredmetServiceBean = new NastavnikPredmetServiceBean();
	
	private JTextField txtIme = new JTextField(30);
	private JTextField txtPrezime = new JTextField(30);
	private JTextField txtZvanje = new JTextField(15);
	private JTextField txtPassword = new JTextField(13);
	private Lookup<Predmet> predmetiLookup = new Lookup<Predmet>(predmetServiceBean.getAllPredmeti());
	
}