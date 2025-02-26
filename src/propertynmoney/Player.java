package propertynmoney;

public abstract class Player {
    int money;
    int position;
    String name;

    public Player(int money, String name) {
        this.money = money;
        this.name = name;
        this.position = 0;
    }

    public int getMoney() {return money;}
    public String getName() {return name;}

    public void addMoney(int money) {this.money += money;}
    public void subMoney(int money) {this.money -= money;}


}
