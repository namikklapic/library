package swing;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import jpa.Bibliotekar;
import swing.VrstaKnjigePanels.NovaVrstaKnjige;
import swing.VrstaKnjigePanels.VrstaKnjigePregled;
import swing.autorPanels.AutorPregled;
import swing.autorPanels.NoviAutor;
import swing.izdavacPanels.IzdavacPregled;
import swing.izdavacPanels.NoviIzdavac;
import swing.knjigaPanels.KnjigaPregled;
import swing.knjigaPanels.NovaKnjiga;
import swing.nastavniciPanels.NastavniciPregled;
import swing.nastavniciPanels.NoviNastavnik;
import swing.posudbaPanels.NovaPosudba;
import swing.posudbaPanels.PosudbePregled;
import swing.predmetPanels.NoviPredmet;
import swing.predmetPanels.PredmetPregled;
import swing.studentiPanels.NoviStudent;
import swing.studentiPanels.StudentPregled;

public class PanelBibliotekar extends JFrame{

	public PanelBibliotekar (Bibliotekar b){

		// Frame options -- BEGIN
		this.bibliotekar = b;
		setTitle(b.getKorisnik().getImeKorisnika() + " " + b.getKorisnik().getPrezimeKorisnika());
		setResizable(false);
		getContentPane().setLayout(null);

		// Frame options -- END
		
		// Panels definition and options -- BEGIN
		
		// Overall panel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(new Color(51, 51, 51));
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 78, 742);
		getContentPane().add(panel);
	
