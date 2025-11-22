package com.blockchain.supplychain.ui;

import com.blockchain.supplychain.model.*;
import com.blockchain.supplychain.service.AuthService;
import com.blockchain.supplychain.service.BlockchainService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final AuthService authService = new AuthService();
    private final BlockchainService blockchainService = new BlockchainService();

    public LoginFrame() {
        setTitle("Blockchain Supply Chain - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");
        JLabel statusLabel = new JLabel("");

        add(emailLabel);
        add(emailField);
        add(passLabel);
        add(passField);
        add(new JLabel());
        add(loginBtn);
        add(statusLabel);

        loginBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String pass = new String(passField.getPassword());

            User user = authService.login(email, pass);
            if (user == null) {
                statusLabel.setText("Invalid credentials");
                return;
            }

            // open appropriate dashboard using polymorphism
            openDashboard(user);
        });
    }

    private void openDashboard(User user) {
        SwingUtilities.invokeLater(() -> {
            JFrame dashboard;
            if (user.getRole() == Role.SUPPLY_CHAIN_MANAGER) {
                dashboard = new ManagerDashboardFrame(user, blockchainService);
            } else if (user.getRole() == Role.SUPPLIER) {
                dashboard = new SupplierDashboardFrame(user, blockchainService);
            } else {
                dashboard = new RetailerDashboardFrame(user, blockchainService);
            }
            dashboard.setVisible(true);
            this.dispose();
        });
    }
}
