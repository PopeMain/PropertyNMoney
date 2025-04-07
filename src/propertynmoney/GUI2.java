package propertynmoney;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GUI2 extends JFrame {
    private final Bank theBank = new Bank();

    private GUI2(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));

        // Make the Board add the image
        Image boardImage = new ImageIcon("src/GameBoard.png").getImage();
        Board board = new Board(boardImage);

        PlayerPanel playerOne = new PlayerPanel("Player 1", 1500);
        playerOne.paintPlayerSidePanel();
        //Add Players to the board
        board.addPlayer("Player 1", Color.RED);
        board.addPlayer("Player 2", Color.BLUE);
        board.movePlayer("Player 1", 0);
        board.movePlayer("Player 2", 15);

        add(playerOne, BorderLayout.EAST);
        add(board);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI2();
    }
}
