package com.blockchain.supplychain.dao;

import com.blockchain.supplychain.model.*;

import java.sql.*;

public class UserDAO {

    public User findByEmailAndPassword(String email, String passwordHash) {
        String sql = "SELECT * FROM users WHERE email = ? AND password_hash = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, passwordHash);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("user_id");
                    String name = rs.getString("name");
                    String roleStr = rs.getString("role");
                    Role role = Role.valueOf(roleStr);

                    switch (role) {
                        case SUPPLY_CHAIN_MANAGER:
                            return new SupplyChainManager(id, name, email, passwordHash);
                        case SUPPLIER:
                            return new Supplier(id, name, email, passwordHash);
                        case RETAILER:
                            return new Retailer(id, name, email, passwordHash);
                        default:
                            return null;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
