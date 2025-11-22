package com.blockchain.supplychain.model;

import com.blockchain.supplychain.util.HashUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Block {
    private int index;
    private LocalDateTime timestamp;
    private List<TransactionRecord> transactions;
    private String previousHash;
    private String hash;
    private int nonce;

    public Block(int index, String previousHash) {
        this.index = index;
        this.previousHash = previousHash;
        this.timestamp = LocalDateTime.now();
        this.transactions = new ArrayList<>();
        this.hash = calculateHash();
    }

    public void addTransaction(TransactionRecord tx) {
        transactions.add(tx);
        this.hash = calculateHash();
    }

    public String calculateHash() {
        StringBuilder data = new StringBuilder();
        data.append(index)
                .append(timestamp.toString())
                .append(previousHash)
                .append(nonce);
        for (TransactionRecord tx : transactions) {
            data.append(tx.toString());
        }
        return HashUtil.applySha256(data.toString());
    }

    public void mineBlock(int difficulty) {
        String target = "0".repeat(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
    }

    public int getIndex() { return index; }
    public String getPreviousHash() { return previousHash; }
    public String getHash() { return hash; }
    public List<TransactionRecord> getTransactions() { return transactions; }
}
