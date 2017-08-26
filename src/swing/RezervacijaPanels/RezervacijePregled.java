package swing.RezervacijaPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.RezervacijaServiceBean;
import jpa.Rezervacija;
import jpa.RezervacijaPK;
import jpa.Korisnik;
import tableModel.RezervacijeTableModel;

public class RezervacijePregled extends JFrame {
	
public RezervacijePregled() {
		
		panel = new JPanel();
		add(panel);
	}
	
public RezervacijePregled(Korisnik k){
		
	setTitle("Pregled rezervacija");
	setSize(800, 800);
	
	panel = new JPanel();	
	panel.setSize(800, 800);
	
	JScrollPane scrollPane = new JScrollPane();
	RezervacijeTableModel model;
	if(k == null) //it is bibliotekar panel, and we want all users
		model = new RezervacijeTableModel(rezervacijaServiceBean.getAllRezervacije());
	else //it is korisnik panel, show only for specific user 
		model = new RezervacijeTableModel(rezervacijaServiceBean.getRezervacijeByKorisnik(k));
	table = new JTable(model);
	currUser = k;
	
	JButton confirm = new JButton("Confirm reservation");
	confirm.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent event){
			if(table.getSelectedRow() > -1) {
				Rezervacija r = ((RezervacijeTableModel) table.getModel()).getRezervacija(table.getSelectedRow());
				if(r.getIsConfirmed() == false) {
					rezervacijaServiceBean.setRezervacijaConfirmed(r.getId());
					JOptionPane.showMessageDialog(confirm.getParent(), "Rezervation confirmed.", "Success!", JOptionPane.INFORMATION_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(confirm.getParent(), "That reservation is already confirmed.", "Oops!", JOptionPane.ERROR_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(confirm.getParent(), "No item selected!.", "Oops!", JOptionPane.ERROR_MESSAGE);
		}
	});
	
	class CheckboxAction extends AbstractAction {
	    public CheckboxAction(String text) {
	        super(text);
	    }
	 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	refreshTable();
	    }
	}
	
	onlyActive = new JCheckBox(new CheckboxAction("Show only active reservations"));
	onlyActive.setSelected(false);
	
	scrollPane.setViewportView(table);
	panel.add(scrollPane);
	if(currUser == null)
		panel.add(confirm);
	panel.add(onlyActive);
	
	add(panel);
	
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
	
	public void refreshTable() {
		 if (onlyActive.isSelected()) {
	        	RezervacijeTableModel activeLoans;
	        	if(currUser == null) 
	        		activeLoans = new RezervacijeTableModel(rezervacijaServiceBean.getAllActiveRezervacije());
	        	else 
	        		activeLoans = new RezervacijeTableModel(rezervacijaServiceBean.getActiveRezervacijeByKorisnik(currUser));	
		        table.setModel(activeLoans);
	        } else {	
	        	RezervacijeTableModel loans;
	        	if(currUser == null) 
	        		loans = new RezervacijeTableModel(rezervacijaServiceBean.getAllRezervacije());
	        	else 
	        		loans = new RezervacijeTableModel(rezervacijaServiceBean.getRezervacijeByKorisnik(currUser));
		        table.setModel(loans);
	        }	
	}
	
	private JCheckBox onlyActive;
	private JPanel panel;
	private RezervacijaServiceBean rezervacijaServiceBean = new RezervacijaServiceBean();
	JTable table;
	Korisnik currUser; //can be null if bibliotekar is logged in

}
