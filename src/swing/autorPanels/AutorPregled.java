package swing.autorPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bussines.AutorServiceBean;
import jpa.Autor;
import tableModel.AutorTableModel;
import tableModel.VrstaKnjigeTableModel;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AutorPregled extends JFrame {
	
	public AutorPregled() {
		
		setTitle("View all authors");
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 600;
		int sirinaProzora = 700;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(700, 600);
		setResizable(false);
		
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setBounds(12, 16, 676, 571);
		
		searchLabel = new JLabel("Author: ");
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
			scrollPane.setBounds(0, 0, 458, 571);
			
			model = new AutorTableModel();
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
			edit.setBounds(521, 345, 97, 37);
			edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event){
					int selectedRow = table.getSelectedRow();
					if(selectedRow > -1){
						Autor a = model.getAutor(table.getSelectedRow());
						NoviAutor na = new NoviAutor(a);
						na.prikazi();
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
			

			panel.setLayout(null);
			
			scrollPane.setViewportView(table);
			panel.add(scrollPane);
			panel.add(edit);
			
			getContentPane().add(panel);
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setBackground(Color.DARK_GRAY);
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
			btnCancel.setBounds(521, 408, 97, 37);
			panel.add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AutorPregled.class.getResource("/swing/images/background.jpg")));
		lblNewLabel.setBounds(0, 0, 800, 800);
		getContentPane().add(lblNewLabel);
		
		
	}
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem item = new JMenuItem("Author review");
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
		
		model = new AutorTableModel();
		table.setModel(model);
	}
	
	public void refreshTable() {
		String filter = txtSearchFilter.getText();
		if(filter.equals(null) || filter.equals("")){
			model = new AutorTableModel(autorServiceBean.getAllAutor());
		}else{
			String[] spliter = filter.split(" ");
			if(spliter.length != 2){
				message = "Please, enter author's first and last name!";
				txtSearchFilter.setBackground(Color.RED);
				displayMessageDialogBox();
			}else
				model = new AutorTableModel(autorServiceBean.getAutorByFullName(spliter[0], spliter[1]));
		}
		
		table.setModel(model);
		
		if(table.getRowCount() == 0){
			message = "No result found!";
			displayMessageDialogBox();
		}
	}
	
	public void prikazi() { setVisible(true); }
	
	private JPanel panel;
	private String message;
	
	private AutorTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	
	private JLabel searchLabel;
	private JTextField txtSearchFilter;
	private JButton searchBtn;
	
	private AutorServiceBean autorServiceBean = new AutorServiceBean();
}
