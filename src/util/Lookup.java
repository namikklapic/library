package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import swing.autorPanels.NoviAutor;

public class Lookup<T> extends JPanel {

	private static final long serialVersionUID = 1L;

	public Lookup (List<T> items){
		// this section will be visible when lookup is opened
		
		DefaultListModel<T> listModel = new DefaultListModel<T>();
		DefaultListModel<T> selectedItemsListModel = new DefaultListModel<T>();
		for(T element : items) {
			listModel.addElement(element);
		}
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 400;
		int sirinaProzora = 400;
		options.setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		options.setUndecorated(true);
		
	
		JScrollPane scrollOptions = new JScrollPane();
		scrollOptions.setBackground(new Color(255,255,255,70));
		scrollOptions.setBounds(12, 13, 173, 265);
		scrollOptions.setPreferredSize(new Dimension(120, 180));
		JList<T> selectionLista = new JList<T>(listModel);
		selectionLista.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		selectionLista.setBackground(new Color(255,255,255,70));
		selectionLista.setSize(118, 215);
		scrollOptions.setViewportView(selectionLista);
		
		JScrollPane scrollSelection = new JScrollPane();
		scrollSelection.setBackground(new Color(255,255,255,70));
		scrollSelection.setBounds(197, 13, 167, 265);
		scrollSelection.setPreferredSize(new Dimension(120, 180));
		selectedItemsList.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		selectedItemsList.setBackground(new Color(255,255,255,70));
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
				
				options.repaint();
				options.revalidate();
			}
		});
		
		selectedItemsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if(!event.getValueIsAdjusting() && selectedItemsList.getSelectedValue() != null) {
					try{
						listModel.addElement(selectedItemsList.getSelectedValue());
						selectedItemsListModel.remove(selectedItemsList.getSelectedIndex());
					}catch(Exception e) {}
				}
				
				options.repaint();
				options.revalidate();
			}
		});
		
		
		JPanel lookupPanel = new JPanel();
		lookupPanel.setBounds(12, 13, 376, 374);
		lookupPanel.setBackground(new Color(255,255,255,70));
		potvrdi = new JButton("Confirm");
		potvrdi.setBounds(91, 324, 89, 37);
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setSelectionText();
				options.dispose();
			}
		});
		potvrdi.setBorder(null);
		potvrdi.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		potvrdi.setForeground(new Color(255, 255, 255));
		potvrdi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				potvrdi.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				potvrdi.setBackground(Color.DARK_GRAY);

			}
		});
		potvrdi.setBackground(Color.DARK_GRAY);
		
		ponisti = new JButton("Cancel");
		ponisti.setBounds(195, 324, 89, 37);
		ponisti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				selectedItemsListModel.removeAllElements();
				options.dispose();
				ponisti.setBackground(Color.DARK_GRAY);
			}
		});
		ponisti.setBorder(null);
		ponisti.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		ponisti.setForeground(new Color(255, 255, 255));
		ponisti.setBackground(Color.DARK_GRAY);
		ponisti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ponisti.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ponisti.setBackground(Color.DARK_GRAY);

			}
		});
		options.getContentPane().setLayout(null);
		lookupPanel.setLayout(null);
		
		lookupPanel.add(scrollOptions);
		lookupPanel.add(scrollSelection);
		
		lookupPanel.add(potvrdi);
		lookupPanel.add(ponisti);
		options.getContentPane().add(lookupPanel);
		options.setSize(400, 400);
		
		JLabel backgroundPicture = new JLabel("");
		backgroundPicture.setIcon(new ImageIcon(NoviAutor.class.getResource("/swing/images/background.jpg")));
		backgroundPicture.setBounds(0, 0, 400, 400);
		options.getContentPane().add(backgroundPicture);
		
		
		// this section will be visible when lookup is included into panel
		
		openDialog = new JButton("Select");
		openDialog.setBounds(204, 5, 75, 37);
		openDialog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				options.setVisible(true);
			}
		});
		openDialog.setBorder(null);
		openDialog.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		openDialog.setForeground(new Color(255, 255, 255));
		openDialog.setBackground(Color.DARK_GRAY);
		openDialog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				openDialog.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				openDialog.setBackground(Color.DARK_GRAY);

			}
		});
		setLayout(null);
		setBackground(new Color(255,255,255,70));
		selected.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		selected.setBounds(12, 11, 180, 24);
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

