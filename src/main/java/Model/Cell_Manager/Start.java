package Model.Cell_Manager;

import Model.Player.Player;

// Concrete subclasses
public class Start extends Cell {
    private int money = 200;

    public Start() {
        super();
    }

    public int getMoney(){return money;}

    @Override
    public String getDescription() {
        return "This is the Start cell. Collect $200!";
    }
}
