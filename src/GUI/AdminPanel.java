package GUI;

import Exceptions.EmptyFieldException;
import Exceptions.PatientAlreadyPresentException;
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
import java.io.IOException;
import java.text.ParseException;

public class AdminPanel extends JPanel {

	static JButton btnDeconnexion;
	static JButton buttonRechercher;
	static JButton buttonSupprimer;
	static JButton buttonModifier;
	static JButton buttonAjouter;
	static JTextField textFieldNom;
	static JTextField textFieldPrenom;
	static JFormattedTextField textFieldDateDeNaissance;
	static JTextField textFieldNumeroSS;
	static DefaultTableModel defaultTableModel;
	static JTable table;
	static JLabel labelError;

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

		for (int i = 0; i < Patient.getListePatient().size(); i ++) {
			String nom = Patient.getListePatient().get(i).getNom();
			String prenom = Patient.getListePatient().get(i).getPrenom();
			String datedenaissance = Patient.getListePatient().get(i).getDateNaissance();
			String numeross = Patient.getListePatient().get(i).getNbSecuriteSociale();

			String[] data = {nom, prenom, datedenaissance, numeross};

			defaultTableModel.addRow(data);

		}
		
		labelError = new JLabel();
		labelError.setHorizontalAlignment(SwingConstants.CENTER);
		labelError.setForeground(Color.WHITE);
		labelError.setFont(new Font("Montserrat", Font.PLAIN, 11));
		labelError.setBounds(459, 162, 332, 14);
		add(labelError);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(298, 191, 655, 250);
		add(scrollPane);
		
		textFieldNumeroSS = new JTextField();
		textFieldNumeroSS.setForeground(Color.BLACK);
		textFieldNumeroSS.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldNumeroSS.setColumns(10);
		textFieldNumeroSS.setBorder(new LineBorder(Color.WHITE));
		textFieldNumeroSS.setBounds(681, 125, 269, 25);
		add(textFieldNumeroSS);
		
		MaskFormatter formatter = new MaskFormatter("##/##/####");
        formatter.setPlaceholder("#");
		
		textFieldDateDeNaissance = new JFormattedTextField(formatter);
		textFieldDateDeNaissance.setForeground(Color.BLACK);
		textFieldDateDeNaissance.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldDateDeNaissance.setColumns(10);
		textFieldDateDeNaissance.setBorder(new LineBorder(Color.WHITE));
		textFieldDateDeNaissance.setBounds(301, 125, 269, 25);
		add(textFieldDateDeNaissance);

