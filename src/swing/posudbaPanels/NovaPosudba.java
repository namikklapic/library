package swing.posudbaPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import bussines.NastavnikServiceBean;
import bussines.PosudbaServiceBean;
import bussines.StudentServiceBean;
import jpa.Korisnik;
import jpa.Nastavnik;
import jpa.Primjerak;
import jpa.Student;

public class NovaPosudba extends JFrame {
	
	public NovaPosudba(){
		
		setTitle("Nova posudba");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
		
		buttonGroup = new ButtonGroup();
		studentRadBtn = new JRadioButton("Student", true);
		nastavnikRadBtn = new JRadioButton("Nastavnik", false);
		buttonGroup.add(studentRadBtn);
		buttonGroup.add(nastavnikRadBtn);
		
		searchKorisnikLabel = new JLabel("Broj indeksa: ");
		searchKorisnikTxtField = new JTextField(10);
		searchKorisnikButton = new JButton("Pretraga");
		
		korisnikLabel = new JLabel("Korisnik: ");
		imeIprezimeKorisnika = new JTextField(20);
		
		studentRadBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				searchKorisnikLabel.setText("Broj indexa: ");
				searchKorisnikTxtField.setText("");
			}
		});
		
		nastavnikRadBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				searchKorisnikLabel.setText("JMBG: ");
				searchKorisnikTxtField.setText("");
			}
		});
		
		searchKorisnikButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(studentRadBtn.isSelected()){
					korisnik = studentServiceBean.searchByIndexNumber(searchKorisnikTxtField.getText()).getKorisnik();
				}else{
					korisnik = nastavnikServiceBean.searchByJMBG(searchKorisnikTxtField.getText()).getKorisnik();
				}
				if(korisnik != null)
					imeIprezimeKorisnika.setText(korisnik.getImeKorisnika() + ' ' + korisnik.getPrezimeKorisnika());
			}
		});
		
		panel.add(studentRadBtn);
		panel.add(nastavnikRadBtn);
		panel.add(searchKorisnikLabel);
		panel.add(searchKorisnikTxtField);
		panel.add(searchKorisnikButton);
		panel.add(korisnikLabel);
		panel.add(imeIprezimeKorisnika);
		
		add(panel);
	
	}
	
	public JMenuItem getMenuItem(){
		JMenuItem item = new JMenuItem("Nova posudba");
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				setVisible(true);
			}
		});
		return item;
	}
	
	private JPanel panel;
	
	private Korisnik korisnik=null;
	private Primjerak primjerak=null;
	private Date datumPosudbe=null;
	
	private ButtonGroup buttonGroup;
	private JRadioButton studentRadBtn;
	private JRadioButton nastavnikRadBtn;
	
	private JLabel searchKorisnikLabel;
	private JTextField searchKorisnikTxtField;
	private JButton searchKorisnikButton;
	
	private JLabel korisnikLabel;
	private JTextField imeIprezimeKorisnika;
	
	private StudentServiceBean studentServiceBean = new StudentServiceBean();
	private NastavnikServiceBean nastavnikServiceBean = new NastavnikServiceBean();

}
