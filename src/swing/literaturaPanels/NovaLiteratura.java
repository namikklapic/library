package swing.literaturaPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.KnjigaServiceBean;
import bussines.LiteraturaServiceBean;
import bussines.NastavnikPredmetServiceBean;
import jpa.Knjiga;
import jpa.Literatura;
import jpa.Predmet;
import swing.autorPanels.AutorPregled;
import java.awt.Font;
import java.awt.Toolkit;

public class NovaLiteratura extends JFrame {
	/**
	 * @wbp.parser.constructor
	 */
	public NovaLiteratura(Predmet p) {
		setTitle("Nova literatura za predmet " + p.getNazivPredmeta());
		this.predmet = p;
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 500;
		int sirinaProzora = 500;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(500, 500);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setBounds(12, 13, 476, 474);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Knjiga: ");
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label.setBounds(33, 121, 154, 25);
		panel.add(label);
		availableBooks.setBounds(244, 124, 220, 25);
		panel.add(availableBooks);
		
		JLabel label_1 = new JLabel("Broj vaznosti");
		label_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label_1.setBounds(33, 159, 154, 25);
		panel.add(label_1);
		brojVaznosti.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		brojVaznosti.setBounds(244, 159, 220, 25);
		panel.add(this.brojVaznosti);
		JLabel label_2 = new JLabel("Mandatory");
		label_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		label_2.setBounds(33, 197, 154, 25);
		panel.add(label_2);
		obavezna.setBounds(244, 197, 25, 25);
		panel.add(this.obavezna);
		
		
		JButton potvrdi = new JButton("Add");
		potvrdi.setBounds(62, 408, 145, 37);
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Literatura l = validateForm();
				if(l != null) {
					literaturaServiceBean.save(l);
					dispose();
					if(parent != null) {
						parent.update();
					}
				}
			}
		});
		potvrdi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				potvrdi.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				potvrdi.setBackground(Color.DARK_GRAY);
			}
		});
		potvrdi.setForeground(Color.WHITE);
		potvrdi.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		potvrdi.setFocusPainted(false);
		potvrdi.setBorder(null);
		potvrdi.setBackground(Color.DARK_GRAY);
		
		
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
		cancel.setBounds(269, 407, 145, 37);
		
		panel.add(cancel);
		
		getContentPane().setLayout(null);
		panel.add(potvrdi);
		
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AutorPregled.class.getResource("/swing/images/background.jpg")));
		lblNewLabel.setBounds(0, 0, 553, 527);
		getContentPane().add(lblNewLabel);
	}
	public NovaLiteratura(Predmet p, LiteraturaPregled parent) {
		this(p);
		this.parent = parent;
		
	}
	private Literatura validateForm() {
		Literatura l = null;
		try{
			Integer bv = Integer.parseInt(brojVaznosti.getText());
			if(availableBooks.getSelectedIndex() != -1) {
				Knjiga k = (Knjiga)availableBooks.getSelectedItem();
				l = new Literatura (k, this.predmet, bv, obavezna.isSelected());
			}
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Broj vaznosti must be a number.", "Error adding new literatura", JOptionPane.ERROR_MESSAGE);
		}
		
		return l;
	}
	
	public void prikazi() {
		setVisible(true);
		for(Knjiga k : knjigaServiceBean.getAllKnjige()) {
			availableBooks.addItem(k);
		}
	}
	
	private LiteraturaPregled parent =  null;
	
	
	private JComboBox<Knjiga> availableBooks = new JComboBox<Knjiga>();
	private JTextField brojVaznosti = new JTextField(10);
	private JCheckBox obavezna = new JCheckBox();
	private Predmet predmet;
	
	private KnjigaServiceBean knjigaServiceBean = new KnjigaServiceBean();
	private NastavnikPredmetServiceBean nastavnikPredmetServiceBean = new NastavnikPredmetServiceBean();
	private LiteraturaServiceBean literaturaServiceBean = new LiteraturaServiceBean();
}
