package propertynmoney;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private final JPanel boardPanel;
    private final JPanel sideBarPanel;
    private final JPanel actionPanel;

    GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Property N Money");
        this.setSize(1000, 900);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        final ImageIcon gameBoard = new ImageIcon("src/GameBoard.png");

        // Construct Board Panel
        boardPanel = new JPanel();
        JLabel boardLabel = new JLabel("Board");
        boardLabel.setIcon(gameBoard);
        boardLabel.setBounds(0, 0, gameBoard.getIconWidth(), gameBoard.getIconHeight());
        System.out.println(gameBoard.getIconWidth());
        boardLabel.setVisible(true);
        boardPanel.add(boardLabel);

        this.add(boardPanel, BorderLayout.CENTER);

        // Construct Side Bar Panel
        sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        paintPlayerSidePanel();

        this.add(sideBarPanel, BorderLayout.EAST);

        // Construct Action Panel
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        paintStandardButtonFrame();

        this.add(actionPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private void paintPlayerSidePanel() {
        clearSideBarPanel();

        JLabel playerNameLabel = new JLabel("Player Name:");
        JLabel playerMoneyLabel = new JLabel("Player Money:");
        JButton propertiesButton = new JButton("Properties");

        sideBarPanel.add(playerNameLabel);
        sideBarPanel.add(playerMoneyLabel);
        sideBarPanel.add(propertiesButton);

    }

    private void clearSideBarPanel() {
        sideBarPanel.removeAll();
    }

    private void paintStandardButtonFrame() {
        clearActionPanel();

        JButton rollDiceButton = new JButton("Roll Dice");
        JButton buyHousesButton = new JButton("Buy Houses");
        JButton mortgageButton = new JButton("Mortgage");
        JButton endTurnButton = new JButton("End Turn");

        actionPanel.add(rollDiceButton);
        actionPanel.add(buyHousesButton);
        actionPanel.add(mortgageButton);
        actionPanel.add(endTurnButton);

    }

    private void clearActionPanel() {
        actionPanel.removeAll();
    }

    public static void main(String[] args) {
        new GUI();
    }
}
