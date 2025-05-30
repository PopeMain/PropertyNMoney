package propertynmoney;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class EndGame extends JPanel {

    private final StartGame frame;
    private final Player[] players;

    public EndGame(Player[] players, StartGame frame) {
        this.players = players;
        this.frame = frame;
        setLayout(new BorderLayout());
        setSize(200, 200);

        // Add a title at the top of the panel
        JLabel title = new JLabel("GAME OVER", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Calculate the final value of each player
        Player[] sortedPlayers = Arrays.copyOf(players, players.length);
        Arrays.sort(sortedPlayers, (p1, p2) -> Integer.compare(
                getPlayerFinalValue(p2), getPlayerFinalValue(p1)
        ));

        // Create a panel to hold the leaderboard
        JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        leaderboardPanel.setAlignmentX(CENTER_ALIGNMENT);

        // Add each player's details to the leaderboard
        for (int i = 0; i < sortedPlayers.length; i++) {
            Player player = sortedPlayers[i];
            int finalValue = getPlayerFinalValue(player);
            JLabel playerLabel = new JLabel((i + 1) + ". " + player.getName() + " - $" + finalValue);
            playerLabel.setFont(new Font("Serif", Font.PLAIN, 18));
            playerLabel.setAlignmentX(CENTER_ALIGNMENT);
            leaderboardPanel.add(playerLabel);
        }

        // Add the leaderboard to the center of the panel
        add(leaderboardPanel, BorderLayout.CENTER);

        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        JButton restartButton = new JButton("DONE");
        restartButton.addActionListener(e -> {
            frame.backToStart();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Calculate the total value of a player by adding their money
     * and the total value of their properties and utilities.
     *
     * @param player The player whose value is to be calculated.
     * @return The total value of the player.
     */
    private int getPlayerFinalValue(Player player) {
        int totalValue = player.getMoney();
        for (Property property : player.getProperties()) {
            totalValue += property.getValue();
        }
        for (Utility utility : player.getUtilities()) {
            totalValue += utility.getValue();
        }
        return totalValue;
    }
}

