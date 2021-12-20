package GUI;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

	JButton btnDeconnexion;

	/**
	 * Create the panel.
	 */
	public AdminPanel() {
		setLayout(null);
		
		btnDeconnexion = new JButton("");
		btnDeconnexion.setBackground(new Color(153, 0, 255));
		btnDeconnexion.setForeground(new Color(153, 0, 255));
		btnDeconnexion.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonDeconnexion.png")));
		btnDeconnexion.setBounds(56, 372, 125, 33);
		btnDeconnexion.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonPressedDeconnexion.png")));
		btnDeconnexion.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonPressedDeconnexion.png")));
		add(btnDeconnexion);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/bgAdminPanel.png")));
		background.setBounds(0, 0, 1000, 500);
		add(background);

	}
}
