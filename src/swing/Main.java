package swing;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import swing.MenuToolbar;
//import swing.panelPretrage.PanelPretrage;
import swing.PanelPrijava;
//import swing.panelUnosa.PanelRasporedProfesor;
//import swing.panelUnosa.PanelUnosa;
//import ejb.Osoblje;

public class Main {
	private JFrame frame;

	private MenuToolbar menu;

//	private PanelPretrage panelPretrage = new PanelPretrage();
//	private PanelUnosa panelUnosa = new PanelUnosa();
	private PanelPrijava panelPrijava = new PanelPrijava();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelPrikaza panel = new PanelPrikaza();
		frame.add(panel);
	}

	class PanelPrikaza extends JPanel {

		private static final int menuHeight = 20;

		private int selectedMenuIndex;
		public boolean prikaziUnos;

		PanelPrikaza() {
			paintPanel();
		}

		@Override
		protected void paintComponent(Graphics g) {
			for (int i = 0; i < menu.getToogleButtons().length; i++) {
				if (menu.getToogleButtons()[i].isSelected())
					selectedMenuIndex = i;
			}
			this.removeAll();
			this.revalidate();
			super.paintComponent(g);
			paintPanel();
		}

		private void paintPanel() {
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
			gridBagLayout.rowWeights = new double[] { 0.0, 1.0,
					Double.MIN_VALUE };
			setLayout(gridBagLayout);

			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.anchor = GridBagConstraints.NORTH;
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			gbc_panel.ipady = menuHeight;
			JPanel[] panels = new JPanel[] {
					panelPrijava };

			menu = new MenuToolbar(panels);
//			Osoblje o = panelPrijave.getOsoblje();
//			if (o.getTitula2() != null
//					&& o.getTitula2().getTitula().equals("prodekan")) {
//				menu.getToogleButtons()[1].setEnabled(true);
//				panelPretrage.setOsoba(null);
//				panelUnosa.setOsoba(null);
				// panelPretrage.setSearchPanel4All(new JPanel());
				// prodekan;
//			} else if (o.getTitula1() != null) {
//				menu.getToogleButtons()[1].setEnabled(true);
//				panelPretrage.setOsoba(o);
//				panelUnosa.setOsoba(o);
				// profesor;
//			} else {
//				menu.getToogleButtons()[1].setEnabled(false);
//				panelPretrage.setOsoba(null);
//				panelUnosa.setOsoba(null);
//			}
//			panelPretrage.setSearchPanel4All(new JPanel());

			add(menu, gbc_panel);

			menu.actionPerformed(new ActionEvent(
					menu.getToogleButtons()[selectedMenuIndex], 1, null));
			final JLayeredPane layeredPane = new JLayeredPane();
			JPanel panel_2 = new JPanel();
			gbc_panel.anchor = GridBagConstraints.NORTH;
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.weightx = 0.0;
			gbc_panel.weighty = 0.0;
			gbc_panel.ipady = getSize().height - menuHeight;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			add(panel_2, gbc_panel);
			panel_2.setLayout(new GridLayout(0, 1, 0, 0));

			panel_2.add(layeredPane);
			layeredPane.setLayout(new BoxLayout(layeredPane, BoxLayout.X_AXIS));
//			layeredPane.add(panelPretrage);
//			layeredPane.add(panelUnosa);
			layeredPane.add(panelPrijava);
			
		}

	}
}
