package swing;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import jpa.Nastavnik;
import swing.knjigaPanels.KnjigaPregled;

public class PanelNastavnik extends JFrame {
	public PanelNastavnik (Nastavnik n){
		this.nastavnik = n;
		setTitle(n.getKorisnik().getImeKorisnika() + " " + n.getKorisnik().getPrezimeKorisnika());
		
		JPanel panel = new JPanel();
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu knjiga = new JMenu("Books");
		knjiga.add(knjigaPregled.getMenuItem(panel));
		
		menuBar.add(knjiga);
		
		setJMenuBar(menuBar);
		
		add(panel);
		
		
		
		
	}
	private Nastavnik nastavnik;
	private KnjigaPregled knjigaPregled = new KnjigaPregled(false);
}
