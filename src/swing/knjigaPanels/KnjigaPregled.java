package swing.knjigaPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class KnjigaPregled extends JPanel{
	public KnjigaPregled(){
		add(new JLabel("Helloooooooooooooooooooooooooooooooooooooooooo"));
	}
	public JMenuItem createMenuItem(JPanel parent){
		JMenuItem item = new JMenuItem("Pregled Knijga");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
//				parent.add(	);
			}
		});
		return item;
	}
}
