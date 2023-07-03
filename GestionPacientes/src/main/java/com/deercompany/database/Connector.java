/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deercompany.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author evak
 */
public class Connector {
    
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/gestion";
    private final String USER = "root";
    private final String PASSWORD = "Loreto_1";
    
    public Connector() {
        this.connection = null;
    }
    
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al establecer la conexión con la base de datos");
            if (e.getErrorCode() == 1049) {
                JOptionPane.showMessageDialog(null, "Motivo: La base de datos no se encuentra en los registros");
            }
            return connection;
        }
    }
    
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexion:" + e.getMessage());
        }
    }
    
}
