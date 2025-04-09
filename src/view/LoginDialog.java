package view ;

import javax.swing.*;
import java.awt.*;
import service.AuthService;

public class LoginDialog extends JDialog {
    private boolean authenticated = false;
    private final AuthService authService;

    public LoginDialog(Frame parent, AuthService authService) {
        super(parent, "Admin Login", true);
        this.authService = authService;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(3, 2, 10, 10));
        setSize(300, 150);

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authService.authenticate(username, password)) {
                authenticated = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials",
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(new JLabel());
        add(loginButton);
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}