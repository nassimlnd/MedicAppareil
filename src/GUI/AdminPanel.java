package GUI;

import org.Patient;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminPanel extends JPanel {

	JButton btnDeconnexion;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldDateDeNaissance;
	private JTextField textFieldNumeroSS;

	/**
	 * Create the panel.
	 */
	public AdminPanel() {
		setLayout(null);

		Patient.initList();

		String[] columns = {"Nom", "Date de naissance", "Numéro de sécurité sociale" };

		DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

		JTable table = new JTable(defaultTableModel);

		for (int i = 0; i < Patient.listePatient.size(); i ++) {
			String nom = Patient.listePatient.get(i).getNom();
			String datedenaissance = Patient.listePatient.get(i).getDateNaissance();
			String numeross = Patient.listePatient.get(i).getNbSecuriteSociale();

			String[] data = {nom, datedenaissance, numeross};

			defaultTableModel.addRow(data);

		}
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(310, 190, 655, 263);
		add(scrollPane);
		
		textFieldNumeroSS = new JTextField();
		textFieldNumeroSS.setForeground(Color.BLACK);
		textFieldNumeroSS.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldNumeroSS.setColumns(10);
		textFieldNumeroSS.setBorder(new LineBorder(Color.WHITE));
		textFieldNumeroSS.setBounds(687, 125, 270, 25);
		add(textFieldNumeroSS);
		
		textFieldDateDeNaissance = new JTextField();
		textFieldDateDeNaissance.setForeground(Color.BLACK);
		textFieldDateDeNaissance.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldDateDeNaissance.setColumns(10);
		textFieldDateDeNaissance.setBorder(new LineBorder(Color.WHITE));
		textFieldDateDeNaissance.setBounds(313, 125, 270, 25);
		add(textFieldDateDeNaissance);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setForeground(Color.BLACK);
		textFieldPrenom.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBorder(new LineBorder(Color.WHITE));
		textFieldPrenom.setBounds(687, 61, 270, 25);
		add(textFieldPrenom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBorder(new LineBorder(Color.WHITE));
		textFieldNom.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldNom.setForeground(Color.BLACK);
		textFieldNom.setBounds(313, 61, 270, 25);
		add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JButton buttonRechercher = new JButton("");
		buttonRechercher.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonRechercherPressed.png")));
		buttonRechercher.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonRechercherPressed.png")));
		buttonRechercher.setBackground(new Color(153, 0, 255));
		buttonRechercher.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonRechercher.png")));
		buttonRechercher.setBounds(63, 293, 113, 33);
		add(buttonRechercher);
		
		JButton buttonSupprimer = new JButton("");
		buttonSupprimer.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonSupprimerPressed.png")));
		buttonSupprimer.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonSupprimerPressed.png")));
		buttonSupprimer.setBackground(new Color(153, 0, 255));
		buttonSupprimer.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonSupprimer.png")));
		buttonSupprimer.setBounds(71, 226, 97, 31);
		add(buttonSupprimer);
		
		JButton buttonModifier = new JButton("");
		buttonModifier.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonModifierPressed.png")));
		buttonModifier.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonModifierPressed.png")));
		buttonModifier.setBackground(new Color(153, 0, 255));
		buttonModifier.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonModifier.png")));
		buttonModifier.setBounds(78, 159, 82, 31);
		add(buttonModifier);
		
		JButton buttonAjouter = new JButton("");
		buttonAjouter.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonAjouterPressed.png")));
		buttonAjouter.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonAjouterPressed.png")));
		buttonAjouter.setBackground(new Color(153, 0, 255));
		buttonAjouter.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonAjouter.png")));
		buttonAjouter.setBounds(80, 93, 77, 31);
		add(buttonAjouter);
		
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