		// Middle panel
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new CardLayout(0, 0));
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(244, 0, 743, 742);
		getContentPane().add(panel_1);
		
		CardLayout cl = (CardLayout)(panel_1.getLayout());
		
		// Disappearing panel
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setLayout(null);
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panel_2.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				panel_2.setVisible(false);
			}
		});
		panel_2.setVisible(false);
		panel_2.setBackground(new Color(65, 65, 65));
		panel_2.setBounds(78, 0, 165, 742);
		getContentPane().add(panel_2);
		
		// Right panel 
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(985, 0, 209, 742);
		getContentPane().add(infoPanel);
		infoPanel.setBackground(new Color(51,51,51));
		infoPanel.setLayout(null);
		
		// Implementation of clock in right panel -- BEGIN
		
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
					
							class ClockLabel extends JLabel implements ActionListener {
			
								  public ClockLabel() {
								    super("" + sdf.format(date));
								    Timer t = new Timer(1000, this);
								    t.start();
								  }
			
								  public void actionPerformed(ActionEvent ae) {	
									Date date = new Date();
									SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
									
								    setText(sdf.format(date));
								  }
								}
							
							JLabel Clock = new ClockLabel();
							Clock.setFont(new Font("Segoe UI Emoji", Font.BOLD, 67));
							Clock.setForeground(Color.WHITE);
							Clock.setBounds(12, 13, 202, 104);
							
							
							Clock.setVisible(true);
					
					infoPanel.add(Clock);
		
		// Implementation of clock in right panel -- END
		
					
		
		
		// Panels definition end options -- END
		
		// Menu bar options -- BEGIN
		JMenuBar menuBar = new JMenuBar();
		
		
		JMenu knjiga = new JMenu("Books");
		knjiga.add(novaKnjiga.getMenuItem());
		knjiga.add(pregledKnjiga.getMenuItem(panel));
		
		JMenu vrstaKnjige = new JMenu("Book type");
		vrstaKnjige.add(novaVrstaKnjige.getMenuItem());
		vrstaKnjige.add(vrstaKnjigePregled.getMenuItem(panel));
		knjiga.add(vrstaKnjige);
		
		JMenu autori = new JMenu("Authors");
		autori.add(noviAutor.getMenuItem());
		autori.add(autorPregled.getMenuItem(panel));
		knjiga.add(autori);
		
		JMenu izdavac = new JMenu("Publishers");
		izdavac.add(noviIzdavac.getMenuItem());
		izdavac.add(pregledIzdavac.getMenuItem(panel));
		knjiga.add(izdavac);
		
		JMenu nastavnici = new JMenu("Teachers");
		nastavnici.add(noviNastavnik.getMenuItem());
		nastavnici.add(nastavniciPregled.getMenuItem(panel));
		
		JMenu studenti = new JMenu("Students");
		studenti.add(noviStudent.getMenuItem());
		studenti.add(studentPregled.getMenuItem(panel));
		
		JMenu predmeti = new JMenu("Subjects");
		predmeti.add(noviPredmet.getMenuItem());
		predmeti.add(predmetPregled.getMenuItem(panel));
		
		JMenu posudbe = new JMenu("Book loans");
		posudbe.add(novaPosudba.getMenuItem());
		posudbe.add(posudbePregled.getMenuItem(panel));
				
		menuBar.add(knjiga);
		menuBar.add(nastavnici);
		menuBar.add(studenti);
		menuBar.add(predmeti);
		menuBar.add(posudbe);
		
		setJMenuBar(menuBar);
		
		// Menu bar options -- END
		
		// Items on right panel -- BEGIN
		Box lineExit = Box.createHorizontalBox();
		lineExit.setVisible(false);
		lineExit.setOpaque(true);
		lineExit.setForeground(Color.WHITE);
		lineExit.setBackground(Color.WHITE);
		lineExit.setBounds(125, 715, 34, 3);
		infoPanel.add(lineExit);
		
		Box lineLogout = Box.createHorizontalBox();
		lineLogout.setVisible(false);
		lineExit.setVisible(false);
		lineLogout.setOpaque(true);
		lineLogout.setBackground(Color.WHITE);
		lineLogout.setForeground(Color.WHITE);
		lineLogout.setBounds(67, 715, 34, 3);
		infoPanel.add(lineLogout);
		
		JLabel exit = new JLabel("");
		exit.setName("65");
		exit.setHorizontalAlignment(SwingConstants.CENTER);
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int confirmed = JOptionPane.showConfirmDialog(null, 
				        "Are you sure you want to exit the program?", "Exit confirmation box",
				        JOptionPane.YES_NO_OPTION);
				
				if (confirmed == JOptionPane.YES_OPTION) {
				      System.exit(1);
				    }
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lineExit.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lineExit.setVisible(false);
			}
		});
		exit.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/power-2-48.png")));
		exit.setBounds(118, 659, 48, 70);
		infoPanel.add(exit);
		
		JLabel logout = new JLabel("");
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int confirmed = JOptionPane.showConfirmDialog(null, 
				        "Are you sure you want to logout from your account?", "Logout confirmation box",
				        JOptionPane.YES_NO_OPTION);
				
				if (confirmed == JOptionPane.YES_OPTION) {
						dispose();
					
				      	JFrame frame = new JFrame();
							
						Toolkit kit = Toolkit.getDefaultToolkit();
						Dimension velicinaEkrana = kit.getScreenSize();
						int visinaProzora = 600;
						int sirinaProzora = 400;
						
						PanelPrijava panelPrijava = new PanelPrijava(frame);
						frame.setSize(400, 600);
						frame.setResizable(false);
	
						frame.setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.getContentPane().add(panelPrijava);
						frame.setTitle("Login");
						
						frame.setVisible(true);
				    }
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lineLogout.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lineLogout.setVisible(false);
			}
		});
		logout.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/account-logout-24.png")));
		logout.setHorizontalAlignment(SwingConstants.CENTER);
		logout.setBounds(58, 659, 48, 70);
		infoPanel.add(logout);
		
		// Items on right panel -- END
		
		// Middle panel items -- BEGIN
		JPanel panelBooks = new JPanel();
		panelBooks.setBorder(null);
		panelBooks.setBackground(new Color(95, 158, 160));
		panelBooks.setLayout(null);
		panel_1.add(panelBooks, "panelBooksName");
		
		JPanel defaultPanel = new JPanel();
		defaultPanel.setBackground(new Color(95, 158, 160));
		panel_1.add(defaultPanel, "defaultPanel");
		cl.show(panel_1, "defaultPanel");		

			// Panel Books items -- BEGIN
					// Lines -- BEGIN
						Box lineAddBook = Box.createHorizontalBox();
						lineAddBook.setVisible(false);
						lineAddBook.setOpaque(true);
						lineAddBook.setForeground(Color.WHITE);
						lineAddBook.setBackground(Color.WHITE);
						lineAddBook.setBounds(35, 266, 231, 3);
						panelBooks.add(lineAddBook);
						
						Box lineViewAllBooks = Box.createHorizontalBox();
						lineViewAllBooks.setVisible(false);
						lineViewAllBooks.setOpaque(true);
						lineViewAllBooks.setForeground(Color.WHITE);
						lineViewAllBooks.setBackground(Color.WHITE);
						lineViewAllBooks.setBounds(425, 266, 231, 3);
						panelBooks.add(lineViewAllBooks);
						
						Box lineAddBookType = Box.createHorizontalBox();
						lineAddBookType.setVisible(false);
						lineAddBookType.setOpaque(true);
						lineAddBookType.setForeground(Color.WHITE);
						lineAddBookType.setBackground(Color.WHITE);
						lineAddBookType.setBounds(15, 471, 279, 3);
						panelBooks.add(lineAddBookType);
						
						Box lineViewBookType = Box.createHorizontalBox();
						lineViewBookType.setVisible(false);
						lineViewBookType.setOpaque(true);
						lineViewBookType.setForeground(Color.WHITE);
						lineViewBookType.setBackground(Color.WHITE);
						lineViewBookType.setBounds(413, 471, 262, 3);
						panelBooks.add(lineViewBookType);
					// Lines -- END
					
					// Buttons -- BEGIN
						JLabel addANewBook = new JLabel("Add a new book");
						addANewBook.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent arg0) {
								lineAddBook.setVisible(true);
							}
							@Override
							public void mouseExited(MouseEvent arg0) {
								lineAddBook.setVisible(false);
							}
							@Override
							public void mouseClicked(MouseEvent arg0) {
								//cl.show(panel_1, "defaultPanel");
								//novaKnjiga.prikazi();
								
							}
						});
						addANewBook.setHorizontalAlignment(SwingConstants.CENTER);
						addANewBook.setForeground(new Color(255, 255, 255));
						addANewBook.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
						addANewBook.setBounds(-22, 165, 346, 175);
						panelBooks.add(addANewBook);
						
						JLabel viewAllBooks = new JLabel("View all books");
						viewAllBooks.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent arg0) {
								lineViewAllBooks.setVisible(true);
							}
							@Override
							public void mouseExited(MouseEvent arg0) {
								lineViewAllBooks.setVisible(false);
							}
							@Override
							public void mouseClicked(MouseEvent arg0) {
								//cl.show(panel_1, "defaultPanel");
								//pregledKnjiga.run();
							}
						});
						viewAllBooks.setHorizontalAlignment(SwingConstants.CENTER);
						viewAllBooks.setForeground(Color.WHITE);
						viewAllBooks.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
						viewAllBooks.setBounds(370, 165, 346, 175);
						panelBooks.add(viewAllBooks);
						
						JLabel addBookType = new JLabel("Add a new book type");
						addBookType.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								lineAddBookType.setVisible(true);
							}
							@Override
							public void mouseExited(MouseEvent e) {
								lineAddBookType.setVisible(false);
							}
							@Override
							public void mouseClicked(MouseEvent arg0) {
								//cl.show(panel_1, "defaultPanel");
								//novaVrstaKnjige.prikazi();
							}
						});
						addBookType.setHorizontalAlignment(SwingConstants.CENTER);
						addBookType.setForeground(Color.WHITE);
						addBookType.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
						addBookType.setBounds(-22, 368, 346, 175);
						panelBooks.add(addBookType);
						
						JLabel viewAllBookTypes = new JLabel("View all book types");
						viewAllBookTypes.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								lineViewBookType.setVisible(true);
							}
							@Override
							public void mouseExited(MouseEvent e) {
								lineViewBookType.setVisible(false);
							}
							@Override
							public void mouseClicked(MouseEvent e) {
								//cl.show(panel_1, "defaultPanel");
								//vrstaKnjigePregled.prikazi();
							}
						});
						viewAllBookTypes.setHorizontalAlignment(SwingConstants.CENTER);
						viewAllBookTypes.setForeground(Color.WHITE);
						viewAllBookTypes.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
						viewAllBookTypes.setBounds(370, 368, 346, 175);
						panelBooks.add(viewAllBookTypes);
						
						JLabel lblNewLabel = new JLabel("");
						lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/literature-48.png")));
						lblNewLabel.setBounds(100, 163, 103, 68);
						panelBooks.add(lblNewLabel);
						
						JLabel label = new JLabel("");
						label.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/books-48.png")));
						label.setBounds(515, 155, 57, 76);
						panelBooks.add(label);
						
						JLabel label_1 = new JLabel("");
						label_1.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/search-9-48.png")));
						label_1.setBounds(512, 376, 62, 60);
						panelBooks.add(label_1);
						
						JLabel label_2 = new JLabel("");
						label_2.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/add-file-48.png")));
						label_2.setBounds(123, 360, 57, 76);
						panelBooks.add(label_2);
					// Buttons -- END
					
					
			// Panel Books items -- END				
		// Middle panel items -- END
						
	
		// Disappearing panel items -- BEGIN
						// Lines -- BEGIN
							Box lineBooks = Box.createHorizontalBox();
							lineBooks.setBounds(12, 65, 75, 3);
							lineBooks.setVisible(false);
							lineBooks.setOpaque(true);
							lineBooks.setForeground(Color.WHITE);
							lineBooks.setBackground(Color.WHITE);
							panel_2.add(lineBooks);
							
							Box linePublishers = Box.createHorizontalBox();
							linePublishers.setOpaque(true);
							linePublishers.setVisible(false);
							linePublishers.setForeground(Color.WHITE);
							linePublishers.setBackground(Color.WHITE);
							linePublishers.setBounds(12, 173, 125, 3);
							panel_2.add(linePublishers);
							
							Box lineAuthors = Box.createHorizontalBox();
							lineAuthors.setOpaque(true);
							lineAuthors.setVisible(false);
							lineAuthors.setForeground(Color.WHITE);
							lineAuthors.setBackground(Color.WHITE);
							lineAuthors.setBounds(12, 292, 103, 3);
							panel_2.add(lineAuthors);
							
							Box lineProffessors = Box.createHorizontalBox();
							lineProffessors.setOpaque(true);
							lineProffessors.setVisible(false);
							lineProffessors.setForeground(Color.WHITE);
							lineProffessors.setBackground(Color.WHITE);
							lineProffessors.setBounds(12, 392, 141, 3);
							panel_2.add(lineProffessors);
							
							Box lineClasses = Box.createHorizontalBox();
							lineClasses.setOpaque(true);
							lineClasses.setVisible(false);
							lineClasses.setForeground(Color.WHITE);
							lineClasses.setBackground(Color.WHITE);
							lineClasses.setBounds(12, 502, 90, 3);
							panel_2.add(lineClasses);
					// Lines -- END			
					// Buttons -- BEGIN
							JLabel lblBooks = new JLabel("BOOKS");
							lblBooks.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent arg0) {
									lineBooks.setVisible(true);
									panel_2.setVisible(true);
								}
								@Override
								public void mouseExited(MouseEvent arg0) {
									lineBooks.setVisible(false);
									panel_2.setVisible(false);
								}
								@Override
								public void mouseClicked(MouseEvent arg0) {
									cl.show(panel_1, "panelBooksName");
									
								}
							});
							lblBooks.setBounds(12, 13, 153, 80);
							lblBooks.setHorizontalAlignment(SwingConstants.LEFT);
							lblBooks.setForeground(new Color(255, 255, 255));
							lblBooks.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
							panel_2.add(lblBooks);
							
							JLabel lblPublishers = new JLabel("PUBLISHERS");
							lblPublishers.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent arg0) {
									panel_2.setVisible(true);
									linePublishers.setVisible(true);
								}
								@Override
								public void mouseExited(MouseEvent arg0) {
									panel_2.setVisible(false);
									linePublishers.setVisible(false);
								}
								@Override
								public void mouseClicked(MouseEvent arg0) {
									cl.show(panel_1, "defaultPanel");
								}
							});
							lblPublishers.setHorizontalAlignment(SwingConstants.LEFT);
							lblPublishers.setForeground(Color.WHITE);
							lblPublishers.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
							lblPublishers.setBounds(12, 123, 193, 80);
							panel_2.add(lblPublishers);
							
							JLabel lblAuthors = new JLabel("AUTHORS");
							lblAuthors.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent arg0) {
									panel_2.setVisible(true);
									lineAuthors.setVisible(true);
								}
								@Override
								public void mouseExited(MouseEvent arg0) {
									panel_2.setVisible(false);
									lineAuthors.setVisible(false);
								}
							});
							lblAuthors.setHorizontalAlignment(SwingConstants.LEFT);
							lblAuthors.setForeground(Color.WHITE);
							lblAuthors.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
							lblAuthors.setBounds(12, 241, 193, 80);
							panel_2.add(lblAuthors);
							
							JLabel lblProffessors = new JLabel("PROFFESSORS");
							lblProffessors.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent e) {
									panel_2.setVisible(true);
									lineProffessors.setVisible(true);
								}
								@Override
								public void mouseExited(MouseEvent e) {
									panel_2.setVisible(false);
									lineProffessors.setVisible(false);
								}
							});
							lblProffessors.setHorizontalAlignment(SwingConstants.LEFT);
							lblProffessors.setForeground(Color.WHITE);
							lblProffessors.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
							lblProffessors.setBounds(12, 344, 193, 80);
							panel_2.add(lblProffessors);
							
							JLabel lblClasses = new JLabel("CLASSES");
							lblClasses.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent e) {
									panel_2.setVisible(true);
									lineClasses.setVisible(true);
								}
								@Override
								public void mouseExited(MouseEvent e) {
									panel_2.setVisible(false);
									lineClasses.setVisible(false);
								}
							});
							lblClasses.setHorizontalAlignment(SwingConstants.LEFT);
							lblClasses.setForeground(Color.WHITE);
							lblClasses.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
							lblClasses.setBounds(12, 451, 193, 80);
							panel_2.add(lblClasses);
					// Buttons -- END
		// Disappearing panel items -- END
	
		// Overall panel items -- BEGIN
					// Buttons -- BEGIN
							JLabel Books = new JLabel("");
							Books.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent arg0) {
									panel_2.setVisible(true);
									lblBooks.setVisible(true);
									lineBooks.setVisible(true);
								}
								@Override
								public void mouseExited(MouseEvent e) {
									panel_2.setVisible(false);
									lineBooks.setVisible(false);
								}
							});
							Books.setHorizontalAlignment(SwingConstants.LEFT);
							Books.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/book-48.png")));
							Books.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
							Books.setForeground(Color.WHITE);
							Books.setBounds(12, -3, 66, 113);
							panel.add(Books);
							
							JLabel lblProducer = new JLabel("");
							lblProducer.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/printer-48.png")));
							lblProducer.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent arg0) {
									panel_2.setVisible(true);
									lblPublishers.setVisible(true);
									linePublishers.setVisible(true);
								}
								@Override
								public void mouseExited(MouseEvent e) {
									panel_2.setVisible(false);
									linePublishers.setVisible(false);
								}
							});						
							lblProducer.setHorizontalAlignment(SwingConstants.LEFT);
							lblProducer.setForeground(Color.WHITE);
							lblProducer.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
							lblProducer.setBounds(12, 107, 66, 113);
							panel.add(lblProducer);
							
							JLabel lblAuthor = new JLabel("");
							lblAuthor.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/edit-9-48.png")));
							lblAuthor.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent arg0) {
									panel_2.setVisible(true);
									lineAuthors.setVisible(true);
								}
								@Override
								public void mouseExited(MouseEvent e) {
									panel_2.setVisible(false);
									lineAuthors.setVisible(false);
								}
							});
							lblAuthor.setHorizontalAlignment(SwingConstants.LEFT);
							lblAuthor.setForeground(Color.WHITE);
							lblAuthor.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
							lblAuthor.setBounds(12, 225, 66, 113);
							panel.add(lblAuthor);
							
							JLabel lblProffesor = new JLabel("");
							lblProffesor.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/administrator-2-48.png")));
							lblProffesor.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent arg0) {
									panel_2.setVisible(true);
									lineProffessors.setVisible(true);

								}
								@Override
								public void mouseExited(MouseEvent e) {
									panel_2.setVisible(false);
									lineProffessors.setVisible(false);
								}
							});
							lblProffesor.setHorizontalAlignment(SwingConstants.LEFT);
							lblProffesor.setForeground(Color.WHITE);
							lblProffesor.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
							lblProffesor.setBounds(12, 328, 66, 113);
							panel.add(lblProffesor);
							
							JLabel lblSubject = new JLabel("");
							lblSubject.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/school-48.png")));
							lblSubject.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent arg0) {
									panel_2.setVisible(true);
									lineClasses.setVisible(true);
								}
								@Override
								public void mouseExited(MouseEvent e) {
									panel_2.setVisible(true);
									lineClasses.setVisible(false);
								}
							});
							lblSubject.setHorizontalAlignment(SwingConstants.LEFT);
							lblSubject.setForeground(Color.WHITE);
							lblSubject.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
							lblSubject.setBounds(12, 435, 66, 113);
							panel.add(lblSubject);
					// Buttons -- END
		// Overall panel items -- END
							
	// Main menu background options -- BEGIN
		JLabel MenuBackground = new JLabel((String) null);
		MenuBackground.setOpaque(true);
		MenuBackground.setBackground(new Color(95, 158, 160));
		MenuBackground.setBounds(0, 0, 1206, 742);
		getContentPane().add(MenuBackground);
	// Main menu background options -- END
							
	}
	private Bibliotekar bibliotekar;

	private NovaKnjiga novaKnjiga = new NovaKnjiga();
	private KnjigaPregled pregledKnjiga = new KnjigaPregled(true);
	
	private NovaVrstaKnjige novaVrstaKnjige = new NovaVrstaKnjige();
	private VrstaKnjigePregled vrstaKnjigePregled = new VrstaKnjigePregled();
	
	private NoviAutor noviAutor = new NoviAutor();
	private AutorPregled autorPregled = new AutorPregled();
	
	private NoviIzdavac noviIzdavac = new NoviIzdavac();
	private IzdavacPregled pregledIzdavac = new IzdavacPregled();
	
	private NoviNastavnik noviNastavnik  = new NoviNastavnik();
	private NastavniciPregled nastavniciPregled = new NastavniciPregled();
	
	private NoviStudent noviStudent = new NoviStudent();
	private StudentPregled studentPregled = new StudentPregled();
	
	private NoviPredmet noviPredmet = new NoviPredmet();
	private PredmetPregled predmetPregled = new PredmetPregled();
	
	private NovaPosudba novaPosudba = new NovaPosudba();
	private PosudbePregled posudbePregled = new PosudbePregled();
}
