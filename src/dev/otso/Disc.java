package dev.otso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Disc extends JPanel {
    // Color of the disc
    private Color color;

    private Color textColor;
    private String text;

    // Disc text font
    private Font textFont;
    private int fontSize;

    // How seethrough the disc is
    private int alpha;

    // How long is the rim of the disc
    private int rimSize;

    // Store the drawing on the disc
    private List<Point> drawnPoints = new ArrayList<>();
    // Store the colors of drawed points
    private List<Color> drawPointColors = new ArrayList<>();
    // Initial rotation angle
    private double rotationAngle = 0;
    // Timer for rotation animation
    private Timer animationTimer;

    private Point center;


    public Disc(Color c, String text) {
        System.out.println("Constructor called");
        this.color = c;
        this.text = text;
        this.alpha = 128;
        this.rimSize = 20;
        this.textColor = Color.RED;
        this.fontSize = 30;
        this.textFont = new Font("Arial", Font.PLAIN, fontSize);
        this.center = new Point(0, 0); // Initialize center at (0, 0)

        // Add mouse listener for drawing
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawOnDisc(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                drawOnDisc(e.getX(), e.getY());
            }
        });
        // Create a timer for animation
        int delay = 50; // Adjust the delay as needed for smoothness of animation
        animationTimer = new Timer(delay, e -> {
            rotationAngle += Math.toRadians(1); // Increment the rotation angle
            repaint(); // Trigger repaint to show updated rotation
        });
        animationTimer.start(); // Start the animation timer
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
    public Font getTextFont() {
        return textFont;
    }

    public void setTextFont(Font textFont) {
        System.out.println("Setting test font to " + textFont);
        this.textFont = textFont.deriveFont((float) fontSize);
        repaint();
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        System.out.println("Setting font size to " + fontSize);
        this.fontSize = fontSize;
        setTextFont(textFont);
    }

    public List<Point> getDrawnPoints() {
        return drawnPoints;
    }

    public void setDrawnPoints(List<Point> drawnPoints) {
        this.drawnPoints = drawnPoints;
    }

    public List<Color> getDrawPointColors() {
        return drawPointColors;
    }

    public void setDrawPointColors(List<Color> drawPointColors) {
        this.drawPointColors = drawPointColors;
    }

    // Method to draw on the disc
    private void drawOnDisc(int x, int y) {
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setColor(textColor);
        g2d.fillOval(x - 5, y - 5, 10, 10); // Adjust the size as needed
        drawnPoints.add(new Point(x,y));
        drawPointColors.add(textColor);
        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast Graphics to Graphics2D because we want thicker outline
        Graphics2D g2d = (Graphics2D) g;

        // Calculate the center of the disc based on the current size
        center.x = getWidth() / 2;
        center.y = getHeight() / 2;
        // Apply rotation transformation around the fixed center
        g2d.rotate(rotationAngle, center.x, center.y);

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

        // Use the specified font when drawing text
        g2d.setFont(textFont);
        FontMetrics metrics = g2d.getFontMetrics(textFont);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        g2d.setColor(textColor);
        // Draw text to center
        g2d.drawString(text, (diameter - textWidth) / 2, (diameter + textHeight) / 2);

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

        // Redraw the drawings
        g2d.setColor(textColor);
        // Matching indexing for drawing colors
        int colorIndex = 0;
        for (Point point : drawnPoints) {
            g2d.setColor(drawPointColors.get(colorIndex));
            g2d.fillOval(point.x - 5, point.y - 5, 10, 10);
            colorIndex++;
        }

        //g2d.rotate(-rotationAngle, center.x, center.y);


    }

}
