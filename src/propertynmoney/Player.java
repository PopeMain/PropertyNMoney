package propertynmoney;
/*
Auth: Frank Pope
GroupID: The Capitalists
Notes: This is Bullshit Code that can be used to store info specific to each player.
 */


public class Player {
    int money;
    int position;
    String name;
    boolean inJail;

    public Player(int money, String name) {
        /*
        The Player only stores the information for each indivdual payer.
         */
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
