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
import swing.nastavniciPanels.NastavniciPregled;
import swing.nastavniciPanels.NoviNastavnik;
import swing.predmetPanels.NoviPredmet;
import swing.predmetPanels.PredmetPregled;
import swing.studentiPanels.NoviStudent;
import swing.studentiPanels.StudentPregled;

public class PanelBibliotekar extends JFrame{

	public PanelBibliotekar (Bibliotekar b){
		this.bibliotekar = b;
		setTitle(b.getKorisnik().getImeKorisnika() + " " + b.getKorisnik().getPrezimeKorisnika());
		
		JPanel panel = new JPanel();
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu knjiga = new JMenu("Knjiga");
		knjiga.add(novaKnjiga.getMenuItem());
		knjiga.add(pregledKnjiga.getMenuItem(panel));
		
		JMenu vrstaKnjige = new JMenu("Vrsta");
		vrstaKnjige.add(novaVrstaKnjige.getMenuItem());
		vrstaKnjige.add(vrstaKnjigePregled.getMenuItem(panel));
		knjiga.add(vrstaKnjige);
		
		JMenu izdavac = new JMenu("Izdavac");
		izdavac.add(noviIzdavac.getMenuItem());
		izdavac.add(pregledIzdavac.getMenuItem(panel));
		
		JMenu autori = new JMenu("Autor");
		autori.add(noviAutor.getMenuItem());
		autori.add(autorPregled.getMenuItem(panel));
		
		JMenu nastavnici = new JMenu("Nastavnici");
		nastavnici.add(noviNastavnik.getMenuItem());
		nastavnici.add(nastavniciPregled.getMenuItem(panel));
		
		JMenu predmeti = new JMenu("Predmeti");
		predmeti.add(predmetPregled.getMenuItem(panel));
		predmeti.add(noviPredmet.getMenuItem());
		
		JMenu studenti = new JMenu("Student");
		studenti.add(studentPregled.getMenuItem(panel));
		studenti.add(noviStudent.getMenuItem());
		
		
		
		
		menuBar.add(knjiga);
		menuBar.add(izdavac);
		menuBar.add(autori);
		menuBar.add(nastavnici);
		menuBar.add(predmeti);
		menuBar.add(studenti);
		setJMenuBar(menuBar);
		
		add(panel);
		
	}
	private Bibliotekar bibliotekar;

	private KnjigaPregled pregledKnjiga = new KnjigaPregled(true);
	private NovaKnjiga novaKnjiga = new NovaKnjiga();
	
	private IzdavacPregled pregledIzdavac = new IzdavacPregled();
	private NoviIzdavac noviIzdavac = new NoviIzdavac();
	
	private VrstaKnjigePregled vrstaKnjigePregled = new VrstaKnjigePregled();
	private NovaVrstaKnjige novaVrstaKnjige = new NovaVrstaKnjige();
	
	private AutorPregled autorPregled = new AutorPregled();
	private NoviAutor noviAutor = new NoviAutor();
	
	private NastavniciPregled nastavniciPregled = new NastavniciPregled();
	private NoviNastavnik noviNastavnik  = new NoviNastavnik();
	
	private NoviPredmet noviPredmet = new NoviPredmet();
	private PredmetPregled predmetPregled = new PredmetPregled();
	
	private NoviStudent noviStudent = new NoviStudent();
	private StudentPregled studentPregled = new StudentPregled();
	
}
