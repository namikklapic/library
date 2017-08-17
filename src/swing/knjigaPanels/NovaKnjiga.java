package swing.knjigaPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.AutorKnjigaServiceBean;
import bussines.AutorServiceBean;
import bussines.IzdavacServiceBean;
import bussines.KnjigaServiceBean;
import bussines.PrimjerakServiceBean;
import bussines.VrstaKnjigeServiceBean;
import jpa.Autor;
import jpa.AutorKnjiga;
import jpa.AutorKnjigaPK;
import jpa.Izdavac;
import jpa.Knjiga;
import jpa.Primjerak;
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
				saveBook();
			}
		});
		panel.add(btnPotvrdi);
		
		
		add(panel);	
	}
	
	public NovaKnjiga(Knjiga k, Boolean isEditable){
		
		this();

		knjiga = k;
		txtNaslov.setText(k.getNaslov());
		txtOrgNaslov.setText(k.getOriginalniNaslov());
		txtBrStranica.setText(Integer.toString(k.getBrojStranica()));
		txtGodIzdavanja.setText(Integer.toString(k.getGodinaIzdavanja()));
		txtNegBodovi.setText(Integer.toString(k.getNegBodovi()));
		txtBrPrimjeraka.setText(Integer.toString(k.getBrojPrimjeraka()));
		
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
					
				}
			});
			
			panel.add(btnRezervisi);
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
		
		Primjerak temp;
		String invBroj;
		for(int i = 1; i <= brPrimjeraka; i++){
			temp = new Primjerak();
			invBroj = Integer.toString(id) + "-" + Integer.toString(i);
			temp.setInventarskiBroj(invBroj);
			temp.setKnjiga(k);
			temp.setRezervisan(false);
			temp.setPosudjen(false);
			primjerakServiceBean.save(temp);
		}
	}
	
	private JPanel panel;
	
	private Knjiga knjiga;
	
	private AutorServiceBean autorServiceBean = new AutorServiceBean();
	private VrstaKnjigeServiceBean vrstaKnjigeServiceBean = new VrstaKnjigeServiceBean();
	private IzdavacServiceBean izdavacServiceBean = new IzdavacServiceBean();
	private KnjigaServiceBean knjigaServiceBean = new KnjigaServiceBean();
	private AutorKnjigaServiceBean autorKnjigaServiceBean = new AutorKnjigaServiceBean();
	private PrimjerakServiceBean primjerakServiceBean = new PrimjerakServiceBean();
	
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
	private JButton btnRezervisi;
}
