package swing.VrstaKnjigePanels;

import java.awt.Color;
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

import bussines.VrstaKnjigeServiceBean;
import jpa.VrstaKnjige;

public class NovaVrstaKnjige extends JFrame {
	
	public NovaVrstaKnjige() {
		setTitle("New book type");
		setSize(300, 300);
		
		panel = new JPanel();
		panel.setSize(300, 300);
		
		lbVrstaKnjige = new JLabel("Book type: ");
		panel.add(lbVrstaKnjige);
		
		txtVrstaKnjige = new JTextField(10);
		panel.add(txtVrstaKnjige);
		
		potvrdi = new JButton("Save");
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
		ponisti.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				initializeUIElements();
				dispose();
			}
		});
		panel.add(ponisti);
		
		panel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent event){
				txtVrstaKnjige.setBackground(Color.WHITE);
			}
		});
		
		add(panel);
	}
	
	public NovaVrstaKnjige(VrstaKnjige vk){
		this();
		txtVrstaKnjige.setText(vk.getNazivVrste());
		setTitle("Edit book type");
		potvrdi.setText("Save changes");
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
		vrstaKnjigeServiceBean.save(new VrstaKnjige(vrstaKnjigeServiceBean.getCount() + 1, txtVrstaKnjige.getText()));
		message = "The book type has been successfully saved!";
	}
	
	private boolean isValidVrstaKnjige(){
		String vk = txtVrstaKnjige.getText();
		if(vrstaKnjigeServiceBean.existsVrstaKnjige(vk)){
			message = "The entered book type already exists!";
			return false;
		}
		if(vk.equals(null) || vk.equals("")){
			txtVrstaKnjige.setBackground(Color.RED);
			message = "Book type title is missing!";
			return false;
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
	
	private String message;
	
	private JPanel panel;
	private JLabel lbVrstaKnjige;
	private JTextField txtVrstaKnjige;
	private JButton potvrdi;
	private JButton ponisti;
}
