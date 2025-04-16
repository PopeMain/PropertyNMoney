package propertynmoney;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel(CardLayout cardLayout, JPanel mainPanelContainer) {
        // Set layout for this panel
        setLayout(new BorderLayout());

        // Create the title label
        JLabel titleLabel = new JLabel("Property and Money", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Create Start and Cancel buttons
        JButton startButton = new JButton("Start");
        JButton cancelButton = new JButton("Cancel");

        // Add action to the Start button to switch panels
        startButton.addActionListener(e -> {
            // Switch to MainPanel
            cardLayout.show(mainPanelContainer, "PlayerSetupPanel");
        });

        // Add action to the Cancel button
        cancelButton.addActionListener(e -> {
            // For now, it simply exits the program
            System.exit(0);
        });

        // Create a panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center align with spacing
        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);

        // Add components to this panel
        add(titleLabel, BorderLayout.NORTH);   // Add title to the top
        add(buttonPanel, BorderLayout.CENTER); // Add buttons to the center
    }

}
