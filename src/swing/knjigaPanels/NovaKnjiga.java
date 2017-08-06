package swing.knjigaPanels;

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

import bussines.AutorKnjigaServiceBean;
import bussines.AutorServiceBean;
import bussines.IzdavacServiceBean;
import bussines.KnjigaServiceBean;
import bussines.VrstaKnjigeServiceBean;
import jpa.Autor;
import jpa.AutorKnjiga;
import jpa.AutorKnjigaPK;
import jpa.Izdavac;
import jpa.Knjiga;
import jpa.VrstaKnjige;
import util.Lookup;

public class NovaKnjiga  extends JFrame{
	public NovaKnjiga(){
		setTitle("Nova knjiga");
		setSize(300, 300);
		
		JPanel panel = new JPanel();
		panel.setSize(300, 300);
		
		JLabel naslov = new JLabel("Naslov: ");		
		panel.add(naslov);
		panel.add(txtNaslov);
		
		JLabel orgNaslov = new JLabel("Originalni naslov: ");		
		panel.add(orgNaslov);
		panel.add(txtOrgNaslov);
		
		JLabel brojStranica = new JLabel("Broj stranica: ");
		panel.add(brojStranica);
		panel.add(txtBrojStranica);
		
		JLabel godinaIzdavanja = new JLabel("Godina izdavanja: ");
		panel.add(godinaIzdavanja);
		panel.add(txtGodinaIzdavanja);
		
		JLabel negBodovi = new JLabel("Negativni bodovi");
		panel.add(negBodovi);
		panel.add(txtNegBodovi);
		
		JLabel vrsta = new JLabel("Vrsta knjige: ");
		for(VrstaKnjige vk: vrstaKnjigeServiceBean.getAllVrstaKnjige()) {
			cbVrsta.addItem(vk);
		}
		panel.add(vrsta);
		panel.add(cbVrsta);
		
		JLabel izdavac = new JLabel("Izdavac");
		for(Izdavac i: izdavacServiceBean.getAllIzdavac()) {
			cbIzdavac.addItem(i);
		}
		panel.add(izdavac);
		panel.add(cbIzdavac);
		
		JLabel autor = new JLabel("Autori: ");
		panel.add(autor);
		panel.add(autoriLookup);
		
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				saveBook();			
			}
		});
		panel.add(potvrdi);
		
		add(panel);
				
	}
	
	public NovaKnjiga(Knjiga k){
		this();
		txtNaslov.setText(k.getNaslov());
		txtOrgNaslov.setText(k.getOriginalniNaslov());
		txtBrojStranica.setText(Integer.toString(k.getBrojStranica()));
		txtGodinaIzdavanja.setText(Integer.toString(k.getGodinaIzdavanja()));
		txtNegBodovi.setText(Integer.toString(k.getNegBodovi()));
	
		setTitle("Uredi knjigu");
		potvrdi.setText("Snimi izmjene");
		
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem item = new JMenuItem("Nova Knjiga");
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
		int brStranica = Integer.parseInt(txtBrojStranica.getText());
		int godIzdavanja = Integer.parseInt(txtGodinaIzdavanja.getText());
		int negBodovi = Integer.parseInt(txtNegBodovi.getText());
		VrstaKnjige vrsta =(VrstaKnjige) cbVrsta.getSelectedItem();
		Izdavac  izdavac = (Izdavac) cbIzdavac.getSelectedItem();
		List<Autor> autori = autoriLookup.getSelectedValues();
		
		Knjiga k = knjigaServiceBean
		.save(new Knjiga(knjigaServiceBean.getCount() + 1, naslov, orgNaslov, brStranica, godIzdavanja, negBodovi, izdavac, vrsta));
		
		for(Autor a : autori) {
			int i = 1;
			autorKnjigaPK = new AutorKnjigaPK(a.getId(), k.getId());
			autorknjigaServiceBean.save(new AutorKnjiga(autorKnjigaPK, k, a, i++));
		}
		
		
	}
	
	private VrstaKnjigeServiceBean vrstaKnjigeServiceBean = new VrstaKnjigeServiceBean();
	private IzdavacServiceBean izdavacServiceBean = new IzdavacServiceBean();
	private AutorServiceBean autorServiceBean = new AutorServiceBean();
	private KnjigaServiceBean knjigaServiceBean = new KnjigaServiceBean();
	private AutorKnjigaServiceBean autorknjigaServiceBean = new AutorKnjigaServiceBean();
	
	private JTextField txtNaslov = new JTextField(15);
	private JTextField txtOrgNaslov = new JTextField(15);
	private JTextField txtBrojStranica = new JTextField(15);
	private JTextField txtGodinaIzdavanja = new JTextField(15);
	private JTextField txtNegBodovi = new JTextField(15);
	private JComboBox<VrstaKnjige> cbVrsta = new JComboBox<VrstaKnjige>();
	private JComboBox<Izdavac> cbIzdavac = new JComboBox<Izdavac>();
	private Lookup<Autor> autoriLookup = new Lookup<Autor>(autorServiceBean.getAllAutor());
	JButton potvrdi = new JButton("Potvrdi");
	
	private AutorKnjigaPK autorKnjigaPK;

}
