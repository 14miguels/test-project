package Controller;

import Model.Dice;
import View.DisplayView.DiceView;
import View.DisplayView.Display;

import java.io.IOException;

public class DiceController {
    public static boolean doubles;
    private static final Dice dice = new Dice();
    public static DiceView view;

    public DiceController(Display display) {
        view = new DiceView(display);
    }

    public static int rollDices(boolean inJail) throws IOException {
        int finalDice1;
        int finalDice2;

        /*
        for (int i = 0; i < 20; i++) {
            try {
                int currentDice1 = dice.random.nextInt(6) + 1;
                int currentDice2 = dice.random.nextInt(6) + 1;

            } catch (Exception e) {
                System.err.println("An error occurred during dice roll: " + e.getMessage());
                e.printStackTrace();
            }
        }
        */
        finalDice1 = dice.random.nextInt(6) + 1;
        finalDice2 = dice.random.nextInt(6) + 1;

        if (inJail){
            doubles = (finalDice1 == finalDice2);
        } else {
            finalDice1 = dice.random.nextInt(6) + 1;
        }


        view.displayRoll(finalDice1, finalDice2, inJail);
        return finalDice1;
    }
}

