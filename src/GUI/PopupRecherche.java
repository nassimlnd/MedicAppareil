package GUI;

import Exceptions.PatientNotFoundException;
import org.Patient;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class PopupRecherche extends JDialog {
	public JTextField textFieldRecherche;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PopupRecherche dialog = new PopupRecherche();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PopupRecherche() {
		setResizable(false);
		setBounds(100, 100, 505, 275);
		getContentPane().setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JLabel labelError = new JLabel("Patient introuvable. Veuillez r\u00E9essayer.");
		labelError.setForeground(Color.WHITE);
		labelError.setHorizontalAlignment(SwingConstants.CENTER);
		labelError.setFont(new Font("Montserrat", Font.PLAIN, 11));
		labelError.setBounds(101, 91, 287, 14);
		getContentPane().add(labelError);

		textFieldRecherche = new JTextField();
		textFieldRecherche.setFont(new Font("Montserrat", Font.PLAIN, 12));
		textFieldRecherche.setBorder(new LineBorder(Color.WHITE));
		textFieldRecherche.setBounds(75, 55, 350, 25);
		getContentPane().add(textFieldRecherche);
		textFieldRecherche.setColumns(10);

		JButton buttonAnnuler = new JButton("");
		buttonAnnuler.setPressedIcon(new ImageIcon(PopupRecherche.class.getResource("/buttonAnnulerPressed.png")));
		buttonAnnuler.setSelectedIcon(new ImageIcon(PopupRecherche.class.getResource("/buttonAnnulerPressed.png")));
		buttonAnnuler.setIcon(new ImageIcon(PopupRecherche.class.getResource("/buttonAnnuler.png")));
		buttonAnnuler.setBounds(300, 160, 88, 33);
		getContentPane().add(buttonAnnuler);

		JButton buttonValider = new JButton("");
		buttonValider.setPressedIcon(new ImageIcon(PopupRecherche.class.getResource("/buttonValiderPressed.png")));
		buttonValider.setSelectedIcon(new ImageIcon(PopupRecherche.class.getResource("/buttonValiderPressed.png")));
		buttonValider.setIcon(new ImageIcon(PopupRecherche.class.getResource("/buttonValider.png")));
		buttonValider.setBounds(101, 160, 80, 33);
		getContentPane().add(buttonValider);

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(PopupRecherche.class.getResource("/PopupRecherche.png")));
		background.setBounds(0, 0, 500, 250);
		getContentPane().add(background);

		buttonAnnuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		buttonValider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = textFieldRecherche.getText();

				int rowNumber = 0;
				try {
					rowNumber = Patient.recherche(input) - 1;
					AdminPanel.table.setRowSelectionInterval(rowNumber, rowNumber);

					AdminPanel.textFieldNom.setText(AdminPanel.defaultTableModel.getValueAt(rowNumber,0).toString());
					AdminPanel.textFieldPrenom.setText(AdminPanel.defaultTableModel.getValueAt(rowNumber, 1).toString());
					AdminPanel.textFieldDateDeNaissance.setText(AdminPanel.defaultTableModel.getValueAt(rowNumber,2).toString());
					AdminPanel.textFieldNumeroSS.setText(AdminPanel.defaultTableModel.getValueAt(rowNumber, 3).toString());

					RechercheMedecin.comboBox.setSelectedIndex(rowNumber);

					dispose();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (PatientNotFoundException ex) {
					labelError.setForeground(Color.red);
				}
			}
		});
	}

	public Patient getSelectedPatient() throws PatientNotFoundException {
		String input = textFieldRecherche.getText();
		int id = -1;

		try {
			id = Patient.recherche(input) - 1;

			return Patient.getListePatient().get(id);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (PatientNotFoundException e) {
			e.printStackTrace();
		}

		if (id == -1) {
			throw new PatientNotFoundException("Patient introuvable.");
		}
		else return Patient.getListePatient().get(id);
	}
}
