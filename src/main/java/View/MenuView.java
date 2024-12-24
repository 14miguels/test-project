package View;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class MenuView {
    private final Screen screen;

    public MenuView(Screen screen) {
        this.screen = screen;
    }

    public void displayMenu(String[] options) throws IOException {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.ANSI.WHITE);

        // Get the screen's width and height
        int screenWidth = screen.getTerminalSize().getColumns();
        int screenHeight = screen.getTerminalSize().getRows();

        // ASCII art for "MONOPOLY"
        String[] monopolyArt = {
                " /$$      /$$                                                   /$$          ",
                "| $$$    /$$$                                                  | $$          ",
                "| $$$$  /$$$$  /$$$$$$  /$$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$ | $$ /$$   /$$",
                "| $$ $$/$$ $$ /$$__  $$| $$__  $$ /$$__  $$ /$$__  $$ /$$__  $$| $$| $$  | $$",
                "| $$  $$$| $$| $$  \\ $$| $$  \\ $$| $$  \\ $$| $$  \\ $$| $$  \\ $$| $$| $$  | $$",
                "| $$\\  $ | $$| $$  | $$| $$  | $$| $$  | $$| $$  | $$| $$  | $$| $$| $$  | $$",
                "| $$ \\/  | $$|  $$$$$$/| $$  | $$|  $$$$$$/| $$$$$$$/|  $$$$$$/| $$|  $$$$$$$",
                "|__/     |__/ \\______/ |__/  |__/ \\______/ | $$____/  \\______/ |__/ \\____  $$",
                "                                           | $$                     /$$  | $$",
                "                                           | $$                    |  $$$$$$/ ",
                "                                           |__/                     \\______/  "
        };

        // Calculate the starting Y position for the ASCII art
        int startY = (screenHeight - monopolyArt.length) / 2;

        // Display ASCII art at the center
        for (int i = 0; i < monopolyArt.length; i++) {
            tg.putString((screenWidth - monopolyArt[i].length()) / 2, startY + i, monopolyArt[i]);
        }

        // Calculate the starting Y position for the options
        int startOptionY = startY + monopolyArt.length + 2; // 2 spaces below the ASCII art

        // Dynamically display the menu options centered
        for (String option : options) {
            tg.putString((screenWidth - option.length()) / 2, startOptionY, option);
            startOptionY += 2;  // Move to the next line
        }

        screen.refresh();
    }





    public void clearScreen() throws IOException {
        screen.clear();
        screen.refresh();
    }
}
