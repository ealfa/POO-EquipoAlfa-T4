/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.t4;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author osilru
 */
public class Ventana2 extends JFrame implements ActionListener {
    
    //Declaramos las propiedades
    private JButton btnAceptar, btnCancelar;    
    private JTextField txtIDTarjeta, txtTicket,txtCantidad;

    //Constructor
    public Ventana2() {
        super("Registro de Compras");
        setSize(400, 200);  //Establecemos las dimensiones del formulario (ancho x alto)
        setLocation(440, 100); //Establecemos la ubicación en pantalla (x,y)
        setResizable(false); //Para que no se pueda modificar el tamaño de la ventana

        //Paso 2. Vamos a crear una etiqueta
        JLabel lblIDTarjeta = new JLabel("No. Tarjeta:");
        JLabel lblTicket = new JLabel("No. Ticket: ");
        JLabel lblCantidad = new JLabel("Cantidad:");
        
        JLabel lblImagen = new JLabel("");
        Image image = new ImageIcon(this.getClass().getResource("Drawing-layerExport.jpeg")).getImage();
        lblImagen.setIcon(new ImageIcon(image));
        lblImagen.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.getContentPane().add(lblImagen);
        
        //Paso 3. Vamos a crear un campo de texto
        //JTextField que limita el que solo se puedan escribir números
        txtIDTarjeta = new JTextField();
        txtIDTarjeta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_MINUS) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    evt.consume();
                    getToolkit().beep();
                }
            }
        });    
        //JTextField que limita el que solo se puedan escribir numeros
        txtTicket = new JTextField();
        txtTicket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isLetter(c) || Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    evt.consume();
                }
            }
        });

        
        //JTextField que solo permite numeros, punto y coma
        txtCantidad = new JTextField();
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    evt.consume();
                }
            }
        });

        
        //Paso 4. Vamos a crear un botón.
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        //Paso 5. Vamos a crear el contenedor.
        JPanel pnlContenido = new JPanel(null); //Gestor nulo, util al usar setBounds

        //Paso 6. Ubicamos los elementos en el contenedor
        lblIDTarjeta.setBounds(10, 30, 80, 15); //x,y, ancho y alto
        txtIDTarjeta.setBounds(80, 25, 290, 25);
        lblTicket.setBounds(10, 60, 80, 10);
        txtTicket.setBounds(80, 55, 290, 25);
        lblCantidad.setBounds(10, 90, 60, 10);
        txtCantidad.setBounds(80, 85, 290, 25);
        btnAceptar.setBounds(100, 120, 90, 25);
        btnCancelar.setBounds(200, 120, 90, 25);

        //Paso 7. Agreguemos los componentes al contenedor
        pnlContenido.add(lblIDTarjeta);
        pnlContenido.add(txtIDTarjeta);
        pnlContenido.add(lblTicket);
        pnlContenido.add(txtTicket);
        pnlContenido.add(lblCantidad);
        pnlContenido.add(txtCantidad);
        pnlContenido.add(btnAceptar);
        pnlContenido.add(btnCancelar);
        
        pnlContenido.add(lblImagen);

        //Paso 8. Asociamos el contenedor a la ventana
        setContentPane(pnlContenido);
        //this.getContentPane().add(new JPanelWithBackground("Drawing-layerExport.jpeg"));

        //Paso 9. Escucha de eventos.
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);

        //Paso 10. Hacemos visible la ventana
        setVisible(true);
    }

    private void salir() {
        dispose();
    }
    
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            String IDTarjeta = txtIDTarjeta.getText();
            String ticket = txtTicket.getText();
            String cantidad = txtCantidad.getText();            

            try {
                // Similar a Ventana 1, en el if debería ir el argumento que checa lo que
                if (Bitacora.registrarCompra(IDTarjeta, ticket, cantidad)){
                    JOptionPane.showMessageDialog(null, "Se ha registrado con éxito", "", -1);
                } else{                
                    JOptionPane.showMessageDialog(null, "No se ha podido registrar", "Advertencia", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ventana2.class.getName()).log(Level.SEVERE, null, ex);
            }
            close();
        } else {
            salir();
        }
    }
}
    