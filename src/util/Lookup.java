package util;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		
		
		JScrollPane scrollOptions = new JScrollPane();
		scrollOptions.setPreferredSize(new Dimension(120, 180));
		JList<T> selectionLista = new JList<T>(listModel);
		scrollOptions.setViewportView(selectionLista);
		
		JScrollPane scrollSelection = new JScrollPane();
		scrollSelection.setPreferredSize(new Dimension(120, 180));
		selectedItemsList.setModel(selectedItemsListModel);
		scrollSelection.setViewportView(selectedItemsList);
		
		selectionLista.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if(!event.getValueIsAdjusting() && selectionLista.getSelectedValue() != null) {
					try{
						selectedItemsListModel.addElement(selectionLista.getSelectedValue());
						listModel.remove(selectionLista.getSelectedIndex());
					}catch(Exception e) {}
					
				}
			}
		});
		
		selectedItemsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if(!event.getValueIsAdjusting()) {
					
				}
			}
		});
		
		
		JPanel lookupPanel = new JPanel();
		JButton potvrdi = new JButton("Potvrdi");
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				options.dispose();
				setSelectionText();
			}
		});
		JButton ponisti = new JButton("Ponisti");
		ponisti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setSelectionText();
			}
		});
		
		lookupPanel.add(scrollOptions);
		lookupPanel.add(scrollSelection);
		
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
	
	public List<T> getSelectedValues(){
		List<T> values = new ArrayList<T>();
		for(int i = 0; i < selectedItemsList.getModel().getSize(); ++i) {
			values.add(selectedItemsList.getModel().getElementAt(i));
		}
		return values;
	}
	
	public void setSelectionText() {
		String selectionText = "";
		for(int i = 0; i < selectedItemsList.getModel().getSize(); ++i) {
			selectionText += selectedItemsList.getModel().getElementAt(i).toString() + ";"; 
		}
		selected.setText(selectionText);
	}
		
			
	private JFrame options = new JFrame();
	private JList<T> selectedItemsList = new JList<T>();
	private JTextField selected = new JTextField(10);
	
	
}

