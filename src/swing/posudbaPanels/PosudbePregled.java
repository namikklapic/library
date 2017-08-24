package swing.posudbaPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.KnjigaServiceBean;
import bussines.PosudbaServiceBean;
import jpa.Posudba;
import jpa.PosudbaPK;
import swing.knjigaPanels.NovaKnjiga;
import jpa.Knjiga;
import jpa.Korisnik;
import tableModel.KnjigaTableModel;
import tableModel.PosudbeTableModel;

public class PosudbePregled extends JFrame {
	
	public PosudbePregled() {
		
		panel = new JPanel();
		add(panel);
	}
	
	public PosudbePregled(Korisnik k){
		
		setTitle("Pregled posudbi");
		setSize(800, 800);
		
		panel = new JPanel();	
		panel.setSize(800, 800);
		
		JScrollPane scrollPane = new JScrollPane();
		PosudbeTableModel model;
		if(k == null) //it is bibliotekar panel, and we want all users
			model = new PosudbeTableModel(posudbaServiceBean.getAllPosudbe());
		else //it is korisnik panel, show only for specific user 
			model = new PosudbeTableModel(posudbaServiceBean.getPosudbeByKorisnik(k));
		table = new JTable(model);
		currUser = k;
		
		class CheckboxAction extends AbstractAction {
		    public CheckboxAction(String text) {
		        super(text);
		    }
		 
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JCheckBox chkbox = (JCheckBox) e.getSource();
		        if (chkbox.isSelected()) {
		        	PosudbeTableModel activeLoans;
		        	if(currUser == null) 
		        		activeLoans = new PosudbeTableModel(posudbaServiceBean.getActivePosudba());
		        	else 
		        		activeLoans = new PosudbeTableModel(posudbaServiceBean.getActivePosudbaByKorisnik(currUser));	
			        table.setModel(activeLoans);
		        } else {	
		        	PosudbeTableModel loans;
		        	if(currUser == null) 
		        		loans = new PosudbeTableModel(posudbaServiceBean.getAllPosudbe());
		        	else 
		        		loans = new PosudbeTableModel(posudbaServiceBean.getPosudbeByKorisnik(currUser));
			        table.setModel(loans);
		        }
		    }
		}
		
		JCheckBox onlyActive = new JCheckBox(new CheckboxAction("Show only active loans"));
		onlyActive.setSelected(false);
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(onlyActive);
		
		add(panel);
		
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
	
	private JPanel panel;
	private PosudbaServiceBean posudbaServiceBean = new PosudbaServiceBean();
	private KnjigaServiceBean knjigaServiceBean = new KnjigaServiceBean();
	JTable table;
	Korisnik currUser; //can be null if bibliotekar is logged in
	
}
