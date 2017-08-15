package swing.posudbaPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
import tableModel.PosudbeTableModel;

public class PosudbePregled extends JFrame {
	
	public PosudbePregled() {
		
		panel = new JPanel();
		add(panel);
	}
	
	public PosudbePregled(Korisnik k){
		
		setTitle("Pregled posudbi za korisnika");
		setSize(800, 800);
		
		panel = new JPanel();	
		panel.setSize(800, 800);
		
		JScrollPane scrollPane = new JScrollPane();
		
		PosudbeTableModel model = new PosudbeTableModel(posudbaServiceBean.getPosudbeByKorisnik(k));
		JTable table = new JTable(model);
		
		JButton edit = new JButton("Uredi");
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				Posudba p = model.getPosudba(table.getSelectedRow());
				Knjiga k = knjigaServiceBean.getById(p.getPrimjerak().getKnjiga().getId());
				NovaKnjiga nk = new NovaKnjiga(k, false);
				nk.prikazi();
			}
		});
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(edit);
		
		add(panel);
		
	}
	
	public JMenuItem getMenuItem(JPanel parent){
		JMenuItem item = new JMenuItem("Pregled Posudbi");
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
	
}
