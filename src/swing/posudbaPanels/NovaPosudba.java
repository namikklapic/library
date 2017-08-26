package swing.posudbaPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import bussines.KnjigaServiceBean;
import bussines.NastavnikServiceBean;
import bussines.PosudbaServiceBean;
import bussines.PrimjerakServiceBean;
import bussines.RezervacijaServiceBean;
import bussines.StudentServiceBean;
import jpa.Knjiga;
import jpa.Korisnik;
import jpa.Nastavnik;
import jpa.Posudba;
import jpa.PosudbaPK;
import jpa.Primjerak;
import jpa.Rezervacija;
import jpa.RezervacijaPK;
import jpa.Student;
import swing.autorPanels.NoviAutor;

public class NovaPosudba extends JFrame {
	
	public NovaPosudba(){
		
		setTitle("New book loan");
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
		
		selectUserLabel = new JLabel("Select user: ");
		selectUserLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		selectUserLabel.setBounds(12, 76, 148, 25);
		buttonGroup = new ButtonGroup();
		studentRadBtn = new JRadioButton("Student", true);
		studentRadBtn.setFocusPainted(false);
		studentRadBtn.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		studentRadBtn.setBounds(205, 76, 104, 25);
		nastavnikRadBtn = new JRadioButton("Teacher", false);
		nastavnikRadBtn.setFocusPainted(false);
		nastavnikRadBtn.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		nastavnikRadBtn.setBounds(315, 76, 104, 25);
		buttonGroup.add(studentRadBtn);
		buttonGroup.add(nastavnikRadBtn);
		
		studentRadBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				searchKorisnikLabel.setText("Student index: ");
				initializeView();	
				repaint();
				revalidate();
			}
		});
		
		nastavnikRadBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				searchKorisnikLabel.setText("Teacher's ID: ");
				initializeView();
				repaint();
				revalidate();
			}
		});
		panel.setLayout(null);
		
		panel.add(selectUserLabel);
		panel.add(studentRadBtn);
		panel.add(nastavnikRadBtn);
		
		searchKorisnikLabel = new JLabel("Student number: ");
		searchKorisnikLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		searchKorisnikLabel.setBounds(12, 114, 148, 25);
		txtSearchKorisnik = new JTextField(10);
		txtSearchKorisnik.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtSearchKorisnik.setBounds(172, 115, 183, 22);
		searchKorisnikButton = new JButton("Search");
		searchKorisnikButton.setBounds(381, 114, 73, 25);
		searchKorisnikButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				searchKorisnikButton.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				searchKorisnikButton.setBackground(Color.DARK_GRAY);
			}
		});
		searchKorisnikButton.setBorder(null);
		searchKorisnikButton.setFocusPainted(false);
		searchKorisnikButton.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		searchKorisnikButton.setForeground(new Color(255, 255, 255));
		searchKorisnikButton.setBackground(Color.DARK_GRAY);
		userLabel = new JLabel("User: ");
		userLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		userLabel.setBounds(12, 152, 148, 25);
		txtUser = new JTextField(20);
		txtUser.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtUser.setBounds(172, 153, 282, 22);
		txtUser.setEditable(false);
		
		searchKorisnikButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(studentRadBtn.isSelected()){
					if(txtSearchKorisnik.getText().equals("") || txtSearchKorisnik.getText().equals(null)){
						message = "Please, enter the student number!";
						displayMessageDialogBox();
					}else{
						try{
							korisnik = studentServiceBean.searchByIndexNumber(txtSearchKorisnik.getText()).getKorisnik();
							txtUser.setText(korisnik.getImeKorisnika() + ' ' + korisnik.getPrezimeKorisnika());
						}catch(Exception e){
							message = "No user found!";
							displayMessageDialogBox();
						}
					}
				}else{
					if(txtSearchKorisnik.getText().equals("") || txtSearchKorisnik.getText().equals(null)){
						message = "Please, enter the teacher's personal ID!";
						displayMessageDialogBox();
					}else{
						try{
							korisnik = studentServiceBean.searchByIndexNumber(txtSearchKorisnik.getText()).getKorisnik();
							txtUser.setText(korisnik.getImeKorisnika() + ' ' + korisnik.getPrezimeKorisnika());
						}catch(Exception e){
							message = "No user found!";
							displayMessageDialogBox();
						}
					}
				}
			}
	
		});
		
		panel.add(searchKorisnikLabel);
		panel.add(txtSearchKorisnik);
		panel.add(searchKorisnikButton);
		panel.add(userLabel);
		panel.add(txtUser);
		
		primjerakLabel = new JLabel("Book copy ID: ");
		primjerakLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		primjerakLabel.setBounds(12, 190, 148, 25);
		txtPrimjerak = new JTextField(10);
		txtPrimjerak.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtPrimjerak.setBounds(172, 191, 282, 22);
		panel.add(primjerakLabel);
		panel.add(txtPrimjerak);
		
		datumPosudbe = java.sql.Date.valueOf(LocalDate.now());
		dateWithoutTime = datumPosudbe.toString().substring(0, 10);
		datumPosudbeLabel = new JLabel("Start date: ");
		datumPosudbeLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		datumPosudbeLabel.setBounds(12, 228, 148, 25);
		txtDatumPosudbe = new JTextField(10);
		txtDatumPosudbe.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtDatumPosudbe.setBounds(172, 229, 282, 22);
		txtDatumPosudbe.setText(dateWithoutTime);
		txtDatumPosudbe.setEditable(false);
		panel.add(datumPosudbeLabel);
		panel.add(txtDatumPosudbe);
		
		krajnjiDatumVracanja = java.sql.Date.valueOf(LocalDate.now().plusDays(30));
		dateWithoutTime = krajnjiDatumVracanja.toString().substring(0, 10);
		krajnjiDatumVracanjaLabel = new JLabel("Ultimate end date: ");
		krajnjiDatumVracanjaLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		krajnjiDatumVracanjaLabel.setBounds(12, 266, 148, 25);
		txtKrajnjiDatumVracanja = new JTextField(10);
		txtKrajnjiDatumVracanja.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtKrajnjiDatumVracanja.setBounds(172, 267, 282, 22);
		txtKrajnjiDatumVracanja.setEditable(false);
		txtKrajnjiDatumVracanja.setText(dateWithoutTime);
		panel.add(krajnjiDatumVracanjaLabel);
		panel.add(txtKrajnjiDatumVracanja);
		
		brojDanaLabel = new JLabel("Number of days: ");
		brojDanaLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		brojDanaLabel.setBounds(12, 304, 148, 25);
		cbBrojDana = new JComboBox<Integer>();
		cbBrojDana.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		cbBrojDana.setBounds(172, 305, 282, 22);
		for(int i = 30; i <= 180; i+=30)
			cbBrojDana.addItem(i);
		cbBrojDana.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				Integer brDana = (Integer)cbBrojDana.getSelectedItem();
				krajnjiDatumVracanja = java.sql.Date.valueOf(LocalDate.now().plusDays(brDana));
				dateWithoutTime = krajnjiDatumVracanja.toString().substring(0, 10);
				txtKrajnjiDatumVracanja.setText(dateWithoutTime);
			}
		});
		panel.add(brojDanaLabel);
		panel.add(cbBrojDana);
		
		potvrdi = new JButton("Save");
		potvrdi.setBounds(150, 397, 87, 41);
		potvrdi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(isValidPosudba())
					savePosudba();
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
				initializeView();
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
		getContentPane().setLayout(null);
		panel.add(ponisti);
		
		getContentPane().add(panel);
		
		JLabel backgroundPicture = new JLabel("");
		backgroundPicture.setIcon(new ImageIcon(NoviAutor.class.getResource("/swing/images/background.jpg")));
		backgroundPicture.setBounds(0, 0, 500, 500);
		getContentPane().add(backgroundPicture);
	}
	
	public JMenuItem getMenuItem(){
		JMenuItem item = new JMenuItem("New book loan");
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				setVisible(true);
			}
		});
		return item;
	}
	
	private boolean isPosudbaDataEmpty(){
		boolean success = false;
		if(txtUser.getText().equals("") || txtUser.getText().equals(null)){
			txtUser.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtPrimjerak.getText().equals("") || txtPrimjerak.getText().equals(null)){
			txtPrimjerak.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		return success;
	}
	
	private boolean isValidPosudba(){
		if(isPosudbaDataEmpty()){
			message = "All data must be entered!";
			return false;
		}
		try{
			primjerak = primjerakServiceBean.getByInvBroj(txtPrimjerak.getText());
		}catch(Exception e) {}
		
		if(primjerak == null){
			message = "The book copy with specified ID does not exist!";
			return false;
		}
		if(primjerak.isPosudjen()){
			message = "The requested book copy is not available at the moment!";
			return false;
		}
		if(primjerak.isRezervisan()) {
			RezervacijaPK id = new RezervacijaPK(primjerak.getInventarskiBroj(), korisnik.getSifraKorisnika());
			boolean isThisUsersReservation = rezervacijaServiceBean.doesReservationExist(id);
			if(isThisUsersReservation)
				return true;
			else {
				message = "The requested book copy is not available at the moment!";
				return false;
			}
		}
		return true;
	}
	
	private void savePosudba(){
		
		id = new PosudbaPK(primjerak.getInventarskiBroj(), korisnik.getSifraKorisnika(), datumPosudbe);
		Posudba p = new Posudba(id, primjerak, korisnik, krajnjiDatumVracanja);
		posudbaServiceBean.save(p);
		primjerakServiceBean.setPrimjerakPosudjen(primjerak, true);
		if(primjerak.isRezervisan()) {
			RezervacijaPK id = new RezervacijaPK(primjerak.getInventarskiBroj(), korisnik.getSifraKorisnika());
			rezervacijaServiceBean.setRezervacijaConfirmed(id);
		}
		message = "The book loan has been successfully saved!";
	}
	
	private void initializeView(){
		txtSearchKorisnik.setText("");
		txtUser.setText("");
		txtPrimjerak.setText("");
		
		txtUser.setBackground(Color.WHITE);
		txtPrimjerak.setBackground(Color.WHITE);
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	public void prikazi() { setVisible(true); }
	
	private JPanel panel;
	private String message;
	private String dateWithoutTime;
	
	private Korisnik korisnik = null;
	private Primjerak primjerak = null;
	private PosudbaPK id = null;
	private Date datumPosudbe = null;;
	private Date krajnjiDatumVracanja = null;
	
	private JLabel selectUserLabel;
	private ButtonGroup buttonGroup;
	private JRadioButton studentRadBtn;
	private JRadioButton nastavnikRadBtn;
	
	private JLabel searchKorisnikLabel;
	private JTextField txtSearchKorisnik;
	private JButton searchKorisnikButton;
	
	private JLabel userLabel;
	private JTextField txtUser;
	
	private JLabel primjerakLabel;
	private JTextField txtPrimjerak;
	
	private JLabel datumPosudbeLabel;
	private JTextField txtDatumPosudbe;
	private JLabel krajnjiDatumVracanjaLabel;
	private JTextField txtKrajnjiDatumVracanja;
	private JLabel brojDanaLabel;
	private JComboBox<Integer> cbBrojDana;
	
	private JButton potvrdi;
	private JButton ponisti;
	
	private StudentServiceBean studentServiceBean = new StudentServiceBean();
	private NastavnikServiceBean nastavnikServiceBean = new NastavnikServiceBean();
	private PosudbaServiceBean posudbaServiceBean = new PosudbaServiceBean();
	private PrimjerakServiceBean primjerakServiceBean = new PrimjerakServiceBean();
	private RezervacijaServiceBean rezervacijaServiceBean = new RezervacijaServiceBean();
}