package com.blockchain.supplychain.dao;

import com.blockchain.supplychain.model.TransactionRecord;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public int insert(TransactionRecord tx, int blockId) {
        String sql = "INSERT INTO transactions(product_id, from_party, to_party, quantity, timestamp, status, block_id) " +
                "VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, tx.getProductId());
            ps.setString(2, tx.getFromParty());
            ps.setString(3, tx.getToParty());
            ps.setInt(4, tx.getQuantity());
            ps.setTimestamp(5, Timestamp.valueOf(tx.getTimestamp()));
            ps.setString(6, tx.getStatus());
            ps.setInt(7, blockId);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<TransactionRecord> findByProductId(int productId) {
        String sql = "SELECT * FROM transactions WHERE product_id = ? ORDER BY timestamp";
        List<TransactionRecord> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TransactionRecord tx = new TransactionRecord(
                            rs.getInt("transaction_id"),
                            rs.getInt("product_id"),
                            rs.getString("from_party"),
                            rs.getString("to_party"),
                            rs.getInt("quantity"),
                            rs.getTimestamp("timestamp").toLocalDateTime(),
                            rs.getString("status")
                    );
                    list.add(tx);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
