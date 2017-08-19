package swing.autorPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bussines.AutorServiceBean;
import jpa.Autor;
import tableModel.AutorTableModel;
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
		int sirinaProzora = 800;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(800, 600);
		setResizable(false);
		
		AutorTableModel model = new AutorTableModel(autorServiceBean.getAllAutor());
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setBounds(12, 16, 776, 571);
		
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
			scrollPane.getViewport().setBackground(new Color(255, 255, 255,20));
			scrollPane.setOpaque(false);
			scrollPane.setBounds(0, 0, 458, 569);
			JTable table = new JTable(model);
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
			
			JButton edit = new JButton("Uredi");
			edit.setBounds(578, 346, 97, 37);
			edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event){
					Autor a = model.getAutor(table.getSelectedRow());
					NoviAutor na = new NoviAutor(a);
					na.prikazi();
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
			btnCancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
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
			btnCancel.setBounds(578, 409, 97, 37);
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
	
	private JPanel panel;
	private List<Autor> autori;
	private AutorServiceBean autorServiceBean = new AutorServiceBean();
}
