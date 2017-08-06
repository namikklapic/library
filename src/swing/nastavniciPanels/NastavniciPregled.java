package swing.nastavniciPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.NastavnikServiceBean;
import jpa.Nastavnik;
import tableModel.NastavnikTableModel;

public class NastavniciPregled extends JPanel{
	
	
	
	public NastavniciPregled(){
		panel = this;
		//add(new JLabel("Nastavnici pregled panel"));
		
		JScrollPane scrollPane = new JScrollPane();
		
		NastavnikTableModel model = new NastavnikTableModel(nastavnikServiceBean.getAllNastavnik());
		JTable table = new JTable(model);
		
		JButton edit = new JButton("Uredi");
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				Nastavnik n = model.getNastavnik(table.getSelectedRow());
				NoviNastavnik nn = new NoviNastavnik(n);
				nn.prikazi();
			}
		});
		
		scrollPane.setViewportView(table);
		add(scrollPane);
		add(edit);
		
	}
	public JMenuItem getMenuItem(JPanel parent){
		JMenuItem item = new JMenuItem("Pregled nastavnika");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				parent.removeAll();
				parent.add(panel);
				parent.repaint();
				parent.revalidate();
			}
		});
		return item;
	}

	private JPanel panel;
	private NastavnikServiceBean nastavnikServiceBean = new NastavnikServiceBean();

}

