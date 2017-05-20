package swing;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

public class MenuToolbar extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JToggleButton[] toogleButtons;
	private JButton closeBtn = new JButton("Close");
	private JPanel panels[];

	private int numberOfElements;

	public MenuToolbar(JPanel[] panels) {
		this.panels = panels;
		this.numberOfElements = panels.length;
		toogleButtons = new JToggleButton[numberOfElements];
		setLayout(new GridLayout(0, numberOfElements + 1, 0, 0));
		createMenu();
	}

	private void createMenu() {
		for (int i = 0; i < numberOfElements; i++) {
			toogleButtons[i] = new JToggleButton();
			toogleButtons[i].setSelected(false);
			toogleButtons[i].addActionListener(this);
			toogleButtons[i].setIcon(new ImageIcon(Toolkit
					.getDefaultToolkit()
					.getImage(
							getClass().getResource(
									"/" + (i + 1) + ".png"))
					.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH)));
			super.add(toogleButtons[i]);
		}
		closeBtn.addActionListener(this);
		closeBtn.setIcon(new ImageIcon(Toolkit
				.getDefaultToolkit()
				.getImage(
						getClass().getResource(
								"/" + (numberOfElements + 1)
										+ ".png"))
				.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH)));
		super.add(closeBtn);
		toogleButtons[0].setSelected(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < toogleButtons.length; i++) {
			if (e.getSource() == toogleButtons[i]) {
				toogleButtons[i].setSelected(true);
				panels[i].setVisible(true);
			} else {
				toogleButtons[i].setSelected(false);
				panels[i].setVisible(false);
			}
		}
		if (e.getSource() == closeBtn) {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			frame.dispatchEvent(new WindowEvent(frame,
					WindowEvent.WINDOW_CLOSING));
		}
	}

	public JToggleButton[] getToogleButtons() {
		return toogleButtons;
	}
}
