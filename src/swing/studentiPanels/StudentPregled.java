package swing.studentiPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.StudentServiceBean;
import jpa.Student;
import tableModel.StudentTableModel;

public class StudentPregled extends JPanel {
	public StudentPregled() {
		panel = this;
		
		JScrollPane scrollPane = new JScrollPane();
		
		StudentTableModel model = new StudentTableModel(studentServiceBean.getAllStudent());
		JTable table = new JTable(model);
		
		JButton edit = new JButton("Uredi");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Student s = model.getStudent(table.getSelectedRow());
				NoviStudent ns = new NoviStudent(s);
				ns.prikazi();
			}
		});
		
		scrollPane.setViewportView(table);
		add(scrollPane);
		add(edit);
	}
	
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem item = new JMenuItem("Pregled studenata");
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
	
	private StudentServiceBean studentServiceBean = new StudentServiceBean();
	

}
