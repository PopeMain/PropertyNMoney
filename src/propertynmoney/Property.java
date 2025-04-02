package propertynmoney;

import java.awt.*;

public class Property {
    int buyValue;
    private final PropertyNames name;
    private boolean purchased;

    enum Houses {MORTGAGE, ZERO, ONE, TWO, THREE, FOUR, HOTEL}
    private Houses homes;

    public Property(int value, PropertyNames name) {
        this.buyValue = value;
        this.name = name;
        this.purchased = false;
        this.homes = Houses.ZERO;
    }
    private String getName() {
        return name.toString();
    }

    public Color getColor(){
        return name.getColor();
    }

    @Override
    public String toString() {
        return getName();
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void gotPurchased() {
        purchased = true;
    }

    public int getBuyValue() {
        return buyValue;
    }

    public int rentVal(){
        if (homes == Houses.MORTGAGE) {
            return 0;
        } else if (homes == Houses.ZERO) {
            return (int) (buyValue * 0.1);
        } else if (homes == Houses.ONE) {
            return (int) (buyValue * 0.3);
        } else if (homes == Houses.TWO) {
            return (int) (buyValue * 0.75);
        } else if (homes == Houses.THREE) {
            return (int) (buyValue * 0.95);
        } else if (homes == Houses.FOUR) {
            return (int) (buyValue * 1.25);
        } else if (homes == Houses.HOTEL) {
            return (int) (buyValue * 2.0);
        } else {
            return 0;
        }
    }

    public void addHomes() {
        if (homes != Houses.MORTGAGE){
            if (homes == Houses.ZERO) {
                homes = Houses.ONE;
            } else if (homes == Houses.ONE) {
                homes = Houses.TWO;
            } else if (homes == Houses.TWO) {
                homes = Houses.THREE;
            } else if (homes == Houses.THREE) {
                homes = Houses.FOUR;
            } else if (homes == Houses.FOUR) {
                homes = Houses.HOTEL;
            }
        } else {
            homes = Houses.ZERO;
        }
    }

    public void removeHomes() {
        if (homes != Houses.MORTGAGE){
            if (homes == Houses.HOTEL) {
                homes = Houses.FOUR;
            } else if (homes == Houses.FOUR) {
                homes = Houses.THREE;
            } else if (homes == Houses.THREE) {
                homes = Houses.TWO;
            } else if (homes == Houses.TWO) {
                homes = Houses.ONE;
            } else if (homes == Houses.ONE) {
                homes = Houses.ZERO;
            } else {
                homes = Houses.MORTGAGE;
            }
        }
    }
}
