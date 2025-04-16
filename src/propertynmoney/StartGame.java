package propertynmoney;

import javax.swing.*;
import java.awt.*;

public class StartGame extends JFrame {

    public StartGame() {
        // Set the frame title
        super("Property and Money");

        // Set size of the frame
        setSize(1000, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen

        // Create a container panel with CardLayout
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanelContainer = new JPanel(cardLayout);

        // Create panels
        StartPanel startPanel = new StartPanel(cardLayout, mainPanelContainer);
        PlayerSetupPanel playerSetupPanel = new PlayerSetupPanel(cardLayout, mainPanelContainer);
        GUI gamePanel = new GUI();

        // Add panels to the container with unique keys
        mainPanelContainer.add(startPanel, "StartPanel");
        mainPanelContainer.add(playerSetupPanel, "PlayerSetupPanel");
        mainPanelContainer.add(gamePanel, "GamePanel");

        // Add the container to the frame
        add(mainPanelContainer);

        // Show the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create and display the application
        new StartGame();
    }
}

