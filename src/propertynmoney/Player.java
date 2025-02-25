package propertynmoney;

public abstract class Player {
    int money;
    String name;

    public Player(int money, String name) {
        this.money = money;
        this.name = name;
    }

    public int getMoney() {return money;}
    public String getName() {return name;}

    public void addMoney(int money) {this.money += money;}
}
