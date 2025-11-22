package com.blockchain.supplychain.model;

public class Supplier extends User {

    public Supplier(int id, String name, String email, String passwordHash) {
        super(id, name, email, passwordHash, Role.SUPPLIER);
    }

    @Override
    public String getDashboardTitle() {
        return "Supplier Dashboard";
    }
}
