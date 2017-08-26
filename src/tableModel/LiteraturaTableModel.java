package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import jpa.Autor;
import jpa.Literatura;

public class LiteraturaTableModel extends AbstractTableModel {
	private String [] columnNames = {"Knjiga", "Broj vaznosti", "Obavezna"};
	private List<Literatura> literatura;
	
	public LiteraturaTableModel(){
		literatura = new ArrayList<Literatura>();
	}
	public LiteraturaTableModel(List<Literatura> literatura){
		this.literatura = literatura;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column){
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return literatura.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		Literatura l = getLiteratura(row);
		switch(col){
		case 0: 
			return l.getKnjiga().getNaslov();
		case 1:
			return l.getBrojVaznosti();
		case 2:
			return l.isObavezna();
		default: return null;
		}
	}
	
	public Literatura getLiteratura(int row){
		return literatura.get(row);
	}
}
