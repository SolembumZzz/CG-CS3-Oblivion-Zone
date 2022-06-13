package com.cg.service;

import com.cg.model.Role;
import com.cg.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {

    User selectUserById(int id);

    User selectUserByUsername(String username);

    List<User> selectAllUsers();

    Role getRole(String username);

    boolean exist(String userName);

    boolean checkPassword(String username, String password);

    void signUp(User newUser);

    void setRole(User user, Role role);

    boolean isAuthorized(HttpServletRequest request);

    boolean isBlocked(String username);

    void blockUser(int id);
}
