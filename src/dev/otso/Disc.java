package dev.otso;

import javax.swing.*;
import java.awt.*;

public class Disc extends JPanel {
    private Color color;
    private PlasticType plasticType;

    private int alpha;

    public void setAlpha(int a) {
        if (a < 0) {
            alpha = 0;
        } else if (a > 255) {
            alpha = 255;
        } else {
            this.alpha = a;
        }
        repaint();
    }

    public Disc(Color c, PlasticType p) {
        System.out.println("Constructor called");
        this.color = c;
        this.plasticType = p;
        this.alpha = 128;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("paintComponent and alpha is "+alpha);

        // Do this because different plastic have different opacity
        Color transColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
        // Draw a colored circle representing the disc
        g.setColor(transColor);
        int diameter = Math.min(getWidth() -50, getHeight() -50);
        g.fillOval(0,0, diameter, diameter);
        g.setColor(Color.black);
        // Draw plastic type to disc center
        g.drawString(plasticType.toString(), diameter / 2, diameter / 2);
    }
}
