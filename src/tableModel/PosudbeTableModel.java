package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import jpa.Posudba;
import jpa.PosudbaPK;

public class PosudbeTableModel extends AbstractTableModel {
	
	private String [] columnNames = {"Title", "Inventory number", "Year of publishing", "Negative points", "Date of loan", "Ultimate date of return", "Date of return" };
	private List<Posudba> posudbe;
	
	public PosudbeTableModel(){
		posudbe = new ArrayList<Posudba>();
	}
	
	public PosudbeTableModel(List<Posudba> posudbe){
		this.posudbe = posudbe;
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
		return posudbe.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		Posudba p = getPosudba(row);
		switch(column){
		case 0:
			return p.getPrimjerak().getKnjiga().getNaslov();
		case 1:
			return p.getPrimjerak().getInventarskiBroj();
		case 2:
			return p.getPrimjerak().getKnjiga().getGodinaIzdavanja();
		case 3:
			return p.getPrimjerak().getKnjiga().getNegBodovi();
		case 4:
			return p.getId().getDatumPosudbe();
		case 5:
			return p.getKrajnjiDatumVracanja();
		case 6:
			return p.getDatumVracanja();
		default:
			return null;
		}
	}
	
	public Posudba getPosudba(int row){
		return posudbe.get(row);
	}

	

}