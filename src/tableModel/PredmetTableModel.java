package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import jpa.Autor;
import jpa.Predmet;

public class PredmetTableModel extends AbstractTableModel {
	
	private String [] columnNames = {"Naziv predmeta", "Skracenica", "Broj semestra"};
	private List<Predmet> predmeti;
	
	public PredmetTableModel(){
		predmeti = new ArrayList<Predmet>();
	}
	
	public PredmetTableModel(List<Predmet> predmeti){
		this.predmeti = predmeti;
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
		return predmeti.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		Predmet p = getPredmet(row);
		switch(column){
		case 0:
			return p.getNazivPredmeta();
		case 1:
			return p.getSkraceniNazivPredmeta();
		case 2:
			return p.getBrojSemestra();
			
		default:
			return null;
		}
	}
	
	public Predmet getPredmet(int row){
		return predmeti.get(row);
	}
	
}
