package swing.nastavniciPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.KorisnikServiceBean;
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
		
		JLabel negBodovi = new JLabel("Negativni bodovi: ");
		panel.add(negBodovi);
		panel.add(txtNegBodovi);
			
		JLabel predmeti = new JLabel("Predmeti: ");
		panel.add(predmeti);
		panel.add(predmetiLookup);
		
		JLabel jmbg = new JLabel( "JMBG: ");
		panel.add(jmbg);
		panel.add(txtJmbg);
		
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
	
	public NoviNastavnik(Nastavnik n){
		this();
		txtIme.setText(n.getKorisnik().getImeKorisnika());
		txtPrezime.setText(n.getKorisnik().getPrezimeKorisnika());
		txtZvanje.setText(n.getAkademskoZvanje());
		txtNegBodovi.setText(Integer.toString(n.getKorisnik().getBrojNegativnihBodova()));
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
		String password = txtJmbg.getText();
		int sifra = Integer.parseInt(txtJmbg.getText());
		
		Korisnik k = new Korisnik(Integer.toString(sifra), ime, prezime, 0);
		
		Nastavnik n = nastavnikServiceBean
				.save(new Nastavnik(k, zvanje, password));
		
		for(Predmet p : predmeti)
		{
			nastavnikPredmetPK = new NastavnikPredmetPK(p.getSifraPredmeta(), n.getKorisnik().getSifraKorisnika());
			nastavnikPredmetServiceBean.save(new NastavnikPredmet(nastavnikPredmetPK, n, p));
		}
		
	}
	
	private NastavnikServiceBean nastavnikServiceBean = new NastavnikServiceBean();
	private PredmetServiceBean predmetServiceBean = new PredmetServiceBean();
	private NastavnikPredmetPK nastavnikPredmetPK;
	private NastavnikPredmetServiceBean nastavnikPredmetServiceBean = new NastavnikPredmetServiceBean();
	private KorisnikServiceBean korisnikServiceBean = new KorisnikServiceBean();
	
	private JTextField txtIme = new JTextField(30);
	private JTextField txtPrezime = new JTextField(30);
	private JTextField txtZvanje = new JTextField(15);
	private JTextField txtNegBodovi = new JTextField(2);
	private JTextField txtJmbg = new JTextField(13);
	private Lookup<Predmet> predmetiLookup = new Lookup<Predmet>(predmetServiceBean.getAllPredmeti());
	
}