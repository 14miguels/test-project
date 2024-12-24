package Controller.CLI_Manager;

import View.DisplayView.Display;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandLogger {
    private final List<String> commandLog = new ArrayList<>();

    // Log a command description
    public void logCommand(Command command) {
        commandLog.add(command.getDescription());
    }

    // Display the command log using the provided Display object
    public void displayLog(Display display, int column, int startRow) throws IOException {
        for (int i = 0; i < commandLog.size(); i++) {
            display.showMessage(commandLog.get(i), column, startRow + i);
        }
    }
}
