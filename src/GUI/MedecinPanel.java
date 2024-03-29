package GUI;

import Exceptions.EmptyFieldException;
import org.Consultation;
import org.Patient;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MedecinPanel extends JPanel {
	// Attributs

	JLabel background;
	JButton buttonDeconnexion;
	static JComboBox comboBox;
	public static JTable table;
	JLabel labelError;
	DefaultTableModel defaultTableModel;
	public String[] names;
	public static JTextField textFieldNomMedecin;
	public static JComboBox textFieldPathologies;
	public static JTextField textFieldAppareil;

	// Constructeur

	public MedecinPanel() {
		setLayout(null);
		Consultation.initList();

		String[] columns = {"Nom du patient", "Nom du médecin", "Date", "Pathologies diagnostiquées", "Appareil" };
		defaultTableModel = new DefaultTableModel(columns, 0);
		table = new JTable(defaultTableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setFields();
			}
		});
		initTab(defaultTableModel, table);
		
		textFieldAppareil = new JTextField();
		textFieldAppareil.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldAppareil.setColumns(10);
		textFieldAppareil.setBorder(new LineBorder(Color.WHITE));
		textFieldAppareil.setBounds(682, 125, 267, 25);
		add(textFieldAppareil);
		
		JScrollPane scrollPanetTable = new JScrollPane(table);
		scrollPanetTable.setBounds(298, 191, 655, 263);
		add(scrollPanetTable);

		labelError = new JLabel();
		labelError.setHorizontalAlignment(SwingConstants.CENTER);
		labelError.setForeground(Color.WHITE);
		labelError.setFont(new Font("Montserrat", Font.PLAIN, 11));
		labelError.setBounds(459, 162, 332, 14);
		add(labelError);
		
		textFieldPathologies = new JComboBox();
		textFieldPathologies.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldPathologies.setBounds(675, 55, 279, 33);
		add(textFieldPathologies);
		textFieldPathologies.setSelectedIndex(-1);

		
		textFieldNomMedecin = new JTextField();
		textFieldNomMedecin.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldNomMedecin.setBorder(new LineBorder(Color.WHITE));
		textFieldNomMedecin.setBounds(307, 125, 267, 25);
		add(textFieldNomMedecin);
		textFieldNomMedecin.setColumns(10);


		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel<Patient>(Patient.getListePatient().toArray(new Patient[0])));
		comboBox.setFont(new Font("Montserrat", Font.PLAIN, 12));
		comboBox.setBounds(298, 47, 284, 39);
		add(comboBox);
		comboBox.setSelectedIndex(-1);
		
		buttonDeconnexion = new JButton("");
		buttonDeconnexion.setOpaque(false);
		buttonDeconnexion.setBackground((Color) null);
		buttonDeconnexion.setIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonDeconnexion.png")));
		buttonDeconnexion.setSelectedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonPressedDeconnexion.png")));
		buttonDeconnexion.setPressedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonPressedDeconnexion.png")));
		buttonDeconnexion.setBounds(63, 372, 125, 33);
		add(buttonDeconnexion);
		
		JButton buttonSupprimer = new JButton("");
		buttonSupprimer.setOpaque(false);
		buttonSupprimer.setBackground((Color) null);
		buttonSupprimer.setIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonSupprimer.png")));
		buttonSupprimer.setPressedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonSupprimerPressed.png")));
		buttonSupprimer.setSelectedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonSupprimerPressed.png")));
		buttonSupprimer.setBounds(72, 225, 107, 33);
		add(buttonSupprimer);
		
		JButton buttonRechercher = new JButton("");
		buttonRechercher.setOpaque(false);
		buttonRechercher.setBackground((Color) null);
		buttonRechercher.setIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonRechercher.png")));
		buttonRechercher.setPressedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonRechercherPressed.png")));
		buttonRechercher.setSelectedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonRechercherPressed.png")));
		buttonRechercher.setBounds(69, 293, 113, 33);
		add(buttonRechercher);
		
		JButton buttonModifier = new JButton("");
		buttonModifier.setBackground(null);
		buttonModifier.setOpaque(false);
		buttonModifier.setIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonModifier.png")));
		buttonModifier.setPressedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonModifierPressed.png")));
		buttonModifier.setSelectedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonModifierPressed.png")));
		buttonModifier.setBounds(80, 157, 91, 33);
		add(buttonModifier);
		
		JButton buttonAjouter = new JButton("");
		buttonAjouter.setBackground(null);
		buttonAjouter.setOpaque(false);
		buttonAjouter.setIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonAjouter.png")));
		buttonAjouter.setPressedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonAjouterPressed.png")));
		buttonAjouter.setSelectedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonAjouterPressed.png")));
		buttonAjouter.setBounds(83, 89, 84, 33);
		add(buttonAjouter);
		
		background = new JLabel("");
		background.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearFields();
			}
		});
		background.setIcon(new ImageIcon(MedecinPanel.class.getResource("/backgroundMedecinPanel.png")));
		background.setBounds(0, 0, 1000, 500);
		add(background);

		buttonAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (textFieldPathologies.getSelectedIndex() == -1 || textFieldNomMedecin.getText().isEmpty()) {
						throw new EmptyFieldException();
					}

					Patient patient = Patient.getListePatient().get(comboBox.getSelectedIndex());
					String nomMedecin = textFieldNomMedecin.getText();
					String pathologies = textFieldPathologies.getSelectedItem().toString();
					String appareil = textFieldAppareil.getText();

					if (textFieldAppareil.getText().isEmpty()) {
						new Consultation(patient, pathologies, nomMedecin);
					}
					else new Consultation(patient, pathologies, appareil, nomMedecin);

					comboBox.setSelectedIndex(-1);
					textFieldNomMedecin.setText("");
					textFieldPathologies.setSelectedIndex(-1);
					textFieldAppareil.setText("");

					initTab(defaultTableModel, table);
					labelError.setForeground(Color.white);
				} catch (EmptyFieldException emptyFieldException) {
					labelError.setText("Champs incorrects. Veuillez réessayer.");
					labelError.setForeground(Color.red);
				} catch (NumberFormatException numberFormatException) {
					labelError.setText(numberFormatException.getMessage());
					labelError.setForeground(Color.red);
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				catch (IndexOutOfBoundsException indexOutOfBoundsException) {
					labelError.setText("Champs incorrects. Veuillez réessayer.");
					labelError.setForeground(Color.red);
				}
			}
		});

		buttonModifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nomMedecin = textFieldNomMedecin.getText();
				String pathologies = textFieldPathologies.getSelectedItem().toString();
				String appareil = textFieldAppareil.getText();

				int ligneSelectionne = table.getSelectedRow();

				Consultation consultationmodif = Consultation.getListeConsultation().get(ligneSelectionne);

				if (appareil.isEmpty()) {
					consultationmodif.setAppareil("");
				}

				consultationmodif.setAppareil(appareil);
				consultationmodif.setNomMedecin(nomMedecin);
				consultationmodif.setPathologies(pathologies);
				consultationmodif.modif();

				Consultation.initList();
				initTab(defaultTableModel, table);

				textFieldNomMedecin.setText("");
				textFieldPathologies.setSelectedIndex(-1);
				textFieldAppareil.setText("");
				comboBox.setSelectedIndex(-1);
				labelError.setForeground(Color.white);
			}
		});

		buttonSupprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ligneSelectionne = table.getSelectedRow();

				Consultation consultationsuppr = Consultation.getListeConsultation().get(ligneSelectionne);

				PopupSupprimer popupSupprimer = new PopupSupprimer();
				popupSupprimer.labelPatient.setText("La consultation de " + consultationsuppr.getPatient().getNom() + " " + consultationsuppr.getPatient().getPrenom());
				popupSupprimer.buttonOui.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						consultationsuppr.supprimerConsultation();

						initTab(defaultTableModel, table);

						textFieldAppareil.setText("");
						textFieldPathologies.setSelectedIndex(-1);
						textFieldNomMedecin.setText("");
						comboBox.setSelectedIndex(-1);

						popupSupprimer.dispose();
					}
				});
			}
		});

		buttonRechercher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RechercheMedecin rechercheMedecin = new RechercheMedecin();
			}
		});

	}

	// Méthodes

	public void initTab(DefaultTableModel defaultTableModel, JTable table) {
		// Initialise le tableau
		defaultTableModel.setRowCount(0);
		table.revalidate();

		for (int i = 0; i < Consultation.getListeConsultation().size(); i ++) {
			String nom = Consultation.getListeConsultation().get(i).getPatient().getNom() + " " + Consultation.getListeConsultation().get(i).getPatient().getPrenom();
			String nomMedecin = Consultation.getListeConsultation().get(i).getNomMedecin();
			String date = Consultation.getListeConsultation().get(i).getDate();
			String pathologies = Consultation.getListeConsultation().get(i).getPathologies();
			String appareil = Consultation.getListeConsultation().get(i).getAppareil();

			String[] data = {nom, nomMedecin, date, pathologies, appareil};

			defaultTableModel.addRow(data);
		}

		defaultTableModel.fireTableDataChanged();
	}

	public void initPathologies() {
		ArrayList<String> pathologies = new ArrayList<>();
		for (int i = 0; i < 10; i ++) {
			pathologies.add("Pathologies " + i);
		}

		textFieldPathologies.setModel(new DefaultComboBoxModel<String>(pathologies.toArray(new String[0])));
	}

	public void initCombo(JComboBox comboBox) {
		// Initialise la comboBox
		comboBox.setModel(new DefaultComboBoxModel<Patient>(Patient.getListePatient().toArray(new Patient[0])));
	}

	public static void setFields() {
		// Set les fields de la ligne sélectionnée.
		int ligneSelectionne = table.getSelectedRow();

		String value = (String) table.getValueAt(ligneSelectionne, 2);

		Consultation consultation = Consultation.getListeConsultation().get(ligneSelectionne);

		comboBox.setSelectedIndex(consultation.getPatient().getId() - 1);

		textFieldPathologies.setSelectedIndex(Integer.parseInt(String.valueOf(consultation.getPathologies().charAt(consultation.getPathologies().length() - 1))));
		textFieldNomMedecin.setText(consultation.getNomMedecin());
		textFieldAppareil.setText(consultation.getAppareil());
	}

	public static void clearFields() {
		// Clear les fields si une ligne est sélectionnée.
		if (table.getSelectedRow() != -1) {
			table.clearSelection();

			comboBox.setSelectedIndex(-1);
			textFieldNomMedecin.setText("");
			textFieldPathologies.setSelectedIndex(-1);
			textFieldAppareil.setText("");
		}
	}

}
