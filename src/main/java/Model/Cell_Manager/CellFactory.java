package Model.Cell_Manager;

public class CellFactory {
    public Cell createCell(String type, String name, int price, int rent, int housePrice, int hotelPrice, int x, int y) {
        switch (type) {
            case "Start":
                return new Start();
            case "Jail":
                return new Jail();
            case "GoToJail":
                return new GoToJail();
            case "ParkingSpot":
                return new ParkingSpot();
            case "Property":
                return new Property(name, price, rent, housePrice, hotelPrice, x, y);
            default:
                throw new IllegalArgumentException("Unknown cell type: " + type);
        }
    }
}
