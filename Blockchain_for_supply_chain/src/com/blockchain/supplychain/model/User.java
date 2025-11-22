package com.blockchain.supplychain.model;

public abstract class User {
    protected int id;
    protected String name;
    protected String email;
    protected String passwordHash;
    protected Role role;

    public User(int id, String name, String email, String passwordHash, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public int getId() { return id; }
    public Role getRole() { return role; }
    public String getName() { return name; }

    // Polymorphic method – each role can show different dashboard
    public abstract String getDashboardTitle();
}
