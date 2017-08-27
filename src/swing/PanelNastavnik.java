package swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

import jpa.Nastavnik;
import swing.RezervacijaPanels.RezervacijePregled;
import swing.knjigaPanels.KnjigaPregled;
import swing.literaturaPanels.LiteraturaPregled;
import swing.posudbaPanels.PosudbePregled;

public class PanelNastavnik extends JFrame {
	public PanelNastavnik (Nastavnik n){
		this.nastavnik = n;
		knjigaPregled = new KnjigaPregled(false, nastavnik.getKorisnik());
		this.posudbePregled = new PosudbePregled(n.getKorisnik());
		this.rezervacijePregled = new RezervacijePregled(n.getKorisnik());
		setTitle(n.getKorisnik().getImeKorisnika() + " " + n.getKorisnik().getPrezimeKorisnika());
		this.literaturaPregled = new LiteraturaPregled(this.nastavnik);
		setResizable(false);
		getContentPane().setLayout(null);
		setUndecorated(true);

		
		// Panels definition and options -- BEGIN
		
				// Overall panel
				JPanel panel = new JPanel();
				panel.setLayout(null);
				panel.setBorder(null);
				panel.setBackground(new Color(51, 51, 51,180));
				panel.setForeground(Color.WHITE);
				panel.setBounds(0, 0, 78, 804);
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
				panel_1.setBounds(90, 13, 883, 774);
				getContentPane().add(panel_1);
				
				CardLayout cl = (CardLayout)(panel_1.getLayout());
				// Buttons -- END
				
				
					// Panel Books items -- END				
				// Middle panel items -- END
					
			
				// Disappearing panel items -- BEGIN
					// Lines -- BEGIN
						Box lineBooks = Box.createHorizontalBox();
						lineBooks.setBounds(13, 224, 74, 3);
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
						lineLoans.setBounds(13, 122, 141, 3);
						panel_2.add(lineLoans);
						
						Box lineClasses = Box.createHorizontalBox();
						lineClasses.setOpaque(true);
						lineClasses.setVisible(false);
						lineClasses.setForeground(Color.WHITE);
						lineClasses.setBackground(Color.WHITE);
						lineClasses.setBounds(12, 325, 119, 3);
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
								lblBooks.setBounds(13, 173, 154, 80);
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
								lblLoans_1.setBounds(12, 73, 154, 80);
								panel_2.add(lblLoans_1);
								
								JLabel lblClasses = new JLabel("LITERATURE");
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
								lblClasses.setBounds(13, 273, 154, 80);
								panel_2.add(lblClasses);
				
				// Right panel 
				JPanel infoPanel = new JPanel();
				infoPanel.setBounds(985, 0, 215, 804);
				getContentPane().add(infoPanel);
				infoPanel.setBackground(new Color(51,51,51,180));
				infoPanel.setLayout(null);
				
				// Right panel minimize and exit buttons -- BEGIN
				JButton btnMinimize = new JButton("__");
				btnMinimize.setOpaque(false);
				btnMinimize.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {
						btnMinimize.setBackground(new Color(197,223,208));	
						btnMinimize.setForeground(new Color(197,223,208));
						
						repaint();
						revalidate();
						}
					@Override
					public void mouseExited(MouseEvent e) {
						btnMinimize.setBackground(Color.DARK_GRAY);
						btnMinimize.setForeground(Color.WHITE);
						repaint();
						revalidate();
					}
				});
				btnMinimize.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setExtendedState(JFrame.ICONIFIED);
						repaint();
						revalidate();
						
					}
				});
				btnMinimize.setForeground(Color.WHITE);
				btnMinimize.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
				btnMinimize.setFocusPainted(false);
				btnMinimize.setBorder(null);
				btnMinimize.setBackground(Color.DARK_GRAY);
				btnMinimize.setBounds(144, 9, 26, 23);
				infoPanel.add(btnMinimize);
				
				
				
				JButton btnExit = new JButton("x");
				btnExit.setOpaque(false);
				btnExit.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {
						btnExit.setBackground(new Color(197,223,208));	
						btnExit.setForeground(new Color(197,223,208));

						repaint();
						revalidate();
						}
					@Override
					public void mouseExited(MouseEvent e) {
						btnExit.setBackground(Color.DARK_GRAY);
						btnExit.setForeground(Color.WHITE);

						repaint();
						revalidate();
					}
				});
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(1);
						
						repaint();
						revalidate();
					}
				});
				btnExit.setForeground(Color.WHITE);
				btnExit.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
				btnExit.setFocusPainted(false);
				btnExit.setBorder(null);
				btnExit.setBackground(Color.DARK_GRAY);
				btnExit.setBounds(177, 11, 26, 23);
				infoPanel.add(btnExit);
				
			// Right panel minimize and exit buttons -- END
				
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
									Clock.setBounds(12, 100, 202, 104);
									Clock.setOpaque(false);
									
									
									Clock.setVisible(true);
									
							
							infoPanel.add(Clock);
				
				// Implementation of clock in right panel -- END
				
							
				
				
				// Panels definition end options -- END
		/*
							
		JMenuBar menuBar = new JMenuBar();
		
		JMenu knjiga = new JMenu("Books");
		knjiga.add(knjigaPregled.getMenuItem(panel));
		
		JMenu posudba = new JMenu("Loans");
		posudba.add(posudbePregled.getMenuItem(panel));
		
		JMenu literatura = new JMenu("Reading lists");
		literatura.add(literaturaPregled.getMenuItem());
		
		
		
		
		menuBar.add(knjiga);
		menuBar.add(posudba);
		menuBar.add(literatura);
		
		setJMenuBar(menuBar);
		*/
		getContentPane().add(panel);
		
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
								int visinaProzora = 530;
								int sirinaProzora = 470;
								
								PanelPrijava panelPrijava = new PanelPrijava(frame);
								frame.setSize(470, 530);
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
				
				// CHANGE PROFILE PICTURE TEST -- BEGIN
				Box lineChangePic = Box.createHorizontalBox();
				lineChangePic.setVisible(false);
				lineChangePic.setOpaque(true);
				lineChangePic.setBackground(new Color(255, 255, 255));
				lineChangePic.setBounds(12, 385, 177, 2);
				infoPanel.add(lineChangePic);
				
				JLabel profilePictureBox = new JLabel("");
				profilePictureBox.setBounds(27, 207, 140, 140);
				infoPanel.add(profilePictureBox);
				
				File profilePicture = new File(PanelBibliotekar.class.getResource("/swing/profileImages/").toString().substring(6) + n.getKorisnik().getSifraKorisnika() + ".jpg");
				
				boolean exists = profilePicture.exists();
				
				if(exists == true) 
				{
					ImageIcon icon = new ImageIcon(PanelBibliotekar.class.getResource("/swing/profileImages/" + n.getKorisnik().getSifraKorisnika()	+ ".jpg"));
					Image image = icon.getImage();
					Image toShow = image.getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH);
		            profilePictureBox.setIcon(new ImageIcon(toShow));
				}
				else {
					ImageIcon icon = new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/default-profile.png"));
					Image image = icon.getImage();
					Image toShow = image.getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH);
		            profilePictureBox.setIcon(new ImageIcon(toShow));
				}
				
				JLabel lblChangeProfilePicture = new JLabel("Change profile picture");
				lblChangeProfilePicture.setHorizontalAlignment(SwingConstants.CENTER);
				lblChangeProfilePicture.setAlignmentX(200.0f);
				JFileChooser fc = new JFileChooser();
				lblChangeProfilePicture.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						JOptionPane.showMessageDialog(null, "The picture dimensions must be maximum 200 x 200 !");
						
						FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
		                fc.setFileFilter(filter);
		                int response = fc.showOpenDialog(null);
		                try {
		                    if (response == JFileChooser.APPROVE_OPTION) {
		                        String pathName = fc.getSelectedFile().getPath();                    
		                        
		                        File src = new File(pathName);
		                        File target = new File(PanelBibliotekar.class.getResource("/swing/profileImages/").toString().substring(6) + n.getKorisnik().getSifraKorisnika() + ".jpg");

		                        Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
		                        
		                        JOptionPane.showMessageDialog(null, "Picture changed succesfully!");
		                        ImageIcon icon = new ImageIcon(pathName);
		                        Image image = icon.getImage();
		            			Image toShow = image.getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH);
		                        profilePictureBox.setIcon(new ImageIcon(toShow));
		                    } 
		                } catch (Exception e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
					}
					@Override
					public void mouseEntered(MouseEvent arg0) {
						lineChangePic.setVisible(true);
						repaint();
						revalidate();
					}
					@Override
					public void mouseExited(MouseEvent arg0) {
						lineChangePic.setVisible(false);
						repaint();
						revalidate();
					}
				});
				
				
				lblChangeProfilePicture.setForeground(new Color(255, 255, 255));
				lblChangeProfilePicture.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
				lblChangeProfilePicture.setBounds(12, 353, 177, 35);
				infoPanel.add(lblChangeProfilePicture);
				
				JLabel lblName = new JLabel("First name :");
				lblName.setHorizontalAlignment(SwingConstants.CENTER);
				lblName.setForeground(Color.WHITE);
				lblName.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
				lblName.setAlignmentX(200.0f);
				lblName.setBounds(12, 411, 87, 35);
				infoPanel.add(lblName);
				
				JLabel lblLastName = new JLabel("Last name :");
				lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
				lblLastName.setForeground(Color.WHITE);
				lblLastName.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
				lblLastName.setAlignmentX(200.0f);
				lblLastName.setBounds(12, 452, 87, 35);
				infoPanel.add(lblLastName);
				
				JLabel lblNameEntry = new JLabel(n.getKorisnik().getImeKorisnika());
				lblNameEntry.setHorizontalAlignment(SwingConstants.LEFT);
				lblNameEntry.setForeground(new Color(255, 255, 255));
				lblNameEntry.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
				lblNameEntry.setBounds(102, 411, 94, 35);
				infoPanel.add(lblNameEntry);
				
				JLabel lblSurnameEntry = new JLabel(n.getKorisnik().getPrezimeKorisnika());
				lblSurnameEntry.setHorizontalAlignment(SwingConstants.LEFT);
				lblSurnameEntry.setForeground(Color.WHITE);
				lblSurnameEntry.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
				lblSurnameEntry.setBounds(102, 452, 94, 35);
				infoPanel.add(lblSurnameEntry);
				
				JLabel lblNegativePoints = new JLabel("Negative points :");
				lblNegativePoints.setHorizontalAlignment(SwingConstants.LEFT);
				lblNegativePoints.setForeground(Color.WHITE);
				lblNegativePoints.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
				lblNegativePoints.setAlignmentX(200.0f);
				lblNegativePoints.setBounds(12, 493, 140, 35);
				infoPanel.add(lblNegativePoints);
				
				JLabel lblNegPtsEntry = new JLabel(String.valueOf(n.getKorisnik().getBrojNegativnihBodova()));
				lblNegPtsEntry.setHorizontalAlignment(SwingConstants.LEFT);
				lblNegPtsEntry.setForeground(Color.WHITE);
				lblNegPtsEntry.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
				lblNegPtsEntry.setBounds(148, 493, 48, 35);
				infoPanel.add(lblNegPtsEntry);
			
				// Panel Students items -- END
				
				
				JPanel panelClasses = new JPanel();
				panelClasses.setOpaque(false);
				panelClasses.setBackground(new Color(95, 158, 160));
				panelClasses.setLayout(null);
				panel_1.add(panelClasses, "panelClassesName");
							
							Box lineViewSubjects = Box.createHorizontalBox();
							lineViewSubjects.setVisible(false);
							lineViewSubjects.setOpaque(true);
							lineViewSubjects.setBackground(Color.WHITE);
							lineViewSubjects.setBounds(315, 375, 253, 3);
							panelClasses.add(lineViewSubjects);
							
							JLabel lblViewAllSubjects = new JLabel("View my literature");
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
								@Override
								public void mouseClicked(MouseEvent arg0) {
									literaturaPregled.prikazi();
								}
							});	
							lblViewAllSubjects.setHorizontalAlignment(SwingConstants.CENTER);
							lblViewAllSubjects.setForeground(Color.WHITE);
							lblViewAllSubjects.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
							lblViewAllSubjects.setBounds(275, 309, 332, 96);
							panelClasses.add(lblViewAllSubjects);
							
							JLabel lblViewSubjectsPic = new JLabel("");
							lblViewSubjectsPic.setHorizontalAlignment(SwingConstants.CENTER);
							lblViewSubjectsPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/search-9-48.png")));
							lblViewSubjectsPic.setBounds(413, 286, 56, 52);
							panelClasses.add(lblViewSubjectsPic);
				
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
					
					Box lineViewLoans = Box.createHorizontalBox();
					lineViewLoans.setOpaque(true);
					lineViewLoans.setVisible(false);
					lineViewLoans.setBackground(Color.WHITE);
					lineViewLoans.setBounds(118, 375, 254, 3);
					panelLoans.add(lineViewLoans);
					
					Box lineViewReservations = Box.createHorizontalBox();
					lineViewReservations.setVisible(false);
					lineViewReservations.setOpaque(true);
					lineViewReservations.setBackground(new Color(255, 255, 255));
					lineViewReservations.setBounds(500, 375, 280, 3);
					panelLoans.add(lineViewReservations);
					
					JLabel viewLoansIcon = new JLabel("");
					viewLoansIcon.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/search-9-48.png")));
					viewLoansIcon.setHorizontalAlignment(SwingConstants.CENTER);
					viewLoansIcon.setBounds(211, 286, 56, 52);
					panelLoans.add(viewLoansIcon);
					
					JLabel lblViewLoans = new JLabel("View my book loans");
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
						@Override
						public void mouseClicked(MouseEvent arg0) {
							posudbePregled.prikazi();
						}
					});
					lblViewLoans.setHorizontalAlignment(SwingConstants.CENTER);
					lblViewLoans.setForeground(Color.WHITE);
					lblViewLoans.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
					lblViewLoans.setBounds(76, 309, 332, 96);
					panelLoans.add(lblViewLoans);
					
					JLabel lblViewReservations = new JLabel("View book reservations");
					lblViewReservations.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							lineViewReservations.setVisible(true);
							repaint();
							revalidate();
						}
						@Override
						public void mouseExited(MouseEvent e) {
							lineViewReservations.setVisible(false);
							repaint();
							revalidate();
						}
						@Override
						public void mouseClicked(MouseEvent arg0) {
							rezervacijePregled.prikazi();
						}
					});
					lblViewReservations.setHorizontalAlignment(SwingConstants.CENTER);
					lblViewReservations.setForeground(Color.WHITE);
					lblViewReservations.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
					lblViewReservations.setBounds(473, 309, 332, 96);
					panelLoans.add(lblViewReservations);
					
					JLabel viewReservationsIcon = new JLabel("");
					viewReservationsIcon.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/edit-property-48.png")));
					viewReservationsIcon.setHorizontalAlignment(SwingConstants.CENTER);
					viewReservationsIcon.setBounds(616, 286, 56, 52);
					panelLoans.add(viewReservationsIcon);
					
				// Panel Loans items -- END
				
				JPanel defaultPanel = new JPanel();
				defaultPanel.setOpaque(false);
				panel_1.add(defaultPanel, "defaultPanel");
				cl.show(panel_1, "defaultPanel");
								
								Box lineViewAllBooks = Box.createHorizontalBox();
								lineViewAllBooks.setVisible(false);
								lineViewAllBooks.setOpaque(true);
								lineViewAllBooks.setForeground(Color.WHITE);
								lineViewAllBooks.setBackground(Color.WHITE);
								lineViewAllBooks.setBounds(341, 370, 201, 3);
								panelBooks.add(lineViewAllBooks);
								
								JLabel viewAllBooks = new JLabel("View my books");
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
										knjigaPregled.prikazi();
									}
								});
								viewAllBooks.setHorizontalAlignment(SwingConstants.CENTER);
								viewAllBooks.setForeground(Color.WHITE);
								viewAllBooks.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
								viewAllBooks.setBounds(268, 269, 346, 175);
								panelBooks.add(viewAllBooks);
								
								JLabel lblViewAllBooksPic = new JLabel("");
								lblViewAllBooksPic.setHorizontalAlignment(SwingConstants.CENTER);
								lblViewAllBooksPic.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/books-48.png")));
								lblViewAllBooksPic.setBounds(413, 270, 57, 76);
								panelBooks.add(lblViewAllBooksPic);
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
											repaint();
											revalidate();
										}
										@Override
										public void mouseExited(MouseEvent e) {
											panel_2.setVisible(false);
											lineLoans.setVisible(false);
											repaint();
											revalidate();
										}
									});
									lblLoans.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/external-link-48.png")));
									lblLoans.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
									lblLoans.setForeground(Color.WHITE);
									lblLoans.setBounds(0, 58, 78, 110);
									panel.add(lblLoans);
									
									JLabel lblBooks_1 = new JLabel("");
									lblBooks_1.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/book-48.png")));
									lblBooks_1.addMouseListener(new MouseAdapter() {
										@Override
										public void mouseEntered(MouseEvent arg0) {
											panel_2.setVisible(true);
											lblBooks.setVisible(true);
											lineBooks.setVisible(true);
											repaint();
											revalidate();
										}
										@Override
										public void mouseExited(MouseEvent e) {
											panel_2.setVisible(false);
											lineBooks.setVisible(false);
											repaint();
											revalidate();
										}
									});						
									lblBooks_1.setHorizontalAlignment(SwingConstants.CENTER);
									lblBooks_1.setForeground(Color.WHITE);
									lblBooks_1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
									lblBooks_1.setBounds(0, 158, 78, 110);
									panel.add(lblBooks_1);
									
									JLabel lblSubject = new JLabel("");
									lblSubject.setIcon(new ImageIcon(PanelBibliotekar.class.getResource("/swing/images/school-48.png")));
									lblSubject.addMouseListener(new MouseAdapter() {
										@Override
										public void mouseEntered(MouseEvent arg0) {
											panel_2.setVisible(true);
											lineClasses.setVisible(true);
											repaint();
											revalidate();
										}
										@Override
										public void mouseExited(MouseEvent e) {
											panel_2.setVisible(true);
											lineClasses.setVisible(false);
											repaint();
											revalidate();
										}
									});
									lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
									lblSubject.setForeground(Color.WHITE);
									lblSubject.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
									lblSubject.setBounds(0, 258, 78, 110);
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
	
	public void updateInterface(String reason) {
		if(reason == "Update AuthorKnjiga") {
			knjigaPregled.refreshTable(true);
		}
		else if(reason == "Update Author") {
			knjigaPregled.refreshTable(true);
		}
		else if(reason == "Update Bibliotekar") {
			rezervacijePregled.refreshTable(true);
		}
		else if(reason == "Update Izdavac") {
			knjigaPregled.refreshTable(true);
		}
		else if(reason == "Update Knjiga") {
			knjigaPregled.refreshTable(true);
			posudbePregled.refreshTable(true);
			rezervacijePregled.refreshTable(true);
			literaturaPregled.refreshTable(true);
		}
		else if(reason == "Update NastavnikPredmet") {
			literaturaPregled.refreshTable(true);
		}
		else if(reason == "Update Nastavnik") {
			rezervacijePregled.refreshTable(true);
			literaturaPregled.refreshTable(true);
		}
		else if(reason == "Update Posudba") {
			knjigaPregled.refreshTable(true);
			posudbePregled.refreshTable(true);
			rezervacijePregled.refreshTable(true);
		}
		else if(reason == "Update Predmet") {
			literaturaPregled.refreshTable(true);
		}
		else if(reason == "Update Primjerak") {
			knjigaPregled.refreshTable(true);
			posudbePregled.refreshTable(true);
			rezervacijePregled.refreshTable(true);
		}
		else if(reason == "Update Rezervacija") {
			knjigaPregled.refreshTable(true);
			rezervacijePregled.refreshTable(true);
		}
		else if(reason == "Update Student") {
			rezervacijePregled.refreshTable(true);
		}
		else if(reason == "Update VrstaKnjige") {
			knjigaPregled.refreshTable(true);
		}
	}
	
	private Nastavnik nastavnik;
	
	private KnjigaPregled knjigaPregled;
	private PosudbePregled posudbePregled;
	private LiteraturaPregled literaturaPregled;
	private RezervacijePregled rezervacijePregled;
}


