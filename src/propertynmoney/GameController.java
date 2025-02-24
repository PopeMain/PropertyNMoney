package propertynmoney;

import java.util.Random;

public class GameController {
    static final Random rand = new Random();

    public static void main(String[] args) {
        GameController gc = new GameController();
        int roll1 = gc.rollD6();
        System.out.println(Integer.toString(roll1));

    }

    public static int rollD6() {
        return rand.nextInt(1,6);
    }
}
