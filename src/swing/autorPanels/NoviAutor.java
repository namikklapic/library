package swing.autorPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bussines.AutorServiceBean;
import jpa.Autor;
import jpa.Izdavac;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;

public class NoviAutor extends JFrame {
	
	public NoviAutor() {
		
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 400;
		int sirinaProzora = 400;
		setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		setUndecorated(true);
		
		setSize(400, 300);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,150));
		panel.setLocation(12, 16);

		panel.setSize(376, 271);
		
		JLabel imeAutora = new JLabel("Author's First name");
		imeAutora.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		imeAutora.setBounds(12, 62, 154, 19);
		
		JLabel prezimeAutora = new JLabel("Author's Last name");
		prezimeAutora.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		prezimeAutora.setBounds(12, 122, 154, 16);
		
		JButton potvrdi = new JButton("Add");
		potvrdi.setBounds(88, 222, 82, 36);
		potvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				autorServiceBean.save(new Autor(autorServiceBean.getCount() + 1, 
						txtIme.getText(), txtPrezime.getText()));
			}
		});
		potvrdi.setBorder(null);
		potvrdi.setFocusPainted(false);
		potvrdi.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		potvrdi.setForeground(new Color(255, 255, 255));
		potvrdi.setBackground(Color.DARK_GRAY);
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
		
		
		JButton ponisti = new JButton("Cancel");
		ponisti.setBounds(198, 222, 82, 36);
		ponisti.setBorder(null);
		ponisti.setFocusPainted(false);
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
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		panel.setLayout(null);
		
		panel.add(imeAutora);
		txtIme.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtIme.setBounds(178, 56, 186, 30);
		panel.add(txtIme);
		panel.add(prezimeAutora);
		txtPrezime.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtPrezime.setBounds(178, 115, 186, 30);
		panel.add(txtPrezime);
		panel.add(potvrdi);
		panel.add(ponisti);
		
		getContentPane().add(panel);
		
		JLabel backgroundPicture = new JLabel("");
		backgroundPicture.setIcon(new ImageIcon(NoviAutor.class.getResource("/swing/images/background.jpg")));
		backgroundPicture.setBounds(0, 0, 400, 300);
		getContentPane().add(backgroundPicture);
		
		
	}
	
	public NoviAutor(Autor a) {
		this();
		txtIme.setText(a.getImeAutora());
		txtPrezime.setText(a.getPrezimeAutora());
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem item = new JMenuItem("New author");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				prikazi();
			}
		});
		return item;
	}
	
	public void prikazi() {
		setVisible(true);
	}
	
	private AutorServiceBean autorServiceBean = new AutorServiceBean();
	private JTextField txtIme = new JTextField(20);
	private JTextField txtPrezime = new JTextField(30);

}
