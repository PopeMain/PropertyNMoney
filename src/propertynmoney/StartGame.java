package propertynmoney;

import javax.swing.*;

/**
 * @author Frank Pope
 * @author Nevin Fullerton
 * StartGame is the main class of the program, it creates the frame and sets up the panels for the game.
 */
public class StartGame extends JFrame {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // Create and display the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(StartGame::new);
    }

    /**
     * Create the application window, and start on the default start panel.
     */
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

    /**
     * Sets up the panel for the player to setup their game, and switches to that panel.
     */
    public void setupPanel() {
        setContentPane(new PlayerSetupPanel(this));
        revalidate();
        repaint();
        pack();
    }

    /**
     * Takes the player information from the setup panel and starts the game.
     * @param players is a list of the players made in setupPanel
     */
    public void playGame(Player[] players) {
        setContentPane(new GUI(this, players));
        revalidate();
        repaint();
        pack();
    }

    /**
     * Allows the player to end the game, and switches to the end game panel.
     * @param players is the list of players in the game, used to display the winner of the game.
     */
    public void endGame(Player[] players) {
        setContentPane(new EndGame(players, this));
        revalidate();
        repaint();
        pack();
    }

    /**
     * Allows users to go back to the start panel.
     */
    public void backToStart() {
        setContentPane(new StartPanel(this));
        revalidate();
        repaint();
        pack();
    }

    /**
     * Opens a panel that has a graphic explanation of the game setup panel.
     */
    public void setupHelp() {
        setContentPane(new setupHelp(this));
        revalidate();
        repaint();
        pack();
    }

    /**
     * Opens a panel that has a graphic explanation of the game play panel.
     */
    public void gameHelp() {
        setContentPane(new gameHelp(this));
        revalidate();
        repaint();
        pack();
    }
}


