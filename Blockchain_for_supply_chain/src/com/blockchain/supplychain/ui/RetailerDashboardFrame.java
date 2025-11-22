package com.blockchain.supplychain.ui;

import com.blockchain.supplychain.model.TransactionRecord;
import com.blockchain.supplychain.model.User;
import com.blockchain.supplychain.service.SupplyChainService;
import com.blockchain.supplychain.service.BlockchainService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RetailerDashboardFrame extends JFrame {

    private final SupplyChainService supplyChainService = new SupplyChainService();

    public RetailerDashboardFrame(User user, BlockchainService blockchainService) {
        setTitle(user.getDashboardTitle());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JTextField productIdField = new JTextField(10);
        JButton traceBtn = new JButton("Trace Product");
        topPanel.add(new JLabel("Product ID:"));
        topPanel.add(productIdField);
        topPanel.add(traceBtn);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        traceBtn.addActionListener(e -> {
            try {
                int pid = Integer.parseInt(productIdField.getText().trim());
                List<TransactionRecord> list = supplyChainService.getTraceabilityForProduct(pid);
                StringBuilder sb = new StringBuilder();
                for (TransactionRecord tx : list) {
                    sb.append(tx.toString()).append("\n");
                }
                if (sb.length() == 0) {
                    sb.append("No records found.");
                }
                resultArea.setText(sb.toString());
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid product id");
            }
        });
    }
}
