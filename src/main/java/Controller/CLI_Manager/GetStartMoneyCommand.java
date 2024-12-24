package Controller.CLI_Manager;

import Model.Cell_Manager.Start;
import Model.Player.Player;

import java.io.IOException;

public class GetStartMoneyCommand implements Command{
    private Player player;
    private Start start;

    public GetStartMoneyCommand(Player player, Start start) {
        this.player = player;
        this.start = start;
    }
    public void execute() throws IOException, InterruptedException {
        player.deposit(start.getMoney());
    }

    public String getDescription() {
        return "You passed START and received: " + start.getMoney();
    }
}
