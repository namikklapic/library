package swing.izdavacPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;

import bussines.IzdavacServiceBean;
import jpa.Izdavac;
import tableModel.IzdavacTableModel;

public class IzdavacPregled extends JPanel{
	
	
	
	public IzdavacPregled() {
		panel  = this;
		IzdavacTableModel model = new IzdavacTableModel(izdavacServiceBean.getAllIzdavac());
		JTable table = new JTable(model);
		
		JButton edit = new JButton("Uredi");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Izdavac i = model.getIzdavac(table.getSelectedRow());
				NoviIzdavac ni = new NoviIzdavac(i);
				ni.prikazi();
			}
		});
		add(table);
		add(edit);
				
	}
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem item = new JMenuItem("Pregled izdavaca");
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
	private List<Izdavac> izdavaci;
	private IzdavacServiceBean izdavacServiceBean = new IzdavacServiceBean();

}
