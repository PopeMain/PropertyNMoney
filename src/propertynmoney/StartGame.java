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
        setLocation(25,25);
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
        setContentPane(new GUI(this, players));
        revalidate();
        repaint();
        pack();
    }

    public void endGame(Player[] players) {
        setContentPane(new EndGame(players, this));
        revalidate();
        repaint();
        pack();
    }

    public void backToStart() {
        setContentPane(new StartPanel(this));
        revalidate();
        repaint();
        pack();
    }

    public void setupHelp() {
        setContentPane(new setupHelp(this));
        revalidate();
        repaint();
        pack();
    }

    public void gameHelp() {
        setContentPane(new gameHelp(this));
        revalidate();
        repaint();
        pack();
    }
}


