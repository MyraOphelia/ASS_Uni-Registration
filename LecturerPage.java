import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LecturerPage extends JFrame {
    private ArrayList<String> assignedCourses;

    public LecturerPage(String username) {
        this.assignedCourses = new ArrayList<>();

        setTitle("Lecturer Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 300);
        getContentPane().setBackground(new Color(240, 240, 240));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Welcome, " + username + "!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, gbc);

        JButton enrollButton = new JButton("View Assgined Courses");
        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAssignedCourses();
            }
        });
        gbc.gridy++;
        add(enrollButton, gbc);

        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logOut();
            }
        });
        gbc.gridy++;
        add(logoutButton, gbc);

        setLocationRelativeTo(null);
    }

    private void viewAssignedCourses() {
        if (assignedCourses.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You are not enrolled in any courses.");
        } else {
            StringBuilder message = new StringBuilder("Enrolled Courses:\n");
            for (String course : assignedCourses) {
                message.append(course).append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString());
        }
    }

    private void logOut() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Close the StudentPage frame
            dispose();

            // Redirect to the LoginApp frame
            new LoginApp().setVisible(true);
        }
    }

}
