package com.mycompany.racunarskaoprema.dao;

import com.mycompany.racunarskaoprema.data.*;
import java.sql.*;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User find(int id) throws SQLException {
        String query = "SELECT * FROM user WHERE id_user = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setIdUser(rs.getInt("id_user"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setBirthDate(rs.getDate("birth_date"));
            user.setAccountBalance(rs.getBigDecimal("account_balance"));
            return user;
        }
        return null;
    }
    
    public void insert(User user) throws SQLException {
        String query = "INSERT INTO user (name, email, username, birth_date, account_balance) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getUsername());
        stmt.setDate(4, user.getBirthDate());
        stmt.setBigDecimal(5, user.getAccountBalance());
        stmt.executeUpdate();
    }

    public void update(User user) throws SQLException {
        String query = "UPDATE user SET name = ?, email = ?, username = ?, birth_date = ?, account_balance = ? WHERE id_user = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getUsername());
        stmt.setDate(4, user.getBirthDate());
        stmt.setBigDecimal(5, user.getAccountBalance());
        stmt.setInt(6, user.getIdUser());
        stmt.executeUpdate();
    }
    
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM user WHERE id_user = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}