package swing.knjigaPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
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
import jpa.Predmet;
import tableModel.KnjigaTableModel;

public class KnjigaPregled extends JFrame {
	
	
	public KnjigaPregled(Boolean canEdit, Korisnik k){
		
		currUser = k;
		setTitle("Pregled knjiga");
		setSize(800, 800);
		
		panel = new JPanel();
		panel.setSize(800, 800);
		
		scrollPane = new JScrollPane();
		model = new KnjigaTableModel();
		table = new JTable(model);
		
		searchCriteriaLabel = new JLabel("Choose search criteria: ");
		panel.add(searchCriteriaLabel);
		
		txtSearchFilter = new JTextField(10);
		//ovdje Ismare dodaj action da se boja promijeni kad kliknes na polje
	
		cbSearchFilters = new JComboBox<String>();
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
					if(criteria.equals("Book title"))
						model = new KnjigaTableModel(knjigaServiceBean.getKnjigaByNaslov(filter));
					
					else if(criteria.equals("Author")){
						String[] spliter = filter.split(" ");
						if(spliter.length != 2){
							txtSearchFilter.setBackground(Color.RED);
							message = "Please, enter the author's first and last name!";
							displayMessageDialogBox();
						}else{							
							model = new KnjigaTableModel(autorKnjigaServiceBean.getKnjigeByAutor(spliter[0], spliter[1]));
						}		
					}
					else if(criteria.equals("Publisher")){
						model = new KnjigaTableModel(knjigaServiceBean.getKnjigaByIzdavac(filter));
					}
					else if(criteria.equals("Subject")){
						model = new KnjigaTableModel(literaturaServiceBean.getLiteraturaByPredmet(filter));
					}
					else
						model = new KnjigaTableModel(knjigaServiceBean.getAllKnjige());
					
					table.setModel(model);
					if(table.getRowCount() == 0){
						message = "No result found!";
						displayMessageDialogBox();
					}
				}
			}
		});
		panel.add(searchBtn);
		
		
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
				}else{
					message = "No item selected!";
					displayMessageDialogBox();
				}
			}
		});
		
		class CheckboxAction extends AbstractAction {
		    public CheckboxAction(String text) {
		        super(text);
		    }
				 
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JCheckBox chkbox = (JCheckBox) e.getSource();
		        if (chkbox.isSelected()) {
		        	KnjigaTableModel availableBooks = new KnjigaTableModel(primjerakServiceBean.getAllAvailableKnjige());
			        table.setModel(availableBooks);
		        } else {	
			        KnjigaTableModel books = new KnjigaTableModel(knjigaServiceBean.getAllKnjige());
		        	table.setModel(books);
			        }
			    }
		}
				
		JCheckBox onlyAvail = new JCheckBox(new CheckboxAction("Show only available books"));
		onlyAvail.setSelected(false);
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(edit);
		panel.add(onlyAvail);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				clearUIElements();
				dispose();
			}
		});
		panel.add(cancel);
		
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
		
		model = new KnjigaTableModel();
		table.setModel(model);
	}
	
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
	
	private KnjigaServiceBean knjigaServiceBean = new KnjigaServiceBean();
	private PredmetServiceBean predmetServiceBean = new PredmetServiceBean();
	private AutorKnjigaServiceBean autorKnjigaServiceBean = new AutorKnjigaServiceBean();
	private LiteraturaServiceBean literaturaServiceBean = new LiteraturaServiceBean();
	private PrimjerakServiceBean primjerakServiceBean = new PrimjerakServiceBean();
}
