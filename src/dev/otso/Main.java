package dev.otso;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() {
        setTitle("Disc Dying Is Fun!");
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Center the window
        setLocationRelativeTo(null);

        // Creeate menu
        createMenu();

        // Create other components
        createComponents();

    }

    // Create menus
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);

        // Add quit action
        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void createComponents() {
        // Make panel to hold components
        JPanel componentPanel = new JPanel();
        this.add(componentPanel);
        Disc testDisc = new Disc(Color.GREEN, "Text");
        componentPanel.setLayout(new BorderLayout());
        componentPanel.add(testDisc, BorderLayout.CENTER);

        // Slider for choosing alpha value for disc
        JSlider alphaSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
        alphaSlider.setMajorTickSpacing(50);
        alphaSlider.setMinorTickSpacing(10);
        alphaSlider.setPaintTicks(true);
        alphaSlider.setPaintLabels(true);

        alphaSlider.addChangeListener(e -> {
            int alpha = alphaSlider.getValue();
            // Create or update your color with the new alpha value
            testDisc.setAlpha(alphaSlider.getValue());
            testDisc.repaint();
            // Update the color display or any other component using the color

        });

        componentPanel.add(alphaSlider, BorderLayout.EAST);

        // Panel for color buttons
        JPanel colorPanel = new JPanel();
        // Add button for choosing disc color
        JButton discColorButton = new JButton("Choose disc color");
        discColorButton.addActionListener(e -> {
            System.out.println("Color button pushed");
            Color initalColor = testDisc.getColor();
            Color newColor = JColorChooser.showDialog(this, "Select disc color", initalColor);
            testDisc.setColor(newColor);
            testDisc.repaint();
        });
        colorPanel.add(discColorButton);

        // Add button for choosing text color
        JButton textColorButton = new JButton("Choose disc text color");
        textColorButton.addActionListener(e -> {
            System.out.println("Disc Color button pushed");
            Color initalColor = testDisc.getTextColor();
            Color newColor = JColorChooser.showDialog(this, "Select text color", initalColor);
            testDisc.setTextColor(newColor);
            testDisc.repaint();
        });
        colorPanel.add(textColorButton);

        // Add text field for Disc text
        JTextField discTextField = new JTextField(testDisc.getText(),20);

        // Add DocumentListener to track text changes
        discTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeDiscText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeDiscText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeDiscText();
            }

            private void changeDiscText() {
                testDisc.setText(discTextField.getText());
                testDisc.repaint();
            }
        });
        colorPanel.add(discTextField);

        // Panel for font size selection
        JPanel fontSizePanel = new JPanel();

        // Add JSpinner for choosing text size
        SpinnerNumberModel fontSizeModel = new SpinnerNumberModel(30, 1, 100, 1); // Initial value, minimum, maximum, step
        JSpinner fontSizeSpinner = new JSpinner(fontSizeModel);
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newSize = (int) fontSizeSpinner.getValue();
                testDisc.setFontSize(newSize);
                testDisc.repaint();
            }
        });

        fontSizePanel.add(new JLabel("Text Size:"));
        fontSizePanel.add(fontSizeSpinner);

        componentPanel.add(fontSizePanel, BorderLayout.NORTH);

        // Add combobox for choosing font
        JPanel fontPanel = new JPanel();

        // Add JComboBox for font selection
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        JComboBox<String> fontComboBox = new JComboBox<>(fontNames);
        fontComboBox.addActionListener(e -> {
            String selectedFontName = (String) fontComboBox.getSelectedItem();
            Font selectedFont = new Font(selectedFontName, Font.PLAIN, testDisc.getFontSize()); // You can adjust the size as needed
            testDisc.setTextFont(selectedFont);
            testDisc.repaint();
        });
        fontPanel.add(new JLabel("Select Text Font:"));
        fontPanel.add(fontComboBox);
        fontPanel.add(fontSizePanel);

        componentPanel.add(fontPanel, BorderLayout.NORTH);


        componentPanel.add(colorPanel, BorderLayout.SOUTH);

        setContentPane(componentPanel);
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });

    }

}
