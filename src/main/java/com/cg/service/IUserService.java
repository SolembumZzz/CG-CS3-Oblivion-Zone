package com.cg.service;

import com.cg.model.User;

import java.util.ArrayList;

public interface IUserService {

    User selectUser(int id);

    ArrayList<User> selectAllUsers();

    public void signIn();

    public void getRole(String username);

    boolean exist(String userName);

    boolean checkPassword(String username, String password);
}
