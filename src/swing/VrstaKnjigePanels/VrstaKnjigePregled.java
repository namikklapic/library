package swing.VrstaKnjigePanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.VrstaKnjigeServiceBean;
import jpa.VrstaKnjige;
import tableModel.VrstaKnjigeTableModel;

public class VrstaKnjigePregled extends JFrame {
	
	public VrstaKnjigePregled() {
		
		setTitle("Pregled vrsta knjige");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
		
		JScrollPane scrollPane = new JScrollPane();
		
		VrstaKnjigeTableModel model = new VrstaKnjigeTableModel(vrstaKnjigeServiceBean.getAllVrstaKnjige());
		JTable table = new JTable(model);
		
		JButton edit = new JButton("Uredi");
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				VrstaKnjige vk = model.getVrstaKnjige(table.getSelectedRow());
				NovaVrstaKnjige nvk = new NovaVrstaKnjige(vk);
				nvk.prikazi();
			}
		});
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(edit);
		
		add(panel);
		
	}
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem item = new JMenuItem("Book types review");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(true);
			}
		});
		return item;
	}
	
	private JPanel panel;
	private VrstaKnjigeServiceBean vrstaKnjigeServiceBean = new VrstaKnjigeServiceBean();

}
