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
import swing.posudbaPanels.NovaPosudba;
import swing.posudbaPanels.PosudbePregled;
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
		
		JMenu knjiga = new JMenu("Books");
		knjiga.add(novaKnjiga.getMenuItem());
		knjiga.add(pregledKnjiga.getMenuItem(panel));
		
		JMenu vrstaKnjige = new JMenu("Book type");
		vrstaKnjige.add(novaVrstaKnjige.getMenuItem());
		vrstaKnjige.add(vrstaKnjigePregled.getMenuItem(panel));
		knjiga.add(vrstaKnjige);
		
		JMenu autori = new JMenu("Authors");
		autori.add(noviAutor.getMenuItem());
		autori.add(autorPregled.getMenuItem(panel));
		knjiga.add(autori);
		
		JMenu izdavac = new JMenu("Publishers");
		izdavac.add(noviIzdavac.getMenuItem());
		izdavac.add(pregledIzdavac.getMenuItem(panel));
		knjiga.add(izdavac);
		
		JMenu nastavnici = new JMenu("Teachers");
		nastavnici.add(noviNastavnik.getMenuItem());
		nastavnici.add(nastavniciPregled.getMenuItem(panel));
		
		JMenu studenti = new JMenu("Students");
		studenti.add(noviStudent.getMenuItem());
		studenti.add(studentPregled.getMenuItem(panel));
		
		JMenu predmeti = new JMenu("Subjects");
		predmeti.add(noviPredmet.getMenuItem());
		predmeti.add(predmetPregled.getMenuItem(panel));
		
		JMenu posudbe = new JMenu("Book loans");
		posudbe.add(novaPosudba.getMenuItem());
		posudbe.add(posudbePregled.getMenuItem(panel));
				
		menuBar.add(knjiga);
		menuBar.add(nastavnici);
		menuBar.add(studenti);
		menuBar.add(predmeti);
		menuBar.add(posudbe);
		
		setJMenuBar(menuBar);
		
		add(panel);
		
	}
	private Bibliotekar bibliotekar;

	private NovaKnjiga novaKnjiga = new NovaKnjiga();
	private KnjigaPregled pregledKnjiga = new KnjigaPregled(true);
	
	private NovaVrstaKnjige novaVrstaKnjige = new NovaVrstaKnjige();
	private VrstaKnjigePregled vrstaKnjigePregled = new VrstaKnjigePregled();
	
	private NoviAutor noviAutor = new NoviAutor();
	private AutorPregled autorPregled = new AutorPregled();
	
	private NoviIzdavac noviIzdavac = new NoviIzdavac();
	private IzdavacPregled pregledIzdavac = new IzdavacPregled();
	
	private NoviNastavnik noviNastavnik  = new NoviNastavnik();
	private NastavniciPregled nastavniciPregled = new NastavniciPregled();
	
	private NoviStudent noviStudent = new NoviStudent();
	private StudentPregled studentPregled = new StudentPregled();
	
	private NoviPredmet noviPredmet = new NoviPredmet();
	private PredmetPregled predmetPregled = new PredmetPregled();
	
	private NovaPosudba novaPosudba = new NovaPosudba();
	private PosudbePregled posudbePregled = new PosudbePregled();
	
}
