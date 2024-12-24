package ViewTest;

import Model.Position;
import View.BoardView.Board;
import View.BoardView.HouseView;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.util.List;


import static org.mockito.Mockito.*;

class HouseViewTest {

    @Mock
    private Board boardMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowHouse() throws IOException {
        // Arrange
        Position position = new Position(5, 5);
        HouseView houseView = new HouseView(boardMock);
        List<String> expectedMatrix = List.of("/^\\", "|_|");

        // Act
        HouseView.showHouse(position);

        // Assert
        for (int i = 0; i < expectedMatrix.size(); i++) {
            verify(boardMock).showMessage(expectedMatrix.get(i), position.getX(), position.getY() + i);
        }
    }

    @Test
    void testShowHotel() throws IOException {
        // Arrange
        Position position = new Position(3, 3);
        HouseView houseView = new HouseView(boardMock);
        List<String> expectedMatrix = List.of("/H\\", "|_|");

        // Act
        HouseView.showHotel(position);

        // Assert
        for (int i = 0; i < expectedMatrix.size(); i++) {
            verify(boardMock).showMessage(expectedMatrix.get(i), position.getX(), position.getY() + i);
        }
    }

    @Test
    void testRemoveHouseDoesNothing() throws IOException {
        // Arrange
        Position position = new Position(1, 1);
        HouseView houseView = new HouseView(boardMock);

        // Act
        HouseView.removeHouse(position);

        // Assert
        verifyNoInteractions(boardMock);
    }

    @Test
    void testShowHouseHandlesIOException() throws IOException {
        // Arrange
        Position position = new Position(0, 0);
        doThrow(IOException.class).when(boardMock).showMessage(anyString(), anyInt(), anyInt());
        HouseView houseView = new HouseView(boardMock);

        // Act & Assert
        try {
            HouseView.showHouse(position);
        } catch (IOException e) {
            // Expected exception
        }

        verify(boardMock).showMessage("/^\\", 0, 0);
    }


}
