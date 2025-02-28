package propertynmoney;

public class Player {
    int money;
    int position;
    String name;
    boolean inJail;

    public Player(int money, String name) {
        this.money = money;
        this.name = name;
        this.position = 0;
        this.inJail = false;
    }

    public int getMoney() {return money;}
    public String getName() {return name;}

    public void addMoney(int money) {this.money += money;}
    public void subMoney(int money) {this.money -= money;}

    public int getPosition() {return position;}
    public void movePosition(int rollValue){
        position = (position + rollValue) % 40;
    }


}
