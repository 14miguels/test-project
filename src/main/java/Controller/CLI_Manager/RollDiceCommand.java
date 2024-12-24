package Controller.CLI_Manager;

import Model.Player.Player;

import java.io.IOException;

public class RollDiceCommand implements Command {
    private Player player;

    public RollDiceCommand(Player player) {
        this.player = player;
    }

    public void execute() throws IOException {
        player.rollDice();
    }

    public String getDescription() {
        return "executed: RollDice";
    }
}