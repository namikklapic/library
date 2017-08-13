package swing.VrstaKnjigePanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.VrstaKnjigeServiceBean;
import jpa.VrstaKnjige;
import tableModel.VrstaKnjigeTableModel;

public class VrstaKnjigePregled extends JPanel {
	
	public VrstaKnjigePregled() {
		panel = this;
		//add(new JLabel("vrsta knjige pregled panel"));
		
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
		add(scrollPane);
		add(edit);
		
	}
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem item = new JMenuItem("Pregled vrsta knjige");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				parent.removeAll();
				parent.add(panel);
				parent.repaint();
				parent.revalidate();
			}
		});
		return item;
	}
	
	private JPanel panel;
	private VrstaKnjigeServiceBean vrstaKnjigeServiceBean = new VrstaKnjigeServiceBean();

}
