package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import jpa.Autor;

public class AutorTableModel extends AbstractTableModel {
	
	private String [] columnNames = {"Ime i prezime autora"};
	private List<Autor> autori;
	
	public AutorTableModel(){
		autori = new ArrayList<Autor>();
	}
	public AutorTableModel(List<Autor> autori){
		this.autori = autori;
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
		return autori.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		Autor a = getAutor(row);
		switch(col){
		case 0:
			return a.getImeAutora() + ' ' + a.getPrezimeAutora();
			
		default: return null;
		}
	}
	
	public Autor getAutor(int row){
		return autori.get(row);
	}
	
}
