package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import jpa.Knjiga;

public class KnjigaTableModel extends AbstractTableModel {
	
	private String [] columnNames = {"Naslov", "Originalni naslov", "Broj stranica", "Godina izdavanja", "Negativni bodovi", "Vrsta", "Izdavac" };
	private List<Knjiga> knjige;
	
	public KnjigaTableModel(){
		knjige = new ArrayList<Knjiga>();
	}
	
	public KnjigaTableModel(List<Knjiga> knjige){
		this.knjige = knjige;
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
		return knjige.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		Knjiga k = getKnjiga(row);
		switch(column){
		case 0:
			return k.getNaslov();
		case 1:
			return k.getOriginalniNaslov();
		case 2:
			return k.getBrojStranica();
		case 3:
			return k.getGodinaIzdavanja();
		case 4:
			return k.getNegBodovi();
		case 5:
			return k.getVrsta();
		case 6:
			return k.getIzdavac();
		default:
			return null;
		}
	}
	
	public Knjiga getKnjiga(int row){
		return knjige.get(row);
	}
	

}
