package swing;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


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
		int visinaProzora = 350;
		int sirinaProzora = 390;
		frame = new JFrame();
		panelPrijava = new PanelPrijava(frame);
		frame.setSize(sirinaProzora, visinaProzora);
		frame.setLocation(velicinaEkrana.width/2 - sirinaProzora/2, velicinaEkrana.height/2 - visinaProzora/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panelPrijava);
		frame.setTitle("Prijava");
	}

}
