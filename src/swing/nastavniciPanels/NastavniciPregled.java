package swing.nastavniciPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class NastavniciPregled extends JPanel{
	public NastavniciPregled(){
		panel = this;
		add(new JLabel("Nastavnici pregled panel"));
	}
	public JMenuItem getMenuItem(JPanel parent){
		JMenuItem item = new JMenuItem("Pregled nastavnika");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
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

