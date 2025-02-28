package propertynmoney;

import java.util.Random;

public class PlayerTest {
    public static void main(String[] args) {
        System.out.println("PlayerTestStart");
        int startMoney = 200;
        int endMoney = startMoney;
        int position = 0;
        Player p1 = new Player(startMoney, "Bary");
        Random rand = new Random();

        for(int i = 0; i <= 15; i++) {
            int roll = rand.nextInt(12) + 1;
            int money = rand.nextInt(1000);
            p1.addMoney(money);
            endMoney += money;
            p1.movePosition(roll);
            position += roll;

            if(i == 6){
                int lessmoney = rand.nextInt(500);
                endMoney -= lessmoney;
                p1.subMoney(lessmoney);
            }
        }

        System.out.println("Player Money: " + p1.getMoney() + " == System Money: " + endMoney);
        System.out.println("Player Position: " + p1.getPosition() + " == Position Total: " + position);
        System.out.println("Player Position Check: " + (p1.getPosition() == position%40));
    }
}
