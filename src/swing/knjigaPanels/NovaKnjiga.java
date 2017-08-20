package swing.knjigaPanels;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.AutorKnjigaServiceBean;
import bussines.AutorServiceBean;
import bussines.IzdavacServiceBean;
import bussines.KnjigaServiceBean;
import bussines.PrimjerakServiceBean;
import bussines.RezervacijaServiceBean;
import bussines.VrstaKnjigeServiceBean;
import jpa.Autor;
import jpa.AutorKnjiga;
import jpa.AutorKnjigaPK;
import jpa.Izdavac;
import jpa.Knjiga;
import jpa.Korisnik;
import jpa.Primjerak;
import jpa.Rezervacija;
import jpa.RezervacijaPK;
import jpa.VrstaKnjige;
import swing.VrstaKnjigePanels.NovaVrstaKnjige;
import swing.autorPanels.NoviAutor;
import swing.izdavacPanels.NoviIzdavac;
import util.Lookup;

public class NovaKnjiga extends JFrame {
	public NovaKnjiga(){
		
		setTitle("New book");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
		
		knjiga = null;
		
		lbNaslov = new JLabel("Title: ");
		txtNaslov = new JTextField(20);
		panel.add(lbNaslov);
		panel.add(txtNaslov);
		
		lbOrgNaslov = new JLabel("Original title: ");
		txtOrgNaslov = new JTextField(20);
		panel.add(lbOrgNaslov);
		panel.add(txtOrgNaslov);
		
		lbBrStranica = new JLabel("Number of pages: ");
		txtBrStranica = new JTextField(3);
		panel.add(lbBrStranica);
		panel.add(txtBrStranica);
		
		lbGodIzdavanja = new JLabel("Publishing year: ");
		txtGodIzdavanja = new JTextField(4);
		panel.add(lbGodIzdavanja);
		panel.add(txtGodIzdavanja);
		
		lbNegBodovi = new JLabel("Negative points: ");
		txtNegBodovi = new JTextField(3);
		panel.add(lbNegBodovi);
		panel.add(txtNegBodovi);
		
		lbVrstaKnjige = new JLabel("Book type: ");
		cbVrstaKnjige = new JComboBox<VrstaKnjige>();
		for(VrstaKnjige vk : vrstaKnjigeServiceBean.getAllVrstaKnjige())
			cbVrstaKnjige.addItem(vk);
		cbVrstaKnjige.setSelectedItem(null);
		btnAddNovaVrsta = new JButton("+");
		btnAddNovaVrsta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				NovaVrstaKnjige nvk = new NovaVrstaKnjige();
				nvk.prikazi();
			}
		});
		panel.add(lbVrstaKnjige);
		panel.add(cbVrstaKnjige);
		panel.add(btnAddNovaVrsta);
		
		lbIzdavac = new JLabel("Publisher: ");
		cbIzdavac = new JComboBox<Izdavac>();
		for(Izdavac i : izdavacServiceBean.getAllIzdavac())
			cbIzdavac.addItem(i);
		cbIzdavac.setSelectedItem(null);
		btnAddNoviIzdavac = new JButton("+");
		btnAddNoviIzdavac.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				NoviIzdavac ni = new NoviIzdavac();
				ni.prikazi();
			}
		});
		panel.add(lbIzdavac);
		panel.add(cbIzdavac);
		panel.add(btnAddNoviIzdavac);
		
		lbAutori = new JLabel("Authors: ");
		lookupAutori = new Lookup<Autor>(autorServiceBean.getAllAutor());
		btnAddNoviAutor = new JButton("+");
		btnAddNoviAutor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				NoviAutor na = new NoviAutor();
				na.prikazi();
			}
		});
		panel.add(lbAutori);
		panel.add(lookupAutori);
		panel.add(btnAddNoviAutor);
		
		lbBrPrimjeraka = new JLabel("Number of copies: ");
		txtBrPrimjeraka = new JTextField(3);
		panel.add(lbBrPrimjeraka);
		panel.add(txtBrPrimjeraka);
		
		btnPotvrdi = new JButton("Save");
		btnPotvrdi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(isValidBook() == true)
					saveBook();
				displayMessageDialogBox();
			}
		});
		panel.add(btnPotvrdi);
		
		btnOdustani = new JButton("Cancel");
		btnOdustani.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				initializeUIElements();
				dispose();
			}
		});
		panel.add(btnOdustani);
		
	panel.addMouseListener(new MouseAdapter(){
		@Override
		public void mousePressed(MouseEvent event){
			setUIElementsColor(Color.WHITE);
		}
	});
		
		
		add(panel);	
	}
	
	public NovaKnjiga(Knjiga k, Boolean isEditable, Korisnik currUser){
		
		this();

		knjiga = k;
		txtNaslov.setText(k.getNaslov());
		txtOrgNaslov.setText(k.getOriginalniNaslov());
		txtBrStranica.setText(Integer.toString(k.getBrojStranica()));
		txtGodIzdavanja.setText(Integer.toString(k.getGodinaIzdavanja()));
		txtNegBodovi.setText(Integer.toString(k.getNegBodovi()));
		txtBrPrimjeraka.setText(Integer.toString(k.getBrojPrimjeraka()));
		cbVrstaKnjige.setSelectedIndex(vrstaKnjigeServiceBean.getIndexOfVrstaKnjige(k.getVrsta()));
		cbIzdavac.setSelectedIndex(izdavacServiceBean.getIndexOfIzdavac(k.getIzdavac()));
		
		if(isEditable){
			setTitle("Edit book");
			btnPotvrdi.setText("Save changes");
		}
		else{
			setTitle("Book review");
			txtNaslov.setEditable(false);
			txtOrgNaslov.setEditable(false);
			txtBrStranica.setEditable(false);
			txtGodIzdavanja.setEditable(false);
			txtNegBodovi.setEditable(false);
			txtBrPrimjeraka.setEditable(false);
			cbVrstaKnjige.setEnabled(false);
			cbIzdavac.setEnabled(false);
			btnPotvrdi.setVisible(false);
			lookupAutori.removeButtons();
			
			btnRezervisi = new JButton("Reserve");
			btnRezervisi.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					onReserveBookClicked(currUser);
				}
			});
			
			panel.add(btnRezervisi);
		}
	}
	
	private void onReserveBookClicked(Korisnik currUser) {
		List<Primjerak> listaDostupnih = primjerakServiceBean.getAllAvailablePrimjerakByBook(knjiga);
		if (listaDostupnih.size() == 0) {
			JOptionPane.showMessageDialog(btnRezervisi.getParent(), "There are no available copies of that book.", "Oops!", JOptionPane.WARNING_MESSAGE);
			return;
		}
		List<Rezervacija> listaRezervacija = rezervacijaServiceBean.getRezervacijeByKorisnik(currUser);
		if(listaRezervacija.size() >= 3) {
			JOptionPane.showMessageDialog(btnRezervisi.getParent(), "You can have only 3 books reserved at the same time.", "Oops!", JOptionPane.WARNING_MESSAGE);
			return;
		}
		for (int i=0; i<listaRezervacija.size(); i++) {
			if(listaRezervacija.get(i).getPrimjerak().getKnjiga().getId() == knjiga.getId()) {
				JOptionPane.showMessageDialog(btnRezervisi.getParent(), "You can reserve only 1 copy of the same book at the same time.", "Oops!", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		
			
		Boolean isValidDate = false;
		while(!isValidDate) {
			String mess = "Please enter the motherfucking date in \"dd.mm.yyyy.\" format:\n";
			String date = (String)JOptionPane.showInputDialog(btnRezervisi.getParent(), mess, "Make a reservation", JOptionPane.INFORMATION_MESSAGE );
			if(date != null) {
				String[] koefs = date.split("\\.");
				/*(System.out.println(koefs[0]);
				System.out.println(koefs[1]);
				System.out.println(koefs[2]);*/
				if(koefs.length == 3){
					int d = Integer.parseInt(koefs[0]);
					int m = Integer.parseInt(koefs[1]) - 1;
					int y = Integer.parseInt(koefs[2]) - 1900;
					Date datum = new Date(y,m,d);
					Date today = new Date();
					isValidDate = datum.getYear() == today.getYear() && datum.getMonth() == today.getMonth() && datum.getDate()-today.getDate() >= 0 && datum.getDate()-today.getDate() <= 5;
					if(!isValidDate)
						JOptionPane.showMessageDialog(btnRezervisi.getParent(), "Date must not be more than 5 days from today!", "Invalid date!", JOptionPane.ERROR_MESSAGE);
					else {
						RezervacijaPK id = new RezervacijaPK(listaDostupnih.get(0).getInventarskiBroj(), currUser.getSifraKorisnika());
						Rezervacija entity = new Rezervacija(id, listaDostupnih.get(0), currUser, datum);
						rezervacijaServiceBean.save(entity);
						Primjerak p = listaDostupnih.get(0);
						p.setRezervisan(true);
						primjerakServiceBean.save(p);
					}
				}
				else
					JOptionPane.showMessageDialog(btnRezervisi.getParent(), "Date must be in \"dd.mm.yyyy.\" format", "Invalid date!", JOptionPane.ERROR_MESSAGE);
			}
			else
				isValidDate = true;
		}
	}
	
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem item = new JMenuItem("New book");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				prikazi();
			}
		});
		return item;
	}
	
	public void prikazi(){
		setVisible(true);
	}
	
	private void initializeUIElements(){
		txtNaslov.setText("");
		txtOrgNaslov.setText("");
		txtBrStranica.setText("");
		txtGodIzdavanja.setText("");
		txtNegBodovi.setText("");
		cbVrstaKnjige.setSelectedItem(null);
		cbIzdavac.setSelectedItem(null);
		lookupAutori.initializeLookup();
		txtBrPrimjeraka.setText("");
		
		setUIElementsColor(Color.WHITE);
	}
	
	private void setUIElementsColor(Color c){
		txtNaslov.setBackground(c);
		txtOrgNaslov.setBackground(c);
		txtBrStranica.setBackground(c);
		txtGodIzdavanja.setBackground(c);
		txtNegBodovi.setBackground(c);
		cbVrstaKnjige.setBackground(c);
		cbIzdavac.setBackground(c);
		lookupAutori.getSelected().setBackground(c);
		txtBrPrimjeraka.setBackground(c);
	}
	
	private boolean isBookDataEmpty(){
		boolean success = false;
		
		if(txtNaslov.getText().equals(null) || txtNaslov.getText().equals("")){
			txtNaslov.setBackground(Color.RED);
			success = true;
		}		
		if(txtOrgNaslov.getText().equals(null) || txtOrgNaslov.getText().equals("")){
			txtOrgNaslov.setBackground(Color.RED);
			success = true;
		}	
		if(txtBrStranica.getText().equals(null) || txtBrStranica.getText().equals("")){
			txtBrStranica.setBackground(Color.RED);
			success = true;
		}
		if(txtGodIzdavanja.getText().equals(null) || txtGodIzdavanja.getText().equals("")){
			txtGodIzdavanja.setBackground(Color.RED);
			success = true;
		}
		if(txtNegBodovi.getText().equals(null) || txtNegBodovi.getText().equals("")){
			txtNegBodovi.setBackground(Color.RED);
			success = true;
		}
		if(cbVrstaKnjige.getSelectedItem() == null){
			cbVrstaKnjige.setBackground(Color.RED);	
			success = true;
		}
		if(cbIzdavac.getSelectedItem() == null){
			cbIzdavac.setBackground(Color.RED);
			success = true;
		}
		if(lookupAutori.getSelected().getText().equals(null) || lookupAutori.getSelected().getText().equals("")){
			lookupAutori.getSelected().setBackground(Color.RED);
			success = true;
		}
		if(txtBrPrimjeraka.getText().equals(null) || txtBrPrimjeraka.getText().equals("")){
			txtBrPrimjeraka.setBackground(Color.RED);
			success = true;
		}
		return success;
	}
	
	private boolean isNumeric(String s){
		
		int num;
		try{
			num = Integer.parseInt(s);
		}catch(NumberFormatException e){
			return false;
		}catch(NullPointerException e){
			return false;
		}
		if(num < 1)
			return false;
		
		return true;
	}
	
	private boolean isValidBook(){
		
		if(isBookDataEmpty()){
			message = "All data must be entered!";
			return false;
		}
		else if(!isNumeric(txtBrStranica.getText())){
			message = "Invalid number of pages!";
			txtBrStranica.setBackground(Color.RED);
			return false;
		}
		else if(!isNumeric(txtGodIzdavanja.getText()) || txtGodIzdavanja.getText().length() != 4){
			message = "Invalid year!";
			txtGodIzdavanja.setBackground(Color.RED);
			return false;
		}
		else if(!isNumeric(txtNegBodovi.getText())){
			message = "Invalid number of negative points!";
			txtNegBodovi.setBackground(Color.RED);
			return false;
		}
		else if(!isNumeric(txtBrPrimjeraka.getText())){
			message = "Invalid number of copies!";
			txtBrPrimjeraka.setBackground(Color.RED);
			return false;
		}
		return true;
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private void saveBook() {
		String naslov = txtNaslov.getText();
		String orgNaslov = txtOrgNaslov.getText();
		int brStranica = Integer.parseInt(txtBrStranica.getText());
		int godIzdavanja = Integer.parseInt(txtGodIzdavanja.getText());
		int negBodovi = Integer.parseInt(txtNegBodovi.getText());
		int brPrimjeraka = Integer.parseInt(txtBrPrimjeraka.getText());
		VrstaKnjige vrsta = (VrstaKnjige) cbVrstaKnjige.getSelectedItem();
		Izdavac izdavac = (Izdavac) cbIzdavac.getSelectedItem();
		List<Autor> autori = lookupAutori.getSelectedValues();
		
		Integer id = knjiga != null ? knjiga.getId() :  knjigaServiceBean.getCount() + 1;
		
		Knjiga k = knjigaServiceBean
				.save(new Knjiga(id, naslov, orgNaslov, brStranica, godIzdavanja, negBodovi, brPrimjeraka, izdavac, vrsta));
		
		AutorKnjigaPK autorKnjigaPK;
		for(Autor a : autori){
			int i = 1;
			autorKnjigaPK = new AutorKnjigaPK(a.getId(), k.getId());
			autorKnjigaServiceBean.save(new AutorKnjiga(autorKnjigaPK, k, a, i++));
		}
		
		//save primjerci - each primjerak will get generated invBroj
		Primjerak temp;
		String invBroj;
		for(int i = 1; i <= brPrimjeraka; i++){
			temp = new Primjerak();
			invBroj = Integer.toString(id) + "-" + Integer.toString(i);
			temp.setInventarskiBroj(invBroj);
			temp.setDatumnabavke(new Date());
			temp.setStanje("New");
			temp.setKnjiga(k);
			temp.setRezervisan(false);
			temp.setPosudjen(false);
			primjerakServiceBean.save(temp);
		}
		message = "The book has been successfully saved!";
	}
	
	private JPanel panel;
	
	private Knjiga knjiga;
	
	private String message;
	
	private AutorServiceBean autorServiceBean = new AutorServiceBean();
	private VrstaKnjigeServiceBean vrstaKnjigeServiceBean = new VrstaKnjigeServiceBean();
	private IzdavacServiceBean izdavacServiceBean = new IzdavacServiceBean();
	private KnjigaServiceBean knjigaServiceBean = new KnjigaServiceBean();
	private AutorKnjigaServiceBean autorKnjigaServiceBean = new AutorKnjigaServiceBean();
	private PrimjerakServiceBean primjerakServiceBean = new PrimjerakServiceBean();
	private RezervacijaServiceBean rezervacijaServiceBean = new RezervacijaServiceBean();
	
	private JLabel lbNaslov;
	private JTextField txtNaslov;
	private JLabel lbOrgNaslov;
	private JTextField txtOrgNaslov;
	private JLabel lbBrStranica;
	private JTextField txtBrStranica;
	private JLabel lbGodIzdavanja;
	private JTextField txtGodIzdavanja;
	private JLabel lbNegBodovi;
	private JTextField txtNegBodovi;
	private JLabel lbVrstaKnjige;
	private JComboBox<VrstaKnjige> cbVrstaKnjige;
	private JButton btnAddNovaVrsta;
	private JLabel lbIzdavac;
	private JComboBox<Izdavac> cbIzdavac;
	private JButton btnAddNoviIzdavac;
	private JLabel lbAutori;
	private Lookup<Autor> lookupAutori;
	private JButton btnAddNoviAutor;
	private JLabel lbBrPrimjeraka;
	private JTextField txtBrPrimjeraka;
	private JButton btnPotvrdi;
	private JButton btnOdustani;
	private JButton btnRezervisi;
}
