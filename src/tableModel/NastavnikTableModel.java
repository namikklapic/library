package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import jpa.Nastavnik;

public class NastavnikTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Ime i prezime nastavnika", "Akademsko zvanje", "Broj negativnih bodova"};
	private List<Nastavnik> nastavnici;
	
	public NastavnikTableModel(){
		nastavnici = new ArrayList<Nastavnik>();
	}
	public NastavnikTableModel(List<Nastavnik> nastavnici){
		this.nastavnici = nastavnici;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return nastavnici.size();
	}
	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		Nastavnik n = getNastavnik(row);
		switch(column){
		case 0: 
			return n.getKorisnik().getImeKorisnika() + ' ' + n.getKorisnik().getPrezimeKorisnika();
		case 1:
			return n.getAkademskoZvanje();
		case 2:
			return n.getKorisnik().getBrojNegativnihBodova();
			
		default: return null;
		}
	}
	public Nastavnik getNastavnik(int row){
		return nastavnici.get(row);
	}
	
}
