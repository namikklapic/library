package swing.predmetPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import bussines.PredmetServiceBean;
import jpa.Predmet;
import tableModel.PredmetTableModel;
import tableModel.StudentTableModel;

public class PredmetPregled extends JFrame {
	
	public PredmetPregled() {
		
		setTitle("Subject review");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
		
		scrollPane = new JScrollPane();
		model = new PredmetTableModel();
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		searchCriteriaLabel = new JLabel("Choose search criteria: ");
		panel.add(searchCriteriaLabel);
		
		txtSearchFilter = new JTextField(10);
		
		cbSearchFilters = new JComboBox<String>();
		cbSearchFilters.addItem("Title");
		cbSearchFilters.addItem("Short title");
		cbSearchFilters.addItem("Semester");
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
				refreshTable();
			}
		});
		panel.add(searchBtn);
		
		edit = new JButton("Edit");
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				int selectedRow = table.getSelectedRow();
				if(selectedRow > -1){
					Predmet p = model.getPredmet(table.getSelectedRow());
					NoviPredmet np = new NoviPredmet(p);
					np.prikazi();
				}else{
					message = "No item selected!";
					displayMessageDialogBox();
				}	
			}
		});
		
		cancel = new JButton("Cancel");
		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				clearUIElements();
				dispose();
			}
		});
		
		panel.add(scrollPane);
		panel.add(edit);
		panel.add(cancel);
		
		add(panel);
		
	}
	
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem menuItem = new JMenuItem("Subject review");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(true);
			}
		});
		return menuItem;
	}
	
	public void refreshTable() {
		String criteria = (String)cbSearchFilters.getSelectedItem();
		String filter = txtSearchFilter.getText();
		boolean success = true;
		
		if(!criteria.equals("Show all") && (filter.equals(null) || filter.equals(""))){
			txtSearchFilter.setBackground(Color.LIGHT_GRAY);
			message = "Please, enter the value in search filter!";
			displayMessageDialogBox();
		}
		else{
			if(criteria.equals("Title"))
				model = new PredmetTableModel(predmetServiceBean.getPredmetiByNaziv(filter));
			
			else if(criteria.equals("Short title"))				
				model = new PredmetTableModel(predmetServiceBean.getPredmetiBySkracenica(filter));	
	
			else if(criteria.equals("Semester")){
				try{
					int sem = Integer.parseInt(filter);
					if(sem < 1 || sem > 8){
						message = "Invalid value for semester!";
						success = false;
						displayMessageDialogBox();
					}else
						model = new PredmetTableModel(predmetServiceBean.getPredmetiBySemestar(sem));
				}catch(Exception e){
					txtSearchFilter.setBackground(Color.LIGHT_GRAY);
					message = "Invalid value for semester!";
					success = false;
					displayMessageDialogBox();
				}
			}
			else
				model = new PredmetTableModel(predmetServiceBean.getAllPredmeti());
			
			if(success){
				table.setModel(model);
				if(table.getRowCount() == 0){
					message = "No result found!";
					displayMessageDialogBox();
				}
			}	
		}
		
		getContentPane().repaint();
		getContentPane().revalidate();
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private void clearUIElements(){
		txtSearchFilter.setText("");
		txtSearchFilter.setBackground(Color.WHITE);
		cbSearchFilters.setSelectedItem("Title");
		txtSearchFilter.setEditable(true);
		
		table.getSelectionModel().clearSelection();
		model = new PredmetTableModel();
		table.setModel(model);
	}

	private JPanel panel;
	private String message;
	
	private JTable table;
	private JScrollPane scrollPane;
	private PredmetTableModel model;
	
	private JLabel searchCriteriaLabel;
	private JComboBox<String> cbSearchFilters;
	private JTextField txtSearchFilter;
	private JButton searchBtn;
	private JButton edit;
	private JButton cancel;
	
	private PredmetServiceBean predmetServiceBean = new PredmetServiceBean();
}
