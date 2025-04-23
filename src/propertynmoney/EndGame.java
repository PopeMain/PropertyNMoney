package propertynmoney;

import javax.swing.*;
import java.awt.*;

public class EndGame extends JPanel {

    public EndGame(Player[] players){
        setLayout(new BorderLayout());
        setSize(200, 200);

        JLabel title = new JLabel("GAME OVER");
        add(title, BorderLayout.NORTH);
    }
}
