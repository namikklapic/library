package swing;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import jpa.Bibliotekar;
import swing.VrstaKnjigePanels.NovaVrstaKnjige;
import swing.izdavacPanels.IzdavacPregled;
import swing.izdavacPanels.NoviIzdavac;
import swing.knjigaPanels.KnjigaPregled;
import swing.knjigaPanels.NovaKnjiga;

public class PanelBibliotekar extends JFrame{

	public PanelBibliotekar (Bibliotekar b){
		this.bibliotekar = b;
		setTitle(b.getKorisnik().getImeKorisnika() + " " + b.getKorisnik().getPrezimeKorisnika());
		
		JPanel panel = new JPanel();
		
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu knjiga = new JMenu("Knjiga");
		knjiga.add(pregledKnjiga.getMenuItem(panel));
		knjiga.add(novaKnjiga.getMenuItem());
		
		JMenu vrstaKnjige = new JMenu("Vrsta");
		vrstaKnjige.add(novaVrstaKnjige.getMenuItem());
		knjiga.add(vrstaKnjige);
		
		
		JMenu izdavac = new JMenu("Izdavac");
		izdavac.add(pregledIzdavac.getMenuItem(panel));
		izdavac.add(noviIzdavac.getMenuItem());
		
		
		
		
		
		
	
		
		menuBar.add(knjiga);
		menuBar.add(izdavac);
		setJMenuBar(menuBar);
		
		
		add(panel);
		
	}
	private Bibliotekar bibliotekar;

	private KnjigaPregled pregledKnjiga = new KnjigaPregled();
	private NovaKnjiga novaKnjiga = new NovaKnjiga();
	
	private NoviIzdavac noviIzdavac = new NoviIzdavac();
	private IzdavacPregled pregledIzdavac = new IzdavacPregled();
	
	private NovaVrstaKnjige novaVrstaKnjige = new NovaVrstaKnjige();
}
