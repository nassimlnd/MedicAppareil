package GUI;

import org.Connexion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class Frame extends JFrame {

	private JPanel contentPane;
	private LoginPanel loginPanel;
    private AdminPanel adminPanel;
	private MedecinPanel medecinPanel;
	private TechnicienPanel technicienPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Frame() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Frame.class.getResource("/img/connexionButton.png")));
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("MedicAppareil");
		setBounds(100, 100, 1000, 500);
		setResizable(false);
		loginPanel = new LoginPanel();
		adminPanel = new AdminPanel();
		medecinPanel = new MedecinPanel();
		technicienPanel = new TechnicienPanel();
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

				if (Connexion.connect(type, identifiant, motdepasse) && type.equals("admin")) {
					System.out.println("Connexion réussie");
					loginPanel.connected = true;
					loginPanel.labelErreur.setForeground(Color.WHITE);
					loginPanel.textFieldIdentifiant.setText("");
					loginPanel.textFieldMotDePasse.setText("");
					loginPanel.buttonGroup.clearSelection();
					loginPanel.setVisible(false);
					setContentPane(adminPanel);
				}
				else if (Connexion.connect(type, identifiant, motdepasse) && type.equals("medecin")) {
					System.out.println("Connexion réussie");
					loginPanel.connected = true;
					loginPanel.labelErreur.setForeground(Color.WHITE);
					loginPanel.textFieldIdentifiant.setText("");
					loginPanel.textFieldMotDePasse.setText("");
					loginPanel.buttonGroup.clearSelection();
					loginPanel.setVisible(false);
					setContentPane(medecinPanel);
				}
				else if (Connexion.connect(type, identifiant, motdepasse) && type.equals("technicien")) {
					System.out.println("Connexion réussie");
					loginPanel.connected = true;
					loginPanel.labelErreur.setForeground(Color.WHITE);
					loginPanel.textFieldIdentifiant.setText("");
					loginPanel.textFieldMotDePasse.setText("");
					loginPanel.buttonGroup.clearSelection();
					loginPanel.setVisible(false);
					setContentPane(technicienPanel);
				}
				else {
					loginPanel.labelErreur.setForeground(Color.RED);
					System.out.println("Connexion échoué");
				}
			}
		});

		adminPanel.btnDeconnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent deconnexion) {
				loginPanel.setVisible(true);
				setContentPane(loginPanel);
			}
		});


	}
}
