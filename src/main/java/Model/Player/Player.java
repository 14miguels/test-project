package Model.Player;

import Controller.PlayerController;
import Model.Cell_Manager.Property;
import Model.Pawn;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    public int position_index = 0;
    public int ref;
    public TextColor color;
    public Pawn pawn;
    public int account_balance = 1500;
    public int consecutive_doubles = 0;
    public int jailturns;

    private List<Property> Properties = new ArrayList<>();

    private boolean jail = false;

    public Player(String name, int ref, TextColor color) {
        this.name = name;
        this.ref = ref;
        this.color = color;
        this.pawn = new Pawn(this, color);  // Initialize pawn after color is set
    }

    //doubles
    public int getConsecutive_doubles() {
        return consecutive_doubles;
    }

    public void setConsecutive_doubles(int consecutive_doubles) {
        this.consecutive_doubles = consecutive_doubles;
    }


    //pawn
    public void setPawnX(int x){
        pawn.setX(x);
    }

    public void setPawnY(int y){
        pawn.setY(y);
    }

    public int getPawnX(){
        return pawn.getX();
    }

    public int getPawnY(){
        return pawn.getY();
    }


    //others
    public List<Property> getProperties() {
        return Properties;
    }

    public void rollDice() throws IOException {
        PlayerController.rollDice(this, pawn);
    }

    public void withdraw(int price){
        PlayerController.withdraw(this, price);
    }

    public void deposit(int price){
        PlayerController.deposit(this, price);
    }

    public void buyProperty(Property property) {
        PlayerController.buyProperty(this, property);
    }

    public void showPawn() throws IOException {
        pawn.showSelf(this);
    }

    public TextColor getPawnColor(){
        return pawn.color;
    }

    public void buyHouse(Property property) throws IOException {
        PlayerController.buyHouse(this, property);
    }

    public String buyHouseDescription(boolean success){
        if (success) return "Bought House";
        else return "You already have 5 houses";
    }

    public String buyPropertyDescription(boolean success){
        if (success) return "Bought Property";
        else return "This cell is not a Property";
    }


    public String getName() {
        return this.name;
    }

    public boolean getState() {
        return this.jail;
    }

    public void setState(boolean jail) {
        this.jail = jail;
    }

    public int getPosition_index() {
        return position_index;
    }

    public void setPosition_index(int position) {
        position = position % 16;
        this.position_index = position;
    }
}