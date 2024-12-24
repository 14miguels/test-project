package ViewTest;

import View.DisplayView.DiceView;
import View.DisplayView.Display;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.io.IOException;

class DiceViewTest {

    private Display mockDisplay;
    private DiceView diceView;

    @BeforeEach
    void setUp() {
        mockDisplay = Mockito.mock(Display.class);
        diceView = new DiceView(mockDisplay);
    }

    @Test
    void testDisplayRollInJail() throws IOException {
        int dice1 = 3;
        int dice2 = 5;
        boolean inJail = true;

        diceView.displayRoll(dice1, dice2, inJail);

        verify(mockDisplay).showMessage("      Dice 1: " + dice1 + "       |     Dice 2: " + dice2 + "       ", 1, 6);
    }

    @Test
    void testDisplayRollNotInJail() throws IOException {
        int dice1 = 6;
        int dice2 = 0; // Dice 2 is irrelevant when not in jail.
        boolean inJail = false;

        diceView.displayRoll(dice1, dice2, inJail);

        verify(mockDisplay).showMessage("                  Dice: " + dice1, 1, 6);
    }

    @Test
    void testDisplayRollHandlesIOException() throws IOException {
        doThrow(new IOException("Error displaying message")).when(mockDisplay).showMessage(anyString(), anyInt(), anyInt());

        int dice1 = 4;
        int dice2 = 2;
        boolean inJail = true;

        try {
            diceView.displayRoll(dice1, dice2, inJail);
        } catch (IOException e) {
            assert e.getMessage().equals("Error displaying message");
        }

        verify(mockDisplay).showMessage("      Dice 1: " + dice1 + "       |     Dice 2: " + dice2 + "       ", 1, 6);
    }
}
