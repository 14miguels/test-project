package Controller.CLI_Manager;

import Model.Cell_Manager.Cell;
import Model.Player.Player;
import Model.Cell_Manager.Property;
import Model.Player.Player;

public class BuyPropertyCommand implements Command {
    private Player player;
    private Cell cell;
    private boolean success;

    public BuyPropertyCommand(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }

    public void execute() {
        if (cell.getKey(player.position_index) instanceof Property property){
            player.buyProperty(property);
            cell.addCell(property, player);
            System.out.println("Bought the hosue");
            success = true;
        }
        else {
            success = false;
        }
    }

    public String getDescription() {
        return player.buyPropertyDescription(success);
    }
}