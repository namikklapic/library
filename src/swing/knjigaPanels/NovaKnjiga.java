package swing.knjigaPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
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
import swing.autorPanels.AutorPregled;
import swing.autorPanels.NoviAutor;
import swing.izdavacPanels.NoviIzdavac;
import util.Lookup;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class NovaKnjiga extends JFrame {
	public NovaKnjiga(){
		
		setTitle("New book");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 600;
		int sirinaProzora = 700;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		setSize(700, 600);
		
		panel = new JPanel();
		panel.setBounds(12, 13, 676, 574);
		panel.setBackground(new Color(255, 255, 255,150));
		
		knjiga = null;
		
		lbNaslov = new JLabel("Title: ");
		lbNaslov.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lbNaslov.setBounds(83, 80, 145, 27);
		txtNaslov = new JTextField(20);
		txtNaslov.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNaslov.setBackground(Color.WHITE);
			}
		});
		txtNaslov.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtNaslov.setBounds(260, 82, 343, 22);
		panel.setLayout(null);
		panel.add(lbNaslov);
		panel.add(txtNaslov);
		
		lbOrgNaslov = new JLabel("Original title: ");
		lbOrgNaslov.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lbOrgNaslov.setBounds(83, 116, 145, 27);
		txtOrgNaslov = new JTextField(20);
		txtOrgNaslov.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtOrgNaslov.setBackground(Color.WHITE);
			}
		});
		txtOrgNaslov.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtOrgNaslov.setBounds(260, 118, 343, 22);
		panel.add(lbOrgNaslov);
		panel.add(txtOrgNaslov);
		
		lbBrStranica = new JLabel("Number of pages: ");
		lbBrStranica.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lbBrStranica.setBounds(83, 151, 145, 27);
		txtBrStranica = new JTextField(3);
		txtBrStranica.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBrStranica.setBackground(Color.WHITE);
			}
		});
		txtBrStranica.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtBrStranica.setBounds(260, 153, 343, 22);
		panel.add(lbBrStranica);
		panel.add(txtBrStranica);
		
		lbGodIzdavanja = new JLabel("Publishing year: ");
		lbGodIzdavanja.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lbGodIzdavanja.setBounds(83, 186, 145, 27);
		txtGodIzdavanja = new JTextField(4);
		txtGodIzdavanja.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtGodIzdavanja.setBackground(Color.WHITE);
			}
		});
		txtGodIzdavanja.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtGodIzdavanja.setBounds(260, 188, 343, 22);
		panel.add(lbGodIzdavanja);
		panel.add(txtGodIzdavanja);
		
		lbNegBodovi = new JLabel("Negative points: ");
		lbNegBodovi.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lbNegBodovi.setBounds(83, 226, 145, 27);
		txtNegBodovi = new JTextField(3);
		txtNegBodovi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNegBodovi.setBackground(Color.WHITE);
			}
		});
		txtNegBodovi.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtNegBodovi.setBounds(260, 228, 343, 22);
		panel.add(lbNegBodovi);
		panel.add(txtNegBodovi);
		
		lbVrstaKnjige = new JLabel("Book type: ");
		lbVrstaKnjige.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lbVrstaKnjige.setBounds(83, 266, 145, 27);
		cbVrstaKnjige = new JComboBox<VrstaKnjige>();
		cbVrstaKnjige.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cbVrstaKnjige.setBackground(Color.WHITE);
			}
		});
		cbVrstaKnjige.setBounds(260, 268, 278, 22);
		for(VrstaKnjige vk : vrstaKnjigeServiceBean.getAllVrstaKnjige())
			cbVrstaKnjige.addItem(vk);
		cbVrstaKnjige.setSelectedItem(null);
		btnAddNovaVrsta = new JButton("+");
		btnAddNovaVrsta.setBounds(562, 267, 41, 25);
		btnAddNovaVrsta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				NovaVrstaKnjige nvk = new NovaVrstaKnjige();
				nvk.prikazi();
			}
		});
		btnAddNovaVrsta.setBorder(null);
		btnAddNovaVrsta.setFocusPainted(false);
		btnAddNovaVrsta.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		btnAddNovaVrsta.setForeground(new Color(255, 255, 255));
		btnAddNovaVrsta.setBackground(Color.DARK_GRAY);
		btnAddNovaVrsta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnAddNovaVrsta.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAddNovaVrsta.setBackground(Color.DARK_GRAY);
			}
		});
		panel.add(lbVrstaKnjige);
		panel.add(cbVrstaKnjige);
		panel.add(btnAddNovaVrsta);
		
		lbIzdavac = new JLabel("Publisher: ");
		lbIzdavac.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lbIzdavac.setBounds(83, 306, 145, 27);
		cbIzdavac = new JComboBox<Izdavac>();
		cbIzdavac.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cbIzdavac.setBackground(Color.WHITE);
			}
		});
		cbIzdavac.setBounds(260, 308, 278, 22);
		for(Izdavac i : izdavacServiceBean.getAllIzdavac())
			cbIzdavac.addItem(i);
		cbIzdavac.setSelectedItem(null);
		btnAddNoviIzdavac = new JButton("+");
		btnAddNoviIzdavac.setBounds(562, 308, 41, 25);
		btnAddNoviIzdavac.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				NoviIzdavac ni = new NoviIzdavac();
				ni.prikazi();
			}
		});
		btnAddNoviIzdavac.setBorder(null);
		btnAddNoviIzdavac.setFocusPainted(false);
		btnAddNoviIzdavac.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		btnAddNoviIzdavac.setForeground(new Color(255, 255, 255));
		btnAddNoviIzdavac.setBackground(Color.DARK_GRAY);
		btnAddNoviIzdavac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnAddNoviIzdavac.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAddNoviIzdavac.setBackground(Color.DARK_GRAY);
			}
		});
		panel.add(lbIzdavac);
		panel.add(cbIzdavac);
		panel.add(btnAddNoviIzdavac);
		
		lbAutori = new JLabel("Authors: ");
		lbAutori.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lbAutori.setBounds(83, 357, 145, 27);
		lookupAutori = new Lookup<Autor>(autorServiceBean.getAllAutor());
		lookupAutori.getSelected().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				lookupAutori.getSelected().setBackground(Color.WHITE);
			}
		});
		
		
		lookupAutori.setBounds(260, 346, 280, 48);
		btnAddNoviAutor = new JButton("+");
		btnAddNoviAutor.setBounds(562, 361, 41, 25);
		btnAddNoviAutor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				NoviAutor na = new NoviAutor();
				na.prikazi();
			}
		});
		btnAddNoviAutor.setBorder(null);
		btnAddNoviAutor.setFocusPainted(false);
		btnAddNoviAutor.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		btnAddNoviAutor.setForeground(new Color(255, 255, 255));
		btnAddNoviAutor.setBackground(Color.DARK_GRAY);
		btnAddNoviAutor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnAddNoviAutor.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAddNoviAutor.setBackground(Color.DARK_GRAY);
			}
		});
		panel.add(lbAutori);
		panel.add(lookupAutori);
		panel.add(btnAddNoviAutor);
		
		lbBrPrimjeraka = new JLabel("Number of copies: ");
		lbBrPrimjeraka.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lbBrPrimjeraka.setBounds(80, 407, 145, 27);
		txtBrPrimjeraka = new JTextField(3);
		txtBrPrimjeraka.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBrPrimjeraka.setBackground(Color.WHITE);
			}
		});
		txtBrPrimjeraka.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtBrPrimjeraka.setBounds(260, 409, 343, 22);
		panel.add(lbBrPrimjeraka);
		panel.add(txtBrPrimjeraka);
		
		btnPotvrdi = new JButton("Save");
		btnPotvrdi.setBounds(234, 499, 82, 36);
		btnPotvrdi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(isValidBook() == true)
					saveBook();
				displayMessageDialogBox();
			}
		});
		btnPotvrdi.setBorder(null);
		btnPotvrdi.setFocusPainted(false);
		btnPotvrdi.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		btnPotvrdi.setForeground(new Color(255, 255, 255));
		btnPotvrdi.setBackground(Color.DARK_GRAY);
		btnPotvrdi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnPotvrdi.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPotvrdi.setBackground(Color.DARK_GRAY);
			}
		});
		panel.add(btnPotvrdi);
		
		btnOdustani = new JButton("Cancel");
		btnOdustani.setBounds(339, 499, 82, 36);
		btnOdustani.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				initializeUIElements();
				dispose();
				btnOdustani.setBackground(Color.DARK_GRAY);

			}
		});
		btnOdustani.setBorder(null);
		btnOdustani.setFocusPainted(false);
		btnOdustani.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		btnOdustani.setForeground(new Color(255, 255, 255));
		btnOdustani.setBackground(Color.DARK_GRAY);
		btnOdustani.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnOdustani.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				btnOdustani.setBackground(Color.DARK_GRAY);
			}
		});
		panel.add(btnOdustani);
		
		getContentPane().setLayout(null);
		
		
		getContentPane().add(panel);	
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AutorPregled.class.getResource("/swing/images/background.jpg")));
		lblNewLabel.setBounds(0, 0, 800, 800);
		getContentPane().add(lblNewLabel);
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
			txtNaslov.setBackground(Color.LIGHT_GRAY);
			success = true;
		}		
		if(txtOrgNaslov.getText().equals(null) || txtOrgNaslov.getText().equals("")){
			txtOrgNaslov.setBackground(Color.LIGHT_GRAY);
			success = true;
		}	
		if(txtBrStranica.getText().equals(null) || txtBrStranica.getText().equals("")){
			txtBrStranica.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtGodIzdavanja.getText().equals(null) || txtGodIzdavanja.getText().equals("")){
			txtGodIzdavanja.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtNegBodovi.getText().equals(null) || txtNegBodovi.getText().equals("")){
			txtNegBodovi.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(cbVrstaKnjige.getSelectedItem() == null){
			cbVrstaKnjige.setBackground(Color.LIGHT_GRAY);	
			success = true;
		}
		if(cbIzdavac.getSelectedItem() == null){
			cbIzdavac.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(lookupAutori.getSelected().getText().equals(null) || lookupAutori.getSelected().getText().equals("")){
			lookupAutori.getSelected().setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtBrPrimjeraka.getText().equals(null) || txtBrPrimjeraka.getText().equals("")){
			txtBrPrimjeraka.setBackground(Color.LIGHT_GRAY);
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
			txtBrStranica.setBackground(Color.LIGHT_GRAY);
			return false;
		}
		else if(!isNumeric(txtGodIzdavanja.getText()) || txtGodIzdavanja.getText().length() != 4){
			message = "Invalid year!";
			txtGodIzdavanja.setBackground(Color.LIGHT_GRAY);
			return false;
		}
		else if(!isNumeric(txtNegBodovi.getText())){
			message = "Invalid number of negative points!";
			txtNegBodovi.setBackground(Color.LIGHT_GRAY);
			return false;
		}
		else if(!isNumeric(txtBrPrimjeraka.getText())){
			message = "Invalid number of copies!";
			txtBrPrimjeraka.setBackground(Color.LIGHT_GRAY);
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
	private JButton delete;
}
