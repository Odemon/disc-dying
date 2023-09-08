package dev.otso;

import javax.swing.*;
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
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });

    }

}
