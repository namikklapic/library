package tableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import jpa.Posudba;
import jpa.PosudbaPK;

public class PosudbeTableModel extends AbstractTableModel {
	
	private String [] columnNames = {"User ID", "First name", "Last name", "Book title", "Copy ID", "Start date", "Ultimate return date", "Return date" };
	private List<Posudba> posudbe;
	
	public void setColumnLabel(String label, int col){
		columnNames[col] = label;
	}
	
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
			return p.getKorisnik().getSifraKorisnika();
		case 1:
			return p.getKorisnik().getImeKorisnika();
		case 2:
			return p.getKorisnik().getPrezimeKorisnika();
		case 3:
			return p.getPrimjerak().getKnjiga().getNaslov();
		case 4:
			return p.getPrimjerak().getInventarskiBroj();
		case 5:
			return new SimpleDateFormat("dd-MM-yyyy").format(p.getId().getDatumPosudbe());
		case 6:
			return new SimpleDateFormat("dd-MM-yyyy").format(p.getKrajnjiDatumVracanja());
		case 7:
			if(p.getDatumVracanja() != null)
				return new SimpleDateFormat("dd-MM-yyyy").format(p.getDatumVracanja());
			else return "";
		default:
			return null;
		}
	}
	
	public Posudba getPosudba(int row){
		return posudbe.get(row);
	}

	

}