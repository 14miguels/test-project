package View.DisplayView;

import Controller.TurnController;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Display {
    private final Screen screen;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Display(Screen screen, int x, int y, int width, int height) {
        this.screen = screen;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawBorder() {
        TextGraphics textGraphics = screen.newTextGraphics();

        for (int i = x; i < x + width; i++) {
            textGraphics.putString(i, y, "_");
            textGraphics.putString(i, y + 13, "-");
            textGraphics.putString(i, y + height - 1, "-");
        }

        for (int i = y; i < y + height; i++) {
            textGraphics.putString(x, i, "|");
            textGraphics.putString(x + width - 1, i, "|");
        }

        textGraphics.putString(x, y, " ");
        textGraphics.putString(x + width - 1, y, " ");
        textGraphics.putString(x, y + height - 1, " ");
        textGraphics.putString(x + width - 1, y + height - 1, " ");

        textGraphics.putString(x+3, 32, "-".repeat(42)); // Top border from x = 2 to x = 43
        textGraphics.putString(x+3, 36, "-".repeat(42));

        // Draw the top and bottom borders using putString
        textGraphics.putString(x+3, 39, "-".repeat(42)); // Top border from x = 2 to x = 43
        textGraphics.putString(x+3, 49, "-".repeat(42)); // Bottom border from x = 2 to x = 43

        // Draw the left and right borders using putString
        for (int y = 33; y <= 35; y++) {
            textGraphics.putString(x+2, y, "|"); // Left border
            textGraphics.putString(x+45, y, "|"); // Right border
        }

        for (int y = 40; y <= 48; y++) {
            textGraphics.putString(x+2, y, "|"); // Left border
            textGraphics.putString(x+45, y, "|"); // Right border
        }
    }

    public void showMessage(String message, int relativeX, int relativeY) throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();

        int lineLength = width - 5 - relativeX;
        String blankLine = " ".repeat(Math.max(0, lineLength));
        textGraphics.putString(x + 1 + relativeX, y + 1 + relativeY, blankLine);

        if (relativeX >= 0 && relativeX < width - 2 && relativeY >= 0 && relativeY < height - 2) {
            int maxMessageLength = Math.min(width - 2 - relativeX, message.length());
            String trimmedMessage = message.substring(0, maxMessageLength);
            textGraphics.putString(x + 1 + relativeX, y + 1 + relativeY, trimmedMessage);
        }
        screen.refresh();
    }

    public String getInput(Screen screen) throws IOException {
        StringBuilder inputBuffer = new StringBuilder();

        int inputRow = 15;
        int inputColumnStart = 1;
        int maxInputWidth = 50;

        showMessage("Run 'commands' to list available commands", 1, 14);
        showMessage("Input: ", inputColumnStart, inputRow);

        while (true) {
            KeyStroke keyStroke = screen.readInput(); // Read key input

            if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() != 'q') {

                inputBuffer.append(keyStroke.getCharacter());
                showMessage("Input: " + inputBuffer.toString(), inputColumnStart, inputRow);

            } else if (keyStroke.getKeyType() == KeyType.Backspace) {
                if (!inputBuffer.isEmpty()) {
                    inputBuffer.deleteCharAt(inputBuffer.length() - 1);
                    showMessage("Input: " + inputBuffer.toString() + " ", 1, 15);
                }
            } else if (keyStroke.getKeyType() == KeyType.Enter) {

                String command = inputBuffer.toString().trim();
                inputBuffer.setLength(0);

                showMessage(" ".repeat(maxInputWidth), inputColumnStart, inputRow);

                for (int i = 0; i<14; i++){
                    showMessage(" ".repeat(maxInputWidth), inputColumnStart, inputRow + i);
                }

                showMessage("Input: ", inputColumnStart, inputRow);

                return command;
            } else if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') {
                TurnController.playing = false;
                return "quit";
            }

            screen.refresh();
        }
    }
}
