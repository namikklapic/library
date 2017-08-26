package swing.literaturaPanels;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bussines.NastavnikPredmetServiceBean;
import jpa.Nastavnik;
import jpa.Predmet;

public class LiteraturaPregled  extends JFrame{
	
	public LiteraturaPregled(){}
	
	public LiteraturaPregled(Nastavnik n) {
		this.nastavnik = n;
		setTitle("Pregled literature");
		JPanel panel = new JPanel();
		panel.add(cbPredmeti);
		add(panel);
	}
	
	public void setVisible() {
		setVisible(true);
		for(Predmet p : nastavnikPredmetServiceBean.getPredmetiByNastavnik(this.nastavnik)) {
			this.cbPredmeti.addItem(p);
		}
	}
	
	private JComboBox<Predmet> cbPredmeti = new JComboBox<Predmet>();
	
	private NastavnikPredmetServiceBean nastavnikPredmetServiceBean = new NastavnikPredmetServiceBean();
	
	private Nastavnik nastavnik;
	



}
