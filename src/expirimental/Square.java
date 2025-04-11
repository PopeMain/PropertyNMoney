package expirimental;

import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {
    private Icon[] players;

    public Square() {
        players = new ImageIcon[4];
        paintSquare();
    }

    public void paintSquare() {
        setLayout(new GridLayout(2,2));
        setSize(60, 60);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(new Color(100, 255, 255));

        //placeholders for player icons
        for (int i = 0; i < 4; i++) {
            JLabel playerIcon = new JLabel("", SwingConstants.CENTER);
            playerIcon.setIcon(players[i]);
            playerIcon.setHorizontalAlignment(SwingConstants.CENTER);
            playerIcon.setVerticalAlignment(SwingConstants.CENTER);
            add(playerIcon);
        }
    }

    public void updatePlayerIcons(){
        removeAll();
        for (int i = 0; i < 4; i++) {
            JLabel playerIcon = new JLabel("", SwingConstants.CENTER);
            playerIcon.setIcon(players[i]);
            playerIcon.setHorizontalAlignment(SwingConstants.CENTER);
            playerIcon.setVerticalAlignment(SwingConstants.CENTER);
            add(playerIcon);
        }
        revalidate();
        repaint();
    }

    public void addPlayer(Icon playerIcon, int index){
        players[index] = playerIcon;
        updatePlayerIcons();
    }

    public void removePlayer(int index){
        players[index] = null;
        updatePlayerIcons();
    }
}
