package propertynmoney;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private final JPanel boardPanel;
    private final JPanel playerPanel;
    private final JPanel actionPanel;

    GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Property N Money");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());

        boardPanel = new JPanel();
        JLabel boardLabel = new JLabel("Board");
        boardPanel.add(boardLabel);

        this.add(boardPanel, BorderLayout.CENTER);

        playerPanel = new JPanel();
        JLabel playerLabel = new JLabel("Player");
        playerPanel.add(playerLabel);

        this.add(playerPanel, BorderLayout.EAST);

        actionPanel = new JPanel();
        JLabel actionLabel = new JLabel("Actions");
        actionPanel.add(actionLabel);

        this.add(actionPanel, BorderLayout.SOUTH);


        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
