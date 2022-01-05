package GUI;

import Exceptions.ConsultationNotFoundException;
import org.Consultation;
import org.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TechnicienPanel extends JPanel {
	// Attributs

	private JLabel background;
	static JButton buttonDeconnexion;
	public static JTable table;
	private static JLabel labelError;
	public static DefaultTableModel defaultTableModel;

	public static ArrayList<Consultation> listTech = new ArrayList<Consultation>();

	// Constructeur

	public TechnicienPanel() {
		setLayout(null);
		Patient.initList();
		Consultation.initList();
		initList();

		String[] columns = {"Nom du patient", "Nom du m?decin", "Date", "Pathologies diagnostiqu?es", "Appareil","statut" };
		defaultTableModel = new DefaultTableModel(columns, 0);
		table = new JTable(defaultTableModel);
		init(defaultTableModel, table);

		JScrollPane scrollPanetTable = new JScrollPane(table);
		scrollPanetTable.setBounds(298, 34, 655, 420);
		add(scrollPanetTable);

		labelError = new JLabel();
		labelError.setHorizontalAlignment(SwingConstants.CENTER);
		labelError.setForeground(Color.WHITE);
		labelError.setFont(new Font("Montserrat", Font.PLAIN, 11));
		labelError.setBounds(459, 162, 332, 14);
		add(labelError);

		buttonDeconnexion = new JButton("");
		buttonDeconnexion.setOpaque(false);
		buttonDeconnexion.setBackground((Color) null);
		buttonDeconnexion.setIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonDeconnexion.png")));
		buttonDeconnexion.setSelectedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonPressedDeconnexion.png")));
		buttonDeconnexion.setPressedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonPressedDeconnexion.png")));
		buttonDeconnexion.setBounds(63, 299, 125, 33);
		add(buttonDeconnexion);

		JButton buttonRechercher = new JButton("");
		buttonRechercher.setOpaque(false);
		buttonRechercher.setBackground((Color) null);
		buttonRechercher.setIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonRechercher.png")));
		buttonRechercher.setPressedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonRechercherPressed.png")));
		buttonRechercher.setSelectedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonRechercherPressed.png")));
		buttonRechercher.setBounds(69, 233, 113, 33);
		add(buttonRechercher);

		JButton buttonModifier = new JButton("");
		buttonModifier.setBackground(null);
		buttonModifier.setOpaque(false);
		buttonModifier.setIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonModifier.png")));
		buttonModifier.setPressedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonModifierPressed.png")));
		buttonModifier.setSelectedIcon(new ImageIcon(MedecinPanel.class.getResource("/buttonModifierPressed.png")));
		buttonModifier.setBounds(80, 167, 91, 33);
		add(buttonModifier);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(219, 232, 46, 14);
		add(lblNewLabel);

		background = new JLabel("");
		background.setLocation(0, 0);
		background.setSize(1000, 500);
		background.setIcon(new ImageIcon(TechnicienPanel.class.getResource("/backgroundTechnicienPanel.png")));
		background.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (table.getSelectedRow() != -1) {
					table.clearSelection();
				}
			}
		});
		add(background);
		
		buttonModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				FileReader fileReader;
				Scanner sc;
				FileWriter fileWriter;
				String oldLine = "";
				String newLine;
				String oldContent = "";
				String newContent;
				int ligne = table.getSelectedRow();

				if(listTech.get(ligne).getOctroi() == false) {
					listTech.get(ligne).setOctroi(true);
				}
				else {
					listTech.get(ligne).setOctroi(false);
				}

				try {
					File file = new File("consultation.txt");

					fileReader = new FileReader(file.getAbsoluteFile());
					sc = new Scanner(fileReader);

					newLine = listTech.get(ligne).getId() + ";" + (listTech.get(ligne).getPatient().getId()-1) + ";" + listTech.get(ligne).getNomMedecin() + ";" + listTech.get(ligne).getDate() + ";" + listTech.get(ligne).getPathologies()+";"+listTech.get(ligne).getAppareil()+";"+listTech.get(ligne).getOctroi();

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

					initList();
					init(defaultTableModel, table);

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
				PopupRechercheTechnicien popupRechercheTechnicien = new PopupRechercheTechnicien();
			}
		});

	}

	// Getter and setters

	public static ArrayList<Consultation> getListTech() {
		return listTech;
	}

	public static void setListTech(ArrayList<Consultation> listTech) {
		TechnicienPanel.listTech = listTech;
	}


	// Méthodes

	public void init(DefaultTableModel defaultTableModel, JTable table) {
		// Init le tableau
		defaultTableModel.setRowCount(0);
		table.revalidate();

		for (int i = 0;i<listTech.size();i++) {
			String nom = listTech.get(i).getPatient().getNom() + " " + listTech.get(i).getPatient().getPrenom();
			String nomMedecin = listTech.get(i).getNomMedecin();
			String date = listTech.get(i).getDate();
			String pathologies = listTech.get(i).getPathologies();
			String appareil = listTech.get(i).getAppareil();
			String statut;

			if (listTech.get(i).getOctroi()==true) {
				statut = "Octroyé";
			}
			else {
				statut = "En attente";
			}

			String[] data = {nom, nomMedecin, date, pathologies, appareil,statut};
			defaultTableModel.addRow(data);
		}

		defaultTableModel.fireTableDataChanged();
	}

	public static void initList() {
		// Init la liste des consultations avec uniquement des appareils
		listTech.clear();
		for (Consultation consultation : Consultation.getListeConsultation()) {
			if (consultation.getAppareil().equals("null") || consultation.getAppareil().equals("")) {
				continue;
			}
			else {
				listTech.add(consultation);
			}
		}
	}

	public static int recherche(String value) throws FileNotFoundException, ConsultationNotFoundException {
		// Fonction de recherche
		FileReader fileReader;
		Scanner sc;
		int id;

		fileReader = new FileReader("consultation.txt");
		sc = new Scanner(fileReader);

		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] split = line.split(";");
			String nom = Patient.getListePatient().get(Integer.parseInt(split[1])).getNom() + Patient.getListePatient().get(Integer.parseInt(split[1])).getPrenom();
			if (split[5].equals("null") || split[5].equals("")) {
				continue;
			}
			else {
				if (line.contains(value)) {
					id = Integer.parseInt(split[0]);
					return id;
				}
				else if (nom.toLowerCase().contains(value)) {
					id  = Integer.parseInt(split[0]);
					return id;
				}
			}
		}
		throw new ConsultationNotFoundException("Consulatation introuvable.");
	}
}




