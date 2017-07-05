package swing;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import jpa.Student;
import swing.knjigaPanels.KnjigaPregled;
import swing.knjigaPanels.NovaKnjiga;

public class PanelStudent extends JFrame {
	public PanelStudent(Student s){
		
		panel = this;
		
		JMenuBar menuBar = new JMenuBar();
		JMenu knjigaMenu = new JMenu("Knjiga");
		JMenuItem pregledKnjiga = new JMenuItem("Pregled knjiga");
		JMenuItem nKnjiga = new JMenuItem("Nova knjiga");
		
//		add(knjigaPregled);
		
		pregledKnjiga.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
//				knjigaPregled.prikazi();
				add(knjigaPregled);
				revalidate();
			}
		});
		
		nKnjiga.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				novaKnjiga.prikazi();
			}
		});
		
		knjigaMenu.add(pregledKnjiga);
		knjigaMenu.add(nKnjiga);
		menuBar.add(knjigaMenu);
		setJMenuBar(menuBar);
		
		
		this.student = s;
//		add((new JPanel()).add(new JLabel("Hello from student dashboard")));
		setTitle(s.getKorisnik().getImeKorisnika() + " " + s.getKorisnik().getPrezimeKorisnika());
		
		
		
		
	}
	
	public void paintComponent(Graphics g){
		add(knjigaPregled);
	}
	private PanelStudent panel;
	private Student student;
	private KnjigaPregled knjigaPregled = new KnjigaPregled();
	private NovaKnjiga novaKnjiga = new NovaKnjiga();
}
