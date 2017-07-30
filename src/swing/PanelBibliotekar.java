package swing;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import jpa.Bibliotekar;
import swing.VrstaKnjigePanels.NovaVrstaKnjige;
import swing.VrstaKnjigePanels.VrstaKnjigePregled;
import swing.autorPanels.AutorPregled;
import swing.autorPanels.NoviAutor;
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
		vrstaKnjige.add(vrstaKnjigePregled.getMenuItem(panel));
		knjiga.add(vrstaKnjige);
		
		
		JMenu izdavac = new JMenu("Izdavac");
		izdavac.add(pregledIzdavac.getMenuItem(panel));
		izdavac.add(noviIzdavac.getMenuItem());
		
		JMenu autori = new JMenu("Autor");
		autori.add(noviAutor.getMenuItem());
		autori.add(autorPregled.getMenuItem(panel));
		
		JMenu nastavnici = new JMenu("Nastavnici");
		//nastavnici.add(noviNastavnik.getMenuItem());
		//nastavnici.add(nastavnikPregled.getMenuItem(panel));
	
		
		menuBar.add(knjiga);
		menuBar.add(izdavac);
		menuBar.add(autori);
		menuBar.add(nastavnici);
		setJMenuBar(menuBar);
		
		add(panel);
		
	}
	private Bibliotekar bibliotekar;

	private KnjigaPregled pregledKnjiga = new KnjigaPregled();
	private NovaKnjiga novaKnjiga = new NovaKnjiga();
	
	private NoviIzdavac noviIzdavac = new NoviIzdavac();
	private IzdavacPregled pregledIzdavac = new IzdavacPregled();
	
	private NovaVrstaKnjige novaVrstaKnjige = new NovaVrstaKnjige();
	private VrstaKnjigePregled vrstaKnjigePregled = new VrstaKnjigePregled();
	
	private NoviAutor noviAutor = new NoviAutor();
	private AutorPregled autorPregled = new AutorPregled();
	
}
