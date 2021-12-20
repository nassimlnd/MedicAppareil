package GUI;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class LoginPanel extends JPanel {
	
	private JTextField textFieldIdentifiant;
	private JPasswordField textFieldMotDePasse;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setLayout(null);

		ButtonGroup buttonGroup = new ButtonGroup();
		
		JButton btnConnexion = new JButton("");
		btnConnexion.setSelectedIcon(new ImageIcon(LoginPanel.class.getResource("/img/connexionButtonSelected.png")));
		btnConnexion.setIcon(new ImageIcon(LoginPanel.class.getResource("/img/connexionButton.png")));
		btnConnexion.setBounds(703, 328, 140, 44);
		ImageIcon imageIcon = new ImageIcon("/img/connexionButtonSelected.png");
		btnConnexion.setPressedIcon(new ImageIcon(LoginPanel.class.getResource("/img/connexionButtonSelected.png")));
		add(btnConnexion);
		
		textFieldMotDePasse = new JPasswordField();
		textFieldMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldMotDePasse.setBounds(638, 253, 270, 25);
		add(textFieldMotDePasse);
		textFieldMotDePasse.setColumns(10);
		textFieldMotDePasse.setBorder(BorderFactory.createLineBorder(Color.white));
		
		textFieldIdentifiant = new JTextField();
		textFieldIdentifiant.setFont(new Font("Montserrat", Font.PLAIN, 16));
		textFieldIdentifiant.setBounds(638, 151, 270, 25);
		add(textFieldIdentifiant);
		textFieldIdentifiant.setColumns(10);
		textFieldIdentifiant.setBorder(BorderFactory.createLineBorder(Color.white));
		
		JRadioButton radioButtonAdmin = new JRadioButton("");
		radioButtonAdmin.setBackground(new Color(153, 0, 255));
		radioButtonAdmin.setBounds(87, 284, 21, 23);
		add(radioButtonAdmin);
		
		JRadioButton radioButtonMedecin = new JRadioButton("");
		radioButtonMedecin.setBackground(new Color(153, 0, 255));
		radioButtonMedecin.setBounds(87, 249, 21, 23);
		add(radioButtonMedecin);
		
		JRadioButton radioButtonTechnicien = new JRadioButton("");
		radioButtonTechnicien.setBackground(new Color(153, 0, 255));
		radioButtonTechnicien.setBounds(87, 213, 21, 23);
		add(radioButtonTechnicien);

		buttonGroup.add(radioButtonAdmin);
		buttonGroup.add(radioButtonMedecin);
		buttonGroup.add(radioButtonTechnicien);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(LoginPanel.class.getResource("/img/background.png")));
		background.setBounds(0, 0, 1000, 500);
		add(background);

	}
}
