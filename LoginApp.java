import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginApp extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginApp() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 300);
        getContentPane().setBackground(new Color(240, 240, 240));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JComboBox<String> loginTypeComboBox = new JComboBox<>(new String[]{"Student", "Lecturer", "Admin"});
        add(loginTypeComboBox, gbc);

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

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginType = (String) loginTypeComboBox.getSelectedItem();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                performLogin(loginType, username, password);
            }
        });
        gbc.gridy++;
        add(loginButton, gbc);

        setLocationRelativeTo(null);
    }

    private void performLogin(String loginType, String username, String password) {
        System.out.println("Login Type: " + loginType);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        // Add your actual login logic here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginApp().setVisible(true));
    }
}
