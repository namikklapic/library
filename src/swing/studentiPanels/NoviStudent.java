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
		
		JLabel password = new JLabel("Password");
		panel.add(password);
		panel.add(txtPass);
		
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
		txtUpisaniSem.setText(Integer.toString(s.getUspisaniSemestar()));
		
		txtPass.setText(s.getPassword());
		txtPass.setEditable(false);
		
		setTitle("Uredi studenta");
		potvrdi.setText("Snimi izmjene");
		
	}
	
	private void saveStudent() {
		String ime = txtIme.getText();
		String prezime = txtPrezime.getText();
		String pass = txtPass.getText();
		String brojIndeksa = txtBrojIndeksa.getText();
		int upisaniSem = Integer.parseInt(txtUpisaniSem.getText());
		
		int sifra = korisnikServiceBean.getCount() + 1;
		
		Korisnik k = new Korisnik(Integer.toString(sifra), ime, prezime, 0);
		
		studentServiceBean.save(new Student(k, brojIndeksa, pass, upisaniSem));
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem item = new JMenuItem("Novi student");
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
	private JPasswordField txtPass = new JPasswordField(15);
	private JTextField txtBrojIndeksa = new JTextField(10);
	private JTextField txtUpisaniSem = new JTextField(10);
	
	JButton potvrdi = new JButton("Potvrdi");
	
	private KorisnikServiceBean korisnikServiceBean = new KorisnikServiceBean();
	private StudentServiceBean studentServiceBean = new StudentServiceBean();
	
	
}
