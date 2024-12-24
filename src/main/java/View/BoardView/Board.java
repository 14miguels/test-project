package View.BoardView;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.List;

public class Board {
    private List<String> lines;
    private Screen screen;

    private final int width;
    private final int height;

    public Board(List<String> lines, Screen screen, int width, int height) {
        this.lines = lines;
        this.screen = screen;
        this.width = width;
        this.height = height;
    }

    // Method to draw the board with color based on regions
    public void drawBoardWithColor(TextGraphics graphics) {
        int y = 0;

        for (String line : lines) {
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);

                // Determine color based on position
                boolean isTop = y < height / 4 - 2;
                boolean isBottom = y >= height * 3 / 4 + 1;
                boolean isLeft = x < width / 4 - 4;
                boolean isRight = x >= width * 3 / 4 + 6;

                boolean isTopLeftCorner = isTop && isLeft;
                boolean isTopRightCorner = isTop && isRight;
                boolean isBottomLeftCorner = isBottom && isLeft;
                boolean isBottomRightCorner = isBottom && isRight;

                if (isTopLeftCorner || isTopRightCorner || isBottomLeftCorner || isBottomRightCorner) {
                    graphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT); // Corners (Red)
                } else if (isTop) {
                    graphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT); // Top row (Magenta)
                } else if (isBottom) {
                    graphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT); // Bottom row (Blue)
                } else if (isLeft) {
                    graphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT); // Left column (Yellow)
                } else if (isRight) {
                    graphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT); // Right column (Green)
                } else {
                    graphics.setForegroundColor(TextColor.ANSI.WHITE); // Default (White)
                }

                // Draw the character
                // graphics.setBackgroundColor(TextColor.ANSI.WHITE);
                graphics.putString(x, y, String.valueOf(c));
            }
            y++;
        }

    }

    public void showMessage(String message, int relativeX, int relativeY) throws IOException {
        showMessage(message, relativeX, relativeY, TextColor.ANSI.WHITE);
    }

    public void showMessage(String message, int relativeX, int relativeY, TextColor color) throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(color); // Set text color

        if (relativeX >= 0 && relativeX < width - 2 && relativeY >= 0 && relativeY < height - 2) {
            int maxMessageLength = Math.min(width - 2 - relativeX, message.length());
            String trimmedMessage = message.substring(0, maxMessageLength);
            textGraphics.putString(1 + relativeX, 1 + relativeY, trimmedMessage);
        }
        screen.refresh();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}