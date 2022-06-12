package com.cg.service;

import com.cg.model.Role;
import com.cg.model.User;
import com.cg.utils.MySQLConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM shop_manager.users AS u WHERE u.id = ?";
    private static final String SELECT_USER_BY_USERNAME_SQL = "SELECT * FROM shop_manager.users AS u WHERE u.username = ?";

    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM shop_manager.users";


    @Override
    public User selectUserById(int id) {
        User user = null;
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_ID_SQL);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                Role role = Role.parseRole(rs.getInt("role"));

                user = new User(id, username, password, role);
            }
        } catch (SQLException e) {
            MySQLConnectionUtils.printSQLException(e);
        }
        return user;
    }

    @Override
    public User selectUserByUsername(String username) {
        User user = null;
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_USERNAME_SQL);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                Role role = Role.parseRole(rs.getInt("role"));

                user = new User(id, username, password, role);
            }
        } catch (SQLException e) {
            MySQLConnectionUtils.printSQLException(e);
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            Connection conn = MySQLConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_USERS_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Role role = Role.parseRole(rs.getInt("role"));

                userList.add(new User(id, username, password, role));
            }
        } catch (SQLException e) {
            MySQLConnectionUtils.printSQLException(e);
        }
        return userList;
    }

    @Override
    public boolean exist(String userName) {
        return selectUserByUsername(userName) != null;
    }

    @Override
    public Role getRole(String username) {
        return selectUserByUsername(username).getRole();
    }

    @Override
    public boolean checkPassword(String username, String password) {
        return selectUserByUsername(username).getPassword().equals(password);
    }

    @Override
    public void signUp(User newUser) {

    }

    @Override
    public void setRole(User user, Role role) {

    }
}
