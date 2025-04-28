package propertynmoney;

import javax.swing.*;
import java.awt.*;

/**
 * @author Frank Pope
 * @author Nevin Fullerton
 * setupHelp is a JPanel that displays a help screen for the setup panel, including an image with instructions and a
 * button to return to the setup panel.
 */
public class setupHelp extends JPanel {

    /**
     * Constructor for setupHelp, sets up the panel and adds the components to it.
     * @param frame gives the setupHelp access to the StartGame class, so that it can switch panels.
     */
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