		textFieldDateDeNaissance.setText("");
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setForeground(Color.BLACK);
		textFieldPrenom.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBorder(new LineBorder(Color.WHITE));
		textFieldPrenom.setBounds(681, 61, 269, 25);
		add(textFieldPrenom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBorder(new LineBorder(Color.WHITE));
		textFieldNom.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldNom.setForeground(Color.BLACK);
		textFieldNom.setBounds(301, 61, 269, 25);
		add(textFieldNom);
		textFieldNom.setColumns(10);
		
		buttonRechercher = new JButton("");
		buttonRechercher.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonRechercherPressed.png")));
		buttonRechercher.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonRechercherPressed.png")));
		buttonRechercher.setBackground(null);
		buttonRechercher.setOpaque(false);
		buttonRechercher.setIcon(new ImageIcon(AdminPanel.class.getResource("/buttonRechercher.png")));
		buttonRechercher.setBounds(69, 291, 113, 33);
		add(buttonRechercher);
		
		buttonSupprimer = new JButton("");
		buttonSupprimer.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonSupprimerPressed.png")));
		buttonSupprimer.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonSupprimerPressed.png")));
		buttonSupprimer.setBackground(null);
		buttonSupprimer.setOpaque(false);
		buttonSupprimer.setIcon(new ImageIcon(AdminPanel.class.getResource("/buttonSupprimer.png")));
		buttonSupprimer.setBounds(72, 224, 107, 33);
		add(buttonSupprimer);
		
		buttonModifier = new JButton("");
		buttonModifier.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonModifierPressed.png")));
		buttonModifier.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonModifierPressed.png")));
		buttonModifier.setBackground(null);
		buttonModifier.setOpaque(false);
		buttonModifier.setIcon(new ImageIcon(AdminPanel.class.getResource("/buttonModifier.png")));
		buttonModifier.setBounds(80, 157, 91, 33);
		add(buttonModifier);
		
		buttonAjouter = new JButton("");
		buttonAjouter.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonAjouterPressed.png")));
		buttonAjouter.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonAjouterPressed.png")));
		buttonAjouter.setBackground(null);
		buttonAjouter.setOpaque(false);
		buttonAjouter.setIcon(new ImageIcon(AdminPanel.class.getResource("/buttonAjouter.png")));
		buttonAjouter.setBounds(83, 90, 84, 33);
		add(buttonAjouter);

		buttonAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (textFieldNom.getText().isEmpty() || textFieldPrenom.getText().isEmpty() || textFieldDateDeNaissance.getText().isEmpty() || textFieldNumeroSS.getText().isEmpty()) {
						throw new EmptyFieldException();
					}

					String nom = textFieldNom.getText();
					String prenom = textFieldPrenom.getText();
					String datedenaissance = textFieldDateDeNaissance.getText();
					String numeross = textFieldNumeroSS.getText();

					new Patient(nom, prenom, datedenaissance, numeross);

					textFieldNom.setText("");
					textFieldDateDeNaissance.setText("");
					textFieldPrenom.setText("");
					textFieldNumeroSS.setText("");

					initTab(defaultTableModel, table);
				}
				catch (PatientAlreadyPresentException patientAlreadyPresentException) {
					labelError.setText("Patient déjà présent. Veuillez vérifier les données du patient.");
					labelError.setForeground(Color.red);
				} catch (EmptyFieldException emptyFieldException) {
					labelError.setText("Champs incorrects. Veuillez réessayer.");
					labelError.setForeground(Color.red);
				} catch (NumberFormatException numberFormatException) {
					labelError.setText(numberFormatException.getMessage());
					labelError.setForeground(Color.red);
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}

			}
		});
		
		btnDeconnexion = new JButton("");
		btnDeconnexion.setBackground(null);
		btnDeconnexion.setOpaque(false);
		btnDeconnexion.setForeground(new Color(153, 0, 255));
		btnDeconnexion.setIcon(new ImageIcon(AdminPanel.class.getResource("/buttonDeconnexion.png")));
		btnDeconnexion.setBounds(63, 358, 125, 33);
		btnDeconnexion.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonPressedDeconnexion.png")));
		btnDeconnexion.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonPressedDeconnexion.png")));
		add(btnDeconnexion);
		
		JLabel background = new JLabel("");
		background.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (table.getSelectedRow() != -1) {
					table.clearSelection();

					textFieldNom.setText("");
					textFieldDateDeNaissance.setText("");
					textFieldPrenom.setText("");
					textFieldNumeroSS.setText("");
				}
			}
		});
		background.setIcon(new ImageIcon(AdminPanel.class.getResource("/bgAdminPanel.png")));
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

				Patient.getListePatient().get(ligneSelectionne).setPrenom(prenom);
				Patient.getListePatient().get(ligneSelectionne).setNom(nom);
				Patient.getListePatient().get(ligneSelectionne).setDateNaissance(datedenaissance);
				Patient.getListePatient().get(ligneSelectionne).setNbSecuriteSociale(numeroSS);
				Patient.getListePatient().get(ligneSelectionne).modif();

				Patient.initList();
				initTab(defaultTableModel, table);

				textFieldNom.setText("");
				textFieldDateDeNaissance.setText("");
				textFieldPrenom.setText("");
				textFieldNumeroSS.setText("");
			}
		});

		buttonSupprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ligneSelectionne = table.getSelectedRow();

				Patient patientSuppr = Patient.getListePatient().get(ligneSelectionne);

				PopupSupprimer popupSupprimer = new PopupSupprimer();
				popupSupprimer.labelPatient.setText(patientSuppr.getNom() + " " + patientSuppr.getPrenom());
				popupSupprimer.buttonOui.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						patientSuppr.supprimerPatient();

						initTab(defaultTableModel, table);

						textFieldNom.setText("");
						textFieldDateDeNaissance.setText("");
						textFieldPrenom.setText("");
						textFieldNumeroSS.setText("");

						popupSupprimer.dispose();
					}
				});
			}
		});

		buttonRechercher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupRecherche popupRecherche = new PopupRecherche();
			}
		});
		
		

	}

	public void initTab(DefaultTableModel defaultTableModel, JTable table) {
		defaultTableModel.setRowCount(0);
		table.revalidate();

		for (int i = 0; i < Patient.getListePatient().size(); i ++) {
			String nom1 = Patient.getListePatient().get(i).getNom();
			String prenom = Patient.getListePatient().get(i).getPrenom();
			String datedenaissance1 = Patient.getListePatient().get(i).getDateNaissance();
			String numeross1 = Patient.getListePatient().get(i).getNbSecuriteSociale();

			String[] data = {nom1, prenom, datedenaissance1, numeross1};

			defaultTableModel.addRow(data);

		}

		defaultTableModel.fireTableDataChanged();
	}
}
