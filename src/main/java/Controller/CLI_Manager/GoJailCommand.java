package Controller.CLI_Manager;

import Model.Cell_Manager.Cell;
import Model.Cell_Manager.GoToJail;
import Model.Cell_Manager.Property;
import Model.Player.Player;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GoJailCommand implements Command{
    Player player;
    Cell cell;

    public GoJailCommand(Player player, Cell cells) {
        this.player = player;
        this.cell = cells.getKey(12);
    }

    public void execute() throws IOException{
        if (cell instanceof GoToJail) {
            ((GoToJail) cell).goToJail(player);
        }
        if (cell instanceof Property){
            System.out.println("cell: "+(((Property) cell).getName()));
        }

        System.out.println("cell: " +cell.getClass().getSimpleName());
        System.out.println("position: " +player.position_index);
    }

    public String getDescription() {
        return "You went to jail";
    }
}
