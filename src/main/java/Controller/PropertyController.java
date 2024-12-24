package Controller;

import Model.Cell_Manager.Property;
import Model.House;

import java.io.IOException;

public class PropertyController {

    public static void acquireHouse(Property property) throws IOException {
        int houses = property.getHouseNumber() +1;
        property.setHouseNumber(houses);

        House house = new House(property.x, property.y);
        house.showHouse(property.getHouseNumber());

        if(property.getHouseNumber() < 5){ property.setRent(property.getRent() + property.getHouseRent() * property.getHouseNumber());}
        else if(property.getHouseNumber() == 5){ property.setRent(property.getRent() + property.getHouseRent() * 6);}
    }
}
