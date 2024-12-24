package Controller.Cell_Manager;

import Model.Cell_Manager.Cell;
import Model.Cell_Manager.CellFactory;

public class CellManager {
    private static final int cellWidth = 22;
    private static final int cellHeight = 10;
    private final int x = 98;
    private final int y = 41;

    Cell cell = new Cell() {
        @Override
        public String getDescription() {
            return "";
        }
    };

    public Cell getCell() {
        return cell;
    }
    public void initCells (){
        CellFactory factory = new CellFactory();
        cell.addCell(factory.createCell("Start", "start", 0, 0, 0, 0, 97, 44), null);
        cell.addCell(factory.createCell("Property", "Asprela", 100, 10, 50, 30, x - cellWidth, y), null);
        cell.addCell(factory.createCell("Property", "Antas", 120, 12, 60, 40, x -2 * cellWidth, y), null);
        cell.addCell(factory.createCell("Property", "Ramalde", 140, 14, 70, 50, x -3 * cellWidth, y), null);
        cell.addCell(factory.createCell("Jail", "jail", 0, 0, 0, 0, x -4 * cellWidth, y), null);
        cell.addCell(factory.createCell("Property", "Massarelos", 160, 16, 80, 60, x - 4 * cellWidth, y - cellHeight), null);
        cell.addCell(factory.createCell("Property", "Campanhã", 180, 18, 90, 70, x - 4 * cellWidth, y - 2 * cellHeight) , null);
        cell.addCell(factory.createCell("Property", "Cedofeita", 200, 20, 100, 80, x - 4 * cellWidth, y - 3 * cellHeight), null);
        cell.addCell(factory.createCell("ParkingSpot", "parking", 0, 0, 0, 0, x - 4 * cellWidth, y - 4 * cellHeight), null);
        cell.addCell(factory.createCell("Property", "Cordoaria", 220, 22, 110, 90, x - 3 * cellWidth, y - 4 * cellHeight), null);
        cell.addCell(factory.createCell("Property", "Clérigos", 240, 24, 120, 100, x - 2 * cellWidth, y - 4 * cellHeight), null);
        cell.addCell(factory.createCell("Property", "Boavista", 260, 26, 130, 110, x - cellWidth, y - 4 * cellHeight), null);
        cell.addCell(factory.createCell("GoToJail", "gojail", 0, 0, 0, 0, x - cellWidth, y - 4 * cellHeight), null);
        cell.addCell(factory.createCell("Property", "Foz do Douro", 270, 27, 135, 115, x, y - 3 * cellHeight), null);
        cell.addCell(factory.createCell("Property", "Leça da Palmeira", 280, 28, 140, 120, x, y - 2 * cellHeight), null);
        cell.addCell(factory.createCell("Property", "Viana do Castelo", 300, 30, 150, 130, x, y - cellHeight), null);
    }

}