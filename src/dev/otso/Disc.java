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

        // Cast Graphics to Graphics2D because we want thicker outline
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        // Set the stroke (outline)
        Stroke customStroke = new BasicStroke(2.0f);
        g2d.setStroke(customStroke);

        // Do this because different plastic have different opacity
        Color transColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
        // Draw a colored circle representing the disc
        g2d.setColor(transColor);
        int diameter = Math.min(getWidth() -50, getHeight() -50);
        g2d.fillOval(0,0, diameter, diameter);
        g2d.setColor(Color.black);
        // Draw plastic type to disc center
        g2d.drawString(plasticType.toString(), diameter / 2, diameter / 2);


        g2d.setColor(Color.BLACK);
        // Draw the stroke (outline)
        g2d.drawOval(0,0, diameter, diameter);
    }
}
