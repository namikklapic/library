package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class Lookup<T> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Lookup (List<T> items){
		// this section will be visible when lookup is opened
		DefaultListModel<T> listModel = new DefaultListModel<T>();
		for(T element : items) {
			listModel.addElement(element);
		}
		JList<T> selectionLista = new JList<T>(listModel);
		JPanel lookupPanel = new JPanel();
		JButton potvrdi = new JButton("Potvrdi");
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				selectedValues =(T[])selectionLista.getSelectedValues();
				setSelectionText();
			}
		});
		JButton ponisti = new JButton("Ponisti");
		ponisti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				selectionLista.clearSelection();
				selectedValues = (T[])selectionLista.getSelectedValues();
				setSelectionText();
			}
		});
		lookupPanel.add(selectionLista);
		lookupPanel.add(potvrdi);
		lookupPanel.add(ponisti);
		options.add(lookupPanel);
		
		
		// this section will be visible when lookup is included into panel
		
		JButton openDialog = new JButton("Odaberi");
		openDialog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				options.setVisible(true);
			}
		});
		JButton cancelSelection = new JButton("Ponisti");
		add(selected);
		add(openDialog);
		add(cancelSelection);
		
		
	}
	public Lookup(List<T> items, String label) {
		
	}
	public void setSelectionText() {
		String selectionText = "";
		for(T selectedValue : selectedValues) {
			selectionText += selectedValue.toString() + ";";
		}
		selected.setText(selectionText);
	}
	public Object[] getSelectedValues () {
		return this.selectedValues;
	}
		
			
	private JFrame options = new JFrame();
	private T[] selectedValues;
	JTextField selected = new JTextField(10);
	
	
}

