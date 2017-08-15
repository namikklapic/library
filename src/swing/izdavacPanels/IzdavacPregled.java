package swing.izdavacPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.IzdavacServiceBean;
import jpa.Izdavac;
import tableModel.IzdavacTableModel;

public class IzdavacPregled extends JFrame {
	
	public IzdavacPregled() {
		
		setTitle("Pregled izdavaca");
		setSize(800, 800);
		
		panel  = new JPanel();
		panel.setSize(800, 800);
		
		JScrollPane scrollPane = new JScrollPane();
		
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
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(edit);
		
		add(panel);
				
	}
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem item = new JMenuItem("Pregled izdavaca");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(true);
			}
		});
		return item;
	}
	
	private JPanel panel;
	private IzdavacServiceBean izdavacServiceBean = new IzdavacServiceBean();

}
