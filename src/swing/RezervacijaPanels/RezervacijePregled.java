package swing.RezervacijaPanels;

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

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bussines.RezervacijaServiceBean;
import jpa.Rezervacija;
import jpa.RezervacijaPK;
import swing.posudbaPanels.NovaPosudba;
import swing.posudbaPanels.PosudbePregled;
import jpa.Korisnik;
import tableModel.RezervacijeTableModel;
import javax.swing.SwingConstants;

public class RezervacijePregled extends JFrame {
	
public RezervacijePregled() {
	
		panel = new JPanel();
		add(panel);
	}
	
public RezervacijePregled(Korisnik k){	
	setTitle("Pregled rezervacija");
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension velicinaEkrana = kit.getScreenSize();
	int visinaProzora = 600;
	int sirinaProzora = 1300;
	setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
	setUndecorated(true);
	setSize(1300, 600);
	setResizable(false);
	
	panel = new JPanel();	
	panel.setBackground(new Color(255, 255, 255,150));
	panel.setBounds(12, 13, 1276, 574);
	panel.setLayout(null);
	
	scrollPane = new JScrollPane();
	scrollPane.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
	scrollPane.getViewport().setBackground(new Color(255, 255, 255,20));
	scrollPane.setOpaque(false);
	scrollPane.setBounds(0, 49, 1276, 385);
	model = new RezervacijeTableModel(rezervacijaServiceBean.getAllRezervacije());
	if(k == null) //it is bibliotekar panel, and we want all users
		model = new RezervacijeTableModel(rezervacijaServiceBean.getAllRezervacije());
	else //it is korisnik panel, show only for specific user 
		model = new RezervacijeTableModel(rezervacijaServiceBean.getRezervacijeByKorisnik(k));
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
	currUser = k;
	
	confirm = new JButton("Create a loan");
	confirm.setBounds(433, 524, 197, 37);
	confirm.setForeground(Color.WHITE);
	confirm.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
	confirm.setFocusPainted(false);
	confirm.setBorder(null);
	confirm.setBackground(Color.DARK_GRAY);
	
	confirm.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent event){
			if(table.getSelectedRow() > -1) {
				Rezervacija r = ((RezervacijeTableModel) table.getModel()).getRezervacija(table.getSelectedRow());
				if(r.getIsConfirmed() == false) {
					NovaPosudba np = new NovaPosudba(r);
					np.prikazi();
				}
				else
					JOptionPane.showMessageDialog(confirm.getParent(), "That reservation is already confirmed.", "Oops!", JOptionPane.ERROR_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(confirm.getParent(), "No item selected!.", "Oops!", JOptionPane.ERROR_MESSAGE);
			}
		}
	});
	
	confirm.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent arg0) {
			confirm.setBackground(Color.GRAY);			
			}
		@Override
		public void mouseExited(MouseEvent e) {
			confirm.setBackground(Color.DARK_GRAY);
		}
	});
	
	class CheckboxAction extends AbstractAction {
	    public CheckboxAction(String text) {
	        super(text);
	    }
	 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	//refreshTable(false);
	    }
	}
	
	onlyActive = new JCheckBox(new CheckboxAction("Show only active reservations"));
	onlyActive.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
	onlyActive.setBounds(988, 461, 280, 33);
	onlyActive.setSelected(false);
	
	scrollPane.setViewportView(table);
	panel.add(scrollPane);
	if(currUser == null)
		panel.add(confirm);
	panel.add(onlyActive);
	
	getContentPane().add(panel);
	
	//rezervacija review ------- search criteria -------------------------------------------------------
	searchCriteriaLabel = new JLabel("Choose search criteria : ");
	searchCriteriaLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
	searchCriteriaLabel.setBounds(10, 461, 191, 33);
	if(currUser == null)
		panel.add(searchCriteriaLabel);
	
	txtSearchFilter = new JTextField(10);
	txtSearchFilter.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
	txtSearchFilter.setBounds(552, 461, 238, 33);
	txtSearchFilter.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent arg0) {
			txtSearchFilter.setBackground(Color.WHITE);
		}
	});
	
	cbSearchFilters = new JComboBox<String>();
	cbSearchFilters.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
	cbSearchFilters.setBounds(197, 461, 191, 33);
	cbSearchFilters.addItem("User");
	cbSearchFilters.addItem("Book");
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
	if(currUser == null){
		panel.add(cbSearchFilters);
		panel.add(txtSearchFilter);
	}
	
	searchBtn = new JButton("Search");
	searchBtn.setBounds(802, 459, 140, 37);
	searchBtn.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent event){
//			searchClicked = true;
			refreshTable(false);
//			searchClicked = false;
		}
	});
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
	searchBtn.setForeground(Color.WHITE);
	searchBtn.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
	searchBtn.setFocusPainted(false);
	searchBtn.setBorder(null);
	searchBtn.setBackground(Color.DARK_GRAY);
	if(currUser == null)
		panel.add(searchBtn);
	
	//end-----------------------------------------------------------------------------------------------
	
	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setIcon(new ImageIcon(PosudbePregled.class.getResource("/swing/images/background.jpg")));
	lblNewLabel.setBounds(0, 0, 1346, 600);
	getContentPane().add(lblNewLabel);
	
	JButton cancel = new JButton("Cancel");
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
	cancel.setBounds(643, 524, 197, 37);
	
	
	panel.add(cancel);
	
	JLabel lblViewAllReservations = new JLabel("View all reservations");
	lblViewAllReservations.setHorizontalAlignment(SwingConstants.CENTER);
	lblViewAllReservations.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
	lblViewAllReservations.setBounds(460, 13, 355, 32);
	panel.add(lblViewAllReservations);
	
	lblTypeToSerach = new JLabel("Type to search :");
	lblTypeToSerach.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
	lblTypeToSerach.setBounds(420, 461, 140, 33);
	if(currUser == null)
	panel.add(lblTypeToSerach);
}
	
	public JMenuItem getMenuItem(JPanel parent){
		JMenuItem item = new JMenuItem("Book reservations review");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(true);
			}
		});
		return item;
	}
	
	public void refreshTable(boolean isAutomatic) {
			boolean success = true;
			
			if(currUser == null){ //bibliotekar je logovan
				String criteria = (String)cbSearchFilters.getSelectedItem();
				String filter = txtSearchFilter.getText();
				
				if(!isAutomatic && !criteria.equals("Show all") && (filter.equals(null) || filter.equals(""))){
					txtSearchFilter.setBackground(Color.LIGHT_GRAY);
					message = "Please, enter the value in search filter!";
					success = false;
					displayMessageDialogBox();
				}
				else{
					if(!isAutomatic && criteria.equals("User"))
					{
						if(onlyActive.isSelected())
							model = new RezervacijeTableModel(rezervacijaServiceBean.getActiveRezervacijaByUserFilter(filter));
						else
							model = new RezervacijeTableModel(rezervacijaServiceBean.getAllRezervacijeByUserFilter(filter));
					}
					else if(!isAutomatic && criteria.equals("Book")){
						if(onlyActive.isSelected())
							model = new RezervacijeTableModel(rezervacijaServiceBean.getActiveRezervacijaByBookFilter(filter));
						else
							model = new RezervacijeTableModel(rezervacijaServiceBean.getAllRezervacijeByBookFilter(filter));
					}
					else {
						if(onlyActive.isSelected())
							model = new RezervacijeTableModel(rezervacijaServiceBean.getAllActiveRezervacije());
						else
							model = new RezervacijeTableModel(rezervacijaServiceBean.getAllRezervacije());
					}
				}
			}
			else{ //logovan je nastavnik ili student
				String filter = txtSearchFilter.getText();
				if(!isAutomatic) {
					if(onlyActive.isSelected())
						model = new RezervacijeTableModel(rezervacijaServiceBean.getActiveRezervacijaByUserBookFilter(currUser, filter));
					else
						model = new RezervacijeTableModel(rezervacijaServiceBean.getAllRezervacijeByUserBookFilter(currUser, filter));
				}
				else {
				if(onlyActive.isSelected())
					model = new RezervacijeTableModel(rezervacijaServiceBean.getActiveRezervacijeByKorisnik(currUser));
				else
					model = new RezervacijeTableModel(rezervacijaServiceBean.getRezervacijeByKorisnik(currUser));
				}
			}
				
			if(success){
				table.setModel(model);
				if(table.getRowCount() == 0){
					message = "No result found!";
					
					getContentPane().repaint();
					getContentPane().revalidate();
					
					displayMessageDialogBox();
				}
			}
			
		    	getContentPane().repaint();
				getContentPane().revalidate();
	}
	
	private void clearUIElements(){
		txtSearchFilter.setText("");
		txtSearchFilter.setBackground(Color.WHITE);
		cbSearchFilters.setSelectedItem("User");
		txtSearchFilter.setEditable(true);
		table.getSelectionModel().clearSelection();
		onlyActive.setSelected(false);
		
		model = new RezervacijeTableModel(rezervacijaServiceBean.getAllRezervacije());
		table.setModel(model);
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	public void prikazi() { setVisible(true); }
	
	private JCheckBox onlyActive;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private RezervacijeTableModel model;

	private JLabel searchCriteriaLabel;
	private JComboBox<String> cbSearchFilters;
	private JTextField txtSearchFilter;
	private JButton searchBtn;
	private JButton confirm;
	
	private RezervacijaServiceBean rezervacijaServiceBean = new RezervacijaServiceBean();
	
	Korisnik currUser; //can be null if bibliotekar is logged in
	private JLabel lblTypeToSerach;
	private String message;

}
