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
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que almacenará todas las transferencias o transacciones de la tienda
 *
 * @author osilru
 * @author LetiBadillo
 */
public class Bitacora {

    public static boolean registrarCompra(String IDTarjeta, String ticket, String cantidad) throws SQLException {
        if (!("".equals(IDTarjeta) || "".equals(ticket) || "".equals(cantidad))) {
            Boolean seEncontro = false;
            for (int i = 0; i < 100; i++) {

                if (MonederoElectronico.clientes[3][i] == null ? IDTarjeta == null : MonederoElectronico.clientes[3][i].equals(IDTarjeta)) {

                    MonederoElectronico.clientes[4][i] = ticket;

                    int saldo = Integer.parseInt(MonederoElectronico.clientes[5][i]) + Integer.parseInt(cantidad);
                    int porcentageDeAcomulado = saldo + ((saldo * MonederoElectronico.configuracion) / 100);
                    MonederoElectronico.clientes[5][i] = Integer.toString(saldo);
                    seEncontro = true;

                }
            }
            if (seEncontro) {
                JOptionPane.showMessageDialog(null, "El Id de la Tajeta del Cliente no se encuentra en nuestra base de datos. De favor introduzcala nuevamente.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Al menos uno de los cuadros de texto no estan completos. Por favor complete los datos y vuelva a introducir los datos.");
        }

        try {
            mysqlConnection c = new mysqlConnection();
            c.conexion();
            Connection co = c.conexion();
            String query = "insert into registrodecompra (numtarjetaR, ticket, cantidad) values (?, ?, ?)";
            PreparedStatement preparedStmt = co.prepareStatement(query);
            preparedStmt.setString(1, IDTarjeta);
            preparedStmt.setString(2, ticket);
            preparedStmt.setString(3, cantidad);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {

        } catch (Exception r) {

        }
        return true;
    }
}
