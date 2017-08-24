package swing.izdavacPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bussines.IzdavacServiceBean;
import jpa.Autor;
import jpa.Izdavac;
import swing.autorPanels.AutorPregled;
import swing.autorPanels.NoviAutor;
import tableModel.AutorTableModel;
import tableModel.IzdavacTableModel;

public class IzdavacPregled extends JFrame {
	
	public IzdavacPregled() {
		
		setTitle("View all publishers");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 600;
		int sirinaProzora = 700;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(700, 600);
		setResizable(false);
		
		panel  = new JPanel();
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setBounds(12, 13, 676, 574);
		panel.setLayout(null);
		
		searchLabel = new JLabel("Publisher: ");
		txtSearchFilter = new JTextField(10);
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				refreshTable();
				
			}
		});
		
		panel.add(searchLabel);
		panel.add(txtSearchFilter);
		panel.add(searchBtn);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		scrollPane.getViewport().setBackground(new Color(255, 255, 255,20));
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 0, 458, 574);
		
		model = new IzdavacTableModel();
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getContentPane().repaint();
				getContentPane().revalidate();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				getContentPane().repaint();
				getContentPane().revalidate();
			}
		});
		table.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		table.setRowHeight(30);
		table.setBackground(new Color(255, 255, 255,150));
		table.getTableHeader().setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(255, 255, 255,150));
		
		JButton edit = new JButton("Edit");
		edit.setBounds(522, 346, 97, 37);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow > -1){
					Izdavac i = model.getIzdavac(table.getSelectedRow());
					NoviIzdavac ni = new NoviIzdavac(i);
					ni.prikazi();
				}else{
					message = "No item selected!";
					displayMessageDialogBox();
				}
			}
		});
		edit.setBorder(null);
		edit.setFocusPainted(false);
		edit.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		edit.setForeground(new Color(255, 255, 255));
		edit.setBackground(Color.DARK_GRAY);
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				edit.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				edit.setBackground(Color.DARK_GRAY);
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnCancel.setBackground(Color.DARK_GRAY);
				clearUIElements();
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnCancel.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCancel.setBackground(Color.DARK_GRAY);
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		btnCancel.setFocusPainted(false);
		btnCancel.setBorder(null);
		btnCancel.setBackground(Color.DARK_GRAY);
		btnCancel.setBounds(522, 409, 97, 37);
		panel.add(btnCancel);
		
		getContentPane().setLayout(null);
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(edit);
		
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AutorPregled.class.getResource("/swing/images/background.jpg")));
		lblNewLabel.setBounds(0, 0, 800, 800);
		getContentPane().add(lblNewLabel);
				
	}
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem item = new JMenuItem("Publisher review");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(true);
			}
		});
		return item;
	}
	
	private void displayMessageDialogBox(){
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private void clearUIElements(){
		txtSearchFilter.setText("");
		txtSearchFilter.setBackground(Color.WHITE);
		table.getSelectionModel().clearSelection();
		
		model = new IzdavacTableModel();
		table.setModel(model);
	}
	
	public void refreshTable() {
		String filter = txtSearchFilter.getText();
		if(filter.equals(null) || filter.equals("")){
			model = new IzdavacTableModel(izdavacServiceBean.getAllIzdavac());
		}else
			model = new IzdavacTableModel(izdavacServiceBean.getIzdavacByNaziv(filter));
		
		table.setModel(model);
		
		if(table.getRowCount() == 0){
			message = "No result found!";
			displayMessageDialogBox();
		}
	}
	
	public void prikazi() { setVisible(true); }
	
	private JPanel panel;
	private String message;
	
	private JScrollPane scrollPane;
	private IzdavacTableModel model;
	private JTable table;
	
	private JLabel searchLabel;
	private JTextField txtSearchFilter;
	private JButton searchBtn;
	
	private IzdavacServiceBean izdavacServiceBean = new IzdavacServiceBean();

}
