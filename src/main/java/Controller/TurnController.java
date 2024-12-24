package Controller;

import Controller.CLI_Manager.CLI;
import Controller.Cell_Manager.CellManager;
import Model.Player.Player;
import View.DisplayView.Display;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurnController {
    private final Display display;
    private final Screen screen;
    public static boolean playing = true;

    private final CellManager cellCTRL;
    private final DynamicViewController dynaCTRL;

    List<Player> playerList = new ArrayList<>();
    Map<Player, CLI> playerCliMap = new HashMap<>();

    TurnController(Display display, Screen screen, CellManager cellController, DynamicViewController dynaCTRL){
        this.display = display;
        this.screen = screen;

        this.cellCTRL = cellController;
        this.dynaCTRL = dynaCTRL;
    }

    void initTurns() throws IOException {
        List<TextColor> colors = List.of(TextColor.ANSI.RED, TextColor.ANSI.YELLOW, TextColor.ANSI.GREEN, TextColor.ANSI.BLUE);
        for (int i = 1; i < 5; i++) {
            System.out.println("initTurns: "+i);
            Player player = new Player("player" + i, i, colors.get(i-1));
            System.out.println(colors.get(i-1));
            CLI cli = new CLI(player, display, screen, cellCTRL, dynaCTRL);
            playerList.add(player);
            playerCliMap.put(player, cli);
            player.showPawn();
        }
    }

    void run() throws IOException {
        initTurns();



        while (playing) {
            System.out.println("while");
            for (int i = 0; i < playerList.size(); i++) {
                Player player = playerList.get(i);
                CLI cli = playerCliMap.get(player);

                if (playing){
                    cli.run();
                }

                if (player.account_balance < 0) {
                    playerList.remove(i);
                    playerCliMap.remove(player);
                    i--;
                }

                if (playerList.size() == 1) {
                    Player winner = playerList.get(0);
                    display.showMessage("The winner is: " + winner.getName(), 1, 18);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.err.println("Error while pausing: " + e.getMessage());
                    }

                    playing = false;
                    break;
                }
            }
        }
    }

}

