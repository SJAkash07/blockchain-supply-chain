package com.blockchain.supplychain.dao;

import com.blockchain.supplychain.model.Block;

import java.sql.*;

public class BlockDAO {

    public int insert(Block block) {
        String sql = "INSERT INTO blocks(index_no, timestamp, previous_hash, hash, nonce) VALUES(?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, block.getIndex());
            ps.setTimestamp(2, Timestamp.valueOf(java.time.LocalDateTime.now()));
            ps.setString(3, block.getPreviousHash());
            ps.setString(4, block.getHash());
            ps.setInt(5, 0); // nonce already inside block if you like

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
}
