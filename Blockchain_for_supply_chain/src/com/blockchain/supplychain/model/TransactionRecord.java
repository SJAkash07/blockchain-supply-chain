package com.blockchain.supplychain.model;

import java.time.LocalDateTime;

public class TransactionRecord {
    private int transactionId;
    private int productId;
    private String fromParty;
    private String toParty;
    private int quantity;
    private LocalDateTime timestamp;
    private String status;

    public TransactionRecord(int transactionId, int productId, String fromParty, String toParty,
                             int quantity, LocalDateTime timestamp, String status) {
        this.transactionId = transactionId;
        this.productId = productId;
        this.fromParty = fromParty;
        this.toParty = toParty;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.status = status;
    }

    public int getTransactionId() { return transactionId; }
    public int getProductId() { return productId; }
    public String getFromParty() { return fromParty; }
    public String getToParty() { return toParty; }
    public int getQuantity() { return quantity; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return transactionId + "|" + productId + "|" + fromParty + "|" + toParty + "|" +
                quantity + "|" + timestamp + "|" + status;
    }
}
