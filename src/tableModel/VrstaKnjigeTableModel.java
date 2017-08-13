package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import bussines.KnjigaServiceBean;
import jpa.VrstaKnjige;

public class VrstaKnjigeTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Naziv vrste knjige"};
	private List<VrstaKnjige> vrste;
	
	public VrstaKnjigeTableModel(){
		vrste = new ArrayList<VrstaKnjige>();
	}
	
	public VrstaKnjigeTableModel(List<VrstaKnjige> vrste){
		this.vrste = vrste;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column){
		//TODO Auto-generated method stub
		return columnNames[column];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return vrste.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		VrstaKnjige vk = getVrstaKnjige(row);
		switch(column){
		case 0:
			return vk.getNazivVrste();
		default:
			return null;
		}
	}
	
	public VrstaKnjige getVrstaKnjige(int row){
		return vrste.get(row);
	}
	
}
