package com.blockchain.supplychain.ui;

import com.blockchain.supplychain.model.Block;
import com.blockchain.supplychain.model.Blockchain;
import com.blockchain.supplychain.model.User;
import com.blockchain.supplychain.service.BlockchainService;

import javax.swing.*;
import java.awt.*;

public class ManagerDashboardFrame extends JFrame {

    private final BlockchainService blockchainService;

    public ManagerDashboardFrame(User user, BlockchainService blockchainService) {
        this.blockchainService = blockchainService;

        setTitle(user.getDashboardTitle());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea chainArea = new JTextArea();
        chainArea.setEditable(false);

        JButton refreshBtn = new JButton("Refresh Blockchain View");
        refreshBtn.addActionListener(e -> displayChain(chainArea));

        add(new JScrollPane(chainArea), BorderLayout.CENTER);
        add(refreshBtn, BorderLayout.SOUTH);

        displayChain(chainArea);
    }

    private void displayChain(JTextArea area) {
        StringBuilder sb = new StringBuilder();
        Blockchain bc = blockchainService.getBlockchain();
        for (Block block : bc.getBlocks()) {
            sb.append("Block #").append(block.getIndex())
                    .append(" hash=").append(block.getHash())
                    .append("\nTransactions: ").append(block.getTransactions().size())
                    .append("\n\n");
        }
        area.setText(sb.toString());
    }
}
