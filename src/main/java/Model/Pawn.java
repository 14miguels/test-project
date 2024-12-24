package Model;

import Controller.PawnController;
import Model.Player.Player;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class Pawn{
    int x = 97;
    int y = 44;
    Player player;
    public TextColor color;

    public Pawn(Player player, TextColor color){
        this.player = player;
        this.color = color;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void showSelf(Player player) throws IOException {
        PawnController.showSelf(player);
    }

    public void move(int steps) throws IOException {
        PawnController.move(steps, player);
    }

    public void showJail() throws IOException {
        PawnController.showJail(player);
    }
}
