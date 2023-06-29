/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deercompany.query;

import com.deercompany.database.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author evak
 */
public class FoundUser {

    private PreparedStatement statement;
    private ResultSet resultSet;
    private final Connector CONNECTION;
    private final String SQL_QUERY = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

    public FoundUser() {
        this.CONNECTION = new Connector();
        this.statement = null;
    }
    
    public boolean searchUser(String username, String password) {
        boolean found = false;
        try {
            statement = CONNECTION.getConnection().prepareStatement(SQL_QUERY);
            
            statement.setString(1, username);
            statement.setString(2, password);
            
            resultSet = statement.executeQuery();
            
            found = resultSet.next();
            
            CONNECTION.disconnect();
            
            return found;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta");
            return found;
        }
    }
    
    public boolean searchActiveUser(String username, String password) {
        boolean found = false;
        try {
            statement = CONNECTION.getConnection().prepareStatement(SQL_QUERY);
            
            statement.setString(1, username);
            statement.setString(2, password);
            
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                found = resultSet.getBoolean("active");
            }
            return found;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta");
            return found;
        }
    }
    
}
