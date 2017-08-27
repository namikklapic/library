package swing.autorPanels;

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
import javax.swing.JTextField;

import bussines.AutorServiceBean;
import jpa.Autor;
import jpa.Izdavac;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;

public class NoviAutor extends JFrame {
	
	public NoviAutor() {
		
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 400;
		int sirinaProzora = 400;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(400, 300);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setLocation(12, 16);

		panel.setSize(376, 271);
		
		autor = null;
		
		JLabel imeAutora = new JLabel("First name");
		imeAutora.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		imeAutora.setBounds(12, 83, 154, 19);
		
		JLabel prezimeAutora = new JLabel("Last name");
		prezimeAutora.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		prezimeAutora.setBounds(12, 143, 154, 16);
		
		JButton potvrdi = new JButton("Save");
		potvrdi.setBounds(88, 222, 82, 36);
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {			
				boolean valid = isValidAutor();
				if(valid)
					saveAutor();
				displayMessageDialogBox();
				
				if(valid){
					initializeUIElements();
					dispose();
				}
			}
		});
		potvrdi.setBorder(null);
		potvrdi.setFocusPainted(false);
		potvrdi.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		potvrdi.setForeground(new Color(255, 255, 255));
		potvrdi.setBackground(Color.DARK_GRAY);
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
		
		
		JButton ponisti = new JButton("Cancel");
		ponisti.setBounds(198, 222, 82, 36);
		ponisti.setBorder(null);
		ponisti.setFocusPainted(false);
		ponisti.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		ponisti.setForeground(new Color(255, 255, 255));
		ponisti.setBackground(Color.DARK_GRAY);
		ponisti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ponisti.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				ponisti.setBackground(Color.DARK_GRAY);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				initializeUIElements();
				dispose();
				ponisti.setBackground(Color.DARK_GRAY);
			}
		});
		panel.setLayout(null);
		
		panel.add(imeAutora);
		txtIme.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtIme.setBackground(Color.WHITE);
			}
		});
		txtIme.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtIme.setBounds(108, 77, 256, 30);
		panel.add(txtIme);
		panel.add(prezimeAutora);
		txtPrezime.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtPrezime.setBackground(Color.WHITE);
			}
		});
		txtPrezime.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtPrezime.setBounds(108, 136, 256, 30);
		panel.add(txtPrezime);
		panel.add(potvrdi);
		panel.add(ponisti);
		
		getContentPane().add(panel);
		
		JLabel lblAddANew = new JLabel("Add a new author");
		lblAddANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddANew.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		lblAddANew.setBounds(12, 13, 355, 32);
		panel.add(lblAddANew);
		
		JLabel backgroundPicture = new JLabel("");
		backgroundPicture.setIcon(new ImageIcon(NoviAutor.class.getResource("/swing/images/background.jpg")));
		backgroundPicture.setBounds(0, 0, 400, 300);
		getContentPane().add(backgroundPicture);
		
	}
	
	public NoviAutor(Autor a) {
		this();
		autor = a;
		txtIme.setText(a.getImeAutora());
		txtPrezime.setText(a.getPrezimeAutora());
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem item = new JMenuItem("New author");
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
	
	public void saveAutor(){
		
		int id = autor != null ? autor.getId() : autorServiceBean.getCount() + 1;
		autorServiceBean.save(new Autor(id, txtIme.getText(), txtPrezime.getText()));
		
		if(autor != null)
			message = "Changes were successfully saved!";
		else
			message = "The author has been successfully saved!";
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private boolean isValidAutor(){
		String ime = txtIme.getText();
		String prezime = txtPrezime.getText();
		if(ime.equals("") || ime.equals(null)){
			txtIme.setBackground(Color.LIGHT_GRAY);
			message = "First name is missing!";
			return false;
		}
		if(prezime.equals("") || prezime.equals(null)){
			txtPrezime.setBackground(Color.LIGHT_GRAY);
			message = "Last name is missing!";
			return false;
		}
		if(autor == null && autorServiceBean.existsAutor(ime, prezime)){
			message = "The entered author already exists!";
			return false;
		}
		if(autor != null){
			if(autor.getImeAutora().equals(ime) && autor.getPrezimeAutora().equals(prezime)){
				return true;
			}
			else if(autorServiceBean.existsAutor(ime, prezime)){
				message = "The entered author already exists!";
				return false;	
			}		
		}
		
		return true;
	}
	
	private void initializeUIElements(){
		txtIme.setText("");
		txtIme.setBackground(Color.WHITE);
		txtPrezime.setText("");
		txtPrezime.setBackground(Color.WHITE);
	}
	
	private String message;
	
	private Autor autor;
	private AutorServiceBean autorServiceBean = new AutorServiceBean();
	
	private JPanel panel;
	private JTextField txtIme = new JTextField(20);
	private JTextField txtPrezime = new JTextField(30);

}
