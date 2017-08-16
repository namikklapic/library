package swing.studentiPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bussines.KorisnikServiceBean;
import bussines.StudentServiceBean;
import jpa.Korisnik;
import jpa.Student;

public class NoviStudent extends JFrame {
	public NoviStudent() {
		setTitle("Novi student");
		setSize(500, 500);
		
		JPanel panel = new JPanel();
		panel.setSize(500, 500);
		
		JLabel ime = new JLabel ("Ime studenta");
		panel.add(ime);
		panel.add(txtIme);
		
		JLabel prezime = new JLabel("Prezime studenta");
		panel.add(prezime);
		panel.add(txtPrezime);
		
		JLabel jmbg = new JLabel("JMBG");
		panel.add(jmbg);
		panel.add(txtJmbg);
		
		JLabel brojIndeksa = new JLabel("Broj indeksa");
		panel.add(brojIndeksa);
		panel.add(txtBrojIndeksa);
		
		
		JLabel upisaniSem = new JLabel("Upisani semestar");
		panel.add(upisaniSem);
		panel.add(txtUpisaniSem);
		
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				saveStudent();
			}
		});
		panel.add(potvrdi);
		
		add(panel);
	}
	
	public NoviStudent(Student s) {
		this();
		txtIme.setText(s.getKorisnik().getImeKorisnika());
		txtPrezime.setText(s.getKorisnik().getPrezimeKorisnika());
		txtBrojIndeksa.setText(s.getBrojIndeksa());
		txtUpisaniSem.setText(Integer.toString(s.getUpisaniSemestar()));
		
		txtJmbg.setText(s.getPassword());
		txtJmbg.setEditable(false);
		
		setTitle("Uredi studenta");
		potvrdi.setText("Snimi izmjene");
		
	}
	
	private void saveStudent() {
		String ime = txtIme.getText();
		String prezime = txtPrezime.getText();
		String pass = txtJmbg.getText();
		String brojIndeksa = txtBrojIndeksa.getText();
		int upisaniSem = Integer.parseInt(txtUpisaniSem.getText());
		
		String sifra = txtJmbg.getText();
		
		Korisnik k = new Korisnik(sifra, ime, prezime, 0);
		
		studentServiceBean.save(new Student(k, brojIndeksa, pass, upisaniSem));
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem item = new JMenuItem("New student");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				prikazi();
			}
		});
		return item;
	}
	
	
	public void prikazi() {
		setVisible(true);
	}
	
	private JTextField txtIme = new JTextField(15);
	private JTextField txtPrezime = new JTextField(15);
	private JTextField txtJmbg = new JTextField(13);
	private JTextField txtBrojIndeksa = new JTextField(10);
	private JTextField txtUpisaniSem = new JTextField(10);
	
	JButton potvrdi = new JButton("Potvrdi");
	
	private KorisnikServiceBean korisnikServiceBean = new KorisnikServiceBean();
	private StudentServiceBean studentServiceBean = new StudentServiceBean();
	
	
}
