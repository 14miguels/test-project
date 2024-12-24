package Controller.CLI_Manager;

import Model.Cell_Manager.Cell;
import Model.Cell_Manager.Property;
import Model.Player.Player;

import java.io.IOException;

public class ReceiveRentCommand implements Command{
    Player player;
    Property cell;


    ReceiveRentCommand(Property cell, Player player){
        this.cell = cell;
        this.player = player;
    }

    public void execute() throws IOException, InterruptedException {
        player.deposit(cell.getRent());
    }

    public String getDescription() {
        return "You received " + cell.getRent() + "in rent";
    }
}
