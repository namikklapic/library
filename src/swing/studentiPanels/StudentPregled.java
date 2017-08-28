package swing.studentiPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
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
import swing.autorPanels.AutorPregled;
import tableModel.StudentTableModel;
import javax.swing.SwingConstants;

public class StudentPregled extends JFrame {
	
	public StudentPregled() {
		
		setTitle("Students review");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 600;
		int sirinaProzora = 1300;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(1300, 600);
		setResizable(false);
		
		searchClicked = false;
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setBounds(12, 13, 1276, 574);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		scrollPane.getViewport().setBackground(new Color(255, 255, 255,20));
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 68, 954, 506);
		model = new StudentTableModel(studentServiceBean.getAllStudent());
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getContentPane().repaint();
				getContentPane().revalidate();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				getContentPane().repaint();
				getContentPane().revalidate();
			}
		});
		table.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		table.setRowHeight(30);
		table.setBackground(new Color(255, 255, 255,150));
		table.getTableHeader().setFont(new Font("Segoe UI Emoji", Font.PLAIN, 17));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(255, 255, 255,150));
		table.getTableHeader().setEnabled(false);
		panel.setLayout(null);
		
		searchCriteriaLabel = new JLabel("Choose search criteria: ");
		searchCriteriaLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		searchCriteriaLabel.setBounds(1024, 70, 198, 28);
		panel.add(searchCriteriaLabel);
		
		txtSearchFilter = new JTextField(10);
		txtSearchFilter.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtSearchFilter.setBounds(1024, 152, 198, 28);
		txtSearchFilter.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSearchFilter.setBackground(Color.WHITE);
			}
		});

		cbSearchFilters = new JComboBox<String>();
		cbSearchFilters.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		cbSearchFilters.setBounds(1024, 111, 198, 28);
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
		searchBtn.setBounds(1076, 193, 97, 37);
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				searchClicked = true;
				refreshTable(false);
				searchClicked = false;
			}
		});
		searchBtn.setBorder(null);
		searchBtn.setFocusPainted(false);
		searchBtn.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		searchBtn.setForeground(new Color(255, 255, 255));
		searchBtn.setBackground(Color.DARK_GRAY);
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				searchBtn.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				searchBtn.setBackground(Color.DARK_GRAY);
			}
		});
		panel.add(searchBtn);
		
		edit = new JButton("Edit");
		edit.setBounds(1076, 346, 97, 37);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow > -1){
					Student s = model.getStudent(table.getSelectedRow());
					NoviStudent ns = new NoviStudent(s);
					ns.prikazi();
				}else{
					searchClicked = true;
					message = "No item selected!";
					displayMessageDialogBox();
					searchClicked = false;
				}	
			}
		});
		edit.setBorder(null);
		edit.setFocusPainted(false);
		edit.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		edit.setForeground(new Color(255, 255, 255));
		edit.setBackground(Color.DARK_GRAY);
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				edit.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				edit.setBackground(Color.DARK_GRAY);
			}
		});
		
		cancel = new JButton("Cancel");
		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cancel.setBackground(Color.DARK_GRAY);
				clearUIElements();
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				cancel.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				cancel.setBackground(Color.DARK_GRAY);
			}
		});
		cancel.setForeground(Color.WHITE);
		cancel.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		cancel.setFocusPainted(false);
		cancel.setBorder(null);
		cancel.setBackground(Color.DARK_GRAY);
		cancel.setBounds(1076, 409, 97, 37);
		getContentPane().setLayout(null);
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(edit);
		panel.add(cancel);
		
		getContentPane().add(panel);
		
		lblViewAllStudents = new JLabel("View all students");
		lblViewAllStudents.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewAllStudents.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		lblViewAllStudents.setBounds(460, 23, 355, 32);
		panel.add(lblViewAllStudents);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AutorPregled.class.getResource("/swing/images/background.jpg")));
		lblNewLabel.setBounds(0, 0, 1300, 800);
		getContentPane().add(lblNewLabel);
		
		anulirajBodove = new JButton("Reset negative points");
		anulirajBodove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				
			}
		});
		anulirajBodove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				anulirajBodove.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				anulirajBodove.setBackground(Color.DARK_GRAY);
			}
		});
		panel.add(anulirajBodove);
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
		if(this.isVisible() ==  false || searchClicked == false)
			return;
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private void clearUIElements(){
		txtSearchFilter.setText("");
		txtSearchFilter.setBackground(Color.WHITE);
		cbSearchFilters.setSelectedItem("Book title");
		txtSearchFilter.setEditable(true);
		
		table.getSelectionModel().clearSelection();
		model = new StudentTableModel(studentServiceBean.getAllStudent());
		table.setModel(model);
	}
	
	public void refreshTable(boolean isAutomatic) {
		String criteria = (String)cbSearchFilters.getSelectedItem();
		String filter = txtSearchFilter.getText();
		boolean success = true;
		
		if(!isAutomatic && !criteria.equals("Show all") && (filter.equals(null) || filter.equals(""))){
			txtSearchFilter.setBackground(Color.LIGHT_GRAY);
			message = "Please, enter the value in search filter!";
			displayMessageDialogBox();
		}
		else{
			if(!isAutomatic && criteria.equals("Student number"))
				model = new StudentTableModel(studentServiceBean.getStudentByIndexNumber(filter));
			
			else if(!isAutomatic && criteria.equals("Full name")){
				String[] spliter = filter.split(" ");
				if(spliter.length != 2){
					txtSearchFilter.setBackground(Color.LIGHT_GRAY);
					message = "Please, enter the student's first and last name!";
					displayMessageDialogBox();
				}else{							
					model = new StudentTableModel(studentServiceBean.getStudentByFullName(spliter[0], spliter[1]));
				}		
			}
			else if(!isAutomatic && criteria.equals("Negative points")){
				try{
					int bodovi = Integer.parseInt(filter);
					model = new StudentTableModel(studentServiceBean.getStudentByNegativePoints(bodovi));
				}catch(Exception e){
					txtSearchFilter.setBackground(Color.LIGHT_GRAY);
					message = "Invalid value for negative points!";
					success = false;
					displayMessageDialogBox();
				}	
			}
			else
				model = new StudentTableModel(studentServiceBean.getAllStudent());
			
			if(success){
				table.setModel(model);
				if(table.getRowCount() == 0){
					message = "No result found!";
					getContentPane().repaint();
					getContentPane().revalidate();
					displayMessageDialogBox();
				}
			}
		}
		
		getContentPane().repaint();
		getContentPane().revalidate();
	}
	
	public void prikazi() { setVisible(true); }
	
	private JPanel panel;
	private String message;
	private boolean searchClicked;
	
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
	private JLabel lblViewAllStudents;
	
	private JButton anulirajBodove;
}
