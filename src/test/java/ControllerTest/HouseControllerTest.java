package ControllerTest;

import Controller.HouseController;
import Model.House;
import Model.Position;
import View.BoardView.Board;
import View.BoardView.HouseView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class HouseControllerTest {

    @Mock
    private Board mockBoard;

    @Mock
    private HouseView mockHouseView;

    @Mock
    private House mockHouse;

    private HouseController houseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        houseController = new HouseController(mockBoard);
    }

    @Test
    public void testShowHouse_DrawsHouseCorrectly() throws IOException {
        // Arrange
        when(mockHouse.getY()).thenReturn(5);
        doNothing().when(mockHouseView).showHouse(any(Position.class));

        // Inject mock HouseView
        HouseView view = new HouseView(mockBoard);
        HouseView spyView = Mockito.spy(view);
        doNothing().when(spyView).showHouse(any(Position.class));

        // Set coordinate check assertions

        // Verify function invokes Position

        // Check Positions

    }
}