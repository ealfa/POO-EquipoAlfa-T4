/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.t4;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

public class JPanelWithBackgroundImpl extends Ventana1.JPanelWithBackground {

    private Image backgroundImage;

    public JPanelWithBackground(String fileName) throws IOException {
        super(fileName);
        backgroundImage = ImageIO.read(new File(fileName));
    }

  // Some code to initialize the background image.
    // Here, we use the constructor to load the image. This
    // can vary depending on the use case of the panel.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }

}
