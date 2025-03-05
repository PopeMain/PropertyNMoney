package propertynmoney;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameController {


    public static void main(String[] args) {
        new GameController("Property N Money", 50, 50, 800, 800);
    }

    private final JFrame gameJFrame;

    public GameController(String passedInWindowTitle, int gameWindowX, int gameWindowY, int gameWindowWidth, int gameWindowHeight) {
        gameJFrame = new JFrame(passedInWindowTitle);
        gameJFrame.setSize(gameWindowWidth, gameWindowHeight);
        gameJFrame.setLocation(gameWindowX, gameWindowY);
        gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameJFrame.setIconImage(new ImageIcon("icon32.png").getImage());

        // no layout, will use absolute system
        Container gameContentPane = gameJFrame.getContentPane();
        gameContentPane.setLayout(new GridLayout(10,10,25,25));
        gameContentPane.setBackground(Color.CYAN);
        gameJFrame.setVisible(true);
    }

}
