package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
	
	JTextField textFieldIdentifiant;
	JPasswordField textFieldMotDePasse;
	boolean connected = false;
	JButton btnConnexion;
	JRadioButton radioButtonAdmin;
	JRadioButton radioButtonTechnicien;
	JRadioButton radioButtonMedecin;
	JLabel labelErreur;
	ButtonGroup buttonGroup;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setLayout(null);
		buttonGroup = new ButtonGroup();
		
		btnConnexion = new JButton("");
		btnConnexion.setSelectedIcon(new ImageIcon(LoginPanel.class.getResource("/img/connexionButtonSelected.png")));
		btnConnexion.setIcon(new ImageIcon(LoginPanel.class.getResource("/img/connexionButton.png")));
		btnConnexion.setBounds(703, 328, 140, 44);
		ImageIcon imageIcon = new ImageIcon("/img/connexionButtonSelected.png");
		
		labelErreur = new JLabel("Identifiant ou mot de passe incorrect.");
		labelErreur.setHorizontalAlignment(SwingConstants.CENTER);
		labelErreur.setForeground(Color.WHITE);
		labelErreur.setFont(new Font("Montserrat", Font.PLAIN, 13));
		labelErreur.setBounds(638, 303, 270, 14);
		add(labelErreur);
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
		
		radioButtonTechnicien = new JRadioButton("");
		radioButtonTechnicien.setBackground(new Color(153, 0, 255));
		radioButtonTechnicien.setBounds(87, 284, 21, 23);
		add(radioButtonTechnicien);
		
		radioButtonMedecin = new JRadioButton("");
		radioButtonMedecin.setBackground(new Color(153, 0, 255));
		radioButtonMedecin.setBounds(87, 249, 21, 23);
		add(radioButtonMedecin);
		
		radioButtonAdmin = new JRadioButton("");
		radioButtonAdmin.setBackground(new Color(153, 0, 255));
		radioButtonAdmin.setBounds(87, 213, 21, 23);
		add(radioButtonAdmin);

		buttonGroup.add(radioButtonTechnicien);
		buttonGroup.add(radioButtonMedecin);
		buttonGroup.add(radioButtonAdmin);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(LoginPanel.class.getResource("/img/background.png")));
		background.setBounds(0, 0, 1000, 500);
		add(background);

	}
}
