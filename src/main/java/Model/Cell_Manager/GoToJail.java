package Model.Cell_Manager;

import Model.Player.Player;

import java.io.IOException;

public class GoToJail extends Cell {

    public GoToJail() {
        super();
    }
    
    public void goToJail(Player player) throws IOException {
        player.setState(true);
        player.pawn.showJail();
        player.position_index = 4;
    }


    @Override
    public String getDescription() {
        return "Go directly to Jail!";
    }
}
