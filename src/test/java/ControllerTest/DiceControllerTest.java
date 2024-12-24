package ControllerTest;

import Controller.DiceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import View.DisplayView.Display;

public class DiceControllerTest {

    @Test
    public void testRollDicesInJail() throws IOException {
        // Check if doubles is set correctly
        DiceController.doubles = false;
        int result = DiceController.rollDices(true);
        assertTrue(result >= 1 && result <= 6, "Dice roll should be between 1 and 6.");
    }

    @Test
    public void testRollDicesNotInJail() throws IOException {
        int result = DiceController.rollDices(false);
        assertTrue(result >= 1 && result <= 6, "Dice roll should be between 1 and 6.");
    }
}




