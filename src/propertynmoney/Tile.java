package propertynmoney;
import propertynmoney.Player.*;
import propertynmoney.Property.*;


public abstract class Tile {
    Node head;

    private static class Node {
        Property data;
        Node next;

        Node(Property data) {
            this.data = data;
            this.next = null;
        }
    }
//
//    public Tile() {}
//
//    public nodeGetData(){}
//
}
