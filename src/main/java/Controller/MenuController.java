package Controller;

import Model.MenuModel;
import View.MenuView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class MenuController {
    private final MenuModel model;
    private final MenuView view;
    private final Screen screen;

    public MenuController(MenuModel model, MenuView view, Screen screen) {
        this.model = model;
        this.view = view;
        this.screen = screen;
    }

    public void runMenu() {
        try {
            view.displayMenu(model.getOptions().toArray(new String[0]));
            boolean running = true;

            while (running) {
                KeyStroke key = screen.readInput();
                if (key.getKeyType() == KeyType.Character) {
                    char ch = Character.toLowerCase(key.getCharacter());
                    switch (ch) {
                        case '1': // Start Game
                            running = false;
                            break;
                        case '2': // View Rules
                            view.clearScreen();
                            view.displayMenu(new String[]{"Rules:", "1. Rule 1...", "2. Rule 2...", "Press any key to return"});
                            screen.readInput();
                            view.clearScreen();
                            view.displayMenu(model.getOptions().toArray(new String[0]));
                            break;
                        case 'q': // Quit
                            running = false;
                            screen.stopScreen();
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
