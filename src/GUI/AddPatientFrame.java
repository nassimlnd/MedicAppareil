package GUI;

import org.Patient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class AddPatientFrame extends JFrame {

	public static JPanel contentPaneAddPatient;
	private JTextField textFieldNom;
	private JLabel labelDateDeNaissance;
	private JLabel labelNbSecuriteSociale;
	private JTextField textFieldNbSecuriteSociale;
	private JButton btnRetourAuMenu;
	private JList<Patient> list = new JList<>();
	private DefaultListModel<Patient> model = new DefaultListModel<>();

	public AddPatientFrame() {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1000, 500);
			contentPaneAddPatient = new JPanel();
            contentPaneAddPatient.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPaneAddPatient);
            contentPaneAddPatient.setLayout(null);

            JLabel labelNom = new JLabel("Nom du patient");
            labelNom.setBounds(120, 35, 220, 14);
            contentPaneAddPatient.add(labelNom);

            textFieldNom = new JTextField();
            textFieldNom.setBounds(120, 59, 250, 30);
            contentPaneAddPatient.add(textFieldNom);
            textFieldNom.setColumns(10);

            labelDateDeNaissance = new JLabel("Date de naissance");
            labelDateDeNaissance.setBounds(120, 100, 250, 14);
            contentPaneAddPatient.add(labelDateDeNaissance);

            MaskFormatter formatter = new MaskFormatter("##/##/####");
            formatter.setPlaceholder("#");
            JFormattedTextField formattedTextFieldDate = new JFormattedTextField(formatter);
            formattedTextFieldDate.setBounds(120, 125, 250, 30);
            contentPaneAddPatient.add(formattedTextFieldDate);

            labelNbSecuriteSociale = new JLabel("Num\u00E9ro de s\u00E9curit\u00E9 sociale");
            labelNbSecuriteSociale.setBounds(120, 166, 250, 14);
            contentPaneAddPatient.add(labelNbSecuriteSociale);

            textFieldNbSecuriteSociale = new JTextField();
            textFieldNbSecuriteSociale.setBounds(120, 191, 250, 30);
            contentPaneAddPatient.add(textFieldNbSecuriteSociale);
            textFieldNbSecuriteSociale.setColumns(10);

            Patient.initList();
            list.setModel(model);
            for (Patient patient: Patient.listePatient) {
                model.addElement(patient);
            }

            list.setBounds(550, 35, 350, 320);
            contentPaneAddPatient.add(list);

            JButton btnAddPatient2 = new JButton("Ajouter le patient");
            btnAddPatient2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e1) {
                    String nom = textFieldNom.getText();
                    String dateNaissance = formattedTextFieldDate.getText();
                    String nbSecuriteSociale = textFieldNbSecuriteSociale.getText();
                    textFieldNom.setText("");
                    formattedTextFieldDate.setText("");
                    textFieldNbSecuriteSociale.setText("");
                    new Patient(nom, dateNaissance, nbSecuriteSociale);
                    model.removeAllElements();
                    for (Patient patient: Patient.listePatient) {
                        model.addElement(patient);
                    }

                }
            });
            btnAddPatient2.setBounds(170, 275, 126, 43);
            contentPaneAddPatient.add(btnAddPatient2);
            
            JButton btnRetourMenu = new JButton("Retourner au Menu");
            btnRetourMenu.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		contentPaneAddPatient.setVisible(false);
                    //contentPaneMenu.setVisible(true);
                    //setContentPane(contentPaneMenu);
            	}
            });
            btnRetourMenu.setBounds(50, 393, 220, 30);
            contentPaneAddPatient.add(btnRetourMenu);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		


	}
}
