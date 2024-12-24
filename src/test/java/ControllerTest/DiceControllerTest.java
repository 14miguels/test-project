package ControllerTest;

import Controller.DiceController;
import Model.Dice;
import View.DisplayView.DiceView;
import View.DisplayView.Display;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Random;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiceControllerTest {

    @Mock
    private Display mockDisplay;

    @Mock
    private DiceView mockDiceView;

    @Mock
    private Dice mockDice;

    private DiceController diceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockDice.random).thenReturn(new Random());
        diceController = new DiceController(mockDisplay);
        //DiceController.view = mockDiceView; // Ensure view is set to the mocked DiceView
    }

    @Test
    public void testRollDices_WithDoublesInJail() throws IOException {
        // Arrange
        when(mockDice.random.nextInt(6)).thenReturn(2, 2); // Mock doubles (3, 3)

        // Act
        int result = DiceController.rollDices(true);

        // Assert
        assertEquals(3, result);
        //assertTrue(DiceController.doubles);
    }

    @Test
    public void testRollDices_WithoutDoublesInJail() throws IOException {
        // Arrange
        when(mockDice.random.nextInt(6)).thenReturn(2, 4); // Mock dice rolls (3, 5)

        // Act
        int result = DiceController.rollDices(true);

        // Assert
        assertEquals(3, result);
        //assertFalse(DiceController.doubles);
    }

    @Test
    public void testRollDices_NotInJail() throws IOException {
        // Arrange
        when(mockDice.random.nextInt(6)).thenReturn(2, 4, 5); // Mock dice rolls (3, 6)

        // Act
        int result = DiceController.rollDices(false);

        // Assert
        assertEquals(6, result);
    }

    @Test
    public void testRollDices_ExceptionHandling() throws IOException {
        // Arrange
        when(mockDice.random.nextInt(6)).thenThrow(new RuntimeException("Random failure"));

        // Act & Assert
        assertDoesNotThrow(() -> DiceController.rollDices(false));
    }
}

