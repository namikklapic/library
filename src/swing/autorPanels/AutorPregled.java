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
import javax.swing.SwingConstants;

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
		
		searchClicked = false;
		
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setBounds(12, 16, 676, 571);
		
		searchLabel = new JLabel("Type name to search :");
		searchLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		searchLabel.setBounds(470, 62, 194, 25);
		txtSearchFilter = new JTextField(10);
		txtSearchFilter.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtSearchFilter.setBounds(470, 100, 194, 25);
		txtSearchFilter.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSearchFilter.setBackground(Color.WHITE);
			}
		});
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(521, 138, 97, 37);
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				searchClicked = true;
				refreshTable();
				searchClicked = false;
			}
		});
		searchBtn.setBorder(null);
		searchBtn.setFocusPainted(false);
		searchBtn.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		searchBtn.setForeground(new Color(255, 255, 255));
		searchBtn.setBackground(Color.DARK_GRAY);
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				searchBtn.setBackground(Color.GRAY);			
				}
			@Override
			public void mouseExited(MouseEvent e) {
				searchBtn.setBackground(Color.DARK_GRAY);
			}
		});
		
		panel.add(searchLabel);
		panel.add(txtSearchFilter);
		panel.add(searchBtn);
		
			scrollPane = new JScrollPane();
			scrollPane.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
			scrollPane.getViewport().setBackground(new Color(255, 255, 255,20));
			scrollPane.setOpaque(false);
			scrollPane.setBounds(0, 62, 458, 509);
			
			model = new AutorTableModel(autorServiceBean.getAll());
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
						searchClicked = true;
						message = "No item selected!";
						displayMessageDialogBox();
						searchClicked = false;
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
			
			lblViewAllAuthors = new JLabel("View all authors");
			lblViewAllAuthors.setHorizontalAlignment(SwingConstants.CENTER);
			lblViewAllAuthors.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
			lblViewAllAuthors.setBounds(170, 13, 355, 32);
			panel.add(lblViewAllAuthors);
		
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
		if(this.isVisible() ==  false || searchClicked == false)
			return;
		JOptionPane dialogBox = new JOptionPane();
		dialogBox.showMessageDialog(panel, message);
	}
	
	private void clearUIElements(){
		txtSearchFilter.setText("");
		txtSearchFilter.setBackground(Color.WHITE);
		table.getSelectionModel().clearSelection();
		
		model = new AutorTableModel(autorServiceBean.getAll());
		table.setModel(model);
	}
	
	public void refreshTable() {
		String filter = txtSearchFilter.getText();
		boolean success = true;
		
		if(filter.equals(null) || filter.equals("")){
			txtSearchFilter.setBackground(Color.LIGHT_GRAY);
			message = "Please, enter the value in search filter!";
			success = false;
			displayMessageDialogBox();
		}
		else if(filter.equals("*")){
			model = new AutorTableModel(autorServiceBean.getAll());
		}
		else{
			String[] spliter = filter.split(" ");
			if(spliter.length != 2){
				message = "Please, enter author's first and last name!";
				txtSearchFilter.setBackground(Color.LIGHT_GRAY);
				success = false;
				displayMessageDialogBox();
				getContentPane().repaint();
				getContentPane().revalidate();
			}else
				model = new AutorTableModel(autorServiceBean.getAutorByFullName(spliter[0], spliter[1]));
		}
		
		if(success){
			table.setModel(model);
			if(table.getRowCount() == 0){
				message = "No result found!";
				displayMessageDialogBox();
			}
		}
		getContentPane().repaint();
		getContentPane().revalidate();
		
	}
	
	public void prikazi() { setVisible(true); }
	
	private JPanel panel;
	private String message;
	
	private AutorTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	
	private Boolean searchClicked;
	
	private JLabel searchLabel;
	private JTextField txtSearchFilter;
	private JButton searchBtn;
	
	private AutorServiceBean autorServiceBean = new AutorServiceBean();
	private JLabel lblViewAllAuthors;
}
