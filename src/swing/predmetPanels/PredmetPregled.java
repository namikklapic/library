package swing.predmetPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.PredmetServiceBean;
import jpa.Predmet;
import tableModel.PredmetTableModel;

public class PredmetPregled extends JFrame {
	
	
	public PredmetPregled() {
		
		setTitle("Pregled predmeta");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
		
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
		panel.add(scrollPane);
		panel.add(edit);
		
		add(panel);
		
	}
	
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem menuItem = new JMenuItem("Pregled predmeta");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(true);
			}
		});
		return menuItem;
	}

	private JPanel panel;
	private PredmetServiceBean predmetServiceBean = new PredmetServiceBean();
}
