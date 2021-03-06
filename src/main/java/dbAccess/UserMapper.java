/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import exceptions.FogException;
import functionLayer.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jesper
 */
public class UserMapper {

    private final String GET_USER = "SELECT * FROM `user` WHERE email = ? AND password = ?;";
    private final String CREATE_USER = "INSERT INTO `user`(password, email, role, customer_id) VALUES (?, ?, ?, ?);";

    public User getUser(String email, String password) throws FogException {
        User user = null;
        try {
            Connection con = Connector.connection();
            String SQL = GET_USER;
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("user_id");
                String password1 = rs.getString("password");
                String email1 = rs.getString("email");
                String role = rs.getString("role");
                int customerID = rs.getInt("customer_id");
                user = new User(id, password1, email1, role, customerID);
                return user;
            } else {
                throw new FogException("Could not validate user");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + " " + UserMapper.class.getName());
        }
        return null;
    }

    public void createUser(User user) throws FogException {
        try {
            Connection con = Connector.connection();
            String query = CREATE_USER;
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getRole());
            pstmt.setInt(4, user.getCustomerID());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + " " + UserMapper.class.getName());
            System.out.println("Error");
        }
    }

}
