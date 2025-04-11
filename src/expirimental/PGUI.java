package expirimental;

import javax.swing.*;
import java.awt.*;

public class PGUI extends JPanel {
    private PlayerPanel player1;
    private PlayerPanel player2;
    private PlayerPanel player3;
    private PlayerPanel player4;

    public PGUI() {
        setLayout(new GridLayout(1,4));

        // Load utility icon (replace with a real image if available)
        Icon utilityIcon = new ImageIcon("utility.png"); //


        // Path to the sprite sheet (replace with actual path)
        String spriteSheetPath = "src/ppSprite.png";

        player1 = new PlayerPanel("Frank", 1500, 2, spriteSheetPath, utilityIcon);
        player2 = new PlayerPanel("Nevin", 1500, 3, spriteSheetPath, utilityIcon);
        player3 = new PlayerPanel("John", 1500, 4, spriteSheetPath, utilityIcon);
        player4 = new PlayerPanel("Nate", 1500, 5, spriteSheetPath, utilityIcon);

        add(player1);
        add(player2);
        add(player3);
        add(player4);
        setVisible(true);
    }

    public void addProperty(int player, PropertyNames name) {
        if (player == 1) {
            player1.addProperty(name);
        } else if (player == 2) {
            player2.addProperty(name);
        } else if (player == 3) {
            player3.addProperty(name);
        } else if (player == 4) {
            player4.addProperty(name);
        }
    }

    public PlayerPanel getPlayerPanel(int player) {
        if (player == 1) {
            return player1;
        } else if (player == 2) {
            return player2;
        } else if (player == 3) {
            return player3;
        } else if (player == 4) {
            return player4;
        } else {
            return null;
        }
    }
}
