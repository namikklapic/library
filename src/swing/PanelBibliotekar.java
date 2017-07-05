package swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jpa.Bibliotekar;

public class PanelBibliotekar extends JFrame{

	public PanelBibliotekar (Bibliotekar b){
		this.bibliotekar = b;
		add((new JPanel()).add(new JLabel("Hello from bibliotekar dashboard")));
		setTitle(b.getKorisnik().getImeKorisnika() + " " + b.getKorisnik().getPrezimeKorisnika());
	}
	private Bibliotekar bibliotekar;
}
