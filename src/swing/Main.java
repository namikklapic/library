package swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Main {
	private JFrame frame;
	private PanelPrijava panelPrijava;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Main window = new Main();
		window.frame.setVisible(true);		
	}

	/**
	 * Create application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize content of the frame.
	 */
	private void initialize() {
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension velicinaEkrana = kit.getScreenSize();
		int visinaProzora = 600;
		int sirinaProzora = 400;
		frame = new JFrame();
		
		// TEST LOOK AND FEEL
		/*try{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		SwingUtilities.updateComponentTreeUI(frame);
		}
		catch (Exception e)
		{}*/
		
		panelPrijava = new PanelPrijava(frame);
		frame.setSize(400, 600);
		frame.setResizable(false);

		frame.setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panelPrijava);
		frame.setTitle("Login");
	}

}
