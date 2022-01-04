package GUI;

import org.Consultation;
import org.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TechnicienPanel extends JPanel {
	static JTextField textFieldNom;
	static JTextField textFieldNomMedecin;
	static JFormattedTextField textFieldDate;
	static JTextField textFieldPathologies;
	static JTextField textFieldStatut;

	/**
	 * Create the panel.
	 */

	JLabel background;
	static JButton buttonDeconnexion;
	JTable table;
	JLabel labelError;
	DefaultTableModel defaultTableModel;




	public static ArrayList<Consultation> listTech = new ArrayList<Consultation>();

	public TechnicienPanel() throws ParseException {
		setLayout(null);

		Patient.initList();

		Consultation.initList();

		String[] columns = {"Nom du patient", "Nom du m?decin", "Date", "Pathologies diagnostiqu?es", "Appareil","statut" };

		defaultTableModel = new DefaultTableModel(columns, 0);

		table = new JTable(defaultTableModel);



		init(defaultTableModel, table);


		JScrollPane scrollPanetTable = new JScrollPane(table);
		scrollPanetTable.setBounds(294, 40, 655, 389);
		add(scrollPanetTable);

		labelError = new JLabel();
		labelError.setHorizontalAlignment(SwingConstants.CENTER);
		labelError.setForeground(Color.WHITE);
		labelError.setFont(new Font("Montserrat", Font.PLAIN, 11));
		labelError.setBounds(459, 162, 332, 14);
		add(labelError);


		String names[] = new String[Patient.getListePatient().size()];
		for (int i = 0; i < Patient.getListePatient().size(); i ++) {
			names[i] = Patient.getListePatient().get(i).getNom() + " " + Patient.getListePatient().get(i).getPrenom() + " " + Patient.getListePatient().get(i).getDateNaissance();
		}

		buttonDeconnexion = new JButton("");
		buttonDeconnexion.setOpaque(false);
		buttonDeconnexion.setBackground((Color) null);
		buttonDeconnexion.setIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonDeconnexion.png")));
		buttonDeconnexion.setSelectedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonPressedDeconnexion.png")));
		buttonDeconnexion.setPressedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonPressedDeconnexion.png")));
		buttonDeconnexion.setBounds(63, 372, 125, 33);
		add(buttonDeconnexion);

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

		background = new JLabel("");
		background.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (table.getSelectedRow() != -1) {
					table.clearSelection();


				}
			}
		});
		buttonModifier.addActionListener(new ActionListener() {

			FileReader fileReader;
			Scanner sc;
			FileWriter fileWriter;
			String oldLine = "";
			String newLine;
			String oldContent = "";
			String newContent;

			public void actionPerformed(ActionEvent e1) {
				int ligne = table.getSelectedRow();
				if(listTech.get(ligne).getOctroi() == false) {
					listTech.get(ligne).setOctroi(true);

				}else {
					listTech.get(ligne).setOctroi(false);
				}


				try {
					File file = new File("consultation.txt");

					fileReader = new FileReader(file.getAbsoluteFile());
					sc = new Scanner(fileReader);

					newLine = listTech.get(ligne).getId() + ";" + (listTech.get(ligne).getPatient().getId()-1) + ";" + listTech.get(ligne).getNomMedecin() + ";" + listTech.get(ligne).getDate() + ";" + listTech.get(ligne).getPathologies()+";"+listTech.get(ligne).getAppareil()+";"+true;

					while (sc.hasNextLine()) {

						String line = sc.nextLine();

						oldContent += line + "\n";

						if (Integer.parseInt(String.valueOf(line.charAt(0))) == listTech.get(ligne).getId()) {
							oldLine = line;
						}
					}

					newContent = oldContent.replaceAll(oldLine, newLine);

					fileWriter = new FileWriter("consultation.txt");
					fileWriter.append(newContent);
					fileWriter.flush();

					fileReader.close();
					sc.close();

				} catch (IOException e) {
					e.printStackTrace();
				}






			}
		});


		buttonRechercher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PopupRecherche popupRecherche = new PopupRecherche();
			}
		});

	}
	public void init(DefaultTableModel defaultTableModel, JTable table) {
		defaultTableModel.setRowCount(0);
		table.revalidate();

		for (int i = 0; i < Consultation.getListeConsultation().size(); i ++) {
			if (!Consultation.getListeConsultation().get(i).getAppareil().equals("null")||!Consultation.getListeConsultation().get(i).getAppareil().equals("")) {
				listTech.add(Consultation.getListeConsultation().get(i));

			}}
		for(int i = 0;i<listTech.size();i++) {
			String nom = listTech.get(i).getPatient().getNom() + " " + listTech.get(i).getPatient().getPrenom();
			String nomMedecin = listTech.get(i).getNomMedecin();
			String date = listTech.get(i).getDate();
			String pathologies = listTech.get(i).getPathologies();
			String appareil = listTech.get(i).getAppareil();
			String statut;
			if (listTech.get(i).getOctroi()==true) {
				statut = "octroyer";
			}else {
				statut="en attente";
			}


			String[] data = {nom, nomMedecin, date, pathologies, appareil,statut};


			defaultTableModel.addRow(data);
		}

		defaultTableModel.fireTableDataChanged();
	}


}




