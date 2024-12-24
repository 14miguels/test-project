package Controller;

import Model.Cell_Manager.Cell;
import Model.Player.Player;
import View.DisplayView.Display;
import View.DisplayView.DynamicView;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class DynamicViewController {
    private final DynamicView view;

    private Cell cell;
    private Player owner;
    private Player player;

    private String player_name;
    private int rent;

    public DynamicViewController(Display display, Screen screen) {
        this.view = new DynamicView(display, screen); // Pass the display instance to DynamicView
    }

     public void updatePlayer(Player player) throws IOException {
        if (player != null){
            this.player = player;
        }

        viewDisplayPlayer();
     }

    public void updateVariables(Player player, Cell celli, Player owneri) throws IOException {
        if (player != null) {
            this.player = player;
        }
        if (celli != null) {
            this.cell = celli;
        }
        if (owneri != null) {
            this.owner = owneri;
        }

        viewDisplayCell();
    }


    public void viewDisplayCell() throws IOException {
        view.viewDisplayCell(player, cell, owner);
    }

    public void viewDisplayPlayer() throws IOException {
        view.viewDisplayPlayer(player);
    }
}
