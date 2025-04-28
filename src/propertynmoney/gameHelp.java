package propertynmoney;

import javax.swing.*;
import java.awt.*;

public class gameHelp extends JPanel {

    public gameHelp(StartGame frame) {
        setLayout(new BorderLayout());

        // Create an ImageIcon and JLabel to display the image
        // Replace "your_image.png" with your actual image name
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/SetupHelp.png"));
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
