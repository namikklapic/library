package swing.autorPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.AutorServiceBean;
import jpa.Autor;
import tableModel.AutorTableModel;

public class AutorPregled extends JFrame {
	
	public AutorPregled() {
		
		setTitle("Pregled autora");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
	
		JScrollPane scrollPane = new JScrollPane();
		
		AutorTableModel model = new AutorTableModel(autorServiceBean.getAllAutor());
		JTable table = new JTable(model);
		
		JButton edit = new JButton("Uredi");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event){
				Autor a = model.getAutor(table.getSelectedRow());
				NoviAutor na = new NoviAutor(a);
				na.prikazi();
			}
		});
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(edit);
		
		add(panel);
		
		
	}
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem item = new JMenuItem("Pregled autora");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(true);
			}
		});
		return item;
	}
	
	private JPanel panel;
	private List<Autor> autori;
	private AutorServiceBean autorServiceBean = new AutorServiceBean();

}
