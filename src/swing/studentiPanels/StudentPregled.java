package swing.studentiPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bussines.StudentServiceBean;
import jpa.Student;
import tableModel.StudentTableModel;

public class StudentPregled extends JFrame {
	
	public StudentPregled() {
		
		setTitle("Students review");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
		
		scrollPane = new JScrollPane();
		model = new StudentTableModel();
		table = new JTable(model);
		
		searchCriteriaLabel = new JLabel("Choose search criteria: ");
		panel.add(searchCriteriaLabel);
		
		txtSearchFilter = new JTextField(10);
		//ovdje Ismare dodaj action da se boja promijeni kad kliknes na polje
	
		cbSearchFilters = new JComboBox<String>();
		cbSearchFilters.addItem("Student number");
		cbSearchFilters.addItem("Full name");
		cbSearchFilters.addItem("Negative points");
		cbSearchFilters.addItem("Show all");
		cbSearchFilters.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				String option = (String)cbSearchFilters.getSelectedItem();
				txtSearchFilter.setText("");
				txtSearchFilter.setBackground(Color.WHITE);
				if(option.equals("Show all"))		
					txtSearchFilter.setEditable(false);
				else
					txtSearchFilter.setEditable(true);
			}
		});
		panel.add(cbSearchFilters);
		panel.add(txtSearchFilter);
		
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				String criteria = (String)cbSearchFilters.getSelectedItem();
				String filter = txtSearchFilter.getText();
				
				if(!criteria.equals("Show all") && (filter.equals(null) || filter.equals(""))){
					txtSearchFilter.setBackground(Color.RED);
					message = "Please, enter the value in search filter!";
					displayMessageDialogBox();
				}
				else{
					if(criteria.equals("Student number"))
						model = new StudentTableModel(studentServiceBean.getStudentByIndexNumber(filter));
					
					else if(criteria.equals("Full name")){
						String[] spliter = filter.split(" ");
						if(spliter.length != 2){
							txtSearchFilter.setBackground(Color.RED);
							message = "Please, enter the student's first and last name!";
							displayMessageDialogBox();
						}else{							
							model = new StudentTableModel(studentServiceBean.getStudentByFullName(spliter[0], spliter[1]));
						}		
					}
					else if(criteria.equals("Negative points")){
						model = new StudentTableModel(studentServiceBean.getStudentByNegativePoints(Integer.parseInt(filter)));
					}
					else
						model = new StudentTableModel(studentServiceBean.getAllStudent());
					
					table.setModel(model);
					if(table.getRowCount() == 0){
						message = "No result found!";
						displayMessageDialogBox();
					}
				}
			}
		});
		panel.add(searchBtn);
		
		edit = new JButton("Edit");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow > -1){
					Student s = model.getStudent(table.getSelectedRow());
					NoviStudent ns = new NoviStudent(s);
					ns.prikazi();
				}else{
					message = "No item selected!";
					displayMessageDialogBox();
				}	
			}
		});
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				clearUIElements();
				dispose();
			}
		});
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(edit);
		panel.add(cancel);
		
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
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private void clearUIElements(){
		txtSearchFilter.setText("");
		txtSearchFilter.setBackground(Color.WHITE);
		cbSearchFilters.setSelectedItem("Book title");
		txtSearchFilter.setEditable(true);
		
		table.getSelectionModel().clearSelection();
		model = new StudentTableModel();
		table.setModel(model);
	}
	
	
	private JPanel panel;
	private String message;
	
	private JScrollPane scrollPane;
	private StudentTableModel model;
	private JTable table;
	private JButton edit;
	private JButton cancel;
	
	private JLabel searchCriteriaLabel;
	private JComboBox<String> cbSearchFilters;
	private JTextField txtSearchFilter;
	private JButton searchBtn;
	
	private StudentServiceBean studentServiceBean = new StudentServiceBean();	

}
