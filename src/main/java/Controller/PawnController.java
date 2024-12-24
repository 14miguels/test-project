package Controller;

import Model.Player.Player;
import Model.Position;
import View.BoardView.Board;
import View.BoardView.PawnView;

import java.io.IOException;

public class PawnController {
    private static final int cellWidth = 22;
    private static final int cellHeight = 10;
    private static PawnView view;
    private static Board board;
    private static int x;
    private static int y;



    public PawnController(Board board, int height, int width){
        //view = new PawnView(board);
        this.board = board;
        x = width - 13;
        y = height - 9;
        System.out.println(x);
        System.out.println(y);
    }

    public static void showSelf(Player player) throws IOException {
        view = new PawnView(board, player.getPawnColor());
        if (player.ref == 1) {player.setPawnX(91); player.setPawnY(44);}
        else if (player.ref == 2) {player.setPawnX(95); player.setPawnY(44);}
        else if (player.ref == 3) {player.setPawnX(99); player.setPawnY(44);}
        else {player.setPawnX(103); player.setPawnY(44);}
        Position pos = new Position(player.getPawnX(), player.getPawnY());
        view.draw(pos);
    }

    public static void move(int steps, Player player) throws IOException {
        view = new PawnView(board, player.getPawnColor());

        x = player.getPawnX();
        y = player.getPawnY();

        int pos = player.getPosition_index();
        Position position1 = new Position(x ,y);
        view.removePawn(position1);

        int totalCells = 16;

        for (int i = 0; i < steps; i++) {
            int currentPosition = (pos + i) % totalCells;

            // Move down
            if (currentPosition >= 12) {
                y += cellHeight;
                System.out.println("Went down");
            }
            // Move right
            else if (currentPosition >= 8) {
                x += cellWidth;
                System.out.println("Went right");
            }
            // Move up
            else if (currentPosition >= 4) {
                y -= cellHeight;
                System.out.println("Went up");
            }
            // Move left
            else if (currentPosition >= 0) {
                x -= cellWidth;
                System.out.println("Went left");
            }
        }

        Position position2 = new Position(x, y);

        int newPos = (pos + steps) % totalCells;
        player.setPosition_index(newPos);

        player.setPawnX(x);
        player.setPawnY(y);

        view.draw(position2);
    }
    public static void showJail(Player player) throws IOException {
        Position position1 = new Position(player.getPawnX(), player.getPawnY());
        PawnView.removePawn(position1);
        if (player.ref == 1) {player.setPawnX(91 - 4 * cellWidth);}
        else if (player.ref == 2) {player.setPawnX(95 - 4 * cellWidth);}
        else if (player.ref == 3) {player.setPawnX(99 - 4 * cellWidth);}
        else {player.setPawnX(103 - 4 * cellWidth);}
        player.setPawnY(44);
        Position position2 = new Position(player.getPawnX(), 44);
        PawnView.draw(position2);
        System.out.println("Jail? " + player.getState());
    }
}
