/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.t4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Esta clase representará la tarjeta, el monedero electronico. Puede crearse un
 * monedero electrónico con un cliente asociado o sin cliente.
 *
 * @author osilru
 */
public class MonederoElectronico {

    //Atributos
//    private Long IDTarjeta;
//    private Long IDMonedero;
//    private double saldo;
    private static Double sa;
    private static boolean b = false;
    static int configuracion = 0;

    //Nombre Cliente, Email, Telefono, idTarjeta, Ticket, Saldo
    static String[][] clientes = new String[6][100];

    public MonederoElectronico() {
        for (int i = 0; i < 100; i++) {
            this.clientes[0][i] = "";
            this.clientes[1][i] = "";
            this.clientes[2][i] = "";
            this.clientes[3][i] = "";
            this.clientes[4][i] = "";
            this.clientes[5][i] = "0";
        }
    }

    public static boolean pago(String IDTarjeta, String cantidad) {
        if (!("".equals(IDTarjeta) || "".equals(cantidad))) {
            for (int i = 0; i < 100; i++) {
                if (MonederoElectronico.clientes[3][i] == null ? IDTarjeta == null : MonederoElectronico.clientes[3][i].equals(IDTarjeta)) {
                    if (!(Integer.parseInt(cantidad) > Integer.parseInt(MonederoElectronico.clientes[5][i]))) {
                        int saldo = Integer.parseInt(MonederoElectronico.clientes[5][i]) - Integer.parseInt(cantidad);
                        MonederoElectronico.clientes[5][i] = Integer.toString(saldo);
                    } else {
                        JOptionPane.showConfirmDialog(null, "El cliente no tiene suficiente saldo para hacer el pago.");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "El Id de la Tajeta del Cliente no se encuentra en nuestra base de datos. De favor introduzcala nuevamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Al menos uno de los cuadros de texto no estan completos. Por favor complete los datos y vuelva a introducir los datos.");
        }

        if (cantidad.equalsIgnoreCase(".") || cantidad.equalsIgnoreCase(",")) {
            return false;
        } else {
            double monto = Double.parseDouble(cantidad);
            if (monto > 0) {
                return modificarSaldoDeTablaMonedero(IDTarjeta, obtenerSaldoDeTablaMonedero(IDTarjeta) + monto);
            } else {
                return false;
            }
        }
    }

    protected static boolean cargoATarjeta(String IDTarjeta, double cargo) {
        // Debe sacar el saldo que tenga tal ID        
        double monto = obtenerSaldoDeTablaMonedero(IDTarjeta);
        // Debe comparar el saldo que se tiene contra el que se pide.
        // Si se tiene suficiente saldo, se debe modificar la base de datos. En caso de no
        // contar con fondos suficientes, el método debe regresar False        
        if (cargo <= monto) {
            monto = monto - cargo;
            return modificarSaldoDeTablaMonedero(IDTarjeta, monto);
        } else {
            return false;
        }
    }

    // Devolverá un Arreglo de Strings doble. Regresará Null en caso de no haber encontrado dicha tarjeta.
    public static String[] informacionTarjeta(String IDTarjeta) {
        if (!("".equals(IDTarjeta))) {
            for (int i = 0; i < 100; i++) {
                if (IDTarjeta == null ? MonederoElectronico.clientes[3][i] == null : IDTarjeta.equals(MonederoElectronico.clientes[3][i])) {
                    JOptionPane.showMessageDialog(null, "Nombre Del Cliente: " + MonederoElectronico.clientes[0][i]);
                    JOptionPane.showMessageDialog(null, "Saldo: " + MonederoElectronico.clientes[5][i]);
                }
            }
            JOptionPane.showMessageDialog(null, "El Id de la Tajeta del Cliente no se encuentra en nuestra base de datos. De favor introduzcala nuevamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Al menos uno de los cuadros de texto no estan completos. Por favor complete los datos y vuelva a introducir los datos.");
        }

        double saldo = obtenerSaldoDeTablaMonedero(IDTarjeta);
        if (saldo < 0) {
            return null;
        } else {
            String[] informacion = new String[2];
            String nombreCliente = Cliente.obtenerNombreClienteConMonedero(IDTarjeta);
            if (nombreCliente == null) {
                nombreCliente = "=TARJETA SIN CLIENTE ASIGNADO=";
            }
            informacion[0] = nombreCliente;
            informacion[1] = Double.toString(saldo);

            return informacion;
        }
    }

    // Obtiene el Saldo que hay en el monedero. En caso de no existir en la base de datos, regresa un número negativo
    private static double obtenerSaldoDeTablaMonedero(String IDTarjeta) {
        try {
            mysqlConnection s = new mysqlConnection();
            s.conexion();
            Connection cc = s.conexion();
            String select = "select clientes.nombre, tarjetas.saldo from tarjetas, clientes where tarjetas.NumTarjeta =" + IDTarjeta + " and clientes.numtarjetaC=" + IDTarjeta;
            ResultSet rs;
            Statement stmt = cc.createStatement();
            rs = stmt.executeQuery(select);
            while (rs.next()) {
                sa = rs.getDouble("tarjetas.saldo");

            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(MonederoElectronico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {

        }

        return sa;
    }

    // Modifica el saldo el saldo del Monedero en las tablas
    private static boolean modificarSaldoDeTablaMonedero(String IDTarjeta, double monto) {
        try {
            // create the java mysql update preparedstatement
            mysqlConnection s = new mysqlConnection();
            s.conexion();
            Connection cc = s.conexion();

            String query = "update tarjetas set saldo =" + monto + " where NumTarjeta = " + IDTarjeta;
            PreparedStatement pago = cc.prepareStatement(query);

            int q = pago.executeUpdate();

            if (q > 0) {
                b = true;
            }
            // execute the java preparedstatement

        } catch (SQLException ex) {

        }
        return b;
    }

    public static boolean agregarConfiguracion(String configuracion) {
        MonederoElectronico.configuracion = Integer.parseInt(configuracion);
        return true;
    }
}
