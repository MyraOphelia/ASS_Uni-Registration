import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginApp extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;

    private ArrayList<User> userList;

    public LoginApp() {
        userList = new ArrayList<>();

        // For demonstration purposes, add a default user
        userList.add(new User("admin", "admin123", "admin@example.com", "Admin"));

        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 600);
        getContentPane().setBackground(new Color(240, 240, 240));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, gbc);

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        gbc.gridy++;
        add(usernamePanel, gbc);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        gbc.gridy++;
        add(passwordPanel, gbc);

        userTypeComboBox = new JComboBox<>(new String[]{"Student", "Lecturer", "Admin"});
        JPanel userTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel userTypeLabel = new JLabel("User Type:");
        userTypePanel.add(userTypeLabel);
        userTypePanel.add(userTypeComboBox);
        gbc.gridy++;
        add(userTypePanel, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String) userTypeComboBox.getSelectedItem();
                performLogin(username, password, userType);
            }
        });
        gbc.gridy++;
        add(loginButton, gbc);

       
    }

    private void performLogin(String username, String password, String userType) {
        // Implement your login logic here
        // Check against the user information stored in userList
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password) && user.getUserType().equals(userType)) {
                System.out.println("Login successful for user: " + username);
                return;
            }
        }
        System.out.println("Login failed. Invalid credentials.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginApp().setVisible(true));
    }
}
