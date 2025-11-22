package com.blockchain.supplychain.service;

import com.blockchain.supplychain.dao.TransactionDAO;
import com.blockchain.supplychain.model.TransactionRecord;

import java.util.List;

public class SupplyChainService {

    private final TransactionDAO transactionDAO = new TransactionDAO();

    public List<TransactionRecord> getTraceabilityForProduct(int productId) {
        return transactionDAO.findByProductId(productId);
    }
}
