package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.Timer;

import bussines.BibliotekarServiceBean;
import bussines.NastavnikServiceBean;
import bussines.StudentServiceBean;
import jpa.Bibliotekar;
import jpa.Nastavnik;
import jpa.Student;
import javax.swing.SwingConstants;


public class PanelPrijava extends JPanel {
	private static final long serialVersionUID = 1L;
	private StudentServiceBean student = new StudentServiceBean();
	private NastavnikServiceBean nastavnik = new NastavnikServiceBean();
	private BibliotekarServiceBean bibliotekar = new BibliotekarServiceBean();
	private JTextField txtUser = new JTextField(13);
	private JPasswordField txtPass = new JPasswordField(13);
	private JFrame mainFrame;
	private JTextField textField;

	
	
	public PanelPrijava(JFrame mF) {
		
		setLayout(null);
		mainFrame = mF;
		
		// Whole panel that is showing options -- BEGIN
		JPanel userLogin = new JPanel();
		userLogin.setOpaque(false);
		userLogin.setBackground( new Color(95, 158, 160) );
		userLogin.setBounds(0, 0, 400, 600);
		userLogin.setLayout(null);
		
		add(userLogin);
		// Whole panel that is showing options -- END
		
		// Username label options - BEGIN
		JLabel user = new JLabel("Username:");
		user.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
		user.setForeground(new Color(255, 255, 255));
		user.setBounds(54, 265, 124, 23);
		
		userLogin.add(user);
		// Username label options - END
		
		
		// Username enter field options - BEGIN
		txtUser.setAlignmentX(JTextField.RIGHT_ALIGNMENT);
		txtUser.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtUser.setBorder(null);
		txtUser.setText("Enter your username");
		txtUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtUser.setText("");
			}
		});
		txtUser.setForeground(new Color(255, 255, 255));
		txtUser.setOpaque(false);
		txtUser.setToolTipText("");
		txtUser.setBounds(54, 301, 284, 23);
		
		userLogin.add(txtUser);
		// Username enter field options - END
		
		// Login button options -- BEGIN
		JButton prijava = new JButton("Login");
		prijava.setBorder(null);
		prijava.setFocusPainted(false);
		prijava.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		prijava.setForeground(new Color(255, 255, 255));
		prijava.setBackground(Color.DARK_GRAY);
		prijava.setBounds(99, 469, 200, 56);
		prijava.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				prijava.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				prijava.setBackground(Color.DARK_GRAY);
			}
		});
		prijava.addActionListener(new prijavaEvent());
		
		userLogin.add(prijava);
		
				// Login button options -- END
				
				// Label Welcome -- BEGIN
				JLabel lblLogIn = new JLabel("Welcome");
				lblLogIn.setForeground(new Color(255, 255, 255));
				lblLogIn.setFont(new Font("Segoe UI Light", Font.BOLD, 45));
				lblLogIn.setBounds(99, 39, 200, 92);
				
				userLogin.add(lblLogIn);	
				//Label Welcome -- END
				
				// Label Please log in -- BEGIN
				JLabel lblPleaseSignIn = new JLabel("Please sign in");
				lblPleaseSignIn.setForeground(Color.WHITE);
				lblPleaseSignIn.setFont(new Font("Segoe UI Light", Font.BOLD, 27));
				lblPleaseSignIn.setBounds(116, 159, 171, 64);
				
				userLogin.add(lblPleaseSignIn);
				// Label Please log in -- END
				
				// All separators used -- BEGIN
				JSeparator separator = new JSeparator();
				separator.setBounds(73, 221, 265, 2);
				userLogin.add(separator);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(54, 323, 275, 2);
				userLogin.add(separator_1);
				
				JSeparator separator_2 = new JSeparator();
				separator_2.setBounds(54, 403, 275, 2);
				userLogin.add(separator_2);
				// All separators used -- END
				
				// Label password options -- BEGIN
				JLabel password = new JLabel("Password:");
				password.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
				password.setForeground(new Color(255, 255, 255));
				password.setBounds(54, 348, 124, 23);
				
				userLogin.add(password);
				// Label password options -- END

				// Workaround for focus issue -- BEGIN
				textField = new JTextField();
				textField.setBounds(74, 13, -52, -3);
				textField.setColumns(10);
				
				userLogin.add(textField);
				// Workaround for focus issue -- END
				
				// Password enter field options -- BEGIN
				txtPass.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
				txtPass.setBorder(null);
				txtPass.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						txtPass.setText("");
					}
				});
				txtPass.setForeground(new Color(255, 255, 255));
				txtPass.setOpaque(false);
				txtPass.setText("Enter your password");
				txtPass.setBounds(54, 382, 284, 23);
				userLogin.add(txtPass);
				prijava.setForeground(new Color(255, 255, 255));
				prijava.setBackground(Color.DARK_GRAY);
				
				JButton btnMinimize = new JButton("__");
				btnMinimize.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {
						btnMinimize.setBackground(Color.GRAY);			
						}
					@Override
					public void mouseExited(MouseEvent e) {
						btnMinimize.setBackground(Color.DARK_GRAY);
					}
				});
				btnMinimize.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						mF.setExtendedState(JFrame.ICONIFIED);
						
					}
				});
				btnMinimize.setForeground(Color.WHITE);
				btnMinimize.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
				btnMinimize.setFocusPainted(false);
				btnMinimize.setBorder(null);
				btnMinimize.setBackground(Color.DARK_GRAY);
				btnMinimize.setBounds(276, 13, 53, 35);
				userLogin.add(btnMinimize);
				
				JButton btnExit = new JButton("X");
				btnExit.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {
						btnExit.setBackground(Color.GRAY);			
						}
					@Override
					public void mouseExited(MouseEvent e) {
						btnExit.setBackground(Color.DARK_GRAY);
					}
				});
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(1);
						
					}
				});
				btnExit.setForeground(Color.WHITE);
				btnExit.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
				btnExit.setFocusPainted(false);
				btnExit.setBorder(null);
				btnExit.setBackground(Color.DARK_GRAY);
				btnExit.setBounds(335, 13, 53, 35);
				userLogin.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelPrijava.class.getResource("/swing/images/loginbackground.jpg")));
		lblNewLabel.setBounds(0, 0, 400, 600);
		add(lblNewLabel);
		
		// Password enter field options -- END		
		
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
			
			// Timer is used for delaying the loading animation 
			
			Timer delay = new Timer(2000, new ActionListener() {	        		        		            
	        	@Override      		            
	        	public void actionPerformed(ActionEvent e) {
					Student s = student.findByIndexNumber(txtUser.getText(), txtPass.getText());
					Nastavnik n = nastavnik.findByNameAndSurname(txtUser.getText(), txtPass.getText());
					Bibliotekar b = bibliotekar.findByNameAndSurname(txtUser.getText(), txtPass.getText());
					if(b != null){
						mainFrame.setVisible(false);
						JFrame bibliotekarLogin = new PanelBibliotekar(b);
						bibliotekarLogin.setSize(1200, 800);
						Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					    int x = (int) ((dimension.getWidth() - bibliotekarLogin.getWidth()) / 2);
					    int y = (int) ((dimension.getHeight() - bibliotekarLogin.getHeight()) / 2);
					    bibliotekarLogin.setLocation(x, y);
						bibliotekarLogin.setVisible(true);
						bibliotekarLogin.addWindowListener(new UserPanelClosingAction());
					}
					else if (n != null){
						mainFrame.setVisible(false);
						JFrame nastavnikLogin = new PanelNastavnik(n);
						nastavnikLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
						nastavnikLogin.setVisible(true);
						nastavnikLogin.addWindowListener(new UserPanelClosingAction());
					}
					else if(s != null){
						mainFrame.setVisible(false);
						JFrame studentLogin = new PanelStudent(s);
						studentLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
						studentLogin.setVisible(true);
						studentLogin.addWindowListener(new UserPanelClosingAction());
						
					}
					else {						
						mainFrame.dispose();
						
						JOptionPane.showMessageDialog(null,"Login failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
						
						JFrame frame = new JFrame();
						
						Toolkit kit = Toolkit.getDefaultToolkit();
						Dimension velicinaEkrana = kit.getScreenSize();
						int visinaProzora = 600;
						int sirinaProzora = 400;
						
						PanelPrijava panelPrijava = new PanelPrijava(frame);
						frame.setSize(400, 600);
						frame.setResizable(false);

						frame.setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.getContentPane().add(panelPrijava);
						frame.setTitle("Login");
						frame.setUndecorated(true);
						
						frame.setVisible(true);
						
					}
				}
			});
			
			delay.setRepeats(false);
	        delay.start();

			mainFrame.getContentPane().removeAll();
			mainFrame.revalidate();
		
			mainFrame.getContentPane().setBackground(Color.GRAY);

			
			mainFrame.getContentPane().setSize(800, 600);
			mainFrame.setTitle("Loading, please wait...");
		    ImageIcon loading = new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/loader.gif"));
		    mainFrame.add(new JLabel("", loading, JLabel.CENTER)); 	 

		
		    mainFrame.setVisible(true);
		    
		    
		    
		    
	}
}
	
	class UserPanelClosingAction extends WindowAdapter implements WindowListener{
		public UserPanelClosingAction() {
			frame = new JFrame();
			
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension velicinaEkrana = kit.getScreenSize();
			int visinaProzora = 600;
			int sirinaProzora = 400;
			
			PanelPrijava panelPrijava = new PanelPrijava(frame);
			frame.setSize(400, 600);
			frame.setResizable(false);

			frame.setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(panelPrijava);
			frame.setTitle("Login");
		}
		@Override
		public void windowClosing(WindowEvent event){
			frame.setVisible(true);
		}
		private JFrame frame;
	}
}