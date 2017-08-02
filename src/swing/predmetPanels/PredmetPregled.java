package swing.predmetPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PredmetPregled extends JPanel {
	
	public PredmetPregled() {
		panel = this;
		add(new JLabel("Panel za pregled predmeta"));
	}
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem menuItem = new JMenuItem("Pregled predmeta");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				parent.removeAll();
				parent.add(panel);
				parent.revalidate();
				parent.repaint();
			}
		});
		return menuItem;
	}

	private JPanel panel;
}
