package Controller;

import Model.House;
import Model.Position;
import View.BoardView.Board;
import View.BoardView.HouseView;
import View.BoardView.PawnView;

import java.io.IOException;

public class HouseController {
    private static final int cellWidth = 22;
    private static final int cellHeight = 10;
    private static Board board;
    private static int x;
    private static int y;


    public HouseController(Board board){
        this.board = board;
    }

    public static void showHouse(int num, House house, int x) throws IOException {
        System.out.println("num: " + num);
        HouseView view = new HouseView(board);
        if (num < 5){
            if (num == 1) {house.setX(x - 9);}
            else if (num == 2) {house.setX(x - 5);}
            else if (num == 3) {house.setX(x - 1);}
            else if (num == 4) {house.setX(x + 3);}
            Position pos = new Position(house.getX(), house.getY());
            view.showHouse(pos);
        } else {
            house.setX(x + 7);
            System.out.println("Drawed house at position: " + house.getX() + " " +house.getY());
            Position pos = new Position(house.getX(), house.getY());
            view.showHotel(pos);
        }
    }

}