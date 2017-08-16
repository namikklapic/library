package swing.studentiPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.StudentServiceBean;
import jpa.Student;
import tableModel.StudentTableModel;

public class StudentPregled extends JFrame {
	
	public StudentPregled() {
		
		setTitle("Pregled studenata");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
		
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
		panel.add(scrollPane);
		panel.add(edit);
		
		add(panel);
	}
	
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem item = new JMenuItem("Student review");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(true);
			}
		});
		return item;
	}
	
	private JPanel panel;
	private StudentServiceBean studentServiceBean = new StudentServiceBean();	

}
