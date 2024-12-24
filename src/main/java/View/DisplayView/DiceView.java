package View.DisplayView;

import java.io.IOException;

public class DiceView {

    private final Display display;

    public DiceView(Display display){
        this.display = display;
    }

    public void displayRoll(int i1, int i2, boolean inJail) throws IOException {
        if (inJail){
            display.showMessage("      Dice 1: " + i1 + "       |     Dice 2: " + i2 + "       ", 1, 6);
        } else {
            display.showMessage("                  Dice: " + i1, 1, 6);
        }

    }

}
