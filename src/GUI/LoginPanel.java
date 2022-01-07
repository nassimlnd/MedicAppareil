package GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class LoginPanel extends JPanel {
	// Attributs
	
	static JTextField textFieldIdentifiant;
	static JPasswordField textFieldMotDePasse;
	boolean connected = false;
	static JButton btnConnexion;
	static JRadioButton radioButtonAdmin;
	static JRadioButton radioButtonTechnicien;
	static JRadioButton radioButtonMedecin;
	public static JLabel labelErreur;
	static ButtonGroup buttonGroup;
	static JButton buttonStatistiques;

	// Constructeur

	public LoginPanel() {
		setLayout(null);
		buttonGroup = new ButtonGroup();
		
		btnConnexion = new JButton("");
		btnConnexion.setSelectedIcon(new ImageIcon(LoginPanel.class.getResource("/connexionButtonSelected.png")));
		btnConnexion.setIcon(new ImageIcon(LoginPanel.class.getResource("/connexionButton.png")));
		btnConnexion.setBounds(703, 328, 140, 44);
		ImageIcon imageIcon = new ImageIcon("/connexionButtonSelected.png");
		
		buttonStatistiques = new JButton("");
		buttonStatistiques.setBackground(null);
		buttonStatistiques.setOpaque(false);
		buttonStatistiques.setIcon(new ImageIcon(LoginPanel.class.getResource("/buttonStatistiques.png")));
		buttonStatistiques.setSelectedIcon(new ImageIcon(LoginPanel.class.getResource("/buttonStatistiquesPressed.png")));
		buttonStatistiques.setPressedIcon(new ImageIcon(LoginPanel.class.getResource("/buttonStatistiquesPressed.png")));
		buttonStatistiques.setBounds(194, 380, 115, 33);
		add(buttonStatistiques);
		
		labelErreur = new JLabel("Identifiant ou mot de passe incorrect.");
		labelErreur.setHorizontalAlignment(SwingConstants.CENTER);
		labelErreur.setForeground(Color.WHITE);
		labelErreur.setFont(new Font("Montserrat", Font.PLAIN, 13));
		labelErreur.setBounds(638, 303, 270, 14);
		add(labelErreur);
		btnConnexion.setPressedIcon(new ImageIcon(LoginPanel.class.getResource("/connexionButtonSelected.png")));
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
		radioButtonTechnicien.setBackground(null);
		radioButtonTechnicien.setOpaque(false);
		radioButtonTechnicien.setBounds(87, 284, 21, 23);
		add(radioButtonTechnicien);
		
		radioButtonMedecin = new JRadioButton("");
		radioButtonMedecin.setBackground(null);
		radioButtonMedecin.setOpaque(false);
		radioButtonMedecin.setBounds(87, 249, 21, 23);
		add(radioButtonMedecin);
		
		radioButtonAdmin = new JRadioButton("");
		radioButtonAdmin.setBackground(null);
		radioButtonAdmin.setOpaque(false);
		radioButtonAdmin.setBounds(87, 213, 21, 23);
		add(radioButtonAdmin);

		buttonGroup.add(radioButtonTechnicien);
		buttonGroup.add(radioButtonMedecin);
		buttonGroup.add(radioButtonAdmin);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(LoginPanel.class.getResource("/background.png")));
		background.setBounds(0, 0, 1000, 500);
		add(background);

		textFieldIdentifiant.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}
			public void removeUpdate(DocumentEvent e) {
				changed();
			}
			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {
				if (textFieldIdentifiant.getText().equals("")){
					btnConnexion.setEnabled(false);
				}
				else {
					btnConnexion.setEnabled(true);
				}

			}
		});

		textFieldMotDePasse.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}
			public void removeUpdate(DocumentEvent e) {
				changed();
			}
			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {
				if (textFieldMotDePasse.getText().equals("")){
					btnConnexion.setEnabled(false);
				}
				else {
					btnConnexion.setEnabled(true);
				}

			}
		});

	}
}
