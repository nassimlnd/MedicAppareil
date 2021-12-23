package GUI;

import org.Patient;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

public class AdminPanel extends JPanel {

	JButton btnDeconnexion;
	JButton buttonRechercher;
	JButton buttonSupprimer;
	JButton buttonModifier;
	JButton buttonAjouter;
	JTextField textFieldNom;
	JTextField textFieldPrenom;
	JFormattedTextField textFieldDateDeNaissance;
	JTextField textFieldNumeroSS;
	DefaultTableModel defaultTableModel;
	JTable table;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public AdminPanel() throws ParseException {
		setLayout(null);

		Patient.initList();

		String[] columns = {"Nom", "Prénom", "Date de naissance", "Numéro de sécurité sociale" };

		defaultTableModel = new DefaultTableModel(columns, 0);

		table = new JTable(defaultTableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligneSelectionne = table.getSelectedRow();

				String value = (String) table.getValueAt(ligneSelectionne, 2);

				textFieldNom.setText(defaultTableModel.getValueAt(ligneSelectionne,0).toString());
				textFieldPrenom.setText(defaultTableModel.getValueAt(ligneSelectionne, 1).toString());
				textFieldDateDeNaissance.setText(defaultTableModel.getValueAt(ligneSelectionne,2).toString());
				textFieldNumeroSS.setText(defaultTableModel.getValueAt(ligneSelectionne, 3).toString());

			}
		});

		for (int i = 0; i < Patient.listePatient.size(); i ++) {
			String nom = Patient.listePatient.get(i).getNom();
			String prenom = Patient.listePatient.get(i).getPrenom();
			String datedenaissance = Patient.listePatient.get(i).getDateNaissance();
			String numeross = Patient.listePatient.get(i).getNbSecuriteSociale();

			String[] data = {nom, prenom, datedenaissance, numeross};

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
		
		MaskFormatter formatter = new MaskFormatter("##/##/####");
        formatter.setPlaceholder("#");
		
		textFieldDateDeNaissance = new JFormattedTextField(formatter);
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
		
		buttonRechercher = new JButton("");
		buttonRechercher.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonRechercherPressed.png")));
		buttonRechercher.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonRechercherPressed.png")));
		buttonRechercher.setBackground(new Color(153, 0, 255));
		buttonRechercher.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonRechercher.png")));
		buttonRechercher.setBounds(63, 293, 113, 33);
		add(buttonRechercher);
		
		buttonSupprimer = new JButton("");
		buttonSupprimer.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonSupprimerPressed.png")));
		buttonSupprimer.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonSupprimerPressed.png")));
		buttonSupprimer.setBackground(new Color(153, 0, 255));
		buttonSupprimer.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonSupprimer.png")));
		buttonSupprimer.setBounds(71, 226, 97, 31);
		add(buttonSupprimer);
		
		buttonModifier = new JButton("");
		buttonModifier.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonModifierPressed.png")));
		buttonModifier.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonModifierPressed.png")));
		buttonModifier.setBackground(new Color(153, 0, 255));
		buttonModifier.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonModifier.png")));
		buttonModifier.setBounds(78, 159, 82, 31);
		add(buttonModifier);
		
		buttonAjouter = new JButton("");
		buttonAjouter.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonAjouterPressed.png")));
		buttonAjouter.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonAjouterPressed.png")));
		buttonAjouter.setBackground(new Color(153, 0, 255));
		buttonAjouter.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/buttonAjouter.png")));
		buttonAjouter.setBounds(80, 93, 77, 31);
		add(buttonAjouter);

		buttonAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nom = textFieldNom.getText();
				String prenom = textFieldPrenom.getText();
				String datedenaissance = textFieldDateDeNaissance.getText();
				String numeross = textFieldNumeroSS.getText();

				new Patient(nom, prenom, datedenaissance, numeross);

				textFieldNom.setText("");
				textFieldDateDeNaissance.setText("");
				textFieldPrenom.setText("");
				textFieldNumeroSS.setText("");

				ajouter(defaultTableModel, table);
			}
		});
		
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
		
		buttonModifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e1) {
				String nom = textFieldNom.getText();
				String prenom = textFieldPrenom.getText();
				String datedenaissance = textFieldDateDeNaissance.getText();
				String numeroSS = textFieldNumeroSS.getText();

				int ligneSelectionne = table.getSelectedRow();

				Patient.listePatient.get(ligneSelectionne).setPrenom(prenom);
				Patient.listePatient.get(ligneSelectionne).setNom(nom);
				Patient.listePatient.get(ligneSelectionne).setDateNaissance(datedenaissance);
				Patient.listePatient.get(ligneSelectionne).setNbSecuriteSociale(numeroSS);

				Patient.listePatient.get(ligneSelectionne).modif();

				Patient.initList();
			}
		});
		
		

	}

	public void ajouter(DefaultTableModel defaultTableModel, JTable table) {
		defaultTableModel.setRowCount(0);
		table.revalidate();

		for (int i = 0; i < Patient.listePatient.size(); i ++) {
			String nom1 = Patient.listePatient.get(i).getNom();
			String prenom = Patient.listePatient.get(i).getPrenom();
			String datedenaissance1 = Patient.listePatient.get(i).getDateNaissance();
			String numeross1 = Patient.listePatient.get(i).getNbSecuriteSociale();

			String[] data = {nom1, prenom, datedenaissance1, numeross1};

			defaultTableModel.addRow(data);

		}

		defaultTableModel.fireTableDataChanged();
	}
}
