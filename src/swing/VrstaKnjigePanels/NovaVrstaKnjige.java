package swing.VrstaKnjigePanels;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;

import bussines.VrstaKnjigeServiceBean;
import jpa.VrstaKnjige;
import swing.autorPanels.NoviAutor;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NovaVrstaKnjige extends JFrame {
	
	public NovaVrstaKnjige() {
		setTitle("New book type");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 400;
		int sirinaProzora = 400;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(400, 300);
		
		panel = new JPanel();
		panel.setBounds(12, 16, 376, 271);
		panel.setBackground(new Color(255,255,255,70));
		panel.setLayout(null);
		
		vrstaKnjige = null;
		
		lbVrstaKnjige = new JLabel("Book type: ");
		lbVrstaKnjige.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lbVrstaKnjige.setBounds(12, 107, 84, 22);
		panel.add(lbVrstaKnjige);
		
		txtVrstaKnjige = new JTextField(10);
		txtVrstaKnjige.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtVrstaKnjige.setBackground(Color.WHITE);
			}
		});
		txtVrstaKnjige.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtVrstaKnjige.setBounds(122, 103, 242, 31);
		panel.add(txtVrstaKnjige);
		
		potvrdi = new JButton("Save");
		potvrdi.setBorder(null);
		potvrdi.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		potvrdi.setForeground(new Color(255, 255, 255));
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
		potvrdi.setBackground(Color.DARK_GRAY);
		potvrdi.setBounds(80, 214, 97, 41);
		potvrdi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(isValidVrstaKnjige())
					saveVrstaKnjige();
				displayMessageDialogBox();
			}
		});
		panel.add(potvrdi);
		
		ponisti = new JButton("Cancel");
		ponisti.setBorder(null);
		ponisti.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		ponisti.setForeground(new Color(255, 255, 255));
		ponisti.setBackground(Color.DARK_GRAY);
		ponisti.setBounds(202, 214, 97, 41);
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
		ponisti.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				initializeUIElements();
				dispose();
				ponisti.setBackground(Color.DARK_GRAY);
			}
		});
		panel.add(ponisti);
		
		
		getContentPane().add(panel);
		
		JLabel backgroundPicture = new JLabel("");
		backgroundPicture.setIcon(new ImageIcon(NoviAutor.class.getResource("/swing/images/background.jpg")));
		backgroundPicture.setBounds(0, 0, 400, 300);
		getContentPane().add(backgroundPicture);
		getContentPane().setLayout(null);
	}
	
	public NovaVrstaKnjige(VrstaKnjige vk){
		this();
		vrstaKnjige = vk;
		txtVrstaKnjige.setText(vk.getNazivVrste());
		setTitle("Edit book type");
		potvrdi.setText("Save");
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem item = new JMenuItem("New book type");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				prikazi();
			}
		});
		return item;
	}
	
	public void prikazi(){
		setVisible(true);
	}
	
	public void saveVrstaKnjige(){
		
		int id = vrstaKnjige != null ? vrstaKnjige.getId() : vrstaKnjigeServiceBean.getCount() + 1;
		vrstaKnjigeServiceBean.save(new VrstaKnjige(id, txtVrstaKnjige.getText()));
	
		if(vrstaKnjige != null)
			message = "Changes were successfully saved!";
		else
			message = "The book type has been successfully saved!";
	}
	
	private boolean isValidVrstaKnjige(){
		if(vrstaKnjige == null){
			String vk = txtVrstaKnjige.getText();
			if(vrstaKnjigeServiceBean.existsVrstaKnjige(vk)){
				message = "The entered book type already exists!";
				return false;
			}
			if(vk.equals(null) || vk.equals("")){
				txtVrstaKnjige.setBackground(Color.LIGHT_GRAY);
				message = "Book type title is missing!";
				return false;
			}
		}
		return true;
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private void initializeUIElements(){
		txtVrstaKnjige.setText("");
		txtVrstaKnjige.setBackground(Color.WHITE);
	}
	
	private VrstaKnjigeServiceBean vrstaKnjigeServiceBean = new VrstaKnjigeServiceBean();
	
	private VrstaKnjige vrstaKnjige;
	private String message;
	
	private JPanel panel;
	private JLabel lbVrstaKnjige;
	private JTextField txtVrstaKnjige;
	private JButton potvrdi;
	private JButton ponisti;
}
