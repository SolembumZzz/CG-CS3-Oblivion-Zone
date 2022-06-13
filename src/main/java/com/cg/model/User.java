package com.cg.model;

public class User {
    private int id;
    private String username;
    private String password;
    private Role role;
    private boolean isBlocked;

    public User(int id, String username, String password, Role role, boolean isBlocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    public User(int id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        this.isBlocked = blocked;
    }
}
