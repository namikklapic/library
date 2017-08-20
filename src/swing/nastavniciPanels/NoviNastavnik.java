package swing.nastavniciPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
		setTitle("New teacher");
		setSize(500, 500);
		
		panel = new JPanel();
		panel.setSize(500, 500);
		
		ime = new JLabel("First name: ");
		panel.add(ime);
		panel.add(txtIme);
		
		prezime = new JLabel("Last name: ");
		panel.add(prezime);
		panel.add(txtPrezime);
		
		zvanje = new JLabel("Academic title: ");
		panel.add(zvanje);
		panel.add(txtZvanje); //moze i ovo biti dropdown
		
		predmeti = new JLabel("Subjects: ");
		panel.add(predmeti);
		panel.add(predmetiLookup);
		
		jmbg = new JLabel("Personal ID: ");
		panel.add(jmbg);
		panel.add(txtJmbg);
		
		potvrdi = new JButton("Save");
		potvrdi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				if(isValidNastavnik())
					saveNastavnik();
				displayMessageDialogBox();
			}
		});
		panel.add(potvrdi);
		
		ponisti = new JButton("Cancel");
		ponisti.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				initializeUIElements();
				dispose();
			}
		});
		panel.add(ponisti);
		
		panel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent event){
				setUIElementsColor(Color.WHITE);
			}
		});
		
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
		JMenuItem item = new JMenuItem("New teacher");
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
		String sifra = txtJmbg.getText();
		
		Korisnik k = new Korisnik(sifra, ime, prezime, 0);
		
		Nastavnik n = nastavnikServiceBean
				.save(new Nastavnik(k, zvanje, password));
		
		for(Predmet p : predmeti)
		{
			nastavnikPredmetPK = new NastavnikPredmetPK(p.getSifraPredmeta(), n.getKorisnik().getSifraKorisnika());
			nastavnikPredmetServiceBean.save(new NastavnikPredmet(nastavnikPredmetPK, n, p));
		}
		
		message = "The teacher has been successfully saved!";
	}
	
	private boolean isValidNastavnik(){
		if(isNastavnikDataEmpty()){
			message = "All data must be entered!";
			return false;
		}
		else if(txtJmbg.getText().contains("[a-zA-Z]+") || txtJmbg.getText().length() != 13){
			txtJmbg.setBackground(Color.RED);
			message = "Personal ID must contain 13 digits!";
			return false;
		}
		else if(nastavnikServiceBean.existsNastavnik(txtJmbg.getText())){
			message = "Teacher with entered personal ID alreday exists!";
			return false;
		}
		return true;
	}
	
	private boolean isNastavnikDataEmpty(){
		boolean success = false;
		
		if(txtIme.getText().equals("") || txtIme.getText().equals(null)){
			txtIme.setBackground(Color.RED);
			success = true;
		}
		if(txtPrezime.getText().equals("") || txtPrezime.getText().equals(null)){
			txtPrezime.setBackground(Color.RED);
			success = true;
		}
		if(txtZvanje.getText().equals("") || txtZvanje.getText().equals(null)){
			txtZvanje.setBackground(Color.RED);
			success = true;
		}
		if(predmetiLookup.getSelected().getText().equals("") || predmetiLookup.getSelected().getText().equals(null)){
			predmetiLookup.getSelected().setBackground(Color.RED);
			success = true;
		}
		if(txtJmbg.getText().equals("") || txtJmbg.getText().equals(null)){
			txtJmbg.setBackground(Color.RED);
			success = true;
		}
		return success;
	}
	
	private void initializeUIElements(){
		txtIme.setText("");
		txtPrezime.setText("");
		txtZvanje.setText("");
		predmetiLookup.getSelected().setText("");
		txtJmbg.setText("");
		
		setUIElementsColor(Color.WHITE);
	}
	
	private void setUIElementsColor(Color c){
		txtIme.setBackground(c);
		txtPrezime.setBackground(c);
		txtZvanje.setBackground(c);
		predmetiLookup.getSelected().setBackground(c);
		txtJmbg.setBackground(c);
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private JPanel panel;
	private String message;
	
	private NastavnikServiceBean nastavnikServiceBean = new NastavnikServiceBean();
	private PredmetServiceBean predmetServiceBean = new PredmetServiceBean();
	private NastavnikPredmetPK nastavnikPredmetPK;
	private NastavnikPredmetServiceBean nastavnikPredmetServiceBean = new NastavnikPredmetServiceBean();
	private KorisnikServiceBean korisnikServiceBean = new KorisnikServiceBean();
	
	private JLabel ime;
	private JLabel prezime;
	private JLabel zvanje;
	private JLabel predmeti;
	private JLabel jmbg;
	private JTextField txtIme = new JTextField(30);
	private JTextField txtPrezime = new JTextField(30);
	private JTextField txtZvanje = new JTextField(15);
	private JTextField txtNegBodovi = new JTextField(2);
	private JTextField txtJmbg = new JTextField(13);
	private Lookup<Predmet> predmetiLookup = new Lookup<Predmet>(predmetServiceBean.getAllPredmeti());
	private JButton potvrdi;
	private JButton ponisti;
	
}