package com.blockchain.supplychain.ui;

import com.blockchain.supplychain.exception.InvalidTransactionException;
import com.blockchain.supplychain.model.User;
import com.blockchain.supplychain.service.BlockchainService;

import javax.swing.*;
import java.awt.*;

public class SupplierDashboardFrame extends JFrame {

    private final BlockchainService blockchainService;

    public SupplierDashboardFrame(User user, BlockchainService blockchainService) {
        this.blockchainService = blockchainService;

        setTitle(user.getDashboardTitle());
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        JTextField productIdField = new JTextField();
        JTextField toPartyField = new JTextField();
        JTextField qtyField = new JTextField();
        JTextField statusField = new JTextField("SHIPPED");
        JLabel infoLabel = new JLabel("");

        add(new JLabel("Product ID:"));
        add(productIdField);
        add(new JLabel("To Party:"));
        add(toPartyField);
        add(new JLabel("Quantity:"));
        add(qtyField);
        add(new JLabel("Status:"));
        add(statusField);

        JButton addBtn = new JButton("Record Transaction");
        add(new JLabel());
        add(addBtn);
        add(infoLabel);

        addBtn.addActionListener(e -> {
            try {
                int pid = Integer.parseInt(productIdField.getText().trim());
                int qty = Integer.parseInt(qtyField.getText().trim());
                String to = toPartyField.getText().trim();
                String status = statusField.getText().trim();

                blockchainService.addTransaction(pid, user.getName(), to, qty, status);
                infoLabel.setText("Transaction added to blockchain!");
            } catch (NumberFormatException ex) {
                infoLabel.setText("Invalid number format");
            } catch (InvalidTransactionException ex) {
                infoLabel.setText(ex.getMessage());
            }
        });
    }
}
