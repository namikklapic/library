package swing;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class PanelBibliotekar extends JFrame{
	

	public PanelBibliotekar (Bibliotekar b){

		// Frame options -- BEGIN
		this.bibliotekar = b;
		pregledKnjiga = new KnjigaPregled(true, bibliotekar.getKorisnik());
		setTitle("Welcome "+ b.getKorisnik().getImeKorisnika() + " " + b.getKorisnik().getPrezimeKorisnika());
		setResizable(false);
		getContentPane().setLayout(null);
		
		setUndecorated(true);
		

		// Frame options -- END
		
		// Panels definition and options -- BEGIN
		
		// Overall panel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(new Color(51, 51, 51,180));
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 78, 790);
		getContentPane().add(panel);
	
	
		
		// Disappearing panel
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setLayout(null);
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panel_2.setVisible(true);
				repaint();
				revalidate();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				panel_2.setVisible(false);
				repaint();
				revalidate();
			}
		});
		panel_2.setVisible(false);
		panel_2.setBackground(new Color(65, 65, 65,220));
		panel_2.setBounds(78, 0, 165, 804);
		getContentPane().add(panel_2);
		
		// Middle panel
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192,30));
		panel_1.setLayout(new CardLayout(0, 0));
		panel_1.setBounds(90, 13, 883, 748);
		getContentPane().add(panel_1);
		
		CardLayout cl = (CardLayout)(panel_1.getLayout());
		// Buttons -- END
		
		
			// Panel Books items -- END				
		// Middle panel items -- END
			
	
		// Disappearing panel items -- BEGIN
			// Lines -- BEGIN
				Box lineBooks = Box.createHorizontalBox();
				lineBooks.setBounds(10, 153, 74, 3);
				lineBooks.setVisible(false);
				lineBooks.setOpaque(true);
				lineBooks.setForeground(Color.WHITE);
				lineBooks.setBackground(Color.WHITE);
				panel_2.add(lineBooks);
				
				Box lineLoans = Box.createHorizontalBox();
				lineLoans.setOpaque(true);
				lineLoans.setVisible(false);
				lineLoans.setForeground(Color.WHITE);
				lineLoans.setBackground(Color.WHITE);
				lineLoans.setBounds(10, 63, 141, 3);
				panel_2.add(lineLoans);
				
				Box lineAuthors = Box.createHorizontalBox();
				lineAuthors.setOpaque(true);
				lineAuthors.setVisible(false);
				lineAuthors.setForeground(Color.WHITE);
				lineAuthors.setBackground(Color.WHITE);
				lineAuthors.setBounds(10, 455, 110, 3);
				panel_2.add(lineAuthors);
				
				Box lineProffessors = Box.createHorizontalBox();
				lineProffessors.setOpaque(true);
				lineProffessors.setVisible(false);
				lineProffessors.setForeground(Color.WHITE);
				lineProffessors.setBackground(Color.WHITE);
				lineProffessors.setBounds(10, 255, 128, 3);
				panel_2.add(lineProffessors);
				
				Box lineClasses = Box.createHorizontalBox();
				lineClasses.setOpaque(true);
				lineClasses.setVisible(false);
				lineClasses.setForeground(Color.WHITE);
				lineClasses.setBackground(Color.WHITE);
				lineClasses.setBounds(10, 353, 90, 3);
				panel_2.add(lineClasses);
				// Lines -- END			
				// Buttons -- BEGIN
						JLabel lblBooks = new JLabel("BOOKS");
						lblBooks.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent arg0) {
								lineBooks.setVisible(true);
								panel_2.setVisible(true);
								repaint();
								revalidate();
							}
							@Override
							public void mouseExited(MouseEvent arg0) {
								lineBooks.setVisible(false);
								panel_2.setVisible(false);
								repaint();
								revalidate();
							}
							@Override
							public void mouseClicked(MouseEvent arg0) {
								cl.show(panel_1, "panelBooksName");
								repaint();
								revalidate();
								
							}
						});
						lblBooks.setBounds(10, 105, 154, 80);
						lblBooks.setHorizontalAlignment(SwingConstants.LEFT);
						lblBooks.setForeground(new Color(255, 255, 255));
						lblBooks.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
						panel_2.add(lblBooks);
						
						JLabel lblLoans_1 = new JLabel("BOOK LOANS");
						lblLoans_1.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent arg0) {
								panel_2.setVisible(true);
								lineLoans.setVisible(true);
							}
							@Override
							public void mouseExited(MouseEvent arg0) {
								panel_2.setVisible(false);
								lineLoans.setVisible(false);
							}
							@Override
							public void mouseClicked(MouseEvent arg0) {
								cl.show(panel_1, "panelLoansName");
								repaint();
								revalidate();
							}
						});
						lblLoans_1.setHorizontalAlignment(SwingConstants.LEFT);
						lblLoans_1.setForeground(Color.WHITE);
						lblLoans_1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
						lblLoans_1.setBounds(9, 15, 154, 80);
						panel_2.add(lblLoans_1);
						
						JLabel lblAuthors = new JLabel("STUDENTS");
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
							@Override
							public void mouseClicked(MouseEvent arg0) {
								cl.show(panel_1, "panelStudentsName");
								repaint();
								revalidate();
							}
						});
						lblAuthors.setHorizontalAlignment(SwingConstants.LEFT);
						lblAuthors.setForeground(Color.WHITE);
						lblAuthors.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
						lblAuthors.setBounds(11, 405, 154, 80);
						panel_2.add(lblAuthors);
						
						JLabel lblProffessors = new JLabel("PROFESSORS");
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
							@Override
							public void mouseClicked(MouseEvent arg0) {
								cl.show(panel_1, "panelProfessorsName");
								repaint();
								revalidate();
							}
						});
						lblProffessors.setHorizontalAlignment(SwingConstants.LEFT);
						lblProffessors.setForeground(Color.WHITE);
						lblProffessors.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
						lblProffessors.setBounds(9, 205, 154, 80);
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
							@Override
							public void mouseClicked(MouseEvent arg0) {
								cl.show(panel_1, "panelClassesName");
								repaint();
								revalidate();							}
						});
						lblClasses.setHorizontalAlignment(SwingConstants.LEFT);
						lblClasses.setForeground(Color.WHITE);
						lblClasses.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
						lblClasses.setBounds(10, 305, 154, 80);
						panel_2.add(lblClasses);
		
		// Right panel 
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(985, 0, 215, 804);
		getContentPane().add(infoPanel);
		infoPanel.setBackground(new Color(51,51,51,180));
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
								    getContentPane().repaint();
								    getContentPane().revalidate();
							
								    
								  }
								}
							
							JLabel Clock = new ClockLabel();
							Clock.setFont(new Font("Segoe UI Emoji", Font.BOLD, 67));
							Clock.setForeground(Color.WHITE);
							Clock.setBounds(12, 13, 202, 104);
							Clock.setOpaque(false);
							
							
							Clock.setVisible(true);
							
					
					infoPanel.add(Clock);
		
		// Implementation of clock in right panel -- END
		
					
		
		
		// Panels definition end options -- END
		
		// Menu bar options -- BEGIN
		JMenuBar menuBar = new JMenuBar();
		
		
		JMenu knjiga = new JMenu("Books");
		knjiga.add(novaKnjiga.getMenuItem(panel));
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
				repaint();
				revalidate();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lineExit.setVisible(false);
				repaint();
				revalidate();
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
						frame.setUndecorated(true);
						
						frame.setVisible(true);
				    }
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lineLogout.setVisible(true);
				repaint();
				revalidate();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lineLogout.setVisible(false);
				repaint();
				revalidate();
			}
		});
		logout.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/account-logout-24.png")));
		logout.setHorizontalAlignment(SwingConstants.CENTER);
		logout.setBounds(58, 659, 48, 70);
		infoPanel.add(logout);
		

		
		
		// Items on right panel -- END
		
		// Middle panel items -- BEGIN
		
		JPanel panelStudents = new JPanel();
		panelStudents.setOpaque(false);
		panelStudents.setBackground(new Color(95, 158, 160));
		panelStudents.setLayout(null);
		panel_1.add(panelStudents, "panelStudentsName");
		
		// Panel Students items -- BEGIN
		
			Box lineAddStudent = Box.createHorizontalBox();
			lineAddStudent.setVisible(false);
			lineAddStudent.setOpaque(true);
			lineAddStudent.setBackground(new Color(255, 255, 255));
			lineAddStudent.setBounds(103, 375, 274, 3);
			panelStudents.add(lineAddStudent);
			
			Box lineViewStudents = Box.createHorizontalBox();
			lineViewStudents.setVisible(false);
			lineViewStudents.setOpaque(true);
			lineViewStudents.setBackground(Color.WHITE);
			lineViewStudents.setBounds(519, 375, 253, 3);
			panelStudents.add(lineViewStudents);
		
			JLabel lblAddNewStudent = new JLabel("Add a new student");
			lblAddNewStudent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lineAddStudent.setVisible(true);
					repaint();
					revalidate();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lineAddStudent.setVisible(false);
					repaint();
					revalidate();
				}
			});				
			lblAddNewStudent.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddNewStudent.setForeground(Color.WHITE);
			lblAddNewStudent.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
			lblAddNewStudent.setBounds(76, 309, 332, 96);
			panelStudents.add(lblAddNewStudent);
			
			JLabel lblViewAllStudents = new JLabel("View all students");
			lblViewAllStudents.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lineViewStudents.setVisible(true);
					repaint();
					revalidate();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lineViewStudents.setVisible(false);
					repaint();
					revalidate();
				}
			});	
			lblViewAllStudents.setHorizontalAlignment(SwingConstants.CENTER);
			lblViewAllStudents.setForeground(Color.WHITE);
			lblViewAllStudents.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
			lblViewAllStudents.setBounds(473, 309, 332, 96);
			panelStudents.add(lblViewAllStudents);
			
			JLabel lblAddStudentPic = new JLabel("");
			lblAddStudentPic.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddStudentPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/add-file-48.png")));
			lblAddStudentPic.setBounds(211, 286, 56, 52);
			panelStudents.add(lblAddStudentPic);
			
			JLabel lblViewStudentsPic = new JLabel("");
			lblViewStudentsPic.setHorizontalAlignment(SwingConstants.CENTER);
			lblViewStudentsPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/search-9-48.png")));
			lblViewStudentsPic.setBounds(616, 286, 56, 52);
			panelStudents.add(lblViewStudentsPic);
	
		// Panel Students items -- END
		
		
		JPanel panelClasses = new JPanel();
		panelClasses.setOpaque(false);
		panelClasses.setBackground(new Color(95, 158, 160));
		panelClasses.setLayout(null);
		panel_1.add(panelClasses, "panelClassesName");
		
		// Panel Classes items -- BEGIN
		
					Box lineAddSubject = Box.createHorizontalBox();
					lineAddSubject.setVisible(false);
					lineAddSubject.setOpaque(true);
					lineAddSubject.setBackground(new Color(255, 255, 255));
					lineAddSubject.setBounds(103, 375, 274, 3);
					panelClasses.add(lineAddSubject);
					
					Box lineViewSubjects = Box.createHorizontalBox();
					lineViewSubjects.setVisible(false);
					lineViewSubjects.setOpaque(true);
					lineViewSubjects.setBackground(Color.WHITE);
					lineViewSubjects.setBounds(519, 375, 253, 3);
					panelClasses.add(lineViewSubjects);
				
					JLabel lblAddNewSubject = new JLabel("Add a new class");
					lblAddNewSubject.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							lineAddSubject.setVisible(true);
							repaint();
							revalidate();
						}
						@Override
						public void mouseExited(MouseEvent e) {
							lineAddSubject.setVisible(false);
							repaint();
							revalidate();
						}
					});				
					lblAddNewSubject.setHorizontalAlignment(SwingConstants.CENTER);
					lblAddNewSubject.setForeground(Color.WHITE);
					lblAddNewSubject.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
					lblAddNewSubject.setBounds(76, 309, 332, 96);
					panelClasses.add(lblAddNewSubject);
					
					JLabel lblViewAllSubjects = new JLabel("View all classes");
					lblViewAllSubjects.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							lineViewSubjects.setVisible(true);
							repaint();
							revalidate();
						}
						@Override
						public void mouseExited(MouseEvent e) {
							lineViewSubjects.setVisible(false);
							repaint();
							revalidate();
						}
					});	
					lblViewAllSubjects.setHorizontalAlignment(SwingConstants.CENTER);
					lblViewAllSubjects.setForeground(Color.WHITE);
					lblViewAllSubjects.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
					lblViewAllSubjects.setBounds(473, 309, 332, 96);
					panelClasses.add(lblViewAllSubjects);
					
					JLabel lblAddSubjectPic = new JLabel("");
					lblAddSubjectPic.setHorizontalAlignment(SwingConstants.CENTER);
					lblAddSubjectPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/edit-property-48.png")));
					lblAddSubjectPic.setBounds(211, 286, 56, 52);
					panelClasses.add(lblAddSubjectPic);
					
					JLabel lblViewSubjectsPic = new JLabel("");
					lblViewSubjectsPic.setHorizontalAlignment(SwingConstants.CENTER);
					lblViewSubjectsPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/search-9-48.png")));
					lblViewSubjectsPic.setBounds(616, 286, 56, 52);
					panelClasses.add(lblViewSubjectsPic);
				
				// Panel Classes items -- END
		
		
		JPanel panelProfessors = new JPanel();
		panelProfessors.setOpaque(false);
		panelProfessors.setBackground(new Color(95, 158, 160));
		panelProfessors.setLayout(null);
		panel_1.add(panelProfessors, "panelProfessorsName");
		
		// Panel Professors items -- BEGIN
		
			Box lineAddProfessor = Box.createHorizontalBox();
			lineAddProfessor.setVisible(false);
			lineAddProfessor.setOpaque(true);
			lineAddProfessor.setBackground(new Color(255, 255, 255));
			lineAddProfessor.setBounds(103, 375, 274, 3);
			panelProfessors.add(lineAddProfessor);
			
			Box lineViewProfessors = Box.createHorizontalBox();
			lineViewProfessors.setVisible(false);
			lineViewProfessors.setOpaque(true);
			lineViewProfessors.setBackground(Color.WHITE);
			lineViewProfessors.setBounds(519, 375, 253, 3);
			panelProfessors.add(lineViewProfessors);
		
			JLabel lblAddNewProfessor = new JLabel("Add a new professor");
			lblAddNewProfessor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lineAddProfessor.setVisible(true);
					repaint();
					revalidate();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lineAddProfessor.setVisible(false);
					repaint();
					revalidate();
				}
			});				
			lblAddNewProfessor.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddNewProfessor.setForeground(Color.WHITE);
			lblAddNewProfessor.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
			lblAddNewProfessor.setBounds(76, 309, 332, 96);
			panelProfessors.add(lblAddNewProfessor);
			
			JLabel lblViewAllProfessors = new JLabel("View all professors");
			lblViewAllProfessors.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lineViewProfessors.setVisible(true);
					repaint();
					revalidate();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lineViewProfessors.setVisible(false);
					repaint();
					revalidate();
				}
			});	
			lblViewAllProfessors.setHorizontalAlignment(SwingConstants.CENTER);
			lblViewAllProfessors.setForeground(Color.WHITE);
			lblViewAllProfessors.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
			lblViewAllProfessors.setBounds(473, 309, 332, 96);
			panelProfessors.add(lblViewAllProfessors);
			
			JLabel lblAddProfessorPic = new JLabel("");
			lblAddProfessorPic.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddProfessorPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/administrator-2-48.png")));
			lblAddProfessorPic.setBounds(211, 286, 56, 52);
			panelProfessors.add(lblAddProfessorPic);
			
			JLabel lblViewProfessorsPic = new JLabel("");
			lblViewProfessorsPic.setHorizontalAlignment(SwingConstants.CENTER);
			lblViewProfessorsPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/search-9-48.png")));
			lblViewProfessorsPic.setBounds(616, 286, 56, 52);
			panelProfessors.add(lblViewProfessorsPic);
		
		// Panel Professors items -- END
		
		JPanel panelBooks = new JPanel();
		panelBooks.setOpaque(false);
		panelBooks.setBorder(null);
		panelBooks.setBackground(new Color(95, 158, 160));
		panelBooks.setLayout(null);
		panel_1.add(panelBooks, "panelBooksName");
		
		JPanel panelLoans = new JPanel();
		panelLoans.setOpaque(false);
		panelLoans.setBackground(new Color(95, 158, 160));
		panel_1.add(panelLoans, "panelLoansName");
		panelLoans.setLayout(null);
		
		// Panel Loans items -- BEGIN
		
			Box lineNewLoan = Box.createHorizontalBox();
			lineNewLoan.setOpaque(true);
			lineNewLoan.setVisible(false);
			lineNewLoan.setBackground(new Color(255, 255, 255));
			lineNewLoan.setBounds(162, 367, 194, 3);
			panelLoans.add(lineNewLoan);
			
			Box lineViewLoans = Box.createHorizontalBox();
			lineViewLoans.setOpaque(true);
			lineViewLoans.setVisible(false);
			lineViewLoans.setBackground(Color.WHITE);
			lineViewLoans.setBounds(504, 367, 254, 3);
			panelLoans.add(lineViewLoans);
			
			JLabel newLoanIcon = new JLabel("");
			newLoanIcon.setHorizontalAlignment(SwingConstants.CENTER);
			newLoanIcon.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/icons8-Borrow Book-48.png")));
			newLoanIcon.setBounds(223, 279, 75, 63);
			panelLoans.add(newLoanIcon);
			
			JLabel viewLoansIcon = new JLabel("");
			viewLoansIcon.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/search-9-48.png")));
			viewLoansIcon.setHorizontalAlignment(SwingConstants.CENTER);
			viewLoansIcon.setBounds(596, 279, 75, 63);
			panelLoans.add(viewLoansIcon);
			
			JLabel lblNewLoan = new JLabel("New book loan");
			lblNewLoan.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					lineNewLoan.setVisible(true);
					repaint();
					revalidate();
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					lineNewLoan.setVisible(false);
					repaint();
					revalidate();
				}
			});
			lblNewLoan.setForeground(new Color(255, 255, 255));
			lblNewLoan.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
			lblNewLoan.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLoan.setBounds(94, 330, 332, 52);
			panelLoans.add(lblNewLoan);
			
			JLabel lblViewLoans = new JLabel("View all book loans");
			lblViewLoans.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					lineViewLoans.setVisible(true);
					repaint();
					revalidate();
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					lineViewLoans.setVisible(false);
					repaint();
					revalidate();
				}
			});
			lblViewLoans.setHorizontalAlignment(SwingConstants.CENTER);
			lblViewLoans.setForeground(Color.WHITE);
			lblViewLoans.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
			lblViewLoans.setBounds(467, 330, 332, 52);
			panelLoans.add(lblViewLoans);
			
		// Panel Loans items -- END
		
		JPanel defaultPanel = new JPanel();
		defaultPanel.setOpaque(false);
		panel_1.add(defaultPanel, "defaultPanel");
		cl.show(panel_1, "defaultPanel");		
		
		

			// Panel Books items -- BEGIN
					// Lines -- BEGIN
						Box lineAddBook = Box.createHorizontalBox();
						lineAddBook.setVisible(false);
						
						Box lineAddAuthor = Box.createHorizontalBox();
						lineAddAuthor.setVisible(false);
						lineAddAuthor.setOpaque(true);
						lineAddAuthor.setBackground(Color.WHITE);
						lineAddAuthor.setBounds(122, 456, 235, 3);
						panelBooks.add(lineAddAuthor);
						lineAddBook.setOpaque(true);
						lineAddBook.setForeground(Color.WHITE);
						lineAddBook.setBackground(Color.WHITE);
						lineAddBook.setBounds(134, 139, 210, 3);
						panelBooks.add(lineAddBook);
						
						Box lineViewAllBooks = Box.createHorizontalBox();
						lineViewAllBooks.setVisible(false);
						lineViewAllBooks.setOpaque(true);
						lineViewAllBooks.setForeground(Color.WHITE);
						lineViewAllBooks.setBackground(Color.WHITE);
						lineViewAllBooks.setBounds(548, 139, 201, 3);
						panelBooks.add(lineViewAllBooks);
						
						Box lineAddBookType = Box.createHorizontalBox();
						lineAddBookType.setVisible(false);
						lineAddBookType.setOpaque(true);
						lineAddBookType.setForeground(Color.WHITE);
						lineAddBookType.setBackground(Color.WHITE);
						lineAddBookType.setBounds(103, 302, 270, 3);
						panelBooks.add(lineAddBookType);
						
						Box lineViewBookType = Box.createHorizontalBox();
						lineViewBookType.setVisible(false);
						lineViewBookType.setOpaque(true);
						lineViewBookType.setForeground(Color.WHITE);
						lineViewBookType.setBackground(Color.WHITE);
						lineViewBookType.setBounds(522, 302, 253, 3);
						panelBooks.add(lineViewBookType);
					// Lines -- END
					
					// Buttons -- BEGIN
						JLabel addANewBook = new JLabel("Add a new book");
						addANewBook.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent arg0) {
								lineAddBook.setVisible(true);
								repaint();
								revalidate();
							}
							@Override
							public void mouseExited(MouseEvent arg0) {
								lineAddBook.setVisible(false);
								repaint();
								revalidate();
							}
							@Override
							public void mouseClicked(MouseEvent arg0) {
								//cl.show(panel_1, "defaultPanel");
								//novaKnjiga.prikazi();
								
							}
						});
						
						Box lineViewAuthors = Box.createHorizontalBox();
						lineViewAuthors.setVisible(false);
						lineViewAuthors.setOpaque(true);
						lineViewAuthors.setBackground(Color.WHITE);
						lineViewAuthors.setBounds(543, 456, 210, 3);
						panelBooks.add(lineViewAuthors);
						
						Box lineAddPublisher = Box.createHorizontalBox();
						lineAddPublisher.setVisible(false);
						lineAddPublisher.setBounds(111, 609, 262, 3);
						panelBooks.add(lineAddPublisher);
						lineAddPublisher.setOpaque(true);
						lineAddPublisher.setBackground(Color.WHITE);
						
						Box lineViewPublishers = Box.createHorizontalBox();
						lineViewPublishers.setVisible(false);
						lineViewPublishers.setOpaque(true);
						lineViewPublishers.setBackground(Color.WHITE);
						lineViewPublishers.setBounds(522, 609, 253, 3);
						panelBooks.add(lineViewPublishers);
						addANewBook.setHorizontalAlignment(SwingConstants.CENTER);
						addANewBook.setForeground(new Color(255, 255, 255));
						addANewBook.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
						addANewBook.setBounds(65, 30, 346, 175);
						panelBooks.add(addANewBook);
						
						JLabel viewAllBooks = new JLabel("View all books");
						viewAllBooks.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent arg0) {
								lineViewAllBooks.setVisible(true);
								repaint();
								revalidate();
							}
							@Override
							public void mouseExited(MouseEvent arg0) {
								lineViewAllBooks.setVisible(false);
								repaint();
								revalidate();
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
						viewAllBooks.setBounds(474, 38, 346, 175);
						panelBooks.add(viewAllBooks);
						
						JLabel addBookType = new JLabel("Add a new book type");
						addBookType.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								lineAddBookType.setVisible(true);
								repaint();
								revalidate();
							}
							@Override
							public void mouseExited(MouseEvent e) {
								lineAddBookType.setVisible(false);
								repaint();
								revalidate();
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
						addBookType.setBounds(75, 196, 346, 175);
						panelBooks.add(addBookType);
						
						JLabel viewAllBookTypes = new JLabel("View all book types");
						viewAllBookTypes.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								lineViewBookType.setVisible(true);
								repaint();
								revalidate();
							}
							@Override
							public void mouseExited(MouseEvent e) {
								lineViewBookType.setVisible(false);
								repaint();
								revalidate();
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
						viewAllBookTypes.setBounds(474, 199, 346, 175);
						panelBooks.add(viewAllBookTypes);
						
						JLabel lblNewBookPic = new JLabel("");
						lblNewBookPic.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewBookPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/literature-48.png")));
						lblNewBookPic.setBounds(186, 38, 103, 68);
						panelBooks.add(lblNewBookPic);
						
						JLabel lblViewAllBooksPic = new JLabel("");
						lblViewAllBooksPic.setHorizontalAlignment(SwingConstants.CENTER);
						lblViewAllBooksPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/books-48.png")));
						lblViewAllBooksPic.setBounds(619, 28, 57, 76);
						panelBooks.add(lblViewAllBooksPic);
						
						JLabel lblViewBookTypesPic = new JLabel("");
						lblViewBookTypesPic.setHorizontalAlignment(SwingConstants.CENTER);
						lblViewBookTypesPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/search-9-48.png")));
						lblViewBookTypesPic.setBounds(619, 196, 57, 76);
						panelBooks.add(lblViewBookTypesPic);
						
						JLabel lblAddBookTypePic = new JLabel("");
						lblAddBookTypePic.setHorizontalAlignment(SwingConstants.CENTER);
						lblAddBookTypePic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/add-file-48.png")));
						lblAddBookTypePic.setBounds(186, 194, 103, 68);
						panelBooks.add(lblAddBookTypePic);
						
						JLabel lblAddNewAuthor = new JLabel("Add a new author");
						lblAddNewAuthor.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent arg0) {
								lineAddAuthor.setVisible(true);
								repaint();
								revalidate();
							}
							@Override
							public void mouseExited(MouseEvent arg0) {
								lineAddAuthor.setVisible(false);
								repaint();
								revalidate();
							}
						});
						lblAddNewAuthor.setHorizontalAlignment(SwingConstants.CENTER);
						lblAddNewAuthor.setForeground(Color.WHITE);
						lblAddNewAuthor.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
						lblAddNewAuthor.setBounds(65, 351, 346, 175);
						panelBooks.add(lblAddNewAuthor);
						
						JLabel lblViewAllAuthors = new JLabel("View all authors");
						lblViewAllAuthors.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								lineViewAuthors.setVisible(true);
								repaint();
								revalidate();
							}
							@Override
							public void mouseExited(MouseEvent e) {
								lineViewAuthors.setVisible(false);
								repaint();
								revalidate();
							}
						});
						lblViewAllAuthors.setHorizontalAlignment(SwingConstants.CENTER);
						lblViewAllAuthors.setForeground(Color.WHITE);
						lblViewAllAuthors.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
						lblViewAllAuthors.setBounds(474, 351, 346, 175);
						panelBooks.add(lblViewAllAuthors);
						
						JLabel lblAddANewPublisher = new JLabel("Add a new publisher");
						lblAddANewPublisher.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								lineAddPublisher.setVisible(true);
								repaint();
								revalidate();
							}
							@Override
							public void mouseExited(MouseEvent e) {
								lineAddPublisher.setVisible(false);
								repaint();
								revalidate();
							}
						});
						lblAddANewPublisher.setHorizontalAlignment(SwingConstants.CENTER);
						lblAddANewPublisher.setForeground(Color.WHITE);
						lblAddANewPublisher.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
						lblAddANewPublisher.setBounds(65, 506, 346, 175);
						panelBooks.add(lblAddANewPublisher);
						
						JLabel lblViewAllPublishers = new JLabel("View all publishers");
						lblViewAllPublishers.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								lineViewPublishers.setVisible(true);
								repaint();
								revalidate();
							}
							@Override
							public void mouseExited(MouseEvent e) {
								lineViewPublishers.setVisible(false);
								repaint();
								revalidate();
							}
						});
						lblViewAllPublishers.setHorizontalAlignment(SwingConstants.CENTER);
						lblViewAllPublishers.setForeground(Color.WHITE);
						lblViewAllPublishers.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
						lblViewAllPublishers.setBounds(474, 506, 346, 175);
						panelBooks.add(lblViewAllPublishers);
						
						JLabel lblNewAuthorPic = new JLabel("");
						lblNewAuthorPic.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewAuthorPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/edit-9-48.png")));
						lblNewAuthorPic.setBounds(186, 350, 103, 68);
						panelBooks.add(lblNewAuthorPic);
						
						JLabel lblViewAuthorsPic = new JLabel("");
						lblViewAuthorsPic.setHorizontalAlignment(SwingConstants.CENTER);
						lblViewAuthorsPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/edit-property-48.png")));
						lblViewAuthorsPic.setBounds(619, 351, 57, 76);
						panelBooks.add(lblViewAuthorsPic);
						
						JLabel lblNewPublisherPic = new JLabel("");
						lblNewPublisherPic.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewPublisherPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/printer-48.png")));
						lblNewPublisherPic.setBounds(186, 506, 103, 68);
						panelBooks.add(lblNewPublisherPic);
						
						JLabel lblViewPublishersPic = new JLabel("");
						lblViewPublishersPic.setHorizontalAlignment(SwingConstants.CENTER);
						lblViewPublishersPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/user-3-48.png")));
						lblViewPublishersPic.setBounds(619, 498, 57, 76);
						panelBooks.add(lblViewPublishersPic);
					// Buttons -- END
		// Disappearing panel items -- END
	
		// Overall panel items -- BEGIN
					// Buttons -- BEGIN
							JLabel lblLoans = new JLabel("");
							lblLoans.setHorizontalAlignment(SwingConstants.CENTER);
							lblLoans.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent arg0) {
									panel_2.setVisible(true);
									lblLoans_1.setVisible(true);
									lineLoans.setVisible(true);
								}
								@Override
								public void mouseExited(MouseEvent e) {
									panel_2.setVisible(false);
									lineLoans.setVisible(false);
								}
							});
							lblLoans.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/external-link-48.png")));
							lblLoans.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
							lblLoans.setForeground(Color.WHITE);
							lblLoans.setBounds(0, 0, 78, 110);
							panel.add(lblLoans);
							
							JLabel lblBooks_1 = new JLabel("");
							lblBooks_1.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/book-48.png")));
							lblBooks_1.addMouseListener(new MouseAdapter() {
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
							lblBooks_1.setHorizontalAlignment(SwingConstants.CENTER);
							lblBooks_1.setForeground(Color.WHITE);
							lblBooks_1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
							lblBooks_1.setBounds(0, 90, 78, 110);
							panel.add(lblBooks_1);
							
							JLabel lblAuthor = new JLabel("");
							lblAuthor.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/student-48.png")));
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
							lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
							lblAuthor.setForeground(Color.WHITE);
							lblAuthor.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
							lblAuthor.setBounds(0, 390, 78, 110);
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
							lblProffesor.setHorizontalAlignment(SwingConstants.CENTER);
							lblProffesor.setForeground(Color.WHITE);
							lblProffesor.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
							lblProffesor.setBounds(0, 190, 78, 110);
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
							lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
							lblSubject.setForeground(Color.WHITE);
							lblSubject.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
							lblSubject.setBounds(0, 290, 78, 110);
							panel.add(lblSubject);
					// Buttons -- END
		// Overall panel items -- END
							
	// Main menu background options -- BEGIN
		JLabel MenuBackground = new JLabel((String) null);
		MenuBackground.setHorizontalAlignment(SwingConstants.CENTER);
		MenuBackground.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/background.jpg")));
		MenuBackground.setOpaque(true);
		MenuBackground.setBounds(0, -11, 1200, 815);
		getContentPane().add(MenuBackground);
	// Main menu background options -- END
							
	}
	private Bibliotekar bibliotekar;

	private NovaKnjiga novaKnjiga = new NovaKnjiga();
	private KnjigaPregled pregledKnjiga;
	
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
