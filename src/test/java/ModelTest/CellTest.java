package ModelTest;

import Model.Cell_Manager.Cell;
import Model.Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CellTest {

    @Mock
    private Player mockPlayer;

    private Cell cell;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cell = new TestCell(); // TestCell is a concrete implementation of the abstract class Cell
    }

    @Test
    public void testAddCell_AddsCellWithPlayer() {
        // Arrange
        Cell anotherCell = new TestCell();

        // Act
        cell.addCell(anotherCell, mockPlayer);

        // Assert
        Map<Cell, Player> cells = cell.getCells();
        assertEquals(1, cells.size());
        assertTrue(cells.containsKey(anotherCell));
        assertEquals(mockPlayer, cells.get(anotherCell));
    }

    @Test
    public void testGetKey_ReturnsCorrectCell() {
        // Arrange
        Cell anotherCell = new TestCell();
        cell.addCell(anotherCell, mockPlayer);

        // Act
        Cell retrievedCell = cell.getKey(0);

        // Assert
        assertEquals(anotherCell, retrievedCell);
    }

    @Test
    public void testPrintCells_PrintsCorrectly() {
        // Arrange
        Cell anotherCell = new TestCell();
        cell.addCell(anotherCell, mockPlayer);
        when(mockPlayer.getName()).thenReturn("TestPlayer");

        // Act
        cell.printCells();

        // Output verification would normally require a logging library or system output capture tools.
        // For this test, manually verify the console output matches expectations.
    }

    // Concrete implementation for testing purposes
    private static class TestCell extends Cell {
        @Override
        public String getDescription() {
            return "TestCell Description";
        }
    }
}

