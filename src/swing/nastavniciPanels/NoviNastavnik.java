package swing.nastavniciPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
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
import swing.autorPanels.NoviAutor;
import util.Lookup;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NoviNastavnik extends JFrame{
	
	public NoviNastavnik(){
		
		getContentPane().setLayout(null);
		
		setTitle("New teacher");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 500;
		int sirinaProzora = 500;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		setSize(500, 500);
		
		panel = new JPanel();
		panel.setSize(476, 471);
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setLocation(12, 16);
		panel.setLayout(null);
		
		ime = new JLabel("First name: ");
		ime.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		ime.setBounds(32, 118, 127, 16);
		panel.add(ime);
		txtIme.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtIme.setBackground(Color.WHITE);
			}
		});
		txtIme.setBounds(171, 115, 293, 22);
		panel.add(txtIme);
		
		prezime = new JLabel("Last name: ");
		prezime.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		prezime.setBounds(32, 153, 127, 16);
		panel.add(prezime);
		txtPrezime.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtPrezime.setBackground(Color.WHITE);
			}
		});
		txtPrezime.setBounds(171, 150, 293, 22);
		panel.add(txtPrezime);
		
		zvanje = new JLabel("Academic title: ");
		zvanje.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		zvanje.setBounds(32, 190, 127, 16);
		panel.add(zvanje);
		txtZvanje.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtZvanje.setBackground(Color.WHITE);
			}
		});
		txtZvanje.setBounds(171, 187, 293, 22);
		panel.add(txtZvanje); //moze i ovo biti dropdown
		
		predmeti = new JLabel("Subjects: ");
		predmeti.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		predmeti.setBounds(32, 245, 127, 16);
		panel.add(predmeti);
		predmetiLookup.getSelected().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				predmetiLookup.getSelected().setBackground(Color.WHITE);
			}
		});
		predmetiLookup.setBounds(171, 229, 293, 49);
		panel.add(predmetiLookup);
		
		jmbg = new JLabel("Personal ID: ");
		jmbg.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		jmbg.setBounds(32, 295, 127, 16);
		panel.add(jmbg);
		txtJmbg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtJmbg.setBackground(Color.WHITE);
			}
		});
		txtJmbg.setBounds(171, 292, 293, 22);
		panel.add(txtJmbg);
		
		potvrdi = new JButton("Save");
		potvrdi.setBounds(150, 397, 87, 41);
		potvrdi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				boolean valid = isValidNastavnik();
				if(valid)
					saveNastavnik();
				displayMessageDialogBox();
				
				if(valid){
					initializeUIElements();
					dispose();
				}
			}
		});
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
		potvrdi.setBorder(null);
		potvrdi.setFocusPainted(false);
		potvrdi.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		potvrdi.setForeground(new Color(255, 255, 255));
		potvrdi.setBackground(Color.DARK_GRAY);
		panel.add(potvrdi);
		
		ponisti = new JButton("Cancel");
		ponisti.setBounds(249, 397, 87, 41);
		ponisti.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				initializeUIElements();
				dispose();
				ponisti.setBackground(Color.DARK_GRAY);

			}
		});
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
		ponisti.setBorder(null);
		ponisti.setFocusPainted(false);
		ponisti.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		ponisti.setForeground(new Color(255, 255, 255));
		ponisti.setBackground(Color.DARK_GRAY);
		panel.add(ponisti);
		
		
		getContentPane().add(panel);
		
		JLabel backgroundPicture = new JLabel("");
		backgroundPicture.setIcon(new ImageIcon(NoviAutor.class.getResource("/swing/images/background.jpg")));
		backgroundPicture.setBounds(0, 0, 500, 500);
		getContentPane().add(backgroundPicture);
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
			txtJmbg.setBackground(Color.LIGHT_GRAY);
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
			txtIme.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtPrezime.getText().equals("") || txtPrezime.getText().equals(null)){
			txtPrezime.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtZvanje.getText().equals("") || txtZvanje.getText().equals(null)){
			txtZvanje.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(predmetiLookup.getSelected().getText().equals("") || predmetiLookup.getSelected().getText().equals(null)){
			predmetiLookup.getSelected().setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtJmbg.getText().equals("") || txtJmbg.getText().equals(null)){
			txtJmbg.setBackground(Color.LIGHT_GRAY);
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
	private Lookup<Predmet, PredmetServiceBean> predmetiLookup = new Lookup<Predmet, PredmetServiceBean>(predmetServiceBean.getAllPredmeti(), predmetServiceBean);
	private JButton potvrdi;
	private JButton ponisti;
	
}