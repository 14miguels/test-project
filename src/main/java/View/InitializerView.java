package View;

import View.BoardView.Board;
import View.DisplayView.Display;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class InitializerView {
    private final Screen screen;
    private final Display display;
    private final Board board;

    public InitializerView(Display display, Board board, Screen screen){
        this.display = display;
        this.screen = screen;
        this.board = board;

    }

    public void draw() throws IOException {
        screen.clear();
        board.drawBoardWithColor(screen.newTextGraphics());
        display.drawBorder();
        screen.refresh();

    }
}
