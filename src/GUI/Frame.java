package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Frame extends JFrame {

	private JPanel contentPane;
	private LoginPanel loginPanel;

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
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		setResizable(false);
		loginPanel = new LoginPanel();
		setContentPane(loginPanel);
		contentPane = loginPanel;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		loginPanel.setLayout(null);
		setVisible(true);
	}
}
