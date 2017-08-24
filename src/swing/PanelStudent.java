package swing;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import bussines.BibliotekarServiceBean;
import jpa.Student;
import swing.RezervacijaPanels.RezervacijePregled;
import swing.knjigaPanels.KnjigaPregled;
import swing.knjigaPanels.NovaKnjiga;
import swing.posudbaPanels.PosudbePregled;

public class PanelStudent extends JFrame {
	public PanelStudent(Student s){
		JPanel p = new JPanel();
		panel = this;
		
		knjigaPregled = new KnjigaPregled(false, s.getKorisnik());
		posudbePregled = new PosudbePregled(s.getKorisnik());
		rezervacijePregled = new RezervacijePregled(s.getKorisnik());
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu knjigaMenu = new JMenu("Knjiga");
		JMenuItem pregledKnjiga = new JMenuItem("Pregled knjiga");
		knjigaMenu.add(knjigaPregled.getMenuItem(p));
		menuBar.add(knjigaMenu);
		
		JMenu posudbeMenu = new JMenu("Posudbe");
		JMenuItem pregledPosudbi = new JMenuItem("Pregled Posudbi");
		posudbeMenu.add(rezervacijePregled.getMenuItem(p));
		menuBar.add(posudbeMenu);
		
		JMenu rezervacijeMenu = new JMenu("Rezervacije");
		JMenuItem pregledRezervacija = new JMenuItem("Pregled rezervacija");
		rezervacijeMenu.add(rezervacijePregled.getMenuItem(p));
		menuBar.add(rezervacijeMenu);
		
		setJMenuBar(menuBar);
		
		this.student = s;
		setTitle(s.getKorisnik().getImeKorisnika() + " " + s.getKorisnik().getPrezimeKorisnika());
		add(p);
		
		
	}
	
	private PanelStudent panel;
	private Student student;
	private KnjigaPregled knjigaPregled;
	private PosudbePregled posudbePregled;
	private RezervacijePregled rezervacijePregled;
}
