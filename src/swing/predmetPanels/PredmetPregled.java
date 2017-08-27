package swing.predmetPanels;

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

import bussines.PredmetServiceBean;
import jpa.Predmet;
import swing.autorPanels.AutorPregled;
import tableModel.PredmetTableModel;
import tableModel.StudentTableModel;

public class PredmetPregled extends JFrame {
	
	public PredmetPregled() {
		
		setTitle("Subject review");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 600;
		int sirinaProzora = 700;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(700, 600);
		setResizable(false);
		
		panel = new JPanel();
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255,150));
		panel_1.setBounds(12, 16, 676, 571);
		
		searchClicked = false;
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		scrollPane.getViewport().setBackground(new Color(255, 255, 255,20));
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 0, 422, 571);
		model = new PredmetTableModel(predmetServiceBean.getAll());
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
		scrollPane.setViewportView(table);
		panel_1.setLayout(null);
		
		searchCriteriaLabel = new JLabel("Choose search criteria: ");
		searchCriteriaLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		searchCriteriaLabel.setBounds(468, 26, 196, 22);
		panel_1.add(searchCriteriaLabel);
		
		txtSearchFilter = new JTextField(10);
		txtSearchFilter.setBounds(468, 121, 196, 22);
		txtSearchFilter.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSearchFilter.setBackground(Color.WHITE);
			}
		});
		
		cbSearchFilters = new JComboBox<String>();
		cbSearchFilters.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		cbSearchFilters.setBounds(468, 59, 196, 22);
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
		
		panel_1.add(cbSearchFilters);
		panel_1.add(txtSearchFilter);
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(521, 156, 97, 37);
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				searchClicked = true;
				refreshTable();
				searchClicked = false;
			}
		});
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
		panel_1.add(searchBtn);
		
		edit = new JButton("Edit");
		edit.setBounds(521, 320, 97, 37);
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				int selectedRow = table.getSelectedRow();
				if(selectedRow > -1){
					Predmet p = model.getPredmet(table.getSelectedRow());
					NoviPredmet np = new NoviPredmet(p);
					np.prikazi();
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
		cancel.setBackground(Color.DARK_GRAY);
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
		cancel.setBounds(521, 370, 97, 37);
		getContentPane().setLayout(null);
		
		panel_1.add(scrollPane);
		panel_1.add(edit);
		panel_1.add(cancel);
		
		getContentPane().add(panel_1);
		
		JLabel lblTypeToSearch = new JLabel("Type to search");
		lblTypeToSearch.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lblTypeToSearch.setBounds(468, 94, 196, 22);
		panel_1.add(lblTypeToSearch);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AutorPregled.class.getResource("/swing/images/background.jpg")));
		lblNewLabel.setBounds(0, 0, 800, 800);
		getContentPane().add(lblNewLabel);
		
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
		if(this.isVisible() ==  false || searchClicked == false)
			return;
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel_1, message);
	}
	
	private void clearUIElements(){
		txtSearchFilter.setText("");
		txtSearchFilter.setBackground(Color.WHITE);
		cbSearchFilters.setSelectedItem("Title");
		txtSearchFilter.setEditable(true);
		
		table.getSelectionModel().clearSelection();
		model = new PredmetTableModel(predmetServiceBean.getAll());
		table.setModel(model);
	}

	public void prikazi() { setVisible(true); }
	
	private JPanel panel;
	private JPanel panel_1;
	private String message;
	
	private Boolean searchClicked; //needed for msgbox search bug on adding anything
	
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
