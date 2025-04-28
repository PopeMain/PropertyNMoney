package propertynmoney;

import javax.swing.*;
import java.awt.*;

/**
 * @author Frank Pope
 * @author Nevin Fullerton
 * The gameHelp class represents a JPanel that displays a help screen for the game play loop,
 * including an image with instructions and buttons to either proceed to the game setup
 * or return to the start panel.
 */
public class gameHelp extends JPanel {

    /**
     * Constructor for gameHelp, sets up the panel and adds the components to it.
     * @param frame gives the gameHelp access to the StartGame class, so that it can switch panels.
     */
    public gameHelp(StartGame frame) {
        setLayout(new BorderLayout());

        // Create an ImageIcon and JLabel to display the image
        // Replace "your_image.png" with your actual image name
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/GameHelp.png"));
        JLabel imageLabel = new JLabel(imageIcon);

        // Add image to the center
        add(imageLabel, BorderLayout.CENTER);

        // Create button
        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            frame.setupPanel();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            frame.backToStart();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(playButton);
        buttonPanel.add(cancelButton);
        // Add button to the bottom
        add(buttonPanel, BorderLayout.SOUTH);

    }
}
