package Controller;

import Controller.CLI_Manager.CLI;
import Controller.Cell_Manager.CellManager;
import Model.MenuModel;
import View.BoardView.Board;
import View.DisplayView.Display;
import View.InitializerView;
import View.MenuView;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Game {
    private static final String FILE_PATH = "src/main/java/Assets/board.txt"; // Path to the board text file
    private static final int BOARD_WIDTH = 110; // Width of the board display
    private static final int CLI_WIDTH = 50; // Width of the CLI display
    private static final int HEIGHT = 53; // Height of the terminal

    private Screen screen;
    private Board board;
    private Display display;
    private CLI cli;
    private InitializerView view;

    private DiceController diceCTRL;
    private PropertyController propCTRL;
    private TurnController turnCTRL;
    private CellManager cellCTRL;
    private PawnController pawnCTRL;
    private HouseController houseCTRL;
    private DynamicViewController dynaCTRL;

    public Game() {
        try {
            // Initialize the screen
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            terminalFactory.setInitialTerminalSize(new TerminalSize(BOARD_WIDTH + CLI_WIDTH, HEIGHT)); // Adjust size
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.startScreen();

            // Initialize Board
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            board = new Board(lines, screen, BOARD_WIDTH, HEIGHT);

            // Initialize Display
            display = new Display(screen, BOARD_WIDTH + 1, 0, CLI_WIDTH - 2, HEIGHT - 2);

            // Initialize View
            view = new InitializerView(display, board, screen);

            // Initialize the singleton classes
            diceCTRL = new DiceController(display);
            propCTRL = new PropertyController();
            cellCTRL = new CellManager();
            pawnCTRL = new PawnController(board, HEIGHT, BOARD_WIDTH);
            houseCTRL = new HouseController(board);
            dynaCTRL = new DynamicViewController(display, screen);

            turnCTRL = new TurnController(display, screen, cellCTRL, dynaCTRL);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void run() {
        try {
            // Run the menu
            MenuModel menuModel = new MenuModel();
            MenuView menuView = new MenuView(screen);
            MenuController menuController = new MenuController(menuModel, menuView, screen);
            menuController.runMenu();

            // Start the game after the menu
            view.draw();
            cellCTRL.initCells();
            turnCTRL.run();
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stopScreen();
        }
    }


    private void stopScreen() {
        try {
            if (screen != null) {
                screen.stopScreen();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
