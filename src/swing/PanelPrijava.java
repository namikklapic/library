package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bussines.BibliotekarServiceBean;
import bussines.NastavnikServiceBean;
import bussines.StudentServiceBean;
import jpa.Bibliotekar;
import jpa.Nastavnik;
import jpa.Student;


public class PanelPrijava extends JPanel {
	private static final long serialVersionUID = 1L;
	private StudentServiceBean student = new StudentServiceBean();
	private NastavnikServiceBean nastavnik = new NastavnikServiceBean();
	private BibliotekarServiceBean bibliotekar = new BibliotekarServiceBean();
	private JTextField txtUser = new JTextField(13);
	private JPasswordField txtPass = new JPasswordField(13);
	private JFrame mainFrame;
	
	public PanelPrijava(JFrame mF) {
		mainFrame = mF;
		setLayout(new GridLayout(2,2,-100,0));
		//logo image
//		ImageIcon slika = new ImageIcon(this.getClass().getResource("/resource/ikone/rsz_untz.png"));		
		JLabel lblslikaLogo = new JLabel(""){
			@Override
			public void paintComponent(Graphics g){
//				g.drawImage(slika.getImage(), 0, 0, 120, 120, null);
			}
		};
//		lblslikaLogo.setIcon(slika);
		// this panel is used to align logo labels
		JPanel labels = new JPanel();
		//university label
		JLabel univerzitet = new JLabel("UNIVERZITET U TUZLI");
		univerzitet.setForeground(Color.RED);
		univerzitet.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		univerzitet.setHorizontalAlignment(JLabel.CENTER);
		//faculty label
		JLabel fakultet = new JLabel("Fakultet elektrotehnike");
		fakultet.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		// add labels to panel
		labels.add(univerzitet);
		labels.add(fakultet);
		// add logo image and logo labels to the north section of the panel
		add(lblslikaLogo);
		add(labels);
		
		// lock icon
//		ImageIcon slikaLock = new ImageIcon(this.getClass().getResource("/kljuc.png"));	
		JLabel lblslikaLock = new JLabel(""){
			@Override
			public void paintComponent(Graphics g){
//				g.drawImage(slikaLock.getImage(), 0, 0, 120, 120, null);
			}
		};
//		lblslikaLock.setIcon(slikaLock);
		// add lock image to the west section of the panel
		add(lblslikaLock);
		
		JLabel user = new JLabel("User:");
		txtUser.setAlignmentX(JTextField.RIGHT_ALIGNMENT);
		
		
		JButton prijava = new JButton("Prijava");
		prijava.addActionListener(new prijavaEvent());
		JLabel password = new JLabel("Password:");
		JPanel userLogin = new JPanel();
		userLogin.add(user);
		userLogin.add(txtUser);
		userLogin.add(password);
		userLogin.add(txtPass);
		userLogin.add(prijava);
		
		add(userLogin, BorderLayout.EAST);
		
			
		
	}
	
	class prijavaEvent implements ActionListener{
		public void actionPerformed(ActionEvent event){
//			Korisnik k = new Korisnik("123", "Namik","Klapic", 0);
//			Student s = new Student(k, "14020", "14020", 6);
//			student.save(s);
//			
//			Korisnik k1 = new Korisnik("122", "Namik", "Klapic", 0);
//			Nastavnik n = new Nastavnik (k1, "docent", "122");
//			nastavnik.save(n);
//			
//			Korisnik k2 = new Korisnik("121", "Namik", "Klapic", 0);
//			Bibliotekar b = new Bibliotekar(k2, "14020");
//			bibliotekar.save(b);
			
			// Komentar za commit
			
			Student s = student.findByIndexNumber(txtUser.getText(), txtPass.getText());
			Nastavnik n = nastavnik.findByNameAndSurname(txtUser.getText(), txtPass.getText());
			Bibliotekar b = bibliotekar.findByNameAndSurname(txtUser.getText(), txtPass.getText());
			if(b != null){
				mainFrame.setVisible(false);
				JFrame bibliotekarLogin = new PanelBibliotekar(b);
				bibliotekarLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
				bibliotekarLogin.setVisible(true);
				bibliotekarLogin.addWindowListener(new UserPanelClosingAction(mainFrame));
			}
			else if (n != null){
				mainFrame.setVisible(false);
				JFrame nastavnikLogin = new PanelNastavnik(n);
				nastavnikLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
				nastavnikLogin.setVisible(true);
				nastavnikLogin.addWindowListener(new UserPanelClosingAction(mainFrame));
			}
			else if(s != null){
				mainFrame.setVisible(false);
				JFrame studentLogin = new PanelStudent(s);
				studentLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
				studentLogin.setVisible(true);
				studentLogin.addWindowListener(new UserPanelClosingAction(mainFrame));
				
			}
			else {
				System.out.println("Logiranje neuspjesno.");
			}
		}
	}
	
	class UserPanelClosingAction extends WindowAdapter implements WindowListener{
		public UserPanelClosingAction(JFrame frame) {
			frameToDisplay = frame;
		}
		@Override
		public void windowClosing(WindowEvent event){
			frameToDisplay.setVisible(true);
		}
		private JFrame frameToDisplay;
	}
	

}