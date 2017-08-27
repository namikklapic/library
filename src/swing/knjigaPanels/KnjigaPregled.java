package swing.knjigaPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bussines.AutorKnjigaServiceBean;
import bussines.KnjigaServiceBean;
import bussines.LiteraturaServiceBean;
import bussines.PredmetServiceBean;
import bussines.VrstaKnjigeServiceBean;
import bussines.PrimjerakServiceBean;
import jpa.Knjiga;
import jpa.Korisnik;
import jpa.VrstaKnjige;
import swing.autorPanels.AutorPregled;
import jpa.Predmet;
import tableModel.KnjigaTableModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class KnjigaPregled extends JFrame {
	
	
	public KnjigaPregled(Boolean canEdit, Korisnik k){
		
		currUser = k;
		setTitle("Books overview");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 600;
		int sirinaProzora = 1000;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(1000, 600);
		setResizable(false);
		
		searchClicked = false;
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setBounds(12, 13, 976, 574);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		scrollPane.getViewport().setBackground(new Color(255, 255, 255,20));
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 0, 758, 574);
		model = new KnjigaTableModel();
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
		table.getTableHeader().setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(255, 255, 255,150));
		panel.setLayout(null);
		
		searchCriteriaLabel = new JLabel("Choose search criteria: ");
		searchCriteriaLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		searchCriteriaLabel.setBounds(770, 13, 198, 28);
		panel.add(searchCriteriaLabel);
		
		txtSearchFilter = new JTextField(10);
		txtSearchFilter.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSearchFilter.setBackground(Color.WHITE);
			}
		});
		txtSearchFilter.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtSearchFilter.setBounds(770, 95, 198, 28);
		//ovdje Ismare dodaj action da se boja promijeni kad kliknes na polje
	
		cbSearchFilters = new JComboBox<String>();
		cbSearchFilters.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		cbSearchFilters.setBounds(770, 54, 198, 28);
		cbSearchFilters.addItem("Book title");
		cbSearchFilters.addItem("Author");
		cbSearchFilters.addItem("Publisher");
		cbSearchFilters.addItem("Subject");
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
		searchBtn.setBounds(802, 139, 145, 37);
		
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				searchClicked = true;
				refreshTable();
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
		
		
		String editBtnName;
		if(canEdit)
			editBtnName = "Edit";
		else
			editBtnName = "View details";
		JButton edit = new JButton(editBtnName);
		edit.setBounds(802, 346, 145, 37);
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				int selectedRow = table.getSelectedRow();
				if(selectedRow > -1){
					Knjiga k = ((KnjigaTableModel) table.getModel()).getKnjiga(table.getSelectedRow());
					NovaKnjiga nk = new NovaKnjiga(k, canEdit, currUser);
					nk.prikazi();
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
		
		class CheckboxAction extends AbstractAction {
		    public CheckboxAction(String text) {
		        super(text);
		    }
				 
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        /*(JCheckBox chkbox = (JCheckBox) e.getSource();
		        if (chkbox.isSelected()) {
		        	KnjigaTableModel availableBooks = new KnjigaTableModel(primjerakServiceBean.getAllAvailableKnjige());
			        table.setModel(availableBooks);
		        } else {	
			        KnjigaTableModel books = new KnjigaTableModel(knjigaServiceBean.getAllKnjige());
		        	table.setModel(books);
			        }
			    }*/
		    	refreshTable();
		}
		}
				
		onlyAvail = new JCheckBox(new CheckboxAction("Show only available books"));
		onlyAvail.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		onlyAvail.setBounds(780, 204, 188, 25);
		onlyAvail.setSelected(false);
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(edit);
		panel.add(onlyAvail);
		
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
		cancel.setBounds(802, 409, 145, 37);
		
		getContentPane().setLayout(null);
		panel.add(cancel);
		
		getContentPane().add(panel);
			
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AutorPregled.class.getResource("/swing/images/background.jpg")));
		lblNewLabel.setBounds(0, 0, 1000, 800);
		getContentPane().add(lblNewLabel);
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
	
	private void displayMessageDialogBox(){
		if(this.isVisible() ==  false || searchClicked == false)
			return;
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	public void refreshTable() {
		String criteria = (String)cbSearchFilters.getSelectedItem();
		String filter = txtSearchFilter.getText();
		boolean success = true;
		
		if(!criteria.equals("Show all") && (filter.equals(null) || filter.equals(""))){
			txtSearchFilter.setBackground(Color.LIGHT_GRAY);
			message = "Please, enter the value in search filter!";
			success = false;
			displayMessageDialogBox();
		}
		else{
			if(criteria.equals("Book title")) {
				if(onlyAvail.isSelected())
					model = new KnjigaTableModel(primjerakServiceBean.getAllAvailableKnjigeByNaslov(filter));
				else
					model = new KnjigaTableModel(knjigaServiceBean.getKnjigaByNaslov(filter));
			}
			
			else if(criteria.equals("Author")){
				String[] spliter = filter.split(" ");
				if(spliter.length != 2){
					txtSearchFilter.setBackground(Color.LIGHT_GRAY);
					message = "Please, enter the author's first and last name!";
					success = false;
					displayMessageDialogBox();
				}else{
					if(onlyAvail.isSelected())
						model = new KnjigaTableModel(primjerakServiceBean.getAllAvailableKnjigeByAutor(spliter[0], spliter[1]));
					else
						model = new KnjigaTableModel(autorKnjigaServiceBean.getKnjigeByAutor(spliter[0], spliter[1]));		
				}		
			}
			else if(criteria.equals("Publisher")){
				if(onlyAvail.isSelected())
					model = new KnjigaTableModel(primjerakServiceBean.getAllAvailableKnjigeByIzdavac(filter));
				else
					model = new KnjigaTableModel(knjigaServiceBean.getKnjigaByIzdavac(filter));	
			}
			else if(criteria.equals("Subject")){
				if(onlyAvail.isSelected())
					model = new KnjigaTableModel(primjerakServiceBean.getAllAvailableKnjigeByPredmet(filter));
				else
					model = new KnjigaTableModel(literaturaServiceBean.getLiteraturaByPredmet(filter));	
			}
			else {
				if(onlyAvail.isSelected())
					model = new KnjigaTableModel(primjerakServiceBean.getAllAvailableKnjige());
				else
					model = new KnjigaTableModel(knjigaServiceBean.getAllKnjige());
			}
			
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
	
	private void clearUIElements(){
		txtSearchFilter.setText("");
		txtSearchFilter.setBackground(Color.WHITE);
		cbSearchFilters.setSelectedItem("Book title");
		txtSearchFilter.setEditable(true);
		table.getSelectionModel().clearSelection();
		onlyAvail.setSelected(false);
		
		model = new KnjigaTableModel();
		table.setModel(model);
	}
	
	public void prikazi() { setVisible(true); }
	
	private JPanel panel;
	private String message;
	private Korisnik currUser;
	private JButton cancel;
	private KnjigaTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	
	private JLabel searchCriteriaLabel;
	private JComboBox<String> cbSearchFilters;
	private JTextField txtSearchFilter;
	private JButton searchBtn;
	private JCheckBox onlyAvail;
	
	private Boolean searchClicked; //needed for msgbox search bug on adding anything
	
	private KnjigaServiceBean knjigaServiceBean = new KnjigaServiceBean();
	private PredmetServiceBean predmetServiceBean = new PredmetServiceBean();
	private AutorKnjigaServiceBean autorKnjigaServiceBean = new AutorKnjigaServiceBean();
	private LiteraturaServiceBean literaturaServiceBean = new LiteraturaServiceBean();
	private PrimjerakServiceBean primjerakServiceBean = new PrimjerakServiceBean();
}
