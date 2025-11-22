package com.blockchain.supplychain.model;

public class Retailer extends User {

    public Retailer(int id, String name, String email, String passwordHash) {
        super(id, name, email, passwordHash, Role.RETAILER);
    }

    @Override
    public String getDashboardTitle() {
        return "Retailer Dashboard";
    }
}
