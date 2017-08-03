package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import jpa.Izdavac;

public class IzdavacTableModel extends AbstractTableModel {
	private String [] columnNames = {"Ime izdavaca"};
	private List<Izdavac> izdavaci;
	
	public IzdavacTableModel() {
		izdavaci = new ArrayList<Izdavac>();
	}
	public IzdavacTableModel(List<Izdavac> izdavaci) {
		this.izdavaci = izdavaci;
	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	@Override
	public int getRowCount() {
		return izdavaci.size();
	}
	@Override
	public Object getValueAt(int row, int column) {
		Izdavac i = getIzdavac(row);
		switch(column) {
			case 0:
				return i.getNazivIzdavaca();
				
			default: return null;
		}
	}
	public Izdavac getIzdavac(int row) {
		return izdavaci.get(row);
	}

}
