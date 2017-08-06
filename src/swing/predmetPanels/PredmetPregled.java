package swing.predmetPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.PredmetServiceBean;
import jpa.Predmet;
import tableModel.PredmetTableModel;

public class PredmetPregled extends JPanel {
	
	
	public PredmetPregled() {
		panel = this;
		//add(new JLabel("Panel za pregled predmeta"));
		
		JScrollPane scrollPane = new JScrollPane();
		
		PredmetTableModel model = new PredmetTableModel(predmetServiceBean.getAllPredmeti());
		JTable table = new JTable(model);
		
		JButton edit = new JButton("Uredi");
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				Predmet p = model.getPredmet(table.getSelectedRow());
				NoviPredmet np = new NoviPredmet(p);
				np.prikazi();
			}
		});
		
		scrollPane.setViewportView(table);
		add(scrollPane);
		add(edit);
		
	}
	
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem menuItem = new JMenuItem("Pregled predmeta");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				parent.removeAll();
				parent.add(panel);
				parent.revalidate();
				parent.repaint();
			}
		});
		return menuItem;
	}

	private JPanel panel;
	private PredmetServiceBean predmetServiceBean = new PredmetServiceBean();
}
