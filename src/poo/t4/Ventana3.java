/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.t4;

import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
/**
 *
 * @author osilru
 */
public class Ventana3 extends JFrame implements ActionListener {
    
    
    //Declaramos las propiedades
    private JButton btnAceptar, btnCancelar;    
    private JTextField txtIDTarjeta, txtCantidad;

    //Constructor
    public Ventana3() {
        super("Pagos");
        setSize(400, 165);  //Establecemos las dimensiones del formulario (ancho x alto)
        setLocation(440, 100); //Establecemos la ubicación en pantalla (x,y)
        setResizable(false); //Para que no se pueda modificar el tamaño de la ventana

        //Paso 2. Vamos a crear una etiqueta
        JLabel lblIDTarjeta = new JLabel("No. Tarjeta:");
        JLabel lblCantidad = new JLabel("Cantidad:");
        
        //Paso 3. Vamos a crear un campo de texto
        //JTextField que limita el que solo se puedan escribir numeros
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
        lblCantidad.setBounds(10, 60, 60, 10);
        txtCantidad.setBounds(80, 55, 290, 25);
        btnAceptar.setBounds(100, 85, 90, 25);
        btnCancelar.setBounds(200, 85, 90, 25);
        
        //Paso 7. Agreguemos los componentes al contenedor
        pnlContenido.add(lblIDTarjeta);
        pnlContenido.add(txtIDTarjeta);
        pnlContenido.add(lblCantidad);
        pnlContenido.add(txtCantidad);
        pnlContenido.add(btnAceptar);
        pnlContenido.add(btnCancelar);

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
            String cantidad = txtCantidad.getText();            

        // Similar a Ventana 1, en el if debería ir el argumento que checa lo que 
            if (MonederoElectronico.pago(IDTarjeta, cantidad)){
                JOptionPane.showMessageDialog(null, "Se ha pagado con éxito", "", -1);
            } else{
                JOptionPane.showMessageDialog(null, "No se ha podido hacer el pago", "Advertencia", 0);                
            }
            close();
        } else {
            salir();
        }
    }
}

