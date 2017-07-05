package swing;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jpa.Nastavnik;

public class PanelNastavnik extends JFrame {
	public PanelNastavnik (Nastavnik n){
		this.nastavnik = n;
		add((new JPanel()).add(new JLabel("Hello from nastavnik dashboard")));
		setTitle(n.getKorisnik().getImeKorisnika() + " " + n.getKorisnik().getPrezimeKorisnika());
	}
	private Nastavnik nastavnik;
}
