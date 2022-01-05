package GUI;

import org.Consultation;
import org.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RechercheMedecin extends JDialog {
	private JTable table;
	DefaultTableModel defaultTableModel;
	public int idCons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RechercheMedecin dialog = new RechercheMedecin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RechercheMedecin() {
		setBounds(100, 100, 767, 415);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JButton buttonAnnuler = new JButton("");
		buttonAnnuler.setBounds(634, 331, 88, 33);
		buttonAnnuler.setIcon(new ImageIcon(RechercheMedecin.class.getResource("/buttonAnnuler.png")));
		buttonAnnuler.setSelectedIcon(new ImageIcon(RechercheMedecin.class.getResource("/buttonAnnulerPressed.png")));
		buttonAnnuler.setPressedIcon(new ImageIcon(RechercheMedecin.class.getResource("/buttonAnnulerPressed.png")));
		getContentPane().add(buttonAnnuler);
		
		JButton buttonValider = new JButton("");
		buttonValider.setBounds(539, 331, 80, 33);
		buttonValider.setIcon(new ImageIcon(RechercheMedecin.class.getResource("/buttonValider.png")));
		buttonValider.setPressedIcon(new ImageIcon(RechercheMedecin.class.getResource("/buttonValiderPressed.png")));
		buttonValider.setSelectedIcon(new ImageIcon(RechercheMedecin.class.getResource("/buttonValiderPressed.png")));
		getContentPane().add(buttonValider);
		
		JButton buttonRechercher = new JButton("");
		buttonRechercher.setBounds(609, 18, 113, 33);
		buttonRechercher.setIcon(new ImageIcon(RechercheMedecin.class.getResource("/buttonRechercherPopup.png")));
		buttonRechercher.setPressedIcon(new ImageIcon(RechercheMedecin.class.getResource("/buttonRechercherPressedPopup.png")));
		buttonRechercher.setSelectedIcon(new ImageIcon(RechercheMedecin.class.getResource("/buttonRechercherPressedPopup.png")));
		getContentPane().add(buttonRechercher);


		String names[] = new String[Patient.getListePatient().size()];
		for (int i = 0; i < Patient.getListePatient().size(); i ++) {
			names[i] = Patient.getListePatient().get(i).getNom() + " " + Patient.getListePatient().get(i).getPrenom() + " " + Patient.getListePatient().get(i).getDateNaissance();
		}
		JComboBox comboBox = new JComboBox(names);
		comboBox.setFont(new Font("Montserrat", Font.PLAIN, 12));
		comboBox.setBounds(29, 15, 284, 39);
		getContentPane().add(comboBox);

		String columns[] = {"Pathologies", "Date", "Nom du Médecin", "Appareil"};

		defaultTableModel = new DefaultTableModel(columns, 0);

		table = new JTable(defaultTableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(29, 74, 693, 247);
		getContentPane().add(scrollPane);

		buttonRechercher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Patient patient = Patient.getListePatient().get(comboBox.getSelectedIndex());

				defaultTableModel.setRowCount(0);
				table.revalidate();

				for (int i = 0; i < patient.getListeConsultationPatient().size(); i ++) {
					String pathologies = patient.getListeConsultationPatient().get(i).getPathologies();
					String date = patient.getListeConsultationPatient().get(i).getDate();
					String nomMedecin = patient.getListeConsultationPatient().get(i).getNomMedecin();
					String appareil = patient.getListeConsultationPatient().get(i).getAppareil();

					String[] data = {pathologies, date, nomMedecin, appareil};

					defaultTableModel.addRow(data);

				}

				defaultTableModel.fireTableDataChanged();
				table.revalidate();
				scrollPane.repaint();
			}
		});

		buttonAnnuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		buttonValider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelected = table.getSelectedRow();
				Patient patient;

				patient = Patient.getListePatient().get(comboBox.getSelectedIndex());
				idCons = patient.getListeConsultationPatient().get(rowSelected).getId();

				MedecinPanel.table.setRowSelectionInterval(idCons, idCons);

				Consultation consultation = Consultation.getListeConsultation().get(idCons);

				MedecinPanel.comboBox.setSelectedIndex(consultation.getPatient().getId() - 1);

				MedecinPanel.textFieldPathologies.setSelectedIndex(Integer.parseInt(String.valueOf(consultation.getPathologies().charAt(consultation.getPathologies().length() - 1))));
				MedecinPanel.textFieldNomMedecin.setText(consultation.getNomMedecin());
				MedecinPanel.textFieldAppareil.setText(consultation.getAppareil());
				dispose();
			}
		});
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 750, 375);
		background.setIcon(new ImageIcon(RechercheMedecin.class.getResource("/backgroundRechercheMedecin.png")));
		getContentPane().add(background);
	}
}
