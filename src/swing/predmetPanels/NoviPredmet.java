package swing.predmetPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class NoviPredmet extends JFrame {

	public NoviPredmet() {
		setTitle("NoviPredmet");
		setSize(300, 300);
		
		panel = new JPanel();
		panel.setSize(300, 300);
		
		sifraPredmeta = new JLabel("Subject ID: ");
		nazivPredmeta = new JLabel("Subject title: ");
		skraceniNazivPredmeta = new JLabel("Subject short title: ");
		brojSemestra = new JLabel("Semester: ");
		
		JButton potvrdi = new JButton("Save");
		JButton ponisti =  new JButton("Cancel");
		
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(isValidPredmet())
					savePredmet();
				displayMessageDialogBox();
			}
		});
		
		ponisti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				initializeUIElements();
				dispose();
			}
		});
		
		panel.add(sifraPredmeta);
		panel.add(txtSifra);
		panel.add(nazivPredmeta);
		panel.add(txtNaziv);
		panel.add(skraceniNazivPredmeta);
		panel.add(txtSkrNaziv);
		panel.add(brojSemestra);
		panel.add(txtSemestar);
		panel.add(potvrdi);
		panel.add(ponisti);
		
		panel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent event){
				setUIElementsColor(Color.WHITE);
			}
		});
		
		add(panel);
		
	}
	
	public NoviPredmet(Predmet p) {
		this();
		
		txtSifra.setText(p.getSifraPredmeta());
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
		
		String sifra = txtSifra.getText();
		String naziv = txtNaziv.getText();
		String skrNaziv = txtSkrNaziv.getText();
		int semestar = Integer.parseInt(txtSemestar.getText());
		predmetServiceBean.save(new Predmet(sifra, naziv, skrNaziv, semestar));
		
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
		else if(predmetServiceBean.existsPredmetBySifra(txtSifra.getText())){
			message = "Subject with the entered ID already exists!";
			return false;
		}
		else if(predmetServiceBean.existsPredmetByNaziv(txtNaziv.getText())){
			message = "Subject with the entered title alreday exists!";
			return false;
		}
		else if(predmetServiceBean.existsPredmetBySkraceniNaziv(txtSkrNaziv.getText())){
			message = "Subject with the entered short title alreday exists!";
			return false;
		}
		return true;
	}
	
	private boolean isPredmetDataEmpty(){
		boolean success = false;
		
		if(txtSifra.getText().equals("") || txtSifra.getText().equals(null)){
			txtSifra.setBackground(Color.RED);
			success = true;
		}
		if(txtNaziv.getText().equals("") || txtNaziv.getText().equals(null)){
			txtNaziv.setBackground(Color.RED);
			success = true;
		}
		if(txtSkrNaziv.getText().equals("") || txtSkrNaziv.getText().equals(null)){
			txtSkrNaziv.setBackground(Color.RED);
			success = true;
		}
		if(txtSemestar.getText().equals("") || txtSemestar.getText().equals(null)){
			txtSemestar.setBackground(Color.RED);
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
	
	private JLabel sifraPredmeta;
	private JLabel nazivPredmeta;
	private JLabel skraceniNazivPredmeta;
	private JLabel brojSemestra;
	private JTextField txtSifra = new JTextField(5);
	private JTextField txtNaziv = new JTextField(15);
	private JTextField txtSkrNaziv = new JTextField(15);
	private JTextField txtSemestar = new JTextField(15);
}
