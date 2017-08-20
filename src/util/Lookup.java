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
		potvrdi = new JButton("Confirm");
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setSelectionText();
				options.dispose();
			}
		});
		ponisti = new JButton("Cancel");
		ponisti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				options.dispose();
			}
		});
		
		lookupPanel.add(scrollOptions);
		lookupPanel.add(scrollSelection);
		
		lookupPanel.add(potvrdi);
		lookupPanel.add(ponisti);
		options.add(lookupPanel);
		options.setSize(300, 300);
		
		
		// this section will be visible when lookup is included into panel
		
		openDialog = new JButton("Select");
		openDialog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				options.setVisible(true);
			}
		});
		//cancelSelection = new JButton("Abort");
		add(selected);
		add(openDialog);
		//add(cancelSelection);
		
		
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
	
	public void initializeLookup(){
		selected.setText("");
		selectedItemsList.clearSelection(); //ovo iz nekog razloga ne radi ?!
	}
	
	public void removeButtons() {
		remove(potvrdi);
		remove(ponisti);
		remove(openDialog);
		//remove(cancelSelection);
		selected.setEditable(false);
	}
	
	public JTextField getSelected(){
		return selected;
	}
			
	private JFrame options = new JFrame();
	private JList<T> selectedItemsList = new JList<T>();
	private JTextField selected = new JTextField(10);
	private JButton potvrdi;
	private JButton ponisti;
	private JButton openDialog;
	//private JButton cancelSelection;
}

