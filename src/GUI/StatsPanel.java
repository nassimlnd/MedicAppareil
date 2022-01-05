package GUI;

import org.Consultation;
import org.Patient;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {
	// Attributs

	public JButton buttonRetour;
	public static String nbPat, nbCons, nbApp, nbOct, nbConsPat, nbAppCons;
	public static JLabel labelNbApp, labelNbCons, labelNbPat, labelOct, labelConsPat, labelAppCons;

	// Constructeur

	public StatsPanel() {
		setLayout(null);

		initVar();
		
		labelAppCons = new JLabel("");
		labelAppCons.setHorizontalAlignment(SwingConstants.CENTER);
		labelAppCons.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelAppCons.setBounds(815, 241, 50, 19);
		add(labelAppCons);
		
		JLabel labelNbAppCons = new JLabel("Nombres d'appareils par consultation :");
		labelNbAppCons.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelNbAppCons.setBounds(500, 241, 309, 19);
		add(labelNbAppCons);
		
		labelConsPat = new JLabel(nbConsPat);
		labelConsPat.setHorizontalAlignment(SwingConstants.CENTER);
		labelConsPat.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelConsPat.setBounds(818, 130, 46, 19);
		add(labelConsPat);
		
		labelOct = new JLabel(nbOct);
		labelOct.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelOct.setBounds(266, 386, 60, 19);
		add(labelOct);
		
		labelNbApp = new JLabel(nbApp);
		labelNbApp.setHorizontalAlignment(SwingConstants.CENTER);
		labelNbApp.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelNbApp.setBounds(266, 347, 46, 19);
		add(labelNbApp);
		
		labelNbCons = new JLabel(nbCons);
		labelNbCons.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelNbCons.setHorizontalAlignment(SwingConstants.CENTER);
		labelNbCons.setBounds(313, 241, 46, 19);
		add(labelNbCons);
		
		JLabel labelNbConsPat = new JLabel("Nombres de consultations par patient :");
		labelNbConsPat.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelNbConsPat.setBounds(500, 130, 316, 19);
		add(labelNbConsPat);
		
		JLabel labelOctroi = new JLabel("Pourcentage d'octroi :");
		labelOctroi.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelOctroi.setBounds(83, 386, 176, 19);
		add(labelOctroi);
		
		labelNbPat = new JLabel(nbPat);
		labelNbPat.setHorizontalAlignment(SwingConstants.CENTER);
		labelNbPat.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelNbPat.setBounds(266, 130, 46, 19);
		add(labelNbPat);
		
		JLabel labelNbAppareil = new JLabel("Nombres d'appareils :");
		labelNbAppareil.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelNbAppareil.setBounds(86, 347, 173, 19);
		add(labelNbAppareil);
		
		JLabel labelNbConsultations = new JLabel("Nombres de consultation :");
		labelNbConsultations.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelNbConsultations.setBounds(86, 241, 226, 19);
		add(labelNbConsultations);
		
		JLabel labelNbPatient = new JLabel("Nombres de patient :");
		labelNbPatient.setFont(new Font("Montserrat", Font.PLAIN, 16));
		labelNbPatient.setBounds(86, 130, 177, 19);
		add(labelNbPatient);
		
		buttonRetour = new JButton("");
		buttonRetour.setBounds(12, 10, 147, 33);
		buttonRetour.setBackground(null);
		buttonRetour.setOpaque(false);
		buttonRetour.setPressedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonRetourPressed.png")));
		buttonRetour.setSelectedIcon(new ImageIcon(AdminPanel.class.getResource("/buttonRetourPressed.png")));
		buttonRetour.setIcon(new ImageIcon(AdminPanel.class.getResource("/buttonRetour.png")));
		add(buttonRetour);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 1000, 500);
		background.setIcon(new ImageIcon(StatsPanel.class.getResource("/backgroundStatsPanel.png")));
		add(background);

	}

	// Getters and setters


	public static String getNbPat() {
		return nbPat;
	}

	public static void setNbPat(String nbPat) {
		StatsPanel.nbPat = nbPat;
	}

	public static String getNbCons() {
		return nbCons;
	}

	public static void setNbCons(String nbCons) {
		StatsPanel.nbCons = nbCons;
	}

	public static String getNbApp() {
		return nbApp;
	}

	public static void setNbApp(String nbApp) {
		StatsPanel.nbApp = nbApp;
	}

	public static String getNbOct() {
		return nbOct;
	}

	public static void setNbOct(String nbOct) {
		StatsPanel.nbOct = nbOct;
	}

	public static String getNbConsPat() {
		return nbConsPat;
	}

	public static void setNbConsPat(String nbConsPat) {
		StatsPanel.nbConsPat = nbConsPat;
	}

	public static String getNbAppCons() {
		return nbAppCons;
	}

	public static void setNbAppCons(String nbAppCons) {
		StatsPanel.nbAppCons = nbAppCons;
	}

	// Méthodes

	public static void initVar() {
		Patient.initList();
		Consultation.initList();
		TechnicienPanel.initList();
		Consultation.initPatListe();

		setNbPat(String.valueOf(Patient.getListePatient().size()));
		setNbCons(String.valueOf(Consultation.getListeConsultation().size()));
		setNbApp(String.valueOf(TechnicienPanel.getListTech().size()));

		int cb = 0;
		for (Consultation consultation : TechnicienPanel.getListTech()) {
			if (consultation.getOctroi() == true) {
				cb ++;
			}
		}

		if (Integer.parseInt(nbApp) == 0) {
			setNbOct(String.valueOf(0));
		} else {
			double nbPour = (cb * 100 / Integer.parseInt(nbApp));
			setNbOct(String.valueOf(nbPour) + "%");
		}

		if (Integer.parseInt(nbPat) == 0) {
			setNbConsPat(String.valueOf(0));
		} else {
			float nbCoPa = (float) Integer.parseInt(nbCons) / Integer.parseInt(nbPat);
			nbCoPa = (float) Math.round(nbCoPa * 100) / 100;
			setNbConsPat(String.valueOf(nbCoPa));
		}

		if (Integer.parseInt(nbCons) == 0) {
			setNbAppCons(String.valueOf(0));
		} else {
			float nbAppCo = (float) Integer.parseInt(nbApp) / Integer.parseInt(nbCons);
			nbAppCo = (float) Math.round(nbAppCo * 100) / 100;
			setNbAppCons(String.valueOf(nbAppCo));
		}
	}

	public static void refreshLabel() {
		labelNbApp.setText(nbApp);
		labelNbCons.setText(nbCons);
		labelNbPat.setText(nbPat);
		labelOct.setText(nbOct);
		labelConsPat.setText(nbConsPat);
		labelAppCons.setText(nbAppCons);
	}
}
