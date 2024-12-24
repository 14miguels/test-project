package Controller.CLI_Manager;

import Model.Cell_Manager.Cell;
import Model.Player.Player;
import Model.Cell_Manager.Property;

import java.io.IOException;

public class BuyHouseCommand implements Command {
    private final Player player;
    private final Cell cell;
    private boolean success;

    public BuyHouseCommand(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }

    public void execute() throws IOException {
        if ((cell.getKey(player.position_index) instanceof Property property) && (property.getHouseNumber()<5) ){
            player.buyHouse(property);
            success=true;
        } else {
            success = false;
        }
    }

    public String getDescription() {
        return player.buyHouseDescription(success);
    }
}