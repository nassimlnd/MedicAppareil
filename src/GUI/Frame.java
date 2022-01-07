package GUI;

import org.Connexion;
import org.Consultation;
import org.Patient;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;

public class Frame extends JFrame {
	// Attributs

	private JPanel contentPane;
	private LoginPanel loginPanel;
    private AdminPanel adminPanel;
	private MedecinPanel medecinPanel;
	private TechnicienPanel technicienPanel;
	private StatsPanel statsPanel;

	// Constructeur

	public Frame() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Frame.class.getResource("/connexionButton.png")));
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("MedicAppareil");
		setBounds(100, 100, 1000, 500);
		setResizable(false);
		loginPanel = new LoginPanel();
		adminPanel = new AdminPanel();
		medecinPanel = new MedecinPanel();
		technicienPanel = new TechnicienPanel();
		statsPanel = new StatsPanel();
		setContentPane(loginPanel);
		contentPane = loginPanel;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		loginPanel.setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);

		loginPanel.btnConnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String type = "";

				if (loginPanel.radioButtonAdmin.isSelected()) {
					type = "admin";
				}
				else if (loginPanel.radioButtonMedecin.isSelected()) {
					type = "medecin";
				}
				else if (loginPanel.radioButtonTechnicien.isSelected()) {
					type = "technicien";
				}

				String identifiant = loginPanel.textFieldIdentifiant.getText();
				String motdepasse = String.valueOf(loginPanel.textFieldMotDePasse.getPassword());

				try {
					if (Connexion.connect(type, identifiant, motdepasse) && type.equals("admin")) {
						login(loginPanel);
						initAllList();
						setContentPane(adminPanel);
					}
					else if (Connexion.connect(type, identifiant, motdepasse) && type.equals("medecin")) {
						login(loginPanel);
						initAllList();
						medecinPanel.initTab(medecinPanel.defaultTableModel, medecinPanel.table);
						medecinPanel.initCombo(medecinPanel.comboBox);
						medecinPanel.initPathologies();
						setContentPane(medecinPanel);
					}
					else if (Connexion.connect(type, identifiant, motdepasse) && type.equals("technicien")) {
						login(loginPanel);
						initAllList();
						technicienPanel.init(technicienPanel.defaultTableModel, technicienPanel.table);
						setContentPane(technicienPanel);
					}
					else {
						loginPanel.labelErreur.setForeground(Color.RED);
					}
				} catch (AccountNotFoundException ex) {
					loginPanel.labelErreur.setForeground(Color.RED);
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}
			}
		});

		loginPanel.buttonStatistiques.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				statsPanel.initVar();
				statsPanel.refreshLabel();

				loginPanel.setVisible(false);
				setContentPane(statsPanel);
			}
		});

		adminPanel.btnDeconnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent deconnexion) {
				loginPanel.setVisible(true);
				setContentPane(loginPanel);
			}
		});

		medecinPanel.buttonDeconnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(true);
				setContentPane(loginPanel);
			}
		});

		technicienPanel.buttonDeconnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(true);
				setContentPane(loginPanel);
			}
		});

		statsPanel.buttonRetour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(true);
				setContentPane(loginPanel);
			}
		});
	}

	// Méthodes

	public static void login(LoginPanel loginPanel) {
		// Se connecte et clear les fields
		loginPanel.connected = true;
		loginPanel.labelErreur.setForeground(Color.WHITE);
		loginPanel.textFieldIdentifiant.setText("");
		loginPanel.textFieldMotDePasse.setText("");
		loginPanel.buttonGroup.clearSelection();
		loginPanel.setVisible(false);
	}

	public static void initAllList() {
		// Initialise toutes les listes afin d'actualiser.
		Patient.initList();
		Consultation.initList();
		TechnicienPanel.initList();
		Consultation.initPatListe();
	}
}
