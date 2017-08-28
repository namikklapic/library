package swing.posudbaPanels;

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

import bussines.KnjigaServiceBean;
import bussines.PosudbaServiceBean;
import bussines.PrimjerakServiceBean;
import jpa.Posudba;
import jpa.PosudbaPK;
import swing.autorPanels.AutorPregled;
import swing.knjigaPanels.NovaKnjiga;
import jpa.Knjiga;
import jpa.Korisnik;
import tableModel.KnjigaTableModel;
import tableModel.PosudbeTableModel;
import javax.swing.SwingConstants;

public class PosudbePregled extends JFrame {
	
	public PosudbePregled() {	
		
		panel = new JPanel();
		add(panel);
	}
	
	public PosudbePregled(Korisnik k){
		setTitle("Pregled posudbi");
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
		scrollPane.setBounds(0, 52, 1276, 382);
		model = new PosudbeTableModel(posudbaServiceBean.getAllPosudbe());
		if(k == null) //it is bibliotekar panel, and we want all users
			model = new PosudbeTableModel(posudbaServiceBean.getAllPosudbe());
		else //it is korisnik panel, show only for specific user 
			model = new PosudbeTableModel(posudbaServiceBean.getPosudbeByKorisnik(k));
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
		
		class CheckboxAction extends AbstractAction {
		    public CheckboxAction(String text) {
		        super(text);
		    }
		 
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        //refreshTable(false);
		    }
		}
		getContentPane().setLayout(null);
		
		//book review --------- search criteria ----------------------------------------------------------
		searchCriteriaLabel = new JLabel("Choose search criteria : ");
		searchCriteriaLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		searchCriteriaLabel.setBounds(32, 458, 195, 33);
		if(currUser == null)
			panel.add(searchCriteriaLabel);
			
		
		txtSearchFilter = new JTextField(10);
		txtSearchFilter.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtSearchFilter.setBounds(598, 458, 195, 33);
		txtSearchFilter.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSearchFilter.setBackground(Color.WHITE);
			}
		});
		
		cbSearchFilters = new JComboBox<String>();
		cbSearchFilters.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		cbSearchFilters.setBounds(213, 458, 195, 33);
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
		}
		panel.add(txtSearchFilter);
		searchBtn = new JButton("Search");
		searchBtn.setBounds(805, 456, 156, 37);
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				//searchClicked = true;
				refreshTable(false);
				//searchClicked = false;
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
		//if(currUser == null)
			panel.add(searchBtn);
		
		closeLoan = new JButton("Close the loan");
		closeLoan.setBounds(12, 524, 145, 37);
		closeLoan.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				int selectedRow = table.getSelectedRow();
				if(selectedRow > -1){
					Posudba p = ((PosudbeTableModel) table.getModel()).getPosudba(table.getSelectedRow());
					closePosudba(p);
				}else{
					//searchClicked = true;
					message = "No item selected!";
					displayMessageDialogBox();
					//searchClicked = false;
				}
			}
		});
		closeLoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				closeLoan.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				closeLoan.setBackground(Color.DARK_GRAY);
			}
		});
		closeLoan.setForeground(Color.WHITE);
		closeLoan.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		closeLoan.setFocusPainted(false);
		closeLoan.setBorder(null);
		closeLoan.setBackground(Color.DARK_GRAY);
		if(currUser == null)
			panel.add(closeLoan);
		
		//end---------------------------------------------------------------------------------------------
		
		onlyActive = new JCheckBox(new CheckboxAction("Show only active loans"));
		onlyActive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getContentPane().repaint();
				getContentPane().revalidate();
			}
		});
		onlyActive.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		onlyActive.setFocusPainted(false);
		onlyActive.setOpaque(true);
		onlyActive.setBounds(1010, 458, 232, 33);
		onlyActive.setSelected(false);
		panel.setLayout(null);
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(onlyActive);
		
		getContentPane().add(panel);
		
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
		cancel.setBounds(565, 524, 145, 37);
		

		panel.add(cancel);
		
		JLabel lblViewAll = new JLabel("View all book loans");
		lblViewAll.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewAll.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		lblViewAll.setBounds(460, 13, 355, 32);
		panel.add(lblViewAll);
		
		JLabel lblTypeToSearch = new JLabel("Type to search :");
		lblTypeToSearch.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lblTypeToSearch.setBounds(468, 458, 134, 33);
		//if(currUser == null)
		panel.add(lblTypeToSearch);
	}
	
	public JMenuItem getMenuItem(JPanel parent){
		JMenuItem item = new JMenuItem("Book loans review");
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
						model = new PosudbeTableModel(posudbaServiceBean.getActivePosudbaByUserFilter(filter));
					else
						model = new PosudbeTableModel(posudbaServiceBean.getAllPosudbeByUserFilter(filter));
				}
				else if(!isAutomatic && criteria.equals("Book")){
					if(onlyActive.isSelected())
						model = new PosudbeTableModel(posudbaServiceBean.getActivePosudbaByBookFilter(filter));
					else
						model = new PosudbeTableModel(posudbaServiceBean.getAllPosudbeByBookFilter(filter));
				}
				else {
					if(onlyActive.isSelected())
						model = new PosudbeTableModel(posudbaServiceBean.getActivePosudba());
					else
						model = new PosudbeTableModel(posudbaServiceBean.getAllPosudbe());
				}
			}
		}
		else{ //logovan je nastavnik ili student
			String filter = txtSearchFilter.getText();
			if(!isAutomatic) {
				if(onlyActive.isSelected())
					model = new PosudbeTableModel(posudbaServiceBean.getActivePosudbaByUserBookFilter(currUser, filter));
				else
					model = new PosudbeTableModel(posudbaServiceBean.getAllPosudbeByUserBookFilter(currUser, filter));
			}
			else {
			if(onlyActive.isSelected())
				model = new PosudbeTableModel(posudbaServiceBean.getActivePosudbaByKorisnik(currUser));
			else
				model = new PosudbeTableModel(posudbaServiceBean.getPosudbeByKorisnik(currUser));
			}
		}
			
		if(success){
			table.setModel(model);
			if(!isAutomatic && table.getRowCount() == 0){

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
		
		model = new PosudbeTableModel(posudbaServiceBean.getAllPosudbe());
		table.setModel(model);
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	public void closePosudba(Posudba p){
		if(p.getDatumVracanja() != null){
			message = "The loan is already closed!";
			displayMessageDialogBox();
		}else{
			posudbaServiceBean.zakljuciPosudbu(p);
			primjerakServiceBean.setPrimjerakPosudjen(p.getPrimjerak(), false);
			primjerakServiceBean.setPrimjerakRezervisan(p.getPrimjerak(), false);
			message = "The loan has been successfully closed!";
			displayMessageDialogBox();
		}
	}
	
	public void prikazi() { setVisible(true); }
	
	private String message;
	private JPanel panel;
	private JCheckBox onlyActive;
	private PosudbaServiceBean posudbaServiceBean = new PosudbaServiceBean();
	private KnjigaServiceBean knjigaServiceBean = new KnjigaServiceBean();
	private JTable table;
	private Korisnik currUser; //can be null if bibliotekar is logged in
	
	private JScrollPane scrollPane;
	private PosudbeTableModel model;
	
	private JLabel searchCriteriaLabel;
	private JComboBox<String> cbSearchFilters;
	private JTextField txtSearchFilter;
	private JButton searchBtn;
	private JButton closeLoan;
	
	private PrimjerakServiceBean primjerakServiceBean = new PrimjerakServiceBean();
}
