package View.DisplayView;

import Model.Cell_Manager.*;
import Model.Player.Player;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class DynamicView {

    private final Display display;
    private final Screen screen;

    public DynamicView(Display display, Screen screen) {
        this.display = display;
        this.screen = screen;
    }

    public void clear(boolean both) {
        TextGraphics textGraphics = screen.newTextGraphics();
        int leftX = 115; // Start x-coordinate inside the left border
        int rightX = 156; // End x-coordinate inside the right border
        int topYB = 40; // Start y-coordinate inside the top border
        int bottomYB = 48; // End y-coordinate inside the bottom border
        int topYP = 33; // Start y-coordinate inside the top border
        int bottomYP = 35; // End y-coordinate inside the bottom border

        for (int y = topYB; y <= bottomYB; y++) {
            textGraphics.putString(leftX, y, " ".repeat(rightX - leftX - 1)); // Fill each row with spaces
        }
        if(both){
            for (int y = topYP; y <= bottomYP; y++) {
                textGraphics.putString(leftX, y, " ".repeat(rightX - leftX - 1)); // Fill each row with spaces
            }
        }
    }

    public void viewDisplayPlayer(Player player) throws IOException {
        clear(true);
        String state = "(Free)";
        if (player.getState()) state = "(In Jail)";
        display.showMessage(player.getName()+ state,3, 33);
        display.showMessage("balance: "+ player.account_balance,30, 33);
    }

    public void viewDisplayCell(Player player, Cell cell, Player owner) throws IOException {
        clear(false);
        if (player.getState()){
            display.showMessage("You are in Jail",13, 45);
        } else {
            if (cell instanceof Property prop) {
                display.showMessage("Property: " + prop.getName(), 3, 39);

                if (owner == player) {
                    display.showMessage("Owner: " + owner.getName(), 29, 39);
                    display.showMessage("Details:", 3, 42);
                    display.showMessage("- Price: " + prop.price, 5, 43);
                    display.showMessage("- Current rent: " + prop.getRent(), 5, 44);
                    display.showMessage("- House price: " + prop.getHousePrice(), 5, 45);
                    display.showMessage("- House rent: " + prop.getHouseRent(), 5, 46);
                } else if (owner == null) {
                    display.showMessage("Available", 34, 39);
                    display.showMessage("Details:", 3, 42);
                    display.showMessage("- Price: " + prop.price ,5, 43);
                    display.showMessage("- Current rent: " + prop.getRent(), 5, 44);
                    display.showMessage("- House price: " + prop.getHousePrice(), 5, 45);
                    display.showMessage("- House rent: " + prop.getHouseRent(), 5, 46);
                } else {
                    display.showMessage("Owner: " + owner.getName(), 30, 39);
                    display.showMessage("Details:", 3, 43);
                    display.showMessage("- Current rent: " + prop.getRent(), 5, 44);
                }
            } else if (cell instanceof GoToJail gojail) {
                display.showMessage(gojail.getDescription(),13, 43);
            } else if (cell instanceof Start start) {
                display.showMessage(start.getDescription(),5, 43);
            } else if (cell instanceof Jail jail) {
                display.showMessage(jail.getDescription(),12, 43);
            } else if (cell instanceof ParkingSpot ps) {
                display.showMessage(ps.getDescription(),11, 43);
            } else {
                display.showMessage("Let's start! Roll the dice to move",15, 43);
                display.showMessage("          on the board            ",15, 44);
            }
        }


        // Refresh the screen to apply changes
        screen.refresh();
    }
}
