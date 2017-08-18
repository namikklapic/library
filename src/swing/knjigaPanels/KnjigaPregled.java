package swing.knjigaPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.KnjigaServiceBean;
import jpa.Knjiga;
import jpa.Korisnik;
import tableModel.KnjigaTableModel;

public class KnjigaPregled extends JFrame {
	
	
	public KnjigaPregled(Boolean canEdit, Korisnik k){
		
		currUser = k;
		setTitle("Pregled knjiga");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
		
		JScrollPane scrollPane = new JScrollPane();
		
		KnjigaTableModel model = new KnjigaTableModel(knjigaServiceBean.getAllKnjige());
		JTable table = new JTable(model);
		String editBtnName;
		if(canEdit)
			editBtnName = "Edit";
		else
			editBtnName = "View details";
		JButton edit = new JButton(editBtnName);
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				int selectedRow = table.getSelectedRow();
				if(selectedRow > -1){
					Knjiga k = model.getKnjiga(table.getSelectedRow());
					NovaKnjiga nk = new NovaKnjiga(k, canEdit, currUser);
					nk.prikazi();
				}
			}
		});
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(edit);
		
		add(panel);
			
	}
	public JMenuItem getMenuItem(JPanel parent){
		JMenuItem item = new JMenuItem("Book review");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(true);
			}
		});
		return item;
	}
	private JPanel panel;
	private KnjigaServiceBean knjigaServiceBean = new KnjigaServiceBean();
	private Korisnik currUser;
	
}
