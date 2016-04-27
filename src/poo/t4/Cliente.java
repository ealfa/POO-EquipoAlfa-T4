/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.t4;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Cliente {

    //Como usaremos una base de datos 
    //Atributos
//    private Long id_cliente;
//    private String Nombre;
//    private String email;    
//    private String MonederoElectronico;
//    private Long idTarjeta;
    //Método que hace la conexión con el GUI. Debe devolver si se pudo agregar o no el cliente.
    public static boolean agregarCliente(String nombreDeCliente, String email, String telefono, String IDTarjeta) throws SQLException {
        if (!(("".equals(nombreDeCliente) || "".equals(email) || "".equals(telefono) || "".equals(IDTarjeta)))) {
            for (int i = 0; i < 100; i++) {
                if ("".equals(MonederoElectronico.clientes[0][i])) {
                    MonederoElectronico.clientes[0][i] = nombreDeCliente;
                    MonederoElectronico.clientes[1][i] = email;
                    MonederoElectronico.clientes[2][i] = telefono;
                    MonederoElectronico.clientes[3][i] = IDTarjeta;

                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Al menos uno de los cuadros de texto no estan completos. Por favor complete los datos y vuelva a introducir los datos.");
        }
        
        try {
            mysqlConnection con = new mysqlConnection();
            con.conexion();
            Connection cc = con.conexion();
            String query = "insert into clientes (nombre, email, telefono, numtarjetaC, idTarjetaC) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = cc.prepareStatement(query);
            preparedStmt.setString(1, nombreDeCliente);
            preparedStmt.setString(2, email);
            preparedStmt.setString(3, telefono);
            preparedStmt.setString(4, IDTarjeta);
            preparedStmt.setString(5, IDTarjeta);
            preparedStmt.executeUpdate();

            String query1 = "insert into tarjetas (idTarjeta, NumTarjeta, saldo) values (?, ?, ?)";
            PreparedStatement stTarjetas = cc.prepareStatement(query1);
            stTarjetas.setString(1, IDTarjeta);
            stTarjetas.setString(2, IDTarjeta);
            stTarjetas.setDouble(3, 0);
            stTarjetas.executeUpdate();

        } catch (SQLException e) {

        } catch (Exception r) {

        }

        return true;
    }

    static String obtenerNombreClienteConMonedero(String IDTarjeta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
