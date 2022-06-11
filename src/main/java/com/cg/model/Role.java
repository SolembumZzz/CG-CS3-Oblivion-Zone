package com.cg.model;

public enum Role {
    ADMIN(1),
    CUSTOMER(2);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static Role parseRole(int value) {
        Role[] values = values();
        for (Role role : values) {
            if (role.value == (value))
                return role;
        }
        throw new IllegalArgumentException("Role Invalid");
    }
}
