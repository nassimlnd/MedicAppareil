import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Test");

        JTextField textField = new JTextField();

        JButton btn = new JButton("Add patient");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Patient patient1 = new Patient(textField.getText(), "02082002");
            }
        });

        btn.setBounds(125, 250, 150, 60);
        textField.setBounds(300, 250, 150, 60);
        frame.add(btn);
        frame.add(textField);

        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
