package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import bussines.AutorKnjigaServiceBean;
import jpa.Knjiga;

public class KnjigaTableModel extends AbstractTableModel {
	
	private String [] columnNames = {"Title", "Author", "Book type", "Publisher", "Publishing year" };
	private List<Knjiga> knjige;
	
	private AutorKnjigaServiceBean autorKnjigaServiceBean = new AutorKnjigaServiceBean();
	
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
			return autorKnjigaServiceBean.getAutorNaKnjizi(k, 1);
		case 2:
			return k.getVrsta();
		case 3:
			return k.getIzdavac();
		case 4:
			return k.getGodinaIzdavanja();
		default:
			return null;
		}
	}
	
	public Knjiga getKnjiga(int row){
		return knjige.get(row);
	}
	
	public void removeRow(int row){
		knjige.remove(row);
		fireTableRowsDeleted(row, row);
	}
	
}
