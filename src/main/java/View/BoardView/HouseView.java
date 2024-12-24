package View.BoardView;

import Model.Position;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.List;

public class HouseView {
    private static Board board;

    public HouseView(Board board){
        this.board = board;
    }
    public static void showHouse(Position position) throws IOException {
        List<String> emptyMatrix = List.of("/^\\", "|_|");
        for (int i=0; i<emptyMatrix.size(); i++){
            board.showMessage(emptyMatrix.get(i), position.getX(), position.getY() + i);
        }
    }

    public static void showHotel(Position position) throws IOException {
        List<String> emptyMatrix = List.of("/H\\", "|_|");
        for (int i=0; i<emptyMatrix.size(); i++){
            board.showMessage(emptyMatrix.get(i), position.getX(), position.getY() + i);
        }
    }

    public static void removeHouse(Position position) throws IOException {
        List<String> emptyMatrix = List.of("HHH", "HHH");
    }
}
