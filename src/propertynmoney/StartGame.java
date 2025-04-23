package propertynmoney;

import javax.swing.*;

public class StartGame extends JFrame {

    public static void main(String[] args) {
        // Create and display the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(StartGame::new);
    }

    public StartGame() {
        // Set the frame title
        super("Property and Money");

        // Set size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen
        setContentPane(new StartPanel(this));

        // Show the initial frame
        pack(); // Size the frame based on the initial card
        setVisible(true);
    }

    public void setupPanel() {
        setContentPane(new PlayerSetupPanel(this));
        revalidate();
        repaint();
        pack();
    }

    public void playGame(Player[] players) {
        setContentPane(new GUI(players));
        this.setSize(1000, 800);
        this.setResizable(false);
        revalidate();
        repaint();
    }
}


