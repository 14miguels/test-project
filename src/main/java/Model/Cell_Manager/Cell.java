package Model.Cell_Manager;

import Model.Player.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Cell {
    LinkedHashMap<Cell, Player> cells;

    // Constructor to initialize the Map
    public Cell() {
        cells = new LinkedHashMap<>();
    }

    public void printCells() {
        for (Map.Entry<Cell, Player> entry : cells.entrySet()) {
            Cell cell = entry.getKey();
            Player player = entry.getValue();

            String playerName = (player != null) ? player.getName() : "No player";
            System.out.println("Cell: " + cell.getDescription() + ", Player: " + playerName);
        }
    }

    // Add a cell with an associated player
    public void addCell(Cell cell, Player player) {
        cells.put(cell, player);
    }

    // Getter for cells
    public Map<Cell, Player> getCells() {
        return cells;
    }

    public Cell getKey(int index) {
        return new ArrayList<>(cells.keySet()).get(index);
    }

    // Abstract method for description
    public abstract String getDescription();
}
