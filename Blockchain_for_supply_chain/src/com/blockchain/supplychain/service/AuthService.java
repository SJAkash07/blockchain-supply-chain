package com.blockchain.supplychain.service;

import com.blockchain.supplychain.dao.UserDAO;
import com.blockchain.supplychain.model.User;
import com.blockchain.supplychain.util.HashUtil;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();

    public User login(String email, String plainPassword) {
        String passwordHash = HashUtil.applySha256(plainPassword);
        return userDAO.findByEmailAndPassword(email, passwordHash);
    }
}
