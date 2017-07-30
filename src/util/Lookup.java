package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Lookup<T> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Lookup (List<T> items){
		// this section will be visible when lookup is opened
		DefaultListModel<T> listModel = new DefaultListModel<T>();
		DefaultListModel<T> selectedItemsListModel = new DefaultListModel<T>();
		for(T element : items) {
			listModel.addElement(element);
		}
		
		
		
		JList<T> selectionLista = new JList<T>(listModel);
		JList<T> selectedItemsList = new JList<T>(selectedItemsListModel);
		
		selectionLista.addListSelectionListener(new ListSelectionListener() {
			@Override
			@SuppressWarnings("unchecked")
			public void valueChanged(ListSelectionEvent event) {
				if(!event.getValueIsAdjusting()) {
					T elem = (T)selectionLista.getSelectedValue();
					try{
						T cloned = (T) elem.getClass().getMethod("clone").invoke(elem);
						selectedItems.add(cloned);
						selectedItemsListModel.addElement(selectionLista.getSelectedValue());
						listModel.remove(selectionLista.getSelectedIndex());
					}catch(Exception e) {}
					
				}
			}
		});
		
		
		JPanel lookupPanel = new JPanel();
		JButton potvrdi = new JButton("Potvrdi");
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setSelectionText();
			}
		});
		JButton ponisti = new JButton("Ponisti");
		ponisti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				selectionLista.clearSelection();
				selectedItems.clear();
				setSelectionText();
			}
		});
		lookupPanel.add(selectedItemsList);
		lookupPanel.add(selectionLista);
		lookupPanel.add(potvrdi);
		lookupPanel.add(ponisti);
		options.add(lookupPanel);
		options.setSize(300, 300);
		
		
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
	public void setSelectionText() {
		String selectionText = "";
		for(T selectedValue : selectedItems) {
			selectionText += selectedValue.toString() + ";";
		}
		selected.setText(selectionText);
	}
	public List<T> getSelectedValues () {
		return this.selectedItems;
	}
		
			
	private JFrame options = new JFrame();
	private List<T> selectedItems = new ArrayList<T>();
	JTextField selected = new JTextField(10);
	
	
}

