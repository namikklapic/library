package swing.studentiPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bussines.KorisnikServiceBean;
import bussines.StudentServiceBean;
import jpa.Korisnik;
import jpa.Student;

public class NoviStudent extends JFrame {
	
	public NoviStudent() {
		setTitle("New student");
		setSize(500, 500);
		
		panel = new JPanel();
		panel.setSize(500, 500);
		
		ime = new JLabel ("First name: ");
		panel.add(ime);
		panel.add(txtIme);
		
		prezime = new JLabel("Last name: ");
		panel.add(prezime);
		panel.add(txtPrezime);
		
		jmbg = new JLabel("Personal ID: ");
		panel.add(jmbg);
		panel.add(txtJmbg);
		
		brojIndeksa = new JLabel("Student number: ");
		panel.add(brojIndeksa);
		panel.add(txtBrojIndeksa);
		
		upisaniSem = new JLabel("Registered semester: ");
		panel.add(upisaniSem);
		panel.add(txtUpisaniSem);
		
		potvrdi = new JButton("Save");
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(isValidStudent())
					saveStudent();
				displayMessageDialogBox();
			}
		});
		panel.add(potvrdi);
		
		ponisti = new JButton("Cancel");
		ponisti.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				initializeUIElements();
				dispose();
			}
		});
		panel.add(ponisti);
		
		panel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent event){
				setUIElementsColor(Color.WHITE);
			}			
		});
							
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
		
		setTitle("Edit student");
		potvrdi.setText("Save changes");
		
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
		
		message = "Student has been successfully saved!";
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
	
	private boolean isValidStudent(){
		
		if(isStudentDataEmpty()){
			message = "All data must be entered!";
			return false;
		}
		else if(txtJmbg.getText().contains("[a-zA-Z]+") || txtJmbg.getText().length() != 13){
			txtJmbg.setBackground(Color.RED);
			message = "Personal ID must contain 13 digits!";
			return false;
		}
		else if(!isNumeric(txtUpisaniSem.getText())){
			message = "Invalid value for registered semester!";
			return false;
		}
		else if(studentServiceBean.existsStudent(txtJmbg.getText())){
			message = "Student with the entered personal ID alreday exists!";
			return false;
		}
		return true;
	}
	
	private boolean isNumeric(String s){
		
		int num;
		try{
			num = Integer.parseInt(s);
		}catch(NumberFormatException e){
			return false;
		}catch(NullPointerException e){
			return false;
		}
		if(num < 1 || num > 8)
			return false;
		
		return true;
	}
	
	private boolean isStudentDataEmpty(){
		boolean success = false;
		
		if(txtIme.getText().equals("") || txtIme.getText().equals(null)){
			txtIme.setBackground(Color.RED);
			success = true;
		}
		if(txtPrezime.getText().equals("") || txtPrezime.getText().equals(null)){
			txtPrezime.setBackground(Color.RED);
			success = true;
		}
		if(txtJmbg.getText().equals("") || txtJmbg.getText().equals(null)){
			txtJmbg.setBackground(Color.RED);
			success = true;
		}
		if(txtBrojIndeksa.getText().equals("") || txtBrojIndeksa.equals(null)){
			txtBrojIndeksa.setBackground(Color.RED);
			success = true;
		}
		if(txtUpisaniSem.getText().equals("") || txtUpisaniSem.equals(null)){
			txtUpisaniSem.setBackground(Color.RED);
			success = true;
		}
		return success;
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private void initializeUIElements(){
		txtIme.setText("");
		txtPrezime.setText("");
		txtJmbg.setText("");
		txtBrojIndeksa.setText("");
		txtUpisaniSem.setText("");
		setUIElementsColor(Color.WHITE);
	}
	
	private void setUIElementsColor(Color c){
		txtIme.setBackground(c);
		txtPrezime.setBackground(c);
		txtJmbg.setBackground(c);
		txtBrojIndeksa.setBackground(c);
		txtUpisaniSem.setBackground(c);
	}
	
	private JPanel panel;
	
	String message;
	
	private JLabel ime;
	private JLabel prezime;
	private JLabel jmbg;
	private JLabel brojIndeksa;
	private JLabel upisaniSem;
	private JTextField txtIme = new JTextField(15);
	private JTextField txtPrezime = new JTextField(15);
	private JTextField txtJmbg = new JTextField(13);
	private JTextField txtBrojIndeksa = new JTextField(10);
	private JTextField txtUpisaniSem = new JTextField(10);
	private JButton potvrdi;
	private JButton ponisti;
	
	private KorisnikServiceBean korisnikServiceBean = new KorisnikServiceBean();
	private StudentServiceBean studentServiceBean = new StudentServiceBean();
}
