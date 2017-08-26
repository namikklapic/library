package swing.literaturaPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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

import bussines.LiteraturaServiceBean;
import bussines.NastavnikPredmetServiceBean;
import jpa.Literatura;
import jpa.Nastavnik;
import jpa.Predmet;
import swing.autorPanels.AutorPregled;
import tableModel.LiteraturaTableModel;

public class LiteraturaPregled extends JFrame {

	public LiteraturaPregled() {
	}

	public LiteraturaPregled(Nastavnik n) {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 600;
		int sirinaProzora = 1000;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(1000, 600);
		setResizable(false);

		
		this.nastavnik = n;
		setTitle("Pregled literature");
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setBounds(12, 13, 976, 574);
		panel.setLayout(null);
		cbPredmeti.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		cbPredmeti.setBounds(682, 13, 282, 37);
		
		panel.add(cbPredmeti);
		that = this;
	
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 670, 574);
		panel.add(scrollPane);
		addLiteratura.setBounds(771, 232, 145, 37);
		addLiteratura.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				novaLiteratura.prikazi();
			}
		});
		delete.setBounds(771, 282, 145, 37);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Literatura l = null;
				try{
					l = ((LiteraturaTableModel)table.getModel()).getLiteratura(table.getSelectedRow());
				} catch(ArrayIndexOutOfBoundsException exception) {}
				if(l != null) {
					literaturaServiceBean.delete(l);
				}
				update();
			}
		});
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				delete.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				delete.setBackground(Color.DARK_GRAY);
			}
		});
		delete.setForeground(Color.WHITE);
		delete.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		delete.setFocusPainted(false);
		delete.setBorder(null);
		delete.setBackground(Color.DARK_GRAY);
		
		
		cbPredmeti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				currentPredmet = (Predmet)cbPredmeti.getSelectedItem();
				novaLiteratura = new NovaLiteratura(currentPredmet, that);
				update();
			}
		});
		getContentPane().setLayout(null);
		panel.add(addLiteratura);
		panel.add(delete);
		
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AutorPregled.class.getResource("/swing/images/background.jpg")));
		lblNewLabel.setBounds(0, 0, 1000, 800);
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
		cancel.setBounds(771, 393, 145, 37);
		
		panel.add(cancel);
		
		addLiteratura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				addLiteratura.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				addLiteratura.setBackground(Color.DARK_GRAY);
			}
		});
		addLiteratura.setForeground(Color.WHITE);
		addLiteratura.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		addLiteratura.setFocusPainted(false);
		addLiteratura.setBorder(null);
		addLiteratura.setBackground(Color.DARK_GRAY);
	}

	public void prikazi() {
		List<Predmet> predmeti = nastavnikPredmetServiceBean.getPredmetiByNastavnik(this.nastavnik);
		if(predmeti == null || predmeti.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No Predmets assigned to you", "No Predmets", JOptionPane.ERROR_MESSAGE);
			return;
		}
		setVisible(true);
		this.currentPredmet = predmeti.get(0);
		for (Predmet p : predmeti) {
			this.cbPredmeti.addItem(p);
		}
		model = new LiteraturaTableModel(literaturaServiceBean.getLiteraturaOnPredmet(this.currentPredmet));
		table.setModel(this.model);
		novaLiteratura = new NovaLiteratura(this.currentPredmet, this);
		this.repaint();
		this.revalidate();
	}
	public void update() {
		LiteraturaTableModel model = new LiteraturaTableModel(literaturaServiceBean.getLiteraturaOnPredmet(this.currentPredmet));
		table.setModel(model);
		this.repaint();
		this.revalidate();
	}

	public JMenuItem getMenuItem() {
		JMenuItem item = new JMenuItem("Reading lists");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				prikazi();
			}
		});
		return item;
	}
	
	private LiteraturaPregled that;
	private JButton addLiteratura = new JButton("Add");
	private JButton delete = new JButton("Delete");
	private NovaLiteratura novaLiteratura;
	private Predmet currentPredmet;
	
	private LiteraturaTableModel model;
	private JTable table = new JTable();

	private JComboBox<Predmet> cbPredmeti = new JComboBox<Predmet>();

	private NastavnikPredmetServiceBean nastavnikPredmetServiceBean = new NastavnikPredmetServiceBean();
	private LiteraturaServiceBean literaturaServiceBean = new LiteraturaServiceBean();
	private Nastavnik nastavnik;

}
