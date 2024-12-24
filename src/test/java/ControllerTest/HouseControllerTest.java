package ControllerTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Controller.HouseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import Model.House;
import Model.Position;
import View.BoardView.Board;
import View.BoardView.HouseView;
import java.io.IOException;

public class HouseControllerTest {

    @Mock
    private Board mockBoard;  // Mock the Board class

    @Mock
    private HouseView mockHouseView;  // Mock the HouseView class

    private HouseController houseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize the mocks
        houseController = new HouseController(mockBoard);
    }

    @Test
    public void testShowHouse_numLessThan5() throws IOException {
        // Arrange
        House house = mock(House.class);
        int x = 10;
        int num = 3;  // A number less than 5, should draw the house

        // Mock the behavior of the house
        when(house.getX()).thenReturn(0);
        when(house.getY()).thenReturn(0);

        // Inject the mock HouseView
        HouseView mockView = mock(HouseView.class);
        houseController = new HouseController(mockBoard);

        // Act
        houseController.showHouse(num, house, x);  // Call the method

        // Assert
        verify(house, times(1)).setX(x - 1);  // The X coordinate should be set to x - 1
        verify(mockView, times(1)).showHouse(any(Position.class));  // Verify that showHouse is called
    }

    @Test
    public void testShowHouse_numGreaterThanOrEqual5() throws IOException {
        // Arrange
        House house = mock(House.class);
        int x = 10;
        int num = 5;  // A number greater than or equal to 5, should draw a hotel

        // Mock the behavior of the house
        when(house.getX()).thenReturn(0);
        when(house.getY()).thenReturn(0);

        // Inject the mock HouseView
        HouseView mockView = mock(HouseView.class);
        houseController = new HouseController(mockBoard);

        // Act
        houseController.showHouse(num, house, x);  // Call the method

        // Assert
        verify(house, times(1)).setX(x + 7);  // The X coordinate should be set to x + 7 for hotel
        verify(mockView, times(1)).showHotel(any(Position.class));  // Verify that showHotel is called
    }

    @Test
    public void testShowHouse_xPositionCalculation() throws IOException {
        // Arrange
        House house = mock(House.class);
        int x = 15;
        int num = 1;  // A number less than 5, should draw the house

        // Mock the behavior of the house
        when(house.getX()).thenReturn(0);
        when(house.getY()).thenReturn(0);

        // Inject the mock HouseView
        HouseView mockView = mock(HouseView.class);
        houseController = new HouseController(mockBoard);

        // Act
        houseController.showHouse(num, house, x);  // Call the method

        // Assert
        verify(house, times(1)).setX(x - 9);  // The X coordinate should be set to x - 9 for num = 1
        verify(mockView, times(1)).showHouse(any(Position.class));  // Verify that showHouse is called
    }


}

