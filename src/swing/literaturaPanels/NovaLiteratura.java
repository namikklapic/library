package swing.literaturaPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class NovaLiteratura extends JFrame {
	public NovaLiteratura(Predmet p) {
		setTitle("Nova literatura za predmet " + p.getNazivPredmeta());
		this.predmet = p;
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("Knjiga: "));
		panel.add(availableBooks);
		
		panel.add(new JLabel("Broj vaznosti"));
		panel.add(this.brojVaznosti);
		panel.add(new JLabel("Mandatory"));
		panel.add(this.obavezna);
		
		
		JButton potvrdi = new JButton("Add");
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
		panel.add(potvrdi);
		
		add(panel);
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
