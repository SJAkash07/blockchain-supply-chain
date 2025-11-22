package com.blockchain.supplychain.model;

public class SupplyChainManager extends User {

    public SupplyChainManager(int id, String name, String email, String passwordHash) {
        super(id, name, email, passwordHash, Role.SUPPLY_CHAIN_MANAGER);
    }

    @Override
    public String getDashboardTitle() {
        return "Supply Chain Manager Dashboard";
    }
}
