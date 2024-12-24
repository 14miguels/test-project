package Controller.CLI_Manager;

import Model.Cell_Manager.Cell;
import Model.Cell_Manager.Property;
import Model.Player.Player;

import java.io.IOException;

public class PayRentCommand implements Command {
    Player player;
    Property cell;
    boolean success;

    PayRentCommand(Player player, Property cell){
        this.player = player;
        this.cell = cell;
    }

    public void execute() {
        success = cell.getRent() <= player.account_balance;
        player.withdraw(cell.getRent());
    }

    public String getDescription() {
        if (success) {
            return "You payed: " + cell.getRent();
        } else {
            return "You payed the rent but you are in debt";
        }
    }

}
