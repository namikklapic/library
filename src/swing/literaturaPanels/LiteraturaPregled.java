package swing.literaturaPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;

import bussines.LiteraturaServiceBean;
import bussines.NastavnikPredmetServiceBean;
import jpa.Nastavnik;
import jpa.Predmet;
import tableModel.LiteraturaTableModel;

public class LiteraturaPregled extends JFrame {

	public LiteraturaPregled() {
	}

	public LiteraturaPregled(Nastavnik n) {
		setSize(400, 400);
		this.nastavnik = n;
		setTitle("Pregled literature");
		JPanel panel = new JPanel();
		panel.add(cbPredmeti);
		that = this;
	
		panel.add(table);
		addLiteratura.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				novaLiteratura.prikazi();
			}
		});
		cbPredmeti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				currentPredmet = (Predmet)cbPredmeti.getSelectedItem();
				novaLiteratura = new NovaLiteratura(currentPredmet, that);
				update();
			}
		});
		panel.add(addLiteratura);
		
		add(panel);
	}

	public void prikazi() {
		setVisible(true);
		List<Predmet> predmeti = nastavnikPredmetServiceBean.getPredmetiByNastavnik(this.nastavnik);
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
	private NovaLiteratura novaLiteratura;
	private Predmet currentPredmet;
	
	private LiteraturaTableModel model;
	private JTable table = new JTable();

	private JComboBox<Predmet> cbPredmeti = new JComboBox<Predmet>();

	private NastavnikPredmetServiceBean nastavnikPredmetServiceBean = new NastavnikPredmetServiceBean();
	private LiteraturaServiceBean literaturaServiceBean = new LiteraturaServiceBean();
	private Nastavnik nastavnik;

}