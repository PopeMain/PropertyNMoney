package propertynmoney;

import javax.swing.*;
import java.awt.*;

/**
 * @author Frank Pope
 * @author Nevin Fullerton
 * StartPanel is the first panel that is displayed when the program starts, it contains the title, and buttons to
 * start the game into the setup panel, open the Game help panel, or exit the program.
 */
public class StartPanel extends JPanel {

    /**
     * Constructor for StartPanel, sets up the panel and adds the components to it.
     * @param frame gives the StartPanel access to the StartGame class, so that it can switch panels.
     */
    public StartPanel(StartGame frame) {
        // Set layout for this panel
        setLayout(new BorderLayout());
        setSize(200, 200);

        // Create the title label
        JLabel titleLabel = new JLabel("Property and Money", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Create Start and Cancel buttons
        JButton startButton = new JButton("Start");
        JButton cancelButton = new JButton("EXIT");

        // Add action to the Start button to switch panels
        startButton.addActionListener(e -> {
            // Switch to setup Panel
            frame.setupPanel();
        });

        // Add action to the Cancel button
        cancelButton.addActionListener(e -> {
            // For now, it simply exits the program
            System.exit(0);
        });

        //Help Button
        JButton helpButton = new JButton("Help");
        helpButton.addActionListener(e -> {
           frame.gameHelp();
        });

        // Create a panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center align with spacing
        buttonPanel.add(startButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(cancelButton);


        // Add components to this panel
        add(titleLabel, BorderLayout.NORTH);   // Add title to the top
        add(buttonPanel, BorderLayout.CENTER); // Add buttons to the center
    }

}
