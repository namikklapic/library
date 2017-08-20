package swing.studentiPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
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
import swing.autorPanels.NoviAutor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NoviStudent extends JFrame {
	
	public NoviStudent() {
		setTitle("New student");
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 500;
		int sirinaProzora = 500;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(500, 500);
		
		panel = new JPanel();
		panel.setSize(476, 471);
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setLocation(12, 16);
		panel.setLayout(null);
		
		ime = new JLabel ("First name: ");
		ime.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		ime.setBounds(36, 101, 171, 25);
		panel.add(ime);
		txtIme.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtIme.setBackground(Color.white);
			}
		});
		txtIme.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtIme.setBounds(219, 102, 222, 22);
		panel.add(txtIme);
		
		prezime = new JLabel("Last name: ");
		prezime.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		prezime.setBounds(36, 139, 171, 25);
		panel.add(prezime);
		txtPrezime.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtPrezime.setBackground(Color.white);

			}
		});
		txtPrezime.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtPrezime.setBounds(219, 140, 222, 22);
		panel.add(txtPrezime);
		
		jmbg = new JLabel("Personal ID: ");
		jmbg.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		jmbg.setBounds(36, 177, 171, 25);
		panel.add(jmbg);
		txtJmbg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtJmbg.setBackground(Color.white);

			}
		});
		txtJmbg.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtJmbg.setBounds(219, 178, 222, 22);
		panel.add(txtJmbg);
		
		brojIndeksa = new JLabel("Student number: ");
		brojIndeksa.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		brojIndeksa.setBounds(36, 215, 171, 25);
		panel.add(brojIndeksa);
		txtBrojIndeksa.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBrojIndeksa.setBackground(Color.white);

			}
		});
		txtBrojIndeksa.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtBrojIndeksa.setBounds(219, 216, 222, 22);
		panel.add(txtBrojIndeksa);
		
		upisaniSem = new JLabel("Registered semester: ");
		upisaniSem.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		upisaniSem.setBounds(36, 253, 171, 25);
		panel.add(upisaniSem);
		txtUpisaniSem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtUpisaniSem.setBackground(Color.white);

			}
		});
		txtUpisaniSem.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtUpisaniSem.setBounds(219, 254, 222, 22);
		panel.add(txtUpisaniSem);
		
		potvrdi = new JButton("Save");
		potvrdi.setBounds(150, 397, 87, 41);
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(isValidStudent())
					saveStudent();
				displayMessageDialogBox();
			}
		});
		potvrdi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				potvrdi.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				potvrdi.setBackground(Color.DARK_GRAY);
			}
		});
		potvrdi.setBorder(null);
		potvrdi.setFocusPainted(false);
		potvrdi.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		potvrdi.setForeground(new Color(255, 255, 255));
		potvrdi.setBackground(Color.DARK_GRAY);
		panel.add(potvrdi);
		
		ponisti = new JButton("Cancel");
		ponisti.setBounds(249, 397, 87, 41);
		ponisti.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				initializeUIElements();
				dispose();
				ponisti.setBackground(Color.DARK_GRAY);
			}
		});
		ponisti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ponisti.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				ponisti.setBackground(Color.DARK_GRAY);
			}
		});
		ponisti.setBorder(null);
		ponisti.setFocusPainted(false);
		ponisti.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		ponisti.setForeground(new Color(255, 255, 255));
		ponisti.setBackground(Color.DARK_GRAY);
		panel.add(ponisti);
		
		/*panel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent event){
				setUIElementsColor(Color.WHITE);
			}			
		});*/
		getContentPane().setLayout(null);
							
		getContentPane().add(panel);
		
		JLabel backgroundPicture = new JLabel("");
		backgroundPicture.setIcon(new ImageIcon(NoviAutor.class.getResource("/swing/images/background.jpg")));
		backgroundPicture.setBounds(0, 0, 500, 500);
		getContentPane().add(backgroundPicture);
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
			txtJmbg.setBackground(Color.LIGHT_GRAY);
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
			txtIme.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtPrezime.getText().equals("") || txtPrezime.getText().equals(null)){
			txtPrezime.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtJmbg.getText().equals("") || txtJmbg.getText().equals(null)){
			txtJmbg.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtBrojIndeksa.getText().equals("") || txtBrojIndeksa.equals(null)){
			txtBrojIndeksa.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtUpisaniSem.getText().equals("") || txtUpisaniSem.equals(null)){
			txtUpisaniSem.setBackground(Color.LIGHT_GRAY);
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
