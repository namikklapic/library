package swing.posudbaPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		scrollPane.getViewport().setBackground(new Color(255, 255, 255,20));
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 52, 1276, 382);
		PosudbeTableModel model;
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
		currUser = k;
		
		class CheckboxAction extends AbstractAction {
		    public CheckboxAction(String text) {
		        super(text);
		    }
		 
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        refreshTable(false);
		    }
		}
		getContentPane().setLayout(null);
		
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
		onlyActive.setBounds(537, 443, 202, 33);
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
		if (onlyActive.isSelected()) {
        	PosudbeTableModel activeLoans;
        	if(currUser == null) 
        		activeLoans = new PosudbeTableModel(posudbaServiceBean.getActivePosudba());
        	else 
        		activeLoans = new PosudbeTableModel(posudbaServiceBean.getActivePosudbaByKorisnik(currUser));	
	        table.setModel(activeLoans);
	    	getContentPane().repaint();
			getContentPane().revalidate();
        } else {	
        	PosudbeTableModel loans;
        	if(currUser == null) 
        		loans = new PosudbeTableModel(posudbaServiceBean.getAllPosudbe());
        	else 
        		loans = new PosudbeTableModel(posudbaServiceBean.getPosudbeByKorisnik(currUser));
	        table.setModel(loans);
	    	getContentPane().repaint();
			getContentPane().revalidate();
        }
	}
	
	public void prikazi() { setVisible(true); }
	
	private JPanel panel;
	private JCheckBox onlyActive;
	private PosudbaServiceBean posudbaServiceBean = new PosudbaServiceBean();
	private KnjigaServiceBean knjigaServiceBean = new KnjigaServiceBean();
	JTable table;
	Korisnik currUser; //can be null if bibliotekar is logged in
	
}
