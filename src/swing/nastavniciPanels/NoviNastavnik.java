package swing.nastavniciPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.NastavnikServiceBean;
import jpa.Nastavnik;
import jpa.NastavnikPredmet;
import jpa.NastavnikPredmetPK;

import util.Lookup;

public class NoviNastavnik extends JFrame{
	public NoviNastavnik(){
		setTitle("Novi nastavnik");
		setSize(500, 500);
		
		JPanel panel = new JPanel();
		panel.setSize(500, 500);
		
		JLabel ime = new JLabel("Ime nastavnika: ");
		panel.add(ime);
		panel.add(txtIme);
		
		JLabel prezime = new JLabel("Prezime nastavnika: ");
		panel.add(prezime);
		panel.add(txtPrezime);
		
		JLabel zvanje = new JLabel("Akademsko zvanje: ");
		panel.add(zvanje);
		panel.add(txtZvanje); //moze i ovo biti dropdown
			
		JLabel predmeti = new JLabel("Predmeti: ");
		panel.add(predmeti);
		//panel.add dropdown listu sa predmetima
		
		add(panel);
	}
	
	public JMenuItem getMenuItem(){
		JMenuItem item = new JMenuItem("Novi Nastavnik");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				prikazi();
			}
		});
		return item;
	}
	
	public void prikazi(){
		setVisible(true);
	}
	
	private void saveNastavnik(){
		
	}
	
	private NastavnikServiceBean nastavnikServiceBean = new NastavnikServiceBean();
	
	private JTextField txtIme = new JTextField(30);
	private JTextField txtPrezime = new JTextField(30);
	private JTextField txtZvanje = new JTextField(15);
}