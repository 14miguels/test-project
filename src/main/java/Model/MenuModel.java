package Model;

import java.util.ArrayList;
import java.util.List;

public class MenuModel {
    private final List<String> options;

    public MenuModel() {
        options = new ArrayList<>();
        options.add("1. Start Game");
        options.add("2. View Rules");
        options.add("Press Q to Quit");
    }

    public List<String> getOptions() {
        return options;
    }
}

