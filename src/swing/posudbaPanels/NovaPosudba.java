package swing.posudbaPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
import bussines.StudentServiceBean;
import jpa.Knjiga;
import jpa.Korisnik;
import jpa.Nastavnik;
import jpa.Posudba;
import jpa.PosudbaPK;
import jpa.Primjerak;
import jpa.Student;

public class NovaPosudba extends JFrame {
	
	public NovaPosudba(){
		
		setTitle("New book loan");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
		
		selectUserLabel = new JLabel("Select user: ");
		buttonGroup = new ButtonGroup();
		studentRadBtn = new JRadioButton("Student", true);
		nastavnikRadBtn = new JRadioButton("Teacher", false);
		buttonGroup.add(studentRadBtn);
		buttonGroup.add(nastavnikRadBtn);
		
		studentRadBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				searchKorisnikLabel.setText("Student number: ");
				initializeView();	
			}
		});
		
		nastavnikRadBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				searchKorisnikLabel.setText("Teacher's personal ID: ");
				initializeView();
			}
		});
		
		panel.add(selectUserLabel);
		panel.add(studentRadBtn);
		panel.add(nastavnikRadBtn);
		
		searchKorisnikLabel = new JLabel("Student number: ");
		txtSearchKorisnik = new JTextField(10);
		searchKorisnikButton = new JButton("Search");
		userLabel = new JLabel("User: ");
		txtUser = new JTextField(20);
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
		txtPrimjerak = new JTextField(10);
		panel.add(primjerakLabel);
		panel.add(txtPrimjerak);
		
		datumPosudbe = new Date();
		datumPosudbeLabel = new JLabel("Start date: ");
		txtDatumPosudbe = new JTextField(10);
		txtDatumPosudbe.setText(LocalDate.now().toString());
		txtDatumPosudbe.setEditable(false);
		panel.add(datumPosudbeLabel);
		panel.add(txtDatumPosudbe);
		
		//krajnjiDatumVracanja = datumPosudbe + vremenski period ?
		krajnjiDatumVracanjaLabel = new JLabel("Ultimate end date: ");
		txtKrajnjiDatumVracanja = new JTextField(10);
		txtKrajnjiDatumVracanja.setText("?");
		txtKrajnjiDatumVracanja.setEditable(false);
		panel.add(krajnjiDatumVracanjaLabel);
		panel.add(txtKrajnjiDatumVracanja);
		
		potvrdi = new JButton("Save");
		potvrdi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(isValidPosudba())
					savePosudba();
				displayMessageDialogBox();
			}
		});
		panel.add(potvrdi);
		
		ponisti = new JButton("Cancel");
		ponisti.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				initializeView();
				dispose();
			}
		});
		panel.add(ponisti);
		
		add(panel);
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
			txtUser.setBackground(Color.RED);
			success = true;
		}
		if(txtPrimjerak.getText().equals("") || txtPrimjerak.getText().equals(null)){
			txtPrimjerak.setBackground(Color.RED);
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
		if((primjerak != null) && (primjerak.isPosudjen() || primjerak.isRezervisan())){
			message = "The requested book copy is not available at the moment!";
			return false;
		}
		return true;
	}
	
	private void savePosudba(){
		id = new PosudbaPK(primjerak.getInventarskiBroj(), korisnik.getSifraKorisnika(), datumPosudbe);
		Posudba p = new Posudba(id, primjerak, korisnik, krajnjiDatumVracanja);
		posudbaServiceBean.save(p);
		
		message = "The book load has been successfully saved!";
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
	
	private JPanel panel;
	private String message;
	
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
	
	private JButton potvrdi;
	private JButton ponisti;
	
	private StudentServiceBean studentServiceBean = new StudentServiceBean();
	private NastavnikServiceBean nastavnikServiceBean = new NastavnikServiceBean();
	private PosudbaServiceBean posudbaServiceBean = new PosudbaServiceBean();
	private PrimjerakServiceBean primjerakServiceBean = new PrimjerakServiceBean();
}