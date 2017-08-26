package swing.nastavniciPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import bussines.NastavnikServiceBean;
import jpa.Autor;
import jpa.Nastavnik;
import swing.autorPanels.AutorPregled;
import swing.autorPanels.NoviAutor;
import tableModel.AutorTableModel;
import tableModel.NastavnikTableModel;

public class NastavniciPregled extends JFrame{
	
	public NastavniciPregled(){
		
		setTitle("Pregled nastavnika");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 600;
		int sirinaProzora = 700;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(700, 600);
		setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setBounds(12, 16, 676, 571);
		
		searchLabel = new JLabel("Type name to search :");
		searchLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		searchLabel.setBounds(78, 446, 176, 30);
		txtSearchFilter = new JTextField(10);
		txtSearchFilter.setBounds(266, 449, 176, 30);
		searchBtn = new JButton("Search");
		searchBtn.setBounds(454, 446, 97, 37);
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				refreshTable();
			}
		});

		panel.add(searchLabel);
		panel.add(txtSearchFilter);
		panel.add(searchBtn);

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

		panel.add(searchBtn);
		panel.add(txtSearchFilter);
		panel.add(searchLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		scrollPane.getViewport().setBackground(new Color(255, 255, 255,20));
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 0, 676, 418);
		
		model = new NastavnikTableModel();
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
		table.getTableHeader().setFont(new Font("Segoe UI Emoji", Font.PLAIN, 17));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(255, 255, 255,150));
		
		JButton edit = new JButton("Edit");
		edit.setBounds(225, 521, 97, 37);
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				int selectedRow = table.getSelectedRow();
				if(selectedRow > -1){
					Nastavnik n = model.getNastavnik(table.getSelectedRow());
					NoviNastavnik nn = new NoviNastavnik(n);
					nn.prikazi();
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
		btnCancel.setBounds(360, 521, 97, 37);
		panel.add(btnCancel);
		
		getContentPane().setLayout(null);
		panel.setLayout(null);
		
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		panel.add(edit);
		
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AutorPregled.class.getResource("/swing/images/background.jpg")));
		lblNewLabel.setBounds(0, 0, 800, 800);
		getContentPane().add(lblNewLabel);
		
	}
	public JMenuItem getMenuItem(JPanel parent){
		JMenuItem item = new JMenuItem("Teacher review");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
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
		
		model = new NastavnikTableModel();
		table.setModel(model);
	}
	
	public void refreshTable() {
		String filter = txtSearchFilter.getText();
		if(filter.equals(null) || filter.equals("")){
			model = new NastavnikTableModel(nastavnikServiceBean.getAllNastavnik());
		}else{
			String[] spliter = filter.split(" ");
			if(spliter.length != 2){
				message = "Please, enter teacher's first and last name!";
				txtSearchFilter.setBackground(Color.RED);
				displayMessageDialogBox();
			}else
				model = new NastavnikTableModel(nastavnikServiceBean.getNastavniciByFullName(spliter[0], spliter[1]));
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
	
	private JScrollPane scrollPane;
	private NastavnikTableModel model;
	private JTable table;
	
	private JLabel searchLabel;
	private JTextField txtSearchFilter;
	private JButton searchBtn;
	
	private NastavnikServiceBean nastavnikServiceBean = new NastavnikServiceBean();

}

