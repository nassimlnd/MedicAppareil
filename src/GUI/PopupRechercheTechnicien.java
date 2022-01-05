package GUI;

import Exceptions.ConsultationNotFoundException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class PopupRechercheTechnicien extends JDialog {
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
	public PopupRechercheTechnicien() {
		setResizable(false);
		setBounds(100, 100, 505, 275);
		getContentPane().setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel labelError = new JLabel("Consultation introuvable. Veuillez réessayer.");
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
				int idCons = 0;
				try {
					idCons = TechnicienPanel.recherche(input);
					for (int i = 0; i < TechnicienPanel.listTech.size(); i++) {
						if (TechnicienPanel.listTech.get(i).getId() == idCons) {
							rowNumber = i;
						}
					}
					TechnicienPanel.table.setRowSelectionInterval(rowNumber, rowNumber);
					dispose();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (ConsultationNotFoundException consultationNotFoundException) {
					labelError.setForeground(Color.RED);
				}
			}
		});
	}
}
