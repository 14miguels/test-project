package View.BoardView;

import Model.Position;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.List;

public class PawnView {
    protected static TextColor color;
    private static Board board;

    public PawnView(Board board, TextColor color) {
        this.board = board;
        this.color = color;
    }

    public static void removePawn(Position position) throws IOException {
        List<String> emptyMatrix = List.of("███", "███", "███");

        for (int i = 0; i < emptyMatrix.size(); i++) {
            for (int j = 0; j < emptyMatrix.get(i).length(); j++) {
                int x = position.getX() + j; // Current X-coordinate
                int y = position.getY() + i; // Current Y-coordinate

                // Determine the color based on the position
                boolean isTop = y < board.getHeight() / 4 - 2;
                boolean isBottom = y >= board.getHeight() * 3 / 4 + 1;
                boolean isLeft = x < board.getWidth() / 4 - 4;
                boolean isRight = x >= board.getWidth() * 3 / 4 + 6;

                boolean isTopLeftCorner = isTop && isLeft;
                boolean isTopRightCorner = isTop && isRight;
                boolean isBottomLeftCorner = isBottom && isLeft;
                boolean isBottomRightCorner = isBottom && isRight;

                TextColor color;

                if (isTopLeftCorner || isTopRightCorner || isBottomLeftCorner || isBottomRightCorner) {
                    color = TextColor.ANSI.YELLOW_BRIGHT; // Corners
                } else if (isTop) {
                    color = TextColor.ANSI.RED_BRIGHT; // Top row
                } else if (isBottom) {
                    color = TextColor.ANSI.RED_BRIGHT; // Bottom row
                } else if (isLeft) {
                    color = TextColor.ANSI.RED_BRIGHT; // Left column
                } else if (isRight) {
                    color = TextColor.ANSI.RED_BRIGHT; // Right column
                } else {
                    color = TextColor.ANSI.WHITE; // Default
                }

                // Fill in the gap with the appropriate color
                board.showMessage(String.valueOf(emptyMatrix.get(i).charAt(j)), x, y, color);
            }
        }
    }


    public static void draw(Position position) throws IOException {
        List<String> drawingMatrix = List.of(" O ", "/|\\", "/ \\");

        for (int i=0; i<drawingMatrix.size(); i++){
            board.showMessage(drawingMatrix.get(i), position.getX(), position.getY()+i, color);
        }
        System.out.println("Drawing pawn at: (" + position.getX() + ", " + position.getY() + "color:"+ color+")");
    }

}
