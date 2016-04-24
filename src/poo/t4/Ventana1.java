/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.t4;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import poo.t4.Cliente;

/**
 *
 * @author osilru
 */
public class Ventana1 extends JFrame implements ActionListener {

    //Declaramos las propiedades
    private JButton btnAceptar, btnCancelar;
    private JTextField txtNombre, txtEmail, txtTelefono, txtIDTarjeta;

    //Constructor
    public Ventana1() {
        super("Alta de Cliente");
        setSize(400, 230);  //Establecemos las dimensiones del formulario (ancho x alto)
        setLocation(440, 100); //Establecemos la ubicación en pantalla (x,y)
        setResizable(false); //Para que no se pueda modificar el tamaño de la ventana

        //Paso 2. Vamos a crear una etiqueta
        JLabel lblNombre = new JLabel("Nombre: ");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblTelefono = new JLabel("Teléfono:");
        JLabel lblIDTarjeta = new JLabel("No. Tarjeta:");

        JLabel lblImagen = new JLabel("");
        Image image = new ImageIcon(this.getClass().getResource("Drawing-layerExport.jpeg")).getImage();
        lblImagen.setIcon(new ImageIcon(image));
        lblImagen.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.getContentPane().add(lblImagen);

        //Paso 3. Vamos a crear un campo de texto
        //JTextField que limita el que solo se puedan escribir letras
        txtNombre = new JTextField();
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isLetter(c) || (Character.isSpace(c)) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    evt.consume();
                }
            }
        });

        //JTextField que limita el que solo se puedan escribir letras, numero, guion bajo, guion, punto y arroba
        txtEmail = new JTextField();
        /*txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!((Character.isLetter(c)) || (Character.isDigit(c)) || (c == KeyEvent.VK_MINUS) || (c == KeyEvent.VK_UNDERSCORE) || (c == KeyEvent.VK_AT) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    evt.consume();
                }
            }
        });*/

        //JTextField que limita el que solo se puedan escribir números
        txtTelefono = new JTextField();
        txtTelefono.setDocument(new LimiteDeTexto(10));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    evt.consume();
                }
            }
        });

        //JTextField que limita el que solo se puedan escribir números y guiones
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

        //Paso 4. Vamos a crear un botón.
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        //Paso 5. Vamos a crear el contenedor.
        JPanel pnlContenido = new JPanel(null); //Gestor nulo, util al usar setBounds

        //Paso 6. Ubicamos los elementos en el contenedor
        lblNombre.setBounds(10, 30, 60, 10); //x,y, ancho y alto
        txtNombre.setBounds(80, 25, 290, 25);
        lblEmail.setBounds(10, 60, 60, 10);
        txtEmail.setBounds(80, 55, 290, 25);
        lblTelefono.setBounds(10, 90, 60, 10);
        txtTelefono.setBounds(80, 85, 290, 25);
        lblIDTarjeta.setBounds(10, 120, 80, 15);
        txtIDTarjeta.setBounds(80, 115, 290, 25);
        btnAceptar.setBounds(100, 150, 90, 25);
        btnCancelar.setBounds(200, 150, 90, 25);

        //Paso 7. Agreguemos los componentes al contenedor
        pnlContenido.add(lblNombre);
        pnlContenido.add(txtNombre);
        pnlContenido.add(lblEmail);
        pnlContenido.add(txtEmail);
        pnlContenido.add(lblTelefono);
        pnlContenido.add(txtTelefono);
        pnlContenido.add(lblIDTarjeta);
        pnlContenido.add(txtIDTarjeta);
        pnlContenido.add(btnAceptar);
        pnlContenido.add(btnCancelar);

        pnlContenido.add(lblImagen);

        //Paso 8. Asociamos el contenedor a la ventana
        setContentPane(pnlContenido);

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
            String nombreDeCliente = txtNombre.getText();
            String email = txtEmail.getText();
            String telefono = txtTelefono.getText();
            String IDTarjeta = txtIDTarjeta.getText();

            try {
                if (Cliente.agregarCliente(nombreDeCliente, email, telefono, IDTarjeta)) {
                    JOptionPane.showMessageDialog(null, "Se ha agregado con éxito", "", -1);
                    salir();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha podido agregar", "Advertencia", 0);
                }
            } catch (SQLException ex) {

            }
            close();
        } else {
            salir();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package poo.t4;

 import java.awt.Frame;
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
/*public class Ventana1 extends JFrame implements ActionListener {

 //Declaramos las propiedades
 private JButton btnAceptar, btnCancelar;
 private JTextField txtNombre, txtEmail, txtTelefono, txtIDTarjeta;

 //Constructor
 public Ventana1() {
 super("Alta de Cliente");
 //setContentPane(new JLabel(new ImageIcon("/Users/jesusignacio159/Downloads/Drawing-layerExport.jpeg")));
 setSize(400, 230);  //Establecemos las dimensiones del formulario (ancho x alto)
 setLocation(440, 100); //Establecemos la ubicación en pantalla (x,y)
 setResizable(false); //Para que no se pueda modificar el tamaño de la ventana

 //Paso 2. Vamos a crear una etiqueta
 JLabel lblNombre = new JLabel("Nombre: ");
 JLabel lblEmail = new JLabel("Email:");
 JLabel lblTelefono = new JLabel("Teléfono:");
 JLabel lblIDTarjeta = new JLabel("No. Tarjeta:");

 //Paso 3. Vamos a crear un campo de texto
 txtNombre = new JTextField();
        
 txtEmail = new JTextField();
 txtEmail.addKeyListener(new KeyAdapter() {
 @Override
 public void keyTyped(KeyEvent e) {
 char c = e.getKeyChar();
 if ((((c < '0') || (c > '9')) && ((c != '@') && (c != '-'))) && (c != '_') && (c != '.') && (c != KeyEvent.VK_BACK_SPACE)) {
 e.consume();  // ignore event
 getToolkit().beep(); //sonido
 }
 }
 });

 //JTextField que limita el que solo se puedan escribir diez números.
 txtTelefono = new JTextField();
 txtTelefono.setInputVerifier(new InputVerifier() {
 @Override
 public boolean verify(JComponent input) {
 JTextField tField = (JTextField) input;
 return (tField.getText().trim().length() == 10);
 }
 });

 //JTextField que limita el que solo se puedan escribir números
 txtIDTarjeta = new JTextField();
 txtIDTarjeta.addKeyListener(new KeyAdapter() {
 @Override
 public void keyTyped(KeyEvent e) {
 char c = e.getKeyChar();
 if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
 e.consume();  // ignore event
 getToolkit().beep(); //sonido
 }
 }
 });

 //Paso 4. Vamos a crear un botón.
 btnAceptar = new JButton("Aceptar");
 btnCancelar = new JButton("Cancelar");

 //Paso 5. Vamos a crear el contenedor.
 JPanel pnlContenido = new JPanel(null); //Gestor nulo, util al usar setBounds

 //Paso 6. Ubicamos los elementos en el contenedor
 lblNombre.setBounds(10, 30, 60, 10); //x,y, ancho y alto
 txtNombre.setBounds(80, 25, 290, 25);
 lblEmail.setBounds(10, 60, 60, 10);
 txtEmail.setBounds(80, 55, 290, 25);
 lblTelefono.setBounds(10, 90, 60, 10);
 txtTelefono.setBounds(80, 85, 290, 25);
 lblIDTarjeta.setBounds(10, 120, 80, 15);
 txtIDTarjeta.setBounds(80, 115, 290, 25);
 btnAceptar.setBounds(100, 150, 90, 25);
 btnCancelar.setBounds(200, 150, 90, 25);

 //Paso 7. Agreguemos los componentes al contenedor
 pnlContenido.add(lblNombre);
 pnlContenido.add(txtNombre);
 pnlContenido.add(lblEmail);
 pnlContenido.add(txtEmail);
 pnlContenido.add(lblTelefono);
 pnlContenido.add(txtTelefono);
 pnlContenido.add(lblIDTarjeta);
 pnlContenido.add(txtIDTarjeta);
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

 @Override
 public void actionPerformed(ActionEvent e) {
 if (e.getSource() == btnAceptar) {
 this.setVisible(true);
 this.setVisible(false);
 this.dispose();

 String nombreDeCliente = txtNombre.getText();
 String email = txtEmail.getText();
 String telefono = txtTelefono.getText();
 String IDTarjeta = txtIDTarjeta.getText();

 try {
 if (Cliente.agregarCliente(nombreDeCliente, email, telefono, IDTarjeta)) {
 JOptionPane.showMessageDialog(null, "Se ha agregado con éxito", "", -1);
 } else {
 JOptionPane.showMessageDialog(null, "No se ha podido agregar", "Advertencia", 0);

 }
 } catch (SQLException ex) {
 Logger.getLogger(Ventana1.class.getName()).log(Level.SEVERE, null, ex);
 }
 } else {
 salir();
 }
 }
 }
 */
