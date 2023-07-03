/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deercompany.query;

import com.deercompany.database.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author evak
 */
public class ListUsers {

    private PreparedStatement statement;
    private ResultSet resultSet;
    private final Connector CONNECTOR;
    private final String SQL_QUERY = "SELECT * FROM usuarios";
    private DefaultTableModel DT;
    
    public ListUsers() {
        statement = null;
        CONNECTOR = new Connector();
    }
    
    private DefaultTableModel setTitulosInventario(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("ID");
        DT.addColumn("Username");
        DT.addColumn("Password");
        DT.addColumn("Active");
        return DT;
    }
    
    public DefaultTableModel getDatosInventario(){
        try {
            setTitulosInventario();
            statement = CONNECTOR.getConnection().prepareStatement(SQL_QUERY);
            resultSet = statement.executeQuery();
            Object[] fila = new Object[4];
            while(resultSet.next()){
                fila[0] = resultSet.getInt(1);
                fila[1] = resultSet.getString(2);
                fila[2] = resultSet.getString(3);
                fila[3] = resultSet.getBoolean(4);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos."+e.getMessage());
        } finally{
            statement = null;
            resultSet = null;
            CONNECTOR.disconnect();
        }
        return DT;
    }
    
}
