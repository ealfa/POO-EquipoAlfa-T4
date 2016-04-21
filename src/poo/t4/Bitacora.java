/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.t4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 * Clase que almacenará todas las transferencias o transacciones de la tienda
 * @author osilru
 * @author LetiBadillo
 */

public class Bitacora {

    public static boolean registrarCompra(String IDTarjeta, String ticket, String cantidad) throws SQLException {
        
        try{
        mysqlConnection c = new mysqlConnection();
        c.conexion();
        Connection co= c.conexion();
            String query = "insert into registrodecompra (numtarjetaR, ticket, cantidad) values (?, ?, ?)";
      PreparedStatement preparedStmt = co.prepareStatement(query);
      preparedStmt.setString (1, IDTarjeta);
      preparedStmt.setString (2, ticket);
      preparedStmt.setString (3,cantidad);
      preparedStmt.executeUpdate();
        }
      catch (SQLException e){
          JOptionPane.showMessageDialog(null, "Error"+e);
      }
      
        catch(Exception r){
            JOptionPane.showMessageDialog(null, "Error"+r);
        }
            return true;
        }
    }
    
 
    