package tableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import jpa.Rezervacija;
import jpa.RezervacijaPK;

public class RezervacijeTableModel extends AbstractTableModel {
	private String [] columnNames = {"Naslov", "Inventarni broj", "Godina izdavanja", "Negativni bodovi", "Datum rezervacije", "Ime korisnika", "Prezime korisnika"};
	private List<Rezervacija> rezervacije;
	
	public RezervacijeTableModel(){
		rezervacije = new ArrayList<Rezervacija>();
	}
	
	public RezervacijeTableModel(List<Rezervacija> rezervacije){
		this.rezervacije = rezervacije;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column){
		// TODO Auto-generated method stub
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rezervacije.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		Rezervacija r = getRezervacija(row);
		switch(column){
		case 0:
			return r.getPrimjerak().getKnjiga().getNaslov();
		case 1:
			return r.getPrimjerak().getInventarskiBroj();
		case 2:
			return r.getPrimjerak().getKnjiga().getGodinaIzdavanja();
		case 3:
			return r.getPrimjerak().getKnjiga().getNegBodovi();
		case 4:
			return new SimpleDateFormat("dd-MM-yyyy").format(r.getDatumRezervacije());
		case 5:
			return r.getKorisnik().getImeKorisnika();
		case 6:
			return r.getKorisnik().getPrezimeKorisnika();
		default:
			return null;
		}
	}
	
	public Rezervacija getRezervacija(int row){
		return rezervacije.get(row);
	}
}
