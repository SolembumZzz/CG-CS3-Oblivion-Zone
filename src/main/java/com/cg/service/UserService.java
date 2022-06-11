package com.cg.service;

import com.cg.model.User;

import java.util.ArrayList;

public class UserService implements IUserService {

    private static final String SELECT_USER_BY_ID_SQL = "";

    private static final String SELECT_ALL_USERS_SQL = "";

    private static final String CHECK_IF_EXISTS_SQL = "";


    @Override
    public User selectUser(int id) {
        User user = null;
        return user;
    }

    @Override
    public ArrayList<User> selectAllUsers() {
        return null;
    }

    @Override
    public void signIn() {

    }

    @Override
    public boolean exist(String userName) {
        return false;
    }

    @Override
    public void getRole(String username) {

    }

    @Override
    public boolean checkPassword(String username, String password) {
        return false;
    }
}
