package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupSupprimer extends JDialog {

	JLabel labelPatient;
	JButton buttonOui;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PopupSupprimer dialog = new PopupSupprimer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PopupSupprimer() {
		setResizable(false);
		setBounds(100, 100, 405, 230);
		getContentPane().setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JButton buttonAnnuler = new JButton("");
		buttonAnnuler.setPressedIcon(new ImageIcon(PopupSupprimer.class.getResource("/img/buttonAnnulerPressed.png")));
		buttonAnnuler.setSelectedIcon(new ImageIcon(PopupSupprimer.class.getResource("/img/buttonAnnulerPressed.png")));
		buttonAnnuler.setIcon(new ImageIcon(PopupSupprimer.class.getResource("/img/buttonAnnuler.png")));
		buttonAnnuler.setBounds(228, 135, 88, 33);
		getContentPane().add(buttonAnnuler);
		
		buttonOui = new JButton("");
		buttonOui.setSelectedIcon(new ImageIcon(PopupSupprimer.class.getResource("/img/buttonOuiPressed.png")));
		buttonOui.setPressedIcon(new ImageIcon(PopupSupprimer.class.getResource("/img/buttonOuiPressed.png")));
		buttonOui.setIcon(new ImageIcon(PopupSupprimer.class.getResource("/img/buttonOui.png")));
		buttonOui.setBounds(89, 135, 57, 33);
		getContentPane().add(buttonOui);
		
		labelPatient = new JLabel("");
		labelPatient.setHorizontalAlignment(SwingConstants.CENTER);
		labelPatient.setFont(new Font("Montserrat", Font.PLAIN, 13));
		labelPatient.setBounds(89, 78, 227, 18);
		getContentPane().add(labelPatient);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(PopupSupprimer.class.getResource("/img/popupSupprimerBackground.png")));
		background.setBounds(0, 0, 400, 200);
		getContentPane().add(background);

		buttonAnnuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
