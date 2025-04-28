package propertynmoney;

import javax.swing.*;
import java.awt.*;

public class setupHelp extends JPanel {

    public setupHelp(StartGame frame) {
        // Set BorderLayout to position components
        setLayout(new BorderLayout());

        // Create an ImageIcon and JLabel to display the image
        // Replace "your_image.png" with your actual image name
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/SetupHelp.png"));
        JLabel imageLabel = new JLabel(imageIcon);

        // Add image to the center
        add(imageLabel, BorderLayout.CENTER);

        // Create button
        JButton closeButton = new JButton("Back");
        closeButton.addActionListener(e -> {
            frame.setupPanel();
        });

        // Add button to the bottom
        add(closeButton, BorderLayout.SOUTH);
    }
}
