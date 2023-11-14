package dev.otso;

import javax.swing.*;
import java.awt.*;

public class Disc extends JPanel {
    // Color of the disc
    private Color color;

    private Color textColor;
    private PlasticType plasticType;

    // How seethrough the disc is
    private int alpha;

    // How long is the rim of the disc
    private int rimSize;

    public Disc(Color c, PlasticType p) {
        System.out.println("Constructor called");
        this.color = c;
        this.plasticType = p;
        this.alpha = 128;
        this.rimSize = 20;
        this.textColor = Color.RED;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

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

    public int getRimSize() {
        return rimSize;
    }

    public void setRimSize(int rimSize) {
        this.rimSize = rimSize;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
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
        // Draw the filled circle
        g2d.fillOval(0,0, diameter, diameter);
        g2d.setColor(textColor);
        // Draw text to the center
        g2d.drawString(plasticType.toString(), diameter / 2, diameter / 2);


        g2d.setColor(Color.BLACK);
        // Draw the stroke (outline)
        g2d.drawOval(0,0, diameter, diameter);

        // Draw the rim of the disc
        int innerDiameter = diameter - 2 * rimSize;
        int innerX = rimSize;
        int innerY = rimSize;

        // Draw the inner circle
        g2d.setColor(Color.BLACK);
        g2d.drawOval(innerX, innerY, innerDiameter, innerDiameter);


    }
}
