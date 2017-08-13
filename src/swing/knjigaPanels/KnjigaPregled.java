package swing.knjigaPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.KnjigaServiceBean;
import jpa.Knjiga;
import tableModel.KnjigaTableModel;

public class KnjigaPregled extends JPanel{
	
	
	public KnjigaPregled(){
		panel = this;
		//add(new JLabel("Knjiga pregled panel"));
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		KnjigaTableModel model = new KnjigaTableModel(knjigaServiceBean.getAllKnjige());
		JTable table = new JTable(model);
		
		JButton edit = new JButton("Uredi");
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				Knjiga k = model.getKnjiga(table.getSelectedRow());
				NovaKnjiga nk = new NovaKnjiga(k);
				nk.prikazi();
			}
		});
		
		scrollPane.setViewportView(table);
		add(scrollPane);
		add(edit);
		
		
	}
	public JMenuItem getMenuItem(JPanel parent){
		JMenuItem item = new JMenuItem("Pregled Knijga");
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
	private KnjigaServiceBean knjigaServiceBean = new KnjigaServiceBean();
	
}
