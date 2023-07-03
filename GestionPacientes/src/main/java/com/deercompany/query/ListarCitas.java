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
public class ListarCitas {

    private PreparedStatement statement;
    private ResultSet resultSet;
    private final Connector CONNECTOR;
    private final String SQL_QUERY = "SELECT cit_id AS ID, obrasocial.obr_razonsocial AS 'OBRA SOCIAL', cit_apellido AS APELLIDO, cit_nombre AS NOMBRE, cit_fecha AS TURNO FROM cita JOIN obrasocial ON cita.obr_id = obrasocial.obr_id WHERE cit_fecha = DATE(NOW());";
    private DefaultTableModel DT;
    
    public ListarCitas() {
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
        DT.addColumn("OBRA SOCIAL");
        DT.addColumn("APELLIDO");
        DT.addColumn("NOMBRE");
        DT.addColumn("TURNO");
        return DT;
    }
    
    public DefaultTableModel getDatosInventario(){
        try {
            setTitulosInventario();
            statement = CONNECTOR.getConnection().prepareStatement(SQL_QUERY);
            resultSet = statement.executeQuery();
            Object[] fila = new Object[5];
            while(resultSet.next()){
                fila[0] = resultSet.getInt(1);
                fila[1] = resultSet.getString(2);
                fila[2] = resultSet.getString(3);
                fila[3] = resultSet.getString(4);
                fila[4] = resultSet.getDate(5);
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
