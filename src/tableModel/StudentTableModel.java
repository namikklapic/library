package tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import jpa.Student;

public class StudentTableModel extends AbstractTableModel {

	private String[] columnNames = {"JMBG", "Ime", "Prezime", "Broj indeksa", "Upisani semestar", "Broj neg. bodova" };
	private List<Student> studenti;

	public StudentTableModel() {
		studenti = new ArrayList<Student>();
	}

	public StudentTableModel(List<Student> studenti) {
		this.studenti = studenti;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return studenti.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Student s = getStudent(row);
		switch (col) {
		case 0:
			return s.getPassword();
		case 1:
			return s.getKorisnik().getImeKorisnika();
		case 2:
			return s.getKorisnik().getPrezimeKorisnika();
		case 3:
			return s.getBrojIndeksa();
		case 4:
			return s.getUpisaniSemestar();
		case 5:
			return s.getKorisnik().getBrojNegativnihBodova();
		default: return null;
		}
	}

	public Student getStudent(int row) {
		return studenti.get(row);
	}

}
