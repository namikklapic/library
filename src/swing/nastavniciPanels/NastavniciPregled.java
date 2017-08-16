package swing.nastavniciPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.NastavnikServiceBean;
import jpa.Nastavnik;
import tableModel.NastavnikTableModel;

public class NastavniciPregled extends JFrame{
	
	public NastavniciPregled(){
		
		setTitle("Pregled nastavnika");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
		
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
		panel.add(scrollPane);
		panel.add(edit);
		
		add(panel);
		
	}
	public JMenuItem getMenuItem(JPanel parent){
		JMenuItem item = new JMenuItem("Teacher review");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(true);
			}
		});
		return item;
	}

	private JPanel panel;
	private NastavnikServiceBean nastavnikServiceBean = new NastavnikServiceBean();

}

