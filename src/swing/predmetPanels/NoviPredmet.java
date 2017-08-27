package swing.predmetPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.PredmetServiceBean;
import jpa.Autor;
import jpa.Predmet;
import swing.autorPanels.NoviAutor;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;

public class NoviPredmet extends JFrame {

	public NoviPredmet() {
		setTitle("NoviPredmet");
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

		predmet = null;
		
		sifraPredmeta = new JLabel("Subject ID: ");
		sifraPredmeta.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		sifraPredmeta.setBounds(12, 120, 143, 30);
		nazivPredmeta = new JLabel("Subject title: ");
		nazivPredmeta.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		nazivPredmeta.setBounds(12, 163, 143, 30);
		skraceniNazivPredmeta = new JLabel("Subject short title: ");
		skraceniNazivPredmeta.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		skraceniNazivPredmeta.setBounds(12, 206, 143, 30);
		brojSemestra = new JLabel("Semester: ");
		brojSemestra.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		brojSemestra.setBounds(12, 252, 143, 30);
		
		JButton potvrdi = new JButton("Save");
		potvrdi.setBounds(150, 397, 87, 41);
		JButton ponisti =  new JButton("Cancel");
		ponisti.setBounds(249, 397, 87, 41);
		
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				boolean valid = isValidPredmet();
				if(valid)
					savePredmet();
				displayMessageDialogBox();
				
				if(valid){
					initializeUIElements();
					dispose();
				}
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
		
		ponisti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
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
		panel.setLayout(null);
		
		panel.add(sifraPredmeta);
		txtSifra.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSifra.setBackground(Color.WHITE);
			}
		});
		txtSifra.setBounds(219, 124, 245, 22);
		panel.add(txtSifra);
		panel.add(nazivPredmeta);
		txtNaziv.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNaziv.setBackground(Color.WHITE);
			}
		});
		txtNaziv.setBounds(219, 167, 245, 22);
		panel.add(txtNaziv);
		panel.add(skraceniNazivPredmeta);
		txtSkrNaziv.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSkrNaziv.setBackground(Color.WHITE);
			}
		});
		txtSkrNaziv.setBounds(219, 210, 245, 22);
		panel.add(txtSkrNaziv);
		panel.add(brojSemestra);
		txtSemestar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSemestar.setBackground(Color.WHITE);
			}
		});
		txtSemestar.setBounds(219, 256, 245, 22);
		panel.add(txtSemestar);
		panel.add(potvrdi);
		panel.add(ponisti);
		
		getContentPane().setLayout(null);
		
		getContentPane().add(panel);
		
		lblAddANew = new JLabel("Add a new subject");
		lblAddANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddANew.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		lblAddANew.setBounds(60, 33, 355, 32);
		panel.add(lblAddANew);
		
		JLabel backgroundPicture = new JLabel("");
		backgroundPicture.setIcon(new ImageIcon(NoviAutor.class.getResource("/swing/images/background.jpg")));
		backgroundPicture.setBounds(0, 0, 500, 500);
		getContentPane().add(backgroundPicture);
		
	}
	
	public NoviPredmet(Predmet p) {
		this();
		predmet = p;
		txtSifra.setText(p.getSifraPredmeta());
		txtSifra.setEditable(false);
		txtNaziv.setText(p.getNazivPredmeta());
		txtSkrNaziv.setText(p.getSkraceniNazivPredmeta());
		txtSemestar.setText(Integer.toString(p.getBrojSemestra()));
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem menuItem = new JMenuItem("New subject");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent evet) {
				prikazi();
			}
		});
		return menuItem;
	}
	
	public void prikazi() {
		setVisible(true);
	}
	
	private void savePredmet(){
		
		String sifra = predmet != null ? predmet.getSifraPredmeta() : txtSifra.getText();
		String naziv = txtNaziv.getText();
		String skrNaziv = txtSkrNaziv.getText();
		int semestar = Integer.parseInt(txtSemestar.getText());
		predmetServiceBean.save(new Predmet(sifra, naziv, skrNaziv, semestar));
		
		if(predmet != null)
			message = "Changes were successfully saved!";
		else
			message = "The subject has been successfully saved!";
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
	
	private boolean isValidPredmet(){
		
		if(isPredmetDataEmpty()){
			message = "All data must be entered!";
			return false;
		}
		else if(!isNumeric(txtSemestar.getText())){
			message = "Invalid value for semester!";
			return false;
		}
		else if(predmet == null && predmetServiceBean.existsPredmetBySifra(txtSifra.getText())){
			message = "Subject with the entered ID already exists!";
			return false;
		}
//		else if(predmet == null && predmetServiceBean.existsPredmetByNaziv(txtNaziv.getText())){
//			message = "Subject with the entered title alreday exists!";
//			return false;
//		}
//		else if(predmet == null && predmetServiceBean.existsPredmetBySkraceniNaziv(txtSkrNaziv.getText())){
//			message = "Subject with the entered short title alreday exists!";
//			return false;
//		}
		return true;
	}
	
	private boolean isPredmetDataEmpty(){
		boolean success = false;
		
		if(txtSifra.getText().equals("") || txtSifra.getText().equals(null)){
			txtSifra.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtNaziv.getText().equals("") || txtNaziv.getText().equals(null)){
			txtNaziv.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtSkrNaziv.getText().equals("") || txtSkrNaziv.getText().equals(null)){
			txtSkrNaziv.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		if(txtSemestar.getText().equals("") || txtSemestar.getText().equals(null)){
			txtSemestar.setBackground(Color.LIGHT_GRAY);
			success = true;
		}
		
		return success;
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private void initializeUIElements(){
		txtSifra.setText("");
		txtNaziv.setText("");
		txtSkrNaziv.setText("");
		txtSemestar.setText("");
		
		setUIElementsColor(Color.WHITE);
	}
	
	private void setUIElementsColor(Color c){
		txtSifra.setBackground(c);
		txtNaziv.setBackground(c);
		txtSkrNaziv.setBackground(c);
		txtSemestar.setBackground(c);
	}
	
	private JPanel panel;
	
	private String message;
	private PredmetServiceBean predmetServiceBean = new PredmetServiceBean();
	private Predmet predmet;
	
	private JLabel sifraPredmeta;
	private JLabel nazivPredmeta;
	private JLabel skraceniNazivPredmeta;
	private JLabel brojSemestra;
	private JTextField txtSifra = new JTextField(5);
	private JTextField txtNaziv = new JTextField(15);
	private JTextField txtSkrNaziv = new JTextField(15);
	private JTextField txtSemestar = new JTextField(15);
	private JLabel lblAddANew;
}
