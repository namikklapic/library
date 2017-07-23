package swing.knjigaPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class NovaKnjiga  extends JFrame{
	public NovaKnjiga(){
		setTitle("Nova knjiga");
		setSize(300, 300);
		
		JPanel panel = new JPanel();
		panel.setSize(300, 300);
		
		
	}
	
	public JMenuItem getMenuItem() {
		JMenuItem item = new JMenuItem("Nova Knjiga");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				prikazi();
			}
		});
		return item;
	}
	
	public void prikazi(){
		setVisible(true);
	}

}
