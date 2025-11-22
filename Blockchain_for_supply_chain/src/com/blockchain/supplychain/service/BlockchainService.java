package com.blockchain.supplychain.service;

import com.blockchain.supplychain.dao.BlockDAO;
import com.blockchain.supplychain.dao.TransactionDAO;
import com.blockchain.supplychain.exception.BlockchainValidationException;
import com.blockchain.supplychain.exception.InvalidTransactionException;
import com.blockchain.supplychain.model.*;

import java.time.LocalDateTime;

public class BlockchainService {

    private final Blockchain blockchain = new Blockchain();
    private final BlockDAO blockDAO = new BlockDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public BlockchainService() {
        // Background thread for validation
        Thread validator = new Thread(this::periodicValidation);
        validator.setDaemon(true);
        validator.start();
    }

    public synchronized void addTransaction(int productId, String from, String to, int qty, String status)
            throws InvalidTransactionException {

        if (qty <= 0) throw new InvalidTransactionException("Quantity must be positive");

        TransactionRecord tx = new TransactionRecord(0, productId, from, to, qty, LocalDateTime.now(), status);

        Block latest = blockchain.getLatestBlock();
        Block newBlock = new Block(latest.getIndex() + 1, latest.getHash());
        newBlock.addTransaction(tx);

        blockchain.addBlock(newBlock);

        // persist to DB
        int blockId = blockDAO.insert(newBlock);
        transactionDAO.insert(tx, blockId);
    }

    private void periodicValidation() {
        while (true) {
            try {
                Thread.sleep(10000); // every 10 seconds
                try {
                    blockchain.isChainValid();
                    System.out.println("[Validator] Blockchain is valid.");
                } catch (BlockchainValidationException e) {
                    System.err.println("[Validator] ERROR: " + e.getMessage());
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }
}
