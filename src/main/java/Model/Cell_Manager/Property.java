package Model.Cell_Manager;

import Controller.PropertyController;
import Model.Player.Player;

import java.io.IOException;

public class Property extends Cell {
    public Player owner = null;
    public final String name;
    public final int price;
    private int rent;
    private final int housePrice;
    private final int houseRent;
    private int houseNumber = 0;
    public int x, y;

    Property(String name, int price, int rent, int housePrice, int houseRent, int x, int y){
        super();
        this.name = name;
        this.price = price;
        this.rent = rent;
        this.housePrice = housePrice;
        this.houseRent = houseRent;
        this.x = x;
        this.y = y;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int new_rent){
        rent = new_rent;
    }

    public int getHouseRent() {
        return houseRent;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void acquireHouse() throws IOException {
        PropertyController.acquireHouse(this);
    }

    public int getHousePrice() {
        return housePrice;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getName(){
        return name;
    }

    @Override
    public String getDescription() {
        return "This is a property!";
    }
}

