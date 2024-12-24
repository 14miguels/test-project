package ViewTest;

import View.BoardView.Board;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class BoardTest {

    private Board board;
    private Screen screenMock;
    private TextGraphics textGraphicsMock;
    private List<String> lines;

    @BeforeEach
    void setUp() {
        lines = Arrays.asList(
                "##########",
                "#        #",
                "#        #",
                "##########"
        );

        screenMock = mock(Screen.class);
        textGraphicsMock = mock(TextGraphics.class);

        board = new Board(lines, screenMock, 20, 10);
    }

    @Test
    void drawBoardWithColor_drawsCorrectly() {
        board.drawBoardWithColor(textGraphicsMock);

        verify(textGraphicsMock, atLeastOnce()).putString(anyInt(), anyInt(), anyString());
        verify(textGraphicsMock, atLeastOnce()).setForegroundColor(any(TextColor.class));
    }

    @Test
    void showMessage_displaysMessageWithinBounds() throws IOException {
        String message = "Test Message";
        int relativeX = 2;
        int relativeY = 3;

        board.showMessage(message, relativeX, relativeY);

        verify(screenMock).refresh();
        verify(textGraphicsMock).putString(eq(relativeX + 1), eq(relativeY + 1), eq(message));
    }

    @Test
    void showMessage_trimsMessageWhenTooLong() throws IOException {
        String message = "This message is way too long for the board";
        int relativeX = 2;
        int relativeY = 3;

        board.showMessage(message, relativeX, relativeY);

        int maxMessageLength = 20 - 2 - relativeX;
        String trimmedMessage = message.substring(0, maxMessageLength);
        verify(textGraphicsMock).putString(eq(relativeX + 1), eq(relativeY + 1), eq(trimmedMessage));
        verify(screenMock).refresh();
    }

    @Test
    void getWidth_returnsCorrectWidth() {
        int width = board.getWidth();
        assert width == 20;
    }

    @Test
    void getHeight_returnsCorrectHeight() {
        int height = board.getHeight();
        assert height == 10;
    }
}

