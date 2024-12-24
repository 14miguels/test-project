package Controller.CLI_Manager;

import java.io.IOException;

public interface Command {
    void execute() throws IOException, InterruptedException;
    String getDescription();
}
