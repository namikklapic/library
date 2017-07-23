package swing.izdavacPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class IzdavacPregled extends JPanel{
	public IzdavacPregled() {
		panel  = this;
		add(new JLabel("izdavac pregled panel"));
	}
	public JMenuItem getMenuItem(JPanel parent) {
		JMenuItem item = new JMenuItem("Pregled izdavaca");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				parent.removeAll();
				parent.add(panel);
				parent.repaint();
				parent.revalidate();
			}
		});
		return item;
	}
	private JPanel panel;

}
