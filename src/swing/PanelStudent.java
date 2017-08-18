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
import swing.knjigaPanels.KnjigaPregled;
import swing.knjigaPanels.NovaKnjiga;
import swing.posudbaPanels.PosudbePregled;

public class PanelStudent extends JFrame {
	public PanelStudent(Student s){
		JPanel p = new JPanel();
		panel = this;
		knjigaPregled = new KnjigaPregled(false, s.getKorisnik());
		posudbePregled = new PosudbePregled(s.getKorisnik());
		JMenuBar menuBar = new JMenuBar();
		JMenu knjigaMenu = new JMenu("Knjiga");
		JMenuItem pregledKnjiga = new JMenuItem("Pregled knjiga");
		//JMenuItem nKnjiga = new JMenuItem("Nova knjiga");
		
//		add(knjigaPregled);
		
		/*pregledKnjiga.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
//				knjigaPregled.prikazi();
				currentPanel = "knjige";
				drawPanel();
				revalidate();
			}
		});*/
		
		/*nKnjiga.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				novaKnjiga.prikazi();
			}
		});*/
		
		knjigaMenu.add(knjigaPregled.getMenuItem(p));
		//knjigaMenu.add(nKnjiga);
		menuBar.add(knjigaMenu);
		
		JMenu posudbeMenu = new JMenu("Posudbe");
		JMenuItem pregledPosudbi = new JMenuItem("Pregled Posudbi");
		//JMenuItem nKnjiga = new JMenuItem("Nova knjiga");
		
		
		
		posudbeMenu.add(posudbePregled.getMenuItem(p));
		//knjigaMenu.add(nKnjiga);
		menuBar.add(posudbeMenu);
		
		setJMenuBar(menuBar);
		
		
		this.student = s;
//		add((new JPanel()).add(new JLabel("Hello from student dashboard")));
		setTitle(s.getKorisnik().getImeKorisnika() + " " + s.getKorisnik().getPrezimeKorisnika());
		add(p);
		
		
	}
	
	private PanelStudent panel;
	private Student student;
	private KnjigaPregled knjigaPregled;
	private PosudbePregled posudbePregled;
	//private NovaKnjiga novaKnjiga = new NovaKnjiga();
}
